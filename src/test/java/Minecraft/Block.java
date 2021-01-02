package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;
import net.querz.mca.MCAFile;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block {

    private static final int SCALE = 16;
    private static final int MB = 2;

    private static final Color DIRT_COLOR = new Color(121, 85, 58, 255);
    private static final Color COBBLE_COLOR = new Color(127, 127, 127, 255);
    private static final Color OAK_COLOR = new Color(162, 131, 79, 255);
    private static final Color SPRUCE_COLOR = new Color(115, 85, 49, 255);
    private static final Color BIRCH_COLOR = new Color(192, 175, 121, 255);
    private static final Color JUNGLE_COLOR = new Color(160, 115, 81, 255);
    private static final Color ACACIA_COLOR = new Color(168, 90, 50, 255);
    private static final Color DARK_OAK_COLOR = new Color(67, 43, 20, 255);

    private static final int GRASS_BASE = 147;
    private static final int FOLIAGE_BASE = 130;

    static final Set<String> unsupported = new HashSet<>();

    private final String blockType;
    private final CompoundTag properties;

    Block(String blockType, CompoundTag properties) {
        this.blockType = blockType;
        this.properties = properties;
    }

    public Brick cube(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, SCALE);
        brick.setPosition(x, z, y);
        brick.setColor(color);
        return brick;
    }

    public Brick bot7o8(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, (SCALE * 7) / 8);
        brick.setPosition(x, z, y - MB);
        brick.setColor(color);
        return brick;
    }

    public Brick top1o8(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, MB);
        brick.setPosition(x, z, y + MB * 7);
        brick.setColor(color);
        return brick;
    }

    public Brick bot1o8(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, MB);
        brick.setPosition(x, z, y - MB * 7);
        brick.setColor(color);
        return brick;
    }

    public Brick pathBot(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, (SCALE * 7) / 8 - 1);
        brick.setPosition(x, z, y - 3);
        brick.setColor(color);
        return brick;
    }

    public Brick pathTop(int x, int z, int y, Color color) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, MB);
        brick.setPosition(x, z, y + MB * 6);
        brick.setColor(color);
        return brick;
    }

    public List<Brick> fence(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();
        Brick post = new Brick();
        post.setSize(4, 4, SCALE);
        post.setPosition(x, z, y);
        post.setColor(color);
        bricks.add(post);

        boolean north = ((StringTag) properties.get("north")).getValue().equals("true");
        boolean south = ((StringTag) properties.get("south")).getValue().equals("true");
        boolean east = ((StringTag) properties.get("east")).getValue().equals("true");
        boolean west = ((StringTag) properties.get("west")).getValue().equals("true");

        if (north) {
            Brick bot = new Brick();
            bot.setSize(2, 6, 3);
            bot.setPosition(x, z - 5*MB, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(2, 6, 3);
            top.setPosition(x, z - 5*MB, y + 6*MB - 1);
            top.setColor(color);
            bricks.add(top);
        }
        if (south) {
            Brick bot = new Brick();
            bot.setSize(2, 6, 3);
            bot.setPosition(x, z + 5*MB, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(2, 6, 3);
            top.setPosition(x, z + 5*MB, y + 6*MB - 1);
            top.setColor(color);
            bricks.add(top);
        }
        if (east) {
            Brick bot = new Brick();
            bot.setSize(6, 2, 3);
            bot.setPosition(x + 5*MB, z, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(6, 2, 3);
            top.setPosition(x + 5*MB, z, y + 6*MB - 1);
            top.setColor(color);
            bricks.add(top);
        }
        if (west) {
            Brick bot = new Brick();
            bot.setSize(6, 2, 3);
            bot.setPosition(x - 5*MB, z, y - 1);
            bot.setColor(color);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(6, 2, 3);
            top.setPosition(x - 5*MB, z, y + 6*MB - 1);
            top.setColor(color);
            bricks.add(top);
        }

        return bricks;
    }

    public List<Brick> logSides(int x, int z, int y, Color color) {
        String axis = ((StringTag) properties.get("axis")).getValue();
        Brick brickT = new Brick();
        Brick brickL = new Brick();
        Brick brickR = new Brick();
        Brick brickD = new Brick();
        switch (axis) {
            case "y" -> {
                brickT.setSize(SCALE, 1, SCALE);
                brickD.setSize(SCALE, 1, SCALE);
                brickR.setSize(1, SCALE - 2, SCALE);
                brickL.setSize(1, SCALE - 2, SCALE);
                brickT.setPosition(x, z + 15, y);
                brickD.setPosition(x, z - 15, y);
                brickR.setPosition(x + 15, z, y);
                brickL.setPosition(x - 15, z, y);
            }
            case "z" -> {
                brickT.setSize(SCALE, SCALE, 1);
                brickD.setSize(SCALE, SCALE, 1);
                brickR.setSize(1, SCALE, SCALE - 2);
                brickL.setSize(1, SCALE, SCALE - 2);
                brickT.setPosition(x, z, y + 15);
                brickD.setPosition(x, z, y - 15);
                brickR.setPosition(x + 15, z, y);
                brickL.setPosition(x - 15, z, y);
            }
            case "x" -> {
                brickT.setSize(SCALE, SCALE, 1);
                brickD.setSize(SCALE, SCALE, 1);
                brickR.setSize(SCALE, 1, SCALE - 2);
                brickL.setSize(SCALE, 1, SCALE - 2);
                brickT.setPosition(x, z, y + 15);
                brickD.setPosition(x, z, y - 15);
                brickR.setPosition(x, z + 15, y);
                brickL.setPosition(x, z - 15, y);
            }
        }

        brickT.setColor(color);
        brickD.setColor(color);
        brickR.setColor(color);
        brickL.setColor(color);
        return List.of(brickT, brickL, brickR, brickD);
    }

    public Brick logInside(int x, int z, int y, Color color) {
        String axis = ((StringTag) properties.get("axis")).getValue();
        Brick brick = new Brick();
        switch (axis) {
            case "y" -> brick.setSize((SCALE * 7) / 8, (SCALE * 7) / 8, SCALE);
            case "z" -> brick.setSize((SCALE * 7) / 8, SCALE, (SCALE * 7) / 8);
            case "x" -> brick.setSize(SCALE, (SCALE * 7) / 8, (SCALE * 7) / 8);
        }
        brick.setPosition(x, z, y);
        brick.setColor(color);
        return brick;
    }

    public Color grassColor(int x, int z, int y, MCAFile mcaFile) {
        /**
        float rsum = 0;
        float gsum = 0;
        float bsum = 0;
        int i=0;

        for (int rx = x-1; rx < x + 1; ++rx) {
            for (int rz = z - 1; rz < z + 1; ++rz) {
                if (rx < 0 || rx >= 32*16 || rz < 0 || rz >= 32*16)
                    continue;

                int biome = mcaFile.getBiomeAt(rx, y, rz);
                if (biome == -1)
                    continue;
                int color = Minecraft.Biomes.computeBiomeColor(biome, Math.max(0, y - 64), true);
                rsum += Math.pow((color >> 16) & 0xFF, 2);
                gsum += Math.pow((color >> 8) & 0xFF, 2);
                bsum += Math.pow(color & 0xFF, 2);
                i++;
            }
        }

        int r = (int) Math.pow(rsum / i, 0.5);
        int g = (int) Math.pow(gsum / i, 0.5);
        int b = (int) Math.pow(bsum / i, 0.5);

        Color grassColor = new Color(r, g, b, 255);
        grassColor.multiply(GRASS_BASE);
         **/
        int biome = mcaFile.getBiomeAt(x, y, z);
        int color = Biomes.computeBiomeColor(biome, Math.max(0, y - 64), true);
        Color grassColor = new Color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, 255);
        grassColor.multiply(GRASS_BASE);
        return grassColor;
    }

    public Brick liquid(int rx, int rz, int ry, Color color, Block[][][] blocks) {
        int x = rx * SCALE * 2;
        int z = rz * SCALE * 2;
        int y = ry * SCALE * 2 + SCALE;

        boolean top = true;
        if (ry < 256 && blocks[rx][ry+1][rz].blockType.equals(this.blockType)) {
            top = false;
        }

        if (top) {
            int level = Integer.parseInt(((StringTag) properties.get("level")).getValue());
            Brick brick = new Brick();
            brick.setSize(SCALE, SCALE, SCALE - (level+1)*2);
            brick.setPosition(x, z, y - (level+1)*2);
            brick.setColor(color);
            return brick;
        } else {
            return cube(x, z, y, color);
        }
    }

    public Color foliageColor(int x, int z, int y, MCAFile mcaFile) {
        int rsum = 0;
        int gsum = 0;
        int bsum = 0;
        int i=0;

        for (int rx = x-8; rx < x + 8; ++rx) {
            for (int rz = z - 8; rz < z + 8; ++rz) {
                if (rx < 0 || rx >= 32*16 || rz < 0 || rz >= 32*16)
                    continue;

                int biome = mcaFile.getBiomeAt(rx, y, rz);
                if (biome == -1)
                    continue;
                int color = Biomes.computeBiomeColor(biome, Math.max(0, y - 64), false);
                rsum += (color >> 16) & 0xFF;
                gsum += (color >> 8) & 0xFF;
                bsum += color & 0xFF;
                i++;
            }
        }

        int r = rsum / i;
        int g = gsum / i;
        int b = bsum / i;

        Color foliageColor = new Color(r, g, b, 255);
        foliageColor.multiply(FOLIAGE_BASE);
        return foliageColor;
    }

    public List<Brick> pane(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();
        Color border = new Color(255, 255, 255, 255);
        Brick midBot = new Brick();
        Brick midTop = new Brick();
        midBot.setSize(1, 1, 1);
        midTop.setSize(1, 1, 1);
        midBot.setPosition(x, z, y - 7*MB - 1);
        midTop.setPosition(x, z, y + 7*MB + 1);
        midBot.setColor(border);
        midTop.setColor(border);
        //bricks.add(midBot);
        //bricks.add(midTop);

        boolean north = ((StringTag) properties.get("north")).getValue().equals("true");
        boolean south = ((StringTag) properties.get("south")).getValue().equals("true");
        boolean east = ((StringTag) properties.get("east")).getValue().equals("true");
        boolean west = ((StringTag) properties.get("west")).getValue().equals("true");

        if (north && south) {
            Brick edgeN = new Brick();
            edgeN.setSize(1, 1, SCALE);
            edgeN.setPosition(x, z + 7*MB + 1, y);
            edgeN.setColor(border);
            bricks.add(edgeN);
            Brick edgeS = new Brick();
            edgeS.setSize(1, 1, SCALE);
            edgeS.setPosition(x, z - 7*MB - 1, y);
            edgeS.setColor(border);
            bricks.add(edgeS);
            Brick bot = new Brick();
            bot.setSize(1, SCALE - 2, 1);
            bot.setPosition(x, z, y - 7*MB - 1);
            bot.setColor(border);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(1, SCALE - 2, 1);
            top.setPosition(x, z, y + 7*MB + 1);
            top.setColor(border);
            bricks.add(top);
        }
        if (east && west) {
            Brick edgeE = new Brick();
            edgeE.setSize(1, 1, SCALE);
            edgeE.setPosition(x + 7*MB + 1, z, y);
            edgeE.setColor(border);
            bricks.add(edgeE);
            Brick edgeW = new Brick();
            edgeW.setSize(1, 1, SCALE);
            edgeW.setPosition(x - 7*MB - 1, z, y);
            edgeW.setColor(border);
            bricks.add(edgeW);
            Brick bot = new Brick();
            bot.setSize(SCALE - 2, 1, 1);
            bot.setPosition(x, z, y - 7*MB - 1);
            bot.setColor(border);
            bricks.add(bot);
            Brick top = new Brick();
            top.setSize(SCALE - 2, 1, 1);
            top.setPosition(x, z, y + 7*MB + 1);
            top.setColor(border);
            bricks.add(top);
        }

        return bricks;
    }

    public List<Brick> stairs(int x, int z, int y, Color color) {
        List<Brick> bricks = new ArrayList<>();
        String half = ((StringTag) properties.get("half")).getValue();
        String facing = ((StringTag) properties.get("facing")).getValue();
        bricks.add(slab(x, z, y, color, half));

        int offset = MB*4 * (half.equals("bottom") ? 1 : -1);

        switch (facing) {
            case "north" -> {
                Brick brick = new Brick();
                brick.setSize(SCALE, SCALE/2, SCALE/2);
                brick.setPosition(x, z - MB*4, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case "south" -> {
                Brick brick = new Brick();
                brick.setSize(SCALE, SCALE/2, SCALE/2);
                brick.setPosition(x, z + MB*4, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case "east" -> {
                Brick brick = new Brick();
                brick.setSize(SCALE/2, SCALE, SCALE/2);
                brick.setPosition(x + MB*4, z, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
            case "west" -> {
                Brick brick = new Brick();
                brick.setSize(SCALE/2, SCALE, SCALE/2);
                brick.setPosition(x - MB*4, z, y + offset);
                brick.setColor(color);
                bricks.add(brick);
            }
        }

        return bricks;
    }

    public Brick slab(int x, int z, int y, Color color, String half) {
        Brick brick = new Brick();
        brick.setSize(SCALE, SCALE, SCALE/2);
        brick.setColor(color);
        switch (half) {
            case "bottom" -> brick.setPosition(x, z, y - 4*MB);
            case "top" -> brick.setPosition(x, z, y + 4*MB);
        }
        return brick;
    }

    public int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }

    public List<Brick> getBricks(int rx, int rz, int ry, MCAFile mcaFile, Block[][][] blocks) {
        List<Brick> bricks = new ArrayList<>();

        int x = rx * SCALE * 2;
        int z = rz * SCALE * 2;
        int y = ry * SCALE * 2 + SCALE;
        switch (blockType) {
            case "minecraft:air", "minecraft:cave_air", "minecraft:void_air":
                break;
            case "minecraft:dirt":
                bricks.add(cube(x, z, y, DIRT_COLOR));
                break;
            case "minecraft:coarse_dirt":
                bricks.add(cube(x, z, y, new Color(119, 85, 59, 255)));
                break;
            case "minecraft:grass_block":
                bricks.add(bot7o8(x, z, y, new Color(121, 85, 58, 255)));
                bricks.add(top1o8(x, z, y, grassColor(rx, rz, ry, mcaFile)));
                break;
            case "minecraft:grass_path":
                bricks.add(pathBot(x, z, y, new Color(121, 85, 58, 255)));
                bricks.add(pathTop(x, z, y, new Color(148, 122, 65, 255)));
                break;
            case "minecraft:podzol":
                bricks.add(bot7o8(x, z, y, DIRT_COLOR));
                bricks.add(top1o8(x, z, y, new Color(89, 63, 29, 255)));
                break;
            case "minecraft:mycelium":
                bricks.add(bot7o8(x, z, y, DIRT_COLOR));
                bricks.add(top1o8(x, z, y, new Color(111, 98, 101, 255)));
                break;
            case "minecraft:gravel":
                bricks.add(cube(x, z, y, new Color(131, 127, 126, 255)));
                break;
            case "minecraft:sand":
                bricks.add(cube(x, z, y, new Color(219, 207, 163, 255)));
                break;
            case "minecraft:red_sand":
                bricks.add(cube(x, z, y, new Color(190, 102, 33, 255)));
                break;
            case "minecraft:clay":
                bricks.add(cube(x, z, y, new Color(160, 166, 179, 255)));
                break;
            case "minecraft:stone", "minecraft:infested_stone":
                bricks.add(cube(x, z, y, new Color(125, 125, 125, 255)));
                break;
            case "minecraft:cobblestone":
                bricks.add(cube(x, z, y, COBBLE_COLOR));
                break;
            case "minecraft:granite":
                bricks.add(cube(x, z, y, new Color(149, 103, 86, 255)));
                break;
            case "minecraft:polished_granite":
                bricks.add(cube(x, z, y, new Color(154, 107, 89, 255)));
                break;
            case "minecraft:bedrock":
                bricks.add(cube(x, z, y, new Color(85, 85, 85, 255)));
                break;
            case "minecraft:diorite":
                bricks.add(cube(x, z, y, new Color(189, 188, 189, 255)));
                break;
            case "minecraft:polished_diorite":
                bricks.add(cube(x, z, y, new Color(193, 193, 195, 255)));
                break;
            case "minecraft:obsidian":
                bricks.add(cube(x, z, y, new Color(15, 11, 25, 255)));
                break;
            case "minecraft:andesite":
                bricks.add(cube(x, z, y, new Color(136, 136, 137, 255)));
                break;
            case "minecraft:polished_andesite":
                bricks.add(cube(x, z, y, new Color(132, 135, 134, 255)));
                break;
            case "minecraft:mossy_cobblestone":
                bricks.add(cube(x, z, y, new Color(110, 119, 95, 255)));
                break;
            case "minecraft:bricks":
                bricks.add(cube(x, z, y, new Color(151, 98, 83, 255)));
                break;
            case "minecraft:oak_leaves", "minecraft:jungle_leaves", "minecraft:acacia_leaves", "minecraft:dark_oak_leaves":
                bricks.add(cube(x, z, y, foliageColor(rx, rz, ry, mcaFile)));
                break;
            case "minecraft:spruce_leaves":
                Color spruce_color = new Color(97, 153, 97, 255);
                spruce_color.multiply(FOLIAGE_BASE);
                bricks.add(cube(x, z, y, spruce_color));
                break;
            case "minecraft:birch_leaves":
                Color birch_color = new Color(128, 167, 85, 255);
                birch_color.multiply(FOLIAGE_BASE);
                bricks.add(cube(x, z, y, birch_color));
                break;
            case "minecraft:oak_log":
                bricks.addAll(logSides(x, z, y, new Color(109, 85, 50, 255)));
                bricks.add(logInside(x, z, y, new Color(169, 136, 82, 255)));
                break;
            case "minecraft:spruce_log":
                bricks.addAll(logSides(x, z, y, new Color(58, 37, 16, 255)));
                bricks.add(logInside(x, z, y, new Color(117, 87, 51, 255)));
                break;
            case "minecraft:birch_log":
                bricks.addAll(logSides(x, z, y, new Color(216, 215, 210, 255)));
                bricks.add(logInside(x, z, y, new Color(196, 178, 123, 255)));
                break;
            case "minecraft:jungle_log":
                bricks.addAll(logSides(x, z, y, new Color(85, 68, 25, 255)));
                bricks.add(logInside(x, z, y, new Color(168, 121, 84, 255)));
                break;
            case "minecraft:acacia_log":
                bricks.addAll(logSides(x, z, y, new Color(103, 96, 86, 255)));
                bricks.add(logInside(x, z, y, new Color(170, 91, 49, 255)));
                break;
            case "minecraft:dark_oak_log":
                bricks.addAll(logSides(x, z, y, new Color(60, 46, 26, 255)));
                bricks.add(logInside(x, z, y, new Color(70, 44, 21, 255)));
                break;
            case "minecraft:stripped_oak_log":
                bricks.addAll(logSides(x, z, y, new Color(177, 144, 86, 255)));
                bricks.add(logInside(x, z, y, new Color(169, 136, 82, 255)));
                break;
            case "minecraft:stripped_spruce_log":
                bricks.addAll(logSides(x, z, y, new Color(116, 90, 52, 255)));
                bricks.add(logInside(x, z, y, new Color(117, 87, 51, 255)));
                break;
            case "minecraft:stripped_birch_log":
                bricks.addAll(logSides(x, z, y, new Color(197, 176, 118, 255)));
                bricks.add(logInside(x, z, y, new Color(196, 179, 124, 255)));
                break;
            case "minecraft:stripped_jungle_log":
                bricks.addAll(logSides(x, z, y, new Color(200, 174, 142, 255)));
                bricks.add(logInside(x, z, y, new Color(168, 121, 84, 255)));
                break;
            case "minecraft:stripped_acacia_log":
                bricks.addAll(logSides(x, z, y, new Color(174, 92, 59, 255)));
                bricks.add(logInside(x, z, y, new Color(170, 91, 49, 255)));
                break;
            case "minecraft:stripped_dark_oak_log":
                bricks.addAll(logSides(x, z, y, new Color(96, 76, 49, 255)));
                bricks.add(logInside(x, z, y, new Color(69, 44, 21, 255)));
                break;
            case "minecraft:snow":
                bricks.add(bot1o8(x, z, y, new Color(249, 255, 255, 255)));
                break;
            case "minecraft:farmland":
                int moisture = Integer.parseInt(((StringTag) properties.get("moisture")).getValue());
                if (moisture < 7) {
                    bricks.add(cube(x, z, y, new Color(143, 103, 71, 255)));
                } else {
                    bricks.add(cube(x, z, y, new Color(82, 44, 15, 255)));
                }
                break;
            case "minecraft:crafting_table":
                bricks.add(cube(x, z, y, new Color(129, 106, 70, 255)));
                break;
            case "minecraft:coal_ore":
                bricks.add(cube(x, z, y, new Color(116, 116, 116, 255)));
                break;
            case "minecraft:iron_ore":
                bricks.add(cube(x, z, y, new Color(136, 131, 127, 255)));
                break;
            case "minecraft:gold_ore":
                bricks.add(cube(x, z, y, new Color(144, 140, 125, 255)));
                break;
            case "minecraft:diamond_ore":
                bricks.add(cube(x, z, y, new Color(125, 143, 141, 255)));
                break;
            case "minecraft:water":
                Brick waterbrick = liquid(rx, rz, ry, new Color(63, 118, 228, 255), blocks);
                waterbrick.setCollision(false);
                bricks.add(waterbrick);
                break;
            case "minecraft:lava":
                Brick lavabrick = liquid(rx, rz, ry, new Color(207, 92, 20, 255), blocks);
                lavabrick.setMaterialIndex(1);
                lavabrick.setCollision(false);
                bricks.add(lavabrick);
                break;
            case "minecraft:oak_planks":
                bricks.add(cube(x, z, y, OAK_COLOR));
                break;
            case "minecraft:spruce_planks":
                bricks.add(cube(x, z, y, SPRUCE_COLOR));
                break;
            case "minecraft:birch_planks":
                bricks.add(cube(x, z, y, BIRCH_COLOR));
                break;
            case "minecraft:jungle_planks":
                bricks.add(cube(x, z, y, JUNGLE_COLOR));
                break;
            case "minecraft:acacia_planks":
                bricks.add(cube(x, z, y, ACACIA_COLOR));
                break;
            case "minecraft:dark_oak_planks":
                bricks.add(cube(x, z, y, DARK_OAK_COLOR));
                break;
            case "minecraft:oak_fence":
                bricks.addAll(fence(x, z, y, OAK_COLOR));
                break;
            case "minecraft:spruce_fence":
                bricks.addAll(fence(x, z, y, SPRUCE_COLOR));
                break;
            case "minecraft:birch_fence":
                bricks.addAll(fence(x, z, y, BIRCH_COLOR));
                break;
            case "minecraft:jungle_fence":
                bricks.addAll(fence(x, z, y, JUNGLE_COLOR));
                break;
            case "minecraft:acacia_fence":
                bricks.addAll(fence(x, z, y, ACACIA_COLOR));
                break;
            case "minecraft:dark_oak_fence":
                bricks.addAll(fence(x, z, y, DARK_OAK_COLOR));
                break;
            case "minecraft:oak_stairs":
                bricks.addAll(stairs(x, z, y, OAK_COLOR));
                break;
            case "minecraft:spruce_stairs":
                bricks.addAll(stairs(x, z, y, SPRUCE_COLOR));
                break;
            case "minecraft:birch_stairs":
                bricks.addAll(stairs(x, z, y, BIRCH_COLOR));
                break;
            case "minecraft:jungle_stairs":
                bricks.addAll(stairs(x, z, y, JUNGLE_COLOR));
                break;
            case "minecraft:acacia_stairs":
                bricks.addAll(stairs(x, z, y, ACACIA_COLOR));
                break;
            case "minecraft:dark_oak_stairs":
                bricks.addAll(stairs(x, z, y, DARK_OAK_COLOR));
                break;
            case "minecraft:cobblestone_stairs":
                bricks.addAll(stairs(x, z, y, COBBLE_COLOR));
                break;
            case "minecraft:chest":
                bricks.addAll(ChestBuilder.build(x, z, y, properties));
                break;
            case "minecraft:grindstone":
                String face = ((StringTag) properties.get("face")).getValue();
                String facing = ((StringTag) properties.get("face")).getValue();
                Color grindColor = new Color(140, 140, 140, 255);
                Color pivotColor = new Color(73, 46, 21, 255);
                if (face.equals("floor")) {
                    Brick stone = new Brick();
                    Brick pivotBearingL = new Brick();
                    Brick pivotBearingR = new Brick();
                    stone.setColor(grindColor);
                    pivotBearingL.setColor(pivotColor);
                    pivotBearingR.setColor(pivotColor);
                    switch (facing) {
                        case "north", "south" -> {
                            stone.setSize(8, 12, 12);
                            stone.setPosition(x, z, y + 4);
                            pivotBearingL.setSize(2, 6, 6);
                            pivotBearingL.setPosition(x - 5*MB, z, y);
                            pivotBearingR.setSize(2, 6, 6);
                            pivotBearingR.setPosition(x + 5*MB, z, y);
                        }
                        case "east", "west" -> {
                            stone.setSize(12, 8, 12);
                            stone.setPosition(x, z, y + 4);
                            pivotBearingL.setSize(6, 2, 6);
                            pivotBearingL.setPosition(x, z - 5*MB, y);
                            pivotBearingR.setSize(6, 2, 6);
                            pivotBearingR.setPosition(x, z + 5*MB, y);
                        }
                    }
                    //bricks.add(stone);
                    bricks.add(pivotBearingL);
                    bricks.add(pivotBearingR);
                }
                break;
            case "minecraft:glass_pane":
                bricks.addAll(pane(x, z, y, new Color(255, 255, 255, 0)));
                break;
            default:
                unsupported.add(blockType);
        }

        return bricks;
    }

    public boolean isAir() {
        return switch(blockType) {
            case "minecraft:air", "minecraft:cave_air", "minecraft:void_air" -> true;
            default -> false;
        };
    }

    public boolean isSeethrough() {
        return switch(blockType) {
            case "minecraft:air",
                    "minecraft:cave_air",
                    "minecraft:void_air",
                    "minecraft:glass",
                    "minecraft:grass",
                    "minecraft:grass_path",
                    "minecraft:tall_grass",
                    "minecraft:fern",
                    "minecraft:large_fern",
                    "minecraft:grindstone",
                    "minecraft:dead_bush",
                    "minecraft:seagrass",
                    "minecraft:tall_seagrass",
                    "minecraft:sea_pickle",
                    "minecraft:cobweb",
                    "minecraft:dandelion",
                    "minecraft:poppy",
                    "minecraft:chest",
                    "minecraft:blue_orchid",
                    "minecraft:allium",
                    "minecraft:azure_bluet",
                    "minecraft:red_tulip",
                    "minecraft:orange_tulip",
                    "minecraft:wheat",
                    "minecraft:potatoes",
                    "minecraft:glass_pane",
                    "minecraft:spruce_trapdoor",
                    "minecraft:oak_trapdoor",
                    "minecraft:birch_trapdoor",
                    "minecraft:jungle_trapdoor",
                    "minecraft:acacia_trapdoor",
                    "minecraft:dark_oak_trapdoor",
                    "minecraft:spruce_fence",
                    "minecraft:oak_fence",
                    "minecraft:birch_fence",
                    "minecraft:jungle_fence",
                    "minecraft:acacia_fence",
                    "minecraft:dark_oak_fence",
                    "minecraft:spruce_stairs",
                    "minecraft:oak_stairs",
                    "minecraft:birch_stairs",
                    "minecraft:jungle_stairs",
                    "minecraft:acacia_stairs",
                    "minecraft:dark_oak_stairs",
                    "minecraft:sweet_berry_bush",
                    "minecraft:peony",
                    "minecraft:white_tulip",
                    "minecraft:pink_tulip",
                    "minecraft:lilac",
                    "minecraft:lily_of_the_valley",
                    "minecraft:oxeye_daisy",
                    "minecraft:brown_mushroom",
                    "minecraft:red_mushroom",
                    "minecraft:oak_slab",
                    "minecraft:spruce_slab",
                    "minecraft:birch_slab",
                    "minecraft:jungle_slab",
                    "minecraft:acacia_slab",
                    "minecraft:dark_oak_slab",
                    "minecraft:stone_slab",
                    "minecraft:sandstone_slab",
                    "minecraft:petrified_oak_slab",
                    "minecraft:cobblestone_slab",
                    "minecraft:brick_slab",
                    "minecraft:stone_brick_slab",
                    "minecraft:nether_brick_slab",
                    "minecraft:quartz_slab",
                    "minecraft:red_sandstone_slab",
                    "minecraft:purpur_slab",
                    "minecraft:prismarine_slab",
                    "minecraft:prismarine_brick_slab",
                    "minecraft:dark_prismarine_slab",
                    "minecraft:torch",
                    "minecraft:oak_sapling",
                    "minecraft:spruce_sapling",
                    "minecraft:birch_sapling",
                    "minecraft:jungle_sapling",
                    "minecraft:acacia_sapling",
                    "minecraft:dark_oak_sapling",
                    "minecraft:powered_rail",
                    "minecraft:detector_rail",
                    "minecraft:snow",
                    "minecraft:water",
                    "minecraft:lava" -> true;
            default -> false;
        };
    }

}
