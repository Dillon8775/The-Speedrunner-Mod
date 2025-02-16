package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.DOOM_MODE;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin extends HostileEntity {

    public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.entityEnchantment((AbstractSkeletonEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 32;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * Increases/decreases the speed at which skeleton entities can shoot with their bow, depending on if {@code doom mode} is {@code ON.}
     */
    @ModifyVariable(method = "updateAttackType", at = @At("STORE"), ordinal = 0)
    private int updateAttackType(int x) {
        int i = DOOM_MODE ? 5 : 20;
        if (this.getWorld().getDifficulty() != Difficulty.HARD) {
            i = DOOM_MODE ? 10 : 20;
        }
        return i;
    }

    /**
     * Modifies the movement speed of skeleton entities.
     */
    @ModifyArg(method = "createAbstractSkeletonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMovementSpeed(double baseValue) {
        return DOOM_MODE ? 0.3D : 0.25D;
    }
}