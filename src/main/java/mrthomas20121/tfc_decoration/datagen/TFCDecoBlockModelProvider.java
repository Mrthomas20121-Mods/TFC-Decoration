package mrthomas20121.tfc_decoration.datagen;

import com.google.common.base.Preconditions;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TFCDecoBlockModelProvider extends BlockModelProvider {

    public TFCDecoBlockModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    public BlockModelBuilder getBuilder(String path) {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation outputLoc = extendWithFolder(path.contains(":") ? new ResourceLocation(path) : new ResourceLocation(modid, path));
        this.existingFileHelper.trackGenerated(outputLoc, MODEL);
        return generatedModels.computeIfAbsent(outputLoc, factory);
    }

    private ResourceLocation extendWithFolder(ResourceLocation rl) {
        return new ResourceLocation(rl.getNamespace(), folder + "/" + rl.getPath());
    }
}
