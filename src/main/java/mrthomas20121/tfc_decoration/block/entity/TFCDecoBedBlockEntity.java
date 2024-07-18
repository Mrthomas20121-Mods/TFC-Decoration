package mrthomas20121.tfc_decoration.block.entity;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBedBlock;
import mrthomas20121.tfc_decoration.block.TFCDecoBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TFCDecoBedBlockEntity extends BlockEntity {
    private TFCDecoDyeColor color;

    public TFCDecoBedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TFCDecoBlockEntities.BED.get(), blockPos, blockState);
    }

    public TFCDecoBedBlockEntity(TFCDecoDyeColor colorIn, BlockPos blockPos, BlockState blockState) {
        this(blockPos, blockState);
        this.setColor(colorIn);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public TFCDecoDyeColor getColor() {
        if (this.color == null) {
            this.color = ((TFCDecoBedBlock) this.getBlockState().getBlock()).getDecoDyeColor();
        }

        return this.color;
    }

    public void setColor(TFCDecoDyeColor color) {
        this.color = color;
    }
}
