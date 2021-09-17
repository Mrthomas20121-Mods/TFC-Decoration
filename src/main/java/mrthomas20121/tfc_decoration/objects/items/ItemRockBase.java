package mrthomas20121.tfc_decoration.objects.items;

import mrthomas20121.tfc_decoration.api.ModTypes;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ItemRockBase extends ItemTFC {

    private final Rock rock;

    public static ItemRockBase create(Rock rock, ModTypes.ItemRockType rockType) {
        return new ItemRockBase(rock, rockType);
    }

    public ItemRockBase(Rock rock, ModTypes.ItemRockType type)
    {
        this.rock = rock;
        ModTypes.addItemRock(rock, type, this);
    }

    public Rock getRock() {
        return rock;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.LIGHT;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }
}
