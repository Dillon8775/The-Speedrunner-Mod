package net.dillon.speedrunnermod.mixin.entity.thrown;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

@Mixin(SmallFireball.class)
public class SmallFireballMixin {

    /**
     * Decreases the fire time from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;igniteForSeconds(F)V"))
    private float changeFireballFireTime(float x) {
        return doomOrDefault(6, 3);
    }

    /**
     * Decreases damage dealt from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 2)
    private float changeFireballDamageAmount(float x) {
        return ModConstants.getSmallFireballDamageValue();
    }
}