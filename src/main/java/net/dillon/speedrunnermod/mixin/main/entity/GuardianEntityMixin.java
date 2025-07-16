package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(GuardianEntity.class)
public class GuardianEntityMixin {

    /**
     * Modifies {@code guardian} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeGuardianAttributes(EntityType<? extends GuardianEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 7.0D : 3.0D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 24.0D : 8.0D);
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 35.0D : 15.0D);
    }
}