package net.dillon.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.main.command.argument.ItemStackArgumentTypeMixin;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * All Speedrunner Mod {@code options.}
 * <p>When adding new options...</p>
 * <p>- Must add a check for restart required in restart required screen, only if necessary,</p>
 * <p>- Determine if it is leaderboard-eligible, and then implement into {@link Leaderboards}.</p>
 * <p>- An {@code "isBroken"} check safe boot screen and in {@link ModOptions#safeCheck()}</p>
 * <p>- A ModListOption,</p>
 * <p>- A reset option in {@link ModOptions#resetAllOptions()}.</p>
 */
public class ModOptions {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    public static final String CONFIG = "speedrunnermod-options.json";
    private static File file;
    public static ModOptions OPTIONS = getConfig();
    public final Main main = new Main();
    public final Client client = new Client();
    public final Advanced advanced = new Advanced();
    public final StructureSpawnRates structureSpawnRates = new StructureSpawnRates();
    public final TutorialMode tutorialMode = new TutorialMode();
    public final Mixins mixins = new Mixins();

    /**
     * All {@code Main Speedrunner Mod options.}
     * <p>See additional comments inside of static class for option documentation.</p>
     */
    public static class Main {

        /**
         * Tutorial mode, takes the player through various different features in the mod.
         */
        @RequiresRestart
        public boolean tutorialMode = false;

        /**
         * Determines the playing mode of the mod. The mode determines what features are added.
         */
        @RequiresRestart
        public PlayingMode playingMode = PlayingMode.EASY;

        /**
         * Determines how frequently Minecraft structures generate throughout the world.
         */
        public StructureSpawnRate structureSpawnRates = StructureSpawnRate.COMMON;

        /**
         * Allows certain blocks to be broken faster.
         */
        public boolean fasterBlockBreaking = true;

        /**
         * The multiplier for how faster blocks can be broken.
         */
        public int blockBreakingMultiplier = 1;

        /**
         * Allows certain biomes, such as plains, deserts, savannas, etc., to generate more commonly.
         */
        @Deprecated(forRemoval = true)
        @RequiresRestart
        public boolean betterBiomes = false;

        /**
         * Grants the player with a pre-equipped unbreakable elytra and a stack of flight duration 3 firework rockets.
         */
        public boolean iCarusMode = false;

        /**
         * Grants the player with an ender pearl that does not do damage nor get consumed upon use.
         */
        public boolean infiniPearlMode = false;

        /**
         *  Determines the amount of time (in seconds) that it takes for the ender dragon to automatically perch upon entering the end.
         *  <p>Note: {@code 8 = OFF, 9 = Instant.}</p>
         */
        public int dragonPerchTime = 8;

        /**
         * Instantly kills a ghast when they shoot a fireball.
         */
        public boolean killGhastOnFireball = false;

        /**
         * Improves villager trades by making them less expensive and sell better stuff.
         */
        @RequiresRestart
        public boolean betterVillagerTrades = true;

        /**
         * Allows certain items to be fireproof.
         */
        public boolean fireproofItems = true;

        /**
         * Allows the Speedrunner's Wasteland biome to generate, and allows some additional worldgen features to be added to different biomes.
         */
        @RequiresRestart
        public boolean customBiomesAndCustomBiomeFeatures = true;

        /**
         * Allows certain ores to generate more commonly.
         */
        public boolean commonOres = true;

        /**
         * Allows certain boats to be fireproof.
         */
        public boolean lavaBoats = true;

        /**
         * Allows water to be placed in the nether.
         */
        public boolean netherWater = true;

        /**
         * Improves most vanilla food items to restore more hunger bars and give more saturation.
         */
        public boolean betterFoods = true;

        /**
         * Enables/disables fall damage.
         */
        public boolean fallDamage = true;

        /**
         * Enables/disables "kinetic" damage (damage taken when flying into walls with an elytra).
         */
        public boolean kineticDamage = true;

        /**
         * Determines how far from spawn strongholds can generate.
         */
        @RequiresRestart
        public int strongholdDistance = 4;

        /**
         * Determines how far apart strongholds can generate from each other.
         */
        @RequiresRestart
        public int strongholdSpread = 3;

        /**
         * Determines the total amount of strongholds that can generate in a singular Minecraft world.
         */
        @RequiresRestart
        public int strongholdCount = 128;

        /**
         * Determines how many stronghold portal rooms can generate per stronghold.
         */
        @RequiresRestart
        public int strongholdPortalRoomCount = 3;

        /**
         * Determines how many libraries can generate per stronghold.
         */
        @RequiresRestart
        public int strongholdLibraryCount = 2;

        /**
         * Determines how big of packs mobs can spawn in.
         */
        public MobSpawningRate mobSpawningRate = MobSpawningRate.HIGH;

        /**
         * Sets the delay when entering/exiting the nether via a nether portal block.
         */
        public int netherPortalDelay = 2;

        /**
         * Allows fireballs to be thrown.
         */
        public boolean throwableFireballs = true;

        /**
         * Allows arrows to blow up beds.
         */
        public boolean arrowsDestroyBeds = true;

        /**
         * Allows nether portals to be built in the end.
         */
        public boolean globalNetherPortals = true;

        /**
         * Removes the "too expensive" feature from anvils, and also lowers the maximum cost for block use.
         */
        public boolean betterAnvil = true;

        /**
         * Sets the maximum cost that is allowed when using an anvil.
         * <p>If the cost goes above this value, the cost will instead be this value.</p>
         */
        public int anvilCostLimit = 10;

        /**
         * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
         */
        public boolean higherEnchantmentLevels = true;

        /**
         * Allows the player to right-click on an ore block and remove the silk touch enchantment from their handheld item.
         */
        public boolean rightClickToRemoveSilkTouch = true;

        /**
         * Increases the rate at which mobs spawn from spawner blocks.
         */
        public boolean fasterSpawners = true;

        /**
         * This allows all world modifications to be applied, which includes making structures more common, modifying mob/creature spawn rates, doom mode features, and more.
         */
        public boolean customDataGeneration = true;

        /**
         * Enables leaderboard mode, and applies the leaderboard checks and settings to the mod.
         */
        @RequiresRestart @Deprecated
        public boolean leaderboardsMode = false;
    }

    /**
     * All {@code Client-side Speedrunner Mod options.}
     * <p>Not labeled as {@code environment-type client} due to certain things breaking on the server-side.</p>
     */
    public static class Client {

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
         * Applies certain tooltips to certain items.
         */
        public boolean itemTooltips = true;

        /**
         * Puts the creator name of a certain texture on the specified item.
         */
        public boolean textureTooltips = false;

        /**
         * Enable/disable the Speedrunner Mod's custom panorama.
         */
        @RequiresRestart
        public boolean customPanorama = true;

        /**
         * Determines whether certain player messages should be sent to the player's chat or actionbar (the area above the hotbar).
         */
        public ItemMessages itemMessages = ItemMessages.ACTIONBAR;

        /**
         * Enable/disable the confirmation messages when using certain items (ex. eye of annul, ender thruster, piglin awakener, etc.).
         */
        @RequiresRestart
        public boolean confirmMessages = false;

        /**
         * Create a new world with just one click.
         */
        public boolean fastWorldCreation = true;

        /**
         * Determines the gamemode they every new world should generate with.
         */
        public GameMode gameMode = GameMode.SURVIVAL;

        /**
         * Determines the difficulty that every new world should generate with.
         */
        public Difficulty difficulty = Difficulty.EASY;

        /**
         * Allows cheats when a new world is created.
         */
        public boolean allowCheats = false;

        /**
         * Sends the players coordinates to chat upon death, and displays them on the death screen.
         */
        public boolean showDeathCords = true;
    }

    /**
     * All {@code Advanced Speedrunner Mod options.}
     */
    public static class Advanced {

        /**
         * A list of all {@code mod IDS} loaded into Minecraft. Add another mod ID to this list if you are running additional mods with the speedrunner mod. This will allow certain commands to work properly. See {@link ItemStackArgumentTypeMixin}.
         * <p>Do NOT remove "minecraft" from this list, whatever you do.</p>
         */
        public String[] modIds = new String[]{"minecraft", MOD_ID};

        /**
         * Allows strongholds to generate differently, or smaller.
         */
        @RequiresRestart
        public boolean modifiedStrongholdGeneration = true;

        /**
         * Allows strongholds to generate at higher/lower Y-levels, depending on if doom mode is enabled or not.
         */
        @RequiresRestart
        public boolean modifiedStrongholdYGeneration = true;

        /**
         * Allows nether fortresses to generate differently, or smaller, along with more than two blaze spawners per fortress.
         */
        @RequiresRestart
        public boolean modifiedNetherFortressGeneration = true;

        /**
         * Display the reset button on the title screen, game menu screen and pause screen.
         */
        public boolean showResetButton = true;

        /**
         * Allows the player to breath for a longer period of time while underwater, and also allows the player to regain oxygen when coming out of water blocks.
         */
        public boolean higherBreathTime = true;

        /**
         * Allow all types/variants of speedrunner wood to generate across the world. This includes the different variants of speedrunner trees, dead speedrunner trees, and dead speedrunner bushes.
         */
        public boolean generateSpeedrunnerWood = true;

        /**
         * The weight for the Speedrunner's Wasteland biome (how commonly it can generate).
         */
        @RequiresRestart
        public int speedrunnersWastelandBiomeWeight = 9;

        /**
         * In vanilla Minecraft, the ender dragon will fly away after perching when it takes so much damage. However, this option extends that damage amount, to allow the dragon to stay perched for a longer period of time, even after taking a large amount of damage.
         */
        public boolean longerDragonPerchStayTime = true;

        /**
         * Piglins get scared of zombified piglin if they are within 6 blocks of the zombified piglin. This option decreases that distance to 2 blocks.
         */
        public boolean decreasedZombifiedPiglinScareDistance = true;

        /**
         * Determines how long it takes (in ticks) for an eye of ender to break after throwing it.
         */
        public int enderEyeBreakingCooldown = 60;

        /**
         * Determines the total amount of piglin that can teleport to the player per time using the piglin awakener item (Default = 10).
         */
        public int piglinAwakenerPiglinCount = 10;

        /**
         * Sets the inventory slot that the flight duration 3 firework rockets should be given to when iCarus Mode is enabled.
         */
        public int iCarusFireworksInventorySlot = 1;

        /**
         * Sets the inventory slot that the InfiniPearl item should be given to when InfiniPearl mode is enabled.
         * <p>This value is incremented by one if iCarus Mode is already enabled.</p>
         */
        public int infiniPearlInventorySlot = 1;

        /**
         * Determines the explosion power for fireballs when thrown with a fire charge.
         */
        public int fireballExplosionPower = 2;

        /**
         * Determines if -- while the player is looking at a block, if they need to be shifting to throw a fireball. Disable this if you want to do things like fireball jump.
         */
        public boolean shiftToThrowFireball = true;

        /**
         * The minimum brightness amount for the Speedrunner Mod.
         */
        public double minimumBrightness = 0.0D;

        /**
         * The maximum brightness amount for the Speedrunner Mod.
         */
        public double maximumBrightness = 12.0D;

        /**
         * Makes the ender dragon kill all nearby hostile entities upon dying, excluding enderman.
         */
        public boolean dragonKillsNearbyHostileEntities = true;

        /**
         * On doom mode, the ender dragon cannot be killed by any means if Goliath and the Wither are still alive in the end.
         */
        public boolean dragonImmunityFromGoliathAndWither = true;

        /**
         * When using the eye of annul stronghold portal room teleporter feature, it iterates through [-X, -Y, -Z, X, Y, Z] all blocks in this location to locate the portal room block. Negative values go below the player, positive values go above.
         */
        public int[] annulEyePortalRoomDistanceXYZ = createListOption(-128, -128, -128, 128, 128, 128);

        /**
         * When using the piglin awakener, the game will search around the player [X_Y_Z] blocks to find nearby piglin. The higher these numbers, the farther out the game looks. Increasing these numbers however is not recommended, as it could create extreme amounts of lag.
         */
        public double[] piglinAwakenerPiglinDistanceXYZ = createListOption(100.0D, 100.0D, 100.0D);

        /**
         * Determines the distance that the blaze spotter will use to determine the nearest blaze spawner.
         */
        public int[] blazeSpotterDistanceXYZ = createListOption(-156, -72, -156, 156, 72, 156);

        /**
         * When using the raid eradicator, the item will search a distance to search for the nearest raider entities.
         */
        public double[] raidEradicatorDistanceXYZ = createListOption(300.0D, 300.0D, 300.0D);

        /**
         * The dragon's pearl item will look in the radius of [X_Y_Z] for the nearest ender dragon, and choose that dragon to control perching.
         */
        public double[] dragonsPearlDragonDistanceXYZ = createListOption(150.0D, 150.0D, 150.0D);

        /**
         * Determines the entities in range that will be killed upon the ender dragon's death.
         * <p>This option is redundant if the option Dragon Kills Nearby Hostile Entities is OFF.</p>
         */
        public double[] dragonKillsHostileEntitiesDistance = createListOption(200.0D, 200.0D, 200.0D);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby Goliath. This option specifies the range that the Goliath has to be in from the dragon in order for it to be immune.
         */
        public double[] dragonImmunityDetectionDistanceForGoliath = createListOption(200.0D, 200.0D, 200.0D);

        /**
         * When on doom mode, the dragon cannot die if there is a nearby wither. This option specifies the range that the wither has to be in from the dragon in order for it to be immune.
         */
        public double[] dragonImmunityDetectionDistanceForWither = createListOption(300.0D, 300.0D, 300.0D);
    }

    /**
     * All booleans for doing certain things in the tutorial mode.
     */
    @ChatGPT(Credit.MOST_CREDIT)
    public static class TutorialMode implements net.dillon.speedrunnermod.util.TutorialMode {
        public boolean enterWorld = false;
        public boolean obtainedSpeedrunnerPickaxe = false;
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
     * All {@code Mixin} control options.
     */
    public static class Mixins {

        /**
         * Applies the terrablender surface rule data mixin into the game.
         * <p>Disable this if you do not want doom stone to generate throughout the end when doom mode is enabled, or if another mod is trying to generate other blocks.</p>
         */
        @RequiresRestart
        public boolean terraBlenderSurfaceRuleDataMixin = true;

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
     * {@code Structure Spawn Rates} config.
     * <p>These values are only applied if the {@code Structure Spawn Rates} option is set to {@code CUSTOM.}
     * <p>The {@code first integer} in the option list is the {@code spacing value.}
     * <p>The {@code second integer} in the option list is the {@code separation value.}
     * <p>The {@code separation value} should NEVER be greater than or equal to the spacing value. The game will crash if this happens.
     */
    public static class StructureSpawnRates {
        public int[] ancientCities = createStructureSpawnRateOption(16, 8);
        public int[] villages = createStructureSpawnRateOption(16, 8);
        public int[] desertPyramids = createStructureSpawnRateOption(10, 5);
        public int[] junglePyramids = createStructureSpawnRateOption(10, 5);
        public int[] pillagerOutposts = createStructureSpawnRateOption(10, 5);
        public int[] endCities = createStructureSpawnRateOption(7, 3);
        public int[] woodlandMansions = createStructureSpawnRateOption(25, 12);
        public int[] ruinedPortals = createStructureSpawnRateOption(9, 4);
        public int[] shipwrecks = createStructureSpawnRateOption(10, 5);
        public int[] trialChambers = createStructureSpawnRateOption(12, 6);
        public int[] netherComplexes = createStructureSpawnRateOption(8, 4);
    }

    /**
     * Creates an {@code integer list option,} with {@code positive} coordinate values.
     */
    public static double[] createListOption(double posX, double posY, double posZ) {
        return new double[]{posX, posY, posZ};
    }

    /**
     * Creates an {@code integer list option,} with {@code negative} and {@code positive} coordinate values.
     */
    public static int[] createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return new int[]{negX, negY, negZ, posX, posY, posZ};
    }

    /**
     * Creates a new {@code structure spawn rate option.}
     */
    public static int[] createStructureSpawnRateOption(int spacing, int separation) {
        return new int[]{spacing, separation};
    }

    /**
     * Returns true if the {@code Mob Spawning Rate} option is safe to run.
     */
    public boolean isMobSpawningRateSafe() {
        return options().main.mobSpawningRate.equals(MobSpawningRate.LOW) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.NORMAL) ||
                options().main.mobSpawningRate.equals(MobSpawningRate.HIGH);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is valid.
     */
    public boolean isDragonPerchTimeValid() {
        return this.inBounds(main.dragonPerchTime, 8, 90);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code on.}
     */
    public boolean isDragonPerchTimeOn() {
        return this.inBounds(main.dragonPerchTime, 10);
    }

    /**
     * Returns true if the {@code Dragon Perch Time} option is {@code "instant".}
     */
    public boolean isInstantDragonPerchTime() {
        return options().main.dragonPerchTime == 9;
    }

    /**
     * Returns the current {@code Dragon Perch Time} option in milliseconds.
     */
    public int getDragonPerchTime() {
        return ModUtil.millisecondsAsSeconds(options().main.dragonPerchTime);
    }

    /**
     * Returns true if the {@code Block Breaking Multiplier} option is valid.
     */
    public boolean isBlockBreakingMultiplierValid() {
        return this.inBounds(main.blockBreakingMultiplier, 1, 3);
    }

    /**
     * Returns true if the {@code Stronghold Distance} option is valid.
     */
    public boolean isStrongholdDistanceValid() {
        return this.inBounds(main.strongholdDistance, 3, 64);
    }

    /**
     * Returns true if the {@code Stronghold Spread} option is valid.
     */
    public boolean isStrongholdSpreadValid() {
        return this.inBounds(main.strongholdSpread, 2, 32);
    }

    /**
     * Returns true if the {@code Stronghold Count} option is valid.
     */
    public boolean isStrongholdCountValid() {
        return this.inBounds(main.strongholdCount, 4, 156);
    }

    /**
     * Returns true if the {@code Stronghold Portal Room Count} option is valid.
     */
    public boolean isStrongholdPortalRoomCountValid() {
        return this.inBounds(main.strongholdPortalRoomCount, 0, 3);
    }

    /**
     * Returns true if the {@code Stronghold Library Count} option is valid.
     */
    public boolean isStrongholdLibraryCountValid() {
        return this.inBounds(main.strongholdLibraryCount, 1, 10);
    }

    /**
     * Returns true if the {@code Anvil Cost Limit} option is valid.
     */
    public boolean isAnvilCostLimitValid() {
        return this.inBounds(main.anvilCostLimit, 1, 50);
    }

    /**
     * Returns true if the {@code Nether Portal Cooldown} option is valid.
     */
    public boolean isNetherPortalCooldownValid() {
        return this.inBounds(main.netherPortalDelay, 0, 20);
    }

    /**
     * Returns true if the {@code Speedrunner's Wasteland Biome Weight} option is valid.
     */
    public boolean isSpeedrunnersWastelandBiomeWeightValid() {
        return this.inBounds(advanced.speedrunnersWastelandBiomeWeight, 1, 32);
    }

    /**
     * Returns true if the {@code Eye of Ender Breaking Cooldown} advanced option is valid.
     */
    public boolean isEyeOfEnderBreakingCooldownValid() {
        return this.inBounds(advanced.enderEyeBreakingCooldown, 20, 200);
    }

    /**
     * Returns true if the {@code Icarus Fireworks Inventory Slot} advanced option is valid.
     */
    public boolean isIcarusFireworksInventorySlotValid() {
        return this.inBounds(advanced.iCarusFireworksInventorySlot, 1, 36);
    }

    /**
     * Returns true if the {@code InfiniPearl Inventory Slot} advanced option is valid.
     */
    public boolean isInfiniPearlInventorySlotValid() {
        return this.inBounds(advanced.infiniPearlInventorySlot, 1, 36);
    }

    /**
     * Determines if a certain option is {@code greater than} or {@code equal to} inputted parameters.
     */
    public boolean inBounds(int option, int min) {
        return option >= min;
    }

    /**
     * Determines if a certain option is {@code less than} and {@code greater than} equal to said parameters.
     */
    public boolean inBounds(int option, int min, int max) {
        return option >= min && option <= max;
    }

    public enum PlayingMode implements TranslatableOption {
        EASY(0, "speedrunnermod.options.playing_mode.easy"),
        BALANCED(1, "speedrunnermod.options.playing_mode.balanced"),
        DOOM(2, "speedrunnermod.options.playing_mode.doom");

        private static final PlayingMode[] VALUES = Arrays.stream(PlayingMode.values()).sorted(Comparator.comparingInt(PlayingMode::getId)).toArray(PlayingMode[]::new);
        private final int id;
        private final String translateKey;

        PlayingMode(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Playing Mode} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Playing Mode} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static PlayingMode byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }

        public boolean easy() {
            return options().main.playingMode.equals(PlayingMode.EASY);
        }

        public boolean balanced() {
            return options().main.playingMode.equals(PlayingMode.BALANCED);
        }

        public boolean doom() {
            return options().main.playingMode.equals(PlayingMode.DOOM);
        }

        /**
         * Returns true if the {@code Playing Mode} option is safe.
         * <p>Going into the configuration file and setting the option to an invalid string, will crash the game.</p>
         */
        public boolean isSafe() {
            return options().main.playingMode.equals(EASY) ||
                    options().main.playingMode.equals(BALANCED) ||
                    options().main.playingMode.equals(DOOM);
        }
    }

    /**
     * All the different {@code Structure Spawn Rate} options, from extremely common to extremely rare.
     */
    public enum StructureSpawnRate implements TranslatableOption {
        EVERYWHERE(0, "speedrunnermod.options.structure_spawn_rates.everywhere"),
        VERY_COMMON(1, "speedrunnermod.options.structure_spawn_rates.very_common"),
        COMMON(2, "speedrunnermod.options.structure_spawn_rates.common"),
        NORMAL(3, "speedrunnermod.options.structure_spawn_rates.normal"),
        DEFAULT(4, "speedrunnermod.options.structure_spawn_rates.default"),
        RARE(5, "speedrunnermod.options.structure_spawn_rates.rare"),
        VERY_RARE(6, "speedrunnermod.options.structure_spawn_rates.very_rare"),
        CUSTOM(7, "speedrunnermod.options.structure_spawn_rates.custom"),
        DISABLED(8, "speedrunnermod.options.structure_spawn_rates.disabled");

        private static final StructureSpawnRate[] VALUES = Arrays.stream(StructureSpawnRate.values()).sorted(Comparator.comparingInt(StructureSpawnRate::getId)).toArray(StructureSpawnRate[]::new);
        private final int id;
        private final String translateKey;

        StructureSpawnRate(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Structure Spawn Rate} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Structure Spawn Rate} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static StructureSpawnRate byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
        }

        /**
         * Returns true if the {@code Structure Spawn Rate} option is safe.
         * <p>Going into the configuration file and setting the option to an invalid string, will crash the game.</p>
         */
        public boolean isSafe() {
            return options().main.structureSpawnRates.equals(EVERYWHERE) ||
                    options().main.structureSpawnRates.equals(VERY_COMMON) ||
                    options().main.structureSpawnRates.equals(COMMON) ||
                    options().main.structureSpawnRates.equals(NORMAL) ||
                    options().main.structureSpawnRates.equals(DEFAULT) ||
                    options().main.structureSpawnRates.equals(RARE) ||
                    options().main.structureSpawnRates.equals(VERY_RARE) ||
                    options().main.structureSpawnRates.equals(CUSTOM) ||
                    options().main.structureSpawnRates.equals(DISABLED);
        }

        /**
         * Returns the {@code Everywhere} structure spawn rate option.
         */
        public boolean everywhere() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.EVERYWHERE);
        }

        /**
         * Returns the {@code Very Common} structure spawn rate option.
         */
        public boolean veryCommon() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_COMMON);
        }

        /**
         * Returns the {@code Very Common} or {@code Common} structure spawn rate option.
         */
        public boolean veryCommonOrCommon() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_COMMON) || options().main.structureSpawnRates.equals(COMMON);
        }

        /**
         * Returns the {@code Common} structure spawn rate option.
         */
        public boolean common() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.COMMON);
        }

        /**
         * Returns the {@code Normal} structure spawn rate option.
         */
        public boolean normal() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.NORMAL);
        }

        /**
         * Returns the {@code Default} structure spawn rate option,
         */
        public boolean ddefault() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.DEFAULT);
        }

        /**
         * Returns the {@code Normal} or {@code Default} structure spawn rate option.
         */
        public boolean commonNormalOrDefault() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.COMMON) || options().main.structureSpawnRates.equals(StructureSpawnRate.NORMAL) || options().main.structureSpawnRates.equals(StructureSpawnRate.DEFAULT);
        }

        /**
         * Returns the {@code Rare} structure spawn rate option.
         */
        public boolean rare() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.RARE);
        }

        /**
         * Returns the {@code Very Rare} structure spawn rate option.
         */
        public boolean veryRare() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.VERY_RARE);
        }

        /**
         * Returns the {@code Custom} structure spawn rate option.
         */
        public boolean custom() {
            return options().main.structureSpawnRates.equals(StructureSpawnRate.CUSTOM);
        }
    }

    /**
     * All the different {@code Mob Spawning Rate} options.
     */
    public enum MobSpawningRate implements TranslatableOption {
        LOW(0, "speedrunnermod.options.mob_spawning_rate.low"),
        NORMAL(1, "speedrunnermod.options.mob_spawning_rate.normal"),
        HIGH(2, "speedrunnermod.options.mob_spawning_rate.high");

        private static final MobSpawningRate[] VALUES = Arrays.stream(MobSpawningRate.values()).sorted(Comparator.comparingInt(MobSpawningRate::getId)).toArray(MobSpawningRate[]::new);
        private final int id;
        private final String translateKey;

        MobSpawningRate(int id, String translationKey) {
            this.id = id;
            this.translateKey = Objects.requireNonNull(translationKey, "translateKey");
        }

        /**
         * Returns the {@code id value} of the {@code Mob Spawning Rate} option.
         */
        @Override
        public int getId() {
            return this.id;
        }

        /**
         * Returns the {@code translation key} of the {@code Mob Spawning Rate} option.
         */
        @Override
        public String getTranslationKey() {
            return this.translateKey;
        }

        /**
         * Not sure what this does to be honest, but it's used in ModListOptions.
         */
        public static MobSpawningRate byId(int id) {
            return VALUES[MathHelper.floorMod(id, VALUES.length)];
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

        /**
         * Returns true if the {@code GameMode} option is safe to run.
         */
        public boolean isSafe() {
            return options().client.gameMode.equals(SURVIVAL) ||
                    options().client.gameMode.equals(CREATIVE) ||
                    options().client.gameMode.equals(HARDCORE) ||
                    options().client.gameMode.equals(SPECTATOR);
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

        /**
         * Returns true if the {@code Difficulty} option is safe to run.
         */
        public boolean isSafe() {
            return options().client.difficulty.equals(PEACEFUL) ||
                    options().client.difficulty.equals(EASY) ||
                    options().client.difficulty.equals(NORMAL) ||
                    options().client.difficulty.equals(HARD);
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
         * Returns true if the {@code Item Messages} option is safe to run.
         */
        public boolean isSafe() {
            return options().client.itemMessages.equals(ACTIONBAR) ||
                    options().client.itemMessages.equals(CHAT);
        }

        /**
         * Returns true if the {@code Item Messages} option is set to actionbar.
         */
        public boolean isActionbar() {
            return options().client.itemMessages.equals(ACTIONBAR);
        }
    }

    /**
     * Loads the configuration file.
     */
    public static void loadConfig() {
        File configFile = getConfigFile();

        if (!configFile.exists()) {
            OPTIONS = new ModOptions();
            info("Creating speedrunner mod options file...");
        } else {
            info("Reading speedrunner mod options...");
            safeCheck();
            readConfig();
        }
        saveConfig();
    }

    /**
     * Reads the configuration file.
     */
    public static void readConfig() {
        OPTIONS = getConfig();
    }

    /**
     * Saves the configuration file.
     */
    public static void saveConfig() {
        File file = getConfigFile();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(SpeedrunnerMod.options()));
            SpeedrunnerMod.info("Flushed changes.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the {@code OPTIONS} variable to the config.
     */
    public static void setConfig(ModOptions config) {
        OPTIONS = config;
        saveConfig();
    }

    /**
     * Gets all the Speedrunner Mod configuration options and returns them.
     */
    public static ModOptions getConfig() {
        File file = getConfigFile();
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, ModOptions.class);
        } catch (Exception e) {
            ModOptions newconfig = new ModOptions();
            setConfig(newconfig);
            return newconfig;
        }
    }

    /**
     * Returns the Speedrunner Mod configuration file.
     */
    public static File getConfigFile() {
        if (file == null) {
            file = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG);
        }
        return file;
    }

    /**
     * The game is safe to run if the {@code safe} parameter returns true.
     */
    protected static void isSafe(boolean safe) {
        SpeedrunnerMod.safeBoot = !safe;
    }

    /**
     * Preforms a {@code "safe check"} on all the Speedrunner Mod options, and makes sure that they are valid and safe to run in-game.
     * <p>If an option is broken or invalid, and it is not recommended to run, the user will automatically boot into the Safe boot screen.</p>
     */
    private static void safeCheck() {
        final String space = " ";
        final String pertaining = "Pertaining to: ";
        final String related = space + pertaining;

        if (options().main.leaderboardsMode) {
            error("Leaderboards mode is ON, please disable, as the leaderboards have been deleted.");
            isSafe(false);
            BrokenModOptions.leaderboards = true;
        }

        if (!options().main.playingMode.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.playingMode");
            isSafe(false);
            BrokenModOptions.playingMode = true;
        }

        if (!options().main.structureSpawnRates.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.structureSpawnRates");
            isSafe(false);
            BrokenModOptions.structureSpawnRates = true;
        }

        if (!options().isMobSpawningRateSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.mobSpawningRate");
            isSafe(false);
            BrokenModOptions.mobSpawningRate = true;
        }

        if (!options().client.itemMessages.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.itemMessages");
            isSafe(false);
            BrokenModOptions.itemMessages = true;
        }

        if (!options().client.gameMode.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.gameMode");
            isSafe(false);
            BrokenModOptions.gameMode = true;
        }

        if (!options().client.difficulty.isSafe()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.difficulty");
            isSafe(false);
            BrokenModOptions.difficulty = true;
        }

        if (options().main.netherPortalDelay < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
            isSafe(false);
            BrokenModOptions.netherPortalCooldown = true;
        } else if (!options().isNetherPortalCooldownValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.netherPortalCooldown");
        }

        if (options().main.strongholdPortalRoomCount < 1) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
            isSafe(false);
            BrokenModOptions.strongholdPortalRoomCount = true;
        } else if (!options().isStrongholdPortalRoomCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdPortalRoomCount");
        }

        if (options().main.blockBreakingMultiplier < 0) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
            isSafe(false);
            BrokenModOptions.blockBreakingMultiplier = true;
            warn("Cannot divide by zero! o_0");
        } else if (!options().isBlockBreakingMultiplierValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.blockBreakingMultiplier");
        }

        if (options().advanced.speedrunnersWastelandBiomeWeight < 1) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
            isSafe(false);
            BrokenModOptions.speedrunnersWastelandBiomeWeight = true;
            warn("Speedrunner's Wasteland Biome Weight is below 1. Instead, turn \"Custom Biomes and Custom Biome Features\" OFF.");
        } else if (!options().isSpeedrunnersWastelandBiomeWeightValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.speedrunnersWastelandBiomeWeight");
            warn("The weight for the Speedrunner's Wasteland biome is either too high or too low. Proceed with caution.");
        }

        if (!options().isIcarusFireworksInventorySlotValid()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.icarusFireworksInventorySlot");
            isSafe(false);
            BrokenModOptions.iCarusFireworksInventorySlot = true;
        }

        if (!options().isInfiniPearlInventorySlotValid()) {
            error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.infiniPearlInventorySlot");
            isSafe(false);
            BrokenModOptions.infiniPearlInventorySlot = true;
        }

        if (!options().isStrongholdLibraryCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdLibraryCount");
        }

        if (!options().isStrongholdDistanceValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdDistance");
        }

        if (!options().isStrongholdSpreadValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdSpread");
        }

        if (!options().isStrongholdCountValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.strongholdCount");
        }

        if (!options().isDragonPerchTimeValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.dragonPerchTime");
        }

        if (!options().isAnvilCostLimitValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.anvilCostLimit");
        }

        if (!options().isEyeOfEnderBreakingCooldownValid()) {
            warn(OPTIONS_WARNING_MESSAGE + related + "speedrunnermod.options.eyeOfEnderBreakingCooldown");
        }
    }

    /**
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    @Environment(EnvType.CLIENT)
    public static void resetAllOptions() {
        options().main.tutorialMode = false;
        options().main.playingMode = ModOptions.PlayingMode.EASY;
        options().main.structureSpawnRates = ModOptions.StructureSpawnRate.COMMON;
        options().main.fasterBlockBreaking = true;
        options().main.blockBreakingMultiplier = 1;
        options().main.betterBiomes = false;
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

        options().client.firstTimePlaying = false;
        options().client.fog = true;
        options().client.itemTooltips = true;
        options().client.customPanorama = true;
        options().client.itemMessages = ModOptions.ItemMessages.ACTIONBAR;
        options().client.confirmMessages = false;
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
        options().mixins.backgroundRendererMixin = true;
        options().mixins.simpleOptionMixin = true;
        options().mixins.logoDrawerMixin = true;
        options().mixins.renderLayersMixin = true;

        resetAllTutorialModeOptions();
    }

    public static void resetAllTutorialModeOptions() {
        options().tutorialMode.enterWorld = false;
        options().tutorialMode.obtainedSpeedrunnerPickaxe = false;
        options().tutorialMode.obtainedSpeedrunnerBoat = false;
        options().tutorialMode.obtainedSpeedrunnerArmorSet = false;
        options().tutorialMode.obtainedSpeedrunnerShield = false;
        options().tutorialMode.obtainedInfernoEye = false;
        options().tutorialMode.usedInfernoEye = false;
        options().tutorialMode.obtainedPiglinAwakener = false;
        options().tutorialMode.usedPiglinAwakener = false;
        options().tutorialMode.obtainedBlazeSpotter = false;
        options().tutorialMode.usedBlazeSpotter = false;
        options().tutorialMode.obtainedSpeedrunnersEye = false;
        options().tutorialMode.changedSpeedrunnersEyeLocator = false;
        options().tutorialMode.usedSpeedrunnersEye = false;
        options().tutorialMode.obtainedEnderEye = false;
        options().tutorialMode.usedEnderEye = false;
        options().tutorialMode.obtainedDragonsPearl = false;
        options().tutorialMode.obtainedAnnulEye = false;
        options().tutorialMode.usedAnnulEye = false;
        options().tutorialMode.enteredEnd = false;
        options().tutorialMode.obtainedTotem = false;
        options().tutorialMode.freeFalledIntoVoid = false;
        options().tutorialMode.obtainedSpeedrunnersTotem = false;
        options().tutorialMode.killedGoliath = false;
        options().tutorialMode.killedWither = false;
        options().tutorialMode.usedDragonsPearl = false;
        options().tutorialMode.killedDragon = false;
        options().tutorialMode.brokenExperienceOre = false;
        options().tutorialMode.obtainedSpeedrunnersWorkbench = false;
        options().tutorialMode.transferedEnchantments = false;
        options().tutorialMode.interactedWithRetiredSpeedrunner = false;
        options().tutorialMode.obtainedEnderThruster = false;
        options().tutorialMode.usedEnderThruster = false;
        options().tutorialMode.obtainedDragonsSword = false;
        options().tutorialMode.obtainedWitherBone = false;
        options().tutorialMode.obtainedWitherSword = false;
        options().tutorialMode.obtainedEnderMatter = false;
        options().tutorialMode.obtainedInfiniPearl = false;
    }
}