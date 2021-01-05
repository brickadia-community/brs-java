package Minecraft;

import com.kmschr.brs.Brick;

import java.util.List;

public class ChainBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        Brick brick = new Brick();
        brick.setPosition(x, z, y);
        brick.setColor(Colors.CHAIN);

        String axis = block.getProp("axis");

        switch (axis) {
            case "x" -> brick.setSize(16, 1, 1);
            case "z" -> brick.setSize(1, 16, 1);
            case "y" -> brick.setSize(1, 1, 16);
        }

        return List.of(brick);
    }

}
