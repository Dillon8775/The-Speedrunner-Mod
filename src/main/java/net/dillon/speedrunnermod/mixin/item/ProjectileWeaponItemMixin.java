package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.item.tool.SpeedrunnerBowItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {

    /**
     * Modifies bow base power for {@link SpeedrunnerBowItem}, to make it stronger.
     */
    @Inject(method = "createProjectile", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void modifyBowPower(Level level, LivingEntity shooter, ItemStack weapon, ItemStack projectile, boolean isCrit, CallbackInfoReturnable<Projectile> cir, ArrowItem arrowItem, AbstractArrow arrow, Item var8) {
        if (!(weapon.getItem() instanceof SpeedrunnerBowItem)) {
            return;
        }

        arrow.setBaseDamageFromMob(SpeedrunnerBowItem.getPower(shooter));
    }
}