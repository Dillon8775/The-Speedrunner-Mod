package net.dillon.speedrunnermod.mixin.client;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.screen.SafeBootScreen;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.leaderboard.LeaderboardsSafeScreen;
import net.dillon.speedrunnermod.screen.misc.SpeedrunIGTMissingScreen;
import net.dillon.speedrunnermod.util.Overrides;
import net.minecraft.client.GameLoadCookie;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Screen;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);

    /**
     * Adds the {@code Safe Mode} feature.
     * <p>If the speedrunner mod detects broken options, then the game will load into the {@link SafeBootScreen}.</p>
     */
    @Inject(method = "buildInitialScreens", at = @At("RETURN"), cancellable = true)
    private void openSpeedrunnerModScreens(GameLoadCookie cookie, CallbackInfoReturnable<Runnable> cir) {
        Runnable vanillaFlow = cir.getReturnValue();
        cir.setReturnValue(() -> {
            if (ModConstants.safeBoot) {
                this.setScreen(new SafeBootScreen(null));
                warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
            } else if (clientOptions().storedValues.firstTimePlaying.getCurrentValue() || Overrides.firstTimePlaying()) {
                this.setScreen(FeaturePage.FIRST_TIME_PLAYING.createScreen(null));
            } else if (!Leaderboards.isEligibleForLeaderboardRuns() && options().general.leaderboardsMode.getCurrentValue()) {
                this.setScreen(new LeaderboardsSafeScreen(null));
                warn("You have invalid options set for the leaderboards, you must fix these if you want to submit a speedrun to the leaderboards.");
            } else if (options().general.leaderboardsMode.getCurrentValue() && SpeedrunnerModClient.speedrunIGTMissing) {
                this.setScreen(new SpeedrunIGTMissingScreen(null));
                warn("SpeedrunIGT mod is missing, please download to submit speedruns.");
            } else {
                vanillaFlow.run();
            }
        });
    }
}