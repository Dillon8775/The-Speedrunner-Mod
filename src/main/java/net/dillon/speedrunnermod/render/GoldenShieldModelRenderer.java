package net.dillon.speedrunnermod.render;

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
 * The renderer class for the {@code golden shield.}
 */
public class GoldenShieldModelRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final SpriteGetter sprites;
    private final ShieldModel model;

    public GoldenShieldModelRenderer(final SpriteGetter sprites, final ShieldModel model) {
        this.sprites = sprites;
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(final ItemStack stack) {
        return stack.immutableComponents();
    }

    @Override
    public void submit(final @Nullable DataComponentMap components, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final int overlayCoords, final boolean hasFoil, final int outlineColor) {
        BannerPatternLayers patterns = components != null ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;
        SpriteId base = hasPatterns ? ModSheets.GOLDEN_SHIELD_BASE : ModSheets.GOLDEN_SHIELD_BASE_NO_PATTERN;
        if (hasFoil && !hasPatterns) {
            submitNodeCollector.submitModel(
                    this.model,
                    Unit.INSTANCE,
                    poseStack,
                    RenderTypes.entitySolidGlint(base.atlasLocation()),
                    lightCoords,
                    overlayCoords,
                    -1,
                    this.sprites.get(base),
                    outlineColor
            );
        } else {
            submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base, this.sprites, outlineColor);
        }

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
                    Objects.requireNonNullElse(baseColor, DyeColor.WHITE),
                    patterns
            );
            if (hasFoil) {
                submitNodeCollector.order(patterns.layers().size() + 2)
                        .submitModel(this.model, Unit.INSTANCE, poseStack, RenderTypes.patternedShieldGlint(), lightCoords, overlayCoords, -1, this.sprites.get(base), 0);
            }
        }
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {
        public static final GoldenShieldModelRenderer.Unbaked INSTANCE = new GoldenShieldModelRenderer.Unbaked();
        public static final MapCodec<GoldenShieldModelRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<GoldenShieldModelRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public GoldenShieldModelRenderer bake(final SpecialModelRenderer.BakingContext context) {
            return new GoldenShieldModelRenderer(context.sprites(), new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }
    }
}