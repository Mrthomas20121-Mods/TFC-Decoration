package mrthomas20121.tfc_decoration.worldgen;

import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockDecoration;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldgenMud implements IWorldGenerator {

    public WorldgenMud() {
    }

    @Override
    public void generate(Random rng, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(chunkGenerator instanceof ChunkGenTFC) {
            final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 150, chunkZ << 4);
            BlockPos start = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + rng.nextInt(16), 0, 8 + rng.nextInt(16))).add(0, -1, 0);
            if (start.getY() > 155 && !BlocksTFC.isSoil(world.getBlockState(start))) return;

            int y = 1;
            Rock rock = ChunkDataTFC.getRockHeight(world, start);
            WorldGenMinable generator = new WorldGenMinable(BlockDecoration.get(rock, DecorationType.RAW_MUD).getDefaultState(), 20, state -> {
                return state.equals(BlockRockVariant.get(rock, Rock.Type.GRAVEL).getDefaultState()) || state.equals(BlockRockVariant.get(rock, Rock.Type.SAND).getDefaultState());
            });

            generator.generate(world, rng, start.add((rng.nextInt(2) + 1) * (rng.nextBoolean() ? 1 : -1), y + (rng.nextInt(2) + 1) * (rng.nextBoolean() ? 1 : -1), (rng.nextInt(2) + 1) * (rng.nextBoolean() ? 1 : -1)));
        }
    }
}
