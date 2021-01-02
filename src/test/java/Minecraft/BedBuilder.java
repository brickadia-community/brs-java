package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class BedBuilder {

    private static final Color BASE = new Color(150, 119, 71, 255);
    private static final Color PILLOW = new Color(211, 216, 216, 255);
    private static final Color BLANKET_BLACK = new Color(28, 28, 32, 255);
    private static final Color BLANKET_BLUE = new Color(59, 80, 168, 255);
    private static final Color BLANKET_BROWN = new Color(124, 78, 45, 255);
    private static final Color BLANKET_CYAN = new Color(16, 136, 135, 255);
    private static final Color BLANKET_GRAY = new Color(87, 101, 103, 255);
    private static final Color BLANKET_GREEN = new Color(80, 107, 20, 255);
    private static final Color BLANKET_LIGHT_BLUE = new Color(36, 147, 203, 255);
    private static final Color BLANKET_LIGHT_GRAY = new Color(129, 131, 122, 255);
    private static final Color BLANKET_LIME = new Color(108, 180, 24, 255);
    private static final Color BLANKET_MAGENTA = new Color(171, 51, 161, 255);
    private static final Color BLANKET_ORANGE = new Color(234, 113, 15, 255);
    private static final Color BLANKET_PINK = new Color(221, 113, 149, 255);
    private static final Color BLANKET_PURPLE = new Color(114, 37, 165, 255);
    private static final Color BLANKET_RED = new Color(163, 41, 36, 255);
    private static final Color BLANKET_WHITE = PILLOW;
    private static final Color BLANKET_YELLOW = new Color(245, 189, 29, 255);

    private static Color blanketColor(Block block) {
        return switch (block.getBlockType()) {
            case "minecraft:black_bed" -> BLANKET_BLACK;
            case "minecraft:blue_bed" -> BLANKET_BLUE;
            case "minecraft:brown_bed" -> BLANKET_BROWN;
            case "minecraft:cyan_bed" -> BLANKET_CYAN;
            case "minecraft:gray_bed" -> BLANKET_GRAY;
            case "minecraft:green_bed" -> BLANKET_GREEN;
            case "minecraft:light_blue_bed" -> BLANKET_LIGHT_BLUE;
            case "minecraft:light_gray_bed" -> BLANKET_LIGHT_GRAY;
            case "minecraft:lime_bed" -> BLANKET_LIME;
            case "minecraft:magenta_bed" -> BLANKET_MAGENTA;
            case "minecraft:orange_bed" -> BLANKET_ORANGE;
            case "minecraft:pink_bed" -> BLANKET_PINK;
            case "minecraft:purple_bed" -> BLANKET_PURPLE;
            case "minecraft:red_bed" -> BLANKET_RED;
            case "minecraft:white_bed" -> BLANKET_WHITE;
            case "minecraft:yellow_bed" -> BLANKET_YELLOW;
            default -> throw new IllegalStateException("Unexpected value: " + block.getBlockType());
        };
    }

    private static Brick pillow(int x, int z, int y, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(PILLOW);

        int h = -2;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 8 * (facing == Facing.NORTH ? -1 : 1);
                brick.setSize(16, 8, 4);
                brick.setPosition(x, z + off, y + h);
            }
            case EAST, WEST -> {
                int off = 8 * (facing == Facing.WEST ? -1 : 1);
                brick.setSize(8, 16, 4);
                brick.setPosition(x + off, z, y + h);
            }
        }

        return brick;
    }

    private static Brick blanket(int x, int z, int y, Facing facing, Block block) {
        Brick brick = new Brick();
        brick.setColor(blanketColor(block));

        int h = -2;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 24 * (facing == Facing.NORTH ? 1 : -1);
                brick.setSize(16, 24, 4);
                brick.setPosition(x, z + off, y + h);
            }
            case EAST, WEST -> {
                int off = 24 * (facing == Facing.WEST ? 1 : -1);
                brick.setSize(24, 16, 4);
                brick.setPosition(x + off, z, y + h);
            }
        }

        return brick;
    }

    private static Brick base(int x, int z, int y, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(BASE);

        int h = -8;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 16 * (facing == Facing.NORTH ? 1 : -1);
                brick.setSize(16, 32, 2);
                brick.setPosition(x, z + off, y + h);
            }
            case EAST, WEST -> {
                int off = 16 * (facing == Facing.WEST ? 1 : -1);
                brick.setSize(32, 16, 2);
                brick.setPosition(x + off, z, y + h);
            }
        }

        return brick;
    }

    private static List<Brick> legs(int x, int z, int y, Facing facing) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick();
            brick.setColor(BASE);
            switch (facing) {
                case NORTH, SOUTH -> brick.setSize(2, 3, 3);
                case EAST, WEST -> brick.setSize(3, 2, 3);
            }
            bricks.add(brick);
        }

        int h = -13;
        int close = 13;
        int far = -(13 + 32);
        int off = 14;

        switch (facing) {
            case NORTH, SOUTH -> {
                int modifier = facing == Facing.NORTH ? -1 : 1;
                far *= modifier;
                close *= modifier;
                bricks.get(0).setPosition(x + off, z + close, y + h);
                bricks.get(1).setPosition(x - off, z + close, y + h);
                bricks.get(2).setPosition(x + off, z + far, y + h);
                bricks.get(3).setPosition(x - off, z + far, y + h);
            }
            case EAST, WEST -> {
                int modifier = facing == Facing.WEST ? -1 : 1;
                far *= modifier;
                close *= modifier;
                bricks.get(0).setPosition(x + close, z + off, y + h);
                bricks.get(1).setPosition(x + close, z - off, y + h);
                bricks.get(2).setPosition(x + far, z + off, y + h);
                bricks.get(3).setPosition(x + far, z - off, y + h);
            }
        }

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        String part = block.getProp("part");
        if (part.equals("head")) {
            Facing facing = Facing.fromProps(block.getProps());
            bricks.add(pillow(x, z, y, facing));
            bricks.add(blanket(x, z, y, facing, block));
            bricks.add(base(x, z, y, facing));
            bricks.addAll(legs(x, z, y, facing));
        }
        return bricks;
    }

}
