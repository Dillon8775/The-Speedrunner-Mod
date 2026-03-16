package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SmallFireball.class)
public class SmallFireballMixin {

    /**
     * Decreases the fire time from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;igniteForSeconds(F)V"))
    private float changeFireballFireTime(float x) {
        return ModUtil.getFireballFireDamageTime();
    }

    /**
     * Decreases damage dealt from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 2)
    private float changeFireballDamageAmount(float x) {
        return ModUtil.getFireballDamageValue();
    }
}