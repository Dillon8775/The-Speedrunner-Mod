package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.FireChargeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(FireChargeItem.class)
public class FirechargeItemMixin extends Item {

    public FirechargeItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Allows fireballs to be used when shifting and looking at a block.
     */
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void throwFireballWhenShifting(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        if (options().main.throwableFireballs.getCurrentValue()) {
            if (!options().advanced.shiftToThrowFireball.getCurrentValue()) {
                this.throwAndSetReturnValue(context, player, cir);
            } else {
                if (player.isSneaking()) {
                    this.throwAndSetReturnValue(context, player, cir);
                }
            }
        }
    }

    /**
     * Allows fireballs to be thrown normally.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (options().main.throwableFireballs.getCurrentValue()) {
            return this.throwFireball(world, player, hand);
        }
        return super.use(world, player, hand);
    }

    @Unique
    private void throwAndSetReturnValue(ItemUsageContext context, PlayerEntity player, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        Hand hand = context.getHand();
        this.throwFireball(world, player, hand);
        cir.setReturnValue(ActionResult.SUCCESS_SERVER);
    }

    /**
     * The method for throwing fireballs.
     */
    @Unique
    private ActionResult throwFireball(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
            Vec3d lookVec = player.getRotationVec(1.0F);
            FireballEntity fireball = new FireballEntity(world, player, lookVec.normalize(), options().advanced.fireballExplosionPower.getCurrentValue());
            fireball.updatePosition(player.getX(), player.getEyeY() - 0.235, player.getZ());
            fireball.setOwner(player);
            world.spawnEntity(fireball);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

            player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsInTicks(1));
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            player.swingHand(hand);

            return ActionResult.SUCCESS_SERVER;
        }

        return null;
    }
}