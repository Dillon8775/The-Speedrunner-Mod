package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerCrossbowItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    /**
     * Modifies the power for {@link SpeedrunnerCrossbowItem}, to make it stronger.
     */
    @Inject(method = "createProjectile", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void modifyPower(Level level, LivingEntity shooter, ItemStack heldItem, ItemStack projectile, boolean isCrit, CallbackInfoReturnable<Projectile> cir, Projectile projectileEntity) {
        if (!(heldItem.getItem() instanceof SpeedrunnerCrossbowItem)) {
            return;
        }

        if (projectileEntity instanceof AbstractArrow actualArrow) {
            actualArrow.setBaseDamageFromMob(SpeedrunnerCrossbowItem.getPower(shooter));
        }
    }

    /**
     * Modifies the charge duration for {@link SpeedrunnerCrossbowItem}, to make it quicker.
     */
    @Redirect(method = "onUseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;getChargeDuration(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I"))
    private int modfiyChargeDuration(ItemStack crossbow, LivingEntity user) {
        if (!(crossbow.getItem() instanceof SpeedrunnerCrossbowItem)) {
            return CrossbowItem.getChargeDuration(crossbow, user);
        }

        return SpeedrunnerCrossbowItem.getChargeSpeed(crossbow, user);
    }

    /**
     * Grants the speedy betsy advancement to the player, for using a {@link SpeedrunnerCrossbowItem}.
     */
    @Inject(method = "performShooting", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/triggers/ShotCrossbowTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/item/ItemStack;)V"))
    private void speedyBetsy(Level level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, float power, float uncertainty, LivingEntity targetOverride, CallbackInfo ci) {
        if (!(weapon.getItem() instanceof SpeedrunnerCrossbowItem speedrunnerCrossbowItem) || (!(shooter instanceof ServerPlayer serverPlayer))) {
            return;
        }

        ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(serverPlayer, new ItemStack(speedrunnerCrossbowItem));
    }
}