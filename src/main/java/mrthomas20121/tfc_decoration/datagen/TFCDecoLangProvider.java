package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
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
        add("creative_tab.tfc_decoration.decorative_items", "TFC Decoration: Decorative Items");

        for(DecoWood wood: DecoWood.VALUES) {
            addBlock(DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood), capitalize(wood.getSerializedName() +" Vertical Planks"));
            addBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab(), capitalize(wood.getSerializedName() +" Vertical Planks Slab"));
            addBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair(), capitalize(wood.getSerializedName() +" Vertical Planks Stairs"));
            addBlock(DecoBlocks.WOOD_PLANKS.get(wood), capitalize(wood.getSerializedName() +" Planks"));
            addBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab(), capitalize(wood.getSerializedName() +" Planks Slab"));
            addBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair(), capitalize(wood.getSerializedName() +" Planks Stairs"));
        }
        for(Wood wood: Wood.VALUES) {
            addBlock(DecoBlocks.LOG_WALLS.get(wood), capitalize(wood.getSerializedName()+" Log Wall"));
            addBlock(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood), capitalize(wood.getSerializedName() +" Vertical Planks"));
            addBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab(), capitalize(wood.getSerializedName() +" Vertical Planks Slab"));
            addBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair(), capitalize(wood.getSerializedName() +" Vertical Planks Stairs"));
        }

        for(DecoWood wood: DecoWood.VALUES) {
            addItem(DecoItems.WOOD_LUMBERS.get(wood), capitalize(wood.getSerializedName()+" Lumber"));
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
