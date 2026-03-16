package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Dolphin.DolphinSwimWithPlayerGoal.class)
public class DolphinEntitySwimWithPlayerGoalMixin {

    /**
     * Increases the range that a dolphin can detect a player and give them the {@code dolphins grade} status effect.
     */
    @ModifyArg(method = "start", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;I)V"), index = 1)
    private int increaseDolphinsRangePlayerDetectionRadius(int x) {
        return ModUtil.DOLPHIN_RANGE;
    }
}