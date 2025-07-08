package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity {

    public HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code hoglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends HoglinEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 60.0D : 25.0D);
        ModUtil.modifyKnockbackResistance(this, isDoomMode() ? 0.7000000238518589D : 0.6000000238418579D);
        ModUtil.modifyAttackKnockback(this, isDoomMode() ? 1.2D : 0.5D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 8.0D : 4.0D);
    }

    /**
     * Modifies the experience to drop for the hoglin.
     */
    @Inject(method = "getExperienceToDrop", at = @At("HEAD"))
    private void modifyExperienceToDrop(ServerWorld world, CallbackInfoReturnable<Integer> cir) {
        if (this.getAttacker() != null) {
            this.experiencePoints = ModUtil.modifyExperiencePoints(this, this.getAttacker(), 5, 36);
        }
    }
}