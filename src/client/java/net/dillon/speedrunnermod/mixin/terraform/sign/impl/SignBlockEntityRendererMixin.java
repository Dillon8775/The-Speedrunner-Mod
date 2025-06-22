package net.dillon.speedrunnermod.mixin.terraform.sign.impl;

import net.dillon.speedrunnermod.block.sign.CustomSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.WoodType;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Author(Authors.TERRAFORMERSMC)
@Environment(EnvType.CLIENT)
@Mixin({SignBlockEntityRenderer.class, HangingSignBlockEntityRenderer.class})
public class SignBlockEntityRendererMixin extends AbstractSignBlockEntityRendererMixin {

	/**
	 * Returns the sign texture for {@link CustomSign}s.
	 */
	@Inject(method = "getTextureId", at = @At("HEAD"), cancellable = true)
	private void rendererSignTextureId(WoodType woodType, CallbackInfoReturnable<SpriteIdentifier> cir) {
		if (this.renderedBlockEntity != null) {
			if (this.renderedBlockEntity.getCachedState().getBlock() instanceof CustomSign signBlock) {
				cir.setReturnValue(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture()));
			}
		}
	}
}
