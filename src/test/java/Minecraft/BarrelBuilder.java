package Minecraft;

import com.kmschr.brs.Brick;

import java.util.List;

public class BarrelBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        Facing facing = Facing.fromProps(block.getProps());

        Brick band1 = new Brick();
        Brick band2 = new Brick();
        Brick top = new Brick();
        Brick bot = new Brick();
        Brick mid = new Brick();
        band1.setColor(Colors.BARREL_BAND);
        band2.setColor(Colors.BARREL_BAND);
        top.setColor(Colors.BARREL_LIGHT);
        bot.setColor(Colors.BARREL_LIGHT);
        mid.setColor(Colors.BARREL_LIGHT);
        mid.setPosition(x, z, y);

        int boff = 8;
        int coff = 13;

        switch (facing) {
            case NORTH, SOUTH -> {
                band1.setSize(16, 2, 16);
                band2.setSize(16, 2, 16);
                band1.setPosition(x, z + boff, y);
                band2.setPosition(x, z - boff, y);
                top.setSize(16, 3, 16);
                bot.setSize(16, 3, 16);
                top.setPosition(x, z + coff, y);
                bot.setPosition(x, z - coff, y);
                mid.setSize(16, 6, 16);
            }
            case EAST, WEST -> {
                band1.setSize(2, 16, 16);
                band2.setSize(2, 16, 16);
                band1.setPosition(x + boff, z, y);
                band2.setPosition(x - boff, z, y);
                top.setSize(3, 16, 16);
                bot.setSize(3, 16, 16);
                top.setPosition(x + coff, z, y);
                bot.setPosition(x - coff, z, y);
                mid.setSize(6, 16, 16);
            }
            case UP, DOWN -> {
                band1.setSize(16, 16, 2);
                band2.setSize(16, 16, 2);
                band1.setPosition(x, z, y + boff);
                band2.setPosition(x, z, y - boff);
                top.setSize(16, 16, 3);
                bot.setSize(16, 16, 3);
                top.setPosition(x, z, y + coff);
                bot.setPosition(x, z, y - coff);
                mid.setSize(16, 16, 6);
            }
        }

        return List.of(band1, band2, top, bot, mid);
    }

}
