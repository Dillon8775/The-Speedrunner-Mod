package net.dillon.speedrunnermod.mixin.terraform.sign.impl;

import net.dillon.speedrunnermod.block.sign.hanging.CustomHangingSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.HangingSignEditScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Author(Authors.TERRAFORMERSMC)
@Mixin(HangingSignEditScreen.class)
@Environment(EnvType.CLIENT)
public class HangingSignEditScreenMixin {
	@Shadow @Final @Mutable
	private Identifier texture;

	/**
	 * Returns the GUI sign texture id for {@link CustomHangingSign}s.
	 */
	@Inject(method = "<init>", at = @At("TAIL"))
	private void signTextureId(SignBlockEntity signBlockEntity, boolean front, boolean filtered, CallbackInfo ci) {
		if (signBlockEntity.getCachedState().getBlock() instanceof CustomHangingSign signBlock) {
			Identifier guiTexture = signBlock.getGuiTexture();
			this.texture = guiTexture.withSuffixedPath(".png");
		}
	}
}
