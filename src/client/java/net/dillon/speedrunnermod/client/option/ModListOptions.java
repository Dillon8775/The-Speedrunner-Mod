package net.dillon.speedrunnermod.client.option;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.client.util.TranslationStringKeys;
import net.dillon.speedrunnermod.option.IntegerOptionValue;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.option.OptionValue;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static net.dillon.speedrunnermod.client.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.*;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
public class ModListOptions {

    public static OptionInstance<ModOptions.Mode> mode() {
        return new OptionInstance<>("speedrunnermod.options.mode", OptionInstance.noTooltip(), (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ModOptions.Mode.values()), Codec.INT.xmap(ModOptions.Mode::byId, ModOptions.Mode::getId)),
                options().main.mode.getCurrentValue(), value -> options().main.mode.set(value));
    }

    public static OptionInstance<ModOptions.StructureSpawnRate> structureSpawnRate() {
        return new OptionInstance<>("speedrunnermod.options.structure_spawn_rates", OptionInstance.noTooltip(), (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ModOptions.StructureSpawnRate.values()), Codec.INT.xmap(ModOptions.StructureSpawnRate::byId, ModOptions.StructureSpawnRate::getId)),
                options().main.structureSpawnRates.getCurrentValue(), value -> options().main.structureSpawnRates.set(value));
    }

    public static OptionInstance<ClientModOptions.ItemMessages> itemMessages() {
        return new OptionInstance<>("speedrunnermod.options.item_messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.item_messages.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ClientModOptions.ItemMessages.values()),
                        Codec.INT.xmap(ClientModOptions.ItemMessages::byId, ClientModOptions.ItemMessages::getId)),
                clientOptions().client.itemMessages.getCurrentValue(),
                value -> clientOptions().client.itemMessages.set(value));
    }

    public static OptionInstance<ModOptions.CreatureSpawnRate> creatureSpawningRate() {
        return new OptionInstance<>("speedrunnermod.options.creature_spawn_rate",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.creature_spawn_rate.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ModOptions.CreatureSpawnRate.values()),
                        Codec.INT.xmap(ModOptions.CreatureSpawnRate::byId, ModOptions.CreatureSpawnRate::getId)),
                options().main.creatureSpawnRate.getCurrentValue(),
                value -> options().main.creatureSpawnRate.set(value));
    }

    public static OptionInstance<ClientModOptions.GameMode> gameMode() {
        return new OptionInstance<>("speedrunnermod.options.gamemode",
                OptionInstance.noTooltip(),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ClientModOptions.GameMode.values()),
                        Codec.INT.xmap(ClientModOptions.GameMode::byId, ClientModOptions.GameMode::getId)),
                clientOptions().client.gameMode.getCurrentValue(),
                value -> clientOptions().client.gameMode.set(value));
    }

    public static OptionInstance<ClientModOptions.Difficulty> difficulty() {
        return new OptionInstance<>("speedrunnermod.options.difficulty",
                OptionInstance.noTooltip(),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ClientModOptions.Difficulty.values()),
                        Codec.INT.xmap(ClientModOptions.Difficulty::byId, ClientModOptions.Difficulty::getId)),
                isDoomMode() ? ClientModOptions.Difficulty.HARD : clientOptions().client.difficulty.getCurrentValue(),
                value -> clientOptions().client.difficulty.set(value));
    }

    public static OptionInstance<Boolean> fasterBlockBreaking() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_block_breaking",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
                options().main.fasterBlockBreaking
        );
    }

    public static OptionInstance<Boolean> icarusMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.icarus_mode",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.icarus_mode.tooltip")),
                options().main.iCarusMode
        );
    }

    public static OptionInstance<Boolean> fog() {
        return new OptionInstance<>("speedrunnermod.options.fog",
                OptionInstance.noTooltip(),
                (optionText, value) -> !clientOptions().mixins.fogMixins.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON,
                OptionInstance.BOOLEAN_VALUES,
                clientOptions().client.fog.getCurrentValue(),
                value -> {
                    clientOptions().client.fog.set(value);
                    Minecraft.getInstance().levelRenderer.allChanged();
                });
    }

    public static OptionInstance<Boolean> increasedLavaVision() {
        return new OptionInstance<>("speedrunnermod.options.increased_lava_vision",
                OptionInstance.noTooltip(),
                (optionText, value) -> !clientOptions().mixins.fogMixins.getCurrentValue() ? ModTexts.FEATURE_DISABLED : !value ? ModTexts.OFF : ModTexts.ON,
                OptionInstance.BOOLEAN_VALUES,
                clientOptions().client.increasedLavaVision.getCurrentValue(),
                value -> clientOptions().client.increasedLavaVision.set(value));
    }

    public static OptionInstance<Boolean> infiniPearlMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.infini_pearl_mode",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.infini_pearl_mode.tooltip")),
                options().main.infiniPearlMode
        );
    }

    @Deprecated
    public static OptionInstance<Boolean> leaderboardsMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.leaderboards_mode",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
                options().main.leaderboardsMode
        );
    }

    public static OptionInstance<Boolean> killGhastOnFireball() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kill_ghast_on_fireball",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
                options().main.killGhastOnFireball
        );
    }

    public static OptionInstance<Boolean> fireproofItems() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fireproof_items",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fireproof_items.tooltip")),
                options().main.fireproofItems
        );
    }

    public static OptionInstance<Boolean> fasterSpawners() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_spawners",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_spawners.tooltip")),
                options().main.fasterSpawners
        );
    }

    public static OptionInstance<Boolean> fasterSmelting() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_smelting",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_smelting.tooltip")),
                options().main.fasterSmelting
        );
    }

    public static OptionInstance<Boolean> fasterBrewing() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_brewing",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_brewing.tooltip")),
                options().main.fasterBrewing
        );
    }

    public static OptionInstance<Boolean> betterBiomes() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_biomes",
                OptionInstance.noTooltip(),
                options().main.betterBiomes
        );
    }

    public static OptionInstance<Boolean> customBiomesAndCustomBiomeFeatures() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.custom_biomes_and_custom_biome_features",
                OptionInstance.noTooltip(),
                options().main.customBiomesAndCustomBiomeFeatures
        );
    }

    public static OptionInstance<Boolean> commonOres() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.common_ores",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.common_ores.tooltip")),
                options().main.commonOres
        );
    }

    public static OptionInstance<Boolean> lavaBoats() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.lava_boats",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.lava_boats.tooltip")),
                options().main.lavaBoats
        );
    }

    public static OptionInstance<Boolean> netherWater() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.nether_water",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.nether_water.tooltip")),
                options().main.netherWater
        );
    }

    public static OptionInstance<Boolean> betterFoods() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_foods",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.better_foods.tooltip")),
                options().main.betterFoods
        );
    }

    public static OptionInstance<Boolean> fallDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fall_damage",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fall_damage.tooltip")),
                options().main.fallDamage
        );
    }

    public static OptionInstance<Boolean> arrowsDestroyBeds() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.arrows_destroy_beds",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
                options().main.arrowsDestroyBeds
        );
    }

    public static OptionInstance<Boolean> globalNetherPortals() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.global_nether_portals",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
                options().main.globalNetherPortals
        );
    }

    public static OptionInstance<Boolean> betterAnvil() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_anvil",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.better_anvil.tooltip")),
                options().main.betterAnvil
        );
    }

    public static OptionInstance<Boolean> higherEnchantmentLevels() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.higher_enchantment_levels",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
                options().main.higherEnchantmentLevels
        );
    }

    public static OptionInstance<Boolean> rightClickToRemoveSilkTouch() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.right_click_to_remove_silk_touch",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
                options().main.rightClickToRemoveSilkTouch
        );
    }

    public static OptionInstance<Boolean> showDeathCords() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.show_death_cords",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.show_death_cords.tooltip")),
                options().main.showDeathCords
        );
    }

    public static OptionInstance<Boolean> kineticDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kinetic_damage",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
                options().main.kineticDamage
        );
    }

    public static OptionInstance<Boolean> throwableFireballs() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.throwable_fireballs",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
                options().main.throwableFireballs
        );
    }

    public static OptionInstance<Boolean> customDataGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.custom_data_generation",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.custom_data_generation.tooltip")),
                options().main.customDataGeneration
        );
    }

    public static OptionInstance<Boolean> fastWorldCreation() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fast_world_creation",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
                clientOptions().client.fastWorldCreation
        );
    }

    public static OptionInstance<Boolean> allowCheats() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.allow_cheats",
                OptionInstance.noTooltip(),
                clientOptions().client.allowCheats
        );
    }

    public static OptionInstance<Boolean> modifiedStrongholdGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_stronghold_generation",
                new boolean[]{!isBalancedMode()},
                options().advanced.modifiedStrongholdGeneration
        );
    }

    public static OptionInstance<Boolean> modifiedStrongholdYGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_stronghold_y_generation",
                new boolean[]{!isBalancedMode()},
                options().advanced.modifiedStrongholdYGeneration
        );
    }

    public static OptionInstance<Boolean> modifiedNetherFortressGeneration() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.modified_nether_fortress_generation",
                new boolean[]{!isBalancedMode()},
                options().advanced.modifiedNetherFortressGeneration
        );
    }

    public static OptionInstance<Boolean> dragonKillsNearbyHostileEntities() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.dragon_kills_nearby_hostile_entities",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.tooltip")),
                options().advanced.dragonKillsNearbyHostileEntities
        );
    }

    public static OptionInstance<Boolean> dragonImmunityFromGoliathAndWither() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.dragon_immunity_from_goliath_and_wither",
                OptionInstance.noTooltip(),
                options().advanced.dragonImmunityFromGoliathAndWither
        );
    }

    public static OptionInstance<Boolean> shiftToThrowFireball() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.shift_to_throw_fireball",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.shift_to_throw_fireball.tooltip")),
                options().advanced.shiftToThrowFireball
        );
    }

    public static OptionInstance<Boolean> showResetButton() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.show_reset_button",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.show_reset_button.tooltip")),
                clientOptions().client.showResetButton
        );
    }

    public static OptionInstance<Boolean> higherBreathTime() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.higher_breath_time",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.higher_breath_time.tooltip")),
                options().advanced.higherBreathTime
        );
    }

    public static OptionInstance<Boolean> generateSpeedrunnerWood() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.generate_speedrunner_wood",
                OptionInstance.noTooltip(),
                options().advanced.generateSpeedrunnerWood
        );
    }

    public static OptionInstance<Boolean> longerDragonPerchStayTime() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.longer_dragon_perch_stay_time",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.tooltip")),
                options().advanced.longerDragonPerchStayTime,
                true
        );
    }

    public static OptionInstance<Boolean> decreasedZombifiedPiglinScareDistance() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.decreased_zombified_piglin_scare_distance",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.tooltip")),
                options().advanced.decreasedZombifiedPiglinScareDistance
        );
    }

    public static OptionInstance<Boolean> enterFeatureScreens() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.enter_feature_screens",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.enter_feature_screens.tooltip")),
                clientOptions().storedValues.enterFeaturesScreen,
                true
        );
    }

    public static OptionInstance<Boolean> theEndGatewayBlockEntityMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.the_end_gateway_block_entity_mixin",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.the_end_gateway_block_entity_mixin.tooltip")),
                options().mixins.theEndGatewayBlockEntityMixin,
                false
        );
    }

    public static OptionInstance<Boolean> fogMixins() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.fog_mixins",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fog_mixins.tooltip")),
                clientOptions().mixins.fogMixins,
                false
        );
    }

    public static OptionInstance<Boolean> simpleOptionMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.option_instance_mixin",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.option_instance_mixin.tooltip")),
                clientOptions().mixins.optionInstanceMixin,
                false
        );
    }

    public static OptionInstance<Boolean> logoDrawerMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.logo_renderer_mixin",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.logo_renderer_mixin.tooltip")),
                clientOptions().mixins.logoRendererMixin,
                false
        );
    }

    public static OptionInstance<Integer> blockBreakingMultiplier() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.block_breaking_multiplier",
                OptionInstance.noTooltip(),
                options().main.blockBreakingMultiplier,
                (optionText, value) -> {
                    if (value == 1) {
                        return Options.genericValueLabel(optionText, ModTexts.OFF);
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal("x" + value).withStyle(ChatFormatting.AQUA));
                    }
                }
        );
    }

    public static OptionInstance<Integer> dragonPerchTime() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.dragon_perch_time",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.dragon_perch_time.tooltip")),
                options().main.dragonPerchTime,
                (optionText, value) -> {
                    if (value == 9) {
                        return Options.genericValueLabel(optionText, Component.literal("Instant").withStyle(ChatFormatting.GREEN));
                    } else if (value <= 8) {
                        return Options.genericValueLabel(optionText, ModTexts.OFF);
                    } else if (value >= 60 && value <= 119) {
                        int minutes = value / 60;
                        int seconds = value % 60;
                        if (seconds == 0) {
                            return Options.genericValueLabel(optionText, Component.literal(minutes + "m").withStyle(ChatFormatting.DARK_AQUA));
                        } else {
                            return Options.genericValueLabel(optionText, Component.literal(minutes + "m " + seconds + "s").withStyle(ChatFormatting.DARK_AQUA));
                        }
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal(value + "s").withStyle(ChatFormatting.AQUA));
                    }
                }
        );
    }

    public static OptionInstance<Integer> strongholdDistance() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_distance",
                OptionInstance.noTooltip(),
                options().main.strongholdDistance
        );
    }

    public static OptionInstance<Integer> strongholdSpread() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_spread",
                OptionInstance.noTooltip(),
                options().main.strongholdSpread
        );
    }

    public static OptionInstance<Integer> strongholdCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_count",
                OptionInstance.noTooltip(),
                options().main.strongholdCount
        );
    }

    public static OptionInstance<Integer> strongholdPortalRoomCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_portal_room_count",
                OptionInstance.noTooltip(),
                options().main.strongholdPortalRoomCount
        );
    }

    public static OptionInstance<Integer> strongholdLibraryCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_library_count",
                OptionInstance.noTooltip(),
                options().main.strongholdLibraryCount
        );
    }

    public static OptionInstance<Integer> netherPortalDelay() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.nether_portal_delay",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.nether_portal_delay.tooltip")),
                options().main.netherPortalDelay,
                (optionText, value) -> {
                    if (value == -1) {
                        return Options.genericValueLabel(optionText, Component.literal("Go by Gamerule").withStyle(ChatFormatting.GREEN));
                    } else if (value == 0) {
                        return Options.genericValueLabel(optionText, Component.literal("None").withStyle(ChatFormatting.RED));
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal(value + "s").withStyle(ChatFormatting.AQUA));
                    }
                }
        );
    }

    public static OptionInstance<Integer> anvilCostLimit() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.anvil_cost_limit",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.anvil_cost_limit.tooltip")),
                options().main.anvilCostLimit,
                (optionText, value) -> {
                    if (value == 50) {
                        return Options.genericValueLabel(optionText, Component.literal("No Limit").withStyle(ChatFormatting.RED));
                    } else if (value == 1) {
                        return Options.genericValueLabel(optionText, Component.literal(value + " level").withStyle(ChatFormatting.AQUA));
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal(value + " levels").withStyle(ChatFormatting.AQUA));
                    }
                }
        );
    }

    public static OptionInstance<Integer> enderEyeBreakingCooldown() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.ender_eye_breaking_cooldown",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.tooltip")),
                options().advanced.enderEyeBreakingCooldown,
                (optionText, value) -> Options.genericValueLabel(optionText, Component.literal(value + "s").withStyle(ChatFormatting.AQUA))
        );
    }

    public static OptionInstance<Integer> piglinAwakenerPiglinCount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.piglin_awakener_piglin_count",
                OptionInstance.noTooltip(),
                options().advanced.piglinAwakenerPiglinCount
        );
    }

    public static OptionInstance<Integer> fullbrightAmount() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.fullbright_amount",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fullbright_amount.tooltip")),
                clientOptions().client.fullbrightAmount
        );
    }

    public static OptionInstance<Integer> icarusFireworksInventorySlot() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.icarus_fireworks_inventory_slot",
                OptionInstance.noTooltip(),
                clientOptions().client.iCarusFireworksInventorySlot,
                (optionText, value) -> {
                    if (value < 10) {
                        return Options.genericValueLabel(optionText, Component.literal("Hotbar Slot ").withStyle(ChatFormatting.AQUA)
                                .copy().append(Component.literal(Integer.toString(value)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.AQUA)));
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal("Slot ").withStyle(ChatFormatting.AQUA)
                                .copy().append(Component.literal(Integer.toString(value)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.AQUA)));
                    }
                }
        );
    }

    public static OptionInstance<Integer> infiniPearlInventorySlot() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.infini_pearl_inventory_slot",
                OptionInstance.noTooltip(),
                clientOptions().client.infiniPearlInventorySlot,
                (optionText, value) -> {
                    if (value < 10) {
                        return Options.genericValueLabel(optionText, Component.literal("Hotbar Slot ").withStyle(ChatFormatting.AQUA)
                                .copy().append(Component.literal(Integer.toString(value)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.AQUA)));
                    } else {
                        return Options.genericValueLabel(optionText, Component.literal("Slot ").withStyle(ChatFormatting.AQUA)
                                .copy().append(Component.literal(Integer.toString(value)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.AQUA)));
                    }
                }
        );
    }

    public static OptionInstance<Integer> fireballExplosionPower() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.fireball_explosion_power",
                OptionInstance.noTooltip(),
                options().advanced.fireballExplosionPower
        );
    }

    public static OptionInstance<Integer> annulEyeSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.annul_eye_search_radius",
                OptionInstance.noTooltip(),
                options().advanced.annulEyeSearchRadius,
                100,
                200,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> piglinAwakenerSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.piglin_awakener_search_radius",
                OptionInstance.noTooltip(),
                options().advanced.piglinAwakenerSearchRadius,
                100,
                300,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> blazeSpotterSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.blaze_spotter_search_radius",
                OptionInstance.noTooltip(),
                options().advanced.blazeSpotterSearchRadius,
                50,
                300,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> raidEradicatorSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.raid_eradicator_search_radius",
                OptionInstance.noTooltip(),
                options().advanced.raidEradicatorSearchRadius,
                100,
                400,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> dragonsPearlSearchRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragons_pearl_search_radius",
                OptionInstance.noTooltip(),
                options().advanced.dragonsPearlSearchRadius,
                100,
                350,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> dragonMassKillRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_mass_kill_radius",
                OptionInstance.cachedConstantTooltip(ModListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.dragon_mass_kill_radius.tooltip"))),
                options().advanced.dragonMassKillRadius,
                100,
                300,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> dragonImmunityDetectionRadiusForGoliath(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_immunity_detection_radius_for_goliath",
                OptionInstance.noTooltip(),
                options().advanced.dragonImmunityDetectionRadiusForGoliath,
                100,
                300,
                x,
                y,
                z
        );
    }

    public static OptionInstance<Integer> dragonImmunityDetectionRadiusForWither(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.dragon_immunity_detection_radius_for_wither",
                OptionInstance.noTooltip(),
                options().advanced.dragonImmunityDetectionRadiusForWither,
                100,
                350,
                x,
                y,
                z
        );
    }

    /**
     * Creates a new {@code simple boolean option.}
     */
    private static OptionInstance<Boolean> createSimpleBooleanOption(String key, OptionInstance.TooltipSupplier<Boolean> tooltip, OptionValue<Boolean> option) {
        return new OptionInstance<>(key, tooltip,
                (optionText, value) -> !value ? ModTexts.OFF : ModTexts.ON,
                OptionInstance.BOOLEAN_VALUES,
                option.getCurrentValue(),
                option::set);
    }

    /**
     * Creates a new {@code simple boolean option.} with different text displayers based on other options.
     * @param bl the list of booleans to be checked if false, and if one of them are false, return {@code displayer} as the <b>displayer text,</b> and {@code displayerTooltip} as the tooltip.
     */
    private static OptionInstance<Boolean> createSimpleBooleanOption(String key, boolean[] bl, OptionValue<Boolean> option) throws IllegalArgumentException {
        return new OptionInstance<>(key, OptionInstance.noTooltip(),
                (optionText, value) -> {
                    for (boolean b : bl) {
                        if (!b) {
                            return ModTexts.OFF;
                        }
                    }
                    return !value ? ModTexts.OFF : ModTexts.ON;
                },
                OptionInstance.BOOLEAN_VALUES,
                option.getCurrentValue(),
                option::set);
    }

    /**
     * Creates a new {@code simple boolean option} with different text displayers.
     */
    private static OptionInstance<Boolean> createSimpleBooleanOptionWithCustomSwitch(String key, OptionInstance.TooltipSupplier<Boolean> tooltip, OptionValue<Boolean> option, boolean question) {
        return new OptionInstance<>(key, tooltip,
                (optionText, value) -> !value ? question ? ModTexts.NO : ModTexts.DISABLED : question ? ModTexts.YES : ModTexts.ENABLED,
                OptionInstance.BOOLEAN_VALUES,
                option.getCurrentValue(),
                option::set);
    }

    /**
     * Creates a new {@code simple integer option.}
     */
    private static OptionInstance<Integer> createSimpleIntegerOption(String key, OptionInstance.TooltipSupplier<Integer> tooltip, IntegerOptionValue option) {
        return new OptionInstance<>(key, tooltip,
                ModListOptions::getGenericValueText,
                new OptionInstance.IntRange(option.getMinValue(), option.getMaxValue()), option.getCurrentValue(), option::set);
    }

    /**
     * Creates a new {@code simple integer option} custom a custom {@code text supplier.}
     */
    private static OptionInstance<Integer> createSimpleIntegerOption(String key, OptionInstance.TooltipSupplier<Integer> tooltip, IntegerOptionValue option, BiFunction<Component, Integer, Component> formatter) {
        return new OptionInstance<>(key, tooltip,
                formatter::apply,
                new OptionInstance.IntRange(option.getMinValue(), option.getMaxValue()), option.getCurrentValue(), option::set);
    }

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static OptionInstance<Integer> createStructureSpawnRateOption(String structure) {
        return new OptionInstance<>("speedrunnermod.options.structure_spawn_rates." + structure, isSsrCustom() ? OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip")) : OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.structure_spawn_rates.custom_required")),
                (optionText, value) -> ModListOptions.listIntegerText(optionText, structure),
                new OptionInstance.IntRange(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * Creates an {@code integer list option.}
     */
    private static OptionInstance<Integer> ofIntegerList(String key, OptionInstance.TooltipSupplier<Integer> tooltip, OptionValue<List<Integer>> option, int min, int max, boolean x, boolean y, boolean z) {
        return new OptionInstance<>(key, tooltip,
                (optionText, value) -> ModListOptions.listIntegerText(optionText,
                        option,
                        x,
                        y,
                        z
                ),
                new OptionInstance.IntRange(min, max),
                x ? option.getCurrentValue().get(0) :
                        y ? option.getCurrentValue().get(1) :
                                option.getCurrentValue().get(2),
                value -> setValue(option.getCurrentValue(), value, x, y, z));
    }

    /**
     * Bounds the value of the {@link OptionInstance} to the {@code -X, -Y, -Z, X, Y and Z}.
     */
    private static void setValue(List<Integer> option, int value, boolean x, boolean y, boolean z) {
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

    /**
     * @return the text for {@code list integer options}, with {@code x, y, and z} values.
     */
    private static Component listIntegerText(Component prefix, OptionValue<List<Integer>> value, boolean x, boolean y, boolean z) {
        boolean all = !x && !y && !z;
        Component xText = Component.literal("X: " + value.getCurrentValue().get(0)).withStyle(x || all ? ChatFormatting.GREEN : ChatFormatting.GRAY);
        Component yText = Component.literal("Y: " + value.getCurrentValue().get(1)).withStyle(y || all ? ChatFormatting.GREEN : ChatFormatting.GRAY);
        Component zText = Component.literal("Z: " + value.getCurrentValue().get(2)).withStyle(z || all ? ChatFormatting.GREEN : ChatFormatting.GRAY);
        Component comma = Component.literal(", ").withStyle(ChatFormatting.WHITE);
        return Options.genericValueLabel(prefix, xText.copy().append(comma).append(yText).append(comma).append(zText));
    }

    /**
     * @return the {@code tooltip} to display, along with the {@code list options control} tooltip.
     */
    public static Component listIntegerTooltip(Component tooltip) {
        return tooltip.copy().append("\n\n").append(Component.translatable("speedrunnermod.options.list_options_control"));
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
     * Bounds the value of the {@link OptionInstance} to the {@code spacing value} of the structure, and then sets the separate value to that divided by 2.
     */
    private static void setValue(List<Integer> option, int value) {
        option.set(0, value);
        option.set(1, option.getFirst() / 2);
    }

    /**
     * Returns the text that should be displayed on the {@link OptionInstance}.
     */
    private static Component listIntegerText(Component prefix, String structure) {
        switch (structure) {
            case TranslationStringKeys.ANCIENT_CITY -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.ancientCities.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.ancientCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.DESERT_PYRAMID -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.desertPyramids.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.desertPyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.JUNGLE_PYRAMID -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.junglePyramids.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.junglePyramids.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.PILLAGER_OUTPOST -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.pillagerOutposts.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.pillagerOutposts.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.END_CITY -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.endCities.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.endCities.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.WOODLAND_MANSION -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.woodlandMansions.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.woodlandMansions.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.RUINED_PORTAL -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.ruinedPortals.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.ruinedPortals.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.SHIPWRECK -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.shipwrecks.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.shipwrecks.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.TRIAL_CHAMBER -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.trialChambers.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.trialChambers.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.NETHER_COMPLEXES -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.netherComplexes.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.netherComplexes.getCurrentValue().get(1)));
            }
            default -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.villages.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.villages.getCurrentValue().get(1)));
            }
        }
    }

    /**
     * Returns the {@code default spacing value} that the respective {@link OptionInstance} should return when loading into the game.
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
    public static Component structureSpawnRateTooltip() {
        Component structureSpawnRate;
        switch (options().main.structureSpawnRates.getCurrentValue()) {
            case EVERYWHERE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.everywhere.tooltip");
            case VERY_COMMON -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.very_common.tooltip");
            case COMMON -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.common.tooltip");
            case NORMAL -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.normal.tooltip");
            case RARE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.rare.tooltip");
            case VERY_RARE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.very_rare.tooltip");
            case CUSTOM -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.custom.tooltip");
            default -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.default.tooltip");
        }
        return Component.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")
                .copy()
                .append("\n\n")
                .append(structureSpawnRate);
    }

    /**
     * @return the generic value text prefix, with aqua formatting.
     */
    private static Component getGenericValueText(Component prefix, int value) {
        return Options.genericValueLabel(prefix, Component.literal(Integer.toString(value)).withStyle(ChatFormatting.AQUA));
    }
}