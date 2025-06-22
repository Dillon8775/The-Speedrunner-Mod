package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin extends HostileEntity {

    public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.getAttacker() != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((EndermanEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 48;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * Makes enderman drop {@code ender matter} based on the conditions for it.
     */
    @Override
    public void onDeath(DamageSource damageSource) {
        EndermanEntity endermanEntity = (EndermanEntity)(Object)this;
        LivingEntity attacker = endermanEntity.getAttacker();
        if (damageSource.isOf(DamageTypes.PLAYER_ATTACK) && attacker != null) {
            Optional<RegistryEntry.Reference<Enchantment>> optional = this.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(Enchantments.LOOTING);
            RegistryEntry<Enchantment> registryEntry = optional.get();
            int looting = EnchantmentHelper.getEquipmentLevel(registryEntry, attacker);
            boolean attackerHasSpeedrunnerSword = attacker.getMainHandStack().isIn(ModItemTags.SPEEDRUNNER_SWORDS);
            double chance = looting >= 3 ? 0.10 : looting == 2 ? 0.075 : looting == 1 ? 0.06 : 0.05;
            if (attackerHasSpeedrunnerSword) {
                chance += 0.02;
            }
            if (endermanEntity.getRandom().nextDouble() < chance) {
                endermanEntity.dropItem(endermanEntity.getServer().getWorld(endermanEntity.getEntityWorld().getRegistryKey()), ModItems.ENDER_MATTER);
            }
        }
        super.onDeath(damageSource);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code enderman} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createEndermanAttributes() {
        final double genericMaxHealth = options().main.playingMode.doom() ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 8.0D : 4.0D;
        final double genericFollowRange = options().main.playingMode.doom() ? 64.0D : 12.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed).
                add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange);
    }
}