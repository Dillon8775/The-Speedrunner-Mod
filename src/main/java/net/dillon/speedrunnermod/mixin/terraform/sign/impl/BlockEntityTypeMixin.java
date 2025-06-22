package net.dillon.speedrunnermod.mixin.terraform.sign.impl;

import net.dillon.speedrunnermod.block.sign.CustomSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Author(Authors.TERRAFORMERSMC)
@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

	/**
	 * Allows the custom signs to be placed as block entities.
	 */
	@Inject(method = "supports", at = @At("HEAD"), cancellable = true)
	private void signSupports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		Block block = state.getBlock();

		if (block instanceof CustomSign) {
			if (BlockEntityType.HANGING_SIGN.equals(this)) {
				if (!(block instanceof HangingSignBlock || block instanceof WallHangingSignBlock)) {
					return;
				}
			} else if (!BlockEntityType.SIGN.equals(this)) {
				return;
			}

			cir.setReturnValue(true);
		}
	}
}
