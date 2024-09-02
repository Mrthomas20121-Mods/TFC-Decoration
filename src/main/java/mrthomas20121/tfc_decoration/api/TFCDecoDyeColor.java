package mrthomas20121.tfc_decoration.api;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;
import java.util.function.IntFunction;

public enum TFCDecoDyeColor implements StringRepresentable {
    TERRACOTTA(0, DyeColor.ORANGE, MapColor.COLOR_ORANGE, 13010787),
    TERRACOTTA_WHITE(1, DyeColor.WHITE, MapColor.TERRACOTTA_WHITE, 14006180),
    TERRACOTTA_ORANGE(2, DyeColor.ORANGE, MapColor.TERRACOTTA_ORANGE, 10967081),
    TERRACOTTA_MAGENTA(3, DyeColor.MAGENTA, MapColor.TERRACOTTA_MAGENTA, 10838905),
    TERRACOTTA_LIGHT_BLUE(4, DyeColor.LIGHT_BLUE, MapColor.TERRACOTTA_LIGHT_BLUE, 7762060),
    TERRACOTTA_YELLOW(5, DyeColor.YELLOW, MapColor.TERRACOTTA_YELLOW, 12618279),
    TERRACOTTA_LIME(6, DyeColor.LIME, MapColor.TERRACOTTA_LIGHT_GREEN, 10596947),
    TERRACOTTA_PINK(7, DyeColor.PINK, MapColor.TERRACOTTA_PINK, 11031122),
    TERRACOTTA_GRAY(8, DyeColor.GRAY, MapColor.TERRACOTTA_GRAY, 7492935),
    TERRACOTTA_LIGHT_GRAY(9, DyeColor.LIGHT_GRAY, MapColor.TERRACOTTA_LIGHT_GRAY, 9203300),
    TERRACOTTA_CYAN(10, DyeColor.CYAN, MapColor.TERRACOTTA_CYAN, 7238257),
    TERRACOTTA_PURPLE(11, DyeColor.PURPLE, MapColor.TERRACOTTA_PURPLE, 8342621),
    TERRACOTTA_BLUE(12, DyeColor.BLUE, MapColor.TERRACOTTA_BLUE, 6311538),
    TERRACOTTA_BROWN(13, DyeColor.BROWN, MapColor.TERRACOTTA_BROWN, 8016687),
    TERRACOTTA_GREEN(14, DyeColor.GREEN, MapColor.TERRACOTTA_GREEN, 6910524),
    TERRACOTTA_RED(15, DyeColor.RED, MapColor.TERRACOTTA_RED, 9781298),
    TERRACOTTA_BLACK(16, DyeColor.BLACK, MapColor.TERRACOTTA_BLACK, 8017213);

    public static TFCDecoDyeColor[] VALUES = values();

    public static TFCDecoDyeColor byId(int id) {
        return BY_ID.apply(id);
    }

    private static final IntFunction<TFCDecoDyeColor> BY_ID = ByIdMap.continuous(TFCDecoDyeColor::getId, VALUES, ByIdMap.OutOfBoundsStrategy.ZERO);

    private final int id;
    private final DyeColor analogue;
    private final MapColor mapColor;
    private final String serializedName;
    private final float[] textureDiffuseColors;

    TFCDecoDyeColor(int id, DyeColor analogue, MapColor mapColor, int color) {
        this.id = id;
        this.analogue = analogue;
        this.mapColor = mapColor;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        int i = (color & 16711680) >> 16;
        int j = (color & '\uff00') >> 8;
        int k = (color & 255) >> 0;
        this.textureDiffuseColors = new float[]{(float)i / 255.0F, (float)j / 255.0F, (float)k / 255.0F};
    }

    public DyeColor getAnalogue() {
        return analogue;
    }

    public int getId() {
        return id;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }

    @Override
    public String getSerializedName() {
        return this.serializedName;
    }

    public float[] getTextureDiffuseColors() {
        return textureDiffuseColors;
    }
}
