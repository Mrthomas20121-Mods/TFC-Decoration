package mrthomas20121.tfc_decoration.objects.blocks;

import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.entity.EntityFallingBlockTFC;
import net.dries007.tfc.util.IFallingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

public class BlockFallable extends BlockDecoration implements IFallingBlock {

    public BlockFallable(Rock rock, DecorationType decorationType)
    {
        super(rock, decorationType);
    }

    public static boolean isSupportingSideBlock(IBlockState state)
    {
        return state.isNormalCube();
    }

    @Nullable
    @Override
    public BlockPos getFallablePos(World world, BlockPos pos, boolean ignoreSupportChecks)
    {
        if (type.isFallable() && shouldFall(world, pos, pos, ignoreSupportChecks))
        {
            return checkAreaClear(world, pos);
        }
        if (type.isFallable())
        {
            // Check if supported by at least two horizontals, or one on top
            if (isSupportingSideBlock(world.getBlockState(pos.up())))
            {
                return null;
            }

            EnumFacing[] faces = Arrays.stream(EnumFacing.HORIZONTALS)
                    .filter(x -> isSupportingSideBlock(world.getBlockState(pos.offset(x))))
                    .toArray(EnumFacing[]::new);

            if (faces.length >= 2)
            {
                return null;
            }

            // Check if it can fall
            IBlockState originalState = world.getBlockState(pos);
            faces = Arrays.stream(EnumFacing.HORIZONTALS)
                    .filter(x -> shouldFall(world, pos.offset(x), pos, ignoreSupportChecks) && IFallingBlock.canFallThrough(world, pos.offset(x), originalState.getMaterial()))
                    .toArray(EnumFacing[]::new);

            if (faces.length >= 1)
            {
                return checkAreaClear(world, pos.offset(faces[RANDOM.nextInt(faces.length)]));
            }
        }
        return null;
    }

    @Nullable
    private BlockPos checkAreaClear(World world, BlockPos pos) {
        if (!world.getEntitiesWithinAABB(EntityFallingBlockTFC.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))).isEmpty()) {
            world.scheduleUpdate(pos, this, 20);
            return null;
        } else {
            return pos;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (!this.type.isFallable()) return;
        if (rand.nextInt(16) != 0) return;
        if (shouldFall(worldIn, pos, pos))
        {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (float) pos.getZ() + rand.nextFloat();
            worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if (checkFalling(worldIn, pos, state))
        {
            onRockSlide(worldIn, pos);
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        if (checkFalling(worldIn, pos, state))
        {
            onRockSlide(worldIn, pos);
        }
    }

    protected void onRockSlide(World world, BlockPos pos)
    {
        switch (type)
        {
            case MOSSY_COBBLE:
                world.playSound(null, pos, TFCSounds.ROCK_SLIDE_SHORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }


}
