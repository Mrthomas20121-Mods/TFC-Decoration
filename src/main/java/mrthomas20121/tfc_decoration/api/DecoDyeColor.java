package mrthomas20121.tfc_decoration.api;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;
import java.util.function.IntFunction;

public enum DecoDyeColor implements StringRepresentable {
    TERRACOTTA(0, MapColor.COLOR_ORANGE, 13010787),
    TERRACOTTA_WHITE(1, MapColor.TERRACOTTA_WHITE, 14006180),
    TERRACOTTA_ORANGE(2, MapColor.TERRACOTTA_ORANGE, 10967081),
    TERRACOTTA_MAGENTA(3, MapColor.TERRACOTTA_MAGENTA, 10838905),
    TERRACOTTA_LIGHT_BLUE(4, MapColor.TERRACOTTA_LIGHT_BLUE, 7762060),
    TERRACOTTA_YELLOW(5, MapColor.TERRACOTTA_YELLOW, 12618279),
    TERRACOTTA_LIME(6, MapColor.TERRACOTTA_LIGHT_GREEN, 10596947),
    TERRACOTTA_PINK(7, MapColor.TERRACOTTA_PINK, 11031122),
    TERRACOTTA_GRAY(8, MapColor.TERRACOTTA_GRAY, 7492935),
    TERRACOTTA_LIGHT_GRAY(9, MapColor.TERRACOTTA_LIGHT_GRAY, 9203300),
    TERRACOTTA_CYAN(10, MapColor.TERRACOTTA_CYAN, 7238257),
    TERRACOTTA_PURPLE(11, MapColor.TERRACOTTA_PURPLE, 8342621),
    TERRACOTTA_BLUE(12, MapColor.TERRACOTTA_BLUE, 6311538),
    TERRACOTTA_BROWN(13, MapColor.TERRACOTTA_BROWN, 8016687),
    TERRACOTTA_GREEN(14, MapColor.TERRACOTTA_GREEN, 6910524),
    TERRACOTTA_RED(15, MapColor.TERRACOTTA_RED, 9781298),
    TERRACOTTA_BLACK(16, MapColor.TERRACOTTA_BLACK, 8017213);

    public static DecoDyeColor[] VALUES = values();

    public static DecoDyeColor byId(int id) {
        return BY_ID.apply(id);
    }

    private static final IntFunction<DecoDyeColor> BY_ID = ByIdMap.continuous(DecoDyeColor::getId, VALUES, ByIdMap.OutOfBoundsStrategy.ZERO);

    private final int id;
    private final MapColor mapColor;
    private final String serializedName;
    private final float[] textureDiffuseColors;

    DecoDyeColor(int id, MapColor mapColor, int color) {
        this.id = id;
        this.mapColor = mapColor;
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        int i = (color & 16711680) >> 16;
        int j = (color & '\uff00') >> 8;
        int k = (color & 255) >> 0;
        this.textureDiffuseColors = new float[]{(float)i / 255.0F, (float)j / 255.0F, (float)k / 255.0F};
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
