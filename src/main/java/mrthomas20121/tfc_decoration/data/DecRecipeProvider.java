package mrthomas20121.tfc_decoration.data;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public class DecRecipeProvider extends RecipeProvider {

    public DecRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        Arrays.stream(Rock.values()).forEach(rock -> {
            Block PILLAR = TFCDecBlocks.PILLARS.get(rock).get();
            Block rockwool = TFCDecBlocks.ROCKWOOL.get(rock).get();
            Block raw = TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.RAW).get();
            Block chiseled = TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.CHISELED).get();
            ShapedRecipeBuilder.shaped(PILLAR, 2)
                    .define('#', raw)
                    .pattern("#")
                    .pattern("#")
                    .unlockedBy("has_%s_block".formatted(PILLAR.getRegistryName().getPath()), has(raw))
                    .group("pillar")
                    .save(consumer, new ResourceLocation(TFCDecoration.mod_id, "pillar/"+rock.name().toLowerCase()));

            ShapedRecipeBuilder.shaped(rockwool, 8)
                    .define('#', raw)
                    .define('!', TFCItems.BURLAP_CLOTH.get())
                    .pattern("###")
                    .pattern("#!#")
                    .pattern("###")
                    .unlockedBy("has_%s_block".formatted(PILLAR.getRegistryName().getPath()), has(raw))
                    .group("rockwool")
                    .save(consumer, new ResourceLocation(TFCDecoration.mod_id, "rockwool/"+rock.name().toLowerCase()));

            ShapedRecipeBuilder.shaped(TFCDecBlocks.POLISHED_FIRE_CLAY.get(), 8)
                    .define('#', TFCItems.FIRE_CLAY.get())
                    .define('!', chiseled)
                    .pattern("###")
                    .pattern("#!#")
                    .pattern("###")
                    .unlockedBy("has_polished_fire_clay_block", has(chiseled))
                    .group("rockwool")
                    .save(consumer, new ResourceLocation(TFCDecoration.mod_id, "polished_fire_clay/"+rock.name().toLowerCase()));
        });
    }
}
