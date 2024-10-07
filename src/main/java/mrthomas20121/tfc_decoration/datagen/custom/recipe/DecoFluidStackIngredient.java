package mrthomas20121.tfc_decoration.datagen.custom.recipe;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public record DecoFluidStackIngredient(String ingredient, int amount) {

    public static DecoFluidStackIngredient of(Fluid fluid, int amount) {
        return of(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)), amount);
    }

    public static DecoFluidStackIngredient of(ResourceLocation fluid, int amount) {
        return new DecoFluidStackIngredient(fluid.toString(), amount);
    }

    public static DecoFluidStackIngredient of(String fluid, int amount) {
        return new DecoFluidStackIngredient(fluid, amount);
    }

    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("ingredient", ingredient);
        obj.addProperty("amount", amount);
        return obj;
    }
}
