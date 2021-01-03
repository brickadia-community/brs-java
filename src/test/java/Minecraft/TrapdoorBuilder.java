package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class TrapdoorBuilder {

    private static final Color SPRUCE_BODY = new Color(106, 79, 44, 255);
    private static final Color SPRUCE_METAL = new Color(73, 73, 73, 255);

    private static List<Brick> spruceOpen(int x, int z, int y, Facing facing) {
        List<Brick> bricks = new ArrayList<>();
        int moff = 9;
        int boff = 13;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 13 * (facing == Facing.NORTH ? 1  : - 1);

                for (int i=0; i < 2; ++i) {
                    Brick metal = new Brick();
                    Brick wood = new Brick();
                    metal.setColor(SPRUCE_METAL);
                    wood.setColor(SPRUCE_BODY);
                    metal.setSize(16, 3, 1);
                    wood.setSize(16, 3, 3);
                    bricks.add(metal);
                    bricks.add(wood);
                }

                bricks.get(0).setPosition(x, z + off, y - moff);
                bricks.get(1).setPosition(x, z + off, y - boff);
                bricks.get(2).setPosition(x, z + off, y + moff);
                bricks.get(3).setPosition(x, z + off, y + boff);

                Brick center = new Brick();
                center.setSize(16, 3, 8);
                center.setColor(SPRUCE_BODY);
                center.setPosition(x, z + off, y);
                bricks.add(center);
            }
            case EAST, WEST -> {
                int off = 13 * (facing == Facing.WEST ? 1  : - 1);

                for (int i=0; i < 2; ++i) {
                    Brick metal = new Brick();
                    Brick wood = new Brick();
                    metal.setColor(SPRUCE_METAL);
                    wood.setColor(SPRUCE_BODY);
                    metal.setSize(3, 16, 1);
                    wood.setSize(3, 16, 3);
                    bricks.add(metal);
                    bricks.add(wood);
                }

                bricks.get(0).setPosition(x + off, z, y - moff);
                bricks.get(1).setPosition(x + off, z, y - boff);
                bricks.get(2).setPosition(x + off, z, y + moff);
                bricks.get(3).setPosition(x + off, z, y + boff);

                Brick center = new Brick();
                center.setSize(3, 16, 8);
                center.setColor(SPRUCE_BODY);
                center.setPosition(x + off, z, y);
                bricks.add(center);
            }
        }

        return bricks;
    }

    private static List<Brick> spruceClosed(int x, int z, int y, Facing facing, String half) {
        List<Brick> bricks = new ArrayList<>();
        int moff = 9;
        int boff = 13;
        int off = 13 * (half.equals("top") ? 1  : - 1);

        switch (facing) {
            case NORTH, SOUTH -> {
                for (int i=0; i < 2; ++i) {
                    Brick metal = new Brick();
                    Brick wood = new Brick();
                    metal.setColor(SPRUCE_METAL);
                    wood.setColor(SPRUCE_BODY);
                    metal.setSize(16, 1, 3);
                    wood.setSize(16, 3, 3);
                    bricks.add(metal);
                    bricks.add(wood);
                }

                bricks.get(0).setPosition(x, z + moff, y + off);
                bricks.get(1).setPosition(x, z + boff, y + off);
                bricks.get(2).setPosition(x, z - moff, y + off);
                bricks.get(3).setPosition(x, z - boff, y + off);

                Brick center = new Brick();
                center.setColor(SPRUCE_BODY);
                center.setSize(16, 8, 3);
                center.setPosition(x, z, y + off);
                bricks.add(center);
            }
            case EAST, WEST -> {
                for (int i=0; i < 2; ++i) {
                    Brick metal = new Brick();
                    Brick wood = new Brick();
                    metal.setColor(SPRUCE_METAL);
                    wood.setColor(SPRUCE_BODY);
                    metal.setSize(1, 16, 3);
                    wood.setSize(3, 16, 3);
                    bricks.add(metal);
                    bricks.add(wood);
                }

                bricks.get(0).setPosition(x + moff, z, y + off);
                bricks.get(1).setPosition(x + boff, z, y + off);
                bricks.get(2).setPosition(x - moff, z, y + off);
                bricks.get(3).setPosition(x - boff, z, y + off);

                Brick center = new Brick();
                center.setColor(SPRUCE_BODY);
                center.setSize(8, 16, 3);
                center.setPosition(x, z, y + off);
                bricks.add(center);
            }
        }

        return bricks;
    }

    private static List<Brick> spruce(int x, int z, int y, Facing facing, String half, String open) {
        return switch (open) {
            case "true" -> spruceOpen(x, z, y, facing);
            case "false" -> spruceClosed(x, z, y, facing, half);
            default -> throw new IllegalStateException("Unexpected value: " + open);
        };
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        Facing facing = Facing.fromProps(block.getProps());
        String open = block.getProp("open");
        String half = block.getProp("half");
        bricks.addAll(spruce(x, z, y, facing, half, open));
        return bricks;
    }

}
