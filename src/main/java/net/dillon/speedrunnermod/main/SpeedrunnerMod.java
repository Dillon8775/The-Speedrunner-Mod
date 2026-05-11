package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlockFamilies;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.command.ModCommands;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.event.ModEventCallbacks;
import net.dillon.speedrunnermod.item.ModFuels;
import net.dillon.speedrunnermod.item.ModItemGroups;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.menu.ModMenus;
import net.dillon.speedrunnermod.network.ModPackets;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.*;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.dillon.speedrunnermod.villager.ModPoiTypes;
import net.dillon.speedrunnermod.villager.ModVillagers;
import net.dillon.speedrunnermod.world.ModWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The home initializer for the Speedrunner Mod.
 */
public class SpeedrunnerMod implements ModInitializer {
    public static final String MOD_VERSION = "v1.12.1";
    public static final String MC_VERSION = "26.1.2";
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
        safeBoot = false;
        configHandler().load();

        ModPackets.registerPackets();
        ModCommands.registerCommands();

        ModParticleTypes.registerParticles();

        ModWorldGen.initializeWorldGenFeatures();

        ModEntityTypes.initializeEntityTypes();

        ModStatusEffects.registerStatusEffects();
        ModPotions.registerPotions();

        ModPoiTypes.initializeModPois();
        ModVillagers.initializeVillagerProfessions();

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
        ModRecipes.registerModSerializers();
        ModFuels.registerFuels();

        ModMenus.initializeScreenHandlers();

        ServerTickEvents.END_SERVER_TICK.register(TaskScheduler::tick);
        ModUtil.registerInventoryPreserver();

        // Get all mod ids, add and sort
        options().advanced.modIds.getCurrentValue().clear();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            String modId = mod.getMetadata().getId();
            boolean exclude = false;
            List<String> excludedMods = List.of(
                    "fabric-",
                    "fabricloader",
                    "java",
                    "mixinextras"
            );
            for (String excludedMod : excludedMods) {
                if (modId.startsWith(excludedMod)) {
                    exclude = true;
                    break;
                }
            }

            if (!exclude) {
                options().advanced.modIds.getCurrentValue().add(modId);
            }
        }
        configHandler().save();

        if (options().main.mode != null && isDoomMode()) {
            info("You dare to attempt Doom Mode? Good luck...");
        }

        info("The Speedrunner Mod " + MOD_VERSION + " (for fabric) loaded successfully!");
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
     * Sends a {@code debug} message to the {@code debug.log} file.
     * <p>These debug messages <i>only</i> show in the {@code debug.log} file.</p>
     */
    public static void debug(String debug) {
        LOGGER.debug(debug);
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
        return Identifier.fromNamespaceAndPath("speedrunnermod", path);
    }

    /**
     * @return {@code true} if the mod is running on {@code EnvType.SERVER}
     */
    public static boolean isEnvironmentTypeServer() {
        return FabricLoader.getInstance().getEnvironmentType().equals(EnvType.SERVER);
    }
}