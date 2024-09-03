package mrthomas20121.tfc_decoration.api.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mrthomas20121.tfc_decoration.api.mod_compat.ModCompat;
import mrthomas20121.tfc_decoration.api.util.rock.BasicRock;
import mrthomas20121.tfc_decoration.api.util.rock.type.TFCRock;
import mrthomas20121.tfc_decoration.api.util.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.util.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.util.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.api.util.wood.type.TFCWood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {

    static List<BasicWood> WOOD_TYPES = new ObjectArrayList<>();

    static List<BasicRock> ROCK_TYPES = new ObjectArrayList<>();

    static {
        registerALLWoodTypes(TFCDecoWood.VALUES);
        // add tfc wood types
        registerALLWoodTypes(TFCWood.VALUES);

        // add tfc rock types
        registerALLRockTypes(TFCRock.VALUES);

        // register AFC wood types and DFC rock types
        ModCompat.init();
    }

    public static List<BasicWood> getALLWoodTypes() {
        return WOOD_TYPES;
    }
    public static List<BasicRock> getALLRockTypes() {
        return ROCK_TYPES;
    }

    public static List<ExtendedWood> getExtendedWoodTypes() {
        return getALLWoodTypes().stream().filter(wood -> wood instanceof ExtendedWood).map(wood -> (ExtendedWood) wood).toList();
    }

    public static void registerRockType(BasicRock rock) {
        ROCK_TYPES.add(rock);
    }

    public static void registerALLRockTypes(BasicRock[] rocks) {
        ROCK_TYPES.addAll(List.of(rocks));
    }

    public static void registerWoodType(BasicWood wood) {
        WOOD_TYPES.add(wood);
    }

    public static void registerALLWoodTypes(BasicWood[] woods) {
        WOOD_TYPES.addAll(List.of(woods));
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
