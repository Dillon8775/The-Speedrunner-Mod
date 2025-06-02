package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModConstants;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(WitherEntity.class)
public class WitherEntityMixin extends HostileEntity {

    public WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.getAttacker() != null) {
            this.experiencePoints = 50 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((WitherEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 150;
        }
        return super.getExperienceToDrop(world);
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (this.getAttacker() instanceof PlayerEntity player && options().main.playingMode.doom() && options().main.tutorialMode) {
            options().tutorialMode.completeStep(TutorialStep.KILL_WITHER, player, "speedrunnermod.tutorial_mode.kill_dragon");
        }
    }

    /**
     * Decreases the maximum health for withers.
     */
    @ModifyArg(method = "createWitherAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", ordinal = 0), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return ModConstants.WITHER_MAX_HEALTH;
    }
}