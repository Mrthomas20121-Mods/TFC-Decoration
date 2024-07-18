package mrthomas20121.tfc_decoration.block;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBedBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

public class TFCDecoBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TFCDecoration.mod_id);

    public static RegistryObject<BlockEntityType<TFCDecoBedBlockEntity>> BED = BLOCK_ENTITIES.register("bed",
            () -> BlockEntityType.Builder.of(TFCDecoBedBlockEntity::new, Arrays.stream(TFCDecoDyeColor.VALUES).map(color -> TFCDecoBlocks.BEDS.get(color).get()).toList().toArray(new Block[0])).build(null));
}
