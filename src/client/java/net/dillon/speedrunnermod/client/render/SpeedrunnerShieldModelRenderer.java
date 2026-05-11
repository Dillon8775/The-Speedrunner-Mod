package net.dillon.speedrunnermod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
public class SpeedrunnerShieldModelRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final SpriteGetter sprites;
    private final ShieldModel model;

    public SpeedrunnerShieldModelRenderer(final SpriteGetter sprites, final ShieldModel model) {
        this.sprites = sprites;
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(final ItemStack stack) {
        return stack.immutableComponents();
    }

    public void submit(
            final @Nullable DataComponentMap components,
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            final boolean hasFoil,
            final int outlineColor
    ) {
        BannerPatternLayers patterns = components != null
                ? (BannerPatternLayers)components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? (DyeColor)components.get(DataComponents.BASE_COLOR) : null;
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;
        SpriteId base = hasPatterns ? ModSheets.SPEEDRUNNER_SHIELD_BASE : ModSheets.SPEEDRUNNER_SHIELD_BASE_NO_PATTERN;
        submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base, this.sprites, outlineColor, null);
        if (hasPatterns) {
            BannerRenderer.submitPatterns(
                    this.sprites,
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    overlayCoords,
                    this.model,
                    Unit.INSTANCE,
                    false,
                    (DyeColor)Objects.requireNonNullElse(baseColor, DyeColor.WHITE),
                    patterns,
                    null
            );
        }

        if (hasFoil) {
            submitNodeCollector.submitModel(
                    this.model, Unit.INSTANCE, poseStack, RenderTypes.entityGlint(), lightCoords, overlayCoords, -1, this.sprites.get(base), 0, null
            );
        }
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {
        public static final SpeedrunnerShieldModelRenderer.Unbaked INSTANCE = new SpeedrunnerShieldModelRenderer.Unbaked();
        public static final MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpeedrunnerShieldModelRenderer bake(final SpecialModelRenderer.BakingContext context) {
            return new SpeedrunnerShieldModelRenderer(context.sprites(), new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}