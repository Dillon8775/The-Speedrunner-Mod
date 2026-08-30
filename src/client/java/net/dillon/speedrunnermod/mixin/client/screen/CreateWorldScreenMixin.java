package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    @Shadow @Final
    WorldCreationUiState uiState;
    @Shadow
    protected abstract void onCreate();

    /**
     * Hard-locks the difficulty to hard on doom mode.
     */
    @Redirect(method = "createLevelSettings", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/worldselection/WorldCreationUiState;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty lockDifficultyDoomMode(WorldCreationUiState original) {
        return isDoomMode() ? Difficulty.HARD : original.getDifficulty();
    }

    /**
     * Reworks how the create world button works, and allows the {@code instant world creation} feature to work accordingly.
     */
    @Inject(method = "init", at = @At("TAIL"))
    private void fastWorldCreationButtonFunction(CallbackInfo ci) {
        if (!client().client.instantWorldCreation.getCurrentValue()) {
            return;
        }

        Difficulty difficulty = null;
        switch (client().client.difficulty.getCurrentValue()) {
            case PEACEFUL:
                difficulty = Difficulty.PEACEFUL;
                break;
            case EASY:
                difficulty = Difficulty.EASY;
                break;
            case NORMAL:
                difficulty = Difficulty.NORMAL;
                break;
            case HARD:
                difficulty = Difficulty.HARD;
                break;
        }

        WorldCreationUiState.SelectedGameMode gameMode = null;
        switch (client().client.gameMode.getCurrentValue()) {
            case SURVIVAL:
                gameMode = WorldCreationUiState.SelectedGameMode.SURVIVAL;
                break;
            case CREATIVE:
                gameMode = WorldCreationUiState.SelectedGameMode.CREATIVE;
                break;
            case HARDCORE:
                gameMode = WorldCreationUiState.SelectedGameMode.HARDCORE;
                break;
            case SPECTATOR:
                gameMode = WorldCreationUiState.SelectedGameMode.DEBUG;
                break;
        }

        assert gameMode != null;
        assert difficulty != null;
        this.uiState.setGameMode(gameMode);
        if (!client().client.gameMode.getCurrentValue().hardcore()) {
            this.uiState.setDifficulty(difficulty);
            this.uiState.setAllowCommands(client().client.allowCommands.getCurrentValue());
        }
        if (!client().client.seed.getCurrentValue().isEmpty()) {
            this.uiState.setSeed(client().client.seed.getCurrentValue());
        }
        onCreate();
    }
}