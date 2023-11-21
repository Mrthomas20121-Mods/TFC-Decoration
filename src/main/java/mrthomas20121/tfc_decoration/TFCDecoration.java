package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.fluid.DecoFluids;
import mrthomas20121.tfc_decoration.item.DecoItems;
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
		DecoFluids.FLUID_TYPES.register(bus);
		DecoFluids.FLUIDS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);
	}

	public void clientStuff() {

	}
}
