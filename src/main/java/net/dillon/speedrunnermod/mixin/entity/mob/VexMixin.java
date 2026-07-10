package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Vex.class)
public class VexMixin extends Monster {

    public VexMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code vex} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeVexAttributes(EntityType<? extends Vex> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMaxHealth(this, isDoomMode() ? 7.0D : 14.0D);
        ModAttributeHelper.modifyAttackDamage(this, isDoomMode() ? 3.0D : 4.0D);
    }

    /**
     * @author Dillon8775
     * @reason Disables vexes from {@code noClipping} on {@code doom mode.}
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Vex;noPhysics:Z"))
    private void preventNoClippingOnDoomMode(Vex vex, boolean value) {
        vex.noPhysics = !isDoomMode();
    }

    /**
     * Increases the damage dealt to themselves when decaying.
     */
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Vex;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"), index = 1)
    private float increaseVexDecayDamage(float amount) {
        return ModConstants.getVexDecayDamageValue();
    }

    /**
     * Makes vexes take fall damage from doom mode.
     */
    @Override
    public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource source) {
        return !isDoomMode();
    }
}