package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;
import java.util.List;

public class CubeBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 16);
        brick.setPosition(x, z, y);
        brick.setColor(color);
        return List.of(brick);
    }

}
