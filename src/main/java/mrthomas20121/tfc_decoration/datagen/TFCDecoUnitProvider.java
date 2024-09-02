package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.datagen.custom.UnitDataProvider;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.capabilities.size.Weight;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

public class TFCDecoUnitProvider extends UnitDataProvider {

    public TFCDecoUnitProvider(PackOutput output) {
        super(output, TFCDecoration.mod_id);
    }

    @Override
    public void addData() {
        item_size("metal_supports", Ingredient.of(TFCDecoItemTags.METAL_SUPPORTS), Size.NORMAL, Weight.MEDIUM);

        int up = 2;
        int down = 2;
        int horizontal = 6;

        for(SupportMetal metal: SupportMetal.VALUES) {

            if(metal.equals(SupportMetal.RED_STEEL) || metal.equals(SupportMetal.BLUE_STEEL)) {
                up = 4;
                down = 4;
                horizontal = 8;
            }
            else if(metal.equals(SupportMetal.BLACK_STEEL)) {
                up = 3;
                down = 3;
                horizontal = 8;
            }
            else if(metal.equals(SupportMetal.STEEL)) {
                up = 3;
                down = 3;
            }

            support(metal.getSerializedName(), Ingredient.of(TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get()), up, down, horizontal);
        }
    }

    @Override
    public String getName() {
        return "TFC Decoration Unit Provider";
    }
}
