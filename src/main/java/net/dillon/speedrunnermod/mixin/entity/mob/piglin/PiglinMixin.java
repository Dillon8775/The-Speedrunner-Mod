package net.dillon.speedrunnermod.mixin.entity.mob.piglin;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Piglin.class)
public abstract class PiglinMixin extends AbstractPiglin {

    public PiglinMixin(EntityType<? extends AbstractPiglin> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code piglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePiglinAttributes(EntityType<? extends Piglin> entityType, Level world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 24.0D : 16.0D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 6.0D : 2.0D);
    }

    /**
     * Modifies the experience to drop for the piglin.
     */
    @Inject(method = "getBaseExperienceReward", at = @At("HEAD"))
    private void modifyExperienceToDrop(ServerLevel world, CallbackInfoReturnable<Integer> cir) {
        if (this.getLastHurtByMob() != null) {
            this.xpReward = ModUtil.modifyExperiencePoints(this, this.getLastHurtByMob(), 5, 32);
        }
    }
}