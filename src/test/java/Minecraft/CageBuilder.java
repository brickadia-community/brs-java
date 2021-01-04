package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class CageBuilder {

    public static List<Brick> build(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; ++i) {
            Brick corner = new Brick();
            Brick edgeL = new Brick();
            Brick edgeS = new Brick();
            corner.setColor(color);
            edgeL.setColor(color);
            edgeS.setColor(color);
            corner.setSize(1, 1, 16);
            edgeL.setSize(1, 14, 1);
            edgeS.setSize(14, 1, 1);
            bricks.add(corner);
            bricks.add(edgeL);
            bricks.add(edgeS);
        }

        int off = 15;

        bricks.get(0).setPosition(x + off, z + off, y);
        bricks.get(3).setPosition(x + off, z - off, y);
        bricks.get(6).setPosition(x - off, z + off, y);
        bricks.get(9).setPosition(x - off, z - off, y);
        bricks.get(1).setPosition(x + off, z, y - off);
        bricks.get(4).setPosition(x - off, z, y - off);
        bricks.get(7).setPosition(x + off, z, y + off);
        bricks.get(10).setPosition(x - off, z, y + off);
        bricks.get(2).setPosition(x, z + off, y - off);
        bricks.get(5).setPosition(x, z - off, y - off);
        bricks.get(8).setPosition(x, z + off, y + off);
        bricks.get(11).setPosition(x, z - off, y + off);

        return bricks;
    }

}
