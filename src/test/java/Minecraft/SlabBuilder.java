package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class SlabBuilder {

    public static Brick top(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 8);
        brick.setColor(color);
        brick.setPosition(x, z, y + 8);
        return brick;
    }

    public static Brick bottom(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 8);
        brick.setColor(color);
        brick.setPosition(x, z, y - 8);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        String type = block.getProp("type");
        return switch (type) {
            case "bottom" -> List.of(bottom(x, z, y, color));
            case "top" -> List.of(top(x, z, y, color));
            case "double" -> List.of(bottom(x, z, y, color), top(x, z, y, color));
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

}
