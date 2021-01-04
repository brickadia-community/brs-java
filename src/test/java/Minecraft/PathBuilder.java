package Minecraft;

import com.kmschr.brs.Brick;

import java.util.List;

public class PathBuilder {

    public static Brick pathBot(int x, int z, int y) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 13);
        brick.setPosition(x, z, y - 3);
        brick.setColor(Colors.DIRT);
        return brick;
    }

    public static Brick pathTop(int x, int z, int y) {
        Brick brick = new Brick();
        brick.setSize(16, 16, 2);
        brick.setPosition(x, z, y + 12);
        brick.setColor(Colors.PATH);
        return brick;
    }

    public static List<Brick> build(int x, int z, int y) {
        return List.of(pathBot(x, z, y), pathTop(x, z, y));
    }

}
