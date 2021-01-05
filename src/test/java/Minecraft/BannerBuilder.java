package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class BannerBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Brick center = new Brick();
        center.setColor(Colors.OAK);
        center.setSize(2, 2, 27);
        center.setPosition(x, z, y + 11);

        Brick bar = new Brick();
        bar.setColor(Colors.OAK);
        Brick cloth = new Brick();
        cloth.setColor(color);

        int rotation = Integer.parseInt(block.getProp("rotation"));

        switch (rotation) {
            // South/North
            case 0, 14, 15, 7, 8, 9 -> {
                int off = 3 * (rotation >= 7 && rotation <= 9 ? 1 : -1);
                bar.setSize(13, 2, 2);
                bar.setPosition(x, z, y + 40);
                cloth.setSize(13, 1, 27);
                cloth.setPosition(x, z + off, y + 15);
            }
            case 3, 4, 5, 11, 12, 13 -> {
                int off = 3 * (rotation >= 3 && rotation <= 5 ? 1 : -1);
                bar.setSize(2, 13, 2);
                bar.setPosition(x, z, y + 40);
                cloth.setSize(1, 13, 27);
                cloth.setPosition(x + off, z, y + 15);
            }
        }

        return List.of(center, bar, cloth);
    }

}
