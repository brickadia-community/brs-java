package Minecraft;

import com.kmschr.brs.Brick;
import com.kmschr.brs.Color;
import net.querz.mca.MCAFile;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

import javax.xml.stream.events.StartDocument;
import java.util.*;

public class Block {

    private static final int SCALE = 16;

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

    public List<Brick> getBricks(int rx, int rz, int ry, int regX, int regY, MCAFile mcaFile, Block[][][] blocks) {
        int x = rx * SCALE * 2 + regX * 32 * 16 * 32;
        int z = rz * SCALE * 2 + regY * 32 * 16 * 32;
        int y = ry * SCALE * 2 + SCALE;
        return switch (blockType) {
            case "minecraft:air", "minecraft:cave_air", "minecraft:void_air" -> List.of();
            case "minecraft:dirt" -> CubeBuilder.build(x, z, y, Colors.DIRT);
            case "minecraft:coarse_dirt" -> CubeBuilder.build(x, z, y, Colors.COARSE_DIRT);
            case "minecraft:grass_block" -> GrassBuilder.build(x, z, y, rx, rz, ry, Colors.grassColor(rx, rz, ry, mcaFile), blocks);
            case "minecraft:grass_path" -> PathBuilder.build(x, z, y);
            case "minecraft:podzol" -> GrassBuilder.build(x, z, y, rx, rz, ry, Colors.PODZOL, blocks);
            case "minecraft:mycelium" -> GrassBuilder.build(x, z, y, rx, rz, ry, Colors.MYCELIUM, blocks);
            case "minecraft:gravel" -> CubeBuilder.build(x, z, y, Colors.GRAVEL);
            case "minecraft:sand" -> CubeBuilder.build(x, z, y, Colors.SAND);
            case "minecraft:sandstone", "minecraft:chiseled_sandstone", "minecraft:smooth_sandstone", "minecraft:cut_sandstone" -> SandstoneBuilder.build(x, z, y, rx, rz, ry, Colors.SAND, blocks);
            case "minecraft:red_sand" -> CubeBuilder.build(x, z, y, Colors.RED_SAND);
            case "minecraft:red_sandstone", "minecraft:chiseled_red_sandstone", "minecraft:smooth_red_sandstone", "minecraft:cut_red_sandstone" -> SandstoneBuilder.build(x, z, y, rx, rz, ry, Colors.RED_SAND, blocks);
            case "minecraft:clay" -> CubeBuilder.build(x, z, y, Colors.CLAY);
            case "minecraft:stone", "minecraft:infested_stone" -> CubeBuilder.build(x, z, y, Colors.STONE);
            case "minecraft:granite" -> CubeBuilder.build(x, z, y, Colors.GRANITE);
            case "minecraft:bedrock" -> CubeBuilder.build(x, z, y, Colors.BEDROCK);
            case "minecraft:diorite" -> CubeBuilder.build(x, z, y, Colors.DIORITE);
            case "minecraft:obsidian" -> CubeBuilder.build(x, z, y, Colors.OBSIDIAN);
            case "minecraft:andesite" -> CubeBuilder.build(x, z, y, Colors.ANDESITE);
            case "minecraft:cobblestone", "minecraft:mossy_cobblestone" -> CobblestoneBuilder.build(x, z, y, this);
            case "minecraft:bricks" -> CubeBuilder.build(x, z, y, Colors.BRICK);

            // LEAVES
            case "minecraft:oak_leaves",
                 "minecraft:jungle_leaves",
                 "minecraft:acacia_leaves",
                 "minecraft:dark_oak_leaves" -> CubeBuilder.build(x, z, y, Colors.foliageColor(rx, rz, ry, mcaFile));
            case "minecraft:spruce_leaves" -> {
                Color spruce_color = Colors.SPRUCE_LEAVES;
                spruce_color.multiply(Colors.FOLIAGE_BASE);
                yield CubeBuilder.build(x, z, y, spruce_color);
            }
            case "minecraft:birch_leaves" -> {
                Color birch_color = Colors.BIRCH_LEAVES;
                birch_color.multiply(Colors.FOLIAGE_BASE);
                yield CubeBuilder.build(x, z, y, birch_color);
            }

            // LOGS
            case "minecraft:oak_log" -> LogBuilder.build(x, z, y, Colors.OAK_LOG_INSIDE, Colors.OAK_LOG_OUTSIDE, this);
            case "minecraft:spruce_log" -> LogBuilder.build(x, z, y, Colors.SPRUCE_LOG_INSIDE, Colors.SPRUCE_LOG_OUTSIDE, this);
            case "minecraft:birch_log" -> LogBuilder.build(x, z, y, Colors.BIRCH_LOG_INSIDE, Colors.BIRCH_LOG_OUTSIDE, this);
            case "minecraft:jungle_log" -> LogBuilder.build(x, z, y, Colors.JUNGLE_LOG_INSIDE, Colors.JUNGLE_LOG_OUTSIDE, this);
            case "minecraft:acacia_log" -> LogBuilder.build(x, z, y, Colors.ACACIA_LOG_INSIDE, Colors.ACACIA_LOG_OUTSIDE, this);
            case "minecraft:dark_oak_log" -> LogBuilder.build(x, z, y, Colors.DARK_OAK_LOG_INSIDE, Colors.DARK_OAK_LOG_OUTSIDE, this);
            case "minecraft:crimson_stem" -> LogBuilder.build(x, z, y, Colors.CRIMSON_LOG_INSIDE, Colors.CRIMSON_LOG_OUTSIDE, this);
            case "minecraft:warped_stem" -> LogBuilder.build(x, z, y, Colors.WARPED_LOG_INSIDE, Colors.WARPED_LOG_OUTSIDE, this);

            // STRIPPED LOGS
            case "minecraft:stripped_oak_log" -> LogBuilder.build(x, z, y, Colors.OAK_LOG_INSIDE, Colors.STRIPPED_OAK_OUTISDE, this);
            case "minecraft:stripped_spruce_log" -> LogBuilder.build(x, z, y, Colors.SPRUCE_LOG_INSIDE, Colors.STRIPPED_SPRUCE_OUTSIDE, this);
            case "minecraft:stripped_birch_log" -> LogBuilder.build(x, z, y, Colors.BIRCH_LOG_INSIDE, Colors.STRIPPED_BIRCH_OUTSIDE, this);
            case "minecraft:stripped_jungle_log" -> LogBuilder.build(x, z, y, Colors.JUNGLE_LOG_INSIDE, Colors.STRIPPED_JUNGLE_OUTSIDE, this);
            case "minecraft:stripped_acacia_log" -> LogBuilder.build(x, z, y, Colors.ACACIA_LOG_INSIDE, Colors.STRIPPED_ACACIA_OUTSIDE, this);
            case "minecraft:stripped_dark_oak_log" -> LogBuilder.build(x, z, y, Colors.DARK_OAK_LOG_INSIDE, Colors.STRIPPED_DARK_OAK_OUTSIDE, this);
            case "minecraft:stripped_crimson_stem" -> LogBuilder.build(x, z, y, Colors.CRIMSON_LOG_INSIDE, Colors.STRIPPED_CRIMSON_OUTSIDE, this);
            case "minecraft:stripped_warped_stem" -> LogBuilder.build(x, z, y, Colors.WARPED_LOG_INSIDE, Colors.STRIPPED_WARPED_OUTSIDE, this);

            // STRIPPED WOOD
            case "minecraft:stripped_oak_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_OAK_OUTISDE);
            case "minecraft:stripped_spruce_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_SPRUCE_OUTSIDE);
            case "minecraft:stripped_birch_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_BIRCH_OUTSIDE);
            case "minecraft:stripped_jungle_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_OAK_OUTISDE);
            case "minecraft:stripped_acacia_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_ACACIA_OUTSIDE);
            case "minecraft:stripped_dark_oak_wood" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_DARK_OAK_OUTSIDE);
            case "minecraft:stripped_crimson_hyphae" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_CRIMSON_OUTSIDE);
            case "minecraft:stripped_warped_hyphae" -> CubeBuilder.build(x, z, y, Colors.STRIPPED_WARPED_OUTSIDE);

            // WOOD
            case "minecraft:oak_wood" -> CubeBuilder.build(x, z, y, Colors.OAK_LOG_OUTSIDE);
            case "minecraft:spruce_wood" -> CubeBuilder.build(x, z, y, Colors.SPRUCE_LOG_OUTSIDE);
            case "minecraft:birch_wood" -> CubeBuilder.build(x, z, y, Colors.BIRCH_LOG_OUTSIDE);
            case "minecraft:jungle_wood" -> CubeBuilder.build(x, z, y, Colors.JUNGLE_LOG_OUTSIDE);
            case "minecraft:acacia_wood" -> CubeBuilder.build(x, z, y, Colors.ACACIA_LOG_OUTSIDE);
            case "minecraft:dark_oak_wood" -> CubeBuilder.build(x, z, y, Colors.DARK_OAK_LOG_OUTSIDE);
            case "minecraft:crimson_hyphae" -> CubeBuilder.build(x, z, y, Colors.CRIMSON_LOG_OUTSIDE);
            case "minecraft:warped_hyphae" -> CubeBuilder.build(x, z, y, Colors.WARPED_LOG_OUTSIDE);

            case "minecraft:snow" -> CarpetBuilder.build(x, z, y, Colors.SNOW);
            case "minecraft:farmland" -> FarmlandBuilder.build(x, z, y, this);
            case "minecraft:crafting_table" -> CubeBuilder.build(x, z, y, new Color(129, 106, 70, 255));

            // ORE
            case "minecraft:coal_ore" -> OreBuilder.build(x, z, y, Colors.ORE_COAL, Colors.STONE, this);
            case "minecraft:iron_ore" -> OreBuilder.build(x, z, y, Colors.ORE_IRON, Colors.STONE, this);
            case "minecraft:gold_ore" -> OreBuilder.build(x, z, y, Colors.ORE_GOLD, Colors.STONE, this);
            case "minecraft:redstone_ore" -> OreBuilder.build(x, z, y, Colors.ORE_REDSTONE, Colors.STONE, this);
            case "minecraft:diamond_ore" -> OreBuilder.build(x, z, y, Colors.ORE_DIAMOND, Colors.STONE, this);
            case "minecraft:lapis_ore" -> OreBuilder.build(x, z, y, Colors.ORE_LAPIS, Colors.STONE, this);
            case "minecraft:emerald_ore" -> OreBuilder.build(x, z, y, Colors.ORE_EMERALD, Colors.STONE, this);
            case "minecraft:nether_gold_ore" -> OreBuilder.build(x, z, y, Colors.ORE_GOLD, Colors.NETHERRACK, this);

            case "minecraft:water" -> {
                //if (ry <= 64) {
                //    yield List.of();
               // }
                List<Brick> waterbrick = LiquidBuilder.build(rx, rz, ry, regX, regY, Colors.WATER, this, blocks);
                waterbrick.get(0).setCollision(false);
                yield waterbrick;
            }
            case "minecraft:lava" -> {
                List<Brick> lavabrick = LiquidBuilder.build(rx, rz, ry, regX, regY, Colors.LAVA, this, blocks);
                lavabrick.get(0).setMaterialIndex(1);
                lavabrick.get(0).setCollision(false);
                yield lavabrick;
            }

            // PLANKS
            case "minecraft:oak_planks" -> CubeBuilder.build(x, z, y, Colors.OAK);
            case "minecraft:spruce_planks" -> CubeBuilder.build(x, z, y, Colors.SPRUCE);
            case "minecraft:birch_planks" -> CubeBuilder.build(x, z, y, Colors.BIRCH);
            case "minecraft:jungle_planks" -> CubeBuilder.build(x, z, y, Colors.JUNGLE);
            case "minecraft:acacia_planks" -> CubeBuilder.build(x, z, y, Colors.ACACIA);
            case "minecraft:dark_oak_planks" -> CubeBuilder.build(x, z, y, Colors.DARK_OAK);
            case "minecraft:crimson_planks" -> CubeBuilder.build(x, z, y, Colors.CRIMSON);
            case "minecraft:warped_planks" -> CubeBuilder.build(x, z, y, Colors.WARPED);

            // FENCES
            case "minecraft:oak_fence" -> FenceBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_fence" -> FenceBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_fence" -> FenceBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_fence" -> FenceBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_fence" -> FenceBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_fence" -> FenceBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:crimson_fence" -> FenceBuilder.build(x, z, y, Colors.CRIMSON, this);
            case "minecraft:warped_fence" -> FenceBuilder.build(x, z, y, Colors.WARPED, this);
            case "minecraft:nether_brick_fence" -> FenceBuilder.build(x, z, y, Colors.NETHER, this);

            // FENCE GATES
            case "minecraft:oak_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:crimson_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.CRIMSON, this);
            case "minecraft:warped_fence_gate" -> FenceGateBuilder.build(x, z, y, Colors.WARPED, this);

            // BUTTONS
            case "minecraft:oak_button" -> ButtonBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_button" -> ButtonBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_button" -> ButtonBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_button" -> ButtonBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_button" -> ButtonBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_button" -> ButtonBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:stone_button" -> ButtonBuilder.build(x, z, y, Colors.STONE, this);
            case "minecraft:crimson_button" -> ButtonBuilder.build(x, z, y, Colors.CRIMSON, this);
            case "minecraft:warped_button" -> ButtonBuilder.build(x, z, y, Colors.WARPED, this);
            case "minecraft:polished_blackstone_button" -> ButtonBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE, this);

            // STAIRS
            case "minecraft:oak_stairs" -> StairsBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_stairs" -> StairsBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_stairs" -> StairsBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_stairs" -> StairsBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_stairs" -> StairsBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_stairs" -> StairsBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:crimson_stairs" -> StairsBuilder.build(x, z, y, Colors.CRIMSON, this);
            case "minecraft:warped_stairs" -> StairsBuilder.build(x, z, y, Colors.WARPED, this);
            case "minecraft:cobblestone_stairs" -> StairsBuilder.build(x, z, y, Colors.COBBLE, this);
            case "minecraft:brick_stairs" -> StairsBuilder.build(x, z, y, Colors.BRICK, this);
            case "minecraft:nether_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.NETHER, this);
            case "minecraft:red_nether_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.RED_NETHER, this);
            case "minecraft:sandstone_stairs", "minecraft:smooth_sandstone_stairs" -> StairsBuilder.build(x, z, y, Colors.SAND, this);
            case "minecraft:red_sandstone_stairs", "minecraft:smooth_red_sandstone_stairs" -> StairsBuilder.build(x, z, y, Colors.RED_SAND, this);
            case "minecraft:quartz_stairs", "minecraft:smooth_quartz_stairs" -> StairsBuilder.build(x, z, y, Colors.QUARTZ, this);
            case "minecraft:stone_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.STONE, this);
            case "minecraft:mossy_cobblestone_stairs", "minecraft:mossy_stone_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.MOSS, this);
            case "minecraft:prismarine_stairs", "minecraft:prismarine_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.PRISMARINE, this);
            case "minecraft:dark_prismarine_stairs" -> StairsBuilder.build(x, z, y, Colors.DARK_PRISMARINE, this);
            case "minecraft:blackstone_stairs" -> StairsBuilder.build(x, z, y, Colors.BLACKSTONE, this);
            case "minecraft:polished_blackstone_stairs", "minecraft:polished_blackstone_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE, this);
            case "minecraft:diorite_stairs" -> StairsBuilder.build(x, z, y, Colors.DIORITE, this);
            case "minecraft:polished_diorite_stairs" -> StairsBuilder.build(x, z, y, Colors.POLISHED_DIORITE, this);
            case "minecraft:andesite_stairs" -> StairsBuilder.build(x, z, y, Colors.ANDESITE, this);
            case "minecraft:polished_andesite_stairs" -> StairsBuilder.build(x, z, y, Colors.POLISHED_ANDESITE, this);
            case "minecraft:granite_stairs" -> StairsBuilder.build(x, z, y, Colors.GRANITE, this);
            case "minecraft:polished_granite_stairs" -> StairsBuilder.build(x, z, y, Colors.POLISHED_GRANITE, this);
            case "minecraft:purpur_stairs" -> StairsBuilder.build(x, z, y, Colors.PURPUR, this);
            case "minecraft:end_stone_brick_stairs" -> StairsBuilder.build(x, z, y, Colors.END_STONE, this);

            // BEDS
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
            case "minecraft:pumpkin", "minecraft:carved_pumpkin" -> CubeBuilder.build(x, z, y, Colors.PUMPKIN);

            // WALLS
            case "minecraft:cobblestone_wall" -> WallBuilder.build(x, z, y, Colors.COBBLE, this);
            case "minecraft:mossy_cobblestone_wall", "minecraft:mossy_stone_brick_wall" -> WallBuilder.build(x, z, y, Colors.MOSS, this);
            case "minecraft:stone_brick_wall" -> WallBuilder.build(x, z, y, Colors.STONE, this);
            case "minecraft:brick_wall" -> WallBuilder.build(x, z, y, Colors.BRICK, this);
            case "minecraft:sandstone_wall" -> WallBuilder.build(x, z, y, Colors.SAND, this);
            case "minecraft:prismarine_wall" -> WallBuilder.build(x, z, y, Colors.PRISMARINE, this);
            case "minecraft:nether_brick_wall" -> WallBuilder.build(x, z, y, Colors.NETHER, this);
            case "minecraft:red_nether_brick_wall" -> WallBuilder.build(x, z, y, Colors.RED_NETHER, this);
            case "minecraft:blackstone_wall" -> WallBuilder.build(x, z, y, Colors.BLACKSTONE, this);
            case "minecraft:polished_blackstone_wall", "minecraft:polished_blackstone_brick_wall" -> WallBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE, this);
            case "minecraft:andesite_wall" -> WallBuilder.build(x, z, y, Colors.ANDESITE, this);
            case "minecraft:diorite_wall" -> WallBuilder.build(x, z, y, Colors.DIORITE, this);
            case "minecraft:granite_wall" -> WallBuilder.build(x, z, y, Colors.GRANITE, this);
            case "minecraft:red_sandstone_wall" -> WallBuilder.build(x, z, y, Colors.RED_SAND, this);
            case "minecraft:end_stone_brick_wall" -> WallBuilder.build(x, z, y, Colors.END_STONE, this);

            // BRICKS
            case "minecraft:stone_bricks",
                    "minecraft:cracked_stone_bricks",
                    "minecraft:infested_stone_bricks",
                    "minecraft:infested_chiseled_stone_bricks",
                    "minecraft:infested_cracked_stone_bricks" -> BricksBuilder.build(x, z, y, Colors.STONE);
            case "minecraft:mossy_stone_bricks", "minecraft:infested_mossy_stone_bricks" -> BricksBuilder.build(x, z, y, Colors.MOSS);
            case "minecraft:polished_blackstone_bricks", "minecraft:cracked_polished_blackstone_bricks" -> BricksBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE);
            case "minecraft:quartz_bricks" -> BricksBuilder.build(x, z, y, Colors.QUARTZ);
            case "minecraft:end_stone_bricks" -> BricksBuilder.build(x, z, y, Colors.END_STONE);
            case "minecraft:cracked_nether_bricks", "minecraft:chiseled_nether_bricks" -> BricksBuilder.build(x, z, y, Colors.NETHER);
            case "minecraft:chiseled_stone_bricks" -> CubeBuilder.build(x, z, y, Colors.STONE);

            case "minecraft:glowstone" -> {
                List<Brick> brick = CubeBuilder.build(x, z, y, Colors.GLOWSTONE);
                brick.get(0).setMaterialIndex(1);
                yield brick;
            }

            // SLABS
            case "minecraft:stone_slab", "minecraft:stone_brick_slab", "minecraft:smooth_stone_slab" -> SlabBuilder.build(x, z, y, Colors.STONE, this);
            case "minecraft:mossy_stone_brick_slab", "minecraft:mossy_cobblestone_slab" -> SlabBuilder.build(x, z, y, Colors.MOSS, this);
            case "minecraft:cobblestone_slab" -> SlabBuilder.build(x, z, y, Colors.COBBLE, this);
            case "minecraft:sandstone_slab", "minecraft:cut_sandstone_slab", "minecraft:smooth_sandstone_slab" -> SlabBuilder.build(x, z, y, Colors.SAND, this);
            case "minecraft:red_sandstone_slab", "minecraft:cut_red_sandstone_slab", "minecraft:smooth_red_sandstone_slab" -> SlabBuilder.build(x, z, y, Colors.RED_SAND, this);
            case "minecraft:blackstone_slab" -> SlabBuilder.build(x, z, y, Colors.BLACKSTONE, this);
            case "minecraft:polished_blackstone_slab", "minecraft:polished_blackstone_brick_slab" -> SlabBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE, this);
            case "minecraft:nether_brick_slab" -> SlabBuilder.build(x, z, y, Colors.NETHER, this);
            case "minecraft:red_nether_brick_slab" -> SlabBuilder.build(x, z, y, Colors.RED_NETHER, this);
            case "minecraft:end_stone_brick_slab" -> SlabBuilder.build(x, z, y, Colors.END_STONE, this);
            case "minecraft:oak_slab", "minecraft:petrified_oak_slab" -> SlabBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_slab" -> SlabBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_slab" -> SlabBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_slab" -> SlabBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_slab" -> SlabBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_slab" -> SlabBuilder.build(x, z, y, Colors.DARK_OAK, this);
            case "minecraft:crimson_slab" -> SlabBuilder.build(x, z, y, Colors.CRIMSON, this);
            case "minecraft:warped_slab" -> SlabBuilder.build(x, z, y, Colors.WARPED, this);
            case "minecraft:brick_slab" -> SlabBuilder.build(x, z, y, Colors.BRICK, this);
            case "minecraft:quartz_slab", "minecraft:smooth_quartz_slab" -> SlabBuilder.build(x, z, y, Colors.QUARTZ, this);
            case "minecraft:purpur_slab" -> SlabBuilder.build(x, z, y, Colors.PURPUR, this);
            case "minecraft:prismarine_slab", "minecraft:prismarine_brick_slab" -> SlabBuilder.build(x, z, y, Colors.PRISMARINE, this);
            case "minecraft:dark_prismarine_slab" -> SlabBuilder.build(x, z, y, Colors.DARK_PRISMARINE, this);
            case "minecraft:andesite_slab" -> SlabBuilder.build(x, z, y, Colors.ANDESITE, this);
            case "minecraft:polished_andesite_slab" -> SlabBuilder.build(x, z, y, Colors.POLISHED_ANDESITE, this);
            case "minecraft:diorite_slab" -> SlabBuilder.build(x, z, y, Colors.DIORITE, this);
            case "minecraft:polished_diorite_slab" -> SlabBuilder.build(x, z, y, Colors.POLISHED_DIORITE, this);
            case "minecraft:granite_slab" -> SlabBuilder.build(x, z, y, Colors.GRANITE, this);
            case "minecraft:polished_granite_slab" -> SlabBuilder.build(x, z, y, Colors.POLISHED_GRANITE, this);

            // PRESSURE PLATES
            case "minecraft:stone_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.STONE);
            case "minecraft:oak_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.OAK);
            case "minecraft:spruce_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.SPRUCE);
            case "minecraft:birch_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.BIRCH);
            case "minecraft:jungle_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.JUNGLE);
            case "minecraft:acacia_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.ACACIA);
            case "minecraft:dark_oak_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.DARK_OAK);
            case "minecraft:crimson_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.CRIMSON);
            case "minecraft:warped_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.WARPED);
            case "minecraft:polished_blackstone_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.POLISHED_BLACKSTONE);
            case "minecraft:light_weighted_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.GOLD_BLOCK_INSIDE);
            case "minecraft:heavy_weighted_pressure_plate" -> PressurePlateBuilder.build(x, z, y, Colors.IRON_BLOCK_INSIDE);

            // SIGNS
            case "minecraft:wall_sign" -> WallSignBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:oak_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.OAK, this);
            case "minecraft:spruce_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.SPRUCE, this);
            case "minecraft:birch_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.BIRCH, this);
            case "minecraft:jungle_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.JUNGLE, this);
            case "minecraft:acacia_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.ACACIA, this);
            case "minecraft:dark_oak_wall_sign" -> WallSignBuilder.build(x, z, y, Colors.DARK_OAK, this);

            // CARPET
            case "minecraft:black_carpet" -> CarpetBuilder.build(x, z, y, Colors.BLACK_WOOL);
            case "minecraft:blue_carpet" -> CarpetBuilder.build(x, z, y, Colors.BLUE_WOOL);
            case "minecraft:brown_carpet" -> CarpetBuilder.build(x, z, y, Colors.BROWN_WOOL);
            case "minecraft:cyan_carpet" -> CarpetBuilder.build(x, z, y, Colors.CYAN_WOOL);
            case "minecraft:gray_carpet" -> CarpetBuilder.build(x, z, y, Colors.GRAY_WOOL);
            case "minecraft:green_carpet" -> CarpetBuilder.build(x, z, y, Colors.GREEN_WOOL);
            case "minecraft:light_blue_carpet" -> CarpetBuilder.build(x, z, y, Colors.LIGHT_BLUE_WOOL);
            case "minecraft:light_gray_carpet" -> CarpetBuilder.build(x, z, y, Colors.LIGHT_GRAY_WOOL);
            case "minecraft:lime_carpet" -> CarpetBuilder.build(x, z, y, Colors.LIME_WOOL);
            case "minecraft:magenta_carpet" -> CarpetBuilder.build(x, z, y, Colors.MAGENTA_WOOL);
            case "minecraft:orange_carpet" -> CarpetBuilder.build(x, z, y, Colors.ORANGE_WOOL);
            case "minecraft:pink_carpet" -> CarpetBuilder.build(x, z, y, Colors.PINK_WOOL);
            case "minecraft:purple_carpet" -> CarpetBuilder.build(x, z, y, Colors.PURPLE_WOOL);
            case "minecraft:red_carpet" -> CarpetBuilder.build(x, z, y, Colors.RED);
            case "minecraft:white_carpet" -> CarpetBuilder.build(x, z, y, Colors.WHITE);
            case "minecraft:yellow_carpet" -> CarpetBuilder.build(x, z, y, Colors.YELLOW_WOOL);

            // WOOL
            case "minecraft:black_wool" -> CubeBuilder.build(x, z, y, Colors.BLACK_WOOL);
            case "minecraft:blue_wool" -> CubeBuilder.build(x, z, y, Colors.BLUE_WOOL);
            case "minecraft:brown_wool" -> CubeBuilder.build(x, z, y, Colors.BROWN_WOOL);
            case "minecraft:cyan_wool" -> CubeBuilder.build(x, z, y, Colors.CYAN_WOOL);
            case "minecraft:gray_wool" -> CubeBuilder.build(x, z, y, Colors.GRAY_WOOL);
            case "minecraft:green_wool" -> CubeBuilder.build(x, z, y, Colors.GREEN_WOOL);
            case "minecraft:light_blue_wool" -> CubeBuilder.build(x, z, y, Colors.LIGHT_BLUE_WOOL);
            case "minecraft:light_gray_wool" -> CubeBuilder.build(x, z, y, Colors.LIGHT_GRAY_WOOL);
            case "minecraft:lime_wool" -> CubeBuilder.build(x, z, y, Colors.LIME_WOOL);
            case "minecraft:magenta_wool" -> CubeBuilder.build(x, z, y, Colors.MAGENTA_WOOL);
            case "minecraft:orange_wool" -> CubeBuilder.build(x, z, y, Colors.ORANGE_WOOL);
            case "minecraft:pink_wool" -> CubeBuilder.build(x, z, y, Colors.PINK_WOOL);
            case "minecraft:purple_wool" -> CubeBuilder.build(x, z, y, Colors.PURPLE_WOOL);
            case "minecraft:red_wool" -> CubeBuilder.build(x, z, y, Colors.RED_WOOL);
            case "minecraft:white_wool" -> CubeBuilder.build(x, z, y, Colors.WHITE_WOOL);
            case "minecraft:yellow_wool" -> CubeBuilder.build(x, z, y, Colors.YELLOW_WOOL);

            // CONCRETE
            case "minecraft:black_concrete" -> CubeBuilder.build(x, z, y, Colors.BLACK_CONCRETE);
            case "minecraft:black_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.BLACK_CONCRETE_POWDER);
            case "minecraft:blue_concrete" -> CubeBuilder.build(x, z, y, Colors.BLUE_CONCRETE);
            case "minecraft:blue_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.BLUE_CONCRETE_POWDER);
            case "minecraft:brown_concrete" -> CubeBuilder.build(x, z, y, Colors.BROWN_CONCRETE);
            case "minecraft:brown_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.BROWN_CONCRETE_POWDER);
            case "minecraft:cyan_concrete" -> CubeBuilder.build(x, z, y, Colors.CYAN_CONCRETE);
            case "minecraft:cyan_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.CYAN_CONCRETE_POWDER);
            case "minecraft:gray_concrete" -> CubeBuilder.build(x, z, y, Colors.GRAY_CONCRETE);
            case "minecraft:gray_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.GRAY_CONCRETE_POWDER);
            case "minecraft:green_concrete" -> CubeBuilder.build(x, z, y, Colors.GREEN_CONCRETE);
            case "minecraft:green_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.GREEN_CONCRETE_POWDER);
            case "minecraft:light_blue_concrete" -> CubeBuilder.build(x, z, y, Colors.LIGHT_BLUE_CONCRETE);
            case "minecraft:light_blue_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.LIGHT_BLUE_CONCRETE_POWDER);
            case "minecraft:light_gray_concrete" -> CubeBuilder.build(x, z, y, Colors.LIGHT_GRAY_CONCRETE);
            case "minecraft:light_gray_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.LIGHT_GRAY_CONCRETE_POWDER);
            case "minecraft:lime_concrete" -> CubeBuilder.build(x, z, y, Colors.LIME_CONCRETE);
            case "minecraft:lime_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.LIME_CONCRETE_POWDER);
            case "minecraft:magenta_concrete" -> CubeBuilder.build(x, z, y, Colors.MAGENTA_CONCRETE);
            case "minecraft:magenta_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.MAGENTA_CONCRETE_POWDER);
            case "minecraft:orange_concrete" -> CubeBuilder.build(x, z, y, Colors.ORANGE_CONCRETE);
            case "minecraft:orange_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.ORANGE_CONCRETE_POWDER);
            case "minecraft:pink_concrete" -> CubeBuilder.build(x, z, y, Colors.PINK_CONCRETE);
            case "minecraft:pink_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.PINK_CONCRETE_POWDER);
            case "minecraft:purple_concrete" -> CubeBuilder.build(x, z, y, Colors.PURPLE_CONCRETE);
            case "minecraft:purple_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.PURPLE_CONCRETE_POWDER);
            case "minecraft:red_concrete" -> CubeBuilder.build(x, z, y, Colors.RED_CONCRETE);
            case "minecraft:red_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.RED_CONCRETE_POWDER);
            case "minecraft:white_concrete" -> CubeBuilder.build(x, z, y, Colors.WHITE_CONCRETE);
            case "minecraft:white_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.WHITE_CONCRETE_POWDER);
            case "minecraft:yellow_concrete" -> CubeBuilder.build(x, z, y, Colors.YELLOW_CONCRETE);
            case "minecraft:yellow_concrete_powder" -> CubeBuilder.build(x, z, y, Colors.YELLOW_CONCRETE_POWDER);

            // CORAL
            case "minecraft:brain_coral_block" -> CubeBuilder.build(x, z, y, Colors.BRAIN_CORAL);
            case "minecraft:dead_brain_coral_block" -> CubeBuilder.build(x, z, y, Colors.DEAD_BRAIN_CORAL);

            // TERRACOTTA
            case "minecraft:terracotta" -> CubeBuilder.build(x, z, y, Colors.TERRACOTTA);
            case "minecraft:black_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.BLACK_GLAZED_TERRACOTTA);
            case "minecraft:black_terracotta" -> CubeBuilder.build(x, z, y, Colors.BLACK_TERRACOTTA);
            case "minecraft:blue_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.BLUE_GLAZED_TERRACOTTA);
            case "minecraft:blue_terracotta" -> CubeBuilder.build(x, z, y, Colors.BLUE_TERRACOTTA);
            case "minecraft:brown_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.BROWN_GLAZED_TERRACOTTA);
            case "minecraft:brown_terracotta" -> CubeBuilder.build(x, z, y, Colors.BROWN_TERRACOTTA);
            case "minecraft:cyan_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.CYAN_GLAZED_TERRACOTTA);
            case "minecraft:cyan_terracotta" -> CubeBuilder.build(x, z, y, Colors.CYAN_TERRACOTTA);
            case "minecraft:gray_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.GRAY_GLAZED_TERRACOTTA);
            case "minecraft:gray_terracotta" -> CubeBuilder.build(x, z, y, Colors.GRAY_TERRACOTTA);
            case "minecraft:green_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.GREEN_GLAZED_TERRACOTTA);
            case "minecraft:green_terracotta" -> CubeBuilder.build(x, z, y, Colors.GREEN_TERRACOTTA);
            case "minecraft:light_blue_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIGHT_BLUE_GLAZED_TERRACOTTA);
            case "minecraft:light_blue_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIGHT_BLUE_TERRACOTTA);
            case "minecraft:light_gray_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIGHT_GRAY_GLAZED_TERRACOTTA);
            case "minecraft:light_gray_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIGHT_GRAY_TERRACOTTA);
            case "minecraft:lime_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIME_GLAZED_TERRACOTTA);
            case "minecraft:lime_terracotta" -> CubeBuilder.build(x, z, y, Colors.LIME_TERRACOTTA);
            case "minecraft:magenta_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.MAGENTA_GLAZED_TERRACOTTA);
            case "minecraft:magenta_terracotta" -> CubeBuilder.build(x, z, y, Colors.MAGENTA_TERRACOTTA);
            case "minecraft:orange_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.ORANGE_GLAZED_TERRACOTTA);
            case "minecraft:orange_terracotta" -> CubeBuilder.build(x, z, y, Colors.ORANGE_TERRACOTTA);
            case "minecraft:pink_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.PINK_GLAZED_TERRACOTTA);
            case "minecraft:pink_terracotta" -> CubeBuilder.build(x, z, y, Colors.PINK_TERRACOTTA);
            case "minecraft:purple_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.PURPLE_GLAZED_TERRACOTTA);
            case "minecraft:purple_terracotta" -> CubeBuilder.build(x, z, y, Colors.PURPLE_TERRACOTTA);
            case "minecraft:red_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.RED_GLAZED_TERRACOTTA);
            case "minecraft:red_terracotta" -> CubeBuilder.build(x, z, y, Colors.RED_TERRACOTTA);
            case "minecraft:white_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.WHITE_GLAZED_TERRACOTTA);
            case "minecraft:white_terracotta" -> CubeBuilder.build(x, z, y, Colors.WHITE_TERRACOTTA);
            case "minecraft:yellow_glazed_terracotta" -> CubeBuilder.build(x, z, y, Colors.YELLOW_GLAZED_TERRACOTTA);
            case "minecraft:yellow_terracotta" -> CubeBuilder.build(x, z, y, Colors.YELLOW_TERRACOTTA);

            // BANNERS
            case "minecraft:black_banner" -> BannerBuilder.build(x, z, y, Colors.BLACK_WOOL, this);
            case "minecraft:blue_banner" -> BannerBuilder.build(x, z, y, Colors.BLUE_WOOL, this);
            case "minecraft:brown_banner" -> BannerBuilder.build(x, z, y, Colors.BROWN_WOOL, this);
            case "minecraft:cyan_banner" -> BannerBuilder.build(x, z, y, Colors.CYAN_WOOL, this);
            case "minecraft:gray_banner" -> BannerBuilder.build(x, z, y, Colors.GRAY_WOOL, this);
            case "minecraft:green_banner" -> BannerBuilder.build(x, z, y, Colors.GREEN_WOOL, this);
            case "minecraft:light_blue_banner" -> BannerBuilder.build(x, z, y, Colors.LIGHT_BLUE_WOOL, this);
            case "minecraft:light_gray_banner" -> BannerBuilder.build(x, z, y, Colors.LIGHT_GRAY_WOOL, this);
            case "minecraft:lime_banner" -> BannerBuilder.build(x, z, y, Colors.LIME_WOOL, this);
            case "minecraft:magenta_banner" -> BannerBuilder.build(x, z, y, Colors.MAGENTA_WOOL, this);
            case "minecraft:orange_banner" -> BannerBuilder.build(x, z, y, Colors.ORANGE_WOOL, this);
            case "minecraft:pink_banner" -> BannerBuilder.build(x, z, y, Colors.PINK_WOOL, this);
            case "minecraft:purple_banner" -> BannerBuilder.build(x, z, y, Colors.PURPLE_WOOL, this);
            case "minecraft:red_banner" -> BannerBuilder.build(x, z, y, Colors.RED_WOOL, this);
            case "minecraft:white_banner" -> BannerBuilder.build(x, z, y, Colors.WHITE_WOOL, this);
            case "minecraft:yellow_banner" -> BannerBuilder.build(x, z, y, Colors.YELLOW_WOOL, this);

            // WALL BANNERS
            case "minecraft:black_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.BLACK_WOOL, this);
            case "minecraft:blue_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.BLUE_WOOL, this);
            case "minecraft:brown_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.BROWN_WOOL, this);
            case "minecraft:cyan_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.CYAN_WOOL, this);
            case "minecraft:gray_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.GRAY_WOOL, this);
            case "minecraft:green_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.GREEN_WOOL, this);
            case "minecraft:light_blue_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.LIGHT_BLUE_WOOL, this);
            case "minecraft:light_gray_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.LIGHT_GRAY_WOOL, this);
            case "minecraft:lime_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.LIME_WOOL, this);
            case "minecraft:magenta_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.MAGENTA_WOOL, this);
            case "minecraft:orange_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.ORANGE_WOOL, this);
            case "minecraft:pink_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.PINK_WOOL, this);
            case "minecraft:purple_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.PURPLE_WOOL, this);
            case "minecraft:red_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.RED_WOOL, this);
            case "minecraft:white_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.WHITE_WOOL, this);
            case "minecraft:yellow_wall_banner" -> WallBannerBuilder.build(x, z, y, Colors.YELLOW_WOOL, this);

            // GLASS
            case "minecraft:glass" -> GlassBuilder.build(x, z, y, Colors.WHITE, Colors.INVISIBLE);
            case "minecraft:black_stained_glass" -> GlassBuilder.build(x, z, y, Colors.BLACK_GLASS_BORDER, Colors.BLACK_GLASS);
            case "minecraft:blue_stained_glass" -> GlassBuilder.build(x, z, y, Colors.BLUE_GLASS_BORDER, Colors.BLUE_GLASS);
            case "minecraft:brown_stained_glass" -> GlassBuilder.build(x, z, y, Colors.BROWN_GLASS_BORDER, Colors.BROWN_GLASS);
            case "minecraft:cyan_stained_glass" -> GlassBuilder.build(x, z, y, Colors.CYAN_GLASS_BORDER, Colors.CYAN_GLASS);
            case "minecraft:gray_stained_glass" -> GlassBuilder.build(x, z, y, Colors.GRAY_GLASS_BORDER, Colors.GRAY_GLASS);
            case "minecraft:green_stained_glass" -> GlassBuilder.build(x, z, y, Colors.GREEN_GLASS_BORDER, Colors.GREEN_GLASS);
            case "minecraft:light_blue_stained_glass" -> GlassBuilder.build(x, z, y, Colors.LIGHT_BLUE_GLASS_BORDER, Colors.LIGHT_BLUE_GLASS);
            case "minecraft:light_gray_stained_glass" -> GlassBuilder.build(x, z, y, Colors.LIGHT_GRAY_GLASS_BORDER, Colors.LIGHT_GRAY_GLASS);
            case "minecraft:lime_stained_glass" -> GlassBuilder.build(x, z, y, Colors.LIME_GLASS_BORDER, Colors.LIME_GLASS);
            case "minecraft:magenta_stained_glass" -> GlassBuilder.build(x, z, y, Colors.MAGENTA_GLASS_BORDER, Colors.MAGENTA_GLASS);
            case "minecraft:orange_stained_glass" -> GlassBuilder.build(x, z, y, Colors.ORANGE_GLASS_BORDER, Colors.ORANGE_GLASS);
            case "minecraft:pink_stained_glass" -> GlassBuilder.build(x, z, y, Colors.PINK_GLASS_BORDER, Colors.PINK_GLASS);
            case "minecraft:purple_stained_glass" -> GlassBuilder.build(x, z, y, Colors.PURPLE_GLASS_BORDER, Colors.PURPLE_GLASS);
            case "minecraft:red_stained_glass" -> GlassBuilder.build(x, z, y, Colors.RED_GLASS_BORDER, Colors.RED_GLASS);
            case "minecraft:white_stained_glass" -> GlassBuilder.build(x, z, y, Colors.WHITE_GLASS_BORDER, Colors.WHITE_GLASS);
            case "minecraft:yellow_stained_glass" -> GlassBuilder.build(x, z, y, Colors.YELLOW_GLASS_BORDER, Colors.YELLOW_GLASS);

            // GLASS PANES
            case "minecraft:glass_pane" -> PaneBuilder.build(x, z, y, Colors.GLASS_BORDER, Colors.INVISIBLE, this, false);
            case "minecraft:black_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.BLACK_GLASS_BORDER, Colors.BLACK_GLASS, this, true);
            case "minecraft:blue_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.BLUE_GLASS_BORDER, Colors.BLUE_GLASS, this, true);
            case "minecraft:brown_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.BROWN_GLASS_BORDER, Colors.BROWN_GLASS, this, true);
            case "minecraft:cyan_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.CYAN_GLASS_BORDER, Colors.CYAN_GLASS, this, true);
            case "minecraft:gray_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.GRAY_GLASS_BORDER, Colors.GRAY_GLASS, this, true);
            case "minecraft:green_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.GREEN_GLASS_BORDER, Colors.GREEN_GLASS, this, true);
            case "minecraft:light_blue_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.LIGHT_BLUE_GLASS_BORDER, Colors.LIGHT_BLUE_GLASS, this, true);
            case "minecraft:light_gray_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.LIGHT_GRAY_GLASS_BORDER, Colors.LIGHT_GRAY_GLASS, this, true);
            case "minecraft:lime_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.LIME_GLASS_BORDER, Colors.LIME_GLASS, this, true);
            case "minecraft:magenta_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.MAGENTA_GLASS_BORDER, Colors.MAGENTA_GLASS, this, true);
            case "minecraft:orange_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.ORANGE_GLASS_BORDER, Colors.ORANGE_GLASS, this, true);
            case "minecraft:pink_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.PINK_GLASS_BORDER, Colors.PINK_GLASS, this, true);
            case "minecraft:purple_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.PURPLE_GLASS_BORDER, Colors.PURPLE_GLASS, this, true);
            case "minecraft:red_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.RED_GLASS_BORDER, Colors.RED_GLASS, this, true);
            case "minecraft:white_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.WHITE_GLASS_BORDER, Colors.WHITE_GLASS, this, true);
            case "minecraft:yellow_stained_glass_pane" -> PaneBuilder.build(x, z, y, Colors.YELLOW_GLASS_BORDER, Colors.YELLOW_GLASS, this, true);

            // SHULKERS
            case "minecraft:shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.PURPLE_CONCRETE_POWDER, Colors.PURPLE_CONCRETE);
            case "minecraft:black_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.BLACK_CONCRETE_POWDER, Colors.BLACK_CONCRETE);
            case "minecraft:blue_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.BLUE_CONCRETE_POWDER, Colors.BLUE_CONCRETE);
            case "minecraft:brown_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.BROWN_CONCRETE_POWDER, Colors.BROWN_CONCRETE);
            case "minecraft:cyan_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.CYAN_CONCRETE_POWDER, Colors.CYAN_CONCRETE);
            case "minecraft:gray_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.GRAY_CONCRETE_POWDER, Colors.GRAY_CONCRETE);
            case "minecraft:green_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.GREEN_CONCRETE_POWDER, Colors.GREEN_CONCRETE);
            case "minecraft:light_blue_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.LIGHT_BLUE_CONCRETE_POWDER, Colors.LIGHT_BLUE_CONCRETE);
            case "minecraft:light_gray_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.LIGHT_GRAY_CONCRETE_POWDER, Colors.LIGHT_GRAY_CONCRETE);
            case "minecraft:lime_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.LIME_CONCRETE_POWDER, Colors.LIME_CONCRETE);
            case "minecraft:magenta_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.MAGENTA_CONCRETE_POWDER, Colors.MAGENTA_CONCRETE);
            case "minecraft:orange_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.ORANGE_CONCRETE_POWDER, Colors.ORANGE_CONCRETE);
            case "minecraft:pink_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.PINK_CONCRETE_POWDER, Colors.PINK_CONCRETE);
            case "minecraft:purple_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.PURPLE_CONCRETE_POWDER, Colors.PURPLE_CONCRETE);
            case "minecraft:red_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.RED_CONCRETE_POWDER, Colors.RED_CONCRETE);
            case "minecraft:white_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.WHITE_CONCRETE_POWDER, Colors.WHITE_CONCRETE);
            case "minecraft:yellow_shulker_box" -> ShulkerBuilder.build(x, z, y, Colors.YELLOW_CONCRETE_POWDER, Colors.YELLOW_CONCRETE);

            // Smooth/Polished bricks
            case "minecraft:polished_granite" -> SmoothBuilder.build(x, z, y, Colors.POLISHED_GRANITE_OUTSIDE, Colors.POLISHED_GRANITE_INSIDE);
            case "minecraft:polished_diorite" -> SmoothBuilder.build(x, z, y, Colors.POLISHED_DIORITE_OUTSIDE, Colors.POLISHED_DIORITE_INSIDE);
            case "minecraft:polished_andesite" -> SmoothBuilder.build(x, z, y, Colors.POLISHED_ANDESITE_OUTSIDE, Colors.POLISHED_ANDESITE_INSIDE);
            case "minecraft:smooth_stone" -> SmoothBuilder.build(x, z, y, Colors.SMOOTH_STONE_OUTSIDE, Colors.SMOOTH_STONE_INSIDE);
            case "minecraft:iron_block" -> SmoothBuilder.build(x, z, y, Colors.IRON_BLOCK_OUTSIDE, Colors.IRON_BLOCK_INSIDE);
            case "minecraft:gold_block" -> SmoothBuilder.build(x, z, y, Colors.GOLD_BLOCK_OUTSIDE, Colors.GOLD_BLOCK_INSIDE);
            case "minecraft:diamond_block" -> SmoothBuilder.build(x, z, y, Colors.DIAMOND_BLOCK_OUTSIDE, Colors.DIAMOND_BLOCK_INSIDE);
            case "minecraft:lapis_block" -> SmoothBuilder.build(x, z, y, Colors.LAPIS_BLOCK_OUTSIDE, Colors.LAPIS_BLOCK_INSIDE);
            case "minecraft:note_block" -> SmoothBuilder.build(x, z, y, Colors.NOTE_BLOCK_OUTSIDE, Colors.NOTE_BLOCK_INSIDE);

            // Trapdoors
            case "minecraft:spruce_trapdoor" -> TrapdoorBuilder.spruce(x, z, y, this);
            case "minecraft:oak_trapdoor" -> TrapdoorBuilder.cross(x, z, y, Colors.OAK, this);
            case "minecraft:iron_trapdoor" -> TrapdoorBuilder.cross(x, z, y, Colors.IRON_BLOCK_OUTSIDE, this);

            case "minecraft:blackstone" -> CubeBuilder.build(x, z, y, Colors.BLACKSTONE);
            case "minecraft:chain" -> ChainBuilder.build(x, z, y, this);
            case "minecraft:barrel" -> BarrelBuilder.build(x, z, y, this);
            case "minecraft:hay_block" -> HayBuilder.build(x, z, y, this);
            case "minecraft:lantern" -> LanternBuilder.build(x, z, y, this);
            case "minecraft:furnace" -> FurnaceBuilder.build(x, z, y, this);
            case "minecraft:quartz_block" -> CubeBuilder.build(x, z, y, Colors.QUARTZ);
            case "minecraft:prismarine_bricks" -> CubeBuilder.build(x, z, y, Colors.PRISMARINE);
            case "minecraft:torch" -> TorchBuilder.build(x, z, y);
            case "minecraft:wall_torch" -> WallTorchBuilder.build(x, z, y, this);
            case "minecraft:netherrack" -> CubeBuilder.build(x, z, y, Colors.NETHERRACK);
            case "minecraft:cactus" -> CactusBuilder.build(x, z, y);
            case "minecraft:prismarine" -> CubeBuilder.build(x, z, y, Colors.PRISMARINE);
            case "minecraft:dark_prismarine" -> CubeBuilder.build(x, z, y, Colors.DARK_PRISMARINE);
            case "minecraft:ladder" -> LadderBuilder.build(x, z, y, this);
            case "minecraft:ice" -> CubeBuilder.build(x, z, y, Colors.ICE);
            case "minecraft:blue_ice" -> CubeBuilder.build(x, z, y, Colors.BLUE_ICE);
            case "minecraft:packed_ice" -> CubeBuilder.build(x, z, y, Colors.PACKED_ICE);
            case "minecraft:snow_block" -> CubeBuilder.build(x, z, y, Colors.SNOW);
            case "minecraft:nether_portal" -> PortalBuilder.build(x, z, y, Colors.NETHER_PORTAL, this);
            case "minecraft:chest" -> ChestBuilder.build(x, z, y, properties);
            case "minecraft:grindstone" -> GrindstoneBuilder.build(x, z, y, this);
            case "minecraft:sponge", "minecraft:wet_sponge" -> CubeBuilder.build(x, z, y, Colors.SPONGE);
            case "minecraft:dispenser", "minecraft:observer" -> {
                List<Brick> bricks = CageBuilder.build(x, z, y, Colors.FURNACE_CAGE);
                bricks.addAll(FurnaceBuilder.topbot(x, z, y));
                bricks.addAll(FurnaceBuilder.sides(x, z, y, Facing.NORTH, false));
                yield bricks;
            }

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
        if (blockType.contains("slab") ||
            blockType.contains("stairs") ||
            blockType.contains("fence") ||
            blockType.contains("trapdoor") ||
            blockType.contains("glass") ||
            blockType.contains("button") ||
            (blockType.contains("bed") && !blockType.equals("bedrock")) ||
            blockType.contains("wall") ||
            blockType.contains("torch") ||
            blockType.contains("air") ||
            blockType.contains("sign") ||
            blockType.contains("pressure_plate") ||
            blockType.contains("door") ||
            blockType.contains("sapling") ||
            blockType.contains("bush") ||
            blockType.contains("flower") ||
            blockType.contains("tulip") ||
            blockType.contains("banner")) {
            return true;
        }

        return switch(blockType) {
            case
                 "minecraft:lantern",
                 "minecraft:sea_lantern",
                 "minecraft:blue_ice",
                 "minecraft:red_carpet",
                 "minecraft:white_carpet",
                 "minecraft:glass",
                 "minecraft:grass",
                 "minecraft:grass_path",
                 "minecraft:carrots",
                 "minecraft:tall_grass",
                 "minecraft:fern",
                 "minecraft:large_fern",
                 "minecraft:grindstone",
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
                 "minecraft:wheat",
                 "minecraft:potatoes",
                 "minecraft:peony",
                 "minecraft:pumpkin_stem",
                 "minecraft:lilac",
                 "minecraft:lily_of_the_valley",
                 "minecraft:oxeye_daisy",
                 "minecraft:brown_mushroom",
                 "minecraft:red_mushroom",
                 "minecraft:repeater",
                 "minecraft:redstone_wire",
                 "minecraft:white_wall_banner",
                 "minecraft:rail",
                 "minecraft:hopper",
                 "minecraft:powered_rail",
                 "minecraft:detector_rail",
                 "minecraft:snow",
                 "minecraft:water",
                 "minecraft:vine",
                 "minecraft:potted_oxeye_daisy",
                 "minecraft:kelp_plant",
                 "minecraft:cactus",
                 "minecraft:nether_portal",
                 "minecraft:chain",
                 "minecraft:bell",
                 "minecraft:ice",
                 "minecraft:bamboo",
                 "minecraft:iron_bars",
                 "minecraft:campfire",
                 "minecraft:fire",
                 "minecraft:lava" -> true;
            default -> false;
        };
    }

}
