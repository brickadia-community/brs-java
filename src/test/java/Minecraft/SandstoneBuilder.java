package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class SandstoneBuilder {

    public static List<Brick> build (int x, int z, int y, int rx, int rz, int ry, Color color, Block[][][] blocks) {
        List<Brick> bricks = new ArrayList<>();

        Brick bot = GrassBuilder.bot7o8(x, z, y, rx, rz, ry, color, blocks);
        if (bot != null)
            bricks.add(bot);

        bricks.add(GrassBuilder.top1o8(x, z, y, color));

        return bricks;
    }

}
