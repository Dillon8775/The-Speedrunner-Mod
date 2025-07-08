package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity {

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code piglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends PiglinEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 24.0D : 16.0D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 6.0D : 2.0D);
    }

    /**
     * Modifies the experience to drop for the piglin.
     */
    @Inject(method = "getExperienceToDrop", at = @At("HEAD"))
    private void modifyExperienceToDrop(ServerWorld world, CallbackInfoReturnable<Integer> cir) {
        if (this.getAttacker() != null) {
            this.experiencePoints = ModUtil.modifyExperiencePoints(this, this.getAttacker(), 5, 32);
        }
    }
}