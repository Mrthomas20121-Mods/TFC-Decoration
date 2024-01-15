package mrthomas20121.tfc_decoration;

import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.TFCCreativeTabs;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TFCDecoration.mod_id);

    public static final TFCCreativeTabs.CreativeTabHolder DECORATIONS = register("decorations", () -> new ItemStack(DecoBlocks.WOOD_PLANKS.get(DecoWood.blue).get()), CreativeTabRegistry::fillDecorationTab);
    public static final TFCCreativeTabs.CreativeTabHolder FOOD = register("food", () -> new ItemStack(DecoItems.CARAMEL.get()), CreativeTabRegistry::fillFoodTab);


    private static TFCCreativeTabs.CreativeTabHolder register(String name, Supplier<ItemStack> icon, CreativeModeTab.DisplayItemsGenerator displayItems)
    {
        final RegistryObject<CreativeModeTab> reg = CREATIVE_TABS.register(name, () -> CreativeModeTab.builder()
                .icon(icon)
                .title(Component.translatable("tfc_decoration.creative_tab." + name))
                .displayItems(displayItems)
                .build());
        return new TFCCreativeTabs.CreativeTabHolder(reg, displayItems);
    }

    private static void fillFoodTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        out.accept(DecoItems.CARAMEL_BUCKET.get());
        out.accept(DecoItems.CARAMEL.get());
        out.accept(DecoItems.CARAMEL_APPLE.get());
    }

    private static void fillDecorationTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {

        for(Wood wood: Wood.values()) {
            out.accept(DecoBlocks.LOG_WALLS.get(wood).get());
        }

        for(DecoWood wood: DecoWood.values()) {
            out.accept(DecoBlocks.WOOD_PLANKS.get(wood).get());
            out.accept(DecoBlocks.WOOD_DECORATIONS.get(wood).slab().get());
            out.accept(DecoBlocks.WOOD_DECORATIONS.get(wood).stair().get());
        }

        for(DecoWood wood: DecoWood.values()) {
            out.accept(DecoItems.WOOD_LUMBERS.get(wood).get());
        }

        for(Rock rock: Rock.values()) {
            out.accept(DecoBlocks.PILLARS.get(rock).get());
        }
        for(Rock rock: Rock.values()) {
            out.accept(DecoBlocks.ROCKWOOL.get(rock).get());
            out.accept(DecoBlocks.ROCKWOOL_DECORATIONS.get(rock).slab().get());
            out.accept(DecoBlocks.ROCKWOOL_DECORATIONS.get(rock).stair().get());
            out.accept(DecoBlocks.ROCKWOOL_DECORATIONS.get(rock).wall().get());
        }

        for(Rock rock: Rock.values()) {
            out.accept(DecoBlocks.CUT_ROCKWOOL.get(rock).get());
            out.accept(DecoBlocks.CUT_ROCKWOOL_DECORATIONS.get(rock).slab().get());
            out.accept(DecoBlocks.CUT_ROCKWOOL_DECORATIONS.get(rock).stair().get());
            out.accept(DecoBlocks.CUT_ROCKWOOL_DECORATIONS.get(rock).wall().get());
        }

        for(Rock rock: Rock.values()) {
            out.accept(DecoItems.ROCKWOOl_BRICK.get(rock).get());
        }
        for(Rock rock: Rock.values()) {
            out.accept(DecoBlocks.ROCKWOOL_BRICKS.get(rock).get());
            out.accept(DecoBlocks.ROCKWOOL_BRICKS_DECORATIONS.get(rock).slab().get());
            out.accept(DecoBlocks.ROCKWOOL_BRICKS_DECORATIONS.get(rock).stair().get());
            out.accept(DecoBlocks.ROCKWOOL_BRICKS_DECORATIONS.get(rock).wall().get());
        }
    }
}
