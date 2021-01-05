package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class ShulkerBuilder {

    public static List<Brick> build(int x, int z, int y, Color light, Color dark) {
        Brick base = new Brick(dark);
        base.setSize(16, 16, 3);
        base.setPosition(x, z, y - 13);
        Brick baseL = new Brick(dark);
        baseL.setSize(16, 8, 5);
        baseL.setPosition(x, z, y - 5);
        Brick baseSL = new Brick(dark);
        Brick baseSR = new Brick(dark);
        baseSL.setSize(8, 4, 5);
        baseSR.setSize(8, 4, 5);
        baseSL.setPosition(x, z + 12, y - 5);
        baseSR.setPosition(x, z - 12, y - 5);
        Brick top = new Brick(light);
        top.setSize(16, 16, 8);
        top.setPosition(x, z, y + 8);
        Brick corn1 = new Brick(light);
        Brick corn2 = new Brick(light);
        Brick corn3 = new Brick(light);
        Brick corn4 = new Brick(light);
        corn1.setSize(4, 4, 5);
        corn2.setSize(4, 4, 5);
        corn3.setSize(4, 4, 5);
        corn4.setSize(4, 4, 5);
        corn1.setPosition(x + 12, z + 12, y - 5);
        corn2.setPosition(x + 12, z - 12, y - 5);
        corn3.setPosition(x - 12, z + 12, y - 5);
        corn4.setPosition(x - 12, z - 12, y - 5);
        return List.of(base, baseL, baseSL, baseSR, top, corn1, corn2, corn3, corn4);
    }

}
