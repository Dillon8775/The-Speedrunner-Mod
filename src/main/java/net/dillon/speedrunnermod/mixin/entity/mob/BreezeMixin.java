package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.breeze.Breeze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Breeze.class)
public class BreezeMixin {

    /**
     * Modifies {@code breeze} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeBreezeAttributes(CallbackInfo ci) {
        Monster dis = (Monster)(Object)this;
        ModAttributeHelper.modifyMovementSpeed(dis, ModCommonOptions.doomOrDefault(0.65D, 0.50D));
        ModAttributeHelper.modifyMaxHealth(dis, ModCommonOptions.doomOrDefault(35.0D, 25.0D));
        ModAttributeHelper.modifyFollowRange(dis, ModCommonOptions.doomOrDefault(32.0D, 16.0D));
        ModAttributeHelper.modifyAttackDamage(dis, ModCommonOptions.doomOrDefault(3.5D, 2.0D));
    }
}