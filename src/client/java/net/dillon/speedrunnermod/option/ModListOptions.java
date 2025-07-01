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
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isStructureSpawnRatesCustom;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
@Environment(EnvType.CLIENT)
public class ModListOptions {

    public static final SimpleOption<ModOptions.PlayingMode> PLAYING_MODE =
            new SimpleOption<>("speedrunnermod.options.playing_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.playing_mode.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.PlayingMode.values()), Codec.INT.xmap(ModOptions.PlayingMode::byId, ModOptions.PlayingMode::getId)),
                    options().main.playingMode.getCurrentValue(), value -> options().main.playingMode.set(value));

    public static final SimpleOption<ModOptions.StructureSpawnRate> STRUCTURE_SPAWN_RATE =
            new SimpleOption<>("speedrunnermod.options.structure_spawn_rates", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.StructureSpawnRate.values()), Codec.INT.xmap(ModOptions.StructureSpawnRate::byId, ModOptions.StructureSpawnRate::getId)),
                    options().main.structureSpawnRates.getCurrentValue(), value -> options().main.structureSpawnRates.set(value));

    public static final SimpleOption<ClientModOptions.ItemMessages> ITEM_MESSAGES =
            new SimpleOption<>("speedrunnermod.options.item_messages", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_messages.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.ItemMessages.values()), Codec.INT.xmap(ClientModOptions.ItemMessages::byId, ClientModOptions.ItemMessages::getId)),
                    clientOptions().client.itemMessages.getCurrentValue(), value -> clientOptions().client.itemMessages.set(value));

    public static final SimpleOption<ModOptions.MobSpawningRate> MOB_SPAWNING_RATE =
            new SimpleOption<>("speedrunnermod.options.mob_spawning_rate", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.mob_spawning_rate.tooltip")), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.MobSpawningRate.values()), Codec.INT.xmap(ModOptions.MobSpawningRate::byId, ModOptions.MobSpawningRate::getId)),
                    options().main.mobSpawningRate.getCurrentValue(), value -> options().main.mobSpawningRate.set(value));

    public static final SimpleOption<ClientModOptions.GameMode> GAMEMODE =
            new SimpleOption<>("speedrunnermod.options.gamemode", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.GameMode.values()), Codec.INT.xmap(ClientModOptions.GameMode::byId, ClientModOptions.GameMode::getId)),
                    clientOptions().client.gameMode.getCurrentValue(), value -> clientOptions().client.gameMode.set(value));

    public static final SimpleOption<ClientModOptions.Difficulty> DIFFICULTY =
            new SimpleOption<>("speedrunnermod.options.difficulty", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                    new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.Difficulty.values()), Codec.INT.xmap(ClientModOptions.Difficulty::byId, ClientModOptions.Difficulty::getId)),
                    clientOptions().client.difficulty.getCurrentValue(), value -> clientOptions().client.difficulty.set(value));

    public static final SimpleOption<Boolean> TUTORIAL_MODE = new SimpleOption<>("speedrunnermod.options.tutorial_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.tutorial_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, clientOptions().client.tutorialMode.getCurrentValue(), value -> clientOptions().client.tutorialMode.set(value));

    public static final SimpleOption<Boolean> FASTER_BLOCK_BREAKING = new SimpleOption<>("speedrunnermod.options.faster_block_breaking", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterBlockBreaking.getCurrentValue(), value -> options().main.fasterBlockBreaking.set(value));

    public static final SimpleOption<Boolean> ICARUS_MODE = new SimpleOption<>("speedrunnermod.options.icarus_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.iCarusMode.getCurrentValue(), value -> options().main.iCarusMode.set(value));

    public static final SimpleOption<Boolean> FOG = new SimpleOption<>("speedrunnermod.options.fog", SimpleOption.emptyTooltip(),
            (optionText, value) -> !clientOptions().mixins.backgroundRendererMixin.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, clientOptions().client.fog.getCurrentValue(), value -> {
        clientOptions().client.fog.set(value);
        MinecraftClient.getInstance().worldRenderer.reload();
    });

    public static final SimpleOption<Boolean> INFINI_PEARL_MODE = new SimpleOption<>("speedrunnermod.options.infini_pearl_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.infiniPearlMode.getCurrentValue(), value -> options().main.infiniPearlMode.set(value));

    @Deprecated
    public static final SimpleOption<Boolean> LEADERBOARDS_MODE = new SimpleOption<>("speedrunnermod.options.leaderboards_mode", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.leaderboardsMode.getCurrentValue(), value -> options().main.leaderboardsMode.set(value));

    public static final SimpleOption<Boolean> KILL_GHAST_ON_FIREBALL = new SimpleOption<>("speedrunnermod.options.kill_ghast_on_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.killGhastOnFireball.getCurrentValue(), value -> options().main.killGhastOnFireball.set(value));

    public static final SimpleOption<Boolean> BETTER_VILLAGER_TRADES = new SimpleOption<>("speedrunnermod.options.better_villager_trades", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_villager_trades.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterVillagerTrades.getCurrentValue(), value -> options().main.betterVillagerTrades.set(value));

    public static final SimpleOption<Boolean> FIREPROOF_ITEMS = new SimpleOption<>("speedrunnermod.options.fireproof_items", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireproof_items.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fireproofItems.getCurrentValue(), value -> options().main.fireproofItems.set(value));

    public static final SimpleOption<Boolean> FASTER_SPAWNERS = new SimpleOption<>("speedrunnermod.options.faster_spawners", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_spawners.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fasterSpawners.getCurrentValue(), value -> options().main.fasterSpawners.set(value));

    public static final SimpleOption<Boolean> CUSTOM_BIOMES_AND_CUSTOM_BIOME_FEATURES = new SimpleOption<>("speedrunnermod.options.custom_biomes_and_custom_biome_features", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_biomes_and_custom_biome_features.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue(), value -> options().main.customBiomesAndCustomBiomeFeatures.set(value));

    public static final SimpleOption<Boolean> COMMON_ORES = new SimpleOption<>("speedrunnermod.options.common_ores", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.common_ores.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.commonOres.getCurrentValue(), value -> options().main.commonOres.set(value));

    public static final SimpleOption<Boolean> LAVA_BOATS = new SimpleOption<>("speedrunnermod.options.lava_boats", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.lava_boats.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.lavaBoats.getCurrentValue(), value -> options().main.lavaBoats.set(value));

    public static final SimpleOption<Boolean> NETHER_WATER = new SimpleOption<>("speedrunnermod.options.nether_water", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_water.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.netherWater.getCurrentValue(), value -> options().main.netherWater.set(value));

    public static final SimpleOption<Boolean> BETTER_FOODS = new SimpleOption<>("speedrunnermod.options.better_foods", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_foods.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterFoods.getCurrentValue(), value -> options().main.betterFoods.set(value));

    public static final SimpleOption<Boolean> FALL_DAMAGE = new SimpleOption<>("speedrunnermod.options.fall_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fall_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.fallDamage.getCurrentValue(), value -> options().main.fallDamage.set(value));

    public static final SimpleOption<Boolean> ARROWS_DESTROY_BEDS = new SimpleOption<>("speedrunnermod.options.arrows_destroy_beds", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.arrowsDestroyBeds.getCurrentValue(), value -> options().main.arrowsDestroyBeds.set(value));

    public static final SimpleOption<Boolean> GLOBAL_NETHER_PORTALS = new SimpleOption<>("speedrunnermod.options.global_nether_portals", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.globalNetherPortals.getCurrentValue(), value -> options().main.globalNetherPortals.set(value));

    public static final SimpleOption<Boolean> BETTER_ANVIL = new SimpleOption<>("speedrunnermod.options.better_anvil", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_anvil.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.betterAnvil.getCurrentValue(), value -> options().main.betterAnvil.set(value));

    public static final SimpleOption<Boolean> HIGHER_ENCHANTMENT_LEVELS = new SimpleOption<>("speedrunnermod.options.higher_enchantment_levels", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.higherEnchantmentLevels.getCurrentValue(), value -> options().main.higherEnchantmentLevels.set(value));

    public static final SimpleOption<Boolean> RIGHT_CLICK_TO_REMOVE_SILK_TOUCH = new SimpleOption<>("speedrunnermod.options.right_click_to_remove_silk_touch", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.rightClickToRemoveSilkTouch.getCurrentValue(), value -> options().main.rightClickToRemoveSilkTouch.set(value));

    public static final SimpleOption<Boolean> SHOW_DEATH_CORDS = new SimpleOption<>("speedrunnermod.options.show_death_cords", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_death_cords.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.showDeathCords.getCurrentValue(), value -> options().main.showDeathCords.set(value));

    public static final SimpleOption<Boolean> KINETIC_DAMAGE = new SimpleOption<>("speedrunnermod.options.kinetic_damage", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.kineticDamage.getCurrentValue(), value -> options().main.kineticDamage.set(value));

    public static final SimpleOption<Boolean> THROWABLE_FIREBALLS = new SimpleOption<>("speedrunnermod.options.throwable_fireballs", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.throwableFireballs.getCurrentValue(), value -> options().main.throwableFireballs.set(value));

    public static final SimpleOption<Boolean> CUSTOM_DATA_GENERATION = new SimpleOption<>("speedrunnermod.options.custom_data_generation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().main.customDataGeneration.getCurrentValue(), value -> options().main.customDataGeneration.set(value));

    public static final SimpleOption<Boolean> FAST_WORLD_CREATION = new SimpleOption<>("speedrunnermod.options.fast_world_creation", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, clientOptions().client.fastWorldCreation.getCurrentValue(), value -> clientOptions().client.fastWorldCreation.set(value));

    public static final SimpleOption<Boolean> ALLOW_CHEATS = new SimpleOption<>("speedrunnermod.options.allow_cheats", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, clientOptions().client.allowCheats.getCurrentValue(), value -> clientOptions().client.allowCheats.set(value));

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdGeneration.getCurrentValue(), value -> options().advanced.modifiedStrongholdGeneration.set(value));

    public static final SimpleOption<Boolean> MODIFIED_STRONGHOLD_Y_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_stronghold_y_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedStrongholdYGeneration.getCurrentValue(), value -> options().advanced.modifiedStrongholdYGeneration.set(value));

    public static final SimpleOption<Boolean> MODIFIED_NETHER_FORTRESS_GENERATION = new SimpleOption<>("speedrunnermod.options.modified_nether_fortress_generation", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.modifiedNetherFortressGeneration.getCurrentValue(), value -> options().advanced.modifiedNetherFortressGeneration.set(value));

    public static final SimpleOption<Boolean> SHOW_RESET_BUTTON = new SimpleOption<>("speedrunnermod.options.show_reset_button", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_reset_button.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, clientOptions().client.showResetButton.getCurrentValue(), value -> clientOptions().client.showResetButton.set(value));

    public static final SimpleOption<Boolean> HIGHER_BREATH_TIME = new SimpleOption<>("speedrunnermod.options.higher_breath_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_breath_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.higherBreathTime.getCurrentValue(), value -> options().advanced.higherBreathTime.set(value));

    public static final SimpleOption<Boolean> GENERATE_SPEEDRUNNER_WOOD = new SimpleOption<>("speedrunnermod.options.generate_speedrunner_wood", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.generateSpeedrunnerWood.getCurrentValue(), value -> options().advanced.generateSpeedrunnerWood.set(value));

    public static final SimpleOption<Boolean> LONGER_DRAGON_PERCH_STAY_TIME = new SimpleOption<>("speedrunnermod.options.longer_dragon_perch_stay_time", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.tooltip")),
            (optionText, value) -> !value ? ModTexts.NO : ModTexts.YES, SimpleOption.BOOLEAN, options().advanced.longerDragonPerchStayTime.getCurrentValue(), value -> options().advanced.longerDragonPerchStayTime.set(value));

    public static final SimpleOption<Boolean> DECREASED_ZOMBIFIED_PIGLIN_SCARE_DISTANCE = new SimpleOption<>("speedrunnermod.options.decreased_zombified_piglin_scare_distance", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.decreasedZombifiedPiglinScareDistance.getCurrentValue(), value -> options().advanced.decreasedZombifiedPiglinScareDistance.set(value));

    public static final SimpleOption<Boolean> DRAGON_KILLS_NEARBY_HOSTILE_ENTITIES = new SimpleOption<>("speedrunnermod.options.dragon_kills_nearby_hostile_entities", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.tooltip")),
            (optionText, value) -> !value ? ModTexts.NO : ModTexts.YES, SimpleOption.BOOLEAN, options().advanced.dragonKillsNearbyHostileEntities.getCurrentValue(), value -> options().advanced.dragonKillsNearbyHostileEntities.set(value));

    public static final SimpleOption<Boolean> DRAGON_IMMUNITY_FROM_GOLIATH_AND_WITHER = new SimpleOption<>("speedrunnermod.options.dragon_immunity_from_goliath_and_wither", SimpleOption.emptyTooltip(),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue(), value -> options().advanced.dragonImmunityFromGoliathAndWither.set(value));

    public static final SimpleOption<Boolean> SHIFT_TO_THROW_FIREBALL = new SimpleOption<>("speedrunnermod.options.shift_to_throw_fireball", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.shift_to_throw_fireball.tooltip")),
            (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON, SimpleOption.BOOLEAN, options().advanced.shiftToThrowFireball.getCurrentValue(), value -> options().advanced.shiftToThrowFireball.set(value));

    public static final SimpleOption<Boolean> TERRABLENDER_SURFACE_RULE_DATA_MIXIN = new SimpleOption<>("speedrunnermod.options.terrablender_surface_rule_data_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.terrablender_surface_rule_data_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, options().mixins.terraBlenderSurfaceRuleDataMixin.getCurrentValue(), value -> options().mixins.terraBlenderSurfaceRuleDataMixin.set(value));

    public static final SimpleOption<Boolean> BACKGROUND_RENDERER_MIXIN = new SimpleOption<>("speedrunnermod.options.background_renderer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.background_renderer_mixin.tooltip")),
                (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, clientOptions().mixins.backgroundRendererMixin.getCurrentValue(), value -> clientOptions().mixins.backgroundRendererMixin.set(value));

    public static final SimpleOption<Boolean> SIMPLE_OPTION_MIXIN = new SimpleOption<>("speedrunnermod.options.simple_option_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.simple_option_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, clientOptions().mixins.simpleOptionMixin.getCurrentValue(), value -> clientOptions().mixins.simpleOptionMixin.set(value));

    public static final SimpleOption<Boolean> LOGO_DRAWER_MIXIN = new SimpleOption<>("speedrunnermod.options.logo_drawer_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.logo_drawer_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, clientOptions().mixins.logoDrawerMixin.getCurrentValue(), value -> clientOptions().mixins.logoDrawerMixin.set(value));

    public static final SimpleOption<Boolean> RENDER_LAYERS_MIXIN = new SimpleOption<>("speedrunnermod.options.render_layers_mixin", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.render_layers_mixin.tooltip")),
            (optionText, value) -> !value ? ModTexts.DISABLED : ModTexts.ENABLED, SimpleOption.BOOLEAN, clientOptions().mixins.renderLayersMixin.getCurrentValue(), value -> clientOptions().mixins.renderLayersMixin.set(value));

    public static final SimpleOption<Integer> BLOCK_BREAKING_MULTIPLIER =
            new SimpleOption<>("speedrunnermod.options.block_breaking_multiplier", SimpleOption.emptyTooltip(),
                    (optionText, value) -> {
                        if (value == 1) {
                            return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal("x" + value).formatted(Formatting.AQUA));
                        }
                    },
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.blockBreakingMultiplier.getCurrentValue(), value -> options().main.blockBreakingMultiplier.set(value));

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
                    new SimpleOption.ValidatingIntSliderCallbacks(8, 90), options().main.dragonPerchTime.getCurrentValue(), value -> options().main.dragonPerchTime.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_DISTANCE =
            new SimpleOption<>("speedrunnermod.options.stronghold_distance", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 64), options().main.strongholdDistance.getCurrentValue(), value -> options().main.strongholdDistance.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_SPREAD =
            new SimpleOption<>("speedrunnermod.options.stronghold_spread", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().main.strongholdSpread.getCurrentValue(), value -> options().main.strongholdSpread.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(4, 156), options().main.strongholdCount.getCurrentValue(), value -> options().main.strongholdCount.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_PORTAL_ROOM_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_portal_room_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 3), options().main.strongholdPortalRoomCount.getCurrentValue(), value -> options().main.strongholdPortalRoomCount.set(value));

    public static final SimpleOption<Integer> STRONGHOLD_LIBRARY_COUNT =
            new SimpleOption<>("speedrunnermod.options.stronghold_library_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 8), options().main.strongholdLibraryCount.getCurrentValue(), value -> options().main.strongholdLibraryCount.set(value));

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
                    new SimpleOption.ValidatingIntSliderCallbacks(-1, 20), options().main.netherPortalDelay.getCurrentValue(), value -> options().main.netherPortalDelay.set(value));

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
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 50), options().main.anvilCostLimit.getCurrentValue(), value -> options().main.anvilCostLimit.set(value));

    public static final SimpleOption<Integer> SPEEDRUNNERS_WASTELAND_BIOME_WEIGHT =
            new SimpleOption<>("speedrunnermod.options.speedrunners_wasteland_biome_weight", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(2, 32), options().advanced.speedrunnersWastelandBiomeWeight.getCurrentValue(), value -> options().advanced.speedrunnersWastelandBiomeWeight.set(value));

    public static final SimpleOption<Integer> ENDER_EYE_BREAKING_COOLDOWN =
            new SimpleOption<>("speedrunnermod.options.ender_eye_breaking_cooldown", SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.tooltip")),
                    (optionText, value) -> GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds")),
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.enderEyeBreakingCooldown.getCurrentValue() / 20, value -> options().advanced.enderEyeBreakingCooldown.set(value * 20));

    public static final SimpleOption<Integer> PIGLIN_AWAKENER_PIGLIN_COUNT =
            new SimpleOption<>("speedrunnermod.options.piglin_awakener_piglin_count", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(3, 25), options().advanced.piglinAwakenerPiglinCount.getCurrentValue(), value -> options().advanced.piglinAwakenerPiglinCount.set(value));

    public static final SimpleOption<Integer> ICARUS_FIREWORKS_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.icarus_fireworks_inventory_slot", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), clientOptions().client.iCarusFireworksInventorySlot.getCurrentValue(), value -> clientOptions().client.iCarusFireworksInventorySlot.set(value));

    public static final SimpleOption<Integer> INFINI_PEARL_INVENTORY_SLOT =
            new SimpleOption<>("speedrunnermod.options.infini_pearl_inventory_slot", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 36), clientOptions().client.infiniPearlInventorySlot.getCurrentValue(), value -> clientOptions().client.infiniPearlInventorySlot.set(value));

    public static final SimpleOption<Integer> FIREBALL_EXPLOSION_POWER =
            new SimpleOption<>("speedrunnermod.options.fireball_explosion_power", SimpleOption.emptyTooltip(),
                    ModListOptions::getGenericValueText,
                    new SimpleOption.ValidatingIntSliderCallbacks(1, 10), options().advanced.fireballExplosionPower.getCurrentValue(), value -> options().advanced.fireballExplosionPower.set(value));

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static SimpleOption<Integer> structureSpawnRate(String structure) {
        return new SimpleOption<>("speedrunnermod.options.structure_spawn_rates." + structure, isStructureSpawnRatesCustom() ? SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip")) : SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.custom_required")),
                (optionText, value) -> ModListOptions.listIntegerText(optionText, structure),
                new SimpleOption.ValidatingIntSliderCallbacks(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * Sets the values for each respective structure setting.
     */
    private static void determineValue(String structure, int value) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> setValue(options().structureSpawnRates.ancientCities.getCurrentValue(), value);
            case TranslationStringKeys.VILLAGE -> setValue(options().structureSpawnRates.villages.getCurrentValue(), value);
            case TranslationStringKeys.DESERT_PYRAMID -> setValue(options().structureSpawnRates.desertPyramids.getCurrentValue(), value);
            case TranslationStringKeys.JUNGLE_PYRAMID -> setValue(options().structureSpawnRates.junglePyramids.getCurrentValue(), value);
            case TranslationStringKeys.PILLAGER_OUTPOST -> setValue(options().structureSpawnRates.pillagerOutposts.getCurrentValue(), value);
            case TranslationStringKeys.END_CITY -> setValue(options().structureSpawnRates.endCities.getCurrentValue(), value);
            case TranslationStringKeys.WOODLAND_MANSION -> setValue(options().structureSpawnRates.woodlandMansions.getCurrentValue(), value);
            case TranslationStringKeys.RUINED_PORTAL -> setValue(options().structureSpawnRates.ruinedPortals.getCurrentValue(), value);
            case TranslationStringKeys.SHIPWRECK -> setValue(options().structureSpawnRates.shipwrecks.getCurrentValue(), value);
            case TranslationStringKeys.TRIAL_CHAMBER -> setValue(options().structureSpawnRates.trialChambers.getCurrentValue(), value);
            case TranslationStringKeys.NETHER_COMPLEXES -> setValue(options().structureSpawnRates.netherComplexes.getCurrentValue(), value);
        }
    }

    /**
     * Bounds the value of the {@link SimpleOption} to the {@code spacing value} of the structure, and then sets the separate value to that divided by 2.
     */
    private static void setValue(List<Integer> option, int value) {
        option.set(0, value);
        option.set(1, option.getFirst() / 2);
    }

    /**
     * Returns the text that should be displayed on the {@link SimpleOption}.
     */
    private static Text listIntegerText(Text prefix, String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ancientCities.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.ancientCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.desertPyramids.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.desertPyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.junglePyramids.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.junglePyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.pillagerOutposts.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.pillagerOutposts.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.END_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.endCities.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.endCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.woodlandMansions.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.woodlandMansions.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.ruinedPortals.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.ruinedPortals.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.shipwrecks.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.shipwrecks.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.trialChambers.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.trialChambers.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.netherComplexes.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.netherComplexes.getCurrentValue().get(1)));
            }
            default -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().structureSpawnRates.villages.getCurrentValue().getFirst() + ", " + options().structureSpawnRates.villages.getCurrentValue().get(1)));
            }
        }
    }

    /**
     * Returns the {@code default spacing value} that the respective {@link SimpleOption} should return when loading into the game.
     */
    private static int defaultStructureValue(String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return options().structureSpawnRates.ancientCities.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return options().structureSpawnRates.desertPyramids.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return options().structureSpawnRates.junglePyramids.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return options().structureSpawnRates.pillagerOutposts.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.END_CITY -> {
                return options().structureSpawnRates.endCities.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return options().structureSpawnRates.woodlandMansions.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return options().structureSpawnRates.ruinedPortals.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return options().structureSpawnRates.shipwrecks.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return options().structureSpawnRates.trialChambers.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return options().structureSpawnRates.netherComplexes.getCurrentValue().getFirst();
            }
            default -> {
                return options().structureSpawnRates.villages.getCurrentValue().getFirst();
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