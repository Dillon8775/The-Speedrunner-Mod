package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(GuardianEntity.class)
public class GuardianEntityMixin extends HostileEntity {

    public GuardianEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((GuardianEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code guardian} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createGuardianAttributes() {
        final double genericAttackDamage = options().main.playingMode.doom() ? 7.0D : 3.0D;
        final double genericMovementSpeed = 0.5D;
        final double genericFollowRange = options().main.playingMode.doom() ? 24.0D : 8.0;
        final double genericMaxHealth = options().main.playingMode.doom() ? 35.0D : 15.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth);
    }
}