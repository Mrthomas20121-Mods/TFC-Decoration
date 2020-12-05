package mrthomas20121.tfc_decoration.objects.items;

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

    private Rock rock;

    private static Map<Rock, EnumMap<ItemType, ItemRockBase>> table = new HashMap<>();

    public static ItemRockBase get(Rock rock, ItemType type)
    {
        return table.get(rock).get(type);
    }

    public ItemRockBase(Rock rock, ItemType type)
    {
        this.rock = rock;

        if(!table.containsKey(rock))
        {
            table.put(rock, new EnumMap<>(ItemType.class));
        }
        table.get(rock).put(type, this);
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

    public enum ItemType {
        MUD_BRICK,
        MUD_BALL
    }
}
