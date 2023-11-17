package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.item.TFCDecItems;
import net.dries007.tfc.common.blocks.DecorationBlockRegistryObject;
import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.TFCSlabBlock;
import net.dries007.tfc.common.blocks.wood.TFCStairBlock;
import net.dries007.tfc.util.Helpers;
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

public class TFCDecBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCDecoration.mod_id);

    public static final Map<Rock, RegistryObject<RotatedPillarBlock>> PILLARS = Helpers.mapOfKeys(Rock.class, rock -> register("pillar/" + rock.name(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(rock.color()).strength(2f))));

    public static final Map<Rock, RegistryObject<Block>> ROCKWOOL = Helpers.mapOfKeys(Rock.class, rock -> register("rockwool/" + rock.name(), () -> new Block(BlockBehaviour.Properties.of().mapColor(rock.color()).strength(2f))));

    public static final Map<Rock, DecorationBlockRegistryObject> ROCKWOOL_DECORATIONS = Helpers.mapOfKeys(Rock.class, rock -> new DecorationBlockRegistryObject(
            register(("rockwool/" + rock.name() + "_slab"), () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f))),
            register(("rockwool/" + rock.name() + "_stairs"), () -> new StairBlock(() -> ROCKWOOL.get(rock).get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f))),
            register(("rockwool/" + rock.name() + "_wall"), () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2f)))
    ));

    public static final Map<DecoWood, RegistryObject<Block>> WOOD_PLANKS = Helpers.mapOfKeys(DecoWood.class, decoWood -> register("wood/%s_planks".formatted(decoWood), () -> new ExtendedBlock(ExtendedProperties.of().mapColor(decoWood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));

    public static final Map<DecoWood, WoodBlockRegistryObject> WOOD_DECORATIONS = Helpers.mapOfKeys(DecoWood.class, wood -> new WoodBlockRegistryObject(
            register(("wood/" + wood.name() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.getColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
            register(("wood/" + wood.name() + "_stairs"), () -> new TFCStairBlock(() -> WOOD_PLANKS.get(wood).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.getColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
    ));

    private static RegistryObject<Block> register(String name, float strength)
    {
        return register(name, () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(strength)),block -> new BlockItem(block, new Item.Properties()));
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
            TFCDecItems.ITEMS.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }
}
