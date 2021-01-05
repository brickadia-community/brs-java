package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class GlassBuilder {

    public static List<Brick> build(int x, int z, int y, Color border, Color glass) {
        List<Brick> bricks = CageBuilder.build(x, z, y, border);
        Brick brick = new Brick(glass);
        brick.setSize(14, 14, 14);
        brick.setPosition(x, z, y);
        bricks.add(brick);
        return bricks;
    }

}
