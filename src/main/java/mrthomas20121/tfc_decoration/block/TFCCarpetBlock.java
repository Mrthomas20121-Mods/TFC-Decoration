package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.minecraft.world.level.block.CarpetBlock;

public class TFCCarpetBlock extends CarpetBlock implements IForgeBlockExtension {

    private final ExtendedProperties extendedProperties;

    public TFCCarpetBlock(ExtendedProperties properties) {
            super(properties.properties());
            this.extendedProperties = properties;
        }

    public ExtendedProperties getExtendedProperties() {
        return this.extendedProperties;
    }

}
