package mrthomas20121.tfc_decoration.datagen;

import com.therighthon.afc.common.blocks.AFCBlocks;
import com.therighthon.afc.common.blocks.AFCWood;
import com.therighthon.afc.common.items.AFCItems;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.datagen.custom.recipe.ShapedDamageInputRecipeBuilder;
import mrthomas20121.tfc_decoration.datagen.custom.recipe.TFCRecipeBuilder;
import mrthomas20121.tfc_decoration.fluid.TFCDecoFluids;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.ingredients.FluidIngredient;
import net.dries007.tfc.common.recipes.ingredients.FluidStackIngredient;
import net.dries007.tfc.common.recipes.ingredients.IngredientType;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Objects;
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

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.CONCRETE_POWDERS.get(dyeColor).get())
                    .pattern("DSS")
                    .pattern("SSG")
                    .pattern("GGG")
                    .define('D', TFCDecoItems.DYES.get(dyeColor).get())
                    .define('S', ItemTags.SAND)
                    .define('G', Tags.Items.GRAVEL)
                    .unlockedBy("has_sand", has(ItemTags.SAND))
                    .unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
                    .save(consumer, new ResourceLocation("tfc_decoration:crafting/color/"+ dyeColor.getSerializedName()+ "_concrete_powder"));

            TFCRecipeBuilder.simplePot(
                    consumer,
                    new ResourceLocation("tfc_decoration:pot/"+dyeColor.getSerializedName()+"_dye"),
                    Ingredient.of(TFCDecoItems.DYES.get(dyeColor).get()),
                    new FluidStackIngredient(new FluidIngredient(List.of(new IngredientType.ObjEntry<>(Fluids.WATER))), 1000),
                    new FluidStack(TFCDecoFluids.COLORED_FLUIDS.get(dyeColor).getSource(), 1000),
                    2000,
                    600);
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            Supplier<Item> lumber;

            if(wood.modID().equals("afc")) {
                lumber = AFCItems.LUMBER.get(AFCWood.valueOf(wood.name()));
            }
            else if(wood.modID().equals("tfc")) {
                lumber = TFCItems.LUMBER.get(Wood.valueOf(wood.name()));
            }
            else {
                lumber = TFCDecoItems.WOOD_LUMBERS.get(wood);
            }

            if(wood.isExtended()) {
                conditionalWalls(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get(), Objects.equals(wood.modID(), "afc") ? AFCBlocks.WOODS.get(AFCWood.valueOf(wood.name())).get(Wood.BlockType.PLANKS).get(): TFCBlocks.WOODS.get(Wood.valueOf(wood.name())).get(Wood.BlockType.PLANKS).get(), wood.modID());
            }

            for(WoodBlockType blockType: WoodBlockType.VALUES) {

                if(blockType.equals(WoodBlockType.DECORATIVE_BOOKSHELF)) {
                    condition(consumer, wood.modID(), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS.get(wood).get(blockType).get())
                            .define('l', lumber.get())
                            .define('b', ItemTags.BOOKSHELF_BOOKS)
                            .pattern("lll")
                            .pattern("bbb")
                            .pattern("lll")
                            .unlockedBy(getHasName(lumber.get()), has(lumber.get())),
                            "tfc_decoration:crafting/decorative_bookshelf/"+wood.getSerializedName()
                    );
                }
                else if(blockType.equals(WoodBlockType.WOOD_BEAM)) {
                    condition(consumer, wood.modID(), ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS.get(wood).get(blockType).get())
                                    .define('l', lumber.get())
                                    .pattern("l l")
                                    .pattern(" l ")
                                    .pattern("l l")
                                    .unlockedBy(getHasName(lumber.get()), has(lumber.get())),
                            "tfc_decoration:crafting/wood_beam/"+wood.getSerializedName()
                    );
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

                stairsConditional(consumer, wood.modID(), TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(), TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
                slabConditional(consumer, wood.modID(), RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get(), TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
            }
        }


        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                if(type.equals(RockBlockType.PILLAR)) {
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get())
                            .requires(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.SMOOTH).get())
                            .requires(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.SMOOTH).get())
                            .unlockedBy(getHasName(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.SMOOTH).get()),
                                    has(TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.SMOOTH).get()))
                            .save(consumer, new ResourceLocation("tfc_decoration:crafting/rock/%s/%s".formatted(rock.getSerializedName(), type.getSerializedName())));
                }
                else if(type.equals(RockBlockType.ROCKWOOL_BRICKS)) {
                    tfc_bricks(consumer, TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(RockBlockType.ROCKWOOL).get());
                }
                slabRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(), TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
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

    protected void slabRecipe(Consumer<FinishedRecipe> p_248880_, RecipeCategory p_251848_, ItemLike output, ItemLike input) {
        slabBuilder(p_251848_, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/slab/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected void walls(Consumer<FinishedRecipe> p_248880_, RecipeCategory p_251848_, ItemLike output, ItemLike input) {
        wallBuilder(p_251848_, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(p_248880_, "tfc_decoration:crafting/wall/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected void condition(Consumer<FinishedRecipe> consumer, String modid, RecipeBuilder builder, ResourceLocation loc) {
        ConditionalRecipe.builder().addCondition(new ModLoadedCondition(modid)).addRecipe(builder::save).build(consumer, loc);
    }

    protected void condition(Consumer<FinishedRecipe> consumer, String modid, RecipeBuilder builder, String loc) {
        ConditionalRecipe.builder().addCondition(new ModLoadedCondition(modid)).addRecipe(builder::save).build(consumer, new ResourceLocation(loc));
    }

    protected void conditionalWalls(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ItemLike output, ItemLike input, String name) {
        ConditionalRecipe.builder().addCondition(new ModLoadedCondition(name)).addRecipe(c -> wallBuilder(recipeCategory, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(c)).build(consumer, new ResourceLocation("tfc_decoration:crafting/wall/"+ RecipeBuilder.getDefaultRecipeId(input).getPath()));
    }

    protected void stairs(Consumer<FinishedRecipe> consumer, ItemLike output, ItemLike input) {
        stairBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)).save(consumer, "tfc_decoration:crafting/stairs/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected void stairsConditional(Consumer<FinishedRecipe> consumer, String modid, ItemLike output, ItemLike input) {
        condition(consumer, modid, stairBuilder(output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)), "tfc_decoration:crafting/stairs/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }

    protected void slabConditional(Consumer<FinishedRecipe> consumer, String modid, RecipeCategory p_251848_, ItemLike output, ItemLike input) {
        condition(consumer, modid, slabBuilder(p_251848_, output, Ingredient.of(input)).unlockedBy(getHasName(input), has(input)), "tfc_decoration:crafting/slab/"+ RecipeBuilder.getDefaultRecipeId(input).getPath());
    }
}
