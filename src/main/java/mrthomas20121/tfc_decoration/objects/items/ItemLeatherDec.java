package mrthomas20121.tfc_decoration.objects.items;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLeatherDec extends ItemTFC {

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
            return Weight.LIGHT;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }

    @MethodsReturnNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {

        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && !player.isSneaking() && stack.getCount() > 0)
        {
            TFCGuiHandler.openGui(world, player.getPosition(), player, TFCGuiHandler.Type.KNAPPING_LEATHER);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
