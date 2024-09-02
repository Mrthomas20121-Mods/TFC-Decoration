package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBannerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TFCDecoWallBannerBlock extends WallBannerBlock
{
    private final TFCDecoDyeColor color;

    public TFCDecoWallBannerBlock(TFCDecoDyeColor color, Properties properties) {
        super(color.getAnalogue(), properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TFCDecoBannerBlockEntity(pos, state);
    }

    public TFCDecoDyeColor getDyeColor() {
        return color;
    }
}
