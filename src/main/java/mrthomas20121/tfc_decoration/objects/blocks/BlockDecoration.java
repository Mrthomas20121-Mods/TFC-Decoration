package mrthomas20121.tfc_decoration.objects.blocks;

import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Rock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;

public class BlockDecoration extends Block implements IItemSize
{
    public DecorationType type;
    public static HashMap<Rock, EnumMap<DecorationType, BlockDecoration>> table = new HashMap<>();

    private DecorationType decorationType;

    public static BlockDecoration get(Rock rock, DecorationType decorationType)
    {
        return table.get(rock).get(decorationType);
    }

    public static BlockDecoration create(Rock rock, DecorationType decorationType)
    {
        if(decorationType.isFallable()) return new BlockFallable(rock, decorationType);
        if(decorationType == DecorationType.WET_MUD) return new BlockMudRaw(rock, decorationType);
        return new BlockDecoration(rock, decorationType);
    }

    public BlockDecoration(Rock rock, DecorationType decorationType)
    {
        super(decorationType.getMaterial());
        this.type = decorationType;
        this.setSoundType(decorationType.getSoundType());
        this.setHardness(decorationType.getHardness());
        this.setHarvestLevel("pickaxe", 1);
        if(!table.containsKey(rock))
        {
            table.put(rock, new EnumMap<>(DecorationType.class));
        }
        table.get(rock).put(decorationType, this);
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.LIGHT;
    }
}
