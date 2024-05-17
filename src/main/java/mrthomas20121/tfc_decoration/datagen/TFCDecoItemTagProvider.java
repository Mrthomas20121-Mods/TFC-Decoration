package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TFCDecoItemTagProvider extends ItemTagsProvider {

    public TFCDecoItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider p_256380_) {
        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                tag(TFCDecoItemTags.GRATES).add(DecoBlocks.GRATES.get(metal).get().asItem());
            }
        }
    }
}
