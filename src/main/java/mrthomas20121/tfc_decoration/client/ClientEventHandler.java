package mrthomas20121.tfc_decoration.client;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBlockEntities;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.client.renderer.TFCDecoBedRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Predicate;

import static mrthomas20121.tfc_decoration.TFCDecoration.BED_MATERIAL_MAP;
import static mrthomas20121.tfc_decoration.TFCDecoration.mod_id;

@Mod.EventBusSubscriber(modid = mod_id, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TFCDecoBlockEntities.BED.get(), TFCDecoBedRenderer::new);
    }

    @SubscribeEvent
    @SuppressWarnings("deprecated")
    public static void clientSetup(FMLClientSetupEvent event) {

        for(TFCDecoDyeColor color: TFCDecoDyeColor.VALUES) {
            BED_MATERIAL_MAP.put(color.getSerializedName(), new Material(Sheets.BED_SHEET, new ResourceLocation(TFCDecoration.mod_id, "entity/bed/" + color.getSerializedName())));
        }

        event.enqueueWork(() -> {
            final RenderType solid = RenderType.solid();
            final RenderType cutout = RenderType.cutout();
            final RenderType cutoutMipped = RenderType.cutoutMipped();
            final RenderType translucent = RenderType.translucent();
            final Predicate<RenderType> ghostBlock = rt -> rt == cutoutMipped || rt == Sheets.translucentCullBlockSheet();

            for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
                ItemBlockRenderTypes.setRenderLayer(TFCDecoBlocks.STAINED_WATTLE.get(dyeColor).get(), ghostBlock);
                ItemBlockRenderTypes.setRenderLayer(TFCDecoBlocks.BEDS.get(dyeColor).get(), Sheets.bedSheet());
            }
        });
    }
}
