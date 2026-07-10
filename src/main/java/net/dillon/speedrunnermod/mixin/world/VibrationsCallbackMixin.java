package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSystem.User.class)
public interface VibrationsCallbackMixin {

    /**
     * Allows players to safely run by or across {@code sculk sensor blocks} if the player has the sculk shrieker protection attribute.
     */
    @Inject(method = "isValidVibration", at = @At("RETURN"), cancellable = true)
    private void entityIsSafeSculkShrieker(Holder<GameEvent> gameEvent, GameEvent.Context emitter, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = emitter.sourceEntity();
        if (entity instanceof LivingEntity living) {
            float sculkShriekerProtection = (float)living.getAttributeValue(ModAttributes.SHRIEKER_STEALTH);
            if (sculkShriekerProtection > 1.0F) {
                cir.setReturnValue(false);
            }
        }
    }
}