package mrthomas20121.tfc_decoration.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TFCDecoItemTags {
    public static final TagKey<Item> SAWS = ItemTags.create(new ResourceLocation("tfc:saws"));
    public static final TagKey<Item> GRATES = ItemTags.create(new ResourceLocation("tfc_decoration:grates"));

    public static final TagKey<Item> METAL_SUPPORTS = ItemTags.create(new ResourceLocation("tfc_decoration:metal_supports"));

    public static final TagKey<Item> UNCOLORED_PLANKS = ItemTags.create(new ResourceLocation("tfc_decoration:uncolored_planks"));

    public static final TagKey<Item> COLORED_RAW_ALABASTER = ItemTags.create(new ResourceLocation("tfc:colored_raw_alabaster"));
    public static final TagKey<Item> COLORED_BRICKS_ALABASTER = ItemTags.create(new ResourceLocation("tfc:colored_bricks_alabaster"));
    public static final TagKey<Item> COLORED_POLISHED_ALABASTER = ItemTags.create(new ResourceLocation("tfc:colored_polished_alabaster"));
}
