package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class CarpetBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 2);
        brick.setPosition(x, z, y - 14);
        brick.setColor(color);
        return List.of(brick);
    }

}
