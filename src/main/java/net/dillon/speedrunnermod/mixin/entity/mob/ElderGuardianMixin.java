package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(ElderGuardian.class)
public class ElderGuardianMixin {

    /**
     * Modifies {@code elder guardian} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeElderGuardianAttributes(EntityType<? extends ElderGuardian> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMovementSpeed(dis, 0.30000001192092896D);
        ModAttributeHelper.modifyAttackDamage(dis, isDoomMode() ? 8.0D : 4.0D);
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 50.0D : 25.0D);
    }

    /**
     * Changes random chance that the player will get the mining fatigue effect.
     */
    @ModifyConstant(method = "customServerAiStep", constant = @Constant(intValue = 1200))
    private int changeRandom(int constant) {
        return isDoomMode() ? 6000 : 1200;
    }

    /**
     * Changes the {@code mining fatigue duration} applied by elder guardians.
     */
    @ModifyConstant(method = "customServerAiStep", constant = @Constant(intValue = 6000))
    private int changeMiningFatigueDuration(int constant) {
        return isDoomMode() ? Arithmetics.mas(5) : Arithmetics.sas(30);
    }

    /**
     * Changes the {@code radius} that the player must be in from the elder guardian to receive the {@code mining fatigue effect.}
     */
    @ModifyConstant(method = "customServerAiStep", constant = @Constant(doubleValue = 50.0D))
    private double changePlayerRadius(double constant) {
        return isDoomMode() ? 55.0D : 25.0D;
    }
}