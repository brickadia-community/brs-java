package Minecraft;

import com.kmschr.brs.*;
import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class MinecraftExample {

    final int CHUNK_WIDTH = 16;
    final int CHUNK_HEIGHT = 256;
    final int REGION_SIZE = 32;

    @Test
    void testWriteMinecraft() throws IOException {
        SaveData save = new SaveData();
        save.setDescription("Minecraft chunk");
        save.setBrickAssets(List.of("PB_DefaultMicroBrick", "PB_DefaultMicroWedge"));

        readRegion("region/r.-1.0.mca", save, -1, 0);

        for (String block : Block.unsupported) {
            System.out.println(block);
        }

        System.out.println("Writing " + save.getBricks().size() + " bricks");
        BRS.writeSave("minecraft.brs", save);
    }

    void readRegion(String filename, SaveData save, int rx, int ry) throws IOException {
        System.out.println("Reading region " + rx + ", " + ry);
        MCAFile mcaFile = MCAUtil.read(filename);

        Block[][][] blocks = new Block[REGION_SIZE * CHUNK_WIDTH][CHUNK_HEIGHT][REGION_SIZE * CHUNK_WIDTH];

        for (int chunkX=0; chunkX < REGION_SIZE; chunkX++) {
            for (int chunkY = 0; chunkY < REGION_SIZE/3; chunkY++) {
                Chunk chunk = mcaFile.getChunk(chunkX, chunkY);
                if (chunk != null)
                    readChunk(blocks, chunk, chunkX, chunkY);
            }
        }

        System.out.println("Processing blocks...");

        for (int x=0; x < REGION_SIZE * CHUNK_WIDTH; x++) {
            if (x % (REGION_SIZE * CHUNK_WIDTH / 10) == 0) {
                System.out.println("#");
            }
            for (int y=0; y < CHUNK_HEIGHT; y++) {
                for (int z=0; z < REGION_SIZE * CHUNK_WIDTH; z++) {
                    if (blocks[x][y][z] == null || blocks[x][y][z].isAir())
                        continue;

                    // remove bottom bedrock
                    if ((y == 0 || y == 1) && !(x == 0 || z == 0 || x == REGION_SIZE * CHUNK_WIDTH - 1 || z == REGION_SIZE * CHUNK_WIDTH - 1))
                        continue;

                    // Don't make bricks for blocks that are completely surrounded
                    if (x != 0 && x != (32 * 16) - 1 && y != 0 && y != 255 && z != 0 && z != (32 * 16) - 1) {
                        Block[] surroundingBlocks = {
                                blocks[x][y][z+1],
                                blocks[x][y][z-1],
                                blocks[x][y+1][z],
                                blocks[x][y-1][z],
                                blocks[x+1][y][z],
                                blocks[x-1][y][z]
                        };
                        boolean surrounded = true;
                        for (Block block : surroundingBlocks)
                            if (block == null || block.isSeethrough()) {
                                surrounded = false;
                                break;
                            }
                        if (surrounded)
                            continue;
                    }

                    save.getBricks().addAll(blocks[x][y][z].getBricks(x, z, y, rx, ry, mcaFile, blocks));
                }
            }
        }
        System.out.println("!");
    }

    void readChunk(Block[][][] blocks, Chunk chunk, int chunkX, int chunkY) {
        for (int x=0; x < CHUNK_WIDTH; x++) {
            for (int z=0; z < CHUNK_WIDTH; z++) {
                for (int y=0; y < CHUNK_HEIGHT; y++) {
                    CompoundTag block = null;
                    try {
                        block = chunk.getBlockStateAt(x, y, z);
                    } catch(NullPointerException ignored) { }
                    if (block == null)
                        continue;
                    String blockType = ((StringTag) block.get("Name")).getValue();
                    CompoundTag props = (CompoundTag) block.get("Properties");
                    blocks[x + chunkX*CHUNK_WIDTH][y][z + chunkY*CHUNK_WIDTH] = new Block(blockType, props);
                }
            }
        }
    }

}
