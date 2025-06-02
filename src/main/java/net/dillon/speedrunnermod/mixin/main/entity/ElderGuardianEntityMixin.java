package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ElderGuardianEntity.class)
public class ElderGuardianEntityMixin extends GuardianEntity {

    public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.getAttacker() != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((ElderGuardianEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 72;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code elder guardian} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createElderGuardianAttributes() {
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 8.0D : 4.0D;
        final double genericMaxHealth = options().main.playingMode.doom() ? 50.0D : 25.0D;
        return GuardianEntity.createGuardianAttributes()
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth);
    }

    /**
     * @author Dillon8775
     * @reason Decreases the range that an elder guardian can detect a player, and also decreases the mining fatigue duration.
     */
    @Overwrite
    public void mobTick(ServerWorld world) {
        super.mobTick(world);
        final int i = options().main.playingMode.doom() ? 600 : 6000;
        if ((this.age + this.getId()) % i == 0) {
            final int duration = options().main.playingMode.doom() ? ModUtil.minutesInTicks(5) : ModUtil.secondsInTicks(30);
            final double d = options().main.playingMode.doom() ? 55.0D : 25.0D;
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, duration, 2);
            List<ServerPlayerEntity> list = StatusEffectUtil.addEffectToPlayersWithinDistance((ServerWorld)this.getWorld(), this, this.getPos(), d, statusEffectInstance, 1200);
            list.forEach(serverPlayerEntity -> serverPlayerEntity.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, this.isSilent() ? GameStateChangeS2CPacket.DEMO_OPEN_SCREEN : (int)1.0f)));
        }
        if (!this.hasPositionTarget()) {
            this.setPositionTarget(this.getBlockPos(), 16);
        }
    }
}