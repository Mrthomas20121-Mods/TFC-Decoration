package mrthomas20121.tfc_decoration.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mrthomas20121.tfc_decoration.TFCDecoration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DecLootProvider extends LootTableProvider {

    public DecLootProvider(DataGenerator gen) {
        super(gen);
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables(){
        BlockLoot lootTables = new BlockLoot() {
            @Nonnull
            @Override
            protected Iterable<Block> getKnownBlocks(){
                return ForgeRegistries.BLOCKS.getValues().stream()
                        .filter(block -> block.getRegistryName().getNamespace().equals(TFCDecoration.mod_id))
                        .collect(Collectors.toList());
            }

            @Override
            protected void addTables(){
                ForgeRegistries.BLOCKS.getValues().stream()
                        .filter(block -> block.getRegistryName().getNamespace().equals(TFCDecoration.mod_id))
                        .forEach(this::dropSelf);
            }
        };

        return ImmutableList.of(Pair.of(() -> lootTables, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation,LootTable> map, ValidationContext validationtracker){
        map.forEach((a, b) -> LootTables.validate(validationtracker, a, b));
    }
}
