package net.dillon.speedrunnermod.mixin.client.gui.widget;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes slider widgets do the same thing as normal buttons.
 */
@Mixin(SliderWidget.class)
public abstract class SliderWidgetMixin extends ClickableWidget {

    public SliderWidgetMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Inject(method = "onRelease", at = @At("TAIL"))
    private void superCall(double mouseX, double mouseY, CallbackInfo ci) {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (screen instanceof AbstractModScreen modScreen && modScreen.isOptionsScreen()) {
            super.onRelease(mouseX, mouseY);
        }
    }
}