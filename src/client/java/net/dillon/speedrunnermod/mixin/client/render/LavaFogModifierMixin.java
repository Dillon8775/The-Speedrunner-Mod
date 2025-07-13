package net.dillon.speedrunnermod.mixin.client.render;

import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.LavaFogModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(LavaFogModifier.class)
public class LavaFogModifierMixin {

    /**
     * Increases lava vision distance.
     */
    @Redirect(method = "applyStartEndModifier", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/fog/FogData;environmentalEnd:F", ordinal = 1))
    private void modifyFireResistanceFogEnd(FogData instance, float value) {
        if (clientOptions().client.increasedLavaVision.getCurrentValue()) {
            instance.environmentalEnd = 35.0F;
        }
    }
}