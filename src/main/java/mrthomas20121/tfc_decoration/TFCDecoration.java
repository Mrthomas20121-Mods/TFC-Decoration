package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import mrthomas20121.tfc_decoration.item.TFCDecItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TFCDecoration.mod_id)
public class TFCDecoration {

	public static final String mod_id = "tfc_decoration";

	public TFCDecoration() {
		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.register(TFCDecBlocks.BLOCKS);
		bus.register(TFCDecItems.ITEMS);
	}
}
