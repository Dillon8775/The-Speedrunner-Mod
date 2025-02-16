package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity {

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.entityEnchantment((PiglinEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 32;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code piglin} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createPiglinAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 24.0D : 16.0D;
        final double genericMovementSpeed = 0.3499999940395355D;
        final double genericAttackDamage =  DOOM_MODE ? 6.0D : 2.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}