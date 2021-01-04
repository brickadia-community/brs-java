package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;
import net.querz.mca.MCAFile;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

import java.util.*;

public class Block {

    private static final int SCALE = 16;
    private static final int MB = 2;

    private static final int GRASS_BASE = 147;
    private static final int FOLIAGE_BASE = 130;

    static final Set<String> unsupported = new HashSet<>();

    private final String blockType;
    private final CompoundTag properties;

    Block(String blockType, CompoundTag properties) {
        this.blockType = blockType;
        this.properties = properties;
    }

    public String getProp(String propName) {
        return ((StringTag) properties.get(propName)).getValue();
    }

    public CompoundTag getProps() {
        return properties;
    }

    public String getBlockType() {
        return blockType;
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

    public int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }

    public List<Brick> getBricks(int rx, int rz, int ry, int regX, int regY, MCAFile mcaFile, Block[][][] blocks) {
        int x = rx * SCALE * 2 + regX * 32 * 16;
        int z = rz * SCALE * 2 + regY * 32 * 16;
        int y = ry * SCALE * 2 + SCALE;
        return switch (blockType) {
            case "minecraft:air", "minecraft:cave_air", "minecraft:void_air" -> List.of();
            case "minecraft:dirt" -> CubeBuilder.build(x, z, y, Colors.DIRT);
            case "minecraft:coarse_dirt" -> CubeBuilder.build(x, z, y, Colors.COARSE_DIRT);
            case "minecraft:grass_block" -> GrassBuilder.build(x, z, y, grassColor(x, z, y, mcaFile));
            case "minecraft:grass_path" -> PathBuilder.build(x, z, y);
            case "minecraft:podzol" -> GrassBuilder.build(x, z, y, Colors.PODZOL);
            case "minecraft:mycelium" -> GrassBuilder.build(x, z, y, Colors.MYCELIUM);
            case "minecraft:gravel" -> CubeBuilder.build(x, z, y, Colors.GRAVEL);
            case "minecraft:sand" -> CubeBuilder.build(x, z, y, Colors.SAND);
            case "minecraft:red_sand" -> CubeBuilder.build(x, z, y, Colors.RED_SAND);
            case "minecraft:clay" -> CubeBuilder.build(x, z, y, Colors.CLAY);
            case "minecraft:stone", "minecraft:infested_stone" -> CubeBuilder.build(x, z, y, Colors.STONE);
            case "minecraft:granite" -> CubeBuilder.build(x, z, y, Colors.GRANITE);
            case "minecraft:polished_granite" -> CubeBuilder.build(x, z, y, Colors.POLISHED_GRANITE);
            case "minecraft:bedrock" -> CubeBuilder.build(x, z, y, Colors.BEDROCK);
            case "minecraft:diorite" -> CubeBuilder.build(x, z, y, Colors.DIORITE);
            case "minecraft:polished_diorite" -> CubeBuilder.build(x, z, y, Colors.POLISHED_DIORITE);
            case "minecraft:obsidian" -> CubeBuilder.build(x, z, y, Colors.OBSIDIAN);
            case "minecraft:andesite" -> CubeBuilder.build(x, z, y, Colors.ANDESITE);
            case "minecraft:polished_andesite" -> CubeBuilder.build(x, z, y, Colors.POLISHED_ANDESITE);
            case "minecraft:cobblestone", "minecraft:mossy_cobblestone" -> CobblestoneBuilder.build(x, z, y, this);
            case "minecraft:bricks" -> CubeBuilder.build(x, z, y, Colors.BRICK);
            case "minecraft:oak_leaves",
                 "minecraft:jungle_leaves",
                 "minecraft:acacia_leaves",
                 "minecraft:dark_oak_leaves" -> CubeBuilder.build(x, z, y, foliageColor(rx, rz, ry, mcaFile));
            case "minecraft:spruce_leaves" -> {
                Color spruce_color = Colors.SPRUCE_LEAVES;
                spruce_color.multiply(FOLIAGE_BASE);
                yield CubeBuilder.build(x, z, y, spruce_color);
            }
            case "minecraft:birch_leaves" -> {
                Color birch_color = Colors.BIRCH_LEAVES;
                birch_color.multiply(FOLIAGE_BASE);
                yield CubeBuilder.build(x, z, y, birch_color);
            }
            case "minecraft:oak_log" -> LogBuilder.build(x, z, y, Colors.OAK_LOG_INSIDE, Colors.OAK_LOG_OUTSIDE, this);
            case "minecraft:spruce_log" -> LogBuilder.build(x, z, y, Colors.SPRUCE_LOG_INSIDE, Colors.SPRUCE_LOG_OUTSIDE, this);
            case "minecraft:birch_log" -> LogBuilder.build(x, z, y, Colors.BIRCH_LOG_INSIDE, Colors.BIRCH_LOG_OUTSIDE, this);
            case "minecraft:jungle_log" -> LogBuilder.build(x, z, y, Colors.JUNGLE_LOG_INSIDE, Colors.JUNGLE_LOG_OUTSIDE, this);
            case "minecraft:acacia_log" -> LogBuilder.build(x, z, y, Colors.ACACIA_LOG_INSIDE, Colors.ACACIA_LOG_OUTSIDE, this);
            case "minecraft:dark_oak_log" -> LogBuilder.build(x, z, y, Colors.DARK_OAK_LOG_INSIDE, Colors.DARK_OAK_LOG_OUTSIDE, this);
            case "minecraft:stripped_oak_log" -> LogBuilder.build(x, z, y, Colors.OAK_LOG_INSIDE, Colors.STRIPPED_OAK_OUTISDE, this);
            case "minecraft:stripped_spruce_log" -> LogBuilder.build(x, z, y, Colors.SPRUCE_LOG_INSIDE, Colors.STRIPPED_SPRUCE_OUTSIDE, this);
            case "minecraft:stripped_birch_log" -> LogBuilder.build(x, z, y, Colors.BIRCH_LOG_INSIDE, Colors.STRIPPED_BIRCH_OUTSIDE, this);
            case "minecraft:stripped_jungle_log" -> LogBuilder.build(x, z, y, Colors.JUNGLE_LOG_INSIDE, Colors.STRIPPED_JUNGLE_OUTSIDE, this);
            case "minecraft:stripped_acacia_log" -> LogBuilder.build(x, z, y, Colors.ACACIA_LOG_INSIDE, Colors.STRIPPED_ACACIA_OUTSIDE, this);
            case "minecraft:stripped_dark_oak_log" -> LogBuilder.build(x, z, y, Colors.DARK_OAK_LOG_INSIDE, Colors.STRIPPED_DARK_OAK_OUTSIDE, this);
            case "minecraft:snow" -> CarpetBuilder.build(x, z, y, Colors.SNOW);
            case "minecraft:farmland" -> FarmlandBuilder.build(x, z, y, this);
            case "minecraft:crafting_table" -> CubeBuilder.build(x, z, y, new Color(129, 106, 70, 255));
            case "minecraft:coal_ore",
                 "minecraft:iron_ore",
                 "minecraft:gold_ore",
                 "minecraft:redstone_ore",
                 "minecraft:diamond_ore",
                 "minecraft:lapis_ore",
                 "minecraft:emerald_ore" -> OreBuilder.build(x, z, y, this);
            case "minecraft:water" -> List.of();
            /*{
                List<Brick> waterbrick = LiquidBuilder.build(rx, rz, ry, regX, regY, Colors.WATER, this, blocks);
                waterbrick.get(0).setCollision(false);
                yield waterbrick;
            }*/
            case "minecraft:lava" -> {
                List<Brick> lavabrick = LiquidBuilder.build(rx, rz, ry, regX, regY, Colors.LAVA, this, blocks);
                lavabrick.get(0).setMaterialIndex(1);
                lavabrick.get(0).setCollision(false);
                yield lavabrick;
            }
            case "minecraft:oak_planks" -> CubeBuilder.build(x, z, y, Colors.OAK);
            case "minecraft:spruce_planks" -> CubeBuilder.build(x, z, y, Colors.SPRUCE);
            case "minecraft:birch_planks" -> CubeBuilder.build(x, z, y, Colors.BIRCH);
            case "minecraft:jungle_planks" -> CubeBuilder.build(x, z, y, Colors.JUNGLE);
            case "minecraft:acacia_planks" -> CubeBuilder.build(x, z, y, Colors.ACACIA);
            case "minecraft:dark_oak_planks" -> CubeBuilder.build(x, z, y, Colors.DARK_OAK);
            case "minecraft:oak_fence" -> FenceBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_fence" -> FenceBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_fence" -> FenceBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_fence" -> FenceBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_fence" -> FenceBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_fence" -> FenceBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:oak_stairs" -> StairsBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_stairs" -> StairsBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_stairs" -> StairsBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_stairs" -> StairsBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_stairs" -> StairsBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_stairs" -> StairsBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:cobblestone_stairs" -> StairsBuilder.build(x, z, y, Colors.COBBLE, this);
            case "minecraft:chest" -> ChestBuilder.build(x, z, y, properties);
            case "minecraft:grindstone" -> GrindstoneBuilder.build(x, z, y, this);
            case "minecraft:glass_pane" -> PaneBuilder.build(x, z, y, this);
            case "minecraft:white_bed",
                 "minecraft:orange_bed",
                 "minecraft:magenta_bed",
                 "minecraft:light_blue_bed",
                 "minecraft:yellow_bed",
                 "minecraft:lime_bed",
                 "minecraft:pink_bed",
                 "minecraft:gray_bed",
                 "minecraft:light_gray_bed",
                 "minecraft:cyan_bed",
                 "minecraft:purple_bed",
                 "minecraft:blue_bed",
                 "minecraft:brown_bed",
                 "minecraft:green_bed",
                 "minecraft:red_bed",
                 "minecraft:black_bed" -> BedBuilder.build(x, z, y, this);
            case "minecraft:pumpkin" -> CubeBuilder.build(x, z, y, Colors.PUMPKIN);
            case "minecraft:cobblestone_wall", "minecraft:mossy_cobblestone_wall" -> WallBuilder.build(x, z, y, this);
            case "minecraft:torch" -> TorchBuilder.build(x, z, y);
            case "minecraft:spruce_trapdoor" -> TrapdoorBuilder.build(x, z, y, this);
            case "minecraft:wall_torch" -> WallTorchBuilder.build(x, z, y, this);
            case "minecraft:netherrack" -> CubeBuilder.build(x, z, y, Colors.NETHERRACK);
            case "minecraft:stone_bricks" -> BricksBuilder.build(x, z, y, Colors.STONE);
            case "minecraft:mossy_stone_bricks" -> BricksBuilder.build(x, z, y, CobblestoneBuilder.MOSS);
            case "minecraft:glowstone" -> {
                List<Brick> brick = CubeBuilder.build(x, z, y, Colors.GLOWSTONE);
                brick.get(0).setMaterialIndex(1);
                yield brick;
            }
            case "minecraft:stone_slab" -> SlabBuilder.build(x, z, y, Colors.STONE, this);
            case "minecraft:sandstone_slab" -> SlabBuilder.build(x, z, y, Colors.SAND, this);
            case "minecraft:oak_slab" -> SlabBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_slab" -> SlabBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_slab" -> SlabBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_slab" -> SlabBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_slab" -> SlabBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_slab" -> SlabBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:brick_slab" -> SlabBuilder.build(x, z, y, Colors.BRICK, this);
            default -> {
                unsupported.add(blockType);
                yield List.of();
            }
        };
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
                 "minecraft:pumpkin_stem",
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
                 "minecraft:rail",
                 "minecraft:stone_brick_slab",
                 "minecraft:nether_brick_slab",
                 "minecraft:quartz_slab",
                 "minecraft:red_sandstone_slab",
                 "minecraft:purpur_slab",
                 "minecraft:prismarine_slab",
                 "minecraft:prismarine_brick_slab",
                 "minecraft:dark_prismarine_slab",
                 "minecraft:torch",
                 "minecraft:wall_torch",
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
                 "minecraft:white_bed",
                 "minecraft:orange_bed",
                 "minecraft:magenta_bed",
                 "minecraft:light_blue_bed",
                 "minecraft:yellow_bed",
                 "minecraft:lime_bed",
                 "minecraft:pink_bed",
                 "minecraft:gray_bed",
                 "minecraft:light_gray_bed",
                 "minecraft:cyan_bed",
                 "minecraft:purple_bed",
                 "minecraft:blue_bed",
                 "minecraft:brown_bed",
                 "minecraft:green_bed",
                 "minecraft:red_bed",
                 "minecraft:black_bed",
                 "minecraft:cobblestone_wall",
                 "minecraft:mossy_cobblestone_wall",
                 "minecraft:oak_door",
                 "minecraft:spruce_door",
                 "minecraft:birch_door",
                 "minecraft:jungle_door",
                 "minecraft:acacia_door",
                 "minecraft:dark_oak_door",
                 "minecraft:vine",
                 "minecraft:potted_oxeye_daisy",
                 "minecraft:kelp_plant",
                 "minecraft:lava" -> true;
            default -> false;
        };
    }

}
