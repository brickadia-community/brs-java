package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class StairsBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        String half = block.getProp("half");
        Facing facing = Facing.fromProps(block.getProps());
        List<Brick> bricks = new ArrayList<>();

        switch (half) {
            case "top" -> bricks.add(SlabBuilder.top(x, z, y, color));
            case "bottom" -> bricks.add(SlabBuilder.bottom(x, z, y, color));
        }

        int offset = 2*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case NORTH -> {
                Brick brick = new Brick();
                brick.setSize(16, 8, 8);
                brick.setPosition(x, z - 8, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case SOUTH -> {
                Brick brick = new Brick();
                brick.setSize(16, 8, 8);
                brick.setPosition(x, z + 8, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case EAST -> {
                Brick brick = new Brick();
                brick.setSize(8, 16, 8);
                brick.setPosition(x + 8, z, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case WEST -> {
                Brick brick = new Brick();
                brick.setSize(8, 16, 8);
                brick.setPosition(x - 8, z, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
        }

        return bricks;
    }

}
