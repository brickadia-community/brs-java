package Minecraft;

import com.kmschr.brs.Brick;

import java.util.List;

public class CactusBuilder {

    public static List<Brick> build(int x, int z, int y) {
        Brick brick = new Brick();
        brick.setSize(14, 14, 16);
        brick.setPosition(x, z, y);
        brick.setColor(Colors.CACTUS);
        return List.of(brick);
    }

}
