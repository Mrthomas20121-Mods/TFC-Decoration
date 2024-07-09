package mrthomas20121.tfc_decoration.datagen;

import com.therighthon.afc.common.blocks.AFCWood;
import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
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

        for(DecoDyeColor dyeColor: DecoDyeColor.VALUES) {
            tag(TFCDecoItemTags.COLORED_RAW_ALABASTER).add(TFCDecoBlocks.RAW_ALABASTER.get(dyeColor).get().asItem());
            tag(TFCDecoItemTags.COLORED_BRICKS_ALABASTER).add(TFCDecoBlocks.ALABASTER_BRICKS.get(dyeColor).get().asItem());
            tag(TFCDecoItemTags.COLORED_POLISHED_ALABASTER).add(TFCDecoBlocks.POLISHED_ALABASTER.get(dyeColor).get().asItem());
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                tag(TFCDecoItemTags.GRATES).add(TFCDecoBlocks.GRATES.get(metal).get().asItem());
            }
        }

        for(SupportMetal metal :SupportMetal.VALUES) {
            tag(TFCDecoItemTags.METAL_SUPPORTS).add(TFCDecoItems.SUPPORTS.get(metal).get());
        }

        for(Wood wood: Wood.VALUES) {
            tag(TFCDecoItemTags.UNCOLORED_PLANKS).add(TFCBlocks.WOODS.get(wood).get(Wood.BlockType.PLANKS).get().asItem());
        }

        for(AFCWood wood: AFCWood.VALUES) {
            tag(TFCDecoItemTags.UNCOLORED_PLANKS).addOptional(new ResourceLocation("afc:wood/planks/"+wood.getSerializedName()));
        }
    }
}
