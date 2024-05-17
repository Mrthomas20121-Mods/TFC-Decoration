package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.block.DecoBlocks;
import mrthomas20121.tfc_decoration.block.DecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.blocks.HorizontalPipeBlock;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.wood.VerticalSupportBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class TFCDecoBlockStateProvider extends BlockStateProvider {

    private final TFCDecoBlockModelProvider blockModels;

    public TFCDecoBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TFCDecoration.mod_id, exFileHelper);
        this.blockModels = new TFCDecoBlockModelProvider(output, TFCDecoration.mod_id, exFileHelper);
    }

    @Override
    public BlockModelProvider models() {
        return this.blockModels;
    }

    @Override
    protected void registerStatesAndModels() {
        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                String typeName = type.getSerializedName();
                String rockName = rock.getSerializedName();
                if(type.equals(RockBlockType.PILLAR)) {
                    axisBlock((RotatedPillarBlock) DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    slabBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    stairsBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    wallBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName));
                }
                else {
                    block(DecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                    slabBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                    stairsBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                    wallBlock(DecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                }
            }
        }

        for(Wood wood: Wood.VALUES) {
            TFCWallBlock block = DecoBlocks.LOG_WALLS.get(wood).get();
            wallBlock(block, new ResourceLocation("tfc:block/wood/log/"+wood.getSerializedName()));
            block(DecoBlocks.VERTICAL_TFC_WOOD_PLANKS.get(wood).get());
            slabBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).slab().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()));
            stairsBlock(DecoBlocks.VERTICAl_TFC_WOOD_PLANKS_DECORATIONS.get(wood).stair().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()));
        }

        for(DecoWood wood: DecoWood.VALUES) {
            block(DecoBlocks.WOOD_PLANKS.get(wood).get());
            slabBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/planks/" + wood.getSerializedName()),
                    new ResourceLocation("tfc_decoration", "block/wood/planks/" + wood.getSerializedName()));
            stairsBlock(DecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/planks/" + wood.getSerializedName()));
            block(DecoBlocks.VERTICAL_WOOD_PLANKS.get(wood).get());
            slabBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).slab().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()));
            stairsBlock(DecoBlocks.VERTICAl_WOOD_PLANKS_DECORATIONS.get(wood).stair().get(),
                    new ResourceLocation("tfc_decoration", "block/wood/vertical_planks/" + wood.getSerializedName()));
        }

        for(SupportMetal metal: SupportMetal.values()) {
            horizontalSupport(DecoBlocks.HORIZONTAL_SUPPORT.get(metal).get(), metal);
            verticalSupport(DecoBlocks.VERTICAL_SUPPORT.get(metal).get(), metal);
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                blockCutout(DecoBlocks.GRATES.get(metal).get());
            }
        }
    }

    private ResourceLocation key(Block block) {
        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
        return new ResourceLocation(key.getNamespace(), "block/" + key.getPath());
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }
    public void blockCutout(Block block) {
        this.simpleBlock(block, this.cubeAllWithCutout(block));
    }

    public ModelFile cubeAll(Block block) {
        return this.models().cubeAll(this.name(block), this.key(block));
    }

    public ModelFile cubeAllWithCutout(Block block) {
        return this.models().cubeAll(this.name(block), this.key(block)).renderType("minecraft:cutout");
    }


    public String name(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    public void wallBlock(WallBlock block, ResourceLocation texture) {
        wallBlockInternalWithRenderType(block, key(block).toString(), texture, new ResourceLocation("cutout"));
    }

    private void wallBlockInternalWithRenderType(WallBlock block, String baseName, ResourceLocation texture, ResourceLocation renderType) {
        wallBlock(block, models().wallPost(baseName + "_post", texture).renderType(renderType),
                models().wallSide(baseName + "_side", texture).renderType(renderType),
                models().wallSideTall(baseName + "_side_tall", texture).renderType(renderType));
    }

    public void horizontalSupport(Block block, SupportMetal metal) {
        support(modLoc(name(block)).toString(), block, new ResourceLocation("tfc:block/wood/support/horizontal"), new ResourceLocation("tfc:block/wood/support/connection"), metal);
    }

    public void verticalSupport(Block block, SupportMetal metal) {
        support(modLoc(name(block)).toString(), block, new ResourceLocation("tfc:block/wood/support/vertical"), new ResourceLocation("tfc:block/wood/support/connection"), metal);
    }

    private void support(String name, Block block, ResourceLocation part, ResourceLocation connection, SupportMetal metal) {
        models().withExistingParent(name+"_inventory", new ResourceLocation("tfc:block/wood/support/inventory"))
                .texture("texture", "tfc:block/metal/block/"+metal.getSerializedName())
                .renderType(new ResourceLocation("cutout"));
        support(block, models().withExistingParent(name, part)
                        .texture("texture", "tfc:block/metal/block/"+metal.getSerializedName())
                        .texture("particle", "tfc:block/metal/block/"+metal.getSerializedName())
                        .renderType(new ResourceLocation("cutout")),
                models().withExistingParent(name+"_connection", connection)
                        .texture("texture", "tfc:block/metal/block/"+metal.getSerializedName())
                        .texture("particle", "tfc:block/metal/block/"+metal.getSerializedName())
                        .renderType(new ResourceLocation("cutout")));
    }

    private void support(Block block, ModelFile part, ModelFile connection) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(part).addModel().end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.NORTH, true).end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.EAST, true).end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.WEST, true).end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.SOUTH, true).end();
    }
}
