package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Helper methods for various different things (ex. items and math calculations)
 */
public class ModUtil {
    public static final String CONFIG_FILE_NAME = "speedrunnermod-config.json";
    public static final String CLIENT_CONFIG_FILE_NAME = "speedrunnermod-client_config.json";

    /**
     * Locates structures.
     */
    @Author(Authors.KWPUGH)
    public static void findStructureAndShoot(World world, PlayerEntity player, ItemStack itemstack, TagKey<Structure> type) {
        BlockPos playerpos = player.getBlockPos();
        ServerWorld serverWorld = (ServerWorld)world;
        BlockPos locpos = serverWorld.locateStructure(type, playerpos, 100, false);

        EyeOfEnderEntity finderentity = new EyeOfEnderEntity(world, player.getX(), player.getBodyY(0.5D), player.getZ());
        finderentity.setItem(itemstack);
        finderentity.initTargetPos(locpos);
        world.spawnEntity(finderentity);

        if (player instanceof ServerPlayerEntity) {
            Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)player, locpos);
        }

        world.syncWorldEvent(null, 1003, player.getBlockPos(), 0);
    }

    /**
     * Returns the player's death coordinates as a clickable text to teleport right to it.
     */
    public static Text deathCords(double x, double y, double z) {
        return Text.translatable("speedrunnermod.player_death_cords",
                        ModUtil.roundToNearestTenthsPlace(x),
                        ModUtil.roundToNearestTenthsPlace(y),
                        ModUtil.roundToNearestTenthsPlace(z))
                .setStyle(Style.EMPTY
                        .withHoverEvent(new HoverEvent.ShowText(Text.translatable("speedrunnermod.teleport_to_player_death_cords")))
                        .withClickEvent(new ClickEvent.SuggestCommand("/teleport @s " + x + " " + y + " " + z)));
    }

    /**
     * Returns an enchantment using the {@code Entity} class.
     */
    public static RegistryEntry<Enchantment> entityEnchantment(Entity entity, RegistryKey<Enchantment> enchantment) {
        Optional<RegistryEntry.Reference<Enchantment>> optional = entity.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
        return optional.get();
    }

    /**
     * Returns an enchantment using the {@code World} class.
     */
    public static RegistryEntry<Enchantment> worldEnchantment(World world, RegistryKey<Enchantment> enchantment) {
        Optional<RegistryEntry.Reference<Enchantment>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
        return optional.get();
    }

    /**
     */
    public static ItemStack unbreakableItem(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        return stack;
    }

    /**
     * Returns flight duration 3 firework rockets.
     */
    public static ItemStack flightDurationComponentItem(int count) {
        ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, count);
        fireworks.set(DataComponentTypes.FIREWORKS, new FireworksComponent(3, List.of()));
        return fireworks;
    }

    /**
     * See {@code ender eye items} for more.
     */
    @Author(Authors.KWPUGH)
    public static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;

        return MathHelper.sqrt((float) (i * i + j * j));
    }

    /**
     * Returns a specific type of formatting.
     */
    public static Formatting toFormatting(UUID uuid, Formatting actionbar, Formatting chat) {
        return ServerSyncedClientOptions.shouldShowInActionbar(uuid) ? actionbar : chat;
    }

    /**
     * Rounds the inputted number to the nearest one decimal place (or nearest tenths place)
     */
    public static double roundToNearestTenthsPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
    }

    /**
     * Returns a random float, with a minimum and maximum value.
     */
    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Converts seconds to milliseconds.
     */
    public static int millisecondsAsSeconds(int seconds) {
        return seconds * 1000;
    }

    /**
     * Converts seconds to ticks.
     */
    public static int secondsInTicks(int seconds) {
        try {
            int testSeconds = 0;
            while (testSeconds < 525600) {
                if (seconds == testSeconds) {
                    throw new NumberFormatException();
                }
                testSeconds += 60;
            }
            return seconds * 20;
        } catch (NumberFormatException o) {
            SpeedrunnerMod.error("Use method minutesInTicks(int) if you're inputting an exact minute.");
            o.printStackTrace();
            return minutesInTicks(seconds / 60);
        }
    }

    /**
     * Converts minutes to ticks.
     */
    public static int minutesInTicks(int minutes) {
        return (minutes * 60) * 20;
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
     * Resets all of the {@code speedrunner mod options} back to factory default.
     */
    @Environment(EnvType.CLIENT)
    public static void resetAllOptions() {
        options().main.tutorialMode = false;
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
     * Resets all tutorial mode options.
     */
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
        options().tutorialMode.obtainedWitherBone = false;
        options().tutorialMode.obtainedWitherSword = false;
        options().tutorialMode.obtainedEnderMatter = false;
        options().tutorialMode.obtainedDragonsSword = false;
        options().tutorialMode.obtainedInfiniPearl = false;
    }
}