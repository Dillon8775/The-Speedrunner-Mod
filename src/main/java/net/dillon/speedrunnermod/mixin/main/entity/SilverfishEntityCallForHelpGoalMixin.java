package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.mob.SilverfishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SilverfishEntity.CallForHelpGoal.class)
public class SilverfishEntityCallForHelpGoalMixin {
    @Shadow
    int delay;

    /**
     * Increases the delay for silverfish to spawn more silverfish when being hit, block broken, etc.
     */
    @Redirect(method = "onHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/SilverfishEntity$CallForHelpGoal;delay:I", ordinal = 0))
    private int changeSilverfishCallForHelpDelay(SilverfishEntity.CallForHelpGoal callForHelpGoal) {
        return this.delay = ModUtil.getSilverfishCallForHelpDelay();
    }
}