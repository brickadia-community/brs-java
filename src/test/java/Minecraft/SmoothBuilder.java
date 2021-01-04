package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class SmoothBuilder {

    public static List<Brick> sides(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 3; ++i) {
            Brick top = new Brick();
            Brick left = new Brick();
            Brick right = new Brick();
            top.setColor(color);
            left.setColor(color);
            right.setColor(color);
            top.setSize(14, 14, 1);
            left.setSize(1, 14, 14);
            right.setSize(14, 1, 14);
            bricks.add(top);
            bricks.add(left);
            bricks.add(right);
        }

        int off = 15;

        bricks.get(0).setPosition(x, z, y + off);
        bricks.get(3).setPosition(x, z, y - off);
        bricks.get(1).setPosition(x + off, z, y);
        bricks.get(4).setPosition(x - off, z, y);
        bricks.get(2).setPosition(x, z + off, y);
        bricks.get(5).setPosition(x, z - off, y);

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Color outside, Color inside) {
        List<Brick> bricks = CageBuilder.build(x, z, y, outside);
        bricks.addAll(sides(x, z, y, inside));
        return bricks;
    }

}
