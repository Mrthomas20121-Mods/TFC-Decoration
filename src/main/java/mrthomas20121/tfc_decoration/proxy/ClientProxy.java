package mrthomas20121.tfc_decoration.proxy;

import mrthomas20121.tfc_decoration.RegistryHandler;
import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockDecoration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

        for (Block block : RegistryHandler.getAllFenceGateBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());

        for (ItemBlock item : RegistryHandler.getItemBlocks())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

		for(Block block: RegistryHandler.getBlocks())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0 , new ModelResourceLocation(block.getRegistryName(), "normal"));
        }
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockMudBrick), 0 , new ModelResourceLocation(blockMudBrick.getRegistryName(), "normal"));
    }
}
