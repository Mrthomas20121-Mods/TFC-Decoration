package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.objects.blocks.BlockDecoration;
import mrthomas20121.tfc_decoration.objects.blocks.BlockMudBrick;
import mrthomas20121.tfc_decoration.objects.te.TeMud;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.recipes.ChiselRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.dries007.tfc.objects.blocks.BlocksTFC;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

import static net.dries007.tfc.objects.fluids.FluidsTFC.*;

@Mod.EventBusSubscriber(modid = TFCDecoration.MODID)
public class RegistryHandler
{
    public static BlockMudBrick blockMudBrick;
    public static ArrayList<BlockDecoration> blocks = new ArrayList<>();

    @SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            event.getRegistry().register(
                    new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of(BlockRockVariant.get(rock, Rock.Type.GRAVEL)), null, new ItemStack(BlockDecoration.get(rock, DecorationType.WET_MUD), 1), 8* ICalendar.TICKS_IN_HOUR).setRegistryName(TFCDecoration.MODID, "wet_mud_"+rock.getRegistryName().getPath())
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
        for(BlockDecoration block : blocks)
        {
            ItemBlockTFC itemBlockTFC = new ItemBlockTFC(block);
            itemBlockTFC.setRegistryName(block.getRegistryName());
            itemBlockTFC.setTranslationKey(block.getTranslationKey());
            itemBlockTFC.setCreativeTab(CreativeTabsTFC.CT_ROCK_BLOCKS);
            r.register(itemBlockTFC);
        }
        r.register(new ItemBlockTFC(blockMudBrick).setRegistryName(blockMudBrick.getRegistryName()));
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
            }
        }

        blockMudBrick = registerMudBlock(r, new BlockMudBrick(), "mud/bricks");
        TileEntity.register("te_mud", TeMud.class);
    }

    public static BlockDecoration registerBlock(IForgeRegistry<Block> r, String name, Rock rock, DecorationType decorationType)
    {
        BlockDecoration block = BlockDecoration.create(rock, decorationType);
        block.setRegistryName(TFCDecoration.MODID, name);
        block.setTranslationKey(TFCDecoration.MODID + "." + name.replace('/', '.'));
        block.setCreativeTab(CreativeTabsTFC.CT_ROCK_BLOCKS);
        r.register(block);
        blocks.add(block);
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
