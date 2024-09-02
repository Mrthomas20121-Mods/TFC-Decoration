package mrthomas20121.tfc_decoration.api.mod_compat;

import com.redstoneguy10ls.decofirmacraft.common.blocks.rock.DFCRock;
import mrthomas20121.tfc_decoration.api.Util;
import net.minecraftforge.fml.ModList;

public class DFCUtil {

    public static void init() {
        Util.registerALLRockTypes(DFCRock.VALUES);
    }
}
