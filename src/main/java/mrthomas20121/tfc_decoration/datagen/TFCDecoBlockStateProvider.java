package mrthomas20121.tfc_decoration.datagen;

import mrthomas20121.tfc_decoration.TFCDecoration;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.api.blockType.RockBlockType;
import mrthomas20121.tfc_decoration.api.SupportMetal;
import mrthomas20121.tfc_decoration.api.Util;
import mrthomas20121.tfc_decoration.api.wood.BasicWood;
import mrthomas20121.tfc_decoration.api.wood.ExtendedWood;
import mrthomas20121.tfc_decoration.api.blockType.WoodBlockType;
import mrthomas20121.tfc_decoration.block.TFCDecoBlocks;
import mrthomas20121.tfc_decoration.api.wood.type.TFCDecoWood;
import mrthomas20121.tfc_decoration.block.TFCWallBlock;
import net.dries007.tfc.common.blocks.HorizontalPipeBlock;
import net.dries007.tfc.common.blocks.StainedWattleBlock;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.Metal;
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
        for(TFCDecoDyeColor dyeColor: TFCDecoDyeColor.VALUES) {
            simpleBlock(TFCDecoBlocks.BEDS.get(dyeColor).get(), models().getExistingFile(new ResourceLocation("minecraft:block/bed")));
            simpleBlock(TFCDecoBlocks.WOOLS.get(dyeColor).get());
            carpetBlock(TFCDecoBlocks.WOOL_CARPETS.get(dyeColor).get(), TFCDecoBlocks.WOOLS.get(dyeColor).get());
            simpleBlock(TFCDecoBlocks.RAW_ALABASTER.get(dyeColor).get());
            simpleBlock(TFCDecoBlocks.POLISHED_ALABASTER.get(dyeColor).get());
            simpleBlock(TFCDecoBlocks.ALABASTER_BRICKS.get(dyeColor).get());
            wattle(TFCDecoBlocks.STAINED_WATTLE.get(dyeColor).get(), dyeColor);

            simpleBlock(TFCDecoBlocks.CONCRETES.get(dyeColor).get());
            simpleBlock(TFCDecoBlocks.CONCRETE_POWDERS.get(dyeColor).get());

            simpleBlock(TFCDecoBlocks.BANNERS.get(dyeColor).get(), models().getExistingFile(new ResourceLocation("minecraft:block/banner")));
        }

        for(Rock rock: Rock.VALUES) {
            for(RockBlockType type: RockBlockType.VALUES) {
                String typeName = type.getSerializedName();
                String rockName = rock.getSerializedName();
                if(type.equals(RockBlockType.PILLAR)) {
                    axisBlock((RotatedPillarBlock) TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    slabBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    stairsBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/top/" + rockName));
                    wallBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/side/" + rockName));
                }
                else {
                    block(TFCDecoBlocks.ROCK_BLOCKS.get(rock).get(type).get());
                    slabBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                    stairsBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                    wallBlock(TFCDecoBlocks.ROCK_DECORATION_BLOCKS.get(rock).get(type).wall().get(),
                            new ResourceLocation("tfc_decoration", "block/rock/"+ typeName + "/" + rockName));
                }
            }
        }

        for(BasicWood wood: Util.getALLWoodTypes()) {
            String woodName = wood.getSerializedName();
            if(wood.shouldGetPlanks()) {
                block(TFCDecoBlocks.WOOD_PLANKS.get((TFCDecoWood) wood).get());
                slabBlock(TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).slab().get(),
                        new ResourceLocation(wood.modID(), "block/wood/planks/" + wood.getSerializedName()),
                        new ResourceLocation(wood.modID(), "block/wood/planks/" + wood.getSerializedName()));
                stairsBlock(TFCDecoBlocks.WOOD_PLANKS_DECORATIONS.get(wood).stair().get(),
                        new ResourceLocation(wood.modID(), "block/wood/planks/" + wood.getSerializedName()));
            }

            if(wood.isExtended()) {
                TFCWallBlock block = TFCDecoBlocks.LOG_WALLS.get((ExtendedWood) wood).get();
                wallBlock(block, new ResourceLocation(wood.modID()+":block/wood/log/"+wood.getSerializedName()));
            }

            for(WoodBlockType blockType: WoodBlockType.VALUES) {
                String typeName = blockType.getSerializedName();
                if(blockType.equals(WoodBlockType.WOOD_BEAM)) {
                    axisBlock((RotatedPillarBlock) TFCDecoBlocks.WOODS.get(wood).get(blockType).get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/side/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/top/" + woodName));
                    slabBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/side/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/top/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/top/" + woodName));
                    stairsBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/side/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/top/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/top/" + woodName));
                }
                else if(blockType.equals(WoodBlockType.DECORATIVE_BOOKSHELF)) {
                    bookshelf(TFCDecoBlocks.WOODS.get(wood).get(blockType).get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/" + woodName),
                            new ResourceLocation(wood.modID(), "block/wood/planks/" + woodName));
                    slabBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/" + woodName),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/" + woodName),
                            new ResourceLocation(wood.modID(), "block/wood/planks/" + woodName),
                            new ResourceLocation(wood.modID(), "block/wood/planks/" + woodName));
                    stairsBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(),
                            new ResourceLocation("tfc_decoration", "block/wood/"+ typeName + "/" + woodName),
                            new ResourceLocation(wood.modID(), "block/wood/planks/" + woodName),
                            new ResourceLocation(wood.modID(), "block/wood/planks/" + woodName));
                }
                else {
                    block(TFCDecoBlocks.WOODS.get(wood).get(blockType).get());
                    slabBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).slab().get(),
                            new ResourceLocation(TFCDecoration.mod_id, "block/wood/"+ typeName +"/" + wood.getSerializedName()),
                            new ResourceLocation(TFCDecoration.mod_id, "block/wood/"+ typeName +"/" + wood.getSerializedName()));
                    stairsBlock(TFCDecoBlocks.WOODS_DECORATION.get(wood).get(blockType).stair().get(),
                            new ResourceLocation(TFCDecoration.mod_id, "block/wood/"+ typeName +"/" + wood.getSerializedName()));
                }
            }
        }

        for(SupportMetal metal: SupportMetal.values()) {
            horizontalSupport(TFCDecoBlocks.HORIZONTAL_SUPPORT.get(metal).get(), metal);
            verticalSupport(TFCDecoBlocks.VERTICAL_SUPPORT.get(metal).get(), metal);
        }

        for(Metal.Default metal: Metal.Default.values()) {
            if(metal.hasParts()) {
                blockCutout(TFCDecoBlocks.GRATES.get(metal).get());
            }
        }
    }

    public void carpetBlock(Block block, Block wool) {
        simpleBlock(block, models().carpet(name(block), key(wool)));
    }

    private ResourceLocation key(Block block) {
        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(block);
        return new ResourceLocation(key.getNamespace(), "block/" + key.getPath());
    }

    public void block(Block block) {
        this.simpleBlock(block, this.cubeAll(block));
    }

    public void bookshelf(Block block, ResourceLocation side, ResourceLocation end) {
        this.simpleBlock(block, this.cubeColumb(block, side, end));
    }

    public void blockCutout(Block block) {
        this.simpleBlock(block, this.cubeAllWithCutout(block));
    }

    public ModelFile cubeAll(Block block) {
        return this.models().cubeAll(this.name(block), this.key(block));
    }

    public ModelFile cubeColumb(Block block, ResourceLocation side, ResourceLocation end) {
        return this.models().cubeColumn(this.name(block), side, end);
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

    public ResourceLocation getName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location;
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
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
        support(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/horizontal"), new ResourceLocation("tfc:block/wood/support/connection"), metal);
    }

    public void verticalSupport(Block block, SupportMetal metal) {
        support(getName(block).toString(), block, new ResourceLocation("tfc:block/wood/support/vertical"), new ResourceLocation("tfc:block/wood/support/connection"), metal);
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
        builder.part().modelFile(connection).rotationY(270).addModel()
                .condition(HorizontalPipeBlock.NORTH, true).end();
        builder.part().modelFile(connection).addModel()
                .condition(HorizontalPipeBlock.EAST, true).end();
        builder.part().modelFile(connection).rotationY(90).addModel()
                .condition(HorizontalPipeBlock.SOUTH, true).end();
        builder.part().modelFile(connection).rotationY(180).addModel()
                .condition(HorizontalPipeBlock.WEST, true).end();
    }

    private void wattle(StainedWattleBlock wattle, TFCDecoDyeColor dye) {
        String wattleName = getName(wattle).toString();
        String dyeName = dye.getSerializedName();
        this.wattle(wattle, models()
                .withExistingParent(wattleName, new ResourceLocation("tfc:block/cube_column_overlay"))
                .texture("all", "tfc_decoration:block/wattle/stained/"+ dyeName)
                .texture("particle", "tfc:block/wattle/wattle_sides")
                .texture("overlay", "tfc:block/wattle/wattle_sides")
                .texture("overlay_end", "tfc:block/wattle/end"),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/top")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/bottom")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/left")),
                models().getExistingFile(new ResourceLocation("tfc:block/wattle/right"))
        );
    }

    private void wattle(StainedWattleBlock wattle, ModelFile part, ModelFile top, ModelFile bottom, ModelFile left, ModelFile right) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(wattle);

        builder.part().modelFile(part).addModel().end();
        builder.part().modelFile(top).addModel().condition(StainedWattleBlock.TOP, true).end();
        builder.part().modelFile(bottom).addModel().condition(StainedWattleBlock.BOTTOM, true).end();
        builder.part().modelFile(left).addModel().condition(StainedWattleBlock.LEFT, true).end();
        builder.part().modelFile(right).addModel().condition(StainedWattleBlock.RIGHT, true).end();
    }
}
