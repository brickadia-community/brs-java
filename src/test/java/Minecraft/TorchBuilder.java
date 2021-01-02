package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class TorchBuilder {

    private static final Color BASE = new Color(110, 87, 55, 255);
    private static final Color GLOW = new Color(255, 255, 203, 255);
    private static final Color FLAME = new Color(255, 180, 0, 255);

    public static List<Brick> build(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        Brick base = new Brick();
        base.setColor(BASE);
        base.setSize(2, 2, 8);
        base.setPosition(x, z, y - 8);
        bricks.add(base);

        Brick glow = new Brick();
        glow.setColor(GLOW);
        glow.setSize(2, 2, 1);
        glow.setPosition(x, z, y + 1);
        bricks.add(glow);

        Brick flame = new Brick();
        flame.setColor(FLAME);
        flame.setSize(2, 2, 1);
        flame.setPosition(x, z, y + 3);
        flame.setMaterialIndex(1);
        bricks.add(flame);

        return bricks;
    }

}
