package mrthomas20121.tfc_decoration.datagen;

import com.therighthon.afc.common.blocks.AFCBlocks;
import com.therighthon.afc.common.blocks.AFCWood;
import com.therighthon.afc.common.items.AFCItems;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.datagen.recipe.ShapedDamageInputRecipeBuilder;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TFCDecoRecipeProvider extends RecipeProvider {

    public TFCDecoRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            carpet(consumer, TFCDecoBlocks.WOOL_CARPETS.get(dyeColor).get(), TFCDecoBlocks.WOOLS.get(dyeColor).get());
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            Supplier<Item> lumber;
            Supplier<Block> planks;

            if(wood.modID().equals("afc")) {
                lumber = AFCItems.LUMBER.get(AFCWood.valueOf(wood.name()));
                planks = AFCBlocks.WOODS.get(AFCWood.valueOf(wood.name())).get(Wood.BlockType.PLANKS);
            }
            else if(wood.modID().equals("tfc")) {
                lumber = TFCItems.LUMBER.get(Wood.valueOf(wood.name()));
                planks = TFCBlocks.WOODS.get(Wood.valueOf(wood.name())).get(Wood.BlockType.PLANKS);
            }
            else {
                lumber = TFCDecoItems.WOOD_LUMBERS.get(wood);
                planks = TFCDecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood);
            }

            if(wood.isExtended()) {
                if(wood.modID().equals("afc")) {
                    conditionalWalls(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get(), AFCBlocks.WOODS.get(AFCWood.valueOf(wood.name())).get(Wood.BlockType.PLANKS).get(), wood.modID());
                }
                else {
                    walls(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get(), TFCBlocks.WOODS.get(Wood.valueOf(wood.name())).get(Wood.BlockType.PLANKS).get());
                }
            }

            for(WoodBlockType blockType: WoodBlockType.VALUES) {

                if(blockType.equals(WoodBlockType.WOOD_BEAM)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS.get(wood).get(blockType).get())
                            .define('l', lumber.get())
                            .pattern("l l")
                            .pattern(" l ")
                            .pattern("l l")
                            .unlockedBy(getHasName(lumber.get()), has(lumber.get()))
                            .save(consumer, "tfc_decoration:crafting/wood_beam/"+wood.getSerializedName());
                }
                else if(blockType.equals(WoodBlockType.VERTICAL_PLANKS)) {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS.get(wood).get(blockType).get())
                            .define('v', lumber.get())
                            .pattern(" v ")
                            .pattern("v v")
                            .pattern(" v ")
                            .unlockedBy("uncolored_planks", has(TFCDecoItemTags.UNCOLORED_PLANKS))
                            .save(consumer, "tfc_decoration:crafting/vertical_planks/"+wood.getSerializedName());
                }

                stairs(consumer, TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(), TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
                slab(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get(), TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
            }
        }

        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                if(type.equals(RockBlockType.ROCKWOOL_BRICKS)) {
                    tfc_bricks(consumer, TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(RockBlockType.ROCKWOOL).get());
                }
                slab(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                stairs(consumer, TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                walls(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
            }
        }

        for(Metal.Default metal: Metal.Default.values()) {

            if(metal.hasParts()) {
                Item sheet = TFCItems.METAL_ITEMS.get(metal).get(Metal.ItemType.SHEET).get();
                ShapedDamageInputRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.GRATES.get(metal).get())
                        .define('s', sheet)
                        .define('t', TFCTags.Items.HAMMERS)
                        .pattern("ts ")
                        .pattern("s s")
                        .pattern(" s ")
                        .unlockedBy(getHasName(sheet), has(sheet))
                        .save(consumer, "tfc_decoration:crafting/grates/"+metal.getSerializedName());
            }
        }
    }

    protected static void tfc_bricks(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output)
                .define('m', TFCItems.MORTAR.get())
                .define('r', input)
                .pattern("mrm")
                .pattern("rmr")
                .pattern("mrm")
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, "tfc_decoration:crafting/bricks/"+RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected static void slab(Consumer<FinishedRecipe> p_248880_, RecipeCategory p_251848_, ItemLike output, ItemLike input) {
        slabBuilder(p_251848_, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/slab/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected static void walls(Consumer<FinishedRecipe> p_248880_, RecipeCategory p_251848_, ItemLike output, ItemLike input) {
        wallBuilder(p_251848_, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/wall/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected static void conditionalWalls(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ItemLike output, ItemLike input, String name) {
        ConditionalRecipe.builder().addCondition(new ModLoadedCondition(name)).addRecipe(c -> wallBuilder(recipeCategory, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(c)).build(consumer, new ResourceLocation("tfc_decoration:crafting/wall/"+ RecipeBuilder.getDefaultRecipeId(input).getPath()));
    }

    protected static void stairs(Consumer<FinishedRecipe> p_248880_, ItemLike output, ItemLike input) {
        stairBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/stairs/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }
}
