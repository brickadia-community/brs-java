package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class WallSignBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Brick brick = new Brick();
        brick.setColor(color);

        Facing facing = Facing.fromProps(block.getProps());

        switch (facing) {
            case NORTH, SOUTH -> {
                int off = 13 * (facing == Facing.NORTH ? 1 : -1);
                brick.setSize(16, 2, 12);
                brick.setPosition(x, z + off, y);
            }
            case WEST, EAST -> {
                int off = 13 * (facing == Facing.WEST ? 1 : -1);
                brick.setSize(2, 16, 12);
                brick.setPosition(x + off, z, y);
            }
        }

        return List.of(brick);
    }

}
