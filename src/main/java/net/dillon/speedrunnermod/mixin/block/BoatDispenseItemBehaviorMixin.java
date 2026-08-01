package net.dillon.speedrunnermod.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BoatDispenseItemBehavior.class)
public class BoatDispenseItemBehaviorMixin {

    /**
     * Allows fireproof boats to be dispensed on lava.
     */
    @ModifyExpressionValue(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 0))
    private boolean allowLavaBoats(boolean original, BlockSource source, ItemStack dispensed) {
        FluidState state = source.level().getFluidState(source.pos().relative(source.state().getValue(DispenserBlock.FACING)));

        return original || (state.is(FluidTags.LAVA) && (dispensed.is(ModItemTags.FIREPROOF_BOATS) || dispensed.is(ModItemTags.FIREPROOF_CHEST_BOATS)));
    }
}