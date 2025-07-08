package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {

    public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code vex} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends VexEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 7.0D : 14.0D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 3.0D : 4.0D);
    }

    /**
     * @author Dillon8775
     * @reason Disables vexes from {@code noClipping} on {@code doom mode.}
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/VexEntity;noClip:Z"))
    private void setNoClip(VexEntity vex, boolean value) {
        vex.noClip = !isDoomMode();
    }

    /**
     * Increases the damage dealt to themselves when decaying.
     */
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/VexEntity;serverDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"), index = 1)
    private float amount(float amount) {
        return ModUtil.getVexDecayDamageValue();
    }

    /**
     * Makes vexes take fall damage from doom mode.
     */
    @Override
    public boolean handleFallDamage(double fallDistance, float damageMultiplier, DamageSource source) {
        return !isDoomMode();
    }
}