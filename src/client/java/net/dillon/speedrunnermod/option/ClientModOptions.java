package net.dillon.speedrunnermod.option;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;
import static net.dillon.speedrunnermod.util.ModUtil.createListOption;
import static net.dillon.speedrunnermod.util.ModUtil.createStructureSpawnRateOption;

/**
 * All {@code Client-side Speedrunner Mod options.}
 */
@Environment(EnvType.CLIENT)
public class ClientModOptions {
    public final Client client = new Client();
    public final Mixins mixins = new Mixins();
    public final StoredValues storedValues = new StoredValues();
    public final TutorialMode tutorialMode = new TutorialMode();

    public static final ClientModOptions.Handler CLIENT_OPTIONS = new Handler();

    /**
     * A handler class for handling the client-side options file.
     */
    public static class Handler extends BaseOptions<ClientModOptions> {

        protected Handler() {
            super(ModUtil.CLIENT_CONFIG_FILE_NAME);
        }

        @Override
        protected ClientModOptions createDefault() {
            return new ClientModOptions();
        }

        @Override
        protected Class<ClientModOptions> getConfigClass() {
            return ClientModOptions.class;
        }

        @Override
        protected void safeCheck() {
            if (clientOptions().client.itemMessages == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
                isSafe(false);
                ClientBrokenModOptions.itemMessages = true;
            }

            if (clientOptions().client.gameMode == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gameMode");
                isSafe(false);
                ClientBrokenModOptions.gameMode = true;
            }

            if (clientOptions().client.difficulty == null) {
                error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
                isSafe(false);
                ClientBrokenModOptions.difficulty = true;
            }
        }
    }

    public static class Client {

        /**
         * Tutorial mode, takes the player through various different features in the mod.
         */
        @RequiresRestart
        public boolean tutorialMode = false;

        /**
         * Determines if the first-time playing screens should load.
         */
        public boolean firstTimePlaying = true;

        /**
         * Enable/disable Minecraft's default fog.
         */
        public boolean fog = true;

        /**
         * Enables/disables fullbright.
         */
        public boolean fullBright = false;

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public ClientModOptions.ItemMessages itemMessages = ClientModOptions.ItemMessages.ACTIONBAR;

        /**
         * Create a new world with just one click.
         */
        public boolean fastWorldCreation = true;

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public ClientModOptions.GameMode gameMode =  ClientModOptions.GameMode.SURVIVAL;

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public ClientModOptions.Difficulty difficulty =  ClientModOptions.Difficulty.EASY;

        /**
         * Allows cheats when a new world is created.
         */
        public boolean allowCheats = false;

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public boolean showResetButton = true;

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public double minimumBrightness = 0.0D;

        /**
         * The maximum brightness amount for the Speedrunner Mod.
         */
        public double maximumBrightness = 12.0D;
    }

    public static class Mixins {

        /**
         * Applies the fog option into the game.
         * <p>Disable this if you are experiencing compatibility issues with other mods that may also mess with fog settings.</p>
         */
        @RequiresRestart
        public boolean backgroundRendererMixin = true;

        /**
         * Applies the simple option mixin into the game, which controls the brightness option slider.
         * <p>Disable this if you are experiencing compatibility issues with other mods, or if you don't want the new brightness slider.</p>
         */
        @RequiresRestart
        public boolean simpleOptionMixin = true;

        /**
         * Applies the logo drawer mixin into the game, which adds the custom speedrunner edition logo to the title screen.
         * <p>Disable this if you do not want the custom logo, or are making a custom texture pack that uses a different logo, or are experiencing compatibility issues with other mods.</p>
         */
        @RequiresRestart
        public boolean logoDrawerMixin = true;

        /**
         * Applies the render layers mixin into the game, which registers a render layer for lava boats.
         * <p>I would only disable this if you absolutely have to, or if you are experiencing noticeable issues with lava boats, or aren't using them.</p>
         */
        @RequiresRestart
        public boolean renderLayersMixin = true;
    }

    /**
     * Unconfigurable options; just used for storage and reference.
     */
    public static class StoredValues {

        /**
         * Returns the last completed tutorial step message translation key(s). These messages are sent when the player rejoins the world.
         */
        public List<String> lastCompletedTutorialStepTranslations = new ArrayList<>();
    }

    /**
     * All booleans for doing certain things in the tutorial mode.
     */
    @ChatGPT(Credit.MOST_CREDIT)
    public static class TutorialMode implements net.dillon.speedrunnermod.tutorial.TutorialMode {
        public boolean enterWorld = false;
        public boolean obtainedSpeedrunnerPickaxe = false;
        public boolean obtainedSpeedrunnerPaddle = false;
        public boolean obtainedSpeedrunnerBoat = false;
        public boolean obtainedSpeedrunnerArmorSet = false;
        public boolean obtainedSpeedrunnerShield = false;
        public boolean obtainedInfernoEye = false;
        public boolean usedInfernoEye = false;
        public boolean obtainedPiglinAwakener = false;
        public boolean usedPiglinAwakener = false;
        public boolean obtainedBlazeSpotter = false;
        public boolean usedBlazeSpotter = false;
        public boolean obtainedSpeedrunnersEye = false;
        public boolean changedSpeedrunnersEyeLocator = false;
        public boolean usedSpeedrunnersEye = false;
        public boolean obtainedEnderEye = false;
        public boolean usedEnderEye = false;
        public boolean obtainedDragonsPearl = false;
        public boolean obtainedAnnulEye = false;
        public boolean usedAnnulEye = false;
        public boolean enteredEnd = false;
        public boolean obtainedTotem = false;
        public boolean freeFalledIntoVoid = false;
        public boolean obtainedSpeedrunnersTotem = false;
        public boolean brokenDoomBlock = false;
        public boolean killedGoliath = false;
        public boolean killedWither = false;
        public boolean usedDragonsPearl = false;
        public boolean killedDragon = false;
        public boolean exitEnd = false;
        public boolean brokenExperienceOre = false;
        public boolean obtainedSpeedrunnersWorkbench = false;
        public boolean transferedEnchantments = false;
        public boolean interactedWithRetiredSpeedrunner = false;
        public boolean obtainedEnderThruster = false;
        public boolean usedEnderThruster = false;
        public boolean obtainedDragonsSword = false;
        public boolean obtainedWitherBone = false;
        public boolean obtainedWitherSword = false;
        public boolean obtainedEnderMatter = false;
        public boolean obtainedInfiniPearl = false;

        @Override
        public boolean getStep(TutorialStep step) {
            return switch (step) {
                case ENTER_WORLD -> enterWorld;
                case CRAFT_SPEEDRUNNER_PICKAXE -> obtainedSpeedrunnerPickaxe;
                case CRAFT_SPEEDRUNNER_PADDLE -> obtainedSpeedrunnerPaddle;
                case CRAFT_SPEEDRUNNER_BOAT -> obtainedSpeedrunnerBoat;
                case CRAFT_SPEEDRUNNER_ARMOR -> obtainedSpeedrunnerArmorSet;
                case CRAFT_SPEEDRUNNER_SHIELD -> obtainedSpeedrunnerShield;
                case CRAFT_INFERNO_EYE -> obtainedInfernoEye;
                case USE_INFERNO_EYE -> usedInfernoEye;
                case CRAFT_PIGLIN_AWAKENER -> obtainedPiglinAwakener;
                case USE_PIGLIN_AWAKENER -> usedPiglinAwakener;
                case CRAFT_BLAZE_SPOTTER -> obtainedBlazeSpotter;
                case USE_BLAZE_SPOTTER -> usedBlazeSpotter;
                case CRAFT_SPEEDRUNNERS_EYE -> obtainedSpeedrunnersEye;
                case CHANGE_SPEEDRUNNERS_EYE_LOCATOR -> changedSpeedrunnersEyeLocator;
                case USE_SPEEDRUNNERS_EYE -> usedSpeedrunnersEye;
                case USE_ENDER_EYE -> usedEnderEye;
                case CRAFT_ANNUL_EYE -> obtainedAnnulEye;
                case CRAFT_DRAGONS_PEARL -> obtainedDragonsPearl;
                case CRAFT_ENDER_EYE -> obtainedEnderEye;
                case USE_ANNUL_EYE -> usedAnnulEye;
                case ENTER_END -> enteredEnd;
                case OBTAIN_TOTEM_OF_UNDYING -> obtainedTotem;
                case FREE_FALL_INTO_VOID -> freeFalledIntoVoid;
                case OBTAIN_SPEEDRUNNERS_TOTEM -> obtainedSpeedrunnersTotem;
                case BREAK_DOOM_BLOCK -> brokenDoomBlock;
                case KILL_GOLIATH -> killedGoliath;
                case KILL_WITHER -> killedWither;
                case USE_DRAGONS_PEARL -> usedDragonsPearl;
                case KILL_DRAGON -> killedDragon;
                case EXIT_END -> exitEnd;
                case MINE_EXPERIENCE_ORE -> brokenExperienceOre;
                case CRAFT_SPEEDRUNNERS_WORKBENCH -> obtainedSpeedrunnersWorkbench;
                case TRANSFER_ENCHANTMENTS -> transferedEnchantments;
                case INTERACT_WITH_RETIRED_SPEEDRUNNER -> interactedWithRetiredSpeedrunner;
                case OBTAIN_ENDER_THRUSTER -> obtainedEnderThruster;
                case USE_ENTER_THRUSTER -> usedEnderThruster;
                case OBTAIN_DRAGONS_SWORD -> obtainedDragonsSword;
                case OBTAIN_WITHER_BONE -> obtainedWitherBone;
                case OBTAIN_WITHER_SWORD -> obtainedWitherSword;
                case OBTAIN_ENDER_MATTER -> obtainedEnderMatter;
                case OBTAIN_INFINI_PEARL -> obtainedInfiniPearl;
            };
        }

        @Override
        public void setStep(TutorialStep step, boolean value) {
            switch (step) {
                case ENTER_WORLD -> enterWorld = value;
                case CRAFT_SPEEDRUNNER_PICKAXE -> obtainedSpeedrunnerPickaxe = value;
                case CRAFT_SPEEDRUNNER_PADDLE -> obtainedSpeedrunnerPaddle = value;
                case CRAFT_SPEEDRUNNER_BOAT -> obtainedSpeedrunnerBoat = value;
                case CRAFT_SPEEDRUNNER_ARMOR -> obtainedSpeedrunnerArmorSet = value;
                case CRAFT_SPEEDRUNNER_SHIELD -> obtainedSpeedrunnerShield = value;
                case CRAFT_INFERNO_EYE -> obtainedInfernoEye = value;
                case USE_INFERNO_EYE -> usedInfernoEye = value;
                case CRAFT_PIGLIN_AWAKENER -> obtainedPiglinAwakener = value;
                case USE_PIGLIN_AWAKENER -> usedPiglinAwakener = value;
                case CRAFT_BLAZE_SPOTTER -> obtainedBlazeSpotter = value;
                case USE_BLAZE_SPOTTER -> usedBlazeSpotter = value;
                case CRAFT_SPEEDRUNNERS_EYE -> obtainedSpeedrunnersEye = value;
                case CHANGE_SPEEDRUNNERS_EYE_LOCATOR -> changedSpeedrunnersEyeLocator = value;
                case USE_SPEEDRUNNERS_EYE -> usedSpeedrunnersEye = value;
                case CRAFT_ENDER_EYE -> obtainedEnderEye = value;
                case USE_ENDER_EYE -> usedEnderEye = value;
                case CRAFT_DRAGONS_PEARL -> obtainedDragonsPearl = value;
                case CRAFT_ANNUL_EYE -> obtainedAnnulEye = value;
                case USE_ANNUL_EYE -> usedAnnulEye = value;
                case ENTER_END -> enteredEnd = value;
                case OBTAIN_TOTEM_OF_UNDYING -> obtainedTotem = value;
                case FREE_FALL_INTO_VOID -> freeFalledIntoVoid = value;
                case OBTAIN_SPEEDRUNNERS_TOTEM -> obtainedSpeedrunnersTotem = value;
                case BREAK_DOOM_BLOCK -> brokenDoomBlock = value;
                case KILL_GOLIATH -> killedGoliath = value;
                case KILL_WITHER -> killedWither = value;
                case USE_DRAGONS_PEARL -> usedDragonsPearl = value;
                case KILL_DRAGON -> killedDragon = value;
                case EXIT_END -> exitEnd = value;
                case MINE_EXPERIENCE_ORE -> brokenExperienceOre = value;
                case CRAFT_SPEEDRUNNERS_WORKBENCH -> obtainedSpeedrunnersWorkbench = value;
                case TRANSFER_ENCHANTMENTS -> transferedEnchantments = value;
                case INTERACT_WITH_RETIRED_SPEEDRUNNER -> interactedWithRetiredSpeedrunner = value;
                case OBTAIN_ENDER_THRUSTER -> obtainedEnderThruster = value;
                case USE_ENTER_THRUSTER -> usedEnderThruster = value;
                case OBTAIN_DRAGONS_SWORD -> obtainedDragonsSword = value;
                case OBTAIN_WITHER_BONE -> obtainedWitherBone = value;
                case OBTAIN_WITHER_SWORD -> obtainedWitherSword = value;
                case OBTAIN_ENDER_MATTER -> obtainedEnderMatter = value;
                case OBTAIN_INFINI_PEARL -> obtainedInfiniPearl = value;
            }
        }
    }

    /**
     * All the different {@code GameMode} options.
     */
    public enum GameMode implements TranslatableOption {
        SURVIVAL(0, "speedrunnermod.options.gamemode.survival"),
        CREATIVE(1, "speedrunnermod.options.gamemode.creative"),
        HARDCORE(2, "speedrunnermod.options.gamemode.hardcore"),
        SPECTATOR(3, "speedrunnermod.options.gamemode.spectator");

        private static final GameMode[] VALUES = Arrays.stream(GameMode.values()).sorted(Comparator.comparingInt(GameMode::getId)).toArray(GameMode[]::new);
        private final int id;
        private final String translateKey;

        GameMode(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the fast world creation {@code GameMode} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code GameMode} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static GameMode byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    /**
     * All the different {@code Difficulty} options.
     */
    public enum Difficulty implements TranslatableOption {
        PEACEFUL(0, "speedrunnermod.options.difficulty.peaceful"),
        EASY(1, "speedrunnermod.options.difficulty.easy"),
        NORMAL(2, "speedrunnermod.options.difficulty.normal"),
        HARD(3, "speedrunnermod.options.difficulty.hard");

        private static final Difficulty[] VALUES = Arrays.stream(Difficulty.values()).sorted(Comparator.comparingInt(Difficulty::getId)).toArray(Difficulty[]::new);
        private final int id;
        private final String translateKey;

        Difficulty(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the fast world creation {@code Difficulty} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Difficulty} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static Difficulty byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }
    }

    public enum ItemMessages implements TranslatableOption {
        CHAT(0, "speedrunnermod.options.item_messages.chat"),
        ACTIONBAR(1, "speedrunnermod.options.item_messages.actionbar");

        private static final ItemMessages[] VALUES = Arrays.stream(ItemMessages.values()).sorted(Comparator.comparingInt(ItemMessages::getId)).toArray(ItemMessages[]::new);
        private final int id;
        private final String translateKey;

        ItemMessages(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Item Messages} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Item Messages} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static ItemMessages byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }

        /**
         * Returns true if the {@code Item Messages} option is set to actionbar.
         */
        public boolean isActionbar() {
            return clientOptions().client.itemMessages.equals(ACTIONBAR);
        }
    }

    /**
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    @Environment(EnvType.CLIENT)
    public static void resetAllOptions() {
        options().main.playingMode = ModOptions.PlayingMode.EASY;
        options().main.structureSpawnRates = ModOptions.StructureSpawnRate.COMMON;
        options().main.fasterBlockBreaking = true;
        options().main.blockBreakingMultiplier = 1;
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

        options().advanced.modifiedStrongholdGeneration = true;
        options().advanced.modifiedStrongholdYGeneration = true;
        options().advanced.modifiedNetherFortressGeneration = true;
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
        options().advanced.dragonKillsNearbyHostileEntities = true;
        options().advanced.dragonImmunityFromGoliathAndWither = true;
        options().advanced.annulEyePortalRoomDistanceXYZ = createListOption(-128, -128, -128, 128, 128, 128);
        options().advanced.piglinAwakenerPiglinDistanceXYZ = createListOption(100.0D, 100.0D, 100.0D);
        options().advanced.blazeSpotterDistanceXYZ = createListOption(-156, -72, -156, 156, 72, 156);
        options().advanced.raidEradicatorDistanceXYZ = createListOption(300.0D, 300.0D, 300.0D);
        options().advanced.dragonsPearlDragonDistanceXYZ = createListOption(150.0D, 150.0D, 150.0D);
        options().advanced.dragonKillsHostileEntitiesDistance = createListOption(200.0D, 200.0D, 200.0D);
        options().advanced.dragonImmunityDetectionDistanceForGoliath = createListOption(200.0D, 200.0D, 200.0D);
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

        resetAllTutorialModeOptions();
    }

    /**
     * Resets all of the {@code speedrunner mod client-side options} back to factory default.
     */
    public static void resetAllClientOptions() {
        clientOptions().client.tutorialMode = false;
        clientOptions().client.firstTimePlaying = true;
        clientOptions().client.fog = true;
        clientOptions().client.itemMessages = ClientModOptions.ItemMessages.ACTIONBAR;
        clientOptions().client.fastWorldCreation = true;
        clientOptions().client.gameMode = ClientModOptions.GameMode.SURVIVAL;
        clientOptions().client.difficulty = ClientModOptions.Difficulty.EASY;
        clientOptions().client.allowCheats = false;
        clientOptions().client.showResetButton = true;
        clientOptions().client.minimumBrightness = 0.0D;
        clientOptions().client.maximumBrightness = 12.0D;

        clientOptions().mixins.backgroundRendererMixin = true;
        clientOptions().mixins.simpleOptionMixin = true;
        clientOptions().mixins.logoDrawerMixin = true;
        clientOptions().mixins.renderLayersMixin = true;

        clientOptions().storedValues.lastCompletedTutorialStepTranslations = new ArrayList<>();
    }

    /**
     * Resets all tutorial mode options.
     */
    public static void resetAllTutorialModeOptions() {
        clientOptions().tutorialMode.enterWorld = false;
        clientOptions().tutorialMode.obtainedSpeedrunnerPickaxe = false;
        clientOptions().tutorialMode.obtainedSpeedrunnerPaddle = false;
        clientOptions().tutorialMode.obtainedSpeedrunnerBoat = false;
        clientOptions().tutorialMode.obtainedSpeedrunnerArmorSet = false;
        clientOptions().tutorialMode.obtainedSpeedrunnerShield = false;
        clientOptions().tutorialMode.obtainedInfernoEye = false;
        clientOptions().tutorialMode.usedInfernoEye = false;
        clientOptions().tutorialMode.obtainedPiglinAwakener = false;
        clientOptions().tutorialMode.usedPiglinAwakener = false;
        clientOptions().tutorialMode.obtainedBlazeSpotter = false;
        clientOptions().tutorialMode.usedBlazeSpotter = false;
        clientOptions().tutorialMode.obtainedSpeedrunnersEye = false;
        clientOptions().tutorialMode.changedSpeedrunnersEyeLocator = false;
        clientOptions().tutorialMode.usedSpeedrunnersEye = false;
        clientOptions().tutorialMode.obtainedEnderEye = false;
        clientOptions().tutorialMode.usedEnderEye = false;
        clientOptions().tutorialMode.obtainedDragonsPearl = false;
        clientOptions().tutorialMode.obtainedAnnulEye = false;
        clientOptions().tutorialMode.usedAnnulEye = false;
        clientOptions().tutorialMode.enteredEnd = false;
        clientOptions().tutorialMode.obtainedTotem = false;
        clientOptions().tutorialMode.freeFalledIntoVoid = false;
        clientOptions().tutorialMode.obtainedSpeedrunnersTotem = false;
        clientOptions().tutorialMode.killedGoliath = false;
        clientOptions().tutorialMode.killedWither = false;
        clientOptions().tutorialMode.usedDragonsPearl = false;
        clientOptions().tutorialMode.killedDragon = false;
        clientOptions().tutorialMode.brokenExperienceOre = false;
        clientOptions().tutorialMode.obtainedSpeedrunnersWorkbench = false;
        clientOptions().tutorialMode.transferedEnchantments = false;
        clientOptions().tutorialMode.interactedWithRetiredSpeedrunner = false;
        clientOptions().tutorialMode.obtainedEnderThruster = false;
        clientOptions().tutorialMode.usedEnderThruster = false;
        clientOptions().tutorialMode.obtainedWitherBone = false;
        clientOptions().tutorialMode.obtainedWitherSword = false;
        clientOptions().tutorialMode.obtainedEnderMatter = false;
        clientOptions().tutorialMode.obtainedDragonsSword = false;
        clientOptions().tutorialMode.obtainedInfiniPearl = false;
    }
}
