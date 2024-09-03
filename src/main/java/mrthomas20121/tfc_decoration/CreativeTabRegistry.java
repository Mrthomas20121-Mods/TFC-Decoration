package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.util.Util;
import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import mrthomas20121.tfc_decoration.api.util.rock.type.TFCRock;
import mrthomas20121.tfc_decoration.api.util.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.util.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.api.util.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TFCDecoration.mod_id);

    public static final TFCCreativeTabs.CreativeTabHolder DYE_DECORATIVE_BLOCKS = register("dye_decorative_blocks", () -> new ItemStack(TFCDecoBlocks.WOODS.get(TFCDecoWood.BLUE).get(WoodBlockType.WOOD_BEAM).get()), CreativeTabRegistry::fillDyeDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder WOOD_DECORATIVE_BLOCKS = register("wood_decorative_blocks", () -> new ItemStack(TFCDecoBlocks.WOODS.get(TFCDecoWood.BLUE).get(WoodBlockType.WOOD_BEAM).get()), CreativeTabRegistry::fillWoodDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder ROCK_DECORATIVE_BLOCKS = register("rock_decorative_blocks", () -> new ItemStack(TFCDecoBlocks.ROCK_BLOCKS.get(TFCRock.ANDESITE).get(RockBlockType.PILLAR).get()), CreativeTabRegistry::fillRockDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder METAL_DECORATIVE_BLOCKS = register("metal_decorative_blocks", () -> new ItemStack(TFCDecoBlocks.GRATES.get(Metal.Default.BISMUTH_BRONZE).get()), CreativeTabRegistry::fillMetalDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder DECORATIVE_ITEMS = register("decorative_items", () -> new ItemStack(TFCDecoItems.WOOD_LUMBERS.get(TFCDecoWood.BLUE).get()), CreativeTabRegistry::fillDecorativeItemsTab);

    private static TFCCreativeTabs.CreativeTabHolder register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        final RegistryObject<CreativeModeTab> reg = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .icon(icon)
                .title(Component.translatable("creative_tab.tfc_decoration." + name))
                .displayItems(displayItems)
                .build());
        return new TFCCreativeTabs.CreativeTabHolder(reg, displayItems);
    }

    private static void fillDecorativeItemsTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(TFCDecoDyeColor color: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoItems.DYES.get(color).get());
        }
        for(TFCDecoWood wood: TFCDecoWood.VALUES) {
            out.accept(TFCDecoItems.WOOD_LUMBERS.get(wood).get());
        }
        for(BasicRock rock: Util.getALLRockTypes()) {
            out.accept(TFCDecoItems.ROCKWOOl_BRICK.get(rock).get());
        }
    }

    private static void fillDyeDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.BEDS.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.BANNERS.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.WOOLS.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.WOOL_CARPETS.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.CONCRETE_POWDERS.get(dyeColor).get());
            out.accept(TFCDecoBlocks.CONCRETES.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.RAW_ALABASTER.get(dyeColor).get());
            out.accept(TFCDecoBlocks.POLISHED_ALABASTER.get(dyeColor).get());
            out.accept(TFCDecoBlocks.ALABASTER_BRICKS.get(dyeColor).get());
        }

        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            out.accept(TFCDecoBlocks.STAINED_WATTLE.get(dyeColor).get());
        }
    }

    private static void fillWoodDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(BasicWood wood: Util.getALLWoodTypes()) {
            for(WoodBlockType blockType: WoodBlockType.VALUES) {
                out.accept(TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
                out.accept(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get());
                out.accept(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get());
            }
            if(wood.isExtended()) {
                out.accept(TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get());
            }
        }
    }

    private static void fillMetalDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                out.accept(TFCDecoBlocks.GRATES.get(metal).get());
            }
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            out.accept(TFCDecoItems.SUPPORTS.get(metal).get());
        }
    }

    private static void fillRockDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        for(BasicRock rock: Util.getALLRockTypes()) {
            for(RockBlockType blockType: RockBlockType.VALUES) {
                out.accept(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(blockType).get());
                out.accept(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(blockType).slab().get());
                out.accept(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(blockType).stair().get());
                out.accept(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(blockType).wall().get());
            }
        }
    }
}
