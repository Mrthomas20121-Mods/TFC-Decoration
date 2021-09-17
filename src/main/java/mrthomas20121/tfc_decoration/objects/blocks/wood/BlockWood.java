package mrthomas20121.tfc_decoration.objects.blocks.wood;

import mrthomas20121.tfc_decoration.api.ModTypes;
import net.dries007.tfc.api.types.Tree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockWood extends Block {

    public static Block create(ModTypes.WoodType type, Tree tree) {
        if(type.getName().equals("ladder")) {
            return new BlockLadderTFC(tree, type);
        }
        else if(type.getName().equals("fence_log")) {
            return new BlockFenceLogTFC(tree, type);
        }

        // table should not contain ladder or fence log
        return new BlockWood(tree, type);
    }

    private final Tree tree;

    public BlockWood(Tree tree, ModTypes.WoodType type) {
        super(Material.WOOD);
        this.tree = tree;

        ModTypes.addWood(tree, type, this);
    }

    public Tree getTree() {
        return tree;
    }
}
