package mrthomas20121.tfc_decoration.item;

import mrthomas20121.tfc_decoration.TFCDecoration;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static net.dries007.tfc.common.TFCItemGroup.ROCK_STUFFS;

public class TFCDecItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCDecoration.mod_id);

//    public static final Map<SoilBlockType.Variant, RegistryObject<Item>> MUD_BALLS = Helpers.mapOfKeys(SoilBlockType.Variant.class, type ->
//            register("mud_ball/" + type.name(), ROCK_STUFFS)
//    );

    private static RegistryObject<Item> register(String name, CreativeModeTab group)
    {
        return register(name, () -> new Item(new Item.Properties().tab(group)));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
