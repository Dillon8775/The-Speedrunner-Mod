package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlockFamilies;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.event.ModEventCallbacks;
import net.dillon.speedrunnermod.item.ModFuels;
import net.dillon.speedrunnermod.item.ModItemGroups;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.packet.ModPackets;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.dillon.speedrunnermod.screen.ModScreenHandlerTypes;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.*;
import net.dillon.speedrunnermod.village.ModPointOfInterestTypes;
import net.dillon.speedrunnermod.village.ModTradeOffers;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.dillon.speedrunnermod.world.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The home initializer for the Speedrunner Mod.
 */
public class SpeedrunnerMod implements ModInitializer {
    public static final String MOD_VERSION = "v1.11";
    public static final String MC_VERSION = "1.21.5";
    public static final String VERSION = "Version: " + MOD_VERSION;
    public static final String THE_SPEEDRUNNER_MOD_STRING = "The Speedrunner Mod";
    public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
    public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
    public static boolean safeBoot;
    private static final Logger LOGGER = LogManager.getLogger("Speedrunner Mod");

    /**
     * Initializes/registers all Speedrunner mod features, items, blocks, etc.
     */
    @Override
    public void onInitialize() {
        ModPackets.registerPackets();

        ModWorldGen.initializeWorldGenFeatures();

        ModEntityTypes.initializeEntityTypes();

        ModPointOfInterestTypes.initializePois();
        ModVillagers.registerVillagerProfessions();
        ModTradeOffers.registerTradeOffers();

        ModCriterions.initializeCriterions();
        ModDataComponentTypes.initializeDataComponents();

        ModBlocks.initializeBlocks();
        ModBlockFamilies.initializeBlockFamilies();
        ModItems.initializeItems();
        ModItemGroups.registerModifiedItemGroups();

        ModEventCallbacks.registerEventCallbacks();

        ModBlockTags.initializeBlockTags();
        ModEnchantmentTags.initializeEnchantmentTags();
        ModFluidTags.initializeFluidTags();
        ModItemTags.initializeItemTags();
        ModStructureTags.initializeStructureTags();

        ModSoundEvents.initializeSoundEvents();

        ModEnchantments.initializeEnchantments();
        ModRecipes.registerCustomRecipes();
        ModFuels.registerFuels();

        ModScreenHandlerTypes.initializeScreenHandlers();

        safeBoot = false;
        configHandler().load();

        if (options().main.playingMode != null && options().main.playingMode.doom()) {
            info("You dare to attempt Doom Mode? Good luck...");
        }

        info("The Speedrunner Mod (" + MOD_VERSION + ")" + " has successfully been loaded!");
    }

    /**
     * Sends an {@code info} message in console.
     */
    public static void info(String info) {
        LOGGER.info(info);
    }

    /**
     * Sends a {@code warning} message in console.
     */
    public static void warn(String warning) {
        LOGGER.warn(warning);
    }

    /**
     * Sends a {@code error} message in console.
     * <p>Mainly used for debugging and testing purposes.</p>
     */
    public static void error(String error) {
        LOGGER.error(error);
    }

    /**
     * Returns the Speedrunner Mod {@code options.}
     */
    public static ModOptions options() {
        return ModOptions.OPTIONS.getInstance();
    }

    /**
     * Returns the Speedrunner Mod {@code options handler} (for saving/loading config).
     */
    public static ModOptions.Handler configHandler() {
        return ModOptions.OPTIONS;
    }

    /**
     * Saves all speedrunner mod option changes on the dedicated {@code server-side}
     */
    public static void saveDedicatedServerChanges() {
        configHandler().save();
    }

    /**
     * Returns a new {@link Identifier} with the {@code Speedrunner Mod's namespace.}
     */
    public static Identifier ofSpeedrunnerMod(String path) {
        return Identifier.of("speedrunnermod", path);
    }
}