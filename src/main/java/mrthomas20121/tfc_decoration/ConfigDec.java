package mrthomas20121.tfc_decoration;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TFCDecoration.MODID)
public class ConfigDec {

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TFCDecoration.MODID))
        {
            ConfigManager.sync(TFCDecoration.MODID, Config.Type.INSTANCE);
        }
    }

    @Config(modid = TFCDecoration.MODID, name = "TFC Decoration")
    public static class ConfigGeneral {

        @Config.RangeInt(min = 2, max = 100)
        @Config.Comment("TFC Hours it take to turn Wet Mud into Mud")
        @Config.LangKey("config."+TFCDecoration.MODID+".time")
        @Config.RequiresMcRestart
        public static int time = 12;
    }
}
