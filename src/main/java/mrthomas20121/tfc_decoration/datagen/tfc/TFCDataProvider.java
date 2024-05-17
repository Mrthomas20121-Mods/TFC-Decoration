package mrthomas20121.tfc_decoration.datagen.tfc;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class TFCDataProvider implements DataProvider {

    protected final PackOutput output;
    protected final PackOutput.PathProvider itemHeatPath;
    protected final PackOutput.PathProvider sizePath;
    protected final String modid;

    private final Map<ResourceLocation, TFCData> registeredData = new LinkedHashMap<>();

    public TFCDataProvider(PackOutput output, String modid) {
        this.output = output;
        this.itemHeatPath = output.createPathProvider(PackOutput.Target.DATA_PACK, "tfc/item_heat");
        this.sizePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "tfc/item_sizes");
        this.modid = modid;
    }

    public abstract void buildData();

    protected void add(ResourceLocation name, TFCData data) {
        this.registeredData.put(name, data);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {

        CompletableFuture<?>[] futures = new CompletableFuture<?>[2 + this.registeredData.size()];

        int i = 0;
        for(Map.Entry<ResourceLocation, TFCData> entry: registeredData.entrySet()) {
            futures[i++] = saveItemData(cache, getPath(entry.getValue()), entry.getValue().toJson(), entry.getKey());
        }

        return CompletableFuture.allOf(futures);
    }

    private PackOutput.PathProvider getPath(TFCData data) {
        if(data.getID().equals(TFCData.ID.ITEM_HEAT)) {
            return itemHeatPath;
        }
        else {
            return sizePath;
        }
    }

    private CompletableFuture<?> saveItemData(CachedOutput cache, PackOutput.PathProvider path, JsonObject stateJson, ResourceLocation name) {
        ResourceLocation resourceLocation = Preconditions.checkNotNull(name);
        return DataProvider.saveStable(cache, stateJson, path.json(resourceLocation));
    }

    @Override
    public String getName() {
        return "TFC Data";
    }
}
