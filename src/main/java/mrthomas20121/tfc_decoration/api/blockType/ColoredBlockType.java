package mrthomas20121.tfc_decoration.api.blockType;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCCarpetBlock;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Locale;
import java.util.function.Function;

public enum ColoredBlockType implements StringRepresentable {

    WOOL,
    CARPET(dyeColor -> new TFCCarpetBlock(ExtendedProperties.of(Blocks.WHITE_CARPET).mapColor(dyeColor.getMapColor())));

    public static ColoredBlockType[] VALUES = ColoredBlockType.values();
    private final Function<TFCDecoDyeColor, Block> supp;

    private final String serializedName;

    ColoredBlockType(Function<TFCDecoDyeColor, Block> supp) {
        this.supp = supp;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
    }

    ColoredBlockType() {
        this((dyeColor) -> new ExtendedBlock(ExtendedProperties.of(Blocks.WHITE_WOOL).mapColor(dyeColor.getMapColor()).flammableLikeWool()));
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public Block getBlock(TFCDecoDyeColor dyeColor) {
        return this.supp.apply(dyeColor);
    }
}
