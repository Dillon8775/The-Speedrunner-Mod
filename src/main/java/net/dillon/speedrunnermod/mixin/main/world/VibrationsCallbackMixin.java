package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.Vibrations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Vibrations.Callback.class)
public interface VibrationsCallbackMixin {

    /**
     * Allows players to safely run by or across {@code sculk sensor blocks} if the player is wearing {@code speedrunner boots.}
     */
    @Inject(method = "canAccept", at = @At("RETURN"), cancellable = true)
    private void playerIsSafeWithSpeedrunnerBoots(RegistryEntry<GameEvent> gameEvent, GameEvent.Emitter emitter, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = emitter.sourceEntity();
        if (entity instanceof PlayerEntity player && player.getEquippedStack(EquipmentSlot.FEET).isIn(ModItemTags.SCULK_SENSOR_SAFE_BOOTS)) {
            cir.setReturnValue(false);
        }
    }
}