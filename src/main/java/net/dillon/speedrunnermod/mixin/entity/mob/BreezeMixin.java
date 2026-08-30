package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.breeze.Breeze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

@Mixin(Breeze.class)
public class BreezeMixin {

    /**
     * Modifies {@code breeze} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeBreezeAttributes(CallbackInfo ci) {
        Monster dis = (Monster)(Object)this;
        ModAttributeHelper.modifyMovementSpeed(dis, isDoomMode() ? 0.65D : 0.50D);
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 35.0D : 25.0D);
        ModAttributeHelper.modifyFollowRange(dis, isDoomMode() ? 32.0D : 16.0D);
        ModAttributeHelper.modifyAttackDamage(dis, isDoomMode() ? 3.5D : 2.0D);
    }
}