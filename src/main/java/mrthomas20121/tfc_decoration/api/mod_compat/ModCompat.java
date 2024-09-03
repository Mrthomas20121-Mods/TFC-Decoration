package mrthomas20121.tfc_decoration.api.mod_compat;

import net.minecraftforge.fml.ModList;

public class ModCompat {

    public static void init() {

        // add AFC wood types only if the mod is loaded,
        // prevent JEI/creative tab from being filled with blocks that can't be crafted
        if(ModList.get().isLoaded("afc")) {
            AFCUtil.init();
        }

        // add DFC Rock types only if the mod is loaded,
        // prevent JEI/creative tab from being filled with blocks that can't be crafted
        if(ModList.get().isLoaded("dfc")) {
            DFCUtil.init();
        }
    }
}
