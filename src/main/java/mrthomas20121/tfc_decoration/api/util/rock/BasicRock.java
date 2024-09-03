package mrthomas20121.tfc_decoration.api.util.rock;

import net.dries007.tfc.common.blocks.rock.RockDisplayCategory;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.material.MapColor;

public interface BasicRock extends StringRepresentable {

    SandBlockType getSandType();

    RockDisplayCategory displayCategory();

    MapColor color();

    RegistryRock getBaseRock();

    String modid();

}
