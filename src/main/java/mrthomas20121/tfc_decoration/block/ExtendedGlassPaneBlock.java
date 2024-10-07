package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import org.jetbrains.annotations.NotNull;

public class ExtendedGlassPaneBlock extends IronBarsBlock implements IForgeBlockExtension {

    private final ExtendedProperties properties;

    public ExtendedGlassPaneBlock(ExtendedProperties props) {
        super(props.properties());
        this.properties = props;
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.FALSE).setValue(EAST, Boolean.FALSE).setValue(SOUTH, Boolean.FALSE).setValue(WEST, Boolean.FALSE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public @NotNull ExtendedProperties getExtendedProperties() {
        return this.properties;
    }
}
