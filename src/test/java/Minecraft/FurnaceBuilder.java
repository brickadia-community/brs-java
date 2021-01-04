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

    public static List<Brick> sides(int x, int z, int y, Facing facing) {
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

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        Facing facing = Facing.fromProps(block.getProps());
        List<Brick> bricks = CageBuilder.build(x, z, y, Colors.FURNACE_CAGE);
        bricks.addAll(topbot(x, z, y));
        bricks.addAll(sides(x, z, y, facing));
        bricks.addAll(front(x, z, y, facing));
        return bricks;
    }

}
