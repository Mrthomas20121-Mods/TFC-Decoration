package mrthomas20121.tfc_decoration.objects.blocks.wood;

import mrthomas20121.tfc_decoration.api.ModTypes;
import net.dries007.tfc.api.types.Tree;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;

public class BlockLadderTFC extends BlockLadder {

    public final Tree wood;

    public BlockLadderTFC(Tree tree, ModTypes.WoodType type) {
        this.wood = tree;
        ModTypes.addWood(tree, type, this);
        this.setSoundType(SoundType.LADDER);
    }
}
