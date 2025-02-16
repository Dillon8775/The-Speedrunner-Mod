package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(ZoglinEntity.class)
public class ZoglinEntityMixin extends HostileEntity {

    public ZoglinEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.entityEnchantment((ZoglinEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code zoglin} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createZoglinAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 60.0D : 25.0D;
        final double genericMovementSpeed = 0.30000001192092896D;
        final double genericKnockbackResistance = DOOM_MODE ? 0.7000000238518589D : 0.6000000238418579D;
        final double genericAttackKnockback = DOOM_MODE ? 1.2D : 0.5D;
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}