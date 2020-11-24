package mrthomas20121.tfc_decoration.objects.te;

import mrthomas20121.tfc_decoration.ConfigDec;
import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.objects.blocks.BlockDecoration;
import mrthomas20121.tfc_decoration.objects.blocks.BlockMudRaw;
import mrthomas20121.tfc_decoration.types.DecorationType;
import net.dries007.tfc.objects.te.TEBase;
import net.dries007.tfc.objects.te.TETickableBase;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.ICalendarTickable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TeMud extends TETickableBase implements ICalendarTickable
{
    private long ticks;
    private long startTime;
    private long dryTime;
    private boolean isDry;

    public TeMud()
    {
        this.dryTime = CalendarTFC.PLAYER_TIME.getTicks();
        this.startTime = dryTime;
        this.isDry = false;
        this.ticks = dryTime;
    }

    public long getDuration()
    {
        return ConfigDec.ConfigGeneral.time*ICalendar.TICKS_IN_HOUR;
}

    @Override
    public void onLoad()
    {
        this.isDry = canDry();
    }

    @Override
    public void onCalendarUpdate(long ticks)
    {
        if(isDry)
        {
            long tickFinished = startTime + getDuration();

            if(dryTime < tickFinished)
            {
                IBlockState blockState = world.getBlockState(this.pos);
                Block block = blockState.getBlock();
                if(block instanceof BlockMudRaw)
                {
                    BlockMudRaw blockMudRaw = (BlockMudRaw) block;
                    world.setBlockState(this.pos, BlockDecoration.get(blockMudRaw.getRock(), DecorationType.MUD).getDefaultState());
                    this.invalidate();
                }
            }
            else if(ICalendar.getTotalHours(dryTime) > 0)
            {
                dryTime = dryTime+(int)ICalendar.TICKS_IN_MINUTE;
            }
        }
    }

    @Override
    public long getLastUpdateTick() {
        return ticks;
    }

    @Override
    public void setLastUpdateTick(long l) {
        this.ticks = l;
    }

    @Override
    public void update()
    {
        super.update();
        checkForCalendarUpdate();
        if(world.isRemote)
        {
            return;
        }
        else
        {
            this.isDry = canDry();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setLong("startTime", startTime);
        tag.setLong("dryTime", dryTime);
        tag.setBoolean("isDry", isDry);
        return super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        startTime = tag.getLong("startTime");
        dryTime = tag.getLong("dryTime");
        isDry = tag.getBoolean("isDry");
    }

    private boolean canDry()
    {
        return !this.world.isRaining();
    }

}
