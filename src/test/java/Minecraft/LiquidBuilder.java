package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class LiquidBuilder {

    public static List<Brick> build(int rx, int rz, int ry, int regX, int regY, Color color, Block block, Block[][][] blocks) {
        int x = rx * 16 * 2 + regX * 32 * 16 * 32;
        int z = rz * 16 * 2 + regY * 32 * 16 * 32;
        int y = ry * 16 * 2 + 16;

        boolean top = true;
        if (ry < 256 && blocks[rx][ry+1][rz].getBlockType().equals(block.getBlockType())) {
            top = false;
        }

        if (top) {
            int level = Integer.parseInt(block.getProp("level"));
            Brick brick = new Brick();
            brick.setSize(16, 16, 16 - (level+1)*2);
            brick.setPosition(x, z, y - (level+1)*2);
            brick.setColor(color);
            return List.of(brick);
        } else {
            return CubeBuilder.build(x, z, y, color);
        }
    }

}
