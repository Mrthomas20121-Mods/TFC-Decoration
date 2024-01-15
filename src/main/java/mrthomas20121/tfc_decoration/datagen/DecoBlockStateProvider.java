package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DecoBlockStateProvider extends BlockStateProvider {

    public DecoBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TFCDecoration.mod_id, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(Wood wood: Wood.values()) {
            TFCWallBlock block = DecoBlocks.LOG_WALLS.get(wood).get();
            wallBlock(block, new ResourceLocation("tfc:block/wood/log/"+wood.getSerializedName()));
        }
    }
}
