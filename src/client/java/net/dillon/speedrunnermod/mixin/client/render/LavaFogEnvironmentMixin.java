package net.dillon.speedrunnermod.mixin.client.render;

import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.LavaFogEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(LavaFogEnvironment.class)
public class LavaFogEnvironmentMixin {

    /**
     * Increases lava vision distance.
     */
    @Redirect(method = "setupFog", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/fog/FogData;environmentalEnd:F", ordinal = 1))
    private void applyIncreasedLavaVisionDistance(FogData instance, float value) {
        if (clientOptions().client.increasedLavaVision.getCurrentValue()) {
            instance.environmentalEnd = 35.0F;
        }
    }
}