package mrthomas20121.tfc_decoration.datagen.custom.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dries007.tfc.common.recipes.ChiselRecipe;
import net.dries007.tfc.common.recipes.TFCRecipeSerializers;
import net.dries007.tfc.common.recipes.ingredients.FluidStackIngredient;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class TFCRecipeBuilder {

    public static void chiselStair(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, String block) {
        chisel(consumer, name, inputItem, block, ChiselRecipe.Mode.STAIR, null);
    }

    public static void chiselSlab(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, String block) {
        chisel(consumer, name, inputItem, block, ChiselRecipe.Mode.SLAB, block);
    }

    public static void chisel(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, String block, ChiselRecipe.Mode mode, @Nullable String extra) {
        if(extra == null) {
            consumer.accept(new Chisel(name, inputItem, block, mode));
        }
        else {
            consumer.accept(new ChiselExtra(name, inputItem, block, mode, extra));
        }
    }

    public static void simplePot(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid, int duration, int temp) {
        consumer.accept(new Pot(name, inputItem, inputFluid, outputFluid, duration, temp));
    }

    public static void sealedBarrel(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid, ItemStack output, int duration) {
        consumer.accept(new Sealed(name, inputItem, inputFluid, outputFluid, output, duration));
    }

    public static void sealedBarrel(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, ItemStack output, int duration) {
        consumer.accept(new Sealed(name, inputItem, inputFluid, FluidStack.EMPTY, output, duration));
    }

    public static void instantBarrel(Consumer<FinishedRecipe> consumer, ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid) {
        consumer.accept(new Instant(name, inputItem, inputFluid, outputFluid));
    }

    public static void instantBarrelFluid(Consumer<FinishedRecipe> consumer, ResourceLocation name, DecoFluidStackIngredient input1, DecoFluidStackIngredient input2, FluidStack outputFluid) {
        consumer.accept(new InstantFluid(name, input1, input2, outputFluid));
    }

    private record Chisel(ResourceLocation name, Ingredient inputItem, String block, ChiselRecipe.Mode mode) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("ingredient", inputItem.toJson());
            jsonObject.addProperty("result", block);
            jsonObject.addProperty("mode", mode.getSerializedName());
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.CHISEL.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private record ChiselExtra(ResourceLocation name, Ingredient inputItem, String block, ChiselRecipe.Mode mode, String extra) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("ingredient", inputItem.toJson());
            jsonObject.addProperty("result", block);
            jsonObject.addProperty("mode", mode.getSerializedName());

            JsonObject extraDrop = new JsonObject();

            extraDrop.addProperty("item", extra);
            jsonObject.add("extra_drop", extraDrop);
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.CHISEL.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private record Pot(ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid, int duration, int temperature) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            JsonArray arr = new JsonArray();

            arr.add(inputItem.toJson());

            jsonObject.add("ingredients", arr);
            jsonObject.add("fluid_ingredient", inputFluid.toJson());

            JsonObject fluidOutput = new JsonObject();
            fluidOutput.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(outputFluid.getFluid()).toString());
            fluidOutput.addProperty("amount", outputFluid.getAmount());
            jsonObject.add("output_fluid", fluidOutput);

            jsonObject.addProperty("duration", duration);
            jsonObject.addProperty("temperature", temperature);
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.POT_SIMPLE.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private record Sealed(ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid, ItemStack output, int duration) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            JsonElement ingredient = inputItem.toJson();
            JsonObject input = new JsonObject();
            input.add("ingredient", ingredient);
            jsonObject.add("input_item", input);
            jsonObject.add("input_fluid", inputFluid.toJson());

            if(!outputFluid.isEmpty()) {
                JsonObject fluidOutput = new JsonObject();
                fluidOutput.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(outputFluid.getFluid()).toString());
                fluidOutput.addProperty("amount", outputFluid.getAmount());
                jsonObject.add("output_fluid", fluidOutput);
            }

            if(output != null) {
                JsonObject item = new JsonObject();

                if(output.getCount() > 1) {
                    item.addProperty("count", output.getCount());
                }

                item.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());

                jsonObject.add("output_item", item);
            }

            jsonObject.addProperty("duration", duration);
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.SEALED_BARREL.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private record Instant(ResourceLocation name, Ingredient inputItem, DecoFluidStackIngredient inputFluid, FluidStack outputFluid) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("input_item", inputItem.toJson());
            jsonObject.add("input_fluid", inputFluid.toJson());

            JsonObject fluidOutput = new JsonObject();
            fluidOutput.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(outputFluid.getFluid()).toString());
            fluidOutput.addProperty("amount", outputFluid.getAmount());
            jsonObject.add("output_fluid", fluidOutput);
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.INSTANT_BARREL.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }

    private record InstantFluid(ResourceLocation name, DecoFluidStackIngredient input1, DecoFluidStackIngredient input2, FluidStack outputFluid) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.add("primary_fluid", input1.toJson());
            jsonObject.add("added_fluid", input2.toJson());

            JsonObject fluidOutput = new JsonObject();
            fluidOutput.addProperty("fluid", ForgeRegistries.FLUIDS.getKey(outputFluid.getFluid()).toString());
            fluidOutput.addProperty("amount", outputFluid.getAmount());
            jsonObject.add("output_fluid", fluidOutput);
        }

        @Override
        public ResourceLocation getId() {
            return this.name;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return TFCRecipeSerializers.INSTANT_FLUID_BARREL.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
