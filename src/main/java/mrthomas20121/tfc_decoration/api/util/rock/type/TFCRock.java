package mrthomas20121.tfc_decoration.api.util.rock.type;

import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.RockDisplayCategory;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;

public enum TFCRock implements BasicRock {

    GRANITE(Rock.GRANITE),
    DIORITE(Rock.DIORITE),
    GABBRO(Rock.GABBRO),
    SHALE(Rock.SHALE),
    CLAYSTONE(Rock.CLAYSTONE),
    LIMESTONE(Rock.LIMESTONE),
    CONGLOMERATE(Rock.CONGLOMERATE),
    DOLOMITE(Rock.DOLOMITE),
    CHERT(Rock.CHERT),
    CHALK(Rock.CHALK),
    RHYOLITE(Rock.RHYOLITE),
    BASALT(Rock.BASALT),
    ANDESITE(Rock.ANDESITE),
    DACITE(Rock.DACITE),
    QUARTZITE(Rock.QUARTZITE),
    SLATE(Rock.SLATE),
    PHYLLITE(Rock.PHYLLITE),
    SCHIST(Rock.SCHIST),
    GNEISS(Rock.GNEISS),
    MARBLE(Rock.MARBLE);

    public static final TFCRock[] VALUES = values();

    private final String serializedName;
    private final RockDisplayCategory category;
    private final MapColor color;
    private final SandBlockType sandType;
    private final Rock rock;

    TFCRock(Rock rock)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.category = rock.displayCategory();
        this.color = rock.color();
        this.sandType = rock.getSandType();
        this.rock = rock;
    }

    @Override
    public String modid() {
        return "tfc";
    }

    @Override
    public Rock getBaseRock() {
        return rock;
    }

    public SandBlockType getSandType()
    {
        return sandType;
    }

    @Override
    public RockDisplayCategory displayCategory()
    {
        return category;
    }

    @Override
    public MapColor color()
    {
        return color;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }

}
