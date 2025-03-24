package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.client.keybind.ModKeybindings;
import net.dillon.speedrunnermod.client.render.ModRenderers;
import net.dillon.speedrunnermod.client.screen.ModHandledScreens;
import net.dillon.speedrunnermod.option.BrokenModOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * The home initializer for the client-side of the Speedrunner Mod.
 */
@Environment(EnvType.CLIENT)
public class SpeedrunnerModClient implements ClientModInitializer {
    public static boolean speedrunIGTMissing = false;

    /**
     * Initializes all the client-side {@code speedrunner mod} renderers, configurations, etc.
     */
    @Override
    public void onInitializeClient() {
        ModRenderers.initializeRenderers();
        ModHandledScreens.registerScreens();

        if (options().main.leaderboardsMode && !isSpeedrunIGTLoaded()) {
            speedrunIGTMissing = true;
            warn("Detected that SpeedrunIGT is not loaded, you should probably download this mod if you would like to submit speedruns to the leaderboards.");
        }

        ModKeybindings.initializeKeybinds();

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