package mrthomas20121.tfc_decoration.api;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mrthomas20121.tfc_decoration.api.mod_compat.DFCUtil;
import mrthomas20121.tfc_decoration.api.mod_compat.ModCompat;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.api.wood.type.TFCWood;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.minecraftforge.fml.ModList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {

    static List<BasicWood> WOOD_TYPES = new ObjectArrayList<>();

    static List<RegistryRock> ROCK_TYPES = new ObjectArrayList<>();

    static {
        registerALLWoodTypes(TFCDecoWood.VALUES);
        // add tfc wood types
        registerALLWoodTypes(TFCWood.VALUES);

        // add tfc rock types
        registerALLRockTypes(Rock.VALUES);

        ModCompat.init();
    }

    public static List<BasicWood> getALLWoodTypes() {
        return WOOD_TYPES;
    }
    public static List<RegistryRock> getALLRockTypes() {
        return ROCK_TYPES;
    }

    public static List<ExtendedWood> getExtendedWoodTypes() {
        return getALLWoodTypes().stream().filter(wood -> wood instanceof ExtendedWood).map(wood -> (ExtendedWood) wood).toList();
    }

    public static void registerRockType(RegistryRock rock) {
        ROCK_TYPES.add(rock);
    }

    public static void registerALLRockTypes(RegistryRock[] rocks) {
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
