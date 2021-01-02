package Minecraft;

import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

public enum Facing {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static Facing fromProps(CompoundTag properties) {
        String facing = ((StringTag) properties.get("facing")).getValue();
        return switch (facing) {
            case "north" -> NORTH;
            case "south" -> SOUTH;
            case "east" -> EAST;
            case "west" -> WEST;
            default -> throw new IllegalStateException("Unexpected value: " + facing);
        };
    }

}
