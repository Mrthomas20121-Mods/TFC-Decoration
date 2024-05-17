package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.datagen.recipe.ShapedDamageInputRecipeBuilder;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class TFCDecoRecipeProvider extends RecipeProvider {

    public TFCDecoRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        for(Wood wood: Wood.VALUES) {
            stairs(consumer, DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get(), DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
            slab(consumer, RecipeCategory.BUILDING_BLOCKS, DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get(), DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
        }

        for(DecoWood wood: DecoWood.VALUES) {
            stairs(consumer, DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair().get(), DecoBlocks.WOOD_PLANKS.get(wood).get());
            stairs(consumer, DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair().get(), DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood).get());
            slab(consumer, RecipeCategory.BUILDING_BLOCKS, DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab().get(), DecoBlocks.WOOD_PLANKS.get(wood).get());
            slab(consumer, RecipeCategory.BUILDING_BLOCKS, DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab().get(), DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood).get());
        }

        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                if(type.equals(RockBlockType.ROCKWOOL_BRICKS)) {
                    tfc_bricks(consumer, DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(), DecoBlocks.ROCK_BLOCKS.get(rock).get(RockBlockType.ROCKWOOL).get());
                }
                slab(consumer, RecipeCategory.BUILDING_BLOCKS, DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(), DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                stairs(consumer, DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(), DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                walls(consumer, RecipeCategory.BUILDING_BLOCKS, DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(), DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
            }
        }

        for(Metal.Default metal: Metal.Default.values()) {

            if(metal.hasParts()) {
                Item sheet = TFCItems.METAL_ITEMS.get(metal).get(Metal.ItemType.SHEET).get();
                ShapedDamageInputRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, DecoBlocks.GRATES.get(metal).get())
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

    protected static void stairs(Consumer<FinishedRecipe> p_248880_, ItemLike output, ItemLike input) {
        stairBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/stairs/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }
}
