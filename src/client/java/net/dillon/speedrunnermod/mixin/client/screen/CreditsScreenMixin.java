package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.screen.CreditsScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

@Mixin(CreditsScreen.class)
public class CreditsScreenMixin {
    @Shadow @Final
    private boolean endCredits;

    /**
     * Allows the player to be able to close the credits screen in the future after they have read  them.
     */
    @Inject(method = "closeScreen", at = @At("TAIL"))
    private static void allowClosingOfCreditsScreen(CallbackInfo ci) {
        clientOptions().client.canCloseEndCredits.set(true);
        saveClientChanges();
    }

    /**
     * Prevents the player from closing the screen if they have not yet read the "credits".
     */
    @Inject(method = "close", at = @At("HEAD"), cancellable = true)
    private void preventClosingOfCreditsScreen(CallbackInfo ci) {
        if (this.endCredits && !clientOptions().client.canCloseEndCredits.getCurrentValue()) {
            ci.cancel();
        }
    }
}