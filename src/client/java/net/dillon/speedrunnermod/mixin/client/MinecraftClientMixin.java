package net.dillon.speedrunnermod.mixin.client;

import com.google.common.collect.Lists;
import net.dillon.speedrunnermod.client.screen.base.SafeBootScreen;
import net.dillon.speedrunnermod.client.screen.base.leaderboard.LeaderboardsSafeScreen;
import net.dillon.speedrunnermod.client.screen.base.misc.SpeedrunIGTMissingScreen;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.FirstTimePlayingScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.QuickPlay;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    public abstract void setScreen(@Nullable Screen screen);
    @Shadow
    protected abstract boolean createInitScreens(List<Function<Runnable, Screen>> list);

    /**
     * @author Dillon8775
     * @reason Adds the {@code Safe Mode} feature.
     * <p>If the speedrunner mod detects broken options, then the game will load into the {@link SafeBootScreen}.</p>
     */
    @Overwrite
    private Runnable onInitFinished(@Nullable MinecraftClient.LoadingContext loadingContext) {
        List<Function<Runnable, Screen>> list = new ArrayList();
        boolean bl = this.createInitScreens(list);
        Runnable runnable = () -> {
            if (loadingContext != null && loadingContext.quickPlayData().isEnabled()) {
                QuickPlay.startQuickPlay((MinecraftClient)(Object)this, loadingContext.quickPlayData().variant(), loadingContext.realmsClient());
            } else {
                if (SpeedrunnerMod.safeBoot) {
                    this.setScreen(new SafeBootScreen(null));
                    warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
                } else if (clientOptions().storedValues.firstTimePlaying.getCurrentValue()) {
                    this.setScreen(new FirstTimePlayingScreen(null));
                } else if (clientOptions().storedValues.enterFeaturesScreen.getCurrentValue()) {
                    this.setScreen(new SpeedrunnerIngotsScreen(null));
                    clientOptions().storedValues.enterFeaturesScreen.set(false);
                    saveClientChanges();
                } else if (!Leaderboards.isEligibleForLeaderboardRuns() && options().main.leaderboardsMode.getCurrentValue()) {
                    this.setScreen(new LeaderboardsSafeScreen(null));
                    warn("You have invalid options set for the leaderboards, you must fix these if you want to submit a speedrun to the leaderboards.");
                } else if (options().main.leaderboardsMode.getCurrentValue() && SpeedrunnerModClient.speedrunIGTMissing) {
                    this.setScreen(new SpeedrunIGTMissingScreen(null));
                    warn("SpeedrunIGT mod is missing, please download to submit speedruns.");
                } else {
                    this.setScreen(new TitleScreen(true, new LogoDrawer(bl)));
                }
            }
        };
        for (Function<Runnable, Screen> function : Lists.reverse(list)) {
            Screen screen = function.apply(runnable);
            runnable = () -> this.setScreen(screen);
        }
        return runnable;
    }
}