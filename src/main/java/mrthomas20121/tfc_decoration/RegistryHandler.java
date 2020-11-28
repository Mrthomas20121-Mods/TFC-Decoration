package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockDecoration;
import mrthomas20121.tfc_decoration.objects.blocks.rock.BlockMudBrick;
import mrthomas20121.tfc_decoration.objects.blocks.wood.BlockFenceLogTFC;
import mrthomas20121.tfc_decoration.objects.te.TeMud;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.recipes.ChiselRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static net.dries007.tfc.objects.fluids.FluidsTFC.*;

@Mod.EventBusSubscriber(modid = TFCDecoration.MODID)
public class RegistryHandler
{
    public static BlockMudBrick blockMudBrick;

    private static ArrayList<Block> normalBlocks = new ArrayList<>();
    private static ArrayList<Block> inventoryBlocks = new ArrayList<>();

    public static ArrayList<Block> getNormalBlocks() {
        return normalBlocks;
    }

    public static ArrayList<Block> getInventoryBlocks() {
        return inventoryBlocks;
    }

    @SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            event.getRegistry().registerAll(
                    new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.GRAVEL)), null, new ItemStack(BlockDecoration.get(rock, DecorationType.WET_MUD), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "wet_mud_"+rock.getRegistryName().getPath()),
                    new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.COBBLE)), new FluidStack(FRESH_WATER.get(), 50), new ItemStack(BlockDecoration.get(rock, DecorationType.MOSSY_COBBLE), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "mossy_cobble_"+rock.getRegistryName().getPath()),
                    new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.BRICKS)), new FluidStack(FRESH_WATER.get(), 50), new ItemStack(BlockDecoration.get(rock, DecorationType.MOSSY_BRICK), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "mossy_brick"+rock.getRegistryName().getPath())
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
            IBlockState crackedRock = BlockDecoration.get(rock, DecorationType.CRACKED_BRICK).getDefaultState();
            event.getRegistry().register(new ChiselRecipe(rawRock, crackedRock).setRegistryName("cracked_" + rock.getRegistryName().getPath()));
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
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
            for(DecorationType type: DecorationType.values())
            {
                BlockDecoration blockDecoration = registerBlock(r, type.name().toLowerCase()+"/"+rock.getRegistryName().getPath(), rock, type);
                normalBlocks.add(blockDecoration);
            }
        }

        for(Tree tree : TFCRegistries.TREES.getValuesCollection())
        {
            inventoryBlocks.add(registerWoodBlock(r, "wood/fence_log/"+tree.getRegistryName().getPath(), new BlockFenceLogTFC(tree)));
        }

        blockMudBrick = registerMudBlock(r, new BlockMudBrick(), "mud/bricks");
        normalBlocks.add(blockMudBrick);
        TileEntity.register("te_mud", TeMud.class);
    }

    public static BlockDecoration registerBlock(IForgeRegistry<Block> r, String name, Rock rock, DecorationType decorationType)
    {
        BlockDecoration block = BlockDecoration.create(rock, decorationType);
        block.setRegistryName(TFCDecoration.MODID, name);
        block.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        block.setCreativeTab(CreativeTabsTFC.CT_ROCK_BLOCKS);
        r.register(block);
        return block;
    }

    public static Block registerWoodBlock(IForgeRegistry<Block> r, String name, Block block)
    {
        block.setRegistryName(TFCDecoration.MODID, name);
        block.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        block.setCreativeTab(CreativeTabsTFC.CT_WOOD);
        r.register(block);
        return block;
    }

    public static BlockMudBrick registerMudBlock(IForgeRegistry<Block> r, BlockMudBrick block, String name)
    {
        block.setRegistryName(TFCDecoration.MODID, name);
        block.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        block.setCreativeTab(CreativeTabsTFC.CT_ROCK_BLOCKS);
        r.register(block);
        return block;
    }
}
