package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class GrassBuilder {

    private static Brick bot7o8(int x, int z, int y) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 14);
        brick.setPosition(x, z, y - 2);
        brick.setColor(Colors.DIRT);
        return brick;
    }

    private static Brick top1o8(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 2);
        brick.setPosition(x, z, y + 14);
        brick.setColor(color);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y, Color color) {
        return List.of(bot7o8(x, z, y), top1o8(x, z, y, color));
    }

}
