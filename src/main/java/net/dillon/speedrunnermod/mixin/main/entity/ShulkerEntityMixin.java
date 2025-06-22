package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends GolemEntity {
    @Shadow
    abstract boolean isClosed();
    @Shadow
    abstract void spawnNewShulker();

    public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((ShulkerEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * Modifies the shulker's maximum health.
     */
    @ModifyArg(method = "createShulkerAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMaxHealth(double baseValue) {
        return options().main.playingMode.doom() ? 32.0D : 20.0D;
    }

    /**
     * @author Dillon8775
     * @reason Prevents {@code shulkers} from teleporting, and allows them to be shot with arrows, even when closed.
     */
    @Overwrite
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        Entity entity2;
        if (this.isClosed()) {
            entity2 = source.getSource();
            if (options().main.playingMode.doom()) {
                if (entity2 instanceof PersistentProjectileEntity) {
                    return false;
                }
            } else {
                if (entity2 instanceof PersistentProjectileEntity && this.random.nextFloat() < 0.25F) {
                    return false;
                }
            }
        }

        if (!super.damage(world, source, amount)) {
            return false;
        } else {
            if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
                entity2 = source.getSource();
                if (entity2 != null && entity2.getType() == EntityType.SHULKER_BULLET) {
                    this.spawnNewShulker();
                }
            }

            return true;
        }
    }
}