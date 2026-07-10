package net.dillon.speedrunnermod.mixin.client.render;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.fog.environment.WaterFogEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WaterFogEnvironment.class)
public class WaterFogEnvironmentMixin {

    /**
     * Increases underwater fog vision.
     */
    @Redirect(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;getWaterVision()F"))
    private float increaseWaterFogDistance(LocalPlayer player) {
        float underwaterVision = (float) player.getAttributeValue(ModAttributes.UNDERWATER_VISION);
        float waterVision = player.getWaterVision();
        if (underwaterVision > 1.0F) {
            return waterVision + underwaterVision;
        }

        return waterVision;
    }
}