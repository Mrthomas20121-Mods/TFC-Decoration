package mrthomas20121.tfc_decoration.api;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;

public enum DecoDyeColor implements StringRepresentable {
    TERRACOTTA_WHITE(MapColor.TERRACOTTA_WHITE),
    TERRACOTTA_ORANGE(MapColor.TERRACOTTA_ORANGE),
    TERRACOTTA_MAGENTA(MapColor.TERRACOTTA_MAGENTA),
    TERRACOTTA_LIGHT_BLUE(MapColor.TERRACOTTA_LIGHT_BLUE),
    TERRACOTTA_YELLOW(MapColor.TERRACOTTA_YELLOW),
    TERRACOTTA_LIME(MapColor.TERRACOTTA_LIGHT_GREEN),
    TERRACOTTA_PINK(MapColor.TERRACOTTA_PINK),
    TERRACOTTA_GRAY(MapColor.TERRACOTTA_GRAY),
    TERRACOTTA_LIGHT_GRAY(MapColor.TERRACOTTA_LIGHT_GRAY),
    TERRACOTTA_CYAN(MapColor.TERRACOTTA_CYAN),
    TERRACOTTA_PURPLE(MapColor.TERRACOTTA_PURPLE),
    TERRACOTTA_BLUE(MapColor.TERRACOTTA_BLUE),
    TERRACOTTA_BROWN(MapColor.TERRACOTTA_BROWN),
    TERRACOTTA_GREEN(MapColor.TERRACOTTA_GREEN),
    TERRACOTTA_RED(MapColor.TERRACOTTA_RED),
    TERRACOTTA_BLACK(MapColor.TERRACOTTA_BLACK);

    public static DecoDyeColor[] VALUES = values();

    private final MapColor color;
    private final String serializedName;

    DecoDyeColor(MapColor color) {
        this.color = color;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
    }

    public MapColor getMapColor() {
        return this.color;
    }

    @Override
    public String getSerializedName() {
        return this.serializedName;
    }
}
