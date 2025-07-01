package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity {

    public HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        int looting = this.getAttacker() != null ? EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((HoglinEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 36 : 0;
        return this.experiencePoints + looting;
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code hoglin} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createHoglinAttributes() {
        final double genericMaxHealth = isPlayingModeDoom() ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericKnockbackResistance = isPlayingModeDoom() ? 0.7000000238518589D : 0.6000000238418579D;
        final double genericAttackKnockback = isPlayingModeDoom() ? 1.2D : 0.5D;
        final double genericAttackDamage = isPlayingModeDoom() ? 8.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}