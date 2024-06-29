package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.item.DecoItems;
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

        for(DecoDyeColor dyeColor: DecoDyeColor.VALUES) {
            String s = capitalize(dyeColor.getSerializedName());
            addBlock(DecoBlocks.WOOLS.get(dyeColor), s +" Wool");
            addBlock(DecoBlocks.WOOL_CARPETS.get(dyeColor), s +" Carpet");
            addBlock(DecoBlocks.RAW_ALABASTER.get(dyeColor), s + " Raw Alabaster");
            addBlock(DecoBlocks.POLISHED_ALABASTER.get(dyeColor), s + " Polished Alabaster");
            addBlock(DecoBlocks.ALABASTER_BRICKS.get(dyeColor), s + " Alabaster Bricks");
            addItem(DecoItems.DYES.get(dyeColor), s + " Dye");
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            String serializedName = wood.getSerializedName();
            if(wood.shouldGetPlanks()) {
                addItem(DecoItems.WOOD_LUMBERS.get(wood), capitalize(serializedName +" Lumber"));
            }
            if(wood.isExtended()) {
                addBlock(DecoBlocks.LOG_WALLS.get((ExtendedWood) wood), capitalize(serializedName +" Log Wall"));
            }

            for(WoodBlockType blockType: WoodBlockType.VALUES) {
                String blockName = blockType.getSerializedName();
                addBlock(DecoBlocks.WOODS.get(wood).get(blockType), "%s %s".formatted(capitalize(serializedName), capitalize(blockName)));
                addBlock(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab(), "%s %s Slab".formatted(capitalize(serializedName), capitalize(blockName)));
                addBlock(DecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair(), "%s %s Stairs".formatted(capitalize(serializedName), capitalize(blockName)));
            }
        }

        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                String rockName = capitalize(rock.getSerializedName());
                String typeName = capitalize(type.getSerializedName());
                addBlock(DecoBlocks.ROCK_BLOCKS.get(rock).get(type), capitalize(rockName + " " + typeName));
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab(), capitalize(rockName + " " + typeName + " Slab"));
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair(), capitalize(rockName + " " + typeName + " Stairs"));
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall(), capitalize(rockName + " " + typeName + " Wall"));
            }
        }

        for(Rock rock: Rock.VALUES) {
            addItem(DecoItems.ROCKWOOl_BRICK.get(rock), capitalize(capitalize(rock.getSerializedName())+" Brick"));
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            addBlock(DecoBlocks.HORIZONTAL_SUPPORT.get(metal), capitalize(metal.getSerializedName()+" Horizontal Support"));
            addBlock(DecoBlocks.VERTICAL_SUPPORT.get(metal), capitalize(metal.getSerializedName()+" Vertical Support"));
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                addBlock(DecoBlocks.GRATES.get(metal), capitalize(metal.getSerializedName()+" Grate"));
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
