package Minecraft;

import com.kmschr.brs.Brick;

import java.util.ArrayList;
import java.util.List;

public class LanternBuilder {

    private static List<Brick> lantern(int x, int z, int y) {
        Brick base = new Brick();
        Brick top = new Brick();
        base.setColor(Colors.CHAIN);
        top.setColor(Colors.CHAIN);
        top.setSize(6, 6, 1);
        base.setSize(6, 6, 1);
        base.setPosition(x, z, y - 15);
        top.setPosition(x, z, y - 3);

        Brick capBot = new Brick();
        Brick capTop = new Brick();
        capBot.setColor(Colors.LANTERN_EDGE);
        capTop.setColor(Colors.CHAIN);
        capBot.setSize(4, 4, 1);
        capTop.setSize(4, 4, 1);
        capBot.setPosition(x, z, y - 1);
        capTop.setPosition(x, z, y + 1);

        Brick corner1 = new Brick();
        Brick corner2 = new Brick();
        Brick corner3 = new Brick();
        Brick corner4 = new Brick();
        corner1.setColor(Colors.LANTERN_EDGE);
        corner2.setColor(Colors.LANTERN_EDGE);
        corner3.setColor(Colors.LANTERN_EDGE);
        corner4.setColor(Colors.LANTERN_EDGE);
        corner1.setSize(1, 1, 5);
        corner2.setSize(1, 1, 5);
        corner3.setSize(1, 1, 5);
        corner4.setSize(1, 1, 5);
        corner1.setPosition(x + 5, z + 5, y - 9);
        corner2.setPosition(x + 5, z - 5, y - 9);
        corner3.setPosition(x - 5, z + 5, y - 9);
        corner4.setPosition(x - 5, z - 5, y - 9);

        Brick flame1 = new Brick();
        Brick flame2 = new Brick();
        Brick flame3 = new Brick();
        flame1.setColor(Colors.LANTERN_FLAME);
        flame2.setColor(Colors.LANTERN_FLAME);
        flame3.setColor(Colors.LANTERN_FLAME);
        flame1.setMaterialIndex(1);
        flame2.setMaterialIndex(1);
        flame3.setMaterialIndex(1);
        flame1.setSize(6, 4, 5);
        flame2.setSize(4, 1, 5);
        flame3.setSize(4, 1, 5);
        flame1.setPosition(x, z, y - 9);
        flame2.setPosition(x, z + 5, y - 9);
        flame3.setPosition(x, z - 5, y - 9);

        return List.of(base, top, capBot, capTop, corner1, corner2, corner3, corner4, flame1, flame2, flame3);
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        boolean hanging = block.getProp("hanging").equals("true");
        List<Brick> bricks = new ArrayList<>(lantern(x, z, y));
        Brick chain = new Brick();
        chain.setColor(Colors.CHAIN);
        if (!hanging) {
            chain.setSize(1, 1, 2);
            chain.setPosition(x, z, y + 4);
        } else {
            chain.setSize(1, 1, 7);
            chain.setPosition(x, z, y + 9);
        }
        bricks.add(chain);
        return bricks;
    }

}
