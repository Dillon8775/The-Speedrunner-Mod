package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    @Shadow
    protected int experiencePoints;

    public MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code experience to drop} when a player has a looting enchantment for each hostile entity.
     */
    @Inject(method = "getExperienceToDrop", at = @At("HEAD"))
    private void getExperienceToDrop(CallbackInfoReturnable<Integer> cir) {
        MobEntity dis = (MobEntity)(Object)this;
        LivingEntity attacker = this.getAttacker();
        if (attacker != null) {
            if (dis instanceof AbstractSkeletonEntity ||
                    dis instanceof CreeperEntity ||
                    dis instanceof IronGolemEntity ||
                    dis instanceof PhantomEntity ||
                    dis instanceof SpiderEntity ||
                    dis instanceof ZombieEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 5, 32);
            } else if (dis instanceof BlazeEntity ||
                    dis instanceof BreezeEntity ||
                    dis instanceof EndermanEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 10, 48);
            } else if (dis instanceof ElderGuardianEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 15, 88);
            } else if (dis instanceof EndermiteEntity || dis instanceof SilverfishEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 5, 16);
            } else if (dis instanceof EvokerEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 5, 63);
            } else if (dis instanceof GhastEntity ||
                    dis instanceof PillagerEntity ||
                    dis instanceof ShulkerEntity ||
                    dis instanceof SlimeEntity ||
                    dis instanceof VexEntity ||
                    dis instanceof VindicatorEntity ||
                    dis instanceof WitchEntity ||
                    dis instanceof WitherSkeletonEntity ||
                    dis instanceof ZoglinEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 5, 36);
            } else if (dis instanceof GuardianEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 10, 36);
            } else if (dis instanceof PiglinBruteEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 20, 72);
            } else if (dis instanceof RavagerEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 5, 72);
            } else if (dis instanceof WitherEntity) {
                this.experiencePoints = ModUtil.modifyExperiencePoints(dis, attacker, 50, 150);
            }
        }
    }
}