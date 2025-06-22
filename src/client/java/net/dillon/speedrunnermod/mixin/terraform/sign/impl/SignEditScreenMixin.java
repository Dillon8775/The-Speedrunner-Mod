package net.dillon.speedrunnermod.mixin.terraform.sign.impl;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dillon.speedrunnermod.block.sign.CustomSign;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Author(Authors.TERRAFORMERSMC)
@Mixin(SignEditScreen.class)
@Environment(EnvType.CLIENT)
public abstract class SignEditScreenMixin extends AbstractSignEditScreen {

	public SignEditScreenMixin(SignBlockEntity blockEntity, boolean front, boolean filtered) {
		super(blockEntity, front, filtered);
	}

	@WrapOperation(
			// DrawContext#draw callback within the renderSignBackground method
			method = "method_64048",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TexturedRenderLayers;getSignTextureId(Lnet/minecraft/block/WoodType;)Lnet/minecraft/client/util/SpriteIdentifier;")
	)
	@SuppressWarnings("unused")
	private SpriteIdentifier editSignTextureId(WoodType type, Operation<SpriteIdentifier> original, DrawContext drawContext) {
		BlockState state = this.blockEntity.getCachedState();

		if (state.getBlock() instanceof CustomSign signBlock) {
			return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture());
		}

		return original.call(type);
	}
}
