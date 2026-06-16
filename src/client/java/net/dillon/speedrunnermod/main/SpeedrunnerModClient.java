package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.keybind.ModKeyMappings;
import net.dillon.speedrunnermod.network.ClientModPackets;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.option.OptionValue;
import net.dillon.speedrunnermod.particle.ModParticleManager;
import net.dillon.speedrunnermod.render.ModRenderers;
import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.screen.ModMenus;
import net.dillon.speedrunnermod.screen.VersionType;
import net.dillon.speedrunnermod.screen.feature.secretdoommode.AbstractSecretDoomModeScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.Screen;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
public class SpeedrunnerModClient implements ClientModInitializer {
    public static final List<Function<Screen, AbstractModScreen>> ALL_MOD_SCREENS = new ArrayList<>(); // A list of all subclasses of AbstractModScreen
    public static final List<Function<Screen, AbstractFeatureScreen>> ALL_FEATURE_SCREENS = new ArrayList<>(); // A list of all subclasses of AbstractFeatureScreen
    public static boolean speedrunIGTMissing = false;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ClientModPackets.registerClientPackets();

        ModParticleManager.registerDefaults();
        ModRenderers.registerRenderers();
        ModMenus.registerScreens();
        ModKeyMappings.initializeKeybinds();

        clientConfigHandler().load();

        if (options().general.leaderboardsMode.getCurrentValue() && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        // For adding all screens to a list, without having to manually add them all
        Reflections modScreenDirectory = new Reflections("net.dillon.speedrunnermod.screen", Scanners.SubTypes);
        Reflections featureScreenDirectory = new Reflections("net.dillon.speedrunnermod.screen.feature", Scanners.SubTypes);
        Set<Class<? extends AbstractModScreen>> modScreenClasses = modScreenDirectory.getSubTypesOf(AbstractModScreen.class);
        Set<Class<? extends AbstractFeatureScreen>> featureScreenClasses = featureScreenDirectory.getSubTypesOf(AbstractFeatureScreen.class);

        // Add all instances of AbstractModScreen to ALL_MOD_SCREENS list
        for (Class<? extends AbstractModScreen> modScreen : modScreenClasses) {
            try {
                Constructor<? extends AbstractModScreen> constructor = modScreen.getConstructor(Screen.class);

                Function<Screen, AbstractModScreen> creator = (parent) -> {
                    try {
                        return constructor.newInstance(parent);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to instantiate " + modScreen.getName(), e);
                    }
                };

                SpeedrunnerModClient.ALL_MOD_SCREENS.add(creator);
            } catch (NoSuchMethodException e) {
                SpeedrunnerMod.debug("Skipping " + modScreen.getName() + ": doesn't have (Screen) constructor.");
            }
        }

        // Add all instances of AbstractFeatureScreen to ALL_FEATURE_SCREENS list
        for (Class<? extends AbstractFeatureScreen> featureScreen : featureScreenClasses) {
            if (featureScreen == AbstractSecretDoomModeScreen.class) {
                continue;
            }

            try {
                Constructor<? extends AbstractFeatureScreen> constructor = featureScreen.getConstructor(Screen.class);

                Function<Screen, AbstractFeatureScreen> creator = (parent) -> {
                    try {
                        return constructor.newInstance(parent);
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to instantiate " + featureScreen.getName(), e);
                    }
                };

                SpeedrunnerModClient.ALL_FEATURE_SCREENS.add(creator);
            } catch (NoSuchMethodException e) {
                SpeedrunnerMod.debug("Skipping " + featureScreen.getName() + ": doesn't have (Screen) constructor.");
            }
        }

        Leaderboards.initializeLeaderboards();

        debug("The client-side for The Speedrunner Mod has successfully loaded.");
    }

    /**
     * @return the version type for speedrunner mod.
     */
    public static VersionType getVersionType() {
        return VersionType.PATCH;
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
        return (double) clientOptions().client.fullbrightAmount.getCurrentValue() / 100;
    }
}