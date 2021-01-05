package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class OreBuilder {

    private static final Color STONE = new Color(125, 125, 125, 255);


    private static List<Brick> corners(int x, int z, int y, Color stoneColor) {
        List<Brick> bricks = new ArrayList<>();
        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick(stoneColor);
            brick.setSize(6, 6, 16);
            bricks.add(brick);
        }
        int off = 10;
        bricks.get(0).setPosition(x + off, z + off, y);
        bricks.get(1).setPosition(x + off, z - off, y);
        bricks.get(2).setPosition(x - off, z + off, y);
        bricks.get(3).setPosition(x - off, z - off, y);

        return bricks;
    }

    private static List<Brick> edges(int x, int z, int y, Color stoneColor) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick(stoneColor);
            brick.setSize(4, 6, 4);
            bricks.add(brick);
        }
        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick(stoneColor);
            brick.setSize(6, 4, 4);
            bricks.add(brick);
        }

        int off = 10;
        int yoff = 12;

        bricks.get(0).setPosition(x, z + off, y - yoff);
        bricks.get(1).setPosition(x, z - off, y - yoff);
        bricks.get(2).setPosition(x, z + off, y + yoff);
        bricks.get(3).setPosition(x, z - off, y + yoff);
        bricks.get(4).setPosition(x + off, z, y - yoff);
        bricks.get(5).setPosition(x - off, z, y - yoff);
        bricks.get(6).setPosition(x + off, z, y + yoff);
        bricks.get(7).setPosition(x - off, z, y + yoff);

        return bricks;
    }

    private static List<Brick> ore(int x, int z, int y, Color oreColor, Block block) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 5; ++i) {
            Brick brick = new Brick();
            brick.setColor(oreColor);
            brick.setSize(4, 4, 8);
            bricks.add(brick);
        }

        int off = 12;

        bricks.get(0).setPosition(x, z + off, y);
        bricks.get(1).setPosition(x, z - off, y);
        bricks.get(2).setPosition(x + off, z, y);
        bricks.get(3).setPosition(x - off, z, y);
        bricks.get(4).setSize(4, 4, 16);
        bricks.get(4).setPosition(x, z, y);

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Color oreColor, Color stoneColor, Block block) {
        List<Brick> bricks = new ArrayList<>();
        bricks.addAll(corners(x, z, y, stoneColor));
        bricks.addAll(edges(x, z, y, stoneColor));
        bricks.addAll(ore(x, z, y, oreColor, block));
        return bricks;
    }

}
