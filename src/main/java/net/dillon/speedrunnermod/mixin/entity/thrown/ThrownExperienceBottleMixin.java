package net.dillon.speedrunnermod.mixin.entity.thrown;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownExperienceBottle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThrownExperienceBottle.class)
public class ThrownExperienceBottleMixin {

    /**
     * Increases the amount of experience that drops from experience bottles.
     */
    @ModifyConstant(method = "onHit", constant = @Constant(intValue = 3))
    private int increaseExperienceSpawned(int constant) {
        return ModConstants.getIncreasedExperienceAmount();
    }
}