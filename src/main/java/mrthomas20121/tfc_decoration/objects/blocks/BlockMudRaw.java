package mrthomas20121.tfc_decoration.objects.blocks;

import mrthomas20121.tfc_decoration.objects.te.TeMud;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.types.Rock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;

public class BlockMudRaw extends BlockFallable implements ITileEntityProvider {

    private Rock rock;
    public BlockMudRaw(Rock rock, DecorationType decorationType)
    {
        super(rock, decorationType);
        this.rock = rock;
    }

    @ParametersAreNullableByDefault
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TeMud();
    }

    public Rock getRock() {
        return rock;
    }
}
