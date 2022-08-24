package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;

public class BlockstateGen extends BlockStateProvider {
    public BlockstateGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TFCDecoration.mod_id, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Arrays.stream(Rock.values()).forEach(rock -> {
            axisBlock(TFCDecBlocks.PILLARS.get(rock).get(), new ResourceLocation(TFCDecoration.mod_id, "block/pillar/side/"+rock.name().toLowerCase()), new ResourceLocation(TFCDecoration.mod_id, "block/pillar/top/"+rock.name().toLowerCase()));
            simpleBlock(TFCDecBlocks.ROCKWOOL.get(rock).get());
        });

        simpleBlock(TFCDecBlocks.POLISHED_FIRE_CLAY.get());
    }

    // replace axisBlock(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) with our own because it doesn't work properly
    @Override
    public void axisBlock(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) {
        axisBlock(block, models().cubeColumn(name(block), side, end), models().cubeColumnHorizontal(name(block) + "_horizontal", side, end));
        itemModel(block);
    }

    @Override
    public void simpleBlock(Block block) {
        super.simpleBlock(block, cubeAll(block));
        itemModel(block);
    }

    public void itemModel(Block block) {
        itemModels().getBuilder(nameItem(block)).parent(new ModelFile.UncheckedModelFile(TFCDecoration.mod_id+":"+name(block)));
    }

    @Override
    public ModelFile cubeAll(Block block) {
        return models().cubeAll(name(block), blockTexture(block));
    }

    private String name(Block block) {
        return "block/"+block.getRegistryName().getPath();
    }

    private String nameItem(Block block) {
        return "item/"+block.getRegistryName().getPath();
    }
}
