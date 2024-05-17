package mrthomas20121.tfc_decoration.datagen.tfc;

import com.google.gson.JsonObject;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.minecraft.world.item.crafting.Ingredient;

public record ItemSize(Ingredient ingredient, Size size, Weight weight) implements TFCData {

    public JsonObject toJson() {

        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("size", this.size.name);
        object.addProperty("weight", this.weight.name);

        return object;
    }

    @Override
    public ID getID() {
        return ID.ITEM_SIZE;
    }
}
