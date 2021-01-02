package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class WallBuilder {

    private static final Color COBBLE = new Color(127, 127, 127, 255);

    private static Brick center(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setSize(8, 8, 16);
        brick.setPosition(x, z, y);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        bricks.add(center(x, z, y, COBBLE));
        return bricks;
    }

}
