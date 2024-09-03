package mrthomas20121.tfc_decoration.api.mod_compat;

import mrthomas20121.tfc_decoration.api.util.Util;
import mrthomas20121.tfc_decoration.api.util.rock.type.DecoFirmaRock;

public class DFCUtil {

    public static void init() {
        Util.registerALLRockTypes(DecoFirmaRock.VALUES);
    }
}
