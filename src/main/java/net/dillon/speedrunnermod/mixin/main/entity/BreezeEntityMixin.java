package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(BreezeEntity.class)
public class BreezeEntityMixin {

    /**
     * Modifies {@code breeze} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeBreezeAttributes(CallbackInfo ci) {
        HostileEntity dis = (HostileEntity)(Object)this;
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.65D : 0.50D);
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 35.0D : 25.0D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 32.0D : 16.0D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 3.5D : 2.0D);
    }
}