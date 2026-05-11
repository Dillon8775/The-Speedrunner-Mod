package net.dillon.speedrunnermod.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
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
    @Inject(method = "getMobArrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;setBaseDamageFromMob(F)V"))
    private static void createSlownessArrow(LivingEntity entity, ItemStack stack, float damageModifier, ItemStack bow, CallbackInfoReturnable<AbstractArrow> cir, @Local AbstractArrow persistantProjectileEntity) {
        if (isDoomMode() && entity instanceof AbstractSkeleton && persistantProjectileEntity instanceof Arrow) {
            ((Arrow)persistantProjectileEntity).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ModUtil.secondsAsTicks(10), 0));
        }
    }
}