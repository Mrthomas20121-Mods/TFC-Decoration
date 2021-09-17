package mrthomas20121.tfc_decoration.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import mrthomas20121.tfc_decoration.api.ModTypes;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;

public class BlockFenceLogTFC extends BlockFence
{
    private static final Map<Tree, BlockFenceLogTFC> MAP = new HashMap<>();

    public static BlockFenceLogTFC get(Tree wood)
    {
        return MAP.get(wood);
    }

    public final Tree wood;

    public BlockFenceLogTFC(Tree wood, ModTypes.WoodType woodType)
    {
        super(Material.WOOD, Material.WOOD.getMaterialMapColor());
        this.wood = wood;
        setHarvestLevel("axe", 0);
        setHardness(2.0F); // match vanilla
        setResistance(15.0F);
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 20);

        // add the wood to the list
        ModTypes.addWood(wood, woodType, this);
    }
}
