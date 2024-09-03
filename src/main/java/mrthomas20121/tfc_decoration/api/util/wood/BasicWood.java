package mrthomas20121.tfc_decoration.api.util.wood;

import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

/**
 * dumb down version of {@link net.dries007.tfc.util.registry.RegistryWood} as I do not need everything from it
 */
public interface BasicWood extends StringRepresentable {

    MapColor woodColor();

    Supplier<Block> getBlock(WoodBlockType blockType);

    String name();

    default String modID() {
        return "tfc_decoration";
    }

    default boolean isExtended() {
        return false;
    }

    default boolean shouldGetPlanks() {
        return false;
    }
}
