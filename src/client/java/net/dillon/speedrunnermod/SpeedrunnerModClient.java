package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.dillon.speedrunnermod.client.screen.ModHandledScreens;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.option.BrokenModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static final List<Function<Screen, AbstractModScreen>> ALL_MOD_SCREENS = new ArrayList<>(); // A list of all subclasses of AbstractModScreen
    public static final List<Function<Screen, AbstractFeatureScreen>> ALL_FEATURE_SCREENS = new ArrayList<>(); // A list of all subclasses of AbstractFeatureScreen
    public static boolean speedrunIGTMissing = false;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    @Override
    public void onInitializeClient() {
        ModRenderers.initializeRenderers();
        ModHandledScreens.registerScreens();

        if (options().main.leaderboardsMode && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        ModKeybindings.initializeKeybinds();

        // For adding all screens to a list, without having to manually add them all
        Reflections modScreenDirectory = new Reflections("net.dillon.speedrunnermod.client.screen", Scanners.SubTypes);
        Reflections featureScreenDirectory = new Reflections("net.dillon.speedrunnermod.client.screen.feature", Scanners.SubTypes);
        Reflections changelogsDirectory = new Reflections("net.dillon.speedrunnermod.client.screen.base.text.changelog", Scanners.SubTypes);
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
                SpeedrunnerMod.warn("Skipping " + modScreen.getName() + ": doesn't have (Screen) constructor.");
            }
        }

        // Add all instances of AbstractFeatureScreen to ALL_FEATURE_SCREENS list
        for (Class<? extends AbstractFeatureScreen> featureScreen : featureScreenClasses) {
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
                SpeedrunnerMod.warn("Skipping " + featureScreen.getName() + ": doesn't have (Screen) constructor.");
            }
        }

        info("Client-side Speedrunner Mod features have successfully loaded!");
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
    public static void fixOptions() {
        if (BrokenModOptions.playingMode) {
            options().main.playingMode = ModOptions.PlayingMode.EASY;
        }

        if (BrokenModOptions.structureSpawnRates) {
            options().main.structureSpawnRates = ModOptions.StructureSpawnRate.COMMON;
        }

        if (BrokenModOptions.blockBreakingMultiplier) {
            options().main.blockBreakingMultiplier = 1;
        }

        if (BrokenModOptions.strongholdPortalRoomCount) {
            options().main.strongholdPortalRoomCount = 3;
        }

        if (BrokenModOptions.strongholdLibraryCount) {
            options().main.strongholdLibraryCount = 2;
        }

        if (BrokenModOptions.netherPortalCooldown) {
            options().main.netherPortalDelay = 2;
        }

        if (BrokenModOptions.mobSpawningRate) {
            options().main.mobSpawningRate = ModOptions.MobSpawningRate.HIGH;
        }

        if (BrokenModOptions.leaderboards) {
            options().main.leaderboardsMode = false;
        }

        if (BrokenModOptions.speedrunnersWastelandBiomeWeight) {
            options().advanced.speedrunnersWastelandBiomeWeight = 9;
        }

        if (BrokenModOptions.iCarusFireworksInventorySlot) {
            options().advanced.iCarusFireworksInventorySlot = 1;
        }

        if (BrokenModOptions.infiniPearlInventorySlot) {
            options().advanced.infiniPearlInventorySlot = 1;
        }

        if (BrokenModOptions.itemMessages) {
            options().client.itemMessages = ModOptions.ItemMessages.CHAT;
        }

        if (BrokenModOptions.gameMode) {
            options().client.gameMode = ModOptions.GameMode.SURVIVAL;
        }

        if (BrokenModOptions.difficulty) {
            options().client.difficulty = ModOptions.Difficulty.EASY;
        }

        ModOptions.saveConfig();
    }

    /**
     * Returns the {@code minimum brightness} value for the speedrunner mod.
     */
    public static double getMinBrightness() {
        return options().advanced.minimumBrightness;
    }

    /**
     * Returns the {@code maximum brightness} value for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return options().advanced.maximumBrightness;
    }
}