package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.LockIconButton;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.world.Difficulty;
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
    private @Nullable LockIconButton lockButton;
    @Shadow
    private @Nullable CycleButton<Difficulty> difficultyButton;

    /**
     * Locks the difficulty button on doom mode. You can't change it >:).
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void lockDifficultyOnDoomMode(CallbackInfo ci) {
        if (isDoomMode()) {
            if (this.lockButton != null) {
                this.lockButton.setLocked(true);
                this.lockButton.active = false;
            }
            if (this.difficultyButton != null) {
                this.difficultyButton.active = false;
            }
        }
    }
}