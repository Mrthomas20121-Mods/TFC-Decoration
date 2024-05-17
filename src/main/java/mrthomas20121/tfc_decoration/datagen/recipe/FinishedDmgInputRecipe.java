package mrthomas20121.tfc_decoration.datagen.recipe;

import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class FinishedDmgInputRecipe extends ShapedRecipeBuilder.Result {
    public FinishedDmgInputRecipe(ResourceLocation p_273548_, Item p_273530_, int p_272738_, String p_273549_, CraftingBookCategory p_273500_, List<String> p_273744_, Map<Character, Ingredient> p_272991_, Advancement.Builder p_273260_, ResourceLocation p_273106_, boolean p_272862_) {
        super(p_273548_, p_273530_, p_272738_, p_273549_, p_273500_, p_273744_, p_272991_, p_273260_, p_273106_, p_272862_);
    }

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {

        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "minecraft:crafting_shaped");

        super.serializeRecipeData(recipe);

        jsonObject.add("recipe", recipe);
    }

    @Override
    public @NotNull RecipeSerializer<?> getType() {
        return TFCRecipeSerializers.DAMAGE_INPUT_SHAPED_CRAFTING.get();
    }
}
