package net.dillon.speedrunnermod;

import net.dillon.speedrunnermod.block.ModBlockFamilies;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.event.ModEventCallbacks;
import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModFuels;
import net.dillon.speedrunnermod.item.ModItemGroups;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.*;
import net.dillon.speedrunnermod.util.MathUtil;
import net.dillon.speedrunnermod.village.ModTradeOffers;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.dillon.speedrunnermod.world.ModWorldGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.dillon.speedrunnermod.option.ModOptions.createListOption;
import static net.dillon.speedrunnermod.option.ModOptions.createStructureSpawnRateOption;

/**
 * The home initializer for the Speedrunner Mod.
 */
public class SpeedrunnerMod implements ModInitializer {
    public static final String MOD_ID = "speedrunnermod";
    public static final String MOD_VERSION = "v1.9.9";
    public static final String MC_VERSION = "1.21.4x";
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
        ModWorldGen.initializeWorldGenFeatures();

        ModBoats.initializeBoats();

        ModBlocks.initializeBlocks();
        ModBlockFamilies.initializeBlockFamilies();
        ModBlockItems.initializeBlockItems();
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

        ModVillagers.initializeVillagerProfessions();
        ModTradeOffers.registerTradeOffers();

        safeBoot = false;
        ModOptions.loadConfig();

        if (options().main.playingMode.doom()) {
            info("You dare to attempt Doom Mode? Good luck...");
        }

        Leaderboards.initializeLeaderboards();

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
     */
    public static void error(String error) {
        LOGGER.error(error);
    }

    /**
     * Returns the Speedrunner Mod {@code options.}
     */
    public static ModOptions options() {
        return ModOptions.OPTIONS;
    }

    /**
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    @Environment(EnvType.CLIENT)
    public static void resetOptions() {
        options().main.playingMode = ModOptions.PlayingMode.EASY;
        options().main.structureSpawnRates = ModOptions.StructureSpawnRate.COMMON;
        options().main.fasterBlockBreaking = true;
        options().main.blockBreakingMultiplier = 1;
        options().main.betterBiomes = true;
        options().main.iCarusMode = false;
        options().main.infiniPearlMode = false;
        options().main.dragonPerchTime = 8;
        options().main.killGhastOnFireball = false;
        options().main.betterVillagerTrades = true;
        options().main.fireproofItems = true;
        options().main.customBiomesAndCustomBiomeFeatures = true;
        options().main.commonOres = true;
        options().main.lavaBoats = true;
        options().main.netherWater = true;
        options().main.betterFoods = true;
        options().main.fallDamage = true;
        options().main.kineticDamage = true;
        options().main.strongholdDistance = 4;
        options().main.strongholdSpread = 3;
        options().main.strongholdCount = 128;
        options().main.strongholdPortalRoomCount = 3;
        options().main.strongholdLibraryCount = 2;
        options().main.mobSpawningRate = ModOptions.MobSpawningRate.HIGH;
        options().main.fasterSpawners = true;
        options().main.netherPortalDelay = 2;
        options().main.throwableFireballs = true;
        options().main.arrowsDestroyBeds = true;
        options().main.globalNetherPortals = true;
        options().main.betterAnvil = true;
        options().main.anvilCostLimit = 10;
        options().main.higherEnchantmentLevels = true;
        options().main.rightClickToRemoveSilkTouch = true;
        options().main.customDataGeneration = true;
        options().main.leaderboardsMode = false;

        options().client.fog = true;
        options().client.itemTooltips = true;
        options().client.customPanorama = true;
        options().client.itemMessages = ModOptions.ItemMessages.ACTIONBAR;
        options().client.confirmMessages = true;
        options().client.socialButtons = false;
        options().client.fastWorldCreation = true;
        options().client.gameMode = ModOptions.GameMode.SURVIVAL;
        options().client.difficulty = ModOptions.Difficulty.EASY;
        options().client.allowCheats = false;
        options().client.showDeathCords = true;

        options().advanced.modifiedStrongholdGeneration = true;
        options().advanced.modifiedStrongholdYGeneration = true;
        options().advanced.modifiedNetherFortressGeneration = true;
        options().advanced.showResetButton = true;
        options().advanced.higherBreathTime = true;
        options().advanced.generateSpeedrunnerWood = true;
        options().advanced.speedrunnersWastelandBiomeWeight = 9;
        options().advanced.longerDragonPerchStayTime = true;
        options().advanced.decreasedZombifiedPiglinScareDistance = true;
        options().advanced.enderEyeBreakingCooldown = 60;
        options().advanced.piglinAwakenerPiglinCount = 10;
        options().advanced.iCarusFireworksInventorySlot = 1;
        options().advanced.infiniPearlInventorySlot = 1;
        options().advanced.fireballExplosionPower = 1;
        options().advanced.minimumBrightness = 0.0D;
        options().advanced.maximumBrightness = 12.0D;
        options().advanced.dragonKillsNearbyHostileEntities = true;
        options().advanced.dragonImmunityFromGiantAndWither = true;
        options().advanced.annulEyePortalRoomDistanceXYZ = createListOption(-128, -128, -128, 128, 128, 128);
        options().advanced.piglinAwakenerPiglinDistanceXYZ = createListOption(100.0D, 100.0D, 100.0D);
        options().advanced.blazeSpotterDistanceXYZ = createListOption(-156, -72, -156, 156, 72, 156);
        options().advanced.raidEradicatorDistanceXYZ = createListOption(300.0D, 300.0D, 300.0D);
        options().advanced.dragonsPearlDragonDistanceXYZ = createListOption(150.0D, 150.0D, 150.0D);
        options().advanced.dragonKillsHostileEntitiesDistance = createListOption(200.0D, 200.0D, 200.0D);
        options().advanced.dragonImmunityDetectionDistanceForGiant = createListOption(200.0D, 200.0D, 200.0D);
        options().advanced.dragonImmunityDetectionDistanceForWither = createListOption(300.0D, 300.0D, 300.0D);

        options().structureSpawnRates.ancientCities = createStructureSpawnRateOption(16, 8);
        options().structureSpawnRates.villages = createStructureSpawnRateOption(16, 8);
        options().structureSpawnRates.desertPyramids = createStructureSpawnRateOption(10, 5);
        options().structureSpawnRates.junglePyramids = createStructureSpawnRateOption(10, 5);
        options().structureSpawnRates.pillagerOutposts = createStructureSpawnRateOption(10, 5);
        options().structureSpawnRates.endCities = createStructureSpawnRateOption(7, 3);
        options().structureSpawnRates.woodlandMansions = createStructureSpawnRateOption(25, 12);
        options().structureSpawnRates.ruinedPortals = createStructureSpawnRateOption(9, 4);
        options().structureSpawnRates.shipwrecks = createStructureSpawnRateOption(10, 5);
        options().structureSpawnRates.trialChambers = createStructureSpawnRateOption(12, 6);
        options().structureSpawnRates.netherComplexes = createStructureSpawnRateOption(8, 4);

        options().mixins.terraBlenderSurfaceRuleDataMixin = true;
        options().mixins.backgroundRendererMixin = true;
        options().mixins.simpleOptionMixin = true;
        options().mixins.logoDrawerMixin = true;
        options().mixins.renderLayersMixin = true;
    }

    /**
     * Returns a new {@link Identifier} with the {@code Speedrunner Mod's namespace.}
     */
    public static Identifier ofSpeedrunnerMod(String path) {
        return Identifier.of(MOD_ID, path);
    }

    /**
     * Returns the player's death coordinates.
     */
    public static Text deathCords(double x, double y, double z) {
        return Text.translatable("speedrunnermod.player_death_cords", MathUtil.roundToOneDecimalPlace(x), MathUtil.roundToOneDecimalPlace(y), MathUtil.roundToOneDecimalPlace(z));
    }

    /**
     * See {@link ModWorldGen} for more.
     */
    public static int getAncientCitySpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 10;
        } else if (options().main.structureSpawnRates.common()) {
            return 16;
        } else if (options().main.structureSpawnRates.normal() || options().main.structureSpawnRates.ddefault()) {
            return 24;
        } else if (options().main.structureSpawnRates.rare()) {
            return 28;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 32;
        } else {
            return options().structureSpawnRates.ancientCities[0];
        }
    }

    public static int getAncientCitySeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() || options().main.structureSpawnRates.normal() || options().main.structureSpawnRates.ddefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 12;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.ancientCities[1];
        }
    }

    public static int getVillageSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 10;
        } else if (options().main.structureSpawnRates.common()) {
            return 16;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 42;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.villages[0];
        }
    }

    public static int getVillageSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.common()) {
            return 9;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.villages[1];
        }
    }

    public static int getDesertPyramidSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 42;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.desertPyramids[0];
        }
    }

    public static int getDesertPyramidSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.desertPyramids[1];
        }
    }

    public static int getJungleTempleSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.junglePyramids[0];
        }
    }

    public static int getJungleTempleSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.junglePyramids[1];
        }
    }

    public static int getPillagerOutpostSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.pillagerOutposts[0];
        }
    }

    public static int getPillagerOutpostSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.pillagerOutposts[1];
        }
    }

    public static int getEndCitySpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 5;
        } else if (options().main.structureSpawnRates.common()) {
            return 7;
        } else if (options().main.structureSpawnRates.normal()) {
            return 15;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 20;
        } else if (options().main.structureSpawnRates.rare()) {
            return 25;
        } else {
            return options().structureSpawnRates.endCities[0];
        }
    }

    public static int getEndCitySeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 4;
        } else if (options().main.structureSpawnRates.common()) {
            return 6;
        } else if (options().main.structureSpawnRates.normal()) {
            return 10;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 11;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 18;
        } else {
            return options().structureSpawnRates.endCities[1];
        }
    }

    public static int getWoodlandMansionSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 6;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 16;
        } else if (options().main.structureSpawnRates.common()) {
            return 25;
        } else if (options().main.structureSpawnRates.normal()) {
            return 40;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 80;
        } else if (options().main.structureSpawnRates.rare()) {
            return 100;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 120;
        } else {
            return options().structureSpawnRates.woodlandMansions[0];
        }
    }

    public static int getWoodlandMansionSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault() || options().main.structureSpawnRates.rare()) {
            return 20;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 25;
        } else {
            return options().structureSpawnRates.woodlandMansions[1];
        }
    }

    public static int getRuinedPortalSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common()) {
            return 9;
        } else if (options().main.structureSpawnRates.normal()) {
            return 16;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 40;
        } else if (options().main.structureSpawnRates.rare()) {
            return 50;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 60;
        } else {
            return options().structureSpawnRates.ruinedPortals[0];
        }
    }

    public static int getRuinedPortalSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 6;
        } else if (options().main.structureSpawnRates.common()) {
            return 8;
        } else if (options().main.structureSpawnRates.normal()) {
            return 9;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 15;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.ruinedPortals[1];
        }
    }

    public static int getShipwreckSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 24;
        } else if (options().main.structureSpawnRates.rare()) {
            return 30;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 40;
        } else {
            return options().structureSpawnRates.shipwrecks[0];
        }
    }

    public static int getShipwreckSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() ||
                options().main.structureSpawnRates.normal()) {
            return 8;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 4;
        } else if (options().main.structureSpawnRates.rare()) {
            return 9;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 10;
        } else {
            return options().structureSpawnRates.shipwrecks[1];
        }
    }

    public static int getTrialChambersSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 5;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 12;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 34;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 45;
        } else {
            return options().structureSpawnRates.trialChambers[0];
        }
    }

    public static int getTrialChambersSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() ||
                options().main.structureSpawnRates.normal()) {
            return 8;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 12;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.trialChambers[1];
        }
    }

    public static int getNetherComplexesSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 6;
        } else if (options().main.structureSpawnRates.common()) {
            return 8;
        } else if (options().main.structureSpawnRates.normal()) {
            return 15;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 27;
        } else if (options().main.structureSpawnRates.rare()) {
            return 34;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 40;
        } else {
            return options().structureSpawnRates.netherComplexes[0];
        }
    }

    public static int getNetherComplexesSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 5;
        } else if (options().main.structureSpawnRates.common()) {
            return 7;
        } else if (options().main.structureSpawnRates.normal() ||
                options().main.structureSpawnRates.rare()) {
            return 8;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 10;
        } else {
            return options().structureSpawnRates.netherComplexes[1];
        }
    }

    public static int getSpeedrunnerWaterColor() {
        return 0x85C1E9;
    }

    public static int getSpeedrunnerWaterFogColor() {
        return 0x85C1E9;
    }

    public static int getMaximumAmountOfPiglinAllowedViaPiglinAwakener() {
        return options().advanced.piglinAwakenerPiglinCount;
    }

    public static float getBoatInLavaVelocityMultiplier() {
        return 0.95F;
    }

    public static float getSpeedrunnerBoatVelocityMultiplier() {
        return 1.035F;
    }

    public static float getBedBlockExplosionPower(World world) {
        if (options().main.playingMode.doom()) {
            return world.getRegistryKey() == World.END ? 15.0F : 5.0F;
        } else {
            return 5.0F;
        }
    }

    public static int getFireFromLavaTime() {
        return options().main.playingMode.doom() ? 15 : 7;
    }

    public static float getLavaDamageAmount() {
        return options().main.playingMode.doom() ? 4.0F : 2.0F;
    }

    public static int getPlayerBreathTime() {
        return options().advanced.higherBreathTime ? 8 : 4;
    }

    public static int getBlazeFireballCooldown() {
        return options().main.playingMode.doom() ? 60 : 180;
    }

    public static int getDolphinRange() {
        return 200;
    }

    public static int getEnderDragonFireballInstantDamageAmplifier() {
        return options().main.playingMode.doom() ? 1 : 0;
    }

    public static double getEnderDragonMaxHealth() {
        return options().main.playingMode.doom() ? 500.0D : 100.0D;
    }

    public static float getEnderDragonEndCrystalHealAmount() {
        return options().main.playingMode.doom() ? 1.7F : 0.1F;
    }

    public static float getEnderDragonDamageMultiplier() {
        return options().main.playingMode.doom() ? 12.0F : 3.0F;
    }

    public static float getEnderDragonEndCrystalDestroyedHealthAmount() {
        return options().main.playingMode.doom() ? 3.0F : 20.0F;
    }

    public static float getEnderDragonStayPerchedTime() {
        if (options().advanced.longerDragonPerchStayTime) {
            return options().main.playingMode.doom() ? 0.18F : 0.60F;
        } else {
            return 0.25F;
        }
    }

    public static float getEnderPearlDamageMultiplier() {
        return options().main.playingMode.doom() ? 5.0F : 2.0F;
    }

    public static int getGhastFireballCooldown() {
        return options().main.playingMode.doom() ? -5 : -40;
    }

    public static int getSlimeJumpTime() {
        return options().main.playingMode.doom() ? 20 : 100;
    }

    public static float getSlimeDamageMultiplier() {
        return options().main.playingMode.doom() ? 2.2F : 1.5F;
    }

    public static double getZombifiedPiglinRunawayDistance() {
        return options().advanced.decreasedZombifiedPiglinScareDistance ? 2.0D : 6.0D;
    }

    public static int getSilverfishCallForHelpDelay() {
        return options().main.playingMode.doom() ? 20 : 100;
    }

    public static int getFireballFireTime() {
        return options().main.playingMode.doom() ? 6 : 3;
    }

    public static float getFireballDamageMultiplier() {
        return options().main.playingMode.doom() ? 5.0F : 1.0F;
    }

    public static float getVexDecayDamageMultiplier() {
        return options().main.playingMode.doom() ? 100.0F : 1.0F;
    }

    public static double getWitherMaxHealth() {
        return options().main.playingMode.doom() ? 150.0D : 100.0D;
    }

    public static int getWitherSkeletonWitherEffectDuration() {
        return options().main.playingMode.doom() ? 200 : 60;
    }

    public static int getStrongholdMinY() {
        return options().main.playingMode.doom() ? -48 : 27;
    }

    public static int getStrongholdMaxY() {
        int seaLevel = 63;
        return options().main.playingMode.doom() ? 0 : seaLevel;
    }

    public static float getEnderEyeChance() {
        return options().main.playingMode.doom() ? 0.99F : 0.6F;
    }

    public static int getOreDiamondChance() {
        return 8;
    }

    public static int getOreDiamondBuriedChance() {
        return 9;
    }

    public static int getOreDiamondLargeChance() {
        return 5;
    }

    public static int getOreLapisChance() {
        return 3;
    }

    public static int getOreLapisBuriedChance() {
        return 4;
    }

    public static int getTreesPlainsCount() {
        return 1;
    }
}