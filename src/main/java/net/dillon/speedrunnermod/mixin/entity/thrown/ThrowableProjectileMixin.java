package net.dillon.speedrunnermod.mixin.entity.thrown;

import net.dillon.speedrunnermod.helper.InfiniPearl;
import net.dillon.speedrunnermod.item.InfiniPearlItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrowableProjectile.class)
public abstract class ThrowableProjectileMixin extends Projectile {

    public ThrowableProjectileMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    /**
     * Gives the {@link InfiniPearlItem} additional inertia in water.
     */
    @ModifyArg(method = "applyInertia", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;"))
    private double increaseInertia(double value) {
        ThrowableProjectile self = (ThrowableProjectile) (Object)this;
        if (!(self instanceof ThrownEnderpearl thrownEnderpearl)) {
            return value;
        }

        if (!(thrownEnderpearl instanceof InfiniPearl infiniPearl)) {
            return value;
        }

        Entity owner = thrownEnderpearl.getOwner();
        if (!(owner instanceof Player)) {
            return value;
        }

        if (thrownEnderpearl.isInWater()) {
            return value + infiniPearl.getInertia();
        }
        return value;
    }
}