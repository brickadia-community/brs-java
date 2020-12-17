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

    final int SCALE = 2;
    final int CHUNK_WIDTH = 16;
    final int CHUNK_HEIGHT = 256;
    final int REGION_SIZE = 32;
    final int MAX_INDEX = CHUNK_WIDTH * REGION_SIZE - 1;
    final int MAX_INDEX_HEIGHT = CHUNK_HEIGHT - 1;
    final int MAX_GROW = 15;

    Random rand = new Random();

    Set<String> blockTypes = new HashSet<>();
    Map<String, Integer> blockColors = Map.<String, Integer>ofEntries(
            Map.entry("minecraft:stone", ColorMode.DARK_GRAY),
            Map.entry("minecraft:stone_brick_wall", ColorMode.DARK_GRAY),
            Map.entry("minecraft:stone_brick_stairs", ColorMode.DARK_GRAY),
            Map.entry("minecraft:cracked_stone_bricks", ColorMode.DARK_GRAY),
            Map.entry("minecraft:mossy_stone_bricks", ColorMode.DARK_GRAY),
            Map.entry("minecraft:mossy_cobblestone", ColorMode.DARK_GRAY),
            Map.entry("minecraft:andesite", ColorMode.DARK_GRAY),
            Map.entry("minecraft:andesite_wall", ColorMode.DARK_GRAY),
            Map.entry("minecraft:cobblestone", ColorMode.DARK_GRAY - 1),
            Map.entry("minecraft:cobblestone_wall", ColorMode.DARK_GRAY - 1),
            Map.entry("minecraft:gravel", ColorMode.LIGHT_GRAY + 2),
            Map.entry("minecraft:granite", ColorMode.DARK_GRAY),
            Map.entry("minecraft:diorite", ColorMode.LIGHT_GRAY + 1),
            Map.entry("minecraft:dirt", ColorMode.BROWN + 1),
            Map.entry("minecraft:coarse_dirt", ColorMode.BROWN + 1),
            Map.entry("minecraft:grass_block", ColorMode.FOREST_GREEN + 1),
            Map.entry("minecraft:bedrock", ColorMode.BLACK),
            Map.entry("minecraft:coal_ore", ColorMode.BLACK),
            Map.entry("minecraft:birch_leaves", ColorMode.FOREST_GREEN),
            Map.entry("minecraft:oak_leaves", ColorMode.FOREST_GREEN),
            Map.entry("minecraft:spruce_leaves", ColorMode.FOREST_GREEN),
            Map.entry("minecraft:dark_oak_leaves", ColorMode.FOREST_GREEN),
            Map.entry("minecraft:acacia_leaves", ColorMode.FOREST_GREEN),
            Map.entry("minecraft:oak_log", ColorMode.BROWN),
            Map.entry("minecraft:oak_wood", ColorMode.BROWN),
            Map.entry("minecraft:birch_log", ColorMode.WHITE + 1),
            Map.entry("minecraft:quartz_bricks", ColorMode.WHITE + 1),
            Map.entry("minecraft:spruce_log", ColorMode.BROWN),
            Map.entry("minecraft:spruce_wood", ColorMode.BROWN),
            Map.entry("minecraft:dark_oak_log", ColorMode.BROWN),
            Map.entry("minecraft:dark_oak_wood", ColorMode.BROWN),
            Map.entry("minecraft:lava", ColorMode.ORANGE),
            Map.entry("minecraft:gold_ore", ColorMode.YELLOW),
            Map.entry("minecraft:lapis_ore", ColorMode.DARK_BLUE),
            Map.entry("minecraft:redstone_ore", ColorMode.RED),
            Map.entry("minecraft:water", 43),
            Map.entry("minecraft:sand", ColorMode.BROWN + 4),
            Map.entry("minecraft:end_stone_bricks", ColorMode.BROWN + 4),
            Map.entry("minecraft:snow", ColorMode.WHITE),
            Map.entry("minecraft:oak_planks", ColorMode.BROWN + 3),
            Map.entry("minecraft:dark_oak_planks", ColorMode.BROWN),
            Map.entry("minecraft:dark_oak_stairs", ColorMode.BROWN),
            Map.entry("minecraft:oak_fence", ColorMode.BROWN + 3),
            Map.entry("minecraft:netherrack", ColorMode.DARK_RED),
            Map.entry("minecraft:emerald_ore", ColorMode.GREEN),
            Map.entry("minecraft:clay", ColorMode.LIGHT_GRAY + 2),
            Map.entry("minecraft:pumpkin", ColorMode.FOREST_GREEN + 7),
            Map.entry("minecraft:obsidian", ColorMode.BLACK - 1),
            Map.entry("minecraft:crying_obsidian", ColorMode.BLACK - 1),
            Map.entry("minecraft:magma_block", ColorMode.DARK_RED),
            Map.entry("minecraft:seagrass", 43),
            Map.entry("minecraft:tall_seagrass", 43),
            Map.entry("minecraft:iron_ore", ColorMode.DARK_GRAY),
            Map.entry("minecraft:sandstone_wall", ColorMode.BROWN + 3),
            Map.entry("minecraft:sandstone", ColorMode.BROWN + 4),
            Map.entry("minecraft:diamond_ore", ColorMode.BLUE),
            Map.entry("minecraft:cyan_terracotta", ColorMode.BLUE),
            Map.entry("minecraft:purple_wool", ColorMode.DARK_PINK),
            Map.entry("minecraft:orange_wool", ColorMode.FOREST_GREEN + 7)
    );


    @Test
    void testWriteMinecraft() throws IOException {
        SaveData save = new SaveData();
        save.setDescription("Minecraft chunk");
        save.setBrickAssets(List.of("PB_DefaultMicroBrick"));

        readRegion("castle/r.0.0.mca", save, 0, 0);
        readRegion("castle/r.0.-1.mca", save, 0, -1);
        readRegion("castle/r.-1.0.mca", save, -1, 0);
        readRegion("castle/r.-1.-1.mca", save, -1, -1);

        for (String blockType : blockTypes)
            System.out.println(blockType);

        System.out.println("Writing " + save.getBricks().size() + " bricks");
        BRS.writeSave("minecraft_castle.brs", save);
    }

    void readRegion(String filename, SaveData save, int rx, int ry) throws IOException {
        System.out.println("Reading region " + rx + ", " + ry);
        MCAFile mcaFile = MCAUtil.read(filename);

        Byte[][][] blocks = new Byte[REGION_SIZE * CHUNK_WIDTH][CHUNK_HEIGHT][REGION_SIZE * CHUNK_WIDTH];

        for (int chunkX=0; chunkX < REGION_SIZE; chunkX++) {
            for (int chunkY = 0; chunkY < REGION_SIZE; chunkY++) {
                Chunk chunk = mcaFile.getChunk(chunkX, chunkY);
                if (chunk != null)
                    readChunk(blocks, chunk, chunkX, chunkY);
            }
        }

        Byte[][][] culledBlocks = new Byte[REGION_SIZE * CHUNK_WIDTH][CHUNK_HEIGHT][REGION_SIZE * CHUNK_WIDTH];

        for (int x=0; x < REGION_SIZE * CHUNK_WIDTH; x++) {
            for (int y=0; y < CHUNK_HEIGHT; y++) {
                for (int z=0; z < REGION_SIZE * CHUNK_WIDTH; z++) {
                    if (blocks[x][y][z] == null || blocks[x][y][z] == -1)
                        continue;

                    // remove bottom bedrock
                    if ((y == 0 || y == 1) && !(x == 0 || z == 0 || x == REGION_SIZE * CHUNK_WIDTH - 1 || z == REGION_SIZE * CHUNK_WIDTH - 1))
                        continue;

                    // Transfer down snow
                    if (y != CHUNK_HEIGHT - 1 && blocks[x][y+1][z] != null && blocks[x][y+1][z] == 0) {
                        blocks[x][y][z] = 0;
                        blocks[x][y+1][z] = null;
                    }

                    // Don't make bricks for blocks that are completely surrounded
                    if (x != 0 && x != (32 * 16) - 1 && y != 0 && y != 255 && z != 0 && z != (32 * 16) - 1) {
                        Byte[] surroundingBlocks = {
                                blocks[x][y][z+1],
                                blocks[x][y][z-1],
                                blocks[x][y+1][z],
                                blocks[x][y-1][z],
                                blocks[x+1][y][z],
                                blocks[x-1][y][z]
                        };
                        boolean surrounded = true;
                        for (Byte block : surroundingBlocks)
                            if (block == null) {
                                surrounded = false;
                                break;
                            }
                        if (surrounded)
                            continue;
                    }

                    Brick brick = new Brick();
                    brick.setSize(new Vec3(SCALE, SCALE, SCALE));
                    brick.setPosition(new Vec3(
                            (x*2 + rx*REGION_SIZE*CHUNK_WIDTH*2) * SCALE,
                            (z*2 + ry*REGION_SIZE*CHUNK_WIDTH*2) * SCALE,
                            (y*2 + 1) * SCALE));
                    brick.setColor(new ColorMode(blocks[x][y][z]));

                    // make lava glow
                    if (blocks[x][y][z] == (byte) ColorMode.ORANGE)
                        brick.setMaterialIndex(1);

                    save.getBricks().add(brick);
                }
            }
        }

        /*
        for (int x=0; x < REGION_SIZE * CHUNK_WIDTH; x++) {
            for (int y = 0; y < CHUNK_HEIGHT; y++) {
                for (int z = 0; z < REGION_SIZE * CHUNK_WIDTH; z++) {
                    Byte block = culledBlocks[x][y][z];
                    if (block == null)
                        continue;

                    boolean canGrowX = true;
                    boolean canGrowY = true;
                    boolean canGrowZ = true;

                    int growX = 0;
                    int growY = 0;
                    int growZ = 0;

                    while (canGrowX || canGrowY || canGrowZ) {
                        int growOrder = rand.nextInt(6);

                        switch (growOrder) {
                            case 0 -> {
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                            }
                            case 1 -> {
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                            }
                            case 2 -> {
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                            }
                            case 3 -> {
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                            }
                            case 4 -> {
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                            }
                            case 5 -> {
                                if (canGrowZ) {
                                    canGrowZ = growZ(culledBlocks, growX, growY, growZ, x, y, z);
                                    growZ += canGrowZ?1:0;
                                }
                                if (canGrowY) {
                                    canGrowY = growY(culledBlocks, growX, growY, growZ, x, y, z);
                                    growY += canGrowY?1:0;
                                }
                                if (canGrowX) {
                                    canGrowX = growX(culledBlocks, growX, growY, growZ, x, y, z);
                                    growX += canGrowX?1:0;
                                }
                            }
                        }

                    }

                    Brick brick = new Brick();
                    brick.setSize(new Vec3((growX + 1) * SCALE, (growZ + 1) * SCALE, (growY + 1) * SCALE));
                    brick.setPosition(new Vec3(
                            (x*2 + growX + rx*REGION_SIZE*CHUNK_WIDTH*2) * SCALE,
                            (z*2 + growZ + ry*REGION_SIZE*CHUNK_WIDTH*2) * SCALE,
                            (y*2 + growY + 1) * SCALE));
                    brick.setColor(new ColorMode(culledBlocks[x][y][z]));

                    // make lava glow
                    if (culledBlocks[x][y][z] == (byte) ColorMode.ORANGE)
                        brick.setMaterialIndex(1);

                    save.getBricks().add(brick);

                    for (int dx=0; dx <= growX; dx++) {
                        for (int dy=0; dy <= growY; dy++) {
                            for (int dz=0; dz <= growZ; dz++) {
                                culledBlocks[x + dx][y + dy][z + dz] = null;
                            }
                        }
                    }
                }
            }
        }
         */
    }

    void readChunk(Byte[][][] blocks, Chunk chunk, int chunkX, int chunkY) {
        for (int x=0; x < CHUNK_WIDTH; x++) {
            for (int z=0; z < CHUNK_WIDTH; z++) {
                for (int y=0; y < CHUNK_HEIGHT; y++) {
                    CompoundTag block = null;
                    try {
                        block = chunk.getBlockStateAt(x, y, z);
                    } catch(NullPointerException ignored) { }
                    String blockType = getBlockType(block);
                    if (block == null)
                        continue;

                    Integer color = blockColors.get(blockType);
                    if (color == null) {
                        blockTypes.add(blockType);
                        continue;
                    }

                    blocks[x + chunkX*CHUNK_WIDTH][y][z + chunkY*CHUNK_WIDTH] = (byte) ((int) color);
                }
            }
        }
    }

    boolean growX(Byte[][][] culledBlocks, int growX, int growY, int growZ, int x, int y, int z) {
        if (growX >= MAX_GROW - rand.nextInt(6))
            return false;

        if (x + growX + 1 > MAX_INDEX)
            return false;
        // check same block type
        for (int dy = 0; dy < growY + 1; dy++) {
            for (int dz = 0; dz < growZ + 1; dz++) {
                Byte potentialBlock = culledBlocks[x + growX + 1][y + dy][z + dz];
                if (potentialBlock == null || !potentialBlock.equals(culledBlocks[x][y][z]))
                    return false;
            }
        }
        return true;
    }

    boolean growY(Byte[][][] culledBlocks, int growX, int growY, int growZ, int x, int y, int z) {
        if (growY >= MAX_GROW - rand.nextInt(6))
            return false;

        if (y + growY + 1 > MAX_INDEX_HEIGHT)
            return false;

        // check same block type
        for (int dx = 0; dx < growX + 1; dx++) {
            for (int dz = 0; dz < growZ + 1; dz++) {
                Byte potentialBlock = culledBlocks[x + dx][y + growY + 1][z + dz];
                if (potentialBlock == null || !potentialBlock.equals(culledBlocks[x][y][z])) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean growZ(Byte[][][] culledBlocks, int growX, int growY, int growZ, int x, int y, int z) {
        if (growZ >= MAX_GROW - rand.nextInt(6))
            return false;

        if (z + growZ + 1 > MAX_INDEX)
            return false;

        // check same block type
        for (int dy = 0; dy < growY + 1; dy++) {
            for (int dx = 0; dx < growX + 1; dx++) {
                Byte potentialBlock = culledBlocks[x + dx][y + dy][z + growZ + 1];
                if (potentialBlock == null || !potentialBlock.equals(culledBlocks[x][y][z]))
                    return false;

            }
        }
        return true;
    }

    String getBlockType(CompoundTag block) {
        if (block == null)
            return null;
        return ((StringTag) block.get("Name")).getValue();
    }

}
