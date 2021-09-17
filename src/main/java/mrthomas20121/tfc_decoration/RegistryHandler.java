package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.api.ModTypes;
import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockRock;
import mrthomas20121.tfc_decoration.objects.blocks.wood.BlockWood;
import mrthomas20121.tfc_decoration.objects.items.ItemRockBase;
import net.dries007.tfc.api.recipes.ChiselRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemTFC;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.ArrayList;

import static net.dries007.tfc.objects.fluids.FluidsTFC.*;

@Mod.EventBusSubscriber(modid = TFCDecoration.MODID)
public class RegistryHandler
{
    private static final ArrayList<Block> normalBlocks = new ArrayList<>();
    private static final ArrayList<Block> inventoryBlocks = new ArrayList<>();
    private static final ArrayList<Item> items = new ArrayList<>();

    public static ArrayList<Block> getNormalBlocks() {
        return normalBlocks;
    }

    public static ArrayList<Block> getInventoryBlocks() {
        return inventoryBlocks;
    }

    public static ArrayList<Item> getItems() {
        return items;
    }

    @SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            event.getRegistry().registerAll(
                    new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.COBBLE)), new FluidStack(FRESH_WATER.get(), 50), new ItemStack(ModTypes.getRock(rock, ModTypes.MOSSY_COBBLE), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "mossy_cobble_"+rock.getRegistryName().getPath()),
                    new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.BRICKS)), new FluidStack(FRESH_WATER.get(), 50), new ItemStack(ModTypes.getRock(rock, ModTypes.MOSSY_BRICKS), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "mossy_brick"+rock.getRegistryName().getPath())
            );
        }
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void onRegisterChiselRecipeEvent(RegistryEvent.Register<ChiselRecipe> event)
    {
        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            Block rawRock = BlockRockVariant.get(rock, Rock.Type.BRICKS);
            IBlockState crackedRock = ModTypes.getRock(rock, ModTypes.CRACKED_BRICKS).getDefaultState();
            event.getRegistry().register(new ChiselRecipe(rawRock, crackedRock).setRegistryName("cracked_" + rock.getRegistryName().getPath()));
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();

        for(Rock rock: TFCRegistries.ROCKS.getValuesCollection())
        {
            for(ModTypes.ItemRockType type: ModTypes.getItemRockTypes())
            {
                items.add(register(r, ItemRockBase.create(rock, type), type.getName().toLowerCase()+"/"+rock.getRegistryName().getPath().toLowerCase(), CreativeTabsTFC.CT_ROCK_ITEMS));
            }
        }

        for(Block block : inventoryBlocks)
        {
            ItemBlockTFC itemBlockTFC = new ItemBlockTFC(block);
            itemBlockTFC.setRegistryName(block.getRegistryName());
            itemBlockTFC.setTranslationKey(block.getTranslationKey());
            itemBlockTFC.setCreativeTab(block.getCreativeTab());
            r.register(itemBlockTFC);
        }
        for(Block block : normalBlocks)
        {
            ItemBlockTFC itemBlockTFC = new ItemBlockTFC(block);
            itemBlockTFC.setRegistryName(block.getRegistryName());
            itemBlockTFC.setTranslationKey(block.getTranslationKey());
            itemBlockTFC.setCreativeTab(block.getCreativeTab());
            r.register(itemBlockTFC);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();
        for(Rock rock: TFCRegistries.ROCKS.getValuesCollection())
        {
            for(ModTypes.RockType type: ModTypes.getRockTypes())
            {
                normalBlocks.add(registerBlock(r, String.format("%s/%s", type.getName().toLowerCase(), rock.getRegistryName().getPath()), rock, type));
            }
        }

        for(Tree tree : TFCRegistries.TREES.getValuesCollection())
        {
            for(ModTypes.WoodType type: ModTypes.getWoodTypes()) {
                inventoryBlocks.add(registerWoodBlock(r, String.format("wood/%s/%s", type.getName().toLowerCase(), tree.getRegistryName().getPath()), type, tree));
            }
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> r = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
        r.remove(new ResourceLocation("tfc:vanilla/ladder"));
        r.remove(new ResourceLocation("minecraft:ladder"));
    }

    public static Block registerBlock(IForgeRegistry<Block> r, String name, Rock rock, ModTypes.RockType rockType)
    {
        BlockRock block = BlockRock.create(rock, rockType);
        block.setCreativeTab(CreativeTabsTFC.CT_ROCK_BLOCKS);
        return register(r, block, name);
    }

    public static Block register(IForgeRegistry<Block> r, Block block, String name)
    {
        block.setRegistryName(TFCDecoration.MODID, name);
        block.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }

    public static Block registerWoodBlock(IForgeRegistry<Block> r, String name, ModTypes.WoodType woodType, Tree tree)
    {
        Block block = BlockWood.create(woodType, tree);
        block.setCreativeTab(CreativeTabsTFC.CT_WOOD);
        return register(r, block, name);
    }

    public static ItemTFC register(IForgeRegistry<Item> r, ItemTFC item, String name, CreativeTabs tabs)
    {
        item.setRegistryName(TFCDecoration.MODID, name);
        item.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(tabs);
        r.register(item);
        return item;
    }
}
