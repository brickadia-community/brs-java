package Minecraft;

import com.kmschr.brs.Brick;

import java.util.ArrayList;
import java.util.List;

public class FurnaceBuilder {

    public static List<Brick> topbot(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();
        for (int i=0; i < 2; ++i) {
            Brick brick = new Brick();
            brick.setColor(Colors.FURNACE_SIDES);
            brick.setSize(14, 14, 1);
            brick.setPosition(x, z, y - 15 + i * 30);
            bricks.add(brick);
        }
        return bricks;
    }

    public static List<Brick> sides(int x, int z, int y, Facing facing, boolean cull) {
        List<Brick> bricks = new ArrayList<>();
        for (int i=0; i < 4; ++i) {
            Brick bot = new Brick();
            bot.setColor(Colors.FURNACE_LIGHT);
            bricks.add(bot);
        }
        for (int i=0; i < 4; ++i) {
            Brick top = new Brick();
            top.setColor(Colors.FURNACE_SIDES);
            bricks.add(top);
        }

        bricks.get(0).setSize(14, 1, 6);
        bricks.get(1).setSize(14, 1, 6);
        bricks.get(2).setSize(1, 14, 6);
        bricks.get(3).setSize(1, 14, 6);
        bricks.get(4).setSize(14, 1, 8);
        bricks.get(5).setSize(14, 1, 8);
        bricks.get(6).setSize(1, 14, 8);
        bricks.get(7).setSize(1, 14, 8);

        bricks.get(0).setPosition(x, z - 15, y - 8);
        bricks.get(1).setPosition(x, z + 15, y - 8);
        bricks.get(2).setPosition(x - 15, z, y - 8);
        bricks.get(3).setPosition(x + 15, z, y - 8);
        bricks.get(4).setPosition(x, z - 15, y + 6);
        bricks.get(5).setPosition(x, z + 15, y + 6);
        bricks.get(6).setPosition(x - 15, z, y + 6);
        bricks.get(7).setPosition(x + 15, z, y + 6);

        if (!cull)
            return bricks;

        switch (facing) {
            case SOUTH -> {
                bricks.remove(5);
                bricks.remove(1);
            }
            case NORTH -> {
                bricks.remove(4);
                bricks.remove(0);
            }
            case EAST -> {
                bricks.remove(7);
                bricks.remove(3);
            }
            case WEST -> {
                bricks.remove(6);
                bricks.remove(2);
            }
        }

        return bricks;
    }

    public static List<Brick> front(int x, int z, int y, Facing facing) {
        List<Brick> bricks = new ArrayList<>();

        Brick bar = new Brick();
        bar.setColor(Colors.FURNACE_CAGE);

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 15 * (facing == Facing.SOUTH ? 1 : -1);
                bar.setSize(14, 1, 1);
                bar.setPosition(x, z + off, y - 1);
            }
            case WEST, EAST -> {
                int off = 15 * (facing == Facing.EAST ? 1 : -1);
                bar.setSize(1, 14, 1);
                bar.setPosition(x + off, z, y - 1);
            }
        }
        bricks.add(bar);

        Brick black = new Brick();
        black.setColor(Colors.FURNACE_DARK);
        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 13 * (facing == Facing.SOUTH ? 1 : -1);
                black.setSize(14, 1, 14);
                black.setPosition(x, z + off, y);
            }
            case WEST, EAST -> {
                int off = 13 * (facing == Facing.EAST ? 1 : -1);
                black.setSize(1, 14, 14);
                black.setPosition(x + off, z, y);
            }
        }
        bricks.add(black);

        Brick top = new Brick();
        Brick top_sideL = new Brick();
        Brick top_sideR = new Brick();
        Brick topBot = new Brick();
        Brick topCornL = new Brick();
        Brick topCornR = new Brick();
        Brick bot = new Brick();
        Brick botLeft = new Brick();
        Brick botRight = new Brick();
        Brick botCornL = new Brick();
        Brick botCornR = new Brick();
        top.setColor(Colors.FURNACE_SIDES);
        top_sideL.setColor(Colors.FURNACE_SIDES);
        top_sideR.setColor(Colors.FURNACE_SIDES);
        topBot.setColor(Colors.FURNACE_SIDES);
        topCornL.setColor(Colors.FURNACE_SIDES);
        topCornR.setColor(Colors.FURNACE_SIDES);
        bot.setColor(Colors.FURNACE_LIGHT);
        botLeft.setColor(Colors.FURNACE_LIGHT);
        botRight.setColor(Colors.FURNACE_LIGHT);
        botCornL.setColor(Colors.FURNACE_LIGHT);
        botCornR.setColor(Colors.FURNACE_LIGHT);
        topCornL.setSize(1, 1, 1);
        topCornR.setSize(1, 1, 1);
        botCornL.setSize(1, 1, 1);
        botCornR.setSize(1, 1, 1);
        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 15 * (facing == Facing.SOUTH ? 1 : -1);
                top.setSize(14, 1, 2);
                top.setPosition(x, z + off, y + 12);
                top_sideL.setSize(2, 1, 4);
                top_sideL.setPosition(x - 12, z + off, y + 6);
                top_sideR.setSize(2, 1, 4);
                top_sideR.setPosition(x + 12, z + off, y + 6);
                topBot.setSize(14, 1, 1);
                topBot.setPosition(x, z + off, y + 1);
                topCornL.setPosition(x - 9, z + off, y + 9);
                topCornR.setPosition(x + 9, z + off, y + 9);
                bot.setSize(14, 1, 2);
                bot.setPosition(x, z + off, y - 4);
                botLeft.setSize(2, 1, 4);
                botLeft.setPosition(x - 12, z + off, y - 10);
                botRight.setSize(2, 1, 4);
                botRight.setPosition(x + 12, z + off, y - 10);
                botCornL.setPosition(x - 9, z + off, y - 7);
                botCornR.setPosition(x + 9, z + off, y - 7);
            }
            case WEST, EAST -> {
                int off = 15 * (facing == Facing.EAST ? 1 : -1);
                top.setSize(1, 14, 2);
                top.setPosition(x + off, z, y + 12);
                top_sideL.setSize(1, 2, 4);
                top_sideL.setPosition(x + off, z - 12, y + 6);
                top_sideR.setSize(1, 2, 4);
                top_sideR.setPosition(x + off, z + 12, y + 6);
                topBot.setSize(1, 14, 1);
                topBot.setPosition(x + off, z, y + 1);
                topCornL.setPosition(x + off, z - 9, y + 9);
                topCornR.setPosition(x + off, z + 9, y + 9);
                bot.setSize(1, 14, 2);
                bot.setPosition(x + off, z, y - 4);
                botLeft.setSize(1, 2, 4);
                botLeft.setPosition(x + off, z - 12, y - 10);
                botRight.setSize(1, 2, 4);
                botRight.setPosition(x + off, z + 12, y - 10);
                botCornL.setPosition(x + off, z - 9, y - 7);
                botCornR.setPosition(x + off, z + 9, y - 7);
            }
        }
        bricks.add(top);
        bricks.add(top_sideL);
        bricks.add(top_sideR);
        bricks.add(topBot);
        bricks.add(topCornL);
        bricks.add(topCornR);
        bricks.add(bot);
        bricks.add(botLeft);
        bricks.add(botRight);
        bricks.add(botCornL);
        bricks.add(botCornR);

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        Facing facing = Facing.fromProps(block.getProps());
        List<Brick> bricks = CageBuilder.build(x, z, y, Colors.FURNACE_CAGE);
        bricks.addAll(topbot(x, z, y));
        bricks.addAll(sides(x, z, y, facing, true));
        bricks.addAll(front(x, z, y, facing));
        return bricks;
    }

}
