package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class LogBuilder {

    public static List<Brick> logSides(int x, int z, int y, Color color, String axis) {
        Brick brickT = new Brick();
        Brick brickL = new Brick();
        Brick brickR = new Brick();
        Brick brickD = new Brick();
        switch (axis) {
            case "y" -> {
                brickT.setSize(16, 1, 16);
                brickD.setSize(16, 1, 16);
                brickR.setSize(1, 14, 16);
                brickL.setSize(1, 14, 16);
                brickT.setPosition(x, z + 15, y);
                brickD.setPosition(x, z - 15, y);
                brickR.setPosition(x + 15, z, y);
                brickL.setPosition(x - 15, z, y);
            }
            case "z" -> {
                brickT.setSize(16, 16, 1);
                brickD.setSize(16, 16, 1);
                brickR.setSize(1, 16, 14);
                brickL.setSize(1, 16, 14);
                brickT.setPosition(x, z, y + 15);
                brickD.setPosition(x, z, y - 15);
                brickR.setPosition(x + 15, z, y);
                brickL.setPosition(x - 15, z, y);
            }
            case "x" -> {
                brickT.setSize(16, 16, 1);
                brickD.setSize(16, 16, 1);
                brickR.setSize(16, 1, 14);
                brickL.setSize(16, 1, 14);
                brickT.setPosition(x, z, y + 15);
                brickD.setPosition(x, z, y - 15);
                brickR.setPosition(x, z + 15, y);
                brickL.setPosition(x, z - 15, y);
            }
        }

        brickT.setColor(color);
        brickD.setColor(color);
        brickR.setColor(color);
        brickL.setColor(color);
        return List.of(brickT, brickL, brickR, brickD);
    }

    public static Brick logInside(int x, int z, int y, Color color, String axis) {
        Brick brick = new Brick();
        switch (axis) {
            case "y" -> brick.setSize(14, 14, 16);
            case "z" -> brick.setSize(14, 16, 14);
            case "x" -> brick.setSize(16, 14, 14);
        }
        brick.setPosition(x, z, y);
        brick.setColor(color);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y, Color inside, Color outside, Block block) {
        String axis = block.getProp("axis");
        List<Brick> bricks = new java.util.ArrayList<>(List.of(logInside(x, z, y, inside, axis)));
        bricks.addAll(logSides(x, z, y, outside, axis));
        return bricks;
    }

}
