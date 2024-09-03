package mrthomas20121.tfc_decoration.api.util.rock.type;

import com.redstoneguy10ls.decofirmacraft.common.blocks.rock.DFCRock;
import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import net.dries007.tfc.common.blocks.rock.RockDisplayCategory;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.minecraft.world.level.material.MapColor;

import java.util.Locale;

// DecoFirmaCraft Rocks
public enum DecoFirmaRock implements BasicRock {

    TRAVERTINE(DFCRock.TRAVERTINE),
    SERPENTINE(DFCRock.SERPENTINE),
    ARKOSE(DFCRock.ARKOSE),
    BLUESCHIST(DFCRock.BLUESCHIST),
    TUFF(DFCRock.TUFF);

    public static final DecoFirmaRock[] VALUES = values();

    private final String serializedName;
    private final RockDisplayCategory category;
    private final MapColor color;
    private final SandBlockType sandType;
    private final DFCRock rock;

    DecoFirmaRock(DFCRock rock)
    {
        this.serializedName = name().toLowerCase(Locale.ROOT);
        this.category = rock.displayCategory();
        this.color = rock.color();
        this.sandType = rock.getSandType();
        this.rock = rock;
    }

    @Override
    public String modid() {
        return "dfc";
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
    public DFCRock getBaseRock() {
        return this.rock;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }

}
