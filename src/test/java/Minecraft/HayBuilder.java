package Minecraft;

import com.kmschr.brs.Brick;

import java.util.List;

public class HayBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        String axis = block.getProp("axis");

        Brick band1 = new Brick();
        Brick band2 = new Brick();
        Brick top = new Brick();
        Brick bot = new Brick();
        Brick mid = new Brick();
        band1.setColor(Colors.HAY_RIBBON);
        band2.setColor(Colors.HAY_RIBBON);
        top.setColor(Colors.HAY);
        bot.setColor(Colors.HAY);
        mid.setColor(Colors.HAY);

        switch (axis) {
            case "z" -> {
                band1.setSize(16, 1, 16);
                band2.setSize(16, 1, 16);
                band1.setPosition(x, z + 9, y);
                band2.setPosition(x, z - 7, y);
                top.setSize(16, 3, 16);
                bot.setSize(16, 4, 16);
                top.setPosition(x, z + 13, y);
                bot.setPosition(x, z - 12, y);
                mid.setSize(16, 7, 16);
                mid.setPosition(x, z + 1, y);
            }
            case "x" -> {
                band1.setSize(1, 16, 16);
                band2.setSize(1, 16, 16);
                band1.setPosition(x + 9, z, y);
                band2.setPosition(x - 7, z, y);
                top.setSize(3, 16, 16);
                bot.setSize(4, 16, 16);
                top.setPosition(x + 13, z, y);
                bot.setPosition(x - 12, z, y);
                mid.setSize(7, 16, 16);
                mid.setPosition(x + 1, z, y);
            }
            case "y" -> {
                band1.setSize(16, 16, 1);
                band2.setSize(16, 16, 1);
                band1.setPosition(x, z, y + 9);
                band2.setPosition(x, z, y - 7);
                top.setSize(16, 16, 3);
                bot.setSize(16, 16, 4);
                top.setPosition(x, z, y + 13);
                bot.setPosition(x, z, y - 12);
                mid.setSize(16, 16, 7);
                mid.setPosition(x, z, y + 1);
            }
        }

        return List.of(band1, band2, top, bot, mid);
    }

}
