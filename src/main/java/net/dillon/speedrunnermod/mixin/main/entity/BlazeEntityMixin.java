package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BlazeEntity.class)
public class BlazeEntityMixin extends HostileEntity {

    public BlazeEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.getAttacker() != null) {
            this.experiencePoints = 10 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((BlazeEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 48;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code blaze} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createBlazeAttributes() {
        final double genericAttackDamage = options().main.playingMode.doom() ? 8.0D : 4.0D;
        final double genericMovementSpeed = 0.23000000417232513D;
        final double genericFollowRange = options().main.playingMode.doom() ? 48.0D : 16.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange);
    }
}