package mrthomas20121.tfc_decoration.api.blockType;

import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Locale;
import java.util.function.Function;

public enum WoodBlockType implements StringRepresentable {

    VERTICAL_PLANKS,
    WOOD_BEAM(woodType -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).mapColor(woodType.woodColor())));

    public static WoodBlockType[] VALUES = WoodBlockType.values();
    private final Function<BasicWood, Block> supp;

    private final String serializedName;

    WoodBlockType(Function<BasicWood, Block> supp) {
        this.supp = supp;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
    }

    WoodBlockType() {
        this((woodType) -> new ExtendedBlock(ExtendedProperties.of(Blocks.OAK_PLANKS).mapColor(woodType.woodColor()).flammableLikePlanks()));
    }

    @Override
    public String getSerializedName() {
        return serializedName;
    }

    public Block getBlock(BasicWood woodType) {
        return this.supp.apply(woodType);
    }
}
