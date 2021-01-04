package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class PortalBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setPosition(x, z, y);
        brick.setCollision(false);
        brick.setMaterialIndex(4);
        String axis = block.getProp("axis");

        switch (axis) {
            case "x" -> brick.setSize(16, 1, 16);
            case "z" -> brick.setSize(1, 16, 16);
        }

        return List.of(brick);
    }

}
