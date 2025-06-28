package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.dillon.speedrunnermod.client.screen.ModHandledScreens;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.option.*;
import net.dillon.speedrunnermod.packet.ClientModPackets;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.gui.screen.Screen;
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
        ClientModPackets.registerClientPackets();

        ModRenderers.initializeRenderers();
        ModHandledScreens.registerScreens();
        ModKeybindings.initializeKeybinds();

        clientConfigHandler().load();

        options().advanced.modIds = new ArrayList<>();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            options().advanced.modIds.add(mod.getMetadata().getId()); // add all mod ids to list
        }

        if (options().main.leaderboardsMode && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

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
                SpeedrunnerMod.debug("Skipping " + modScreen.getName() + ": doesn't have (Screen) constructor.");
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
                SpeedrunnerMod.debug("Skipping " + featureScreen.getName() + ": doesn't have (Screen) constructor.");
            }
        }

        Leaderboards.initializeLeaderboards();

        info("The client-side for The Speedrunner Mod has successfully loaded.");
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options.}
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */
    @Environment(EnvType.CLIENT)
    public static ClientModOptions clientOptions() {
        return ClientModOptions.CLIENT_OPTIONS.getInstance();
    }

    /**
     * Returns the {@code client} Speedrunner Mod {@code options handler} (for saving/loading config).
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */
    @Environment(EnvType.CLIENT)
    public static ClientModOptions.Handler clientConfigHandler() {
        return ClientModOptions.CLIENT_OPTIONS;
    }

    /**
     * Saves all speedrunner mod option changes on the {@code client-side.}
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */
    @Environment(EnvType.CLIENT)
    public static void saveClientChanges() {
        clientConfigHandler().save();
    }

    /**
     * Saves all speedrunner mod option changes (both client and server-side).
     * <p>This should <b>ONLY</b> be called in {@code EnvType.CLIENT} classes and methods.</p>
     */
    @Environment(EnvType.CLIENT)
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

        if (ClientBrokenModOptions.itemMessages) {
            clientOptions().client.itemMessages = ClientModOptions.ItemMessages.CHAT;
        }

        if (ClientBrokenModOptions.gameMode) {
            clientOptions().client.gameMode = ClientModOptions.GameMode.SURVIVAL;
        }

        if (ClientBrokenModOptions.difficulty) {
            clientOptions().client.difficulty = ClientModOptions.Difficulty.EASY;
        }

        saveAllChanges();
    }

    /**
     * Returns the {@code minimum brightness} value for the speedrunner mod.
     */
    public static double getMinBrightness() {
        return clientOptions().client.minimumBrightness;
    }

    /**
     * Returns the {@code maximum brightness} value for the speedrunner mod.
     */
    public static double getMaxBrightness() {
        return clientOptions().client.maximumBrightness;
    }
}