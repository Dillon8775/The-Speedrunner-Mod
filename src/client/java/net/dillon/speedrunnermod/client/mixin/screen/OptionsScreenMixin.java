package net.dillon.speedrunnermod.client.mixin.screen;

import net.minecraft.client.gui.screens.options.DifficultyButtons;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {
    @Shadow
    private @Nullable DifficultyButtons difficultyButtons;

    /**
     * Locks the difficulty button on doom mode. You can't change it >:).
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void lockDifficultyOnDoomMode(CallbackInfo ci) {
        if (isDoomMode() && this.difficultyButtons != null) {
            this.difficultyButtons.lockButton().setLocked(true);
            this.difficultyButtons.lockButton().active = false;
            this.difficultyButtons.difficultyButton().active = false;
        }
    }
}