package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {
    @Shadow
    private int explosionRadius;
    @Shadow
    public abstract boolean isCharged();

    public CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Lowers the creeper's max health.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeCreeperMaxHealth(EntityType<? extends CreeperEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 0.3D : 0.25D);
    }

    /**
     * Makes creepers explode instantly when right-clicked with a flint and steel on {@code doom mode.}
     */
    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/Entity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"))
    private void explodeCreeperInstantly(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        float o = this.isCharged() ? 2.0F : 1.0F;
        if (!this.getWorld().isClient && isDoomMode()) {
            this.discard();
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * o, World.ExplosionSourceType.MOB);
            this.getWorld().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_ITEM_BREAK, this.getSoundCategory(), 1.5F, this.random.nextFloat() * 0.4F + 0.8F);
            itemStack.damage(1, player, CreeperEntity.getSlotForHand(hand));
        }
    }
}