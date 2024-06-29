package mrthomas20121.tfc_decoration.api;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.wood.type.AFCWood;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.api.wood.type.TFCWood;
import net.minecraftforge.fml.ModList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {

    static List<BasicWood> WOOD_TYPES = new ObjectArrayList<>();

    static {
        registerALL(TFCDecoWood.VALUES);
        registerALL(TFCWood.VALUES);

        // add AFC wood only if the mod is loaded,
        // prevent JEI/creative tab from being filled with blocks that can't be crafted
        if(ModList.get().isLoaded("afc")) {
            registerALL(AFCWood.VALUES);
        }
    }

    public static List<BasicWood> getALLWoodTypes() {
        return WOOD_TYPES;
    }

    public static List<ExtendedWood> getExtendedWoodTypes() {
        return getALLWoodTypes().stream().filter(wood -> wood instanceof ExtendedWood).map(wood -> (ExtendedWood) wood).toList();
    }

    public static void register(BasicWood wood) {
        WOOD_TYPES.add(wood);
    }

    public static void registerALL(BasicWood[] wood) {
        WOOD_TYPES.addAll(List.of(wood));
    }

    public static <A, B> Map<A, B> mapOfKeys(List<A> list, Predicate<A> keyPredicate, Function<A, B> func) {
        Map<A, B> map = new HashMap<>();

        list.stream().filter(keyPredicate).forEach(a -> map.put(a, func.apply(a)));
        return map;
    }

    public static <A, B> Map<A, B> mapOfKeys(List<A> list, Function<A, B> func) {
        Map<A, B> map = new HashMap<>();

        list.forEach(a -> map.put(a, func.apply(a)));
        return map;
    }
}
