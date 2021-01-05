package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class FenceGateBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Facing facing = Facing.fromProps(block.getProps());

        Brick edgeL = new Brick();
        Brick edgeR = new Brick();
        Brick barTop = new Brick();
        Brick barBot = new Brick();
        Brick mid = new Brick();
        edgeL.setColor(color);
        edgeR.setColor(color);
        barTop.setColor(color);
        barBot.setColor(color);
        mid.setColor(color);
        edgeL.setSize(2, 2, 11);
        edgeR.setSize(2, 2, 11);
        barTop.setPosition(x, z, y + 11);
        barBot.setPosition(x, z, y - 1);
        mid.setPosition(x, z, y + 5);

        switch (facing) {
            case NORTH, SOUTH -> {
                edgeL.setPosition(x - 14, z, y + 5);
                edgeR.setPosition(x + 14, z, y + 5);
                barTop.setSize(12, 2, 3);
                barBot.setSize(12, 2, 3);
                mid.setSize(4, 2, 3);
            }
            case WEST, EAST -> {
                edgeL.setPosition(x, z - 14, y + 5);
                edgeR.setPosition(x, z + 14, y + 5);
                barTop.setSize(2, 12, 3);
                barBot.setSize(2, 12, 3);
                mid.setSize(2, 4, 3);
            }
        }

        boolean in_wall = block.getProp("in_wall").equals("true");
        if (in_wall) {
            edgeL.shift(0, 0, -6);
            edgeR.shift(0, 0, -6);
            barTop.shift(0, 0, -6);
            barBot.shift(0, 0, -6);
        }

        return List.of(edgeL, edgeR, barTop, barBot, mid);
    }

}
