package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrowableProjectile.class)
public class ThrowableProjectileMixin {

    /**
     * Makes the {@code InfiniPearl} faster in water.
     */
    @ModifyArg(method = "applyInertia", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;"))
    private double cancelPearlDrag(double value) {
        if ((ThrowableProjectile)(Object)this instanceof ThrowableItemProjectile thrownItemEntity) {
            if (thrownItemEntity.isInWater() && thrownItemEntity.getItem().getItem().getDefaultInstance().is(ModItems.INFINI_PEARL)) {
                return 0.93F;
            }
        }
        return value;
    }
}