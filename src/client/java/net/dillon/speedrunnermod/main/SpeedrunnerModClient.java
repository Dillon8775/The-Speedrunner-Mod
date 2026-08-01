package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.keybind.ModKeyMappings;
import net.dillon.speedrunnermod.network.ClientModPackets;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.option.OptionValue;
import net.dillon.speedrunnermod.particle.ModParticleManager;
import net.dillon.speedrunnermod.screen.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
public class SpeedrunnerModClient implements ClientModInitializer {
    public static boolean speedrunIGTMissing = false;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ClientModPackets.registerClientPackets();

        ModParticleManager.registerDefaults();
        ModMenus.registerScreens();
        ModKeyMappings.initializeKeybinds();

        clientConfigHandler().load();

        if (options().general.leaderboardsMode.getCurrentValue() && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        Leaderboards.initializeLeaderboards();

        debug("The client-side for The Speedrunner Mod has successfully loaded.");
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options.}
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */

    public static ClientModOptions clientOptions() {
        return ClientModOptions.CLIENT_INSTANCE.getInstance();
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options handler} (for saving/loading config).
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */

    public static ClientModOptions.ModClientOptionsHandler clientConfigHandler() {
        return ClientModOptions.CLIENT_INSTANCE;
    }

    /**
     * Saves all speedrunner mod option changes on the {@code client-side.}
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */

    public static void saveClientChanges() {
        clientConfigHandler().save();
    }

    /**
     * Saves all speedrunner mod option changes (both client and server-side).
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */

    public static void saveAllChanges() {
        saveDedicatedServerChanges();
        saveClientChanges();
    }

    /**
     * Returns true if the {@code SpeedrunIGT} mod is loaded.
     */
    public static boolean isSpeedrunIGTLoaded() {
        return FabricLoader.getInstance().isModLoaded("speedrunigt");
    }

    /**
     * Returns true if the {@code Simple Keybinds} mod is loaded.
     */
    public static boolean isSimpleKeybindsLoaded() {
        return FabricLoader.getInstance().isModLoaded("simplekeybinds");
    }

    /**
     * Fixes broken speedrunner mod options.
     */
    public static void fixAllBrokenOptions() {
        for (OptionValue<?> option : OptionValue.getBrokenOptions()) {
            if (option.isBroken()) {
                option.reset();
                option.setFixed();
            }
        }

        saveAllChanges();
    }

    /**
     * Returns the {@code minimum brightness} value for the speedrunner mod.
     */
    public static double getMinBrightness() {
        return clientOptions().client.minimumBrightness.getCurrentValue();
    }

    /**
     * Returns the {@code maximum brightness} value for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return (double) clientOptions().client.fullBrightAmount.getCurrentValue() / 100;
    }
}