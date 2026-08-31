package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(Hoglin.class)
public abstract class HoglinMixin extends Animal {

    public HoglinMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code hoglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeHoglinAttributes(EntityType<? extends Hoglin> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMaxHealth(this, isDoomMode() ? 60.0D : 25.0D);
        ModAttributeHelper.modifyKnockbackResistance(this, isDoomMode() ? 0.7000000238518589D : 0.6000000238418579D);
        ModAttributeHelper.modifyAttackKnockback(this, isDoomMode() ? 1.2D : 0.5D);
        ModAttributeHelper.modifyAttackDamage(this, isDoomMode() ? 8.0D : 4.0D);
    }

    /**
     * Modifies the experience to drop for the hoglin.
     */
    @Inject(method = "getBaseExperienceReward", at = @At("HEAD"))
    private void modifyExperienceToDrop(ServerLevel world, CallbackInfoReturnable<Integer> cir) {
        if (this.getLastHurtByMob() != null) {
            this.xpReward = ModHelper.modifyDroppedExperiencePoints(this, this.getLastHurtByMob(), 5, 36);
        }
    }
}