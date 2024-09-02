package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.TFCDecoBlockEntities;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.datagen.TFCDecoData;
import mrthomas20121.tfc_decoration.fluid.TFCDecoFluids;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.minecraft.client.resources.model.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(TFCDecoration.mod_id)
public class TFCDecoration {

	public static final String mod_id = "tfc_decoration";

	public static final Logger LOGGER = LogManager.getLogger();

	public static Map<String, Material> BED_MATERIAL_MAP = new HashMap<>();

	public TFCDecoration() {

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		TFCDecoBlocks.BLOCKS.register(bus);
		TFCDecoItems.ITEMS.register(bus);
		TFCDecoBlockEntities.BLOCK_ENTITIES.register(bus);
		TFCDecoFluids.FLUIDS.register(bus);
		TFCDecoFluids.FLUID_TYPES.register(bus);
		CreativeTabRegistry.CREATIVE_TABS.register(bus);

		if(ModList.get().isLoaded("afc")) {
			bus.addListener(TFCDecoData::datagen);
		}
	}
}
