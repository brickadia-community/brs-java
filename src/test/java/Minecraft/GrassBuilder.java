package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class GrassBuilder {

    public static Brick bot7o8(int x, int z, int y, int rx, int rz, int ry, Color color, Block[][][] blocks) {
        if (rx != 0 && rx != (32 * 16) - 1 && ry != 0 && ry != 255 && rz != 0 && rz != (32 * 16) - 1) {
            Block[] surroundingBlocks = {
                    blocks[rx][ry][rz+1],
                    blocks[rx][ry][rz-1],
                    blocks[rx][ry-1][rz],
                    blocks[rx+1][ry][rz],
                    blocks[rx-1][ry][rz]
            };
            boolean surrounded = true;
            for (Block block : surroundingBlocks)
                if ((block == null || block.isSeethrough()) && !(blocks[rx][ry][rz].getBlockType().equals("minecraft:water") && block != null && block.getBlockType().equals("minecraft:water"))) {
                    surrounded = false;
                    break;
                }
            if (surrounded)
                return null;
        }

        Brick brick = new Brick();
        brick.setSize(16, 16, 14);
        brick.setPosition(x, z, y - 2);
        brick.setColor(color);
        return brick;
    }

    public static Brick top1o8(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 2);
        brick.setPosition(x, z, y + 14);
        brick.setColor(color);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y, int rx, int rz, int ry, Color color, Block[][][] blocks) {
        List<Brick> bricks = new ArrayList<>();

        Brick bot = bot7o8(x, z, y, rx, rz, ry, Colors.DIRT, blocks);
        if (bot != null)
            bricks.add(bot);

        bricks.add(top1o8(x, z, y, color));

        return bricks;
    }

}
