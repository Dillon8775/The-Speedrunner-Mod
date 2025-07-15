package net.dillon.speedrunnermod.mixin.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {
    @Shadow @Final
    private boolean ignoreAlpha;

    @Inject(method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIFFIIIII)V", ordinal = 1), cancellable = true)
    private void replaceJavaEditionLogoWithSpeedrunnerEditionLogo(DrawContext context, int screenWidth, float alpha, int y2, CallbackInfo ci) {
        ci.cancel();
        float f = this.ignoreAlpha ? 1.0F : alpha;
        int j = ColorHelper.getWhite(f);
        int i = screenWidth / 2 - 137;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, LogoDrawer.EDITION_TEXTURE, i + 58, y2 + 37, 0.0F, 0.0F, 184, 14, 184, 16, j);
    }
}