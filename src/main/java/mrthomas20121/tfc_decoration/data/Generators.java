package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TFCDecoration.mod_id, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Generators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        if(event.includeClient()) {
            registerClientGen(generator, fileHelper);
        }
        if(event.includeServer()) {
            registerServerGen(generator, fileHelper);
        }
    }

    public static void registerClientGen(DataGenerator generator, ExistingFileHelper fileHelper) {
        generator.addProvider(new BlockstateGen(generator, fileHelper));
        generator.addProvider(new DecLanguageProvider(generator));
        //generator.addProvider(new ItemModelGen(generator, fileHelper));
    }

    public static void registerServerGen(DataGenerator generator, ExistingFileHelper fileHelper) {
        generator.addProvider(new DecRecipeProvider(generator));
        generator.addProvider(new DecBlockTags(generator, fileHelper));
    }
}
