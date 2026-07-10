package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.monster.cubemob.Slime;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    @Shadow
    protected int xpReward;

    public MobMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code experience to drop} when a player has a looting enchantment for each hostile entity.
     */
    @Inject(method = "getBaseExperienceReward", at = @At("HEAD"))
    private void increaseDroppedExperience(CallbackInfoReturnable<Integer> cir) {
        Mob dis = (Mob)(Object)this;
        LivingEntity attacker = this.getLastHurtByMob();
        if (attacker != null) {
            if (dis instanceof AbstractSkeleton ||
                    dis instanceof Creeper ||
                    dis instanceof IronGolem ||
                    dis instanceof Phantom ||
                    dis instanceof Spider ||
                    dis instanceof Zombie) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 5, 32);
            } else if (dis instanceof Blaze ||
                    dis instanceof Breeze ||
                    dis instanceof EnderMan) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 10, 48);
            } else if (dis instanceof ElderGuardian) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 15, 88);
            } else if (dis instanceof Endermite || dis instanceof Silverfish) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 5, 16);
            } else if (dis instanceof Evoker) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 5, 63);
            } else if (dis instanceof Ghast ||
                    dis instanceof Pillager ||
                    dis instanceof Shulker ||
                    dis instanceof Slime ||
                    dis instanceof Vex ||
                    dis instanceof Vindicator ||
                    dis instanceof Witch ||
                    dis instanceof WitherSkeleton ||
                    dis instanceof Zoglin) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 5, 36);
            } else if (dis instanceof Guardian) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 10, 36);
            } else if (dis instanceof PiglinBrute) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 20, 72);
            } else if (dis instanceof Ravager) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 5, 72);
            } else if (dis instanceof WitherBoss) {
                this.xpReward = ModHelper.modifyDroppedExperiencePoints(dis, attacker, 50, 150);
            }
        }
    }
}