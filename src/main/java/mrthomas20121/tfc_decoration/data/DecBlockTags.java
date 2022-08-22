package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DecBlockTags extends BlockTagsProvider {
    public DecBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void addTags() {
        TagAppender<Block> pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE);
        TagAppender<Block> toughness = tag(TFCTags.Blocks.TOUGHNESS_2);
        TagAppender<Block> needTools = tag(TFCTags.Blocks.NEEDS_COPPER_TOOL);
        TagAppender<Block> forgeInsulation = tag(TFCTags.Blocks.FORGE_INSULATION);
        TagAppender<Block> bloomeryInsulation = tag(TFCTags.Blocks.BLOOMERY_INSULATION);
        TagAppender<Block> blastFurnaceInsulation = tag(TFCTags.Blocks.BLAST_FURNACE_INSULATION);
        Arrays.stream(Rock.values()).forEach(rock -> {
            pickaxe.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            toughness.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            needTools.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            forgeInsulation.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            bloomeryInsulation.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            blastFurnaceInsulation.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
        });
    }
}
