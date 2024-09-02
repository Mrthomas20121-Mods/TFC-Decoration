package mrthomas20121.tfc_decoration.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import mrthomas20121.tfc_decoration.api.TFCDecoDyeColor;
import mrthomas20121.tfc_decoration.block.TFCDecoBannerBlock;
import mrthomas20121.tfc_decoration.block.TFCDecoWallBannerBlock;
import mrthomas20121.tfc_decoration.block.entity.TFCDecoBannerBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;

import java.util.ArrayList;
import java.util.List;

public class TFCDecoBannerRenderer extends BannerRenderer implements BlockEntityRenderer<BannerBlockEntity>
{
    public TFCDecoBannerRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(BannerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        boolean isItem = blockEntity.getLevel() == null;
        poseStack.pushPose();
        long gameTime;
        TFCDecoDyeColor color = blockEntity instanceof TFCDecoBannerBlockEntity bannerBlockEntity ? bannerBlockEntity.getColor() : TFCDecoDyeColor.TERRACOTTA;
        if (isItem) {
            gameTime = 0L;
            poseStack.translate(0.5F, 0.5F, 0.5F);
            this.pole.visible = true;
        } else {
            gameTime = blockEntity.getLevel().getGameTime();
            BlockState blockstate = blockEntity.getBlockState();
            if (blockstate.getBlock() instanceof TFCDecoBannerBlock bannerBlock) {
                color = bannerBlock.getDyeColor();
                poseStack.translate(0.5F, 0.5F, 0.5F);
                float f1 = -RotationSegment.convertToDegrees(blockstate.getValue(BannerBlock.ROTATION));
                poseStack.mulPose(Axis.YP.rotationDegrees(f1));
                this.pole.visible = true;
            } else if (blockstate.getBlock() instanceof TFCDecoWallBannerBlock wallBannerBlock) {
                color = wallBannerBlock.getDyeColor();
                poseStack.translate(0.5F, -0.16666667F, 0.5F);
                float f3 = -blockstate.getValue(WallBannerBlock.FACING).toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f3));
                poseStack.translate(0.0F, -0.3125F, -0.4375F);
                this.pole.visible = false;
            }
        }

        poseStack.pushPose();
        poseStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        VertexConsumer vertexconsumer = ModelBakery.BANNER_BASE.buffer(bufferSource, RenderType::entitySolid);
        this.pole.render(poseStack, vertexconsumer, packedLight, packedOverlay);
        this.bar.render(poseStack, vertexconsumer, packedLight, packedOverlay);
        BlockPos blockpos = blockEntity.getBlockPos();
        float f2 = ((float)Math.floorMod(blockpos.getX() * 7L + blockpos.getY() * 9L + blockpos.getZ() * 13L + gameTime, 100L) + partialTick) / 100.0F;
        this.flag.xRot = (-0.0125F + 0.01F * Mth.cos(((float)Math.PI * 2F) * f2)) * (float)Math.PI;
        this.flag.y = -32.0F;
        List<Pair<Holder<BannerPattern>, DyeColor>> list = new ArrayList<>(blockEntity.getPatterns());
        renderPatterns(color, poseStack, bufferSource, packedLight, packedOverlay, this.flag, ModelBakery.BANNER_BASE, true, list, false);
        poseStack.popPose();
        poseStack.popPose();
    }

    public static void renderPatterns(TFCDecoDyeColor color, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, ModelPart flagModel, Material bannerBase, boolean isBanner, List<Pair<Holder<BannerPattern>, DyeColor>> patterns, boolean pWithGlint) {
        flagModel.render(poseStack, bannerBase.buffer(bufferSource, RenderType::entitySolid, pWithGlint), packedLight, packedOverlay);

        // Render base color
        var basePattern = BuiltInRegistries.BANNER_PATTERN.getHolderOrThrow(BannerPatterns.BASE).unwrapKey().get();
        var mat = isBanner ? Sheets.getBannerMaterial(basePattern) : Sheets.getShieldMaterial(basePattern);
        float[] colors = color.getTextureDiffuseColors();
        flagModel.render(poseStack, mat.buffer(bufferSource, RenderType::entityNoOutline), packedLight, packedOverlay, colors[0], colors[1], colors[2], 1.0F);

        patterns.remove(0);

        for(int i = 0; i < 17 && i < patterns.size(); ++i) {
            Pair<Holder<BannerPattern>, DyeColor> pair = patterns.get(i);
            float[] afloat = pair.getSecond().getTextureDiffuseColors();
            pair.getFirst().unwrapKey().map((pattern) -> isBanner ? Sheets.getBannerMaterial(pattern) : Sheets.getShieldMaterial(pattern)).ifPresent((material) -> {
                flagModel.render(poseStack, material.buffer(bufferSource, RenderType::entityNoOutline), packedLight, packedOverlay, afloat[0], afloat[1], afloat[2], 1.0F);
            });
        }
    }
}
