package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.TFCDecoItemTags;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.StainedWattleBlock;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Custom wattle block because the default implementation does not support my dyes for the ghosting stuff
 */
public class DecoStainedWattleBlock extends StainedWattleBlock {

    private static final double TOP_LIMIT = 0.75;
    private static final double BOTTOM_LIMIT = 0.25;
    private static final double MIDDLE = 0.5;

    public DecoStainedWattleBlock(ExtendedProperties properties) {
        super(properties);
    }

    @Nullable
    private static BlockState removeStateFor(BlockState state, Direction lookDirection, double x, double y, double z)
    {
        if (lookDirection.getAxis() == Direction.Axis.Y) return null;
        final double horizontal = lookDirection.getAxis() == Direction.Axis.Z ? x : z;
        if (y > TOP_LIMIT && state.getValue(TOP))
        {
            return state.setValue(TOP, false);
        }
        else if (y < BOTTOM_LIMIT && state.getValue(BOTTOM))
        {
            return state.setValue(BOTTOM, false);
        }
        else if (horizontal < MIDDLE && state.getValue(LEFT))
        {
            return state.setValue(LEFT, false);
        }
        else if (horizontal > MIDDLE && state.getValue(RIGHT))
        {
            return state.setValue(RIGHT, false);
        }
        return null;
    }

    @Nullable
    private static BlockState getStateFor(BlockState state, Direction lookDirection, double x, double y, double z)
    {
        if (lookDirection.getAxis() == Direction.Axis.Y) return null;
        final double horizontal = lookDirection.getAxis() == Direction.Axis.Z ? x : z;
        if (y > TOP_LIMIT && !state.getValue(TOP))
        {
            return state.setValue(TOP, true);
        }
        else if (y < BOTTOM_LIMIT && !state.getValue(BOTTOM))
        {
            return state.setValue(BOTTOM, true);
        }
        else if (horizontal < MIDDLE && !state.getValue(LEFT))
        {
            return state.setValue(LEFT, true);
        }
        else if (horizontal > MIDDLE && !state.getValue(RIGHT))
        {
            return state.setValue(RIGHT, true);
        }
        return null;
    }

    @Nullable
    private static BlockState getPossibleDecoDyedState(ItemStack item, BlockState current)
    {
        BlockState found = Arrays.stream(TFCDecoDyeColor.VALUES)
                .filter(color -> Helpers.isItem(item, TFCDecoItems.DYES.get(color).get()))
                .map(color -> TFCDecoBlocks.STAINED_WATTLE.get(color).get().defaultBlockState())
                .findFirst().orElse(null);
        return found != null && found.getBlock() != current.getBlock() ? found : null;
    }

    @Nullable
    private static BlockState getPossibleTFCDyedState(ItemStack item, BlockState current)
    {
        BlockState found = Arrays.stream(Helpers.DYE_COLORS)
                .filter(color -> Helpers.isItem(item, DyeItem.byColor(color)))
                .map(color -> TFCBlocks.STAINED_WATTLE.get(color).get().defaultBlockState())
                .findFirst().orElse(null);
        return found != null && found.getBlock() != current.getBlock() ? found : null;
    }

    @Nullable
    private static BlockState getPossibleDyedState(ItemStack item, BlockState current)
    {
        return item.is(TFCDecoItemTags.DYES) ? getPossibleDecoDyedState(item, current): getPossibleTFCDyedState(item, current);
    }

    @Nullable
    @Override
    public BlockState getStateToDraw(Level level, Player player, BlockState state, Direction direction, BlockPos pos, double x, double y, double z, ItemStack item)
    {
        if (item.isEmpty() && player.isShiftKeyDown())
        {
            return removeStateFor(state, direction, x, y, z);
        }
        else if (Helpers.isItem(item, Tags.Items.RODS_WOODEN))
        {
            return getStateFor(state, direction, x, y, z);
        }
        else
        {
            if (Helpers.isBlock(state, TFCBlocks.WATTLE.get()))
            {
                return null;
            }
            BlockState dyed = getPossibleDyedState(item, state);
            if (dyed != null)
            {
                return dyed.setValue(BOTTOM, state.getValue(BOTTOM)).setValue(TOP, state.getValue(TOP)).setValue(LEFT, state.getValue(LEFT)).setValue(RIGHT, state.getValue(RIGHT));
            }
        }
        return null;
    }
}
