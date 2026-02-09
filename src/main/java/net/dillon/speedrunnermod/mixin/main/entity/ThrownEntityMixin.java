package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrownEntity.class)
public class ThrownEntityMixin {

    /**
     * Makes the {@code InfiniPearl} faster in water.
     */
    @ModifyArg(method = "applyDrag", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;multiply(D)Lnet/minecraft/util/math/Vec3d;"))
    private double cancelPearlDrag(double value) {
        if ((ThrownEntity)(Object)this instanceof ThrownItemEntity thrownItemEntity) {
            if (thrownItemEntity.isTouchingWater() && thrownItemEntity.getStack().getItem().getDefaultStack().isOf(ModItems.INFINI_PEARL)) {
                return 0.93F;
            }
        }
        return value;
    }
}