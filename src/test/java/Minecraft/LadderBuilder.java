package Minecraft;

import com.kmschr.brs.Brick;

import java.util.ArrayList;
import java.util.List;

public class LadderBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        Facing facing = Facing.fromProps(block.getProps());

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 15 * (facing == Facing.NORTH ? 1 : -1);

                for (int i=0; i < 4; ++i) {
                    Brick rung = new Brick();
                    rung.setSize(14, 1, 2);
                    rung.setPosition(x, z + off, y - 12 + i * 8);
                    rung.setColor(Colors.OAK);
                    bricks.add(rung);
                }

                for (int i=0; i < 3; ++i) {
                    Brick left = new Brick();
                    Brick right = new Brick();
                    left.setColor(Colors.OAK);
                    right.setColor(Colors.OAK);
                    left.setSize(2, 1, 2);
                    right.setSize(2, 1, 2);
                    left.setPosition(x + 10, z + off, y - 8 + i * 8);
                    right.setPosition(x - 10, z + off, y - 8 + i * 8);
                    bricks.add(left);
                    bricks.add(right);
                }

                for (int i=0; i < 2; ++i) {
                    Brick left = new Brick();
                    Brick right = new Brick();
                    left.setColor(Colors.OAK);
                    right.setColor(Colors.OAK);
                    left.setSize(2, 1, 1);
                    right.setSize(2, 1, 1);
                    left.setPosition(x + 10, z + off, y - 15 + i * 30);
                    right.setPosition(x - 10, z + off, y - 15 + i * 30);
                    bricks.add(left);
                    bricks.add(right);
                }

            }
            case EAST, WEST -> {
                int off = 15 * (facing == Facing.WEST ? 1 : -1);

                for (int i=0; i < 4; ++i) {
                    Brick rung = new Brick();
                    rung.setSize(1, 14, 2);
                    rung.setPosition(x + off, z, y - 12 + i * 8);
                    rung.setColor(Colors.OAK);
                    bricks.add(rung);
                }

                for (int i=0; i < 3; ++i) {
                    Brick left = new Brick();
                    Brick right = new Brick();
                    left.setColor(Colors.OAK);
                    right.setColor(Colors.OAK);
                    left.setSize(1, 2, 2);
                    right.setSize(1, 2, 2);
                    left.setPosition(x + off, z + 10, y - 8 + i * 8);
                    right.setPosition(x + off, z - 10, y - 8 + i * 8);
                    bricks.add(left);
                    bricks.add(right);
                }

                for (int i=0; i < 2; ++i) {
                    Brick left = new Brick();
                    Brick right = new Brick();
                    left.setColor(Colors.OAK);
                    right.setColor(Colors.OAK);
                    left.setSize(1, 2, 1);
                    right.setSize(1, 2, 1);
                    left.setPosition(x + off, z + 10, y - 15 + i * 30);
                    right.setPosition(x + off, z - 10, y - 15 + i * 30);
                    bricks.add(left);
                    bricks.add(right);
                }

            }
        }

        return bricks;
    }

}
