package net.dillon.speedrunnermod.mixin.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TripWireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TripWireBlock.class)
public class TripWireBlockFix {

    /**
     * Fixes {@code speedrunner shears} not working on tripwire hooks.
     */
    @Redirect(method = "playerWillDestroy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectTripwireBlockOnBreakWithShears(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}