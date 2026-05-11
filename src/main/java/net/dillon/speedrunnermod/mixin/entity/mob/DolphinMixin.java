package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.dolphin.Dolphin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Increases the range that a dolphin can detect a player.
 */
@Mixin(Dolphin.class)
public class DolphinMixin {
    @Shadow
    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(ModUtil.DOLPHIN_PREDICATE_RANGE).ignoreLineOfSight();
}