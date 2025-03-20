package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {
    @Shadow
    private int explosionRadius;

    @Shadow public abstract boolean isCharged();

    public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((CreeperEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 32;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * Lowers the creeper's max health.
     */
    @ModifyArg(method = "createCreeperAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMovementSpeed(double baseValue) {
        return options().main.playingMode.doom() ? 0.3D : 0.25D;
    }

    /**
     * A thing for doom mode. >:)
     */
    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    private void explode(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        float o = this.isCharged() ? 2.0F : 1.0F;
        if (!this.getWorld().isClient && options().main.playingMode.doom()) {
            this.discard();
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * o, World.ExplosionSourceType.MOB);
            this.getWorld().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_BREAK, this.getSoundCategory(), 1.5F, this.random.nextFloat() * 0.4F + 0.8F);
            itemStack.damage(1, player, CreeperEntity.getSlotForHand(hand));
        }
    }
}