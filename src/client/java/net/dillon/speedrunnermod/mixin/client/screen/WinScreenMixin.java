package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.screens.WinScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

@Mixin(WinScreen.class)
public class WinScreenMixin {
    @Shadow @Final
    private boolean poem;

    /**
     * Allows the player to be able to close the credits screen in the future after they have read  them.
     */
    @Inject(method = "respawn", at = @At("TAIL"))
    private static void allowClosingOfCreditsScreen(CallbackInfo ci) {
        client().client.canCloseEndCredits.set(true);
        saveClientChanges();
    }

    /**
     * Prevents the player from closing the screen if they have not yet read the "credits".
     */
    @Inject(method = "onClose", at = @At("HEAD"), cancellable = true)
    private void preventClosingOfCreditsScreen(CallbackInfo ci) {
        if (this.poem && !client().client.canCloseEndCredits.getCurrentValue()) {
            ci.cancel();
        }
    }
}