package mrthomas20121.tfc_decoration.api.wood;

import net.minecraft.world.level.material.MapColor;

public interface ExtendedWood extends BasicWood {

    MapColor barkColor();

    @Override
    default boolean isExtended() {
        return true;
    }
}
