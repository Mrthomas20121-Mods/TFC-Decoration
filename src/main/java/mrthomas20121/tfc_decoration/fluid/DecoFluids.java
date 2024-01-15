package mrthomas20121.tfc_decoration.fluid;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.item.DecoItems;
import net.dries007.tfc.common.fluids.FluidId;
import net.dries007.tfc.common.fluids.FluidRegistryObject;
import net.dries007.tfc.common.fluids.MoltenFluid;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;
import java.util.function.Function;

public class DecoFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TFCDecoration.mod_id);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, TFCDecoration.mod_id);

    public static final FluidRegistryObject<ForgeFlowingFluid> MOLTEN_CARAMEL = register(
            "fluid/caramel",
            properties -> properties
                    .bucket(DecoItems.CARAMEL_BUCKET),
            lavaLike()
                    .temperature(300)
                    .descriptionId("fluid.tfc_decoration.caramel"),
            MoltenFluid.Source::new,
            MoltenFluid.Flowing::new
    );

    private static FluidType.Properties lavaLike()
    {
        return FluidType.Properties.create()
                .adjacentPathType(BlockPathTypes.LAVA)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .lightLevel(15)
                .density(3000)
                .viscosity(6000)
                .temperature(1300)
                .canConvertToSource(false)
                .canDrown(false)
                .canExtinguish(false)
                .canHydrate(false)
                .canPushEntity(false)
                .canSwim(false)
                .supportsBoating(false);
    }

    private static <F extends FlowingFluid> FluidRegistryObject<F> register(String name, Consumer<ForgeFlowingFluid.Properties> builder, FluidType.Properties typeProperties, Function<ForgeFlowingFluid.Properties, F> sourceFactory, Function<ForgeFlowingFluid.Properties, F> flowingFactory)
    {
        final String flowingName = name + "/flowing";

        return RegistrationHelpers.registerFluid(FLUID_TYPES, FLUIDS, name, name, flowingName, builder, () -> new FluidType(typeProperties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {

                consumer.accept(new ClientFluidTypeExtensions(new ResourceLocation(TFCDecoration.mod_id, "block/"+name+"/still"), new ResourceLocation(TFCDecoration.mod_id, "block/"+flowingName)));
            }
        }, sourceFactory, flowingFactory);
    }

    public static class ClientFluidTypeExtensions implements IClientFluidTypeExtensions {

        private final ResourceLocation still;
        private final ResourceLocation flow;

        public ClientFluidTypeExtensions(ResourceLocation still, ResourceLocation flow) {

            this.still = still;
            this.flow = flow;
        }

        @Override
        public ResourceLocation getStillTexture() {
            return this.still;
        }

        @Override
        public ResourceLocation getFlowingTexture() {
            return this.flow;
        }
    }
}
