package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(FireChargeItem.class)
public class FirechargeItemMixin extends Item {

    public FirechargeItemMixin(Properties settings) {
        super(settings);
    }

    /**
     * Allows fireballs to be used when shifting and looking at a block.
     */
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void throwFireballWhenShifting(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Player player = context.getPlayer();
        if (options().general.throwableFireballs.getCurrentValue()) {
            if (!options().advanced.shiftToThrowFireball.getCurrentValue()) {
                this.throwAndSetReturnValue(context, player, cir);
            } else {
                if (player.isShiftKeyDown()) {
                    this.throwAndSetReturnValue(context, player, cir);
                }
            }
        }
    }

    /**
     * Allows fireballs to be thrown normally.
     */
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (options().general.throwableFireballs.getCurrentValue()) {
            return this.throwFireball(world, player, hand);
        }
        return super.use(world, player, hand);
    }

    @Unique
    private void throwAndSetReturnValue(UseOnContext context, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        Level world = context.getLevel();
        InteractionHand hand = context.getHand();
        this.throwFireball(world, player, hand);
        cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
    }

    /**
     * The method for throwing fireballs.
     */
    @Unique
    private InteractionResult throwFireball(Level world, Player player, InteractionHand hand) {
        if (ModUtil.createFireball(this, world, player, hand, false)) {
            return InteractionResult.SUCCESS_SERVER;
        }
        return super.use(world, player, hand);
    }
}