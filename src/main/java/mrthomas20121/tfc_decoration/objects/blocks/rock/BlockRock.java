package mrthomas20121.tfc_decoration.objects.blocks.rock;

import mrthomas20121.tfc_decoration.api.ModTypes;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.client.TFCSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

public class BlockRock extends Block implements IItemSize
{
    public final ModTypes.RockType type;

    public static BlockRock create(Rock rock, ModTypes.RockType rockTy)
    {
        return new BlockRock(rock, rockTy);
    }

    public BlockRock(Rock rock, ModTypes.RockType rockType)
    {
        super(Material.ROCK);
        this.type = rockType;
        this.setSoundType(rockType.getSoundType());
        this.setHardness(rockType.getHardness());
        if(rockType.getName().equals("raw_mud")) {
            this.setHarvestLevel("pickaxe", 0);
        }
        else {
            this.setHarvestLevel("pickaxe", 1);
        }
        ModTypes.addRock(rock, rockType,this);

        if(type.canFall())
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

    public ModTypes.RockType getType() {
        return type;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Random rand)
    {
        if (this.type.canFall() && rand.nextInt(16) == 0 && FallingBlockManager.shouldFall(world, pos, pos, state, false))
        {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (float) pos.getZ() + rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(state));
        }
    }

    public enum DecorationType {
        PILLAR(Material.ROCK, SoundType.STONE, 5, false),
        MOSSY_COBBLE(Material.ROCK, SoundType.STONE, 5, true),
        MOSSY_BRICKS(Material.ROCK, SoundType.STONE, 5, false),
        CRACKED_BRICKS(Material.ROCK, SoundType.STONE, 5, false),
        SANDSTONE(Material.ROCK, SoundType.STONE, 1, false),
        RAW_MUD(Material.ROCK, SoundType.STONE, 4, false),
        MUD_BRICKS(Material.ROCK, SoundType.STONE, 4, false),
        MUD_PILLAR(Material.ROCK, SoundType.STONE, 5, false),
        SANDSTONE_PILLAR(Material.ROCK, SoundType.STONE, 5, false),
        ROCKWOOL(Material.ROCK, SoundType.CLOTH, 5, false);

        private final Material material;
        private final SoundType soundType;
        private final int hardness;
        private final boolean fallable;

        DecorationType(Material material, SoundType soundType, int hardness, boolean isFallable)
        {
            this.soundType = soundType;
            this.material = material;
            this.hardness = hardness;
            this.fallable = isFallable;
        }

        public Material getMaterial() {
            return material;
        }

        public SoundType getSoundType() {
            return soundType;
        }

        public boolean isFallable() {
            return fallable;
        }

        public int getHardness() {
            return hardness;
        }
    }
}
