package mrthomas20121.tfc_decoration.block;

import net.minecraft.world.level.material.MaterialColor;

public enum DecoWood {

    black(MaterialColor.COLOR_BLACK),
    blue(MaterialColor.COLOR_BLUE),
    cyan(MaterialColor.COLOR_CYAN),
    gray(MaterialColor.COLOR_GRAY),
    green(MaterialColor.COLOR_GREEN),
    light_blue(MaterialColor.COLOR_LIGHT_BLUE),
    light_gray(MaterialColor.COLOR_LIGHT_GRAY),
    lime(MaterialColor.COLOR_LIGHT_GREEN),
    magenta(MaterialColor.COLOR_MAGENTA),
    orange(MaterialColor.COLOR_ORANGE),
    pink(MaterialColor.COLOR_PINK),
    purple(MaterialColor.COLOR_PURPLE),
    white(MaterialColor.QUARTZ),
    yellow(MaterialColor.COLOR_YELLOW),
    black_terracotta(MaterialColor.TERRACOTTA_BLACK),
    blue_terracotta(MaterialColor.TERRACOTTA_BLUE),
    cyan_terracotta(MaterialColor.TERRACOTTA_CYAN),
    gray_terracotta(MaterialColor.TERRACOTTA_GRAY),
    green_terracotta(MaterialColor.TERRACOTTA_GREEN),
    light_blue_terracotta(MaterialColor.TERRACOTTA_LIGHT_BLUE),
    light_gray_terracotta(MaterialColor.TERRACOTTA_LIGHT_GRAY),
    lime_terracotta(MaterialColor.TERRACOTTA_LIGHT_GREEN),
    magenta_terracotta(MaterialColor.TERRACOTTA_MAGENTA),
    orange_terracotta(MaterialColor.TERRACOTTA_ORANGE),
    pink_terracotta(MaterialColor.TERRACOTTA_PINK),
    purple_terracotta(MaterialColor.TERRACOTTA_PURPLE),
    white_terracotta(MaterialColor.TERRACOTTA_WHITE),
    yellow_terracotta(MaterialColor.TERRACOTTA_YELLOW);

    private final MaterialColor color;

    DecoWood(MaterialColor color) {
        this.color = color;
    }

    public MaterialColor getColor() {
        return color;
    }
}
