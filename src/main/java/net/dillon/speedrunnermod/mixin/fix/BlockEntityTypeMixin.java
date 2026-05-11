package net.dillon.speedrunnermod.mixin.fix;

import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    /**
     * Fixes {@code speedrunner signs} not working correctly.
     */
    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof StandingSignBlock || BlockEntityType.HANGING_SIGN.equals(this) && (state.getBlock() instanceof StandingSignBlock) || state.getBlock() instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
    }
}