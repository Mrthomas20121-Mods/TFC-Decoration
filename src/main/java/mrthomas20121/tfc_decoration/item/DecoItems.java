package mrthomas20121.tfc_decoration.item;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.fluid.DecoFluids;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class DecoItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCDecoration.mod_id);

    public static final RegistryObject<BucketItem> CARAMEL_BUCKET = register("caramel_bucket", () -> new BucketItem(DecoFluids.MOLTEN_CARAMEL.source(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> CARAMEL = register("food/caramel");
    public static final RegistryObject<Item> CARAMEL_APPLE = register("food/caramel_apple");

    public static final Map<DecoWood, RegistryObject<Item>> WOOD_LUMBERS = Helpers.mapOfKeys(DecoWood.class, wood -> register("wood/%s_lumber".formatted(wood.name())));

    public static final Map<Rock, RegistryObject<Item>> ROCKWOOl_BRICK = Helpers.mapOfKeys(Rock.class, rock -> register("rockwool_brick/%s".formatted(rock.getSerializedName())));

    private static RegistryObject<Item> register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
