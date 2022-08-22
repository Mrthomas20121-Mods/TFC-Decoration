package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.models.ModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;

public class ItemModelGen extends ItemModelProvider {


    public ItemModelGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Arrays.stream(Rock.values()).forEach(rock -> {
            Block pillar = TFCDecBlocks.PILLARS.get(rock).get();
            Block rockwool = TFCDecBlocks.ROCKWOOL.get(rock).get();
        });
    }

    private String name(Block block) {
        return "block/"+block.getRegistryName().getPath();
    }
}
