package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class PaneBuilder {

    public static List<Brick> straightPane(int x, int z, int y, Color border, Color inside, boolean north, boolean stained) {
        List<Brick> bricks = new ArrayList<>();

        int off = 15;
        if (north) {
            for (int i=0; i < 2; ++i) {
                Brick side = new Brick(border);
                Brick lip = new Brick(border);
                side.setSize(2, 1, 16);
                lip.setSize(2, 14, 1);
                bricks.add(side);
                bricks.add(lip);
            }
            bricks.get(0).setPosition(x, z+off, y);
            bricks.get(1).setPosition(x, z, y+off);
            bricks.get(2).setPosition(x, z-off, y);
            bricks.get(3).setPosition(x, z, y-off);

            if (!stained) {
                for (int i=0; i < 5; ++i) {
                    Brick brick = new Brick(Colors.GLASS_BORDER);
                    brick.setSize(1, 1, 1);
                    bricks.add(brick);
                }

                bricks.get(4).setPosition(x, z -11, y + 7);
                bricks.get(5).setPosition(x, z - 9, y + 9);
                bricks.get(6).setPosition(x, z - 7, y + 11);
                bricks.get(7).setPosition(x, z + 11, y - 9);
                bricks.get(8).setPosition(x, z + 9, y - 11);
            } else {
                Brick brick = new Brick(inside);
                brick.setSize(2, 14, 14);
                brick.setPosition(x, z, y);
                bricks.add(brick);
            }
        } else {
            for (int i=0; i < 2; ++i) {
                Brick side = new Brick(border);
                Brick lip = new Brick(border);
                side.setSize(1, 2, 16);
                lip.setSize(14, 2, 1);
                bricks.add(side);
                bricks.add(lip);
            }
            bricks.get(0).setPosition(x+off, z, y);
            bricks.get(1).setPosition(x, z, y+off);
            bricks.get(2).setPosition(x-off, z, y);
            bricks.get(3).setPosition(x, z, y-off);

            if (!stained) {
                for (int i=0; i < 5; ++i) {
                    Brick brick = new Brick(Colors.GLASS_BORDER);
                    brick.setSize(1, 1, 1);
                    bricks.add(brick);
                }

                bricks.get(4).setPosition(x - 11, z, y + 7);
                bricks.get(5).setPosition(x - 9, z, y + 9);
                bricks.get(6).setPosition(x - 7, z, y + 11);
                bricks.get(7).setPosition(x + 11, z, y - 9);
                bricks.get(8).setPosition(x + 9, z, y - 11);
            } else {
                Brick brick = new Brick(inside);
                brick.setSize(14, 2, 14);
                brick.setPosition(x, z, y);
                bricks.add(brick);
            }
        }

        return bricks;
    }

    public static List<Brick> unconnectedPane(int x, int z, int y, Color border, Color inside) {
        Brick top = new Brick(border);
        Brick bot = new Brick(border);
        Brick mid = new Brick(inside);
        top.setSize(2, 2, 1);
        bot.setSize(2, 2, 1);
        mid.setSize(2, 2, 14);
        top.setPosition(x, z, y + 15);
        bot.setPosition(x, z, y - 15);
        mid.setPosition(x, z, y);

        return List.of(top, bot, mid);
    }

    public static List<Brick> singleSide(int x, int z, int y, Color border, Color inside, Facing facing) {
        Brick top = new Brick(border);
        Brick bot = new Brick(border);
        Brick edge = new Brick(border);
        Brick mid = new Brick(inside);

        switch (facing) {
            case NORTH, SOUTH -> {
                int edgeOff = 15 * (facing == Facing.NORTH ? 1 : -1);
                int off = 6 * (facing == Facing.NORTH ? 1 : -1);
                top.setSize(2, 8, 1);
                bot.setSize(2, 8, 1);
                edge.setSize(2, 1, 16);
                mid.setSize(2, 8, 14);
                top.setPosition(x, z + off, y + 15);
                bot.setPosition(x, z + off, y - 15);
                edge.setPosition(x, z + edgeOff, y);
                mid.setPosition(x, z + off, y);
            }
            case EAST, WEST -> {
                int edgeOff = 15 * (facing == Facing.WEST ? 1 : -1);
                int off = 6 * (facing == Facing.WEST ? 1 : -1);
                top.setSize(8, 2, 1);
                bot.setSize(8, 2, 1);
                edge.setSize(1, 2, 16);
                mid.setSize(8, 2, 14);
                top.setPosition(x + off, z, y + 15);
                bot.setPosition(x + off, z, y - 15);
                edge.setPosition(x + edgeOff, z, y);
                mid.setPosition(x + off, z, y);
            }
        }

        return List.of(top, bot, edge, mid);
    }

    public static List<Brick> extraSide(int x, int z, int y, Color border, Color inside, Facing facing) {
        Brick top = new Brick(border);
        Brick bot = new Brick(border);
        Brick edge = new Brick(border);
        Brick mid = new Brick(inside);

        switch (facing) {
            case NORTH, SOUTH -> {
                int edgeOff = 15 * (facing == Facing.NORTH ? 1 : -1);
                int off = 8 * (facing == Facing.NORTH ? 1 : -1);
                top.setSize(2, 6, 1);
                bot.setSize(2, 6, 1);
                edge.setSize(2, 1, 16);
                mid.setSize(2, 6, 14);
                top.setPosition(x, z + off, y + 15);
                bot.setPosition(x, z + off, y - 15);
                edge.setPosition(x, z + edgeOff, y);
                mid.setPosition(x, z + off, y);
            }
            case EAST, WEST -> {
                int edgeOff = 15 * (facing == Facing.WEST ? 1 : -1);
                int off = 8 * (facing == Facing.WEST ? 1 : -1);
                top.setSize(6, 2, 1);
                bot.setSize(6, 2, 1);
                edge.setSize(1, 2, 16);
                mid.setSize(6, 2, 14);
                top.setPosition(x + off, z, y + 15);
                bot.setPosition(x + off, z, y - 15);
                edge.setPosition(x + edgeOff, z, y);
                mid.setPosition(x + off, z, y);
            }
        }

        return List.of(top, bot, edge, mid);
    }

    public static List<Brick> corner(int x, int z, int y, Color border, Color inside, Facing face1, Facing face2) {
        List<Brick> bricks = new ArrayList<>(unconnectedPane(x, z, y, border, inside));
        bricks.addAll(extraSide(x, z, y, border, inside, face1));
        bricks.addAll(extraSide(x, z, y, border, inside, face2));
        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Color border, Color inside, Block block, boolean stained) {
        List<Brick> bricks = new ArrayList<>();

        boolean north = block.getProp("north").equals("true");
        boolean south = block.getProp("south").equals("true");
        boolean east = block.getProp("east").equals("true");
        boolean west = block.getProp("west").equals("true");

        if (north && south && !east && !west) {
            return straightPane(x, z, y, border, inside, true, stained);
        } else if (!north && !south && east && west) {
            return straightPane(x, z, y, border, inside, false, stained);
        } else if (!north && !south && !east && !west) {
            return unconnectedPane(x, z, y, border, inside);
        } else if (north && !south && !east && !west) {
            return singleSide(x, z, y, border, inside, Facing.NORTH);
        } else if (!north && south && !east && !west) {
            return singleSide(x, z, y, border, inside, Facing.SOUTH);
        } else if (!north && !south && east && !west) {
            return singleSide(x, z, y, border, inside, Facing.EAST);
        } else if (!north && !south && !east && west) {
            return singleSide(x, z, y, border, inside, Facing.WEST);
        } else if (north && south && east && !west) {
            bricks.addAll(straightPane(x, z, y, border, inside, true, stained));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.EAST));
        } else if (north && south && !east && west) {
            bricks.addAll(straightPane(x, z, y, border, inside, true, stained));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.WEST));
        } else if (north && south && east && west) {
            bricks.addAll(straightPane(x, z, y, border, inside, true, stained));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.EAST));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.WEST));
        } else if (north && !south && east && west) {
            bricks.addAll(straightPane(x, z, y, border, inside, false, stained));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.NORTH));
        } else if (!north && south && east && west) {
            bricks.addAll(straightPane(x, z, y, border, inside, true, stained));
            bricks.addAll(extraSide(x, z, y, border, inside, Facing.SOUTH));
        } else if (north && !south && east && !west) {
            return corner(x, z, y, border, inside, Facing.NORTH, Facing.EAST);
        } else if (north && !south && !east && west) {
            return corner(x, z, y, border, inside, Facing.NORTH, Facing.WEST);
        }  else if (!north && south && east && !west) {
            return corner(x, z, y, border, inside, Facing.SOUTH, Facing.EAST);
        } else if (!north && south && !east && west) {
            return corner(x, z, y, border, inside, Facing.SOUTH, Facing.WEST);
        }

        return bricks;
    }

}
