package net.dillon.speedrunnermod.client.render;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * The renderer class for the {@code speedrunner shield.}
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerShieldModelRenderer implements SpecialModelRenderer<ComponentMap> {
    private final ShieldEntityModel shieldModel;
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base"));
    private static final SpriteIdentifier SPEEDRUNNER_SHIELD_BASE_NO_PATTERN = new SpriteIdentifier(TexturedRenderLayers.SHIELD_PATTERNS_ATLAS_TEXTURE, Identifier.of("entity/speedrunner_shield_base_no_pattern"));

    public SpeedrunnerShieldModelRenderer(ShieldEntityModel model) {
        this.shieldModel = model;
    }

    /**
     * Copied over from {@link net.minecraft.client.render.item.model.special.ShieldModelRenderer}.
     * <p>Creates the renderer for the {@code speedrunner shield.}</p>
     */
    @Override
    public void render(
            @Nullable ComponentMap componentMap,
            ItemDisplayContext itemDisplayContext,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            int j,
            boolean bl
    ) {
        BannerPatternsComponent bannerPatternsComponent = componentMap != null
                ? (BannerPatternsComponent)componentMap.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                : BannerPatternsComponent.DEFAULT;
        DyeColor dyeColor = componentMap != null ? (DyeColor)componentMap.get(DataComponentTypes.BASE_COLOR) : null;
        boolean bl2 = !bannerPatternsComponent.layers().isEmpty() || dyeColor != null;
        matrixStack.push();
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl2 ? SPEEDRUNNER_SHIELD_BASE : SPEEDRUNNER_SHIELD_BASE_NO_PATTERN;
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite()
                .getTextureSpecificVertexConsumer(
                        ItemRenderer.getItemGlintConsumer(
                                vertexConsumerProvider, this.shieldModel.getLayer(spriteIdentifier.getAtlasId()), itemDisplayContext == ItemDisplayContext.GUI, bl
                        )
                );
        this.shieldModel.getHandle().render(matrixStack, vertexConsumer, i, j);
        if (bl2) {
            BannerBlockEntityRenderer.renderCanvas(
                    matrixStack,
                    vertexConsumerProvider,
                    i,
                    j,
                    this.shieldModel.getPlate(),
                    spriteIdentifier,
                    false,
                    (DyeColor)Objects.requireNonNullElse(dyeColor, DyeColor.WHITE),
                    bannerPatternsComponent,
                    bl,
                    false
            );
        } else {
            this.shieldModel.getPlate().render(matrixStack, vertexConsumer, i, j);
        }

        matrixStack.pop();
    }

    @Override
    public @Nullable ComponentMap getData(ItemStack stack) {
        return stack.getImmutableComponents();
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final SpeedrunnerShieldModelRenderer.Unbaked INSTANCE = new SpeedrunnerShieldModelRenderer.Unbaked();
        public static final MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<SpeedrunnerShieldModelRenderer.Unbaked> getCodec() {
            return CODEC;
        }

        @Override
        public SpecialModelRenderer<?> bake(LoadedEntityModels entityModels) {
            return new SpeedrunnerShieldModelRenderer(new ShieldEntityModel(entityModels.getModelPart(EntityModelLayers.SHIELD)));
        }
    }
}