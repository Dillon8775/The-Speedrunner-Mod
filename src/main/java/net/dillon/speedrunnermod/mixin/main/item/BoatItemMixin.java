package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.FireproofBoat;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BoatItem.class)
public class BoatItemMixin extends Item {

    public BoatItemMixin(Properties settings) {
        super(settings);
    }

    /**
     * Sets the {@link AbstractBoat} to {@code fireproof} if the player had a fireproof boat in their hand.
     */
    @ModifyVariable(method = "use", at = @At(value = "STORE", ordinal = 0))
    private AbstractBoat makeBoatFireproof(AbstractBoat abstractBoat, Level world, Player user, InteractionHand hand) {
        ItemStack heldBoat = user.getItemInHand(hand);
        if (heldBoat.is(ModItemTags.FIREPROOF_BOATS) || heldBoat.is(ModItemTags.FIREPROOF_CHEST_BOATS)) {
            ((FireproofBoat)abstractBoat).setFireproof(true);
        }
        return abstractBoat;
    }
}