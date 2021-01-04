package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class BricksBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 2; ++i) {
            Brick brick = new Brick();
            brick.setSize(16, 8, 8);
            brick.setColor(color);
            bricks.add(brick);
        }

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick();
            brick.setSize(8, 8, 8);
            brick.setColor(color);
            bricks.add(brick);
        }

        int off = 8;

        bricks.get(0).setPosition(x, z - off, y - off);
        bricks.get(1).setPosition(x, z + off, y + off);
        bricks.get(2).setPosition(x + off, z + off, y - off);
        bricks.get(3).setPosition(x - off, z + off, y - off);
        bricks.get(4).setPosition(x + off, z - off, y + off);
        bricks.get(5).setPosition(x - off, z - off, y + off);

        return bricks;
    }

}
