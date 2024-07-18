package mrthomas20121.tfc_decoration.fluid;

import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.minecraft.world.level.material.Fluid;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public record DecoFluidId(String name, OptionalInt color, Supplier<? extends Fluid> fluid) {

    private static final Map<Enum<?>, DecoFluidId> IDENTITY = new HashMap<>();
    private static final List<DecoFluidId> VALUES = Arrays.stream(TFCDecoDyeColor.values()).map(dye -> fromEnum(dye, TFCDecoFluids.dyeColorToInt(dye), dye.getSerializedName() + "_dye", TFCDecoFluids.COLORED_FLUIDS.get(dye).source())).toList();

    public static <R> Map<DecoFluidId, R> mapOf(Function<? super DecoFluidId, ? extends R> map)
    {
        return VALUES.stream().collect(Collectors.toMap(Function.identity(), map));
    }

    public static DecoFluidId asType(Enum<?> identity)
    {
        return IDENTITY.get(identity);
    }

    private static DecoFluidId fromEnum(Enum<?> identity, int color, String name, Supplier<? extends Fluid> fluid)
    {
        final DecoFluidId type = new DecoFluidId(name, OptionalInt.of(TFCFluids.ALPHA_MASK | color), fluid);
        IDENTITY.put(identity, type);
        return type;
    }
}
