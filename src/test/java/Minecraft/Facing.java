package Minecraft;

import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.StringTag;

public enum Facing {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    UP,
    DOWN;

    public static Facing fromProps(CompoundTag properties) {
        String facing = ((StringTag) properties.get("facing")).getValue();
        return switch (facing) {
            case "north" -> NORTH;
            case "south" -> SOUTH;
            case "east" -> EAST;
            case "west" -> WEST;
            case "up" -> UP;
            case "down" -> DOWN;
            default -> throw new IllegalStateException("Unexpected value: " + facing);
        };
    }

}
