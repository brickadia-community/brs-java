package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class FenceBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        List<Brick> bricks = new ArrayList<>();
        Brick post = new Brick();
        post.setSize(4, 4, 16);
        post.setPosition(x, z, y);
        post.setColor(color);
        bricks.add(post);

        boolean north = block.getProp("north").equals("true");
        boolean south = block.getProp("south").equals("true");
        boolean east = block.getProp("east").equals("true");
        boolean west = block.getProp("west").equals("true");

        if (north) {
            Brick bot = new Brick();
            bot.setSize(2, 6, 3);
            bot.setPosition(x, z - 10, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(2, 6, 3);
            top.setPosition(x, z - 10, y + 11);
            top.setColor(color);
            bricks.add(top);
        }
        if (south) {
            Brick bot = new Brick();
            bot.setSize(2, 6, 3);
            bot.setPosition(x, z + 10, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(2, 6, 3);
            top.setPosition(x, z + 10, y + 11);
            top.setColor(color);
            bricks.add(top);
        }
        if (east) {
            Brick bot = new Brick();
            bot.setSize(6, 2, 3);
            bot.setPosition(x + 10, z, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(6, 2, 3);
            top.setPosition(x + 10, z, y + 11);
            top.setColor(color);
            bricks.add(top);
        }
        if (west) {
            Brick bot = new Brick();
            bot.setSize(6, 2, 3);
            bot.setPosition(x - 10, z, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(6, 2, 3);
            top.setPosition(x - 10, z, y + 11);
            top.setColor(color);
            bricks.add(top);
        }

        return bricks;
    }

}
