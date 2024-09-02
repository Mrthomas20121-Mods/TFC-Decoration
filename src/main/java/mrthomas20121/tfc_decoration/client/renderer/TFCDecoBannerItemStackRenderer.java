package mrthomas20121.tfc_decoration.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import mrthomas20121.tfc_decoration.block.TFCDecoBannerBlock;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBannerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class TFCDecoBannerItemStackRenderer extends BlockEntityWithoutLevelRenderer
{
    TFCDecoBannerBlockEntity blockEntity = null;

    public TFCDecoBannerItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
        if (blockEntity == null) {
            blockEntity = new TFCDecoBannerBlockEntity(BlockPos.ZERO, Blocks.WHITE_BANNER.defaultBlockState());
        }
        blockEntity.fromItem(stack);
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof TFCDecoBannerBlock bannerBlock) {
                blockEntity.setColor(bannerBlock.getDyeColor());

                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, poseStack, buffer, pPackedLight, pPackedOverlay);
            }
        }
    }
}
