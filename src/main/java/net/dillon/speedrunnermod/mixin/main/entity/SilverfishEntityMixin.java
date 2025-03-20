package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(SilverfishEntity.class)
public abstract class SilverfishEntityMixin extends HostileEntity {

    public SilverfishEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((SilverfishEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 16;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code silverfish} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createSilverfishAttributes() {
        final double genericMaxHealth = options().main.playingMode.doom() ? 8.0D : 4.0D;
        final double genericMovementSpeed = options().main.playingMode.doom() ? 0.25D : 0.15D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 2.0D : 0.01D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}