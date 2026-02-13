package net.dillon.speedrunnermod.client.render;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
public class SpeedrunnerShieldModelRenderer implements SpecialModelRenderer<ComponentMap> {
    private final SpriteHolder spriteHolder;
    private final ShieldEntityModel model;
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base"));
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base_no_pattern"));

    public SpeedrunnerShieldModelRenderer(SpriteHolder spriteHolder, ShieldEntityModel model) {
        this.spriteHolder = spriteHolder;
        this.model = model;
    }

    @Nullable
    public ComponentMap getData(ItemStack itemStack) {
        return itemStack.getImmutableComponents();
    }

    @Override
    public void render(
            @Nullable ComponentMap componentMap,
            ItemDisplayContext itemDisplayContext,
            MatrixStack matrixStack,
            OrderedRenderCommandQueue orderedRenderCommandQueue,
            int i,
            int j,
            boolean bl,
            int k
    ) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null
                ? (BannerPatternsComponent)componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? (DyeColor)componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl2 ? SPEEDRUNNER_SHIELD_BASE : SPEEDRUNNER_SHIELD_BASE_NO_PATTERN;
        orderedRenderCommandQueue.submitModelPart(
                this.model.getHandle(),
                matrixStack,
                this.model.getLayer(spriteIdentifier.getAtlasId()),
                i,
                j,
                this.spriteHolder.getSprite(spriteIdentifier),
                false,
                false,
                -1,
                null,
                k
        );
        if (bl2) {
            BannerBlockEntityRenderer.renderCanvas(
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
                    this.model.getPlate(),
                    matrixStack,
                    this.model.getLayer(spriteIdentifier.getAtlasId()),
                    i,
                    j,
                    this.spriteHolder.getSprite(spriteIdentifier),
                    false,
                    bl,
                    -1,
                    null,
                    k
            );
        }

        matrixStack.pop();
    }

    @Override
    public void collectVertices(Consumer<Vector3fc> consumer) {
        MatrixStack matrixStack = new MatrixStack();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        this.model.getRootPart().collectVertices(matrixStack, consumer);
    }


    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final SpeedrunnerShieldModelRenderer.Unbaked INSTANCE = new SpeedrunnerShieldModelRenderer.Unbaked();
        public static final MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakeContext context) {
            return new SpeedrunnerShieldModelRenderer(context.spriteHolder(), new ShieldEntityModel(context.entityModelSet().getModelPart(EntityModelLayers.SHIELD)));
        }
    }
}