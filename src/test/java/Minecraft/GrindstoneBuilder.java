package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class GrindstoneBuilder {

    private static final Color STONE_COLOR = new Color(140, 140, 140, 255);
    private static final Color WOOD_COLOR = new Color(73, 46, 21, 255);

    public static List<Brick> floor(int x, int z, int y, Facing facing) {
        List<Brick> bricks = new ArrayList<>();
        Brick stone = new Brick();
        Brick pivotBearingL = new Brick();
        Brick pivotBearingR = new Brick();
        Brick legL = new Brick();
        Brick legR = new Brick();
        stone.setColor(STONE_COLOR);
        pivotBearingL.setColor(WOOD_COLOR);
        pivotBearingR.setColor(WOOD_COLOR);
        legL.setColor(WOOD_COLOR);
        legR.setColor(WOOD_COLOR);
        switch (facing) {
            case NORTH, SOUTH -> {
                stone.setSize(8, 12, 12);
                stone.setPosition(x, z, y + 4);
                pivotBearingL.setSize(2, 6, 6);
                pivotBearingL.setPosition(x - 10, z, y + 4);
                pivotBearingR.setSize(2, 6, 6);
                pivotBearingR.setPosition(x + 10, z, y + 4);
                legL.setSize(2, 4, 6);
                legL.setPosition(x - 10, z, y - 8);
                legR.setSize(2, 4, 6);
                legR.setPosition(x + 10, z, y - 8);
            }
            case EAST, WEST -> {
                stone.setSize(12, 8, 12);
                stone.setPosition(x, z, y + 4);
                pivotBearingL.setSize(6, 2, 6);
                pivotBearingL.setPosition(x, z - 5*2, y + 4);
                pivotBearingR.setSize(6, 2, 6);
                pivotBearingR.setPosition(x, z + 5*2, y + 4);
                legL.setSize(4, 2, 6);
                legL.setPosition(x, z - 10, y - 8);
                legR.setSize(4, 2, 6);
                legR.setPosition(x, z + 10, y - 8);
            }
        }
        bricks.add(stone);
        bricks.add(pivotBearingL);
        bricks.add(pivotBearingR);
        bricks.add(legL);
        bricks.add(legR);
        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        String face = block.getProp("face");
        Facing facing = Facing.fromProps(block.getProps());
        switch (face) {
            case "floor" -> bricks.addAll(floor(x, z, y, facing));
        }
        return bricks;
    }

}
