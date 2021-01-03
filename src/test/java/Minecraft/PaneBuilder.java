package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class PaneBuilder {

    private static final Color BORDER = new Color(206, 232, 232, 255);

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();

        boolean north = block.getProp("north").equals("true");
        boolean south = block.getProp("south").equals("true");
        boolean east = block.getProp("east").equals("true");
        boolean west = block.getProp("west").equals("true");

        int off = 15;

        if (north && south) {
            for (int i=0; i < 2; ++i) {
                Brick side = new Brick();
                Brick lip = new Brick();
                side.setColor(BORDER);
                lip.setColor(BORDER);
                side.setSize(1, 1, 16);
                lip.setSize(1, 14, 1);
                bricks.add(side);
                bricks.add(lip);
            }

            bricks.get(0).setPosition(x, z+off, y);
            bricks.get(1).setPosition(x, z, y+off);
            bricks.get(2).setPosition(x, z-off, y);
            bricks.get(3).setPosition(x, z, y-off);

            for (int i=0; i < 5; ++i) {
                Brick brick = new Brick();
                brick.setColor(BORDER);
                brick.setSize(1, 1, 1);
                bricks.add(brick);
            }

            bricks.get(4).setPosition(x, z -11, y + 7);
            bricks.get(5).setPosition(x, z - 9, y + 9);
            bricks.get(6).setPosition(x, z - 7, y + 11);
            bricks.get(7).setPosition(x, z + 11, y - 9);
            bricks.get(8).setPosition(x, z + 9, y - 11);
        } else if (east && west) {
            for (int i=0; i < 2; ++i) {
                Brick side = new Brick();
                Brick lip = new Brick();
                side.setColor(BORDER);
                lip.setColor(BORDER);
                side.setSize(1, 1, 16);
                lip.setSize(14, 1, 1);
                bricks.add(side);
                bricks.add(lip);
            }

            bricks.get(0).setPosition(x+off, z, y);
            bricks.get(1).setPosition(x, z, y+off);
            bricks.get(2).setPosition(x-off, z, y);
            bricks.get(3).setPosition(x, z, y-off);

            for (int i=0; i < 5; ++i) {
                Brick brick = new Brick();
                brick.setColor(BORDER);
                brick.setSize(1, 1, 1);
                bricks.add(brick);
            }

            bricks.get(4).setPosition(x - 11, z, y + 7);
            bricks.get(5).setPosition(x - 9, z, y + 9);
            bricks.get(6).setPosition(x - 7, z, y + 11);
            bricks.get(7).setPosition(x + 11, z, y - 9);
            bricks.get(8).setPosition(x + 9, z, y - 11);
        }

        return bricks;
    }

}
