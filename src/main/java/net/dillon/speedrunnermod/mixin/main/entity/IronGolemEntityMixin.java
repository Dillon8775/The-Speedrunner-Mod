package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin {

    /**
     * Modifies {@code golem} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeIronGolemAttributes(EntityType<? extends IronGolemEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 100.0D : 50.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.3D : 0.25D);
        ModUtil.modifyKnockbackResistance(dis, isDoomMode() ? 0.7D : 0.5D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 20.0D : 7.0D);
    }
}