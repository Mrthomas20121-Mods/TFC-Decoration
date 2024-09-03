package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.StainedWattleBlock;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = TFCDecoration.mod_id, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WattleHelpers {

    @Nullable
    private static BlockState getPossibleDyedState(ItemStack item, BlockState current)
    {
        BlockState found = Arrays.stream(TFCDecoDyeColor.VALUES)
                .filter(color -> Helpers.isItem(item, TFCDecoItems.DYES.get(color).get()))
                .map(color -> TFCDecoBlocks.STAINED_WATTLE.get(color).get().defaultBlockState())
                .findFirst().orElse(null);
        return found != null && found.getBlock() != current.getBlock() ? found : null;
    }

    @SubscribeEvent
    public static void use(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockHitResult hit = event.getHitVec();
        BlockPos pos = hit.getBlockPos();
        BlockState state = level.getBlockState(pos);
        ItemStack item = event.getItemStack();
        Player player = event.getEntity();
        if (Helpers.isBlock(state, TFCBlocks.WATTLE.get()) || !(state.getBlock() instanceof StainedWattleBlock)) {
            event.setCancellationResult(InteractionResult.PASS);
            return;
        }

        BlockState dyed = getPossibleDyedState(item, state);
        if (dyed == null) {
            event.setCancellationResult(InteractionResult.PASS);
        }
        else {
            if (level.isClientSide) {
                for (int i = 0; i < 5; i++) {
                    Vec3 loc = hit.getLocation();
                    level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, item), loc.x, loc.y, loc.z, Helpers.triangle(level.random) / 3, Helpers.triangle(level.random) / 3, Helpers.triangle(level.random) / 3);
                }
            }
            Helpers.playSound(level, pos, TFCSounds.WATTLE_DYED.get());
            dyed = dyed.setValue(StainedWattleBlock.BOTTOM, state.getValue(StainedWattleBlock.BOTTOM)).setValue(StainedWattleBlock.TOP, state.getValue(StainedWattleBlock.TOP)).setValue(StainedWattleBlock.LEFT, state.getValue(StainedWattleBlock.LEFT)).setValue(StainedWattleBlock.RIGHT, state.getValue(StainedWattleBlock.RIGHT));
            if (!player.isCreative()) item.shrink(1);
            level.setBlockAndUpdate(pos, dyed);
            event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}
