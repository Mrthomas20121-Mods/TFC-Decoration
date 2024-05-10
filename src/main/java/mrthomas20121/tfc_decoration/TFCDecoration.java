package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.datagen.TFCDecoBlockStateProvider;
import mrthomas20121.tfc_decoration.datagen.TFCDecoBlockTagProvider;
import mrthomas20121.tfc_decoration.datagen.TFCDecoItemModelProvider;
import mrthomas20121.tfc_decoration.datagen.TFCDecoLangProvider;
import mrthomas20121.tfc_decoration.item.DecoItems;
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
		event.getGenerator().addProvider(event.includeClient(), new TFCDecoBlockStateProvider(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeClient(), new TFCDecoItemModelProvider(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeClient(), new TFCDecoLangProvider(event.getGenerator().getPackOutput()));

		event.getGenerator().addProvider(event.includeServer(), new TFCDecoBlockTagProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
	}
}
