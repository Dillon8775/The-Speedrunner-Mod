package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.item.FireproofBoat;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
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
    private AbstractBoat makeBoatFireproof(AbstractBoat abstractBoat, Level world, Player player, InteractionHand hand) {
        float lavaInvulnerability = (float)player.getAttributeValue(ModAttributes.LAVA_INVULNERABILITY);
        float additionalBoatSpeed = (float)player.getAttributeValue(ModAttributes.BONUS_BOAT_MOVEMENT_SPEED);
        if (lavaInvulnerability > 1.0F) {
            ((FireproofBoat)abstractBoat).setFireproof(true);
        }
        if (additionalBoatSpeed > 1.0F) {
            ((FireproofBoat)abstractBoat).setBoatSpeed(additionalBoatSpeed - 1.0F);
        }
        return abstractBoat;
    }
}