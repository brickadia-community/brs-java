package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class WallBuilder {

    private static Brick center(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setSize(8, 8, 16);
        brick.setPosition(x, z, y);
        return brick;
    }

    private static Brick straight(int x, int z, int y, Color color, Block block) {
        Brick brick = new Brick();
        brick.setColor(color);

        String north = block.getProp("north");
        String east = block.getProp("east");

        if (!north.equals("none")) {
            if (north.equals("tall")) {
                brick.setSize(6, 16, 16);
                brick.setPosition(x, z, y);
            } else {
                brick.setSize(6, 16, 14);
                brick.setPosition(x, z, y - 2);
            }
        } else {
            if (east.equals("tall")) {
                brick.setSize(16, 6, 16);
                brick.setPosition(x, z, y);
            } else {
                brick.setSize(16, 6, 14);
                brick.setPosition(x, z, y - 2);
            }
        }

        return brick;
    }

    private static List<Brick> sides(int x, int z, int y, Color color, Block block) {
        List<Brick> bricks = new ArrayList<>();

        String north = block.getProp("north");
        String east = block.getProp("east");
        String south = block.getProp("south");
        String west = block.getProp("west");

        int off = 12;

        if (!north.equals("none")) {
            Brick brick = new Brick();
            brick.setColor(color);
            if (north.equals("tall")) {
                brick.setSize(6, 4, 16);
                brick.setPosition(x, z - off, y);
            } else {
                brick.setSize(6, 4, 14);
                brick.setPosition(x, z - off, y - 2);
            }
            bricks.add(brick);
        }
        if (!south.equals("none")) {
            Brick brick = new Brick();
            brick.setColor(color);
            if (south.equals("tall")) {
                brick.setSize(6, 4, 16);
                brick.setPosition(x, z + off, y);
            } else {
                brick.setSize(6, 4, 14);
                brick.setPosition(x, z + off, y - 2);
            }
            bricks.add(brick);
        }
        if (!west.equals("none")) {
            Brick brick = new Brick();
            brick.setColor(color);
            if (west.equals("tall")) {
                brick.setSize(4, 6, 16);
                brick.setPosition(x - off, z, y);
            } else {
                brick.setSize(4, 6, 14);
                brick.setPosition(x - off, z, y - 2);
            }
            bricks.add(brick);
        }
        if (!east.equals("none")) {
            Brick brick = new Brick();
            brick.setColor(color);
            if (east.equals("tall")) {
                brick.setSize(4, 6, 16);
                brick.setPosition(x + off, z, y);
            } else {
                brick.setSize(4, 6, 14);
                brick.setPosition(x + off, z, y - 2);
            }
            bricks.add(brick);
        }

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        List<Brick> bricks = new ArrayList<>();
        boolean up = block.getProp("up").equals("true");
        if (up) {
            bricks.add(center(x, z, y, color));
            bricks.addAll(sides(x, z, y, color, block));
        } else {
            bricks.add(straight(x, z, y, color, block));
        }
        return bricks;
    }

}
