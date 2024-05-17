package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.*;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class DecoBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCDecoration.mod_id);

    public static final Map<Rock, Map<RockBlockType, RegistryObject<Block>>> ROCK_BLOCKS = Helpers.mapOfKeys(Rock.class, rock -> Helpers.mapOfKeys(RockBlockType.class, type -> register("rock/"+ type.getSerializedName() + "/" + rock.name(), type.getBlock(rock))));

    public static final Map<Rock, Map<RockBlockType, DecorationBlockRegistryObject>> ROCK_DECORATION_BLOCKS = Helpers.mapOfKeys(Rock.class, rock -> Helpers.mapOfKeys(RockBlockType.class, type -> new DecorationBlockRegistryObject(
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_slab"), () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f))),
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_stairs"), () -> new StairBlock(() -> ROCK_BLOCKS.get(rock).get(type).get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f))),
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_wall"), () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f)))
    )));

    public static final Map<DecoWood, RegistryObject<Block>> WOOD_PLANKS = Helpers.mapOfKeys(DecoWood.class, decoWood -> register("wood/planks/%s".formatted(decoWood), () -> new ExtendedBlock(ExtendedProperties.of().mapColor(decoWood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));
    public static final Map<DecoWood, RegistryObject<Block>> VERTICAL_WOOD_PLANKS = Helpers.mapOfKeys(DecoWood.class, decoWood -> register("wood/vertical_planks/%s".formatted(decoWood), () -> new ExtendedBlock(ExtendedProperties.of().mapColor(decoWood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));
    public static final Map<Wood, RegistryObject<Block>> VERTICAL_TFC_WOOD_PLANKS = Helpers.mapOfKeys(Wood.class, wood -> register("wood/vertical_planks/%s".formatted(wood), () -> new ExtendedBlock(ExtendedProperties.of().mapColor(wood.woodColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));

    public static final Map<Wood, RegistryObject<TFCWallBlock>> LOG_WALLS = Helpers.mapOfKeys(Wood.class, decoWood -> register("wood/%s_log_wall".formatted(decoWood), () -> new TFCWallBlock(ExtendedProperties.of().mapColor(decoWood.barkColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));

    public static final Map<DecoWood, WoodBlockRegistryObject> WOOD_PLANKS_DECORATIONS = Helpers.mapOfKeys(DecoWood.class, wood -> new WoodBlockRegistryObject(
            register(("wood/planks/" + wood.name() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.getColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
            register(("wood/planks/" + wood.name() + "_stairs"), () -> new TFCStairBlock(() -> WOOD_PLANKS.get(wood).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
    ));

    public static final Map<DecoWood, WoodBlockRegistryObject> VERTICAl_WOOD_PLANKS_DECORATIONS = Helpers.mapOfKeys(DecoWood.class, wood -> new WoodBlockRegistryObject(
            register(("wood/vertical_planks/" + wood.name() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.getColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
            register(("wood/vertical_planks/" + wood.name() + "_stairs"), () -> new TFCStairBlock(() -> VERTICAL_WOOD_PLANKS.get(wood).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
    ));

    public static final Map<Wood, WoodBlockRegistryObject> VERTICAl_TFC_WOOD_PLANKS_DECORATIONS = Helpers.mapOfKeys(Wood.class, wood -> new WoodBlockRegistryObject(
            register(("wood/vertical_planks/" + wood.name() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.woodColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
            register(("wood/vertical_planks/" + wood.name() + "_stairs"), () -> new TFCStairBlock(() -> VERTICAL_TFC_WOOD_PLANKS.get(wood).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.woodColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
    ));

    public static final Map<Metal.Default, RegistryObject<GrateBlock>> GRATES = Helpers.mapOfKeys(Metal.Default.class, Metal.Default::hasParts, metal -> register("metal/grate/" + metal.getSerializedName(), () -> new GrateBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(metal.mapColor()).strength(2.0f, 1.0f).sound(SoundType.METAL))));

    public static final Map<SupportMetal, RegistryObject<VerticalSupportBlock>> VERTICAL_SUPPORT = Helpers.mapOfKeys(SupportMetal.class, metal -> register("metal/vertical_support/"+metal.getSerializedName(), () -> new VerticalSupportBlock(ExtendedProperties.of().mapColor(metal.color).strength(2.0f, 4.0f).sound(SoundType.METAL))));
    public static final Map<SupportMetal, RegistryObject<HorizontalSupportBlock>> HORIZONTAL_SUPPORT = Helpers.mapOfKeys(SupportMetal.class, metal -> register("metal/horizontal_support/"+metal.getSerializedName(), () -> new HorizontalSupportBlock(ExtendedProperties.of().mapColor(metal.color).strength(2.0f, 4.0f).sound(SoundType.METAL))));

    private static RegistryObject<Block> register(String name, float strength)
    {
        return register(name, () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(strength)),block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = BLOCKS.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            DecoItems.ITEMS.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }
}
