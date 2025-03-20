package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin extends HostileEntity {

    public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getExperienceToDrop", at = @At("HEAD"))
    private void getExperiencePoints(CallbackInfoReturnable<Integer> cir) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((ZombieEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 32;
        }
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code zombie} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createZombieAttributes() {
        final double genericFollowRange = options().main.playingMode.doom() ? 50.0D : 25.0D;
        final double genericMovementSpeed = options().main.playingMode.doom() ? 0.33000000417232513D : 0.23000000417232513D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 7.0D : 2.0D;
        final double genericArmor = options().main.playingMode.doom() ? 2.0D : 1.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.ARMOR, genericArmor)
                .add(EntityAttributes.SPAWN_REINFORCEMENTS);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (!super.tryAttack(world, target)) {
            return false;
        } else {
            if (options().main.playingMode.doom() && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(10), 0));
            }

            return true;
        }
    }
}