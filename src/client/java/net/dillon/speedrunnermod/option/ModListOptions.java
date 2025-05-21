package net.dillon.speedrunnermod.option;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.client.util.TranslationStringKeys;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Arrays;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
@Environment(EnvType.CLIENT)
public class ModListOptions {

    public static final SimpleOption<ModOptions.PlayingMode> PLAYING_MODE =
            new SimpleOption<>("speedrunnermod.options.playing_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.playing_mode.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.PlayingMode.values()), Codec.INT.xmap(ModOptions.PlayingMode::byId, ModOptions.PlayingMode::getId)),
                    options().main.playingMode, value -> options().main.playingMode = value);

    public static final SimpleOption<ModOptions.StructureSpawnRate> STRUCTURE_SPAWN_RATE =
            new SimpleOption<>("speedrunnermod.options.structure_spawn_rates", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.StructureSpawnRate.values()), Codec.INT.xmap(ModOptions.StructureSpawnRate::byId, ModOptions.StructureSpawnRate::getId)),
                    options().main.structureSpawnRates, value -> options().main.structureSpawnRates = value);

    public static final SimpleOption<ModOptions.ItemMessages> ITEM_MESSAGES =
            new SimpleOption<>("speedrunnermod.options.item_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_messages.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.ItemMessages.values()), Codec.INT.xmap(ModOptions.ItemMessages::byId, ModOptions.ItemMessages::getId)),
                    options().client.itemMessages, value -> options().client.itemMessages = value);

    public static final SimpleOption<ModOptions.MobSpawningRate> MOB_SPAWNING_RATE =
            new SimpleOption<>("speedrunnermod.options.mob_spawning_rate", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.mob_spawning_rate.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.MobSpawningRate.values()), Codec.INT.xmap(ModOptions.MobSpawningRate::byId, ModOptions.MobSpawningRate::getId)),
                    options().main.mobSpawningRate, value -> options().main.mobSpawningRate = value);

    public static final SimpleOption<ModOptions.GameMode> GAMEMODE =
            new SimpleOption<>("speedrunnermod.options.gamemode", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.GameMode.values()), Codec.INT.xmap(ModOptions.GameMode::byId, ModOptions.GameMode::getId)),
                    options().client.gameMode, value -> options().client.gameMode = value);

    public static final SimpleOption<ModOptions.Difficulty> DIFFICULTY =
            new SimpleOption<>("speedrunnermod.options.difficulty", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.Difficulty.values()), Codec.INT.xmap(ModOptions.Difficulty::byId, ModOptions.Difficulty::getId)),
                    ModOptions.Difficulty.EASY, value -> options().client.difficulty = value);

    public static final SimpleOption<Boolean> TUTORIAL_MODE = new SimpleOption<>("speedrunnermod.options.tutorial_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.tutorial_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.tutorialMode, value -> options().main.tutorialMode = value);

    public static final SimpleOption<Boolean> FASTER_BLOCK_BREAKING = new SimpleOption<>("speedrunnermod.options.faster_block_breaking", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterBlockBreaking, value -> options().main.fasterBlockBreaking = value);

    public static final SimpleOption<Boolean> ICARUS_MODE = new SimpleOption<>("speedrunnermod.options.icarus_mode", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.iCarusMode, value -> options().main.iCarusMode = value);

    public static final SimpleOption<Boolean> FOG = new SimpleOption<>("speedrunnermod.options.fog", SimpleOption.emptyTooltip(),
            (optionText, value) -> !options().mixins.backgroundRendererMixin ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fog, value -> {
        options().client.fog = value;
        MinecraftClient.getInstance().worldRenderer.reload();
    });

    public static final SimpleOption<Boolean> INFINI_PEARL_MODE = new SimpleOption<>("speedrunnermod.options.infini_pearl_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.infiniPearlMode, value -> options().main.infiniPearlMode = value);

    @Deprecated
    public static final SimpleOption<Boolean> LEADERBOARDS_MODE = new SimpleOption<>("speedrunnermod.options.leaderboards_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.leaderboardsMode, value -> options().main.leaderboardsMode = value);

    public static final SimpleOption<Boolean> ITEM_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.item_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.itemTooltips, value -> options().client.itemTooltips = value);

    public static final SimpleOption<Boolean> TEXTURE_TOOLTIPS = new SimpleOption<>("speedrunnermod.options.texture_tooltips", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.texture_tooltips.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.textureTooltips, value -> options().client.textureTooltips = value);

    public static final SimpleOption<Boolean> KILL_GHAST_ON_FIREBALL = new SimpleOption<>("speedrunnermod.options.kill_ghast_on_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.killGhastOnFireball, value -> options().main.killGhastOnFireball = value);

    public static final SimpleOption<Boolean> BETTER_VILLAGER_TRADES = new SimpleOption<>("speedrunnermod.options.better_villager_trades", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_villager_trades.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterVillagerTrades, value -> options().main.betterVillagerTrades = value);

    public static final SimpleOption<Boolean> FIREPROOF_ITEMS = new SimpleOption<>("speedrunnermod.options.fireproof_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireproof_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fireproofItems, value -> options().main.fireproofItems = value);

    public static final SimpleOption<Boolean> FASTER_SPAWNERS = new SimpleOption<>("speedrunnermod.options.faster_spawners", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_spawners.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterSpawners, value -> options().main.fasterSpawners = value);

    public static final SimpleOption<Boolean> CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES = new SimpleOption<>("speedrunnermod.options.custom_biomes_and_custom_biome_features", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customBiomesAndCustomBiomeFeatures, value -> options().main.customBiomesAndCustomBiomeFeatures = value);

    public static final SimpleOption<Boolean> COMMON_ORES = new SimpleOption<>("speedrunnermod.options.common_ores", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.common_ores.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.commonOres, value -> options().main.commonOres = value);

    public static final SimpleOption<Boolean> LAVA_BOATS = new SimpleOption<>("speedrunnermod.options.lava_boats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.lava_boats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.lavaBoats, value -> options().main.lavaBoats = value);

    public static final SimpleOption<Boolean> NETHER_WATER = new SimpleOption<>("speedrunnermod.options.nether_water", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_water.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.netherWater, value -> options().main.netherWater = value);

    public static final SimpleOption<Boolean> BETTER_FOODS = new SimpleOption<>("speedrunnermod.options.better_foods", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_foods.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods, value -> options().main.betterFoods = value);

    public static final SimpleOption<Boolean> FALL_DAMAGE = new SimpleOption<>("speedrunnermod.options.fall_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fall_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fallDamage, value -> options().main.fallDamage = value);

    public static final SimpleOption<Boolean> ARROWS_DESTROY_BEDS = new SimpleOption<>("speedrunnermod.options.arrows_destroy_beds", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.arrowsDestroyBeds, value -> options().main.arrowsDestroyBeds = value);

    public static final SimpleOption<Boolean> GLOBAL_NETHER_PORTALS = new SimpleOption<>("speedrunnermod.options.global_nether_portals", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.globalNetherPortals, value -> options().main.globalNetherPortals = value);

    public static final SimpleOption<Boolean> BETTER_ANVIL = new SimpleOption<>("speedrunnermod.options.better_anvil", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_anvil.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterAnvil, value -> options().main.betterAnvil = value);

    public static final SimpleOption<Boolean> HIGHER_ENCHANTMENT_LEVELS = new SimpleOption<>("speedrunnermod.options.higher_enchantment_levels", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.higherEnchantmentLevels, value -> options().main.higherEnchantmentLevels = value);

    public static final SimpleOption<Boolean> RIGHT_CLICK_TO_REMOVE_SILK_TOUCH = new SimpleOption<>("speedrunnermod.options.right_click_to_remove_silk_touch", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.rightClickToRemoveSilkTouch, value -> options().main.rightClickToRemoveSilkTouch = value);

    public static final SimpleOption<Boolean> CONFIRM_MESSAGES = new SimpleOption<>("speedrunnermod.options.confirm_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.confirm_messages.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.confirmationMessages, value -> options().client.confirmationMessages = value);

    public static final SimpleOption<Boolean> SHOW_DEATH_CORDS = new SimpleOption<>("speedrunnermod.options.show_death_cords", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_death_cords.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.showDeathCords, value -> options().client.showDeathCords = value);

    public static final SimpleOption<Boolean> KINETIC_DAMAGE = new SimpleOption<>("speedrunnermod.options.kinetic_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.kineticDamage, value -> options().main.kineticDamage = value);

    public static final SimpleOption<Boolean> THROWABLE_FIREBALLS = new SimpleOption<>("speedrunnermod.options.throwable_fireballs", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.throwableFireballs, value -> options().main.throwableFireballs = value);

    public static final SimpleOption<Boolean> CUSTOM_DATA_GENERATION = new SimpleOption<>("speedrunnermod.options.custom_data_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customDataGeneration, value -> options().main.customDataGeneration = value);

    public static final SimpleOption<Boolean> FAST_WORLD_CREATION = new SimpleOption<>("speedrunnermod.options.fast_world_creation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.fastWorldCreation, value -> options().client.fastWorldCreation = value);

    public static final SimpleOption<Boolean> ALLOW_CHEATS = new SimpleOption<>("speedrunnermod.options.allow_cheats", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.allowCheats, value -> options().client.allowCheats = value);

    public static final SimpleOption<Boolean> PANORAMA = new SimpleOption<>("speedrunnermod.options.custom_panorama", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_panorama.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().client.customPanorama, value -> options().client.customPanorama = value);

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdGeneration, value -> options().advanced.modifiedStrongholdGeneration = value);

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_Y_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_y_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdYGeneration, value -> options().advanced.modifiedStrongholdYGeneration = value);

    public static final SimpleOption<Boolean> MODIFIED_NETHER_FORTRESS_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_nether_fortress_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedNetherFortressGeneration, value -> options().advanced.modifiedNetherFortressGeneration = value);

    public static final SimpleOption<Boolean> SHOW_RESET_BUTTON = new SimpleOption<>("speedrunnermod.options.show_reset_button", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_reset_button.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.showResetButton, value -> options().advanced.showResetButton = value);

    public static final SimpleOption<Boolean> HIGHER_BREATH_TIME = new SimpleOption<>("speedrunnermod.options.higher_breath_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_breath_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.higherBreathTime, value -> options().advanced.higherBreathTime = value);

    public static final SimpleOption<Boolean> GENERATE_SPEEDRUNNER_WOOD = new SimpleOption<>("speedrunnermod.options.generate_speedrunner_wood", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.generateSpeedrunnerWood, value -> options().advanced.generateSpeedrunnerWood = value);

    public static final SimpleOption<Boolean> LONGER_DRAGON_PERCH_STAY_TIME = new SimpleOption<>("speedrunnermod.options.longer_dragon_perch_stay_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.NO : ModTexts.YES, SimpleOption.BOOLEAN, options().advanced.longerDragonPerchStayTime, value -> options().advanced.longerDragonPerchStayTime = value);

    public static final SimpleOption<Boolean> DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE = new SimpleOption<>("speedrunnermod.options.decreased_zombified_piglin_scare_distance", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.decreasedZombifiedPiglinScareDistance, value -> options().advanced.decreasedZombifiedPiglinScareDistance = value);

    public static final SimpleOption<Boolean> DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES = new SimpleOption<>("speedrunnermod.options.dragon_kills_nearby_hostile_entities", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.tooltip")),
            (optionText, value) -> !value ? ModTexts.NO : ModTexts.YES, SimpleOption.BOOLEAN, options().advanced.dragonKillsNearbyHostileEntities, value -> options().advanced.dragonKillsNearbyHostileEntities = value);

    public static final SimpleOption<Boolean> DRAGON_IMMUNITY_FROM_GOLIATH_AND_WITHER = new SimpleOption<>("speedrunnermod.options.dragon_immunity_from_goliath_and_wither", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.dragonImmunityFromGoliathAndWither, value -> options().advanced.dragonImmunityFromGoliathAndWither = value);

    public static final SimpleOption<Boolean> SHIFT_TO_THROW_FIREBALL = new SimpleOption<>("speedrunnermod.options.shift_to_throw_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.shift_to_throw_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.shiftToThrowFireball, value -> options().advanced.shiftToThrowFireball = value);

    public static final SimpleOption<Boolean> TERRABLENDER_SURFACE_RULE_DATA_MIXIN = new SimpleOption<>("speedrunnermod.options.terrablender_surface_rule_data_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.terrablender_surface_rule_data_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.terraBlenderSurfaceRuleDataMixin, value -> options().mixins.terraBlenderSurfaceRuleDataMixin = value);

    public static final SimpleOption<Boolean> BACKGROUND_RENDERER_MIXIN = new SimpleOption<>("speedrunnermod.options.background_renderer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.background_renderer_mixin.tooltip")),
                (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.backgroundRendererMixin, value -> options().mixins.backgroundRendererMixin = value);

    public static final SimpleOption<Boolean> SIMPLE_OPTION_MIXIN = new SimpleOption<>("speedrunnermod.options.simple_option_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.simple_option_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.simpleOptionMixin, value -> options().mixins.simpleOptionMixin = value);

    public static final SimpleOption<Boolean> LOGO_DRAWER_MIXIN = new SimpleOption<>("speedrunnermod.options.logo_drawer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.logo_drawer_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.logoDrawerMixin, value -> options().mixins.logoDrawerMixin = value);

    public static final SimpleOption<Boolean> RENDER_LAYERS_MIXIN = new SimpleOption<>("speedrunnermod.options.render_layers_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.render_layers_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.renderLayersMixin, value -> options().mixins.renderLayersMixin = value);

    public static final SimpleOption<Integer> BLOCK_BREAKING_MULTIPLIER =
            new SimpleOption<>("speedrunnermod.options.block_breaking_multiplier", SimpleOption.emptyTooltip(),
                    (optionText, value) -> {
                        if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal("x" + value).formatted(Formatting.AQUA));
                        }
                    },
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.blockBreakingMultiplier, value -> options().main.blockBreakingMultiplier = value);

    public static final SimpleOption<Integer> DRAGON_PERCH_TIME =
            new SimpleOption<>("speedrunnermod.options.dragon_perch_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_perch_time.tooltip")),
                    (optionText, value) -> {
                        if (value == 9) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("Instant").formatted(Formatting.AQUA));
                        } else if (value <= 8) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(8, 90), options().main.dragonPerchTime, value -> options().main.dragonPerchTime = value);

    public static final SimpleOption<Integer> STRONGHOLD_DISTANCE =
            new SimpleOption<>("speedrunnermod.options.stronghold_distance", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 64), options().main.strongholdDistance, value -> options().main.strongholdDistance = value);

    public static final SimpleOption<Integer> STRONGHOLD_SPREAD =
            new SimpleOption<>("speedrunnermod.options.stronghold_spread", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().main.strongholdSpread, value -> options().main.strongholdSpread = value);

    public static final SimpleOption<Integer> STRONGHOLD_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(4, 156), options().main.strongholdCount, value -> options().main.strongholdCount = value);

    public static final SimpleOption<Integer> STRONGHOLD_PORTAL_ROOM_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_portal_room_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.strongholdPortalRoomCount, value -> options().main.strongholdPortalRoomCount = value);

    public static final SimpleOption<Integer> STRONGHOLD_LIBRARY_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_library_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 8), options().main.strongholdLibraryCount, value -> options().main.strongholdLibraryCount = value);

    public static final SimpleOption<Integer> NETHER_PORTAL_DELAY =
            new SimpleOption<>("speedrunnermod.options.nether_portal_delay", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_portal_delay.tooltip")),
                    (optionText, value) -> {
                        if (value == -1) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("Go by Gamerule").formatted(Formatting.GREEN));
                        } else if (value == 0) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("None").formatted(Formatting.RED));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + "s").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(-1, 20), options().main.netherPortalDelay, value -> options().main.netherPortalDelay = value);

    public static final SimpleOption<Integer> ANVIL_COST_LIMIT =
            new SimpleOption<>("speedrunnermod.options.anvil_cost_limit", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.anvil_cost_limit.tooltip")),
                    (optionText, value) -> {
                        if (value == 50) {
                            return GameOptions.getGenericValueText(optionText, Text.literal("No Limit").formatted(Formatting.RED));
                        } else if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " level").formatted(Formatting.AQUA));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(value + " levels").formatted(Formatting.AQUA));
                        }},
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 50), options().main.anvilCostLimit, value -> options().main.anvilCostLimit = value);

    public static final SimpleOption<Integer> SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT =
            new SimpleOption<>("speedrunnermod.options.speedrunners_wasteland_biome_weight", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().advanced.speedrunnersWastelandBiomeWeight, value -> options().advanced.speedrunnersWastelandBiomeWeight = value);

    public static final SimpleOption<Integer> ENDER_EYE_BREAKING_COOLDOWN =
            new SimpleOption<>("speedrunnermod.options.ender_eye_breaking_cooldown", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.tooltip")),
                    (optionText, value) -> GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds")),
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.enderEyeBreakingCooldown / 20, value -> options().advanced.enderEyeBreakingCooldown = value * 20);

    public static final SimpleOption<Integer> PIGLIN_AWAKENER_PIGLIN_COUNT =
            new SimpleOption<>("speedrunnermod.options.piglin_awakener_piglin_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 25), options().advanced.piglinAwakenerPiglinCount, value -> options().advanced.piglinAwakenerPiglinCount = value);

    public static final SimpleOption<Integer> ICARUS_FIREWORKS_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.icarus_fireworks_inventory_slot", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.iCarusFireworksInventorySlot, value -> options().advanced.iCarusFireworksInventorySlot = value);

    public static final SimpleOption<Integer> INFINI_PEARL_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.infini_pearl_inventory_slot", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), options().advanced.infiniPearlInventorySlot, value -> options().advanced.infiniPearlInventorySlot = value);

    public static final SimpleOption<Integer> FIREBALL_EXPLOSION_POWER =
            new SimpleOption<>("speedrunnermod.options.fireball_explosion_power", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.fireballExplosionPower, value -> options().advanced.fireballExplosionPower = value);

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static SimpleOption<Integer> structureSpawnRate(String structure) {
        return new SimpleOption<>("speedrunnermod.options.structure_spawn_rates." + structure, options().main.structureSpawnRates.custom() ? SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip")) : SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.custom_required")),
                (optionText, value) -> ModListOptions.listIntegerText(optionText, structure),
                new SimpleOption.ValidatingIntSliderCallbacks(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * Sets the values for each respective structure setting.
     */
    private static void determineValue(String structure, int value) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> setValue(options().structureSpawnRates.ancientCities, value);
            case TranslationStringKeys.VILLAGE -> setValue(options().structureSpawnRates.villages, value);
            case TranslationStringKeys.DESERT_PYRAMID -> setValue(options().structureSpawnRates.desertPyramids, value);
            case TranslationStringKeys.JUNGLE_PYRAMID -> setValue(options().structureSpawnRates.junglePyramids, value);
            case TranslationStringKeys.PILLAGER_OUTPOST -> setValue(options().structureSpawnRates.pillagerOutposts, value);
            case TranslationStringKeys.END_CITY -> setValue(options().structureSpawnRates.endCities, value);
            case TranslationStringKeys.WOODLAND_MANSION -> setValue(options().structureSpawnRates.woodlandMansions, value);
            case TranslationStringKeys.RUINED_PORTAL -> setValue(options().structureSpawnRates.ruinedPortals, value);
            case TranslationStringKeys.SHIPWRECK -> setValue(options().structureSpawnRates.shipwrecks, value);
            case TranslationStringKeys.TRIAL_CHAMBER -> setValue(options().structureSpawnRates.trialChambers, value);
            case TranslationStringKeys.NETHER_COMPLEXES -> setValue(options().structureSpawnRates.netherComplexes, value);
        }
    }

    /**
     * Bounds the value of the {@link SimpleOption} to the {@code spacing value} of the structure, and then sets the separate value to that divided by 2.
     */
    private static void setValue(int[] option, int value) {
        option[0] = value;
        option[1] = option[0] / 2;
    }

    /**
     * Returns the text that should be displayed on the {@link SimpleOption}.
     */
    private static Text listIntegerText(Text prefix, String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ancientCities[0] + ", " + options().structureSpawnRates.ancientCities[1]));
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.desertPyramids[0] + ", " + options().structureSpawnRates.desertPyramids[1]));
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.junglePyramids[0] + ", " + options().structureSpawnRates.junglePyramids[1]));
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.pillagerOutposts[0] + ", " + options().structureSpawnRates.pillagerOutposts[1]));
            }
            case TranslationStringKeys.END_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.endCities[0] + ", " + options().structureSpawnRates.endCities[1]));
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.woodlandMansions[0] + ", " + options().structureSpawnRates.woodlandMansions[1]));
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ruinedPortals[0] + ", " + options().structureSpawnRates.ruinedPortals[1]));
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.shipwrecks[0] + ", " + options().structureSpawnRates.shipwrecks[1]));
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.trialChambers[0] + ", " + options().structureSpawnRates.trialChambers[1]));
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.netherComplexes[0] + ", " + options().structureSpawnRates.netherComplexes[1]));
            }
            default -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.villages[0] + ", " + options().structureSpawnRates.villages[1]));
            }
        }
    }

    /**
     * Returns the {@code default spacing value} that the respective {@link SimpleOption} should return when loading into the game.
     */
    private static int defaultStructureValue(String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return options().structureSpawnRates.ancientCities[0];
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return options().structureSpawnRates.desertPyramids[0];
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return options().structureSpawnRates.junglePyramids[0];
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return options().structureSpawnRates.pillagerOutposts[0];
            }
            case TranslationStringKeys.END_CITY -> {
                return options().structureSpawnRates.endCities[0];
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return options().structureSpawnRates.woodlandMansions[0];
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return options().structureSpawnRates.ruinedPortals[0];
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return options().structureSpawnRates.shipwrecks[0];
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return options().structureSpawnRates.trialChambers[0];
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return options().structureSpawnRates.netherComplexes[0];
            }
            default -> {
                return options().structureSpawnRates.villages[0];
            }
        }
    }

    /**
     * Returns the generic value text prefix, with aqua formatting.
     */
    private static Text getGenericValueText(Text prefix, int value) {
        return GameOptions.getGenericValueText(prefix, Text.literal(Integer.toString(value)).formatted(Formatting.AQUA));
    }
}