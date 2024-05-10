package mrthomas20121.tfc_decoration.api;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum SupportMetal implements StringRepresentable {
    WROUGHT_IRON(2, 2, 5, MapColor.COLOR_LIGHT_GRAY),
    STEEL(2, 2, 6),
    BLACK_STEEL(3, 3, 6, MapColor.TERRACOTTA_BLACK),
    RED_STEEL(4, 4, 8, MapColor.COLOR_RED),
    BLUE_STEEL(4, 4, 8, MapColor.COLOR_BLUE);

    public static final SupportMetal[] VALUES = values();

    public final int up;
    public final int down;
    public final int horizontal;
    public final MapColor color;

    SupportMetal(int up, int down, int horizontal, MapColor color) {
        this.up = up;
        this.down = down;
        this.horizontal = horizontal;
        this.color = color;
    }

    SupportMetal(int up, int down, int horizontal) {
        this(up, down, horizontal, MapColor.COLOR_GRAY);
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
