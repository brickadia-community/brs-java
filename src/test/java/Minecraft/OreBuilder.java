package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;

import java.util.ArrayList;
import java.util.List;

public class OreBuilder {

    private static final Color STONE = new Color(125, 125, 125, 255);
    private static final Color ORE_COAL = new Color(59, 59, 59, 255);
    private static final Color ORE_IRON = new Color(205, 168, 143, 255);
    private static final Color ORE_GOLD = new Color(254, 247, 147, 255);
    private static final Color ORE_REDSTONE = new Color(182, 3, 3, 255);
    private static final Color ORE_DIAMOND = new Color(143, 250, 234, 255);
    private static final Color ORE_LAPIS = new Color(20, 77, 181, 255);
    private static final Color ORE_EMERALD = new Color(66, 205, 114, 255);

    private static List<Brick> corners(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick();
            brick.setColor(STONE);
            brick.setSize(6, 6, 16);
            bricks.add(brick);
        }

        int off = 10;

        bricks.get(0).setPosition(x + off, z + off, y);
        bricks.get(1).setPosition(x + off, z - off, y);
        bricks.get(2).setPosition(x - off, z + off, y);
        bricks.get(3).setPosition(x - off, z - off, y);

        return bricks;
    }

    private static List<Brick> edges(int x, int z, int y) {
        List<Brick> bricks = new ArrayList<>();

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick();
            brick.setColor(STONE);
            brick.setSize(4, 6, 4);
            bricks.add(brick);
        }

        for (int i=0; i < 4; ++i) {
            Brick brick = new Brick();
            brick.setColor(STONE);
            brick.setSize(6, 4, 4);
            bricks.add(brick);
        }

        int off = 10;
        int yoff = 12;

        bricks.get(0).setPosition(x, z + off, y - yoff);
        bricks.get(1).setPosition(x, z - off, y - yoff);
        bricks.get(2).setPosition(x, z + off, y + yoff);
        bricks.get(3).setPosition(x, z - off, y + yoff);
        bricks.get(4).setPosition(x + off, z, y - yoff);
        bricks.get(5).setPosition(x - off, z, y - yoff);
        bricks.get(6).setPosition(x + off, z, y + yoff);
        bricks.get(7).setPosition(x - off, z, y + yoff);

        return bricks;
    }

    private static List<Brick> ore(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        Color oreColor = switch(block.getBlockType()) {
            case "minecraft:coal_ore" -> ORE_COAL;
            case "minecraft:iron_ore" -> ORE_IRON;
            case "minecraft:gold_ore" -> ORE_GOLD;
            case "minecraft:redstone_ore" -> ORE_REDSTONE;
            case "minecraft:diamond_ore" -> ORE_DIAMOND;
            case "minecraft:lapis_ore" -> ORE_LAPIS;
            case "minecraft:emerald_ore" -> ORE_EMERALD;
            default -> throw new IllegalStateException("Unexpected value: " + block.getBlockType());
        };

        for (int i=0; i < 5; ++i) {
            Brick brick = new Brick();
            brick.setColor(oreColor);
            brick.setSize(4, 4, 8);
            bricks.add(brick);
        }

        int off = 12;

        bricks.get(0).setPosition(x, z + off, y);
        bricks.get(1).setPosition(x, z - off, y);
        bricks.get(2).setPosition(x + off, z, y);
        bricks.get(3).setPosition(x - off, z, y);
        bricks.get(4).setSize(4, 4, 16);
        bricks.get(4).setPosition(x, z, y);

        return bricks;
    }

    public static List<Brick> build(int x, int z, int y, Block block) {
        List<Brick> bricks = new ArrayList<>();
        bricks.addAll(corners(x, z, y));
        bricks.addAll(edges(x, z, y));
        bricks.addAll(ore(x, z, y, block));
        return bricks;
    }

}
