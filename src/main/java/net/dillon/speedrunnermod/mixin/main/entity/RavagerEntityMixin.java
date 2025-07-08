package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(RavagerEntity.class)
public class RavagerEntityMixin {

    /**
     * Modifies {@code ravanger} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends RavagerEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 100.0D : 50.0D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 16.0D : 10.0D);
        ModUtil.modifyAttackKnockback(dis, isDoomMode() ? 1.6D : 1.1D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 48.0D : 32.0D);
    }

    /**
     * Inflicts players with {@code slowness} when attacking.
     */
    @Inject(method = "tryAttack", at = @At("RETURN"))
    private void tryAttack(ServerWorld world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && target instanceof PlayerEntity) {
            ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsInTicks(10), 0));
        }
    }
}