package mrthomas20121.tfc_decoration.objects.items;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBaseDec extends ItemTFC {

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
            return Weight.VERY_LIGHT;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.VERY_SMALL;
    }
}
