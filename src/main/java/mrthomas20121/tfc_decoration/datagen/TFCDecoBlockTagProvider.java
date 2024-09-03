package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.api.util.Util;
import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import mrthomas20121.tfc_decoration.api.util.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.util.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.util.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
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

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            tag(BlockTags.BEDS).add(TFCDecoBlocks.BEDS.get(dyeColor).get());
            tag(BlockTags.WOOL).add(TFCDecoBlocks.WOOLS.get(dyeColor).get());
            tag(BlockTags.WOOL_CARPETS).add(TFCDecoBlocks.WOOL_CARPETS.get(dyeColor).get());
            tag(TFCTags.Blocks.PET_SITS_ON).add(TFCDecoBlocks.WOOLS.get(dyeColor).get(), TFCDecoBlocks.WOOL_CARPETS.get(dyeColor).get());

            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TFCDecoBlocks.CONCRETE_POWDERS.get(dyeColor).get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TFCDecoBlocks.CONCRETES.get(dyeColor).get());
        }

        for(BasicRock rock: Util.getALLRockTypes()) {
            for(RockBlockType type: RockBlockType.VALUES) {
                tag(TFCTags.Blocks.BLAST_FURNACE_INSULATION)
                        .add(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.BLOOMERY_INSULATION)
                        .add(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.FORGE_INSULATION)
                        .add(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(TFCTags.Blocks.TOUGHNESS_3)
                        .add(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .add(
                                TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(),
                                TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                                TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                                TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get()
                        );
            }
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            if(wood.shouldGetPlanks()) {
                tag(BlockTags.PLANKS).add(TFCDecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get());
                tag(BlockTags.STAIRS).add(TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).stair().get());
                tag(BlockTags.SLABS).add(TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).slab().get());
                tag(BlockTags.MINEABLE_WITH_AXE).add(
                        TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).stair().get(),
                        TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get((TFCDecoWood) wood).slab().get(),
                        TFCDecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get()
                );
            }

            if(wood.modID().equals("afc")) {

                if(wood.isExtended()) {
                    TFCWallBlock block = TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                    tag(BlockTags.WALLS).addOptional(loc(block));
                    tag(BlockTags.MINEABLE_WITH_AXE).addOptional(loc(block));
                }

                for(WoodBlockType blockType: WoodBlockType.VALUES) {
                    tag(BlockTags.MINEABLE_WITH_AXE)
                            .addOptional(loc(TFCDecoBlocks.WOODS.get(wood).get(blockType).get()))
                            .addOptional(loc(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get()))
                            .addOptional(loc(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()));

                    tag(BlockTags.STAIRS).addOptional(loc(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get()));
                    tag(BlockTags.SLABS).addOptional(loc(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()));
                }
            }
            else {
                if(wood.isExtended()) {
                    TFCWallBlock block = TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                    tag(BlockTags.WALLS).add(block);
                    tag(BlockTags.MINEABLE_WITH_AXE).add(block);
                }
                for(WoodBlockType blockType: WoodBlockType.VALUES) {
                    tag(BlockTags.MINEABLE_WITH_AXE).add(
                            TFCDecoBlocks.WOODS.get(wood).get(blockType).get(),
                            TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(),
                            TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get()
                    );

                    if(blockType.equals(WoodBlockType.DECORATIVE_BOOKSHELF)) {
                        tag(Tags.Blocks.BOOKSHELVES)
                                .add(TFCDecoBlocks.WOODS.get(wood).get(blockType).get())
                                .add(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get())
                                .add(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get());
                    }

                    tag(BlockTags.STAIRS).add(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get());
                    tag(BlockTags.SLABS).add(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get());
                }
            }

        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TFCDecoBlocks.GRATES.get(metal).get());

                if(metal.metalTier().ordinal() < Metal.Tier.TIER_III.ordinal()) {
                    tag(TFCTags.Blocks.CAN_TRIGGER_COLLAPSE).add(TFCDecoBlocks.GRATES.get(metal).get());
                }
            }
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            tag(TFCTags.Blocks.SUPPORTS_LANDSLIDE).add(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(TFCTags.Blocks.TOUGHNESS_2).add(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(TFCTags.Blocks.SUPPORT_BEAM).add(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
        }
    }

    protected ResourceLocation loc(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
