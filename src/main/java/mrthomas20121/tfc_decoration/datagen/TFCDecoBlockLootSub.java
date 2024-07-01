package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class TFCDecoBlockLootSub extends BlockLootSubProvider {

    protected TFCDecoBlockLootSub() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        DecoBlocks.BLOCKS.getEntries().stream()
                .map(Supplier::get)
                .forEach(this::dropSelf);
    }

    @Override
    public @NotNull Iterable<Block> getKnownBlocks() {
        return DecoBlocks.BLOCKS.getEntries().stream().map(Supplier::get).toList();
    }
}
