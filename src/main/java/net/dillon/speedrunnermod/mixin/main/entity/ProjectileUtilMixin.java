package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ProjectileUtil.class)
public class ProjectileUtilMixin {

    /**
     * @author Dillon8775
     * @reason Makes skeleton's shoot slowness arrows if {@code doom mode} is enabled.
     */
    @Overwrite
    public static PersistentProjectileEntity createArrowProjectile(LivingEntity entity, ItemStack stack, float damageModifier, @Nullable ItemStack bow) {
        ArrowItem arrowItem = (ArrowItem)(stack.getItem() instanceof ArrowItem ? stack.getItem() : Items.ARROW);
        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(entity.getWorld(), stack, entity, bow);
        persistentProjectileEntity.applyDamageModifier(damageModifier);
        if (options().main.playingMode.doom() && entity instanceof AbstractSkeletonEntity && persistentProjectileEntity instanceof ArrowEntity) {
            ((ArrowEntity)persistentProjectileEntity).addEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(10), 0));
        }
        return persistentProjectileEntity;
    }
}