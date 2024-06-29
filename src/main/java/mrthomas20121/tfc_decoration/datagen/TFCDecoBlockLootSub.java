package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Set;

public class TFCDecoBlockLootSub extends BlockLootSubProvider {

    protected TFCDecoBlockLootSub() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(TFCDecoration.mod_id))
                .map(Map.Entry::getValue)
                .forEach(this::dropSelf);
    }
}
