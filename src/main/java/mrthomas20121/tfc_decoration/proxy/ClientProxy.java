package mrthomas20121.tfc_decoration.proxy;

import mrthomas20121.tfc_decoration.RegistryHandler;
import mrthomas20121.tfc_decoration.objects.blocks.BlockDecoration;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static mrthomas20121.tfc_decoration.RegistryHandler.blockMudBrick;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }
	
	
	
    @Override
    public void postInit(FMLPostInitializationEvent e) {
    
    }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
		for(BlockDecoration block: RegistryHandler.blocks)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0 , new ModelResourceLocation(block.getRegistryName(), "normal"));
        }
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMudBrick), 0 , new ModelResourceLocation(blockMudBrick.getRegistryName(), "normal"));
    }
}
