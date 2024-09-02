package mrthomas20121.tfc_decoration.item;

import mrthomas20121.tfc_decoration.client.renderer.TFCDecoBannerItemStackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class TFCDecoBannerItem extends BannerItem
{
    public TFCDecoBannerItem(Block banner, Block wallBanner, Properties properties) {
        super(banner, wallBanner, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            final BlockEntityWithoutLevelRenderer myRenderer = new TFCDecoBannerItemStackRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return myRenderer;
            }
        });
    }
}
