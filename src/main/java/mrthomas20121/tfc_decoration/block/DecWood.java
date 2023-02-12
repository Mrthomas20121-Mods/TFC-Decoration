package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistryWood;
import net.dries007.tfc.world.feature.tree.TFCTreeGrower;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public enum DecWood implements RegistryWood {

    TEAK(false, MaterialColor.COLOR_BROWN, MaterialColor.TERRACOTTA_YELLOW, 7, 11);

    private final String serializedName;
    private final boolean conifer;
    private final MaterialColor woodColor;
    private final MaterialColor barkColor;
    private final TFCTreeGrower tree;
    private final int maxDecayDistance;
    private final int daysToGrow;

    DecWood(boolean conifer, MaterialColor woodColor, MaterialColor barkColor, int maxDecayDistance, int daysToGrow)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.conifer = conifer;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.tree = new TFCTreeGrower(Helpers.identifier("tree/" + serializedName), Helpers.identifier("tree/" + serializedName + "_large"));
        this.maxDecayDistance = maxDecayDistance;
        this.daysToGrow = daysToGrow;
    }

    @Override
    public MaterialColor woodColor() {
        return woodColor;
    }

    @Override
    public MaterialColor barkColor() {
        return barkColor;
    }

    @Override
    public TFCTreeGrower tree() {
        return this.tree;
    }

    @Override
    public int maxDecayDistance() {
        return maxDecayDistance;
    }

    @Override
    public int daysToGrow() {
        return daysToGrow;
    }

    public Supplier<Block> getBlock(Wood.BlockType type) {
        return (Supplier)((Map) TFCBlocks.WOODS.get(this)).get(type);
    }

    @Nonnull
    @Override
    public String getSerializedName() {
        return this.serializedName;
    }
}
