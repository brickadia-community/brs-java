package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class ButtonBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Brick brick = new Brick();
        brick.setColor(color);
        String face = block.getProp("face");
        Facing facing = Facing.fromProps(block.getProps());

        switch (face) {
            case "floor" -> {
                brick.setSize(6, 3, 1);
                brick.setPosition(x, z, y - 15);
            }
            case "ceiling" -> {
                brick.setSize(6, 3, 1);
                brick.setPosition(x, z, y + 15);
            }
            case "wall" -> {
                switch (facing) {
                    case NORTH, SOUTH -> {
                        int off = 15 * (facing == Facing.NORTH ? 1 : -1);
                        brick.setSize(6, 1, 3);
                        brick.setPosition(x, z + off, y);
                    }
                    case EAST, WEST -> {
                        int off = 15 * (facing == Facing.WEST ? 1 : -1);
                        brick.setSize(1, 6, 3);
                        brick.setPosition(x + off, z, y);
                    }
                }
            }
        }

        return List.of(brick);
    }

}
