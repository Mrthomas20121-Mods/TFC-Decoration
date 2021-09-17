package mrthomas20121.tfc_decoration.worldgen;

import mrthomas20121.tfc_decoration.api.ModTypes;
import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockRock;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldgenMud {

    @SubscribeEvent
    public static void onPopulateChunkPost(PopulateChunkEvent.Post event) {
        if(event.getGenerator() instanceof ChunkGenTFC) {
            final BlockPos chunkBlockPos = new BlockPos(event.getChunkX() << 4, 0, event.getChunkZ() << 4);
            BlockPos start = event.getWorld().getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + Constants.RNG.nextInt(16), 0, 8 + Constants.RNG.nextInt(16))).add(0, -1, 0);
            genFromPoint(event.getWorld(), start);
        }
    }

    private static void genFromPoint(World world, BlockPos start)
    {
        Rock rock = ChunkDataTFC.getRockHeight(world, start);
        final int size = Constants.RNG.nextInt(10) == 0 ? 3 : 2;
        for (int x = -size; x <= size; x++)
        {
            for (int z = -size; z <= size; z++)
            {
                for (int y = -2; y <= 2; y++)
                {
                    if (x * x + z * z + y * y > size * size) continue;
                    BlockPos now = start.add(x, y, z);
                    if(BlocksTFC.isSand(world.getBlockState(now))) {
                        world.setBlockState(now, ModTypes.getRock(rock, ModTypes.RAW_MUD).getDefaultState());
                    }
                }
            }
        }
    }
}
