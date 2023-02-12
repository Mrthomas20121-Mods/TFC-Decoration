package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DecBlockTags extends BlockTagsProvider {

    public static TagKey<Block> ROCKWOOL = BlockTags.create(new ResourceLocation("tfc_decoration:rockwool"));

    public DecBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void addTags() {
        TagAppender<Block> pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TFCDecBlocks.POLISHED_FIRE_CLAY.get());
        TagAppender<Block> wall = tag(BlockTags.WALLS);
        TagAppender<Block> toughness = tag(TFCTags.Blocks.TOUGHNESS_2)
                .add(
                    TFCDecBlocks.POLISHED_FIRE_CLAY.get(),
                    TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.slab().get(),
                    TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.stair().get(),
                    TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.wall().get()
                );
        TagAppender<Block> needTools = tag(TFCTags.Blocks.NEEDS_COPPER_TOOL);
        tag(TFCTags.Blocks.NEEDS_BRONZE_TOOL).add(
                TFCDecBlocks.POLISHED_FIRE_CLAY.get(),
                TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.slab().get(),
                TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.stair().get(),
                TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.wall().get()
        );
        TagAppender<Block> forgeInsulation = tag(TFCTags.Blocks.FORGE_INSULATION);
        TagAppender<Block> bloomeryInsulation = tag(TFCTags.Blocks.BLOOMERY_INSULATION);
        tag(TFCTags.Blocks.BLAST_FURNACE_INSULATION).add(TFCDecBlocks.POLISHED_FIRE_CLAY.get());
        wall.add(TFCDecBlocks.POLISHED_FIRE_CLAY_DECORATIONS.wall().get());
        Arrays.stream(Rock.values()).forEach(rock -> {

            wall.add(TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get());
            pickaxe.add(
                    TFCDecBlocks.PILLARS.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).slab().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).stair().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get()
            );
            toughness.add(
                    TFCDecBlocks.PILLARS.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).slab().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).stair().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get()
            );
            needTools.add(
                    TFCDecBlocks.PILLARS.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL.get(rock).get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).slab().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).stair().get(),
                    TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get()
            );
            forgeInsulation.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
            bloomeryInsulation.add(TFCDecBlocks.PILLARS.get(rock).get(), TFCDecBlocks.ROCKWOOL.get(rock).get());
        });
    }
}
