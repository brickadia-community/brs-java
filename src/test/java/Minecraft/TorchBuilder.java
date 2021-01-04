package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class TorchBuilder {

    public static final Color BASE = new Color(110, 87, 55, 255);
    public static final Color GLOW = new Color(255, 255, 203, 255);
    public static final Color FLAME = new Color(255, 180, 0, 255);

    public static List<Brick> build(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        Brick base = new Brick();
        base.setColor(BASE);
        base.setSize(2, 2, 8);
        base.setPosition(x, z, y - 8);
        base.setCollision(false);
        bricks.add(base);

        Brick glow = new Brick();
        glow.setColor(GLOW);
        glow.setSize(2, 2, 1);
        glow.setPosition(x, z, y + 1);
        glow.setCollision(false);
        bricks.add(glow);

        Brick flame = new Brick();
        flame.setColor(FLAME);
        flame.setSize(2, 2, 1);
        flame.setPosition(x, z, y + 3);
        flame.setMaterialIndex(1);
        flame.setCollision(false);
        bricks.add(flame);

        return bricks;
    }

}
