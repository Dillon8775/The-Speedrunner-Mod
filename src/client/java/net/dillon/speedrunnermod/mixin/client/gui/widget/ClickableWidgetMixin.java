package net.dillon.speedrunnermod.mixin.client.gui.widget;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Refreshes screens when pressing on a button.
 */
@Mixin(ClickableWidget.class)
public class ClickableWidgetMixin {

    @Inject(method = "onRelease", at = @At("TAIL"))
    private void refreshScreens(double mouseX, double mouseY, CallbackInfo ci)  {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (screen instanceof AbstractModScreen modScreen && modScreen.isOptionsScreen()) {
            double scrollY = modScreen.optionList.getScrollY();
            modScreen.refreshScreen(modScreen.pageId());
            AbstractModScreen screen2 = (AbstractModScreen)MinecraftClient.getInstance().currentScreen;
            screen2.optionList.setScrollY(scrollY);
        }
    }
}