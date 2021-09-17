package mrthomas20121.tfc_decoration.proxy;

import mrthomas20121.tfc_decoration.TFCDecoration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod.EventBusSubscriber(modid = TFCDecoration.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {}

    public void init(FMLInitializationEvent e) {}

    public void postInit(FMLPostInitializationEvent e) {}

}
