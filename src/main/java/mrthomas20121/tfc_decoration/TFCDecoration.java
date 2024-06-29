package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.datagen.*;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TFCDecoration.mod_id)
public class TFCDecoration {

	public static final String mod_id = "tfc_decoration";

	public static final Logger LOGGER = LogManager.getLogger();

	public TFCDecoration() {

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		DecoBlocks.BLOCKS.register(bus);
		DecoItems.ITEMS.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);

		bus.addListener(TFCDecoData::datagen);
	}
}
