package net.dillon.speedrunnermod.mixin.client;

import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.util.Overrides;
import net.minecraft.client.GameLoadCookie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

@Mixin(Gui.class)
public class GuiMixin {

    /**
     * Boots into the first time playing screen.
     */
    @Inject(method = "buildInitialScreens", at = @At("RETURN"), cancellable = true)
    private void openSpeedrunnerModScreens(GameLoadCookie cookie, CallbackInfoReturnable<Runnable> cir) {
        Runnable vanillaFlow = cir.getReturnValue();
        cir.setReturnValue(() -> {
            if (client().storedValues().firstTimePlaying || Overrides.firstTimePlaying()) {
                Minecraft.getInstance().gui.setScreen(FeaturePage.FIRST_TIME_PLAYING.createScreen(null));
            } else {
                vanillaFlow.run();
            }
        });
    }
}