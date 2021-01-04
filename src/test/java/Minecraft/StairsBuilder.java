package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class StairsBuilder {

    public static Brick straight(int x, int z, int y, Color color, String half, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(color);

        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH, SOUTH -> {
                int dist = 8 * (facing == Facing.NORTH ? -1 : 1);
                brick.setSize(16, 8, 8);
                brick.setPosition(x, z + dist, y + offset);
            }
            case EAST, WEST -> {
                int dist = 8 * (facing == Facing.WEST ? -1 : 1);
                brick.setSize(8, 16, 8);
                brick.setPosition(x + dist, z, y + offset);
            }
        }

        return brick;
    }

    public static Brick outer_left(int x, int z, int y, Color color, String half, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setSize(8, 8, 8);

        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH, SOUTH -> {
                int dist = 8 * (facing == Facing.NORTH ? -1 : 1);
                brick.setPosition(x + dist, z + dist, y + offset);
            }
            case EAST, WEST -> {
                int dist = 8 * (facing == Facing.WEST ? -1 : 1);
                brick.setPosition(x + dist, z - dist, y + offset);
            }
        }

        return brick;
    }

    public static Brick outer_right(int x, int z, int y, Color color, String half, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setSize(8, 8, 8);

        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH, SOUTH -> {
                int dist = 8 * (facing == Facing.NORTH ? -1 : 1);
                brick.setPosition(x - dist, z + dist, y + offset);
            }
            case EAST, WEST -> {
                int dist = 8 * (facing == Facing.WEST ? -1 : 1);
                brick.setPosition(x + dist, z + dist, y + offset);
            }
        }

        return brick;
    }

    public static List<Brick> inner_left(int x, int z, int y, Color color, String half, Facing facing) {
        List<Brick> bricks = new ArrayList<>();
        Brick brick = new Brick();
        brick.setSize(8, 8, 8);
        brick.setColor(color);
        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH, SOUTH -> {
                int dist = 8 * (facing == Facing.NORTH ? 1 : -1);
                brick.setPosition(x - dist, z + dist, y + offset);
            }
            case EAST, WEST -> {
                int dist = 8 * (facing == Facing.WEST ? 1 : -1);
                brick.setPosition(x + dist, z + dist, y + offset);
            }
        }

        bricks.add(straight(x, z, y, color, half, facing));
        bricks.add(brick);
        return bricks;
    }

    public static List<Brick> inner_right(int x, int z, int y, Color color, String half, Facing facing) {
        List<Brick> bricks = new ArrayList<>();
        Brick brick = new Brick();
        brick.setSize(8, 8, 8);
        brick.setColor(color);
        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH, SOUTH -> {
                int dist = 8 * (facing == Facing.NORTH ? 1 : -1);
                brick.setPosition(x + dist, z + dist, y + offset);
            }
            case EAST, WEST -> {
                int dist = 8 * (facing == Facing.WEST ? 1 : -1);
                brick.setPosition(x + dist, z - dist, y + offset);
            }
        }

        bricks.add(straight(x, z, y, color, half, facing));
        bricks.add(brick);
        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        String half = block.getProp("half");
        String shape = block.getProp("shape");
        Facing facing = Facing.fromProps(block.getProps());
        List<Brick> bricks = new ArrayList<>();

        switch (half) {
            case "top" -> bricks.add(SlabBuilder.top(x, z, y, color));
            case "bottom" -> bricks.add(SlabBuilder.bottom(x, z, y, color));
        }

        switch (shape) {
            case "straight" -> bricks.add(straight(x, z, y, color, half, facing));
            case "outer_left" -> bricks.add(outer_left(x, z, y, color, half, facing));
            case "outer_right" -> bricks.add(outer_right(x, z, y, color, half, facing));
            case "inner_left" -> bricks.addAll(inner_left(x, z, y, color, half, facing));
            case "inner_right" -> bricks.addAll(inner_right(x, z, y, color, half, facing));
        }

        return bricks;
    }

}
