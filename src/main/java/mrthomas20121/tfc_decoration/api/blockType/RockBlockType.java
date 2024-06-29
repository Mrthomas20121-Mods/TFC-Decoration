package mrthomas20121.tfc_decoration.api.blockType;

import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public enum RockBlockType implements StringRepresentable {

    PILLAR((rock) -> () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(rock.color()).strength(2.5f))),
    ROCKWOOL,
    POLISHED_ROCKWOOL,
    CUT_ROCKWOOL,
    ROCKWOOL_BRICKS;

    public static final RockBlockType[] VALUES = RockBlockType.values();

    private final Function<Rock,Supplier<Block>> block;

    RockBlockType(Function<Rock,Supplier<Block>> block) {
        this.block = block;
    }

    RockBlockType() {
        // default block
        this((rock) -> () -> new Block(BlockBehaviour.Properties.of().mapColor(rock.color()).strength(2.5f)));
    }


    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public Supplier<Block> getBlock(Rock rock) {
        return this.block.apply(rock);
    }
}
