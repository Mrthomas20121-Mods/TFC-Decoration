package mrthomas20121.tfc_decoration.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;

public class BlockFenceGateLogTFC extends BlockFenceGate
{
    private static final Map<Tree, BlockFenceGateLogTFC> MAP = new HashMap<>();

    public static BlockFenceGateLogTFC get(Tree wood)
    {
        return MAP.get(wood);
    }

    public final Tree wood;

    public BlockFenceGateLogTFC(Tree wood)
    {
        super(BlockPlanks.EnumType.OAK);
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setHarvestLevel("axe", 0);
        setHardness(2.0F); // match vanilla
        setResistance(15.0F);
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }
}
