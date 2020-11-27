package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockDecoration;
import mrthomas20121.tfc_decoration.proxy.CommonProxy;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(modid = TFCDecoration.MODID, name = TFCDecoration.NAME, version = TFCDecoration.VERSION,
        dependencies = "required-after:forge@[14.23.5.2847,);"
        + "required-after:tfc@[1.4.0.149,);")
public class TFCDecoration
{
    @Mod.Instance
    public static TFCDecoration instance;
    public static final String MODID = "tfc_decoration";
    public static final String NAME = "TFC Decoration";
    public static final String VERSION = "1.0.2";

    public static Logger logger;

    @SidedProxy(serverSide = "mrthomas20121.tfc_decoration.proxy.CommonProxy", clientSide = "mrthomas20121.tfc_decoration.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        logger = event.getModLog();
        proxy.preInit(event);
   }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);

        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            OreDictionary.registerOre(DecorationType.MUD.name().toLowerCase(), BlockDecoration.get(rock, DecorationType.MUD));
        }
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
