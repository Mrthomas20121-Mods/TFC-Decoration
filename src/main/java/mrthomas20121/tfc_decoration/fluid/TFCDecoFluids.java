package mrthomas20121.tfc_decoration.fluid;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.item.TFCDecoItems;
import net.dries007.tfc.common.fluids.*;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class TFCDecoFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TFCDecoration.mod_id);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, TFCDecoration.mod_id);

    /**
     * Texture locations for both vanilla and TFC fluid textures
     */
    public static final ResourceLocation WATER_STILL = Helpers.identifierMC("block/water_still");
    public static final ResourceLocation WATER_FLOW = Helpers.identifierMC("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY = Helpers.identifierMC("block/water_overlay");

    public static final Map<TFCDecoDyeColor, FluidRegistryObject<ForgeFlowingFluid>> COLORED_FLUIDS = Helpers.mapOfKeys(TFCDecoDyeColor.class, color -> register(
            color.getSerializedName() + "_dye",
            properties -> properties
                    .block(TFCDecoBlocks.COLORED_FLUIDS.get(color))
                    .bucket(TFCDecoItems.FLUID_BUCKETS.get(DecoFluidId.asType(color))),
            waterLike()
                    .descriptionId("fluid_type.tfc_decoration." + color.getSerializedName() + "_dye")
                    .canConvertToSource(false),
            new FluidTypeClientProperties(dyeColorToInt(color), WATER_STILL, WATER_FLOW, WATER_OVERLAY, null),
            MixingFluid.Source::new,
            MixingFluid.Flowing::new
    ));

    public static int dyeColorToInt(TFCDecoDyeColor dye)
    {
        float[] colors = dye.getTextureDiffuseColors();
        return new Color(colors[0], colors[1], colors[2]).getRGB();
    }

    private static FluidType.Properties waterLike()
    {
        return FluidType.Properties.create()
                .adjacentPathType(BlockPathTypes.WATER)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .canConvertToSource(true)
                .canDrown(true)
                .canExtinguish(true)
                .canHydrate(true)
                .canPushEntity(true)
                .canSwim(true)
                .supportsBoating(true);
    }

    private static <F extends FlowingFluid> FluidRegistryObject<F> register(String name, Consumer<ForgeFlowingFluid.Properties> builder, FluidType.Properties typeProperties, FluidTypeClientProperties clientProperties, Function<ForgeFlowingFluid.Properties, F> sourceFactory, Function<ForgeFlowingFluid.Properties, F> flowingFactory)
    {
        // Names `metal/foo` to `metal/flowing_foo`
        final int index = name.lastIndexOf('/');
        final String flowingName = index == -1 ? "flowing_" + name : name.substring(0, index) + "/flowing_" + name.substring(index + 1);

        return RegistrationHelpers.registerFluid(FLUID_TYPES, FLUIDS, name, name, flowingName, builder, () -> new ExtendedFluidType(typeProperties, clientProperties), sourceFactory, flowingFactory);
    }
}
