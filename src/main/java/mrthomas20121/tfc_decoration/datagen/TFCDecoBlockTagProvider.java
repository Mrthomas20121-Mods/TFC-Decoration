package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TFCDecoBlockTagProvider extends BlockTagsProvider {

    public TFCDecoBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TFCDecoration.mod_id,  existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                tag(TFCTags.Blocks.BLAST_FURNACE_INSULATION)
                        .add(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.BLOOMERY_INSULATION)
                        .add(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.FORGE_INSULATION)
                        .add(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.TOUGHNESS_2)
                        .add(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .add(
                                DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(),
                                DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                                DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                                DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get()
                        );
            }
        }
        for(Wood wood: Wood.VALUES) {
            TFCWallBlock block = DecoBlocks.LOG_WALLS.get(wood).get();
            tag(BlockTags.WALLS).add(block);
            tag(BlockTags.STAIRS)
                    .add(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
            tag(BlockTags.SLABS)
                    .add(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            tag(BlockTags.PLANKS).add(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
            tag(BlockTags.MINEABLE_WITH_AXE)
                    .add(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get())
                    .add(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get())
                    .add(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
        }

        for(DecoWood decoWood: DecoWood.VALUES) {
            tag(BlockTags.STAIRS)
                    .add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(decoWood).stair().get(), DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(decoWood).stair().get());
            tag(BlockTags.SLABS)
                    .add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(decoWood).slab().get(), DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(decoWood).slab().get());
            tag(BlockTags.PLANKS).add(DecoBlocks.WOOD_PLANKS.get(decoWood).get());
            tag(BlockTags.MINEABLE_WITH_AXE)
                    .add(DecoBlocks.WOOD_PLANKS.get(decoWood).get())
                    .add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(decoWood).slab().get())
                    .add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(decoWood).stair().get())
                    .add(DecoBlocks.VERTICAL_WOOD_PLANKS.get(decoWood).get())
                    .add(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(decoWood).slab().get())
                    .add(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(decoWood).stair().get());
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DecoBlocks.GRATES.get(metal).get());

                if(metal.metalTier().ordinal() < Metal.Tier.TIER_III.ordinal()) {
                    tag(TFCTags.Blocks.CAN_TRIGGER_COLLAPSE).add(DecoBlocks.GRATES.get(metal).get());
                }
            }
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            tag(TFCTags.Blocks.SUPPORT_BEAM).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
        }
    }
}
