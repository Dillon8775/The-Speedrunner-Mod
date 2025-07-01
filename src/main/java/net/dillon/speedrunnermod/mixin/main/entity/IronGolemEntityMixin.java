package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(IronGolemEntity.class)
public class IronGolemEntityMixin extends GolemEntity {

    public IronGolemEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.getAttacker() != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((IronGolemEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 32;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code iron golem} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createIronGolemAttributes() {
        final double genericMaxHealth = isPlayingModeDoom() ? 100.0D : 50.0D;
        final double genericMovementSpeed = isPlayingModeDoom() ? 0.3D : 0.25D;
        final double genericKnockbackResistance = isPlayingModeDoom() ? 0.7D : 0.5D;
        final double genericAttackDamage = isPlayingModeDoom() ? 20.0D : 7.0D;
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}