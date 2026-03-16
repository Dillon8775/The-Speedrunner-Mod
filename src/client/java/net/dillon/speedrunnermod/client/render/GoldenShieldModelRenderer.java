package net.dillon.speedrunnermod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * The renderer class for the {@code golden shield.}
 */
public class GoldenShieldModelRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final MaterialSet spriteHolder;
    private final ShieldModel model;
    private static final Material GOLDEN_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, Identifier.parse("entity/golden_shield_base"));
    private static final Material GOLDEN_SHIELD_BASE_NO_PATTERN = new Material(Sheets.SHIELD_SHEET, Identifier.parse("entity/golden_shield_base_no_pattern"));

    public GoldenShieldModelRenderer(MaterialSet spriteHolder, ShieldModel model) {
        this.spriteHolder = spriteHolder;
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(ItemStack itemStack) {
        return itemStack.immutableComponents();
    }

    @Override
    public void submit(
            @Nullable DataComponentMap componentMap,
            ItemDisplayContext itemDisplayContext,
            PoseStack matrixStack,
            SubmitNodeCollector orderedRenderCommandQueue,
            int i,
            int j,
            boolean bl,
            int k
    ) {
        BannerPatternLayers bannerPatternsComponent = componentMap != null
                ? (BannerPatternLayers)componentMap.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor dyeColor = componentMap != null ? (DyeColor)componentMap.get(DataComponents.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrixStack.pushPose();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        Material spriteIdentifier = bl2 ? GOLDEN_SHIELD_BASE : GOLDEN_SHIELD_BASE_NO_PATTERN;
        orderedRenderCommandQueue.submitModelPart(
                this.model.handle(),
                matrixStack,
                this.model.renderType(spriteIdentifier.atlasLocation()),
                i,
                j,
                this.spriteHolder.get(spriteIdentifier),
                false,
                false,
                -1,
                null,
                k
        );
        if (bl2) {
            BannerRenderer.submitPatterns(
                    this.spriteHolder,
                    matrixStack,
                    orderedRenderCommandQueue,
                    i,
                    j,
                    this.model,
                    Unit.INSTANCE,
                    spriteIdentifier,
                    false,
                    (DyeColor)Objects.requireNonNullElse(dyeColor, DyeColor.WHITE),
                    bannerPatternsComponent,
                    bl,
                    null,
                    k
            );
        } else {
            orderedRenderCommandQueue.submitModelPart(
                    this.model.plate(),
                    matrixStack,
                    this.model.renderType(spriteIdentifier.atlasLocation()),
                    i,
                    j,
                    this.spriteHolder.get(spriteIdentifier),
                    false,
                    bl,
                    -1,
                    null,
                    k
            );
        }

        matrixStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> consumer) {
        PoseStack matrixStack = new PoseStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(matrixStack, consumer);
    }


    public record Unbaked() implements net.minecraft.client.renderer.special.SpecialModelRenderer.Unbaked {
        public static final GoldenShieldModelRenderer.Unbaked INSTANCE = new GoldenShieldModelRenderer.Unbaked();
        public static final MapCodec<GoldenShieldModelRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<GoldenShieldModelRenderer.Unbaked> type() {
            return CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            return new GoldenShieldModelRenderer(context.materials(), new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}