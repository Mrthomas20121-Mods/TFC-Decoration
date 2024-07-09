package mrthomas20121.tfc_decoration.item;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.fluid.DecoFluidId;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class TFCDecoItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TFCDecoration.mod_id);

    public static final Map<BasicWood, RegistryObject<Item>> WOOD_LUMBERS = Util.mapOfKeys(Util.getALLWoodTypes(), BasicWood::shouldGetPlanks, wood -> register("wood/%s_lumber".formatted(wood.name())));
    public static final Map<DecoDyeColor, RegistryObject<Item>> DYES = Helpers.mapOfKeys(DecoDyeColor.class, dye -> register("dye/%s".formatted(dye.name())));

    public static final Map<Rock, RegistryObject<Item>> ROCKWOOl_BRICK = Helpers.mapOfKeys(Rock.class, rock -> register("rockwool_brick/%s".formatted(rock.getSerializedName())));

    public static final Map<SupportMetal, RegistryObject<Item>> SUPPORTS = Helpers.mapOfKeys(SupportMetal.class, metal ->
            register("metal/support/" + metal.name(), () -> new StandingAndWallBlockItem(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get(), new Item.Properties(), Direction.DOWN))
    );

    public static final Map<DecoFluidId, RegistryObject<BucketItem>> FLUID_BUCKETS = DecoFluidId.mapOf(fluid ->
            register("bucket/" + fluid.name(), () -> new BucketItem(fluid.fluid(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)))
    );

    private static RegistryObject<Item> register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
