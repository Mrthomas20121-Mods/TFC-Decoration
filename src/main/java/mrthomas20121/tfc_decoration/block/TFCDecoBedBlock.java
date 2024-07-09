package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TFCDecoBedBlock extends BedBlock {

    private final DecoDyeColor color;

    public TFCDecoBedBlock(DecoDyeColor color, Properties properties) {
        super(DyeColor.WHITE, properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TFCDecoBedBlockEntity(this.color, pPos, pState);
    }

    public DecoDyeColor getDecoDyeColor() {
        return color;
    }
}
