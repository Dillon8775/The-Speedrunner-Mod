package net.dillon.speedrunnermod.mixin.main.entity.boat;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(VehicleEntity.class)
public class VehicleEntityMixin {

    /**
     * Drops {@code fireproof boats.}
     */
    @ModifyVariable(method = "killAndDropItem", at = @At(value = "STORE", ordinal = 0))
    private ItemStack dropFireproofBoat(ItemStack stack, ServerWorld world, Item item) {
        if ((VehicleEntity)(Object)this instanceof AbstractBoatEntity boat && ModEntityTypes.isFireproofBoat(boat)) {
            stack.set(ModDataComponentTypes.BOOLEAN, true);
        }
        return stack;
    }
}