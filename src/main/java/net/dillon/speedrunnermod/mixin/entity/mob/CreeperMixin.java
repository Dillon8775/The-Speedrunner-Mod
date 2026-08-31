package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends Monster {
    @Shadow
    public abstract boolean isPowered();
    @Shadow
    private int explosionRadius;

    public CreeperMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Lowers the creeper's max health.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeCreeperMaxHealth(EntityType<? extends Creeper> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMovementSpeed(this, isDoomMode() ? 0.3D : 0.25D);
    }

    /**
     * Makes creepers explode instantly when right-clicked with a flint and steel on {@code doom mode.}
     */
    @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/Entity;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    private void explodeCreeperInstantly(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        float o = this.isPowered() ? 2.0F : 1.0F;
        if (!this.level().isClientSide() && isDoomMode()) {
            this.discard();
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * o, Level.ExplosionInteraction.MOB);
            this.level().playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.ITEM_BREAK, this.getSoundSource(), 1.5F, this.random.nextFloat() * 0.4F + 0.8F);
            itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        }
    }
}