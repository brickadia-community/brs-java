package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.List;

public class WallBannerBuilder {

    public static List<Brick> build(int x, int z, int y, Color color, Block block) {
        Facing facing = Facing.fromProps(block.getProps());
        Brick wood = new Brick();
        Brick cloth = new Brick();
        wood.setColor(Colors.OAK);
        cloth.setColor(color);

        switch (facing) {
            case NORTH, SOUTH -> {
                int off1 = 14 * (facing == Facing.NORTH ? 1 : -1);
                int off2 = 11 * (facing == Facing.NORTH ? 1 : -1);
                wood.setSize(13, 2, 2);
                wood.setPosition(x, z + off1, y + 10);
                cloth.setSize(13, 1, 27);
                cloth.setPosition(x, z + off2, y - 15);
            }
            case EAST, WEST -> {
                int off1 = 14 * (facing == Facing.WEST ? 1 : -1);
                int off2 = 11 * (facing == Facing.WEST ? 1 : -1);
                wood.setSize(2, 13, 2);
                wood.setPosition(x + off1, z, y + 10);
                cloth.setSize(1, 13, 27);
                cloth.setPosition(x + off2, z, y - 15);
            }
        }

        return List.of(wood, cloth);
    }

}
