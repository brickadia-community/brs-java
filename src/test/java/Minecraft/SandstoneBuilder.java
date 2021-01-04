package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class SandstoneBuilder {

    public static List<Brick> build (int x, int z, int y, Color color) {
        return List.of(GrassBuilder.bot7o8(x, z, y, color), GrassBuilder.top1o8(x, z, y, color));
    }

}
