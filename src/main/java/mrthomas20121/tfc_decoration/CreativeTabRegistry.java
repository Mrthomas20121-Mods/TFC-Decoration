package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
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

    public static final TFCCreativeTabs.CreativeTabHolder WOOD_DECORATIVE_BLOCKS = register("wood_decorative_blocks", () -> new ItemStack(DecoBlocks.VERTICAL_WOOD_PLANKS.get(DecoWood.blue).get()), CreativeTabRegistry::fillWoodDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder ROCK_DECORATIVE_BLOCKS = register("rock_decorative_blocks", () -> new ItemStack(DecoBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(RockBlockType.PILLAR).get()), CreativeTabRegistry::fillRockDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder METAL_DECORATIVE_BLOCKS = register("metal_decorative_blocks", () -> new ItemStack(DecoBlocks.GRATES.get(Metal.Default.BISMUTH_BRONZE).get()), CreativeTabRegistry::fillMetalDecorativeBlocksTab);
    public static final TFCCreativeTabs.CreativeTabHolder DECORATIVE_ITEMS = register("decorative_items", () -> new ItemStack(DecoItems.WOOD_LUMBERS.get(DecoWood.blue).get()), CreativeTabRegistry::fillDecorativeItemsTab);

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

        for(DecoWood wood: DecoWood.values()) {
            out.accept(DecoItems.WOOD_LUMBERS.get(wood).get());
        }
        for(Rock rock: Rock.values()) {
            out.accept(DecoItems.ROCKWOOl_BRICK.get(rock).get());
        }
    }

    private static void fillWoodDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(DecoWood wood: DecoWood.VALUES) {
            out.accept(DecoBlocks.WOOD_PLANKS.get(wood).get());
            out.accept(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            out.accept(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
            out.accept(DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood).get());
            out.accept(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            out.accept(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
        }

        for(Wood wood: Wood.VALUES) {
            out.accept(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
            out.accept(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get());
            out.accept(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get());
            out.accept(DecoBlocks.LOG_WALLS.get(wood).get());
        }
    }

    private static void fillMetalDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                out.accept(DecoBlocks.GRATES.get(metal).get());
            }
        }

        for(SupportMetal metal: SupportMetal.VALUES) {
            out.accept(DecoBlocks.VERTICAL_SUPPORT.get(metal).get());
            out.accept(DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get());
        }
    }

    private static void fillRockDecorativeBlocksTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                out.accept(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                out.accept(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get());
                out.accept(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get());
                out.accept(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get());
            }
        }
    }
}
