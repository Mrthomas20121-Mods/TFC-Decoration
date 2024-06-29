package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
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
                tag(TFCTags.Blocks.TOUGHNESS_3)
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

        for(BasicWood wood: Util.getALLWoodTypes()) {
            if(wood.shouldGetPlanks()) {
                tag(BlockTags.PLANKS).add(DecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get());
                tag(BlockTags.STAIRS).add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).stair().get());
                tag(BlockTags.SLABS).add(DecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).slab().get());
                tag(BlockTags.MINEABLE_WITH_AXE).add(
                        DecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).stair().get(),
                        DecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).slab().get(),
                        DecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get()
                );
            }

            if(wood.modID().equals("afc")) {

                if(wood.isExtended()) {
                    TFCWallBlock block = DecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                    tag(BlockTags.WALLS).addOptional(loc(block));
                    tag(BlockTags.MINEABLE_WITH_AXE).addOptional(loc(block));
                }

                for(WoodBlockType blockType: WoodBlockType.VALUES) {
                    tag(BlockTags.MINEABLE_WITH_AXE)
                            .addOptional(loc(DecoBlocks.WOODS.get(wood).get(blockType).get()))
                            .addOptional(loc(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get()))
                            .addOptional(loc(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()));

                    tag(BlockTags.STAIRS).addOptional(loc(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get()));
                    tag(BlockTags.SLABS).addOptional(loc(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()));
                }
            }
            else {
                if(wood.isExtended()) {
                    TFCWallBlock block = DecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                    tag(BlockTags.WALLS).add(block);
                    tag(BlockTags.MINEABLE_WITH_AXE).add(block);
                }
                for(WoodBlockType blockType: WoodBlockType.VALUES) {
                    tag(BlockTags.MINEABLE_WITH_AXE).add(
                            DecoBlocks.WOODS.get(wood).get(blockType).get(),
                            DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(),
                            DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()
                    );
                    tag(BlockTags.STAIRS).add(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get());
                    tag(BlockTags.SLABS).add(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get());
                }
            }

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
            tag(TFCTags.Blocks.SUPPORTS_LANDSLIDE).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(TFCTags.Blocks.TOUGHNESS_2).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(TFCTags.Blocks.SUPPORT_BEAM).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
        }
    }

    protected ResourceLocation loc(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
