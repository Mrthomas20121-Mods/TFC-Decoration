package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.datagen.*;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TFCDecoration.mod_id)
public class TFCDecoration {

	public static final String mod_id = "tfc_decoration";

	public TFCDecoration() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		DecoBlocks.BLOCKS.register(bus);
		DecoItems.ITEMS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);

		bus.addListener(this::datagen);
	}

	private void datagen(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		dataGenerator.addProvider(event.includeClient(), new TFCDecoBlockStateProvider(dataGenerator.getPackOutput(), event.getExistingFileHelper()));
		dataGenerator.addProvider(event.includeClient(), new TFCDecoItemModelProvider(dataGenerator.getPackOutput(), event.getExistingFileHelper()));
		dataGenerator.addProvider(event.includeClient(), new TFCDecoLangProvider(dataGenerator.getPackOutput()));

		TFCDecoBlockTagProvider blockTagProvider = new TFCDecoBlockTagProvider(dataGenerator.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper());
		dataGenerator.addProvider(event.includeServer(), blockTagProvider);
		dataGenerator.addProvider(event.includeServer(), new TFCDecoItemTagProvider(dataGenerator.getPackOutput(), event.getLookupProvider(), blockTagProvider.contentsGetter(), event.getExistingFileHelper()));
		dataGenerator.addProvider(event.includeServer(), new TFCDecoRecipeProvider(dataGenerator.getPackOutput()));
	}
}
