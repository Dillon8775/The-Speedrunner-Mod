package net.dillon.speedrunnermod.mixin.client.render;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    /**
     * Removes fog from the game when using the keybind.
     */
    @Inject(method = "setupFog", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void modifyFogEnd(Camera camera, int renderDistanceInChunks, DeltaTracker deltaTracker, float darkenWorldAmount, ClientLevel level, CallbackInfoReturnable<FogData> cir, float partialTickTime, float renderDistanceInBlocks, FogType fogType, Entity entity, FogData fog, float renderDistanceFogSpan) {
        if (entity instanceof LivingEntity livingEntity &&
                !livingEntity.hasEffect(MobEffects.BLINDNESS) &&
                !livingEntity.hasEffect(MobEffects.DARKNESS) &&
                !clientOptions().client.fog.getCurrentValue() &&
                fogType != FogType.WATER &&
                fogType != FogType.LAVA &&
                fogType != FogType.POWDER_SNOW) {
            fog.renderDistanceEnd = Integer.MAX_VALUE;
            fog.environmentalEnd = Integer.MAX_VALUE;
        }
    }
}