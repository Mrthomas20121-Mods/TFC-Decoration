package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.TFCDecBlocks;
import mrthomas20121.tfc_decoration.item.TFCDecItems;
import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TFCDecoration.mod_id);

    public static final TFCCreativeTabs.CreativeTabHolder DECORATIONS = register("decorations", () -> new ItemStack(TFCDecBlocks.WOOD_PLANKS.get(DecoWood.blue).get()), CreativeTabRegistry::fillDecorationTab);

    private static TFCCreativeTabs.CreativeTabHolder register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        final RegistryObject<CreativeModeTab> reg = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .icon(icon)
                .title(Component.translatable("tfc.creative_tab." + name))
                .displayItems(displayItems)
                .build());
        return new TFCCreativeTabs.CreativeTabHolder(reg, displayItems);
    }

    private static void fillDecorationTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        for(DecoWood wood: DecoWood.values()) {
            out.accept(TFCDecBlocks.WOOD_PLANKS.get(wood).get());
            out.accept(TFCDecBlocks.WOOD_DECORATIONS.get(wood).slab().get());
            out.accept(TFCDecBlocks.WOOD_DECORATIONS.get(wood).stair().get());
        }

        for(DecoWood wood: DecoWood.values()) {
            out.accept(TFCDecItems.WOOD_LUMBERS.get(wood).get());
        }

        for(Rock rock: Rock.values()) {
            out.accept(TFCDecBlocks.PILLARS.get(rock).get());
        }
        for(Rock rock: Rock.values()) {
            out.accept(TFCDecBlocks.ROCKWOOL.get(rock).get());
            out.accept(TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).slab().get());
            out.accept(TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).stair().get());
            out.accept(TFCDecBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get());
        }
    }
}
