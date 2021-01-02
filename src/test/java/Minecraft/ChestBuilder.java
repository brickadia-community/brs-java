package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;
import net.querz.nbt.tag.CompoundTag;

import java.util.ArrayList;
import java.util.List;

public class ChestBuilder {

    private static final Color BORDER = new Color(52, 46, 37, 255);
    private static final Color BODY = new Color(154, 108, 37, 255);
    private static final Color LOCK = new Color(154, 154, 154, 255);

    private static List<Brick> corners(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; i++) {
            Brick brick = new Brick();
            brick.setColor(BORDER);
            brick.setSize(1, 1, 14);
            bricks.add(brick);
        }

        int off = 13;

        bricks.get(0).setPosition(x + off, z + off, y - 2);
        bricks.get(1).setPosition(x + off, z - off, y - 2);
        bricks.get(2).setPosition(x - off, z + off, y - 2);
        bricks.get(3).setPosition(x - off, z - off, y - 2);

        return bricks;
    }

    private static List<Brick> edges(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        int width = 12;

        for (int i=0; i < 6; i++) {
            Brick hor = new Brick();
            Brick ver = new Brick();
            hor.setColor(BORDER);
            ver.setColor(BORDER);
            hor.setSize(width, 1, 1);
            ver.setSize(1, width, 1);
            bricks.add(hor);
            bricks.add(ver);
        }

        int off = 13;
        int top = 11;
        int mid = 3;
        int bot = -15;

        bricks.get(0).setPosition(x, z - off, y + bot);
        bricks.get(1).setPosition(x - off, z, y + bot);
        bricks.get(2).setPosition(x, z + off, y + bot);
        bricks.get(3).setPosition(x + off, z, y + bot);

        bricks.get(4).setPosition(x, z - off, y + mid);
        bricks.get(5).setPosition(x - off, z, y + mid);
        bricks.get(6).setPosition(x, z + off, y + mid);
        bricks.get(7).setPosition(x + off, z, y + mid);

        bricks.get(8).setPosition(x, z - off, y + top);
        bricks.get(9).setPosition(x - off, z, y + top);
        bricks.get(10).setPosition(x, z + off, y + top);
        bricks.get(11).setPosition(x + off, z, y + top);

        return bricks;
    }

    private static List<Brick> sides(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 10; ++i) {
            Brick brick = new Brick();
            brick.setColor(BODY);
            bricks.add(brick);
        }

        int width = 12;
        int top = 11;
        int bot = -15;
        int off = 13;

        int first = -6;
        int sec = 7;
        int h1 = 8;
        int h2 = 3;

        bricks.get(0).setSize(width, width, 1);
        bricks.get(1).setSize(width, width, 1);
        bricks.get(0).setPosition(x, z, y + top);
        bricks.get(1).setPosition(x, z, y + bot);

        bricks.get(2).setSize(1, width, h1);
        bricks.get(3).setSize(1, width, h1);
        bricks.get(2).setPosition(x + off, z, y + first);
        bricks.get(3).setPosition(x - off, z, y + first);

        bricks.get(4).setSize(width, 1, h1);
        bricks.get(5).setSize(width, 1, h1);
        bricks.get(4).setPosition(x, z + off, y + first);
        bricks.get(5).setPosition(x, z - off, y + first);

        bricks.get(6).setSize(1, width, h2);
        bricks.get(7).setSize(1, width, h2);
        bricks.get(6).setPosition(x + off, z, y + sec);
        bricks.get(7).setPosition(x - off, z, y + sec);

        bricks.get(8).setSize(width, 1, h2);
        bricks.get(9).setSize(width, 1, h2);
        bricks.get(8).setPosition(x, z + off, y + sec);
        bricks.get(9).setPosition(x, z - off, y + sec);

        return bricks;
    }

    private static Brick lock(int x, int z, int y, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(LOCK);

        int h = 2;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 15 * (facing == Facing.SOUTH ? 1 : -1);
                brick.setSize(2, 1, 4);
                brick.setPosition(x, z + off, y + h);
            }
            case EAST, WEST -> {
                int off = 15 * (facing == Facing.EAST ? 1 : -1);
                brick.setSize(1, 2, 4);
                brick.setPosition(x + off, z, y + h);
            }
        }

        return brick;
    }

    public static List<Brick> build(int x, int z, int y, CompoundTag properties) {
        List<Brick> bricks = new ArrayList<>();
        bricks.addAll(corners(x, z, y));
        bricks.addAll(edges(x, z, y));
        bricks.addAll(sides(x, z, y));
        bricks.add(lock(x, z, y, Facing.fromProps(properties)));
        return bricks;
    }

}
