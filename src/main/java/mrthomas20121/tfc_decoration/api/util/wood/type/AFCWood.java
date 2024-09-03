package mrthomas20121.tfc_decoration.api.util.wood.type;

import mrthomas20121.tfc_decoration.api.util.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Supplier;

public enum AFCWood implements ExtendedWood {

    BAOBAB(MapColor.WOOD),
    EUCALYPTUS(MapColor.WOOD),
    MAHOGANY(MapColor.WOOD),
    HEVEA(MapColor.WOOD),
    TUALANG(MapColor.WOOD),
    TEAK(MapColor.WOOD),
    CYPRESS(MapColor.WOOD),
    FIG(MapColor.WOOD),
    IRONWOOD(MapColor.WOOD),
    IPE(MapColor.WOOD);

    public static final AFCWood[] VALUES = values();

    private final MapColor woodColor;
    private final MapColor barkColor;

    private final String serializedName;


    AFCWood(MapColor woodColor) {
        this(woodColor, woodColor);
    }

    AFCWood(MapColor woodColor, MapColor barkColor) {
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        this.woodColor = woodColor;
        this.barkColor = barkColor;
    }

    public MapColor woodColor() {
        return woodColor;
    }

    @Override
    public MapColor barkColor() {
        return this.barkColor;
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
        return "afc";
    }
}
