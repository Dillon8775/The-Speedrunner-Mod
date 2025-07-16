package net.dillon.speedrunnermod.mixin.main.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    /**
     * Makes skeleton's shoot slowness arrows if {@code doom mode} is enabled.
     */
    @Inject(method = "createArrowProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;applyDamageModifier(F)V"))
    private static void createSlownessArrow(LivingEntity entity, ItemStack stack, float damageModifier, ItemStack bow, CallbackInfoReturnable<PersistentProjectileEntity> cir, @Local PersistentProjectileEntity persistantProjectileEntity) {
        if (isDoomMode() && entity instanceof AbstractSkeletonEntity && persistantProjectileEntity instanceof ArrowEntity) {
            ((ArrowEntity)persistantProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsAsTicks(10), 0));
        }
    }
}