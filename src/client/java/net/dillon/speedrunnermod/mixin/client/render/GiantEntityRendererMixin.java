package net.dillon.speedrunnermod.mixin.client.render;

import net.minecraft.client.render.entity.GiantEntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Gives Goliath a unique texture.
 */
@Mixin(GiantEntityRenderer.class)
public class GiantEntityRendererMixin {
    @Shadow
    private static final Identifier TEXTURE = ofSpeedrunnerMod("textures/entity/goliath/goliath.png");
}