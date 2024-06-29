package mrthomas20121.tfc_decoration.api.wood.type;

import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

public enum TFCWood implements ExtendedWood {

    ACACIA(MapColor.TERRACOTTA_ORANGE),
    ASH(MapColor.TERRACOTTA_PINK),
    ASPEN(MapColor.TERRACOTTA_GREEN),
    BIRCH(MapColor.COLOR_BROWN),
    BLACKWOOD(MapColor.COLOR_BLACK),
    CHESTNUT(MapColor.TERRACOTTA_RED),
    DOUGLAS_FIR(MapColor.TERRACOTTA_YELLOW),
    HICKORY(MapColor.TERRACOTTA_BROWN),
    KAPOK(MapColor.COLOR_PURPLE),
    MANGROVE(MapColor.COLOR_RED),
    MAPLE(MapColor.COLOR_ORANGE),
    OAK(MapColor.WOOD),
    PALM(MapColor.COLOR_ORANGE),
    PINE(MapColor.TERRACOTTA_GRAY),
    ROSEWOOD(MapColor.COLOR_RED),
    SEQUOIA(MapColor.TERRACOTTA_RED),
    SPRUCE(MapColor.TERRACOTTA_PINK),
    SYCAMORE(MapColor.COLOR_YELLOW),
    WHITE_CEDAR(MapColor.TERRACOTTA_WHITE),
    WILLOW(MapColor.COLOR_GREEN);

    public static final TFCWood[] VALUES = values();

    private final MapColor woodColor;
    private final MapColor barkColor;

    private final String serializedName;

    TFCWood(MapColor woodColor) {
        this(woodColor, woodColor);
    }

    TFCWood(MapColor woodColor, MapColor barkColor) {
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        this.woodColor = woodColor;
        this.barkColor = barkColor;
    }

    public MapColor woodColor() {
        return woodColor;
    }

    @Override
    public MapColor barkColor() {
        return barkColor;
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
    public String modID() {
        return "tfc";
    }
}
