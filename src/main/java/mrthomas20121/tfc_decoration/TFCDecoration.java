package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.datagen.DecoBlockStateProvider;
import mrthomas20121.tfc_decoration.datagen.DecoBlockTagProvider;
import mrthomas20121.tfc_decoration.datagen.DecoModelProvider;
import mrthomas20121.tfc_decoration.fluid.DecoFluids;
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
		DecoFluids.FLUID_TYPES.register(bus);
		DecoFluids.FLUIDS.register(bus);
		DecoBlocks.BLOCKS.register(bus);
		DecoItems.ITEMS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);

		bus.addListener(this::datagen);
	}

	private void datagen(GatherDataEvent event) {
		event.getGenerator().addProvider(event.includeClient(), new DecoBlockStateProvider(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(event.includeClient(), new DecoModelProvider(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));

		event.getGenerator().addProvider(event.includeServer(), new DecoBlockTagProvider(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
	}
}
