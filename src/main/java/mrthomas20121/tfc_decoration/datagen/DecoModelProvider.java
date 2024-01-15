package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class DecoModelProvider extends ItemModelProvider {

    public DecoModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(Wood wood: Wood.values()) {
            TFCWallBlock block = DecoBlocks.LOG_WALLS.get(wood).get();
            Block log = TFCBlocks.WOODS.get(wood).get(Wood.BlockType.LOG).get();
            itemWallBlock(block, log);
        }
    }

    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.textureTFC(this.blockName(baseBlock)));
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation textureTFC(String name) {
        return new ResourceLocation("tfc","block/" + name);
    }

    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }
}
