package mrthomas20121.tfc_decoration.datagen.custom;

import com.google.gson.JsonObject;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * DataProvider for TFC Data(fuel, metal heat, etc...)
 */
public abstract class UnitDataProvider implements DataProvider {

    private final PackOutput output;
    private final String modid;

    Map<String, JsonObject> data = new HashMap<>();

    public UnitDataProvider(PackOutput output, String modid) {

        this.output = output;
        this.modid = modid;
    }

    protected void fuel(String path, Ingredient ingredient, int duration, float temperature) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("duration", duration);
        object.addProperty("temperature", temperature);

        data.put("fuels/"+path+".json", object);
    }

    protected void item_size(String path, Ingredient ingredient, Size size, Weight weight) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("size", size.name);
        object.addProperty("weight", weight.name);

        data.put("item_sizes/"+path+".json", object);
    }

    protected void support(String path, Ingredient ingredient, int support_up, int support_down, int support_horizontal) {
        JsonObject object = new JsonObject();

        object.add("ingredient", ingredient.toJson());
        object.addProperty("support_up", support_up);
        object.addProperty("support_down", support_down);
        object.addProperty("support_horizontal", support_horizontal);

        data.put("supports/"+path+".json", object);
    }

    public abstract void addData();

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {

        this.addData();

        if(!this.data.isEmpty()) {
            return CompletableFuture.allOf(this.save(cache));
        }
        return CompletableFuture.allOf();
    }

    private CompletableFuture<?>[] save(CachedOutput cache) {

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for(Map.Entry<String, JsonObject> entry : this.data.entrySet()) {
            String key = entry.getKey();
            JsonObject value = entry.getValue();

            Path target = this.output
                    .getOutputFolder(PackOutput.Target.DATA_PACK)
                    .resolve(this.modid)
                    .resolve("tfc")
                    .resolve(key);

            futures.add(DataProvider.saveStable(cache, value, target));
        }

        return futures.toArray(new CompletableFuture<?>[] {});
    }
}
