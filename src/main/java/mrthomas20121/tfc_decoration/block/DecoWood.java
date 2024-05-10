package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum DecoWood implements StringRepresentable {

    black(MapColor.COLOR_BLACK),
    blue(MapColor.COLOR_BLUE),
    cyan(MapColor.COLOR_CYAN),
    gray(MapColor.COLOR_GRAY),
    green(MapColor.COLOR_GREEN),
    light_blue(MapColor.COLOR_LIGHT_BLUE),
    light_gray(MapColor.COLOR_LIGHT_GRAY),
    lime(MapColor.COLOR_LIGHT_GREEN),
    magenta(MapColor.COLOR_MAGENTA),
    orange(MapColor.COLOR_ORANGE),
    pink(MapColor.COLOR_PINK),
    purple(MapColor.COLOR_PURPLE),
    white(MapColor.QUARTZ),
    yellow(MapColor.COLOR_YELLOW),
    black_terracotta(MapColor.TERRACOTTA_BLACK),
    blue_terracotta(MapColor.TERRACOTTA_BLUE),
    cyan_terracotta(MapColor.TERRACOTTA_CYAN),
    gray_terracotta(MapColor.TERRACOTTA_GRAY),
    green_terracotta(MapColor.TERRACOTTA_GREEN),
    light_blue_terracotta(MapColor.TERRACOTTA_LIGHT_BLUE),
    light_gray_terracotta(MapColor.TERRACOTTA_LIGHT_GRAY),
    lime_terracotta(MapColor.TERRACOTTA_LIGHT_GREEN),
    magenta_terracotta(MapColor.TERRACOTTA_MAGENTA),
    orange_terracotta(MapColor.TERRACOTTA_ORANGE),
    pink_terracotta(MapColor.TERRACOTTA_PINK),
    purple_terracotta(MapColor.TERRACOTTA_PURPLE),
    white_terracotta(MapColor.TERRACOTTA_WHITE),
    yellow_terracotta(MapColor.TERRACOTTA_YELLOW);

    public static final DecoWood[] VALUES = values();

    private final MapColor color;

    DecoWood(MapColor color) {
        this.color = color;
    }

    public MapColor getColor() {
        return color;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
