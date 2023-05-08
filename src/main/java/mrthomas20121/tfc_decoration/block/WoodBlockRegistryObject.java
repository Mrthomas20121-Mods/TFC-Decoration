package mrthomas20121.tfc_decoration.block;

import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.RegistryObject;

public record WoodBlockRegistryObject(RegistryObject<? extends SlabBlock> slab, RegistryObject<? extends StairBlock> stair) {
    public WoodBlockRegistryObject(RegistryObject<? extends SlabBlock> slab, RegistryObject<? extends StairBlock> stair) {
        this.slab = slab;
        this.stair = stair;
    }

    public RegistryObject<? extends SlabBlock> slab() {
        return this.slab;
    }

    public RegistryObject<? extends StairBlock> stair() {
        return this.stair;
    }
}

