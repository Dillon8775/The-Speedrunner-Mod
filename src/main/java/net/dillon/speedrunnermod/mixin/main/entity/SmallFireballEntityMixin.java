package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModConstants;
import net.minecraft.entity.projectile.SmallFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SmallFireballEntity.class)
public class SmallFireballEntityMixin {

    /**
     * Decreases the fire time from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
    private float onEntityHitFireTime(float x) {
        return ModConstants.FIREBALL_FIRE_TIME;
    }

    /**
     * Decreases damage dealt from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 2)
    private float onEntityHitDamage(float x) {
        return ModConstants.FIREBALL_DAMAGE_VALUE;
    }
}