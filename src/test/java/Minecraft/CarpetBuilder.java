package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class CarpetBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 1);
        brick.setPosition(x, z, y - 15);
        brick.setColor(color);
        return List.of(brick);
    }

}
