package mrthomas20121.tfc_decoration.api.util.wood;

import net.minecraft.world.level.material.MapColor;

public interface ExtendedWood extends BasicWood {

    MapColor barkColor();

    @Override
    default boolean isExtended() {
        return true;
    }
}
