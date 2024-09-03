package mrthomas20121.tfc_decoration.api.util.wood.type;

import mrthomas20121.tfc_decoration.api.util.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

public enum TFCDecoWood implements BasicWood {

    WHITE(MapColor.QUARTZ),
    ORANGE(MapColor.COLOR_ORANGE),
    MAGENTA(MapColor.COLOR_MAGENTA),
    LIGHT_BLUE(MapColor.COLOR_LIGHT_BLUE),
    YELLOW(MapColor.COLOR_YELLOW),
    LIME(MapColor.COLOR_LIGHT_GREEN),
    PINK(MapColor.COLOR_PINK),
    GRAY(MapColor.COLOR_GRAY),
    LIGHT_GRAY(MapColor.COLOR_LIGHT_GRAY),
    CYAN(MapColor.COLOR_CYAN),
    PURPLE(MapColor.COLOR_PURPLE),
    BLUE(MapColor.COLOR_BLUE),
    BROWN(MapColor.COLOR_BROWN),
    GREEN(MapColor.COLOR_GREEN),
    RED(MapColor.COLOR_RED),
    BLACK(MapColor.COLOR_BLACK),
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


    public static final TFCDecoWood[] VALUES = values();

    private final MapColor woodColor;

    private final String serializedName;

    TFCDecoWood(MapColor woodColor) {
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        this.woodColor = woodColor;
    }

    public MapColor woodColor() {
        return woodColor;
    }

    @Override
    public Supplier<Block> getBlock(WoodBlockType blockType) {
        return () -> null;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.serializedName;
    }

    @Override
    public boolean shouldGetPlanks() {
        return true;
    }
}
