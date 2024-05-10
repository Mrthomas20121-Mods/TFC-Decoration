package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class TFCDecoItemModelProvider extends ItemModelProvider {

    public TFCDecoItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TFCDecoration.mod_id, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(Rock rock: Rock.VALUES) {

            item(DecoItems.ROCKWOOl_BRICK.get(rock).get());

            for(RockBlockType type: RockBlockType.VALUES) {
                itemBlock(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                itemBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get());
                itemBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get());
                if(type.equals(RockBlockType.PILLAR)) {
                    itemWall(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(), "rock/pillar/side/"+rock.getSerializedName());
                }
                else {
                    itemWall(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(), DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                }
            }
        }

        for(Wood wood: Wood.VALUES) {

            // wall blocks
            TFCWallBlock block = DecoBlocks.LOG_WALLS.get(wood).get();
            Block log = TFCBlocks.WOODS.get(wood).get(Wood.BlockType.LOG).get();
            itemWallBlock(block, log);
            itemBlock(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
            itemBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            itemBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());

        }

        for(DecoWood wood: DecoWood.VALUES) {
            item(DecoItems.WOOD_LUMBERS.get(wood).get());
            itemBlock(DecoBlocks.WOOD_PLANKS.get(wood).get());
            itemBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            itemBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            verticalSupport(metal);
            horizontalSupport(metal);
        }

    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }

    public void verticalSupport(SupportMetal metal) {
        this.withExistingParent("item/metal/vertical_support/"+metal.getSerializedName(), "tfc_decoration:block/metal/vertical_support/"+metal.getSerializedName()+"_inventory");
    }

    public void horizontalSupport(SupportMetal metal) {
        this.withExistingParent("item/metal/horizontal_support/"+metal.getSerializedName(), "tfc_decoration:block/metal/horizontal_support/"+metal.getSerializedName()+"_inventory");
    }

    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.itemBlockName(block), this.textureTFC(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, Block baseBlock) {
        this.wallInventory(this.itemBlockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, String baseBlock) {
        this.wallInventory(this.itemBlockName(block), this.texture(baseBlock));
    }

    public void itemBlock(Block block) {
        this.withExistingParent("item/"+this.blockName(block), modLoc("block/"+this.blockName(block)));
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

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }

    public String itemBlockName(Block block) {
        return "item/" + blockName(block);
    }
}
