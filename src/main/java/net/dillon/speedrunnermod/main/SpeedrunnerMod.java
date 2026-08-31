package net.dillon.speedrunnermod.main;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.*;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.entity.ModParticleTypes;
import net.dillon.speedrunnermod.event.ModEventCallbacks;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItemGroups;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.menu.ModMenus;
import net.dillon.speedrunnermod.network.ModPackets;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.ModTags;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.dillon.speedrunnermod.villager.ModPoiTypes;
import net.dillon.speedrunnermod.villager.ModTradeSets;
import net.dillon.speedrunnermod.villager.ModTrades;
import net.dillon.speedrunnermod.villager.ModVillagers;
import net.dillon.speedrunnermod.world.ModWorldGeneration;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * The home initializer for the Speedrunner Mod.
 */
public class SpeedrunnerMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Speedrunner Mod");

    /**
     * Initializes/registers all Speedrunner mod features, items, blocks, etc.
     */
    @Override
    public void onInitialize() {
        ModConstants.initConstants();

        ModConstants.safeBoot = false;
        commonConfigHandler().load();

        ModPackets.registerPackets();

        ModParticleTypes.registerParticles();

        ModWorldGeneration.initializeWorldGenFeatures();

        ModNumberProviders.initializeNumberProviders();

        ModEntityTypes.initializeEntityTypes();

        ModMobEffects.registerStatusEffects();
        ModPotions.initializePotions();

        ModPoiTypes.initializeModPois();
        ModTrades.initializeTrades();
        ModTradeSets.initializeTradeSets();
        ModVillagers.initializeVillagerProfessions();

        ModPredicates.initializeCriterions();

        ModAttributeKeys.initializeAttributeKeys();
        ModAttributes.initializeAttributes();
        ModDataComponentTypes.initializeDataComponents();

        ModBlocks.initializeBlocks();
        ModItems.initializeItems();
        ModItemGroups.initializeItemGroups();

        ModEventCallbacks.registerEventCallbacks();
        ModDispenserBehaviors.registerDispenserBehaviors();

        ModTags.initializeAllTags();

        ModSoundEvents.initializeSoundEvents();

        ModEnchantments.initializeEnchantments();
        ModRecipes.registerModSerializers();

        ModMenus.initializeMenus();

        ServerTickEvents.END_SERVER_TICK.register(TaskScheduler::tick);
        ModHelper.registerInventoryPreserver();

        commonConfigHandler().save();

        if (common().general().mode != null && isDoomMode()) {
            LOGGER.info("You dare to attempt Doom Mode? Good luck...");
        }

        LOGGER.info("The Speedrunner Mod {} (for fabric) loaded successfully!", ModConstants.MOD_VERSION);
    }

    /**
     * Returns the Speedrunner Mod {@code options.}
     */
    public static ModCommonOptions common() {
        return ModCommonOptions.INSTANCE.getInstance();
    }

    /**
     * Returns the Speedrunner Mod {@code options handler} (for saving/loading config).
     */
    public static ModCommonOptions.ModOptionsHandler commonConfigHandler() {
        return ModCommonOptions.INSTANCE;
    }

    /**
     * Saves all speedrunner mod option changes on the dedicated {@code server-side}
     */
    public static void saveDedicatedServerChanges() {
        commonConfigHandler().save();
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