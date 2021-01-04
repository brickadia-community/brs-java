package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class CobblestoneBuilder {

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        Color color = switch (block.getBlockType()) {
            case "minecraft:cobblestone" -> Colors.COBBLE;
            case "minecraft:mossy_cobblestone" -> Colors.MOSS;
            default -> throw new IllegalStateException("Unexpected value: " + block.getBlockType());
        };

        for (int i=0; i < 8; ++i) {
            Brick brick = new Brick();
            brick.setSize(8, 8, 8);
            brick.setColor(color);
            bricks.add(brick);
        }

        int off = 8;

        bricks.get(0).setPosition(x + off, z - off, y + off);
        bricks.get(1).setPosition(x + off, z - off, y - off);
        bricks.get(2).setPosition(x + off, z + off, y + off);
        bricks.get(3).setPosition(x + off, z + off, y - off);
        bricks.get(4).setPosition(x - off, z - off, y + off);
        bricks.get(5).setPosition(x - off, z - off, y - off);
        bricks.get(6).setPosition(x - off, z + off, y + off);
        bricks.get(7).setPosition(x - off, z + off, y - off);

        return bricks;
    }

}
