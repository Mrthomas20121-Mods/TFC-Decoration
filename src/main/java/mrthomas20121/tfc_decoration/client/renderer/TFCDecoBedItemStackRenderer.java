package mrthomas20121.tfc_decoration.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBedBlock;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBedBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class TFCDecoBedItemStackRenderer extends BlockEntityWithoutLevelRenderer
{
    TFCDecoBedBlockEntity blockEntity = null;

    public TFCDecoBedItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
        if (blockEntity == null) {
            blockEntity = new TFCDecoBedBlockEntity(BlockPos.ZERO, TFCDecoBlocks.BEDS.get(TFCDecoDyeColor.TERRACOTTA).get().defaultBlockState());
        }
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof TFCDecoBedBlock bedBlock) {
                blockEntity.setColor(bedBlock.getDecoDyeColor());

                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, poseStack, buffer, pPackedLight, pPackedOverlay);
            }
        }
    }
}
