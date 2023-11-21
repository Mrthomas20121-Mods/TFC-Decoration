package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.minecraft.world.level.block.WallBlock;
import org.jetbrains.annotations.NotNull;

public class TFCWallBlock extends WallBlock implements IForgeBlockExtension {

    private final ExtendedProperties properties;

    public TFCWallBlock(ExtendedProperties properties) {
        super(properties.properties());
        this.properties = properties;
    }

    public @NotNull ExtendedProperties getExtendedProperties() {
        return this.properties;
    }
}
