package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.enums.Direction;
import com.kmschr.brs.enums.Rotation;

import java.util.ArrayList;
import java.util.List;

public class WallTorchBuilder {

    private static List<Brick> wedges(int x, int z, int y, Facing facing) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 5; ++i) {
            Brick brick = new Brick();
            brick.setAssetNameIndex(1);
            switch (facing) {
                case NORTH -> brick.setDirection(Direction.XNegative);
                case SOUTH -> brick.setDirection(Direction.XPositive);
                case EAST -> brick.setDirection(Direction.YNegative);
                case WEST -> brick.setDirection(Direction.YPositive);
            }
            bricks.add(brick);
        }

        bricks.get(0).setRotation(Rotation.Deg180);
        bricks.get(0).setColor(TorchBuilder.BASE);
        bricks.get(0).setSize(4,2,2);
        bricks.get(1).setRotation(Rotation.Deg180);
        bricks.get(1).setColor(TorchBuilder.BASE);
        bricks.get(1).setSize(4,2,2);
        bricks.get(2).setRotation(Rotation.Deg180);
        bricks.get(2).setColor(TorchBuilder.FLAME);
        bricks.get(2).setMaterialIndex(1);
        bricks.get(2).setSize(2,1,2);
        bricks.get(3).setRotation(Rotation.Deg0);
        bricks.get(3).setColor(TorchBuilder.BASE);
        bricks.get(3).setSize(4,2,2);
        bricks.get(4).setRotation(Rotation.Deg0);
        bricks.get(4).setColor(TorchBuilder.FLAME);
        bricks.get(4).setMaterialIndex(1);
        bricks.get(4).setSize(2,1,2);

        int h1 = -6;
        int h2 = 2;
        int h3 = 8;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 14 * (facing == Facing.NORTH ? 1 : -1);
                int off2 = 10 * (facing == Facing.NORTH ? 1 : -1);
                int off3 = 7 * (facing == Facing.NORTH ? 1 : -1);
                int off4 = 11 * (facing == Facing.NORTH ? 1 : -1);

                bricks.get(0).setPosition(x, z + off, y + h1);
                bricks.get(1).setPosition(x, z + off2, y + h2);
                bricks.get(2).setPosition(x, z + off3, y + h3);
                bricks.get(3).setPosition(x, z + off, y + h2);
                bricks.get(4).setPosition(x, z + off4, y + h3);
            }
            case EAST, WEST -> {
                int off = 14 * (facing == Facing.WEST ? 1 : -1);
                int off2 = 10 * (facing == Facing.WEST ? 1 : -1);
                int off3 = 7 * (facing == Facing.WEST ? 1 : -1);
                int off4 = 11 * (facing == Facing.WEST ? 1 : -1);

                bricks.get(0).setPosition(x + off, z, y + h1);
                bricks.get(1).setPosition(x + off2, z, y + h2);
                bricks.get(2).setPosition(x + off3, z, y + h3);
                bricks.get(3).setPosition(x + off, z, y + h2);
                bricks.get(4).setPosition(x + off4, z, y + h3);
            }
        }

        return bricks;
    }

    private static Brick flame(int x, int z, int y, Facing facing) {
        Brick brick = new Brick();
        brick.setColor(TorchBuilder.FLAME);
        brick.setMaterialIndex(1);

        int h = 8;

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 9 * (facing == Facing.NORTH ? 1 : -1);
                brick.setSize(2, 1, 2);
                brick.setPosition(x, z + off, y + h);
            }
            case EAST, WEST -> {
                int off = 9 * (facing == Facing.WEST ? 1 : -1);
                brick.setSize(1, 2, 2);
                brick.setPosition(x + off, z, y + h);
            }
        }

        return brick;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        Facing facing = Facing.fromProps(block.getProps());
        List<Brick> bricks = new ArrayList<>(wedges(x, z, y, facing));
        bricks.add(flame(x, z, y, facing));
        return bricks;
    }

}
