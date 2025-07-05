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
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isSsrCustom;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
@Environment(EnvType.CLIENT)
public class ModListOptions {

    public static SimpleOption<ModOptions.Mode> mode() {
        return new SimpleOption<>("speedrunnermod.options.mode", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.Mode.values()), Codec.INT.xmap(ModOptions.Mode::byId, ModOptions.Mode::getId)),
                options().main.mode.getCurrentValue(), value -> options().main.mode.set(value));
    }

    public static SimpleOption<ModOptions.StructureSpawnRate> structureSpawnRate() {
        return new SimpleOption<>("speedrunnermod.options.structure_spawn_rates", SimpleOption.emptyTooltip(), SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.StructureSpawnRate.values()), Codec.INT.xmap(ModOptions.StructureSpawnRate::byId, ModOptions.StructureSpawnRate::getId)),
                options().main.structureSpawnRates.getCurrentValue(), value -> options().main.structureSpawnRates.set(value));
    }

    public static SimpleOption<ClientModOptions.ItemMessages> itemMessages() {
        return new SimpleOption<>("speedrunnermod.options.item_messages",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.item_messages.tooltip")),
                SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.ItemMessages.values()),
                        Codec.INT.xmap(ClientModOptions.ItemMessages::byId, ClientModOptions.ItemMessages::getId)),
                clientOptions().client.itemMessages.getCurrentValue(),
                value -> clientOptions().client.itemMessages.set(value));
    }

    public static SimpleOption<ModOptions.MobSpawningRate> mobSpawningRate() {
        return new SimpleOption<>("speedrunnermod.options.mob_spawning_rate",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.mob_spawning_rate.tooltip")),
                SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ModOptions.MobSpawningRate.values()),
                        Codec.INT.xmap(ModOptions.MobSpawningRate::byId, ModOptions.MobSpawningRate::getId)),
                options().main.mobSpawningRate.getCurrentValue(),
                value -> options().main.mobSpawningRate.set(value));
    }

    public static SimpleOption<ClientModOptions.GameMode> gameMode() {
        return new SimpleOption<>("speedrunnermod.options.gamemode",
                SimpleOption.emptyTooltip(),
                SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.GameMode.values()),
                        Codec.INT.xmap(ClientModOptions.GameMode::byId, ClientModOptions.GameMode::getId)),
                clientOptions().client.gameMode.getCurrentValue(),
                value -> clientOptions().client.gameMode.set(value));
    }

    public static SimpleOption<ClientModOptions.Difficulty> difficulty() {
        return new SimpleOption<>("speedrunnermod.options.difficulty",
                SimpleOption.emptyTooltip(),
                SimpleOption.enumValueText(),
                new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(ClientModOptions.Difficulty.values()),
                        Codec.INT.xmap(ClientModOptions.Difficulty::byId, ClientModOptions.Difficulty::getId)),
                clientOptions().client.difficulty.getCurrentValue(),
                value -> clientOptions().client.difficulty.set(value));
    }

    public static SimpleOption<Boolean> tutorialMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.tutorial_mode",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.tutorial_mode.tooltip")),
                clientOptions().client.tutorialMode
        );
    }

    public static SimpleOption<Boolean> fasterBlockBreaking() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_block_breaking",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
                options().main.fasterBlockBreaking
        );
    }

    public static SimpleOption<Boolean> icarusMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.icarus_mode",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.icarus_mode.tooltip")),
                options().main.iCarusMode
        );
    }

    public static SimpleOption<Boolean> fog() {
        return new SimpleOption<>("speedrunnermod.options.fog",
                SimpleOption.emptyTooltip(),
                (optionText, value) -> !clientOptions().mixins.backgroundRendererMixin.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON,
                SimpleOption.BOOLEAN,
                clientOptions().client.fog.getCurrentValue(),
                value -> {
                    clientOptions().client.fog.set(value);
                    MinecraftClient.getInstance().worldRenderer.reload();
                });
    }

    public static SimpleOption<Boolean> infiniPearlMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.infini_pearl_mode",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
                options().main.infiniPearlMode
        );
    }

    @Deprecated
    public static SimpleOption<Boolean> leaderboardsMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.leaderboards_mode",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
                options().main.leaderboardsMode
        );
    }

    public static SimpleOption<Boolean> killGhastOnFireball() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kill_ghast_on_fireball",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
                options().main.killGhastOnFireball
        );
    }

    public static SimpleOption<Boolean> betterVillagerTrades() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_villager_trades",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_villager_trades.tooltip")),
                options().main.betterVillagerTrades
        );
    }

    public static SimpleOption<Boolean> fireproofItems() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fireproof_items",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fireproof_items.tooltip")),
                options().main.fireproofItems
        );
    }

    public static SimpleOption<Boolean> fasterSpawners() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_spawners",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.faster_spawners.tooltip")),
                options().main.fasterSpawners
        );
    }

    public static SimpleOption<Boolean> customBiomesAndCustomBiomeFeatures() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.custom_biomes_and_custom_biome_features",
                SimpleOption.emptyTooltip(),
                options().main.customBiomesAndCustomBiomeFeatures
        );
    }

    public static SimpleOption<Boolean> commonOres() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.common_ores",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.common_ores.tooltip")),
                options().main.commonOres
        );
    }

    public static SimpleOption<Boolean> lavaBoats() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.lava_boats",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.lava_boats.tooltip")),
                options().main.lavaBoats
        );
    }

    public static SimpleOption<Boolean> netherWater() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.nether_water",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_water.tooltip")),
                options().main.netherWater
        );
    }

    public static SimpleOption<Boolean> betterFoods() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_foods",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_foods.tooltip")),
                options().main.betterFoods
        );
    }

    public static SimpleOption<Boolean> fallDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fall_damage",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fall_damage.tooltip")),
                options().main.fallDamage
        );
    }

    public static SimpleOption<Boolean> arrowsDestroyBeds() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.arrows_destroy_beds",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
                options().main.arrowsDestroyBeds
        );
    }

    public static SimpleOption<Boolean> globalNetherPortals() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.global_nether_portals",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
                options().main.globalNetherPortals
        );
    }

    public static SimpleOption<Boolean> betterAnvil() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_anvil",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.better_anvil.tooltip")),
                options().main.betterAnvil
        );
    }

    public static SimpleOption<Boolean> higherEnchantmentLevels() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.higher_enchantment_levels",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
                options().main.higherEnchantmentLevels
        );
    }

    public static SimpleOption<Boolean> rightClickToRemoveSilkTouch() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.right_click_to_remove_silk_touch",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
                options().main.rightClickToRemoveSilkTouch
        );
    }

    public static SimpleOption<Boolean> showDeathCords() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.show_death_cords",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_death_cords.tooltip")),
                options().main.showDeathCords
        );
    }

    public static SimpleOption<Boolean> kineticDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kinetic_damage",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
                options().main.kineticDamage
        );
    }

    public static SimpleOption<Boolean> throwableFireballs() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.throwable_fireballs",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
                options().main.throwableFireballs
        );
    }

    public static SimpleOption<Boolean> customDataGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.custom_data_generation",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
                options().main.customDataGeneration
        );
    }

    public static SimpleOption<Boolean> fastWorldCreation() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fast_world_creation",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
                clientOptions().client.fastWorldCreation
        );
    }

    public static SimpleOption<Boolean> allowCheats() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.allow_cheats",
                SimpleOption.emptyTooltip(),
                clientOptions().client.allowCheats
        );
    }

    public static SimpleOption<Boolean> modifiedStrongholdGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_stronghold_generation",
                SimpleOption.emptyTooltip(),
                options().advanced.modifiedStrongholdGeneration
        );
    }

    public static SimpleOption<Boolean> modifiedStrongholdYGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_stronghold_y_generation",
                SimpleOption.emptyTooltip(),
                options().advanced.modifiedStrongholdYGeneration
        );
    }

    public static SimpleOption<Boolean> modifiedNetherFortressGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_nether_fortress_generation",
                SimpleOption.emptyTooltip(),
                options().advanced.modifiedNetherFortressGeneration
        );
    }

    public static SimpleOption<Boolean> dragonKillsNearbyHostileEntities() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.dragon_kills_nearby_hostile_entities",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.tooltip")),
                options().advanced.dragonKillsNearbyHostileEntities
        );
    }

    public static SimpleOption<Boolean> dragonImmunityFromGoliathAndWither() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.dragon_immunity_from_goliath_and_wither",
                SimpleOption.emptyTooltip(),
                options().advanced.dragonImmunityFromGoliathAndWither
        );
    }

    public static SimpleOption<Boolean> shiftToThrowFireball() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.shift_to_throw_fireball",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.shift_to_throw_fireball.tooltip")),
                options().advanced.shiftToThrowFireball
        );
    }

    public static SimpleOption<Boolean> showResetButton() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.show_reset_button",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.show_reset_button.tooltip")),
                clientOptions().client.showResetButton
        );
    }

    public static SimpleOption<Boolean> higherBreathTime() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.higher_breath_time",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.higher_breath_time.tooltip")),
                options().advanced.higherBreathTime
        );
    }

    public static SimpleOption<Boolean> generateSpeedrunnerWood() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.generate_speedrunner_wood",
                SimpleOption.emptyTooltip(),
                options().advanced.generateSpeedrunnerWood
        );
    }

    public static SimpleOption<Boolean> longerDragonPerchStayTime() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.longer_dragon_perch_stay_time",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.tooltip")),
                options().advanced.longerDragonPerchStayTime,
                true
        );
    }

    public static SimpleOption<Boolean> decreasedZombifiedPiglinScareDistance() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.decreased_zombified_piglin_scare_distance",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.tooltip")),
                options().advanced.decreasedZombifiedPiglinScareDistance
        );
    }

    public static SimpleOption<Boolean> enterFeatureScreens() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.enter_feature_screens",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.enter_feature_screens.tooltip")),
                clientOptions().storedValues.enterFeaturesScreen,
                true
        );
    }

    public static SimpleOption<Boolean> terraBlenderSurfaceRuleDataMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.terrablender_surface_rule_data_mixin",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.terrablender_surface_rule_data_mixin.tooltip")),
                options().mixins.terraBlenderSurfaceRuleDataMixin,
                false
        );
    }

    public static SimpleOption<Boolean> backgroundRendererMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.background_renderer_mixin",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.background_renderer_mixin.tooltip")),
                clientOptions().mixins.backgroundRendererMixin,
                false
        );
    }

    public static SimpleOption<Boolean> simpleOptionMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.simple_option_mixin",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.simple_option_mixin.tooltip")),
                clientOptions().mixins.simpleOptionMixin,
                false
        );
    }

    public static SimpleOption<Boolean> logoDrawerMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.logo_drawer_mixin",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.logo_drawer_mixin.tooltip")),
                clientOptions().mixins.logoDrawerMixin,
                false
        );
    }

    public static SimpleOption<Boolean> renderLayersMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.render_layers_mixin",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.render_layers_mixin.tooltip")),
                clientOptions().mixins.renderLayersMixin,
                false
        );
    }

    public static SimpleOption<Integer> blockBreakingMultiplier() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.block_breaking_multiplier",
                SimpleOption.emptyTooltip(),
                options().main.blockBreakingMultiplier,
                (optionText, value) -> {
                    if (value == 1) {
                        return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                    } else {
                        return GameOptions.getGenericValueText(optionText, Text.literal("x" + value).formatted(Formatting.AQUA));
                    }
                }
        );
    }

    public static SimpleOption<Integer> dragonPerchTime() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.dragon_perch_time",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.dragon_perch_time.tooltip")),
                options().main.dragonPerchTime,
                (optionText, value) -> {
                    if (value == 9) {
                        return GameOptions.getGenericValueText(optionText, Text.literal("Instant").formatted(Formatting.GREEN));
                    } else if (value <= 8) {
                        return GameOptions.getGenericValueText(optionText, ModTexts.OFF);
                    } else if (value >= 60 && value <= 119) {
                        int minutes = value / 60;
                        int seconds = value % 60;
                        if (seconds == 0) {
                            return GameOptions.getGenericValueText(optionText, Text.literal(minutes + "m").formatted(Formatting.DARK_AQUA));
                        } else {
                            return GameOptions.getGenericValueText(optionText, Text.literal(minutes + "m " + seconds + "s").formatted(Formatting.DARK_AQUA));
                        }
                    } else {
                        return GameOptions.getGenericValueText(optionText, Text.literal(value + "s").formatted(Formatting.AQUA));
                    }
                }
        );
    }

    public static SimpleOption<Integer> strongholdDistance() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_distance",
                SimpleOption.emptyTooltip(),
                options().main.strongholdDistance
        );
    }

    public static SimpleOption<Integer> strongholdSpread() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_spread",
                SimpleOption.emptyTooltip(),
                options().main.strongholdSpread
        );
    }

    public static SimpleOption<Integer> strongholdCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_count",
                SimpleOption.emptyTooltip(),
                options().main.strongholdCount
        );
    }

    public static SimpleOption<Integer> strongholdPortalRoomCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_portal_room_count",
                SimpleOption.emptyTooltip(),
                options().main.strongholdPortalRoomCount
        );
    }

    public static SimpleOption<Integer> strongholdLibraryCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_library_count",
                SimpleOption.emptyTooltip(),
                options().main.strongholdLibraryCount
        );
    }

    public static SimpleOption<Integer> netherPortalDelay() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.nether_portal_delay",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.nether_portal_delay.tooltip")),
                options().main.netherPortalDelay,
                (optionText, value) -> {
                    if (value == -1) {
                        return GameOptions.getGenericValueText(optionText, Text.literal("Go by Gamerule").formatted(Formatting.GREEN));
                    } else if (value == 0) {
                        return GameOptions.getGenericValueText(optionText, Text.literal("None").formatted(Formatting.RED));
                    } else {
                        return GameOptions.getGenericValueText(optionText, Text.literal(value + "s").formatted(Formatting.AQUA));
                    }
                }
        );
    }

    public static SimpleOption<Integer> anvilCostLimit() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.anvil_cost_limit",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.anvil_cost_limit.tooltip")),
                options().main.anvilCostLimit,
                (optionText, value) -> {
                    if (value == 50) {
                        return GameOptions.getGenericValueText(optionText, Text.literal("No Limit").formatted(Formatting.RED));
                    } else if (value == 1) {
                        return GameOptions.getGenericValueText(optionText, Text.literal(value + " level").formatted(Formatting.AQUA));
                    } else {
                        return GameOptions.getGenericValueText(optionText, Text.literal(value + " levels").formatted(Formatting.AQUA));
                    }
                }
        );
    }

    public static SimpleOption<Integer> speedrunnersWastelandBiomeWeight() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.speedrunners_wasteland_biome_weight",
                SimpleOption.emptyTooltip(),
                options().advanced.speedrunnersWastelandBiomeWeight
        );
    }

    public static SimpleOption<Integer> enderEyeBreakingCooldown() {
        return new SimpleOption<>("speedrunnermod.options.ender_eye_breaking_cooldown",
                SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.tooltip")),
                (optionText, value) -> GameOptions.getGenericValueText(optionText, Text.literal(value + " seconds")),
                new SimpleOption.ValidatingIntSliderCallbacks(1, 10),
                options().advanced.enderEyeBreakingCooldown.getCurrentValue() / 20,
                value -> options().advanced.enderEyeBreakingCooldown.set(value * 20));
    }

    public static SimpleOption<Integer> piglinAwakenerPiglinCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.piglin_awakener_piglin_count",
                SimpleOption.emptyTooltip(),
                options().advanced.piglinAwakenerPiglinCount
        );
    }

    public static SimpleOption<Integer> icarusFireworksInventorySlot() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.icarus_fireworks_inventory_slot",
                SimpleOption.emptyTooltip(),
                clientOptions().client.iCarusFireworksInventorySlot
        );
    }

    public static SimpleOption<Integer> infiniPearlInventorySlot() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.infini_pearl_inventory_slot",
                SimpleOption.emptyTooltip(),
                clientOptions().client.infiniPearlInventorySlot
        );
    }

    public static SimpleOption<Integer> fireballExplosionPower() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.fireball_explosion_power",
                SimpleOption.emptyTooltip(),
                options().advanced.fireballExplosionPower
        );
    }

    public static SimpleOption<Integer> annulEyeSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.annul_eye_search_radius",
                SimpleOption.emptyTooltip(),
                options().advanced.annulEyeSearchRadius,
                100,
                200,
                x,
                y,
                z,
                true
        );
    }

    public static SimpleOption<Integer> piglinAwakenerSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.piglin_awakener_search_radius",
                SimpleOption.emptyTooltip(),
                options().advanced.piglinAwakenerSearchRadius,
                100,
                300,
                x,
                y,
                z,
                false
        );
    }

    public static SimpleOption<Integer> blazeSpotterSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.blaze_spotter_search_radius",
                SimpleOption.emptyTooltip(),
                options().advanced.blazeSpotterSearchRadius,
                50,
                300,
                x,
                y,
                z,
                true
        );
    }

    public static SimpleOption<Integer> raidEradicatorSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.raid_eradicator_search_radius",
                SimpleOption.emptyTooltip(),
                options().advanced.raidEradicatorSearchRadius,
                100,
                400,
                x,
                y,
                z,
                false
        );
    }

    public static SimpleOption<Integer> dragonsPearlSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragons_pearl_search_radius",
                SimpleOption.emptyTooltip(),
                options().advanced.dragonsPearlSearchRadius,
                100,
                350,
                x,
                y,
                z,
                false
        );
    }

    public static SimpleOption<Integer> dragonMassKillRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_mass_kill_radius",
                SimpleOption.constantTooltip(ModListOptions.listIntegerTooltip(Text.translatable("speedrunnermod.options.dragon_mass_kill_radius.tooltip"))),
                options().advanced.dragonMassKillRadius,
                100,
                300,
                x,
                y,
                z,
                false
        );
    }

    public static SimpleOption<Integer> dragonImmunityDetectionRadiusForGoliath(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_immunity_detection_radius_for_goliath",
                SimpleOption.emptyTooltip(),
                options().advanced.dragonImmunityDetectionRadiusForGoliath,
                100,
                300,
                x,
                y,
                z,
                false
        );
    }

    public static SimpleOption<Integer> dragonImmunityDetectionRadiusForWither(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_immunity_detection_radius_for_wither",
                SimpleOption.emptyTooltip(),
                options().advanced.dragonImmunityDetectionRadiusForWither,
                100,
                350,
                x,
                y,
                z,
                false
        );
    }

    /**
     * Creates a new {@code simple boolean option.}
     */
    private static SimpleOption<Boolean> createSimpleBooleanOption(String key, SimpleOption.TooltipFactory<Boolean> tooltip, OptionValue<Boolean> option) {
        return new SimpleOption<>(key, tooltip,
                (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON,
                SimpleOption.BOOLEAN,
                option.getCurrentValue(),
                option::set);
    }

    /**
     * Creates a new {@code simple boolean option} with different text displayers.
     */
    private static SimpleOption<Boolean> createSimpleBooleanOptionWithCustomSwitch(String key, SimpleOption.TooltipFactory<Boolean> tooltip, OptionValue<Boolean> option, boolean question) {
        return new SimpleOption<>(key, tooltip,
                (optionText, value) -> !value ? question ? ModTexts.NO : ModTexts.DISABLED : question ? ModTexts.YES : ModTexts.ENABLED,
                SimpleOption.BOOLEAN,
                option.getCurrentValue(),
                option::set);
    }

    /**
     * Creates a new {@code simple integer option.}
     */
    private static SimpleOption<Integer> createSimpleIntegerOption(String key, SimpleOption.TooltipFactory<Integer> tooltip, IntegerOptionValue option) {
        return new SimpleOption<>(key, tooltip,
                ModListOptions::getGenericValueText,
                new SimpleOption.ValidatingIntSliderCallbacks(option.getMinValue(), option.getMaxValue()), option.getCurrentValue(), option::set);
    }

    /**
     * Creates a new {@code simple integer option} custom a custom {@code text supplier.}
     */
    private static SimpleOption<Integer> createSimpleIntegerOption(String key, SimpleOption.TooltipFactory<Integer> tooltip, IntegerOptionValue option, BiFunction<Text, Integer, Text> formatter) {
        return new SimpleOption<>(key, tooltip,
                formatter::apply,
                new SimpleOption.ValidatingIntSliderCallbacks(option.getMinValue(), option.getMaxValue()), option.getCurrentValue(), option::set);
    }

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static SimpleOption<Integer> createStructureSpawnRateOption(String structure) {
        return new SimpleOption<>("speedrunnermod.options.structure_spawn_rates." + structure, isSsrCustom() ? SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip")) : SimpleOption.constantTooltip(Text.translatable("speedrunnermod.options.structure_spawn_rates.custom_required")),
                (optionText, value) -> ModListOptions.listIntegerText(optionText, structure),
                new SimpleOption.ValidatingIntSliderCallbacks(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * Creates an {@code integer list option.}
     */
    private static SimpleOption<Integer> ofIntegerList(String key, SimpleOption.TooltipFactory<Integer> tooltip, OptionValue<List<Integer>> option, int min, int max, boolean x, boolean y, boolean z, boolean includeNeg) {
        return new SimpleOption<>(key, tooltip,
                (optionText, value) -> ModListOptions.listIntegerText(optionText,
                        option,
                        x,
                        y,
                        z,
                        includeNeg
                ),
                new SimpleOption.ValidatingIntSliderCallbacks(min, max),
                x ? option.getCurrentValue().get(includeNeg ? 3 : 0) :
                        y ? option.getCurrentValue().get(includeNeg ? 4 : 1) :
                                option.getCurrentValue().get(includeNeg ? 5 : 2),
                value -> setValue(option.getCurrentValue(), value, x, y, z, includeNeg));
    }

    /**
     * Bounds the value of the {@link SimpleOption} to the {@code -X, -Y, -Z, X, Y and Z}.
     */
    private static void setValue(List<Integer> option, int value, boolean x, boolean y, boolean z, boolean includeNeg) {
        if (includeNeg) {
            if (x) {
                option.set(0, -value);
                option.set(3, value);
            } else if (y) {
                option.set(1, -value);
                option.set(4, value);
            } else if (z) {
                option.set(2, -value);
                option.set(5, value);
            } else {
                for (int i = 0; i < option.size(); i++) {
                    option.set(i, i < 3 ? -value : value);
                }
            }
        } else {
            if (x) {
                option.set(0, value);
            } else if (y) {
                option.set(1, value);
            } else if (z) {
                option.set(2, value);
            } else {
                Collections.fill(option, value);
            }
        }
    }

    /**
     * @return the text for {@code list integer options}, with {@code x, y, and z} values.
     */
    private static Text listIntegerText(Text prefix, OptionValue<List<Integer>> value, boolean x, boolean y, boolean z, boolean includeNeg) {
        boolean all = !x && !y && !z;
        Text xText = Text.literal("X: " + value.getCurrentValue().get(includeNeg ? 3 : 0)).formatted(x || all ? Formatting.GREEN : Formatting.GRAY);
        Text yText = Text.literal("Y: " + value.getCurrentValue().get(includeNeg ? 4 : 1)).formatted(y || all ? Formatting.GREEN : Formatting.GRAY);
        Text zText = Text.literal("Z: " + value.getCurrentValue().get(includeNeg ? 5 : 2)).formatted(z || all ? Formatting.GREEN : Formatting.GRAY);
        Text comma = Text.literal(", ").formatted(Formatting.WHITE);
        return GameOptions.getGenericValueText(prefix, xText.copy().append(comma).append(yText).append(comma).append(zText));
    }

    /**
     * @return the {@code tooltip} to display, along with the {@code list options control} tooltip.
     */
    public static Text listIntegerTooltip(Text tooltip) {
        return tooltip.copy().append("\n\n").append(Text.translatable("speedrunnermod.options.list_options_control"));
    }

    /**
     * Sets the values for each respective structure setting.
     */
    private static void determineValue(String structure, int value) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> setValue(options().customStructureSpawnRates.ancientCities.getCurrentValue(), value);
            case TranslationStringKeys.VILLAGE -> setValue(options().customStructureSpawnRates.villages.getCurrentValue(), value);
            case TranslationStringKeys.DESERT_PYRAMID -> setValue(options().customStructureSpawnRates.desertPyramids.getCurrentValue(), value);
            case TranslationStringKeys.JUNGLE_PYRAMID -> setValue(options().customStructureSpawnRates.junglePyramids.getCurrentValue(), value);
            case TranslationStringKeys.PILLAGER_OUTPOST -> setValue(options().customStructureSpawnRates.pillagerOutposts.getCurrentValue(), value);
            case TranslationStringKeys.END_CITY -> setValue(options().customStructureSpawnRates.endCities.getCurrentValue(), value);
            case TranslationStringKeys.WOODLAND_MANSION -> setValue(options().customStructureSpawnRates.woodlandMansions.getCurrentValue(), value);
            case TranslationStringKeys.RUINED_PORTAL -> setValue(options().customStructureSpawnRates.ruinedPortals.getCurrentValue(), value);
            case TranslationStringKeys.SHIPWRECK -> setValue(options().customStructureSpawnRates.shipwrecks.getCurrentValue(), value);
            case TranslationStringKeys.TRIAL_CHAMBER -> setValue(options().customStructureSpawnRates.trialChambers.getCurrentValue(), value);
            case TranslationStringKeys.NETHER_COMPLEXES -> setValue(options().customStructureSpawnRates.netherComplexes.getCurrentValue(), value);
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
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.ancientCities.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.ancientCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.desertPyramids.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.desertPyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.junglePyramids.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.junglePyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.pillagerOutposts.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.pillagerOutposts.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.END_CITY -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.endCities.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.endCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.woodlandMansions.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.woodlandMansions.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.ruinedPortals.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.ruinedPortals.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.shipwrecks.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.shipwrecks.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.trialChambers.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.trialChambers.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.netherComplexes.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.netherComplexes.getCurrentValue().get(1)));
            }
            default -> {
                return GameOptions.getGenericValueText(prefix, Text.literal(options().customStructureSpawnRates.villages.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.villages.getCurrentValue().get(1)));
            }
        }
    }

    /**
     * Returns the {@code default spacing value} that the respective {@link SimpleOption} should return when loading into the game.
     */
    private static int defaultStructureValue(String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return options().customStructureSpawnRates.ancientCities.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return options().customStructureSpawnRates.desertPyramids.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return options().customStructureSpawnRates.junglePyramids.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return options().customStructureSpawnRates.pillagerOutposts.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.END_CITY -> {
                return options().customStructureSpawnRates.endCities.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return options().customStructureSpawnRates.woodlandMansions.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return options().customStructureSpawnRates.ruinedPortals.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return options().customStructureSpawnRates.shipwrecks.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return options().customStructureSpawnRates.trialChambers.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return options().customStructureSpawnRates.netherComplexes.getCurrentValue().getFirst();
            }
            default -> {
                return options().customStructureSpawnRates.villages.getCurrentValue().getFirst();
            }
        }
    }

    /**
     * @return the {@code tooltip} to render for each {@code structure spawn rate.}
     */
    public static Text structureSpawnRateTooltip() {
        Text structureSpawnRate;
        switch (options().main.structureSpawnRates.getCurrentValue()) {
            case EVERYWHERE -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.everywhere.tooltip");
            case VERY_COMMON -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.very_common.tooltip");
            case COMMON -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.common.tooltip");
            case NORMAL -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.normal.tooltip");
            case RARE -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.rare.tooltip");
            case VERY_RARE -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.very_rare.tooltip");
            case CUSTOM -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.custom.tooltip");
            default -> structureSpawnRate = Text.translatable("speedrunnermod.options.structure_spawn_rates.default.tooltip");
        }
        return Text.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")
                .copy()
                .append("\n\n")
                .append(structureSpawnRate);
    }

    /**
     * @return the generic value text prefix, with aqua formatting.
     */
    private static Text getGenericValueText(Text prefix, int value) {
        return GameOptions.getGenericValueText(prefix, Text.literal(Integer.toString(value)).formatted(Formatting.AQUA));
    }
}