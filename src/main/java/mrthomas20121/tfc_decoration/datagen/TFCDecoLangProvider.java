package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

public class TFCDecoLangProvider extends LanguageProvider {

    public TFCDecoLangProvider(PackOutput output) {
        super(output, TFCDecoration.mod_id, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("creative_tab.tfc_decoration.decorative_blocks", "TFC Decoration: Decorative Blocks");
        add("creative_tab.tfc_decoration.decorative_items", "TFC Decoration: Decorative Items");

        for(DecoWood wood: DecoWood.VALUES) {
            String serializedName = StringUtils.capitalize(wood.getSerializedName());
            addBlock(DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood), serializedName +" Vertical Planks");
            addBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab(), serializedName +" Vertical Planks Slab");
            addBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair(), serializedName +" Vertical Planks Stairs");
            addBlock(DecoBlocks.WOOD_PLANKS.get(wood), serializedName +" Planks");
            addBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab(), serializedName +" Planks Slab");
            addBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair(), serializedName +" Planks Stairs");
        }
        for(Wood wood: Wood.VALUES) {
            String serializedName = StringUtils.capitalize(wood.getSerializedName());
            addBlock(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood), serializedName +" Vertical Planks");
            addBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab(), serializedName +" Vertical Planks Slab");
            addBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair(), serializedName +" Vertical Planks Stairs");
        }

        for(DecoWood wood: DecoWood.VALUES) {
            addItem(DecoItems.WOOD_LUMBERS.get(wood), wood.getSerializedName()+" Lumber");
        }

        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                String rockName = rock.getSerializedName();
                String typeName = type.getSerializedName();
                addBlock(DecoBlocks.ROCK_BLOCKS.get(rock).get(type), rockName + " " + typeName);
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab(), rockName + " " + typeName + " Slab");
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair(), rockName + " " + typeName + " Stairs");
                addBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall(), rockName + " " + typeName + " Wall");
            }
        }

        for(Rock rock: Rock.VALUES) {
            addItem(DecoItems.ROCKWOOl_BRICK.get(rock), rock.getSerializedName()+" Brick");
        }
    }
}
