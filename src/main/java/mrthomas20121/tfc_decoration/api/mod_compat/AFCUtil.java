package mrthomas20121.tfc_decoration.api.mod_compat;

import com.redstoneguy10ls.decofirmacraft.common.blocks.rock.DFCRock;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.type.AFCWood;
import net.minecraftforge.fml.ModList;

public class AFCUtil {

    public static void init() {

        // add AFC wood only if the mod is loaded,
        // prevent JEI/creative tab from being filled with blocks that can't be crafted
        if(ModList.get().isLoaded("afc")) {
            Util.registerALLWoodTypes(AFCWood.VALUES);
        }
    }
}
