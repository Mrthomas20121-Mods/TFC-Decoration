package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.util.Util;
import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import mrthomas20121.tfc_decoration.api.util.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.util.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.fluid.DecoFluidId;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

public class TFCDecoLangProvider extends LanguageProvider {

    public TFCDecoLangProvider(PackOutput output) {
        super(output, TFCDecoration.mod_id, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creative_tab.tfc_decoration.wood_decorative_blocks", "TFC Decoration: Wood Blocks");
        add("creative_tab.tfc_decoration.rock_decorative_blocks", "TFC Decoration: Rock Blocks");
        add("creative_tab.tfc_decoration.metal_decorative_blocks", "TFC Decoration: Metal Blocks");
        add("creative_tab.tfc_decoration.dye_decorative_blocks", "TFC Decoration: Colored Blocks");
        add("creative_tab.tfc_decoration.decorative_items", "TFC Decoration: Decorative Items");

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            add("fluid_type.tfc_decoration."+dyeColor.getSerializedName()+"_dye", capitalize(dyeColor.getSerializedName())+" Dye");
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            String s = capitalize(dyeColor.getSerializedName());
            addBlock(TFCDecoBlocks.BEDS.get(dyeColor), s +" Bed");
            addBlock(TFCDecoBlocks.WOOLS.get(dyeColor), s +" Wool");
            addBlock(TFCDecoBlocks.WOOL_CARPETS.get(dyeColor), s +" Carpet");
            addBlock(TFCDecoBlocks.RAW_ALABASTER.get(dyeColor), s + " Raw Alabaster");
            addBlock(TFCDecoBlocks.POLISHED_ALABASTER.get(dyeColor), s + " Polished Alabaster");
            addBlock(TFCDecoBlocks.ALABASTER_BRICKS.get(dyeColor), s + " Alabaster Bricks");
            addBlock(TFCDecoBlocks.STAINED_WATTLE.get(dyeColor), s + " Wattle");
            addBlock(TFCDecoBlocks.CONCRETES.get(dyeColor), s+ " Concrete");
            addBlock(TFCDecoBlocks.CONCRETE_POWDERS.get(dyeColor), s+ " Concrete Powder");
            addBlock(TFCDecoBlocks.BANNERS.get(dyeColor), s+ " Banner");
            addItem(TFCDecoItems.DYES.get(dyeColor), s + " Dye");
            addItem(TFCDecoItems.FLUID_BUCKETS.get(DecoFluidId.asType(dyeColor)), s+" Dye");
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            String serializedName = wood.getSerializedName();
            if(wood.shouldGetPlanks()) {
                addItem(TFCDecoItems.WOOD_LUMBERS.get(wood), capitalize(serializedName +" Lumber"));
            }
            if(wood.isExtended()) {
                addBlock(TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood), capitalize(serializedName +" Log Wall"));
            }

            for(WoodBlockType blockType: WoodBlockType.VALUES) {
                String blockName = blockType.getSerializedName();
                addBlock(TFCDecoBlocks.WOODS.get(wood).get(blockType), "%s %s".formatted(capitalize(serializedName), capitalize(blockName)));
                addBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab(), "%s %s Slab".formatted(capitalize(serializedName), capitalize(blockName)));
                addBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair(), "%s %s Stairs".formatted(capitalize(serializedName), capitalize(blockName)));
            }
        }

        for(BasicRock rock: Util.getALLRockTypes()) {
            for(RockBlockType type: RockBlockType.VALUES) {
                String rockName = capitalize(rock.getSerializedName());
                String typeName = capitalize(type.getSerializedName());
                addBlock(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type), capitalize(rockName + " " + typeName));
                addBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab(), capitalize(rockName + " " + typeName + " Slab"));
                addBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair(), capitalize(rockName + " " + typeName + " Stairs"));
                addBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall(), capitalize(rockName + " " + typeName + " Wall"));
            }
        }

        for(BasicRock rock: Util.getALLRockTypes()) {
            addItem(TFCDecoItems.ROCKWOOl_BRICK.get(rock), capitalize(capitalize(rock.getSerializedName())+" Brick"));
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            addBlock(TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal), capitalize(metal.getSerializedName()+" Horizontal Support"));
            addBlock(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal), capitalize(metal.getSerializedName()+" Vertical Support"));
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                addBlock(TFCDecoBlocks.GRATES.get(metal), capitalize(metal.getSerializedName()+" Grate"));
            }
        }
    }

    public String capitalize(String input) {
        if(input.contains("_")) {
            String[] split = input.split("_");
            StringBuilder output = new StringBuilder();
            for(String s : split) {
                output.append(StringUtils.capitalize(s));
                output.append(" ");
            }
            return output.toString().trim();
        }
        return StringUtils.capitalize(input);
    }
}
