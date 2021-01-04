package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class PressurePlateBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setSize(14, 14, 1);
        brick.setPosition(x, z, y - 15);
        return List.of(brick);
    }

}
