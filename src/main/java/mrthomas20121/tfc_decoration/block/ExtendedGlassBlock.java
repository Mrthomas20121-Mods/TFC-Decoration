package mrthomas20121.tfc_decoration.block;

import net.dries007.tfc.common.blocks.ExtendedBlock;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.IForgeBlockExtension;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtendedGlassBlock extends GlassBlock implements IForgeBlockExtension {

    private final ExtendedProperties properties;

    public ExtendedGlassBlock(ExtendedProperties props) {
        super(props.properties());
        this.properties = props;
    }

    @Override
    public @NotNull ExtendedProperties getExtendedProperties() {
        return this.properties;
    }
}
