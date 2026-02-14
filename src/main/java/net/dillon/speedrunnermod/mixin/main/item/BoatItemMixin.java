package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.FireproofBoat;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BoatItem.class)
public class BoatItemMixin extends Item {

    public BoatItemMixin(Settings settings) {
        super(settings);
    }

    /**
     * Sets the {@link AbstractBoatEntity} to {@code fireproof} if the player had a fireproof boat in their hand.
     */
    @ModifyVariable(method = "use", at = @At(value = "STORE", ordinal = 0))
    private AbstractBoatEntity makeBoatFireproof(AbstractBoatEntity abstractBoat, World world, PlayerEntity user, Hand hand) {
        ItemStack heldBoat = user.getStackInHand(hand);
        if (heldBoat.isIn(ModItemTags.FIREPROOF_BOATS) || heldBoat.isIn(ModItemTags.FIREPROOF_CHEST_BOATS)) {
            ((FireproofBoat)abstractBoat).setFireproof(true);
        }
        return abstractBoat;
    }
}