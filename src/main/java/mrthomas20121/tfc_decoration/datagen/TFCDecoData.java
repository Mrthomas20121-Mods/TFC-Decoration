package mrthomas20121.tfc_decoration.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

public class TFCDecoData {

    public static void datagen(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(event.includeClient(), new TFCDecoBlockStateProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(event.includeClient(), new TFCDecoItemModelProvider(packOutput, existingFileHelper));
        dataGenerator.addProvider(event.includeClient(), new TFCDecoLangProvider(packOutput));

        TFCDecoBlockTagProvider blockTagProvider = new TFCDecoBlockTagProvider(packOutput, event.getLookupProvider(), existingFileHelper);
        dataGenerator.addProvider(event.includeServer(), blockTagProvider);
        dataGenerator.addProvider(event.includeServer(), new TFCDecoItemTagProvider(packOutput, event.getLookupProvider(), blockTagProvider.contentsGetter(), existingFileHelper));
        dataGenerator.addProvider(event.includeServer(), new TFCDecoRecipeProvider(packOutput));
        dataGenerator.addProvider(event.includeServer(), create(packOutput));
        dataGenerator.addProvider(event.includeServer(), new TFCDecoUnitProvider(packOutput));
    }

    private static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(TFCDecoBlockLootSub::new, LootContextParamSets.BLOCK)
        ));
    }
}
