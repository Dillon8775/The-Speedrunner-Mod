package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(SilverfishEntity.class)
public class SilverfishEntityMixin {

    /**
     * Modifies {@code silverfish} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSilverfishAttributes(EntityType<? extends SilverfishEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 8.0D : 4.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.25D : 0.15D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 2.0D : 0.01D);
    }
}