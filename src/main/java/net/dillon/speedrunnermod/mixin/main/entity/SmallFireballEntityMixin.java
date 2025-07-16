package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
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
    private float changeFireballFireTime(float x) {
        return ModUtil.getFireballFireDamageTime();
    }

    /**
     * Decreases damage dealt from small fireballs (or blaze fireballs).
     */
    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 2)
    private float changeFireballDamageAmount(float x) {
        return ModUtil.getFireballDamageValue();
    }
}