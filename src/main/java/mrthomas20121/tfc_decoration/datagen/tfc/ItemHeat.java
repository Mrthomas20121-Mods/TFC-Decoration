package mrthomas20121.tfc_decoration.datagen.tfc;

import com.google.gson.JsonObject;
import net.minecraft.world.item.crafting.Ingredient;

public record ItemHeat(Ingredient ingredient, float heatCapacity, int forgingTemp, int weldingTemp) implements TFCData {

    public JsonObject toJson() {

        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("heat_capacity", this.heatCapacity);
        object.addProperty("forging_temperature", this.forgingTemp);
        object.addProperty("welding_temperature", this.weldingTemp);

        return object;
    }

    @Override
    public ID getID() {
        return ID.ITEM_HEAT;
    }
}
