package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSystem.User.class)
public interface VibrationsCallbackMixin {

    /**
     * Allows players to safely run by or across {@code sculk sensor blocks} if the player is wearing {@code speedrunner boots.}
     */
    @Inject(method = "isValidVibration", at = @At("RETURN"), cancellable = true)
    private void playerIsSafeWithSpeedrunnerBoots(Holder<GameEvent> gameEvent, GameEvent.Context emitter, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = emitter.sourceEntity();
        if (entity instanceof Player player && player.getItemBySlot(EquipmentSlot.FEET).is(ModItemTags.SCULK_SENSOR_SAFE_BOOTS)) {
            cir.setReturnValue(false);
        }
    }
}