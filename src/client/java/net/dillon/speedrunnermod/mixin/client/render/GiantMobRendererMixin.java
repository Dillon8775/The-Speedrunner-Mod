package net.dillon.speedrunnermod.mixin.client.render;

import net.minecraft.client.renderer.entity.GiantMobRenderer;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Gives Goliath a unique texture.
 */
@Mixin(GiantMobRenderer.class)
public class GiantMobRendererMixin {
    @Shadow
    private static final Identifier ZOMBIE_LOCATION = ofSpeedrunnerMod("textures/entity/goliath/goliath.png");
}