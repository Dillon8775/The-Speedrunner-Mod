package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(WitherSkeletonEntity.class)
public class WitherSkeletonEntityMixin {

    /**
     * Lowers attack damage from wither skeletons.
     */
    @ModifyArg(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/EntityAttributeInstance;setBaseValue(D)V"))
    private double genericAttackDamage(double baseValue) {
        return isDoomMode() ? 10.0D : 1.0D;
    }

    /**
     * Decreases the amplifier of the wither effect when wither skeleton's attack.
     */
    @ModifyArg(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/registry/entry/RegistryEntry;I)V"), index = 1)
    private int tryAttack(int x) {
        return ModUtil.getWitherSkeletonWitherEffectDuration();
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Inject(method = "tryAttack", at = @At("RETURN"))
    private void tryAttack(ServerWorld world, Entity target, CallbackInfoReturnable<?> cir) {
        if (isDoomMode() && target instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsInTicks(10), 0));
        }
    }
}