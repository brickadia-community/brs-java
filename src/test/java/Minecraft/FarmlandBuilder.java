package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class FarmlandBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        int moisture = Integer.parseInt(block.getProp("moisture"));
        if (moisture < 7) {
            return CubeBuilder.build(x, z, y, new Color(143, 103, 71, 255));
        } else {
            return CubeBuilder.build(x, z, y, new Color(82, 44, 15, 255));
        }
    }

}
