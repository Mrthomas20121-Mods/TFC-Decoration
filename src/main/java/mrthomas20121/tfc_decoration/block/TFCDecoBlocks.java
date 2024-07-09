package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.DecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.fluid.TFCDecoFluids;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.blocks.*;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.*;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class TFCDecoBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TFCDecoration.mod_id);

    public static final Map<DecoDyeColor, RegistryObject<Block>> RAW_ALABASTER = Helpers.mapOfKeys(DecoDyeColor.class, color ->
            register(("alabaster/raw/" + color.getSerializedName()), () -> new Block(BlockBehaviour.Properties.of().mapColor(color.getMapColor()).requiresCorrectToolForDrops().strength(1.0F, 6.0F)))
    );
    public static final Map<DecoDyeColor, RegistryObject<Block>> ALABASTER_BRICKS = Helpers.mapOfKeys(DecoDyeColor.class, color ->
            register(("alabaster/bricks/" + color.getSerializedName()), () -> new Block(BlockBehaviour.Properties.of().mapColor(color.getMapColor()).requiresCorrectToolForDrops().strength(1.5F, 6.0F)))
    );
    public static final Map<DecoDyeColor, RegistryObject<Block>> POLISHED_ALABASTER = Helpers.mapOfKeys(DecoDyeColor.class, color ->
            register(("alabaster/polished/" + color.getSerializedName()), () -> new Block(BlockBehaviour.Properties.of().mapColor(color.getMapColor()).requiresCorrectToolForDrops().strength(1.5F, 6.0F)))
    );

    public static final Map<DecoDyeColor, RegistryObject<TFCDecoBedBlock>> BEDS = Helpers.mapOfKeys(DecoDyeColor.class, color ->
            register(color.getSerializedName()+"_bed", () -> new TFCDecoBedBlock(color, BlockBehaviour.Properties.of().mapColor((state) -> state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY))));

    public static final Map<DecoDyeColor, RegistryObject<Block>> WOOLS = Helpers.mapOfKeys(DecoDyeColor.class, dyeColor -> register("wool/%s".formatted(dyeColor.name()), () -> new ExtendedBlock(ExtendedProperties.of(Blocks.WHITE_WOOL).mapColor(dyeColor.getMapColor()).flammableLikeWool())));
    public static final Map<DecoDyeColor, RegistryObject<Block>> WOOL_CARPETS = Helpers.mapOfKeys(DecoDyeColor.class, dyeColor -> register("wool_carpet/%s".formatted(dyeColor.name()), () -> new TFCCarpetBlock(ExtendedProperties.of(Blocks.WHITE_CARPET).mapColor(dyeColor.getMapColor()).flammableLikeWool())));

    public static final Map<DecoDyeColor, RegistryObject<StainedWattleBlock>> STAINED_WATTLE = Helpers.mapOfKeys(DecoDyeColor.class, dyeColor -> register("wattle/%s".formatted(dyeColor.name()), () -> new StainedWattleBlock(ExtendedProperties.of(MapColor.WOOD).strength(0.3F).sound(SoundType.SCAFFOLDING).flammable(60, 30))));

    public static final Map<Rock, Map<RockBlockType, RegistryObject<Block>>> ROCK_BLOCKS = Helpers.mapOfKeys(Rock.class, rock -> Helpers.mapOfKeys(RockBlockType.class, type -> register("rock/"+ type.getSerializedName() + "/" + rock.name(), type.getBlock(rock))));

    public static final Map<Rock, Map<RockBlockType, DecorationBlockRegistryObject>> ROCK_DECORATION_BLOCKS = Helpers.mapOfKeys(Rock.class, rock -> Helpers.mapOfKeys(RockBlockType.class, type -> new DecorationBlockRegistryObject(
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_slab"), () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f))),
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_stairs"), () -> new StairBlock(() -> ROCK_BLOCKS.get(rock).get(type).get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f))),
            register(("rock/"+ type.getSerializedName() + "/" + rock.name() + "_wall"), () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).sound(SoundType.STONE).strength(2.5f)))
    )));

    public static final Map<TFCDecoWood, RegistryObject<Block>> WOOD_PLANKS = Helpers.mapOfKeys(TFCDecoWood.class, decoWood -> register("wood/planks/%s".formatted(decoWood), () -> new ExtendedBlock(ExtendedProperties.of().mapColor(decoWood.woodColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));

    public static final Map<BasicWood, Map<WoodBlockType, RegistryObject<Block>>> WOODS = Util.mapOfKeys(Util.getALLWoodTypes(), wood -> Helpers.mapOfKeys(WoodBlockType.class, blockType ->
        register("wood/%s/%s".formatted(blockType.getSerializedName(), wood.getSerializedName()), () -> blockType.getBlock(wood))
    ));

    public static final Map<BasicWood, Map<WoodBlockType, WoodBlockRegistryObject>> WOODS_DECORATION = Util.mapOfKeys(Util.getALLWoodTypes(), wood -> Helpers.mapOfKeys(WoodBlockType.class, blockType ->
            new WoodBlockRegistryObject(
                    register(("wood/"+blockType.getSerializedName()+"/" + wood.getSerializedName() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.woodColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
                    register(("wood/"+blockType.getSerializedName()+"/" + wood.getSerializedName() + "_stairs"), () -> new TFCStairBlock(() -> WOODS.get(wood).get(blockType).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.woodColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
            )
    ));

    public static final Map<ExtendedWood, RegistryObject<TFCWallBlock>> LOG_WALLS = Util.mapOfKeys(Util.getExtendedWoodTypes(), decoWood -> register("wood/%s_log_wall".formatted(decoWood), () -> new TFCWallBlock(ExtendedProperties.of().mapColor(decoWood.barkColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f))));

    public static final Map<TFCDecoWood, WoodBlockRegistryObject> WOOD_PLANKS_DECORATIONS = Helpers.mapOfKeys(TFCDecoWood.class, wood -> new WoodBlockRegistryObject(
            register(("wood/planks/" + wood.name() + "_slab"), () -> new TFCSlabBlock(ExtendedProperties.of().mapColor(wood.woodColor()).flammableLikePlanks().sound(SoundType.WOOD).strength(1.5f, 3.0f))),
            register(("wood/planks/" + wood.name() + "_stairs"), () -> new TFCStairBlock(() -> WOOD_PLANKS.get(wood).get().defaultBlockState(), ExtendedProperties.of().mapColor(wood.woodColor()).sound(SoundType.WOOD).flammableLikePlanks().strength(1.5f, 3.0f)))
    ));

    public static final Map<Metal.Default, RegistryObject<GrateBlock>> GRATES = Helpers.mapOfKeys(Metal.Default.class, Metal.Default::hasParts, metal -> register("metal/grate/" + metal.getSerializedName(), () -> new GrateBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(metal.mapColor()).strength(2.0f, 1.0f).sound(SoundType.METAL))));

    public static final Map<SupportMetal, RegistryObject<VerticalSupportBlock>> VERTICAL_SUPPORT = Helpers.mapOfKeys(SupportMetal.class, metal -> registerNoItem("metal/vertical_support/"+metal.getSerializedName(), () -> new VerticalSupportBlock(ExtendedProperties.of().mapColor(metal.color).strength(2.0f, 4.0f).sound(SoundType.METAL))));
    public static final Map<SupportMetal, RegistryObject<HorizontalSupportBlock>> HORIZONTAL_SUPPORT = Helpers.mapOfKeys(SupportMetal.class, metal -> registerNoItem("metal/horizontal_support/"+metal.getSerializedName(), () -> new HorizontalSupportBlock(ExtendedProperties.of().mapColor(metal.color).strength(2.0f, 4.0f).sound(SoundType.METAL))));

    public static final Map<DecoDyeColor, RegistryObject<LiquidBlock>> COLORED_FLUIDS = Helpers.mapOfKeys(DecoDyeColor.class, fluid ->
            registerNoItem("fluid/" + fluid.getSerializedName() + "_dye", () -> new LiquidBlock(TFCDecoFluids.COLORED_FLUIDS.get(fluid).source(), BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()))
    );

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
            TFCDecoItems.ITEMS.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static BedBlock bed(DyeColor p_50765_) {
        return new BedBlock(p_50765_, BlockBehaviour.Properties.of()
                .mapColor((p_284863_) -> p_284863_.getValue(BedBlock.PART) == BedPart.FOOT ? p_50765_.getMapColor() : MapColor.WOOL)
                .sound(SoundType.WOOD).strength(0.2F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY));
    }
}
