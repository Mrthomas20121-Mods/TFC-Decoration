package mrthomas20121.tfc_decoration.objects.blocks.rock;

import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.client.TFCSounds;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Random;

public class BlockDecoration extends Block implements IItemSize
{
    public DecorationType type;
    public static HashMap<Rock, EnumMap<DecorationType, BlockDecoration>> table = new HashMap<>();


    public static BlockDecoration get(Rock rock, DecorationType decorationType)
    {
        return table.get(rock).get(decorationType);
    }

    public static BlockDecoration create(Rock rock, DecorationType decorationType)
    {
        return new BlockDecoration(rock, decorationType);
    }

    public BlockDecoration(Rock rock, DecorationType decorationType)
    {
        super(decorationType.getMaterial());
        this.type = decorationType;
        this.setSoundType(decorationType.getSoundType());
        this.setHardness(decorationType.getHardness());
        if(decorationType.equals(DecorationType.RAW_MUD)) {
            this.setHarvestLevel("pickaxe", 0);
        }
        else {
            this.setHarvestLevel("pickaxe", 1);
        }
        if(!table.containsKey(rock))
        {
            table.put(rock, new EnumMap<>(DecorationType.class));
        }
        table.get(rock).put(decorationType, this);

        if(type.isFallable())
        FallingBlockManager.registerFallable(this, new FallingBlockManager.Specification(true, () -> TFCSounds.ROCK_SLIDE_SHORT));
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.LIGHT;
    }

    public DecorationType getType() {
        return type;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.type.isFallable() && rand.nextInt(16) == 0 && FallingBlockManager.shouldFall(world, pos, pos, state, false))
        {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (float) pos.getZ() + rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(state));
        }
    }
}
