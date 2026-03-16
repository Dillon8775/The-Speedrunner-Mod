package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoRenderer.class)
public class LogoRendererMixin {
    @Shadow @Final
    private boolean keepLogoThroughFade;

    @Inject(method = "extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IFI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIIII)V", ordinal = 1), cancellable = true)
    private void replaceJavaEditionLogoWithSpeedrunnerEditionLogo(GuiGraphicsExtractor context, int screenWidth, float alpha, int y2, CallbackInfo ci) {
        ci.cancel();
        float f = this.keepLogoThroughFade ? 1.0F : alpha;
        int j = ARGB.white(f);
        int i = screenWidth / 2 - 137;
        context.blit(RenderPipelines.GUI_TEXTURED, LogoRenderer.MINECRAFT_EDITION, i + 58, y2 + 37, 0.0F, 0.0F, 184, 14, 184, 16, j);
    }
}