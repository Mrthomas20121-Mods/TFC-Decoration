package mrthomas20121.tfc_decoration.datagen;

import com.google.common.base.Preconditions;
import com.therighthon.afc.common.blocks.AFCBlocks;
import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
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

        for(DecoDyeColor dyeColor: DecoDyeColor.VALUES) {
            item(DecoItems.DYES.get(dyeColor).get());

            itemBlock(DecoBlocks.WOOLS.get(dyeColor).get());
            itemBlock(DecoBlocks.WOOL_CARPETS.get(dyeColor).get());
            itemBlock(DecoBlocks.RAW_ALABASTER.get(dyeColor).get());
            itemBlock(DecoBlocks.POLISHED_ALABASTER.get(dyeColor).get());
            itemBlock(DecoBlocks.ALABASTER_BRICKS.get(dyeColor).get());
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {

            if(wood.shouldGetPlanks()) {
                itemBlock(DecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get());
                item(DecoItems.WOOD_LUMBERS.get((TFCDecoWood) wood).get());
            }

            if(wood.isExtended()) {
                TFCWallBlock block = DecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                Block log = wood.modID().equals("afc") ? AFCBlocks.WOODS.get(com.therighthon.afc.common.blocks.AFCWood.valueOf(wood.name())).get(Wood.BlockType.LOG).get() : TFCBlocks.WOODS.get(Wood.valueOf(wood.name())).get(Wood.BlockType.LOG).get();
                if(wood.modID().equals("afc")) {
                    itemWallBlockAFC(block, log);
                }
                else {
                    itemWallBlock(block, log);
                }
            }

            for(WoodBlockType type: WoodBlockType.VALUES) {
                itemBlock(DecoBlocks.WOODS.get(wood).get(type).get());
                itemBlock(DecoBlocks.WOODS_DECORATION.get(wood).get(type).slab().get());
                itemBlock(DecoBlocks.WOODS_DECORATION.get(wood).get(type).stair().get());
            }
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            verticalSupport(metal);
            horizontalSupport(metal);
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                itemBlock(DecoBlocks.GRATES.get(metal).get());

            }
        }
    }

    public void item(Item item) {
        this.withExistingParent(this.itemName(item), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + this.itemName(item)));
    }

    public void verticalSupport(SupportMetal metal) {
        this.withExistingParent("metal/vertical_support/"+metal.getSerializedName(), "tfc_decoration:block/metal/vertical_support/"+metal.getSerializedName()+"_inventory");
    }

    public void horizontalSupport(SupportMetal metal) {
        this.withExistingParent("metal/horizontal_support/"+metal.getSerializedName(), "tfc_decoration:block/metal/horizontal_support/"+metal.getSerializedName()+"_inventory");
    }

    public void itemWallBlock(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.textureTFC(this.blockName(baseBlock)));
    }

    public void itemWallBlockAFC(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.textureAFC(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, Block baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(this.blockName(baseBlock)));
    }

    public void itemWall(Block block, String baseBlock) {
        this.wallInventory(this.blockName(block), this.texture(baseBlock));
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), modLoc("block/"+this.blockName(block)));
    }

    public ItemModelBuilder getBuilder(String path) {
        Preconditions.checkNotNull(path, "Path must not be null");
        ResourceLocation outputLoc = extendWithFolder(path.contains(":") ? new ResourceLocation(path) : new ResourceLocation(modid, path));
        this.existingFileHelper.trackGenerated(outputLoc, MODEL);
        return generatedModels.computeIfAbsent(outputLoc, factory);
    }

    private ResourceLocation extendWithFolder(ResourceLocation rl) {
        return new ResourceLocation(rl.getNamespace(), folder + "/" + rl.getPath());
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation textureTFC(String name) {
        return new ResourceLocation("tfc","block/" + name);
    }

    protected ResourceLocation textureAFC(String name) {
        return new ResourceLocation("afc","block/" + name);
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
}
