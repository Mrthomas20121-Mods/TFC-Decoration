package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Supplier;

public class TFCDecoBlockLootSub extends BlockLootSubProvider {

    protected TFCDecoBlockLootSub() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        TFCDecoBlocks.BLOCKS.getEntries().stream()
                .map(Supplier::get)
                .filter(block -> !(block instanceof LiquidBlock)) // filter to remove fluids
                .forEach(this::dropSelf);
    }

    @Override
    public @NotNull Iterable<Block> getKnownBlocks() {
        return TFCDecoBlocks.BLOCKS.getEntries().stream().map(Supplier::get).toList();
    }
}
