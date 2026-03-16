package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.monster.Silverfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Silverfish.SilverfishWakeUpFriendsGoal.class)
public class SilverfishEntityCallForHelpGoalMixin {
    @Shadow
    int lookForFriends;

    /**
     * Increases the delay for silverfish to spawn more silverfish when being hit, block broken, etc.
     */
    @Redirect(method = "notifyHurt", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Silverfish$SilverfishWakeUpFriendsGoal;lookForFriends:I", ordinal = 0))
    private int changeSilverfishCallForHelpDelay(Silverfish.SilverfishWakeUpFriendsGoal callForHelpGoal) {
        return this.lookForFriends = ModUtil.getSilverfishCallForHelpDelay();
    }
}