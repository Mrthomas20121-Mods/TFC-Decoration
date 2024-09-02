package mrthomas20121.tfc_decoration.block.entity;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBannerBlock;
import mrthomas20121.tfc_decoration.block.TFCDecoBlockEntities;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.block.TFCDecoWallBannerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TFCDecoBannerBlockEntity extends BannerBlockEntity
{
    private TFCDecoDyeColor baseColor;

    public TFCDecoBannerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        if (state.getBlock() instanceof TFCDecoBannerBlock tfcDecoBannerBlock) {
            this.baseColor = tfcDecoBannerBlock.getDyeColor();
        } else if (state.getBlock() instanceof TFCDecoWallBannerBlock tfcDecoBannerBlock) {
            this.baseColor = tfcDecoBannerBlock.getDyeColor();
        }
    }

    @Override
    public BlockEntityType<?> getType() {
        return TFCDecoBlockEntities.BANNER.get();
    }

    public void setColor(TFCDecoDyeColor color) {
        this.baseColor = color;
    }

    public TFCDecoDyeColor getColor() {
        return baseColor;
    }

    @Override
    public @NotNull ItemStack getItem() {
        ItemStack stack = new ItemStack(TFCDecoBlocks.BANNERS.get(this.baseColor).get());
        if (this.itemPatterns != null && !this.itemPatterns.isEmpty()) {
            CompoundTag $$1 = new CompoundTag();
            $$1.put("Patterns", this.itemPatterns.copy());
            BlockItem.setBlockEntityData(stack, this.getType(), $$1);
        }

        if (this.name != null) {
            stack.setHoverName(this.name);
        }

        return stack;
    }
}
