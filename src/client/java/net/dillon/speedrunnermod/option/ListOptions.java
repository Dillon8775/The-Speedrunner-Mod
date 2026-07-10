package net.dillon.speedrunnermod.option;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.util.TranslationStringKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * All {@code "list"} options, which are used on the actual options screens to allow changing of these options.
 */
public class ListOptions {

    public static OptionInstance<Mode> mode() {
        return new OptionInstance<>("speedrunnermod.options.mode", OptionInstance.noTooltip(), (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(Mode.values()), Codec.INT.xmap(Mode::byId, Mode::getId)),
                options().general.mode.getCurrentValue(), value -> options().general.mode.set(value));
    }

    public static OptionInstance<StructureSpawnRate> structureSpawnRate() {
        return new OptionInstance<>("speedrunnermod.options.structure_spawn_rates", OptionInstance.noTooltip(), (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(StructureSpawnRate.values()), Codec.INT.xmap(StructureSpawnRate::byId, StructureSpawnRate::getId)),
                options().worldGen.structureSpawnRates.getCurrentValue(), value -> options().worldGen.structureSpawnRates.set(value));
    }

    public static OptionInstance<ItemMessages> itemMessages() {
        return new OptionInstance<>("speedrunnermod.options.item_messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.item_messages.tooltip")),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(ItemMessages.values()),
                        Codec.INT.xmap(ItemMessages::byId, ItemMessages::getId)),
                clientOptions().client.itemMessages.getCurrentValue(),
                value -> clientOptions().client.itemMessages.set(value));
    }

    public static OptionInstance<CreatureSpawnRate> creatureSpawningRate() {
        return new OptionInstance<>("speedrunnermod.options.creature_spawn_rate",
                OptionInstance.cachedConstantTooltip(ofWorldReload(Component.translatable("speedrunnermod.options.creature_spawn_rate.tooltip"))),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(CreatureSpawnRate.values()),
                        Codec.INT.xmap(CreatureSpawnRate::byId, CreatureSpawnRate::getId)),
                options().worldGen.creatureSpawnRate.getCurrentValue(),
                value -> options().worldGen.creatureSpawnRate.set(value));
    }

    public static OptionInstance<GameMode> gameMode() {
        return new OptionInstance<>("speedrunnermod.options.gamemode",
                OptionInstance.noTooltip(),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(GameMode.values()),
                        Codec.INT.xmap(GameMode::byId, GameMode::getId)),
                clientOptions().client.gameMode.getCurrentValue(),
                value -> clientOptions().client.gameMode.set(value));
    }

    public static OptionInstance<Difficulty> difficulty() {
        return new OptionInstance<>("speedrunnermod.options.difficulty",
                OptionInstance.noTooltip(),
                (optionText, value) -> value.getText(),
                new OptionInstance.Enum<>(Arrays.asList(Difficulty.values()),
                        Codec.INT.xmap(Difficulty::byId, Difficulty::getId)),
                isDoomMode() ? Difficulty.HARD : clientOptions().client.difficulty.getCurrentValue(),
                value -> clientOptions().client.difficulty.set(value));
    }

    public static OptionInstance<Boolean> warningMessages() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.warning_messages",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.warning_messages.tooltip")),
                clientOptions().client.warningMessages
        );
    }

    public static OptionInstance<Boolean> fasterBlockBreaking() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_block_breaking",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_block_breaking.tooltip")),
                options().general.fasterBlockBreaking
        );
    }

    public static OptionInstance<Boolean> icarusMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.icarus_mode",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.icarus_mode.tooltip")),
                options().general.iCarusMode
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
                    Minecraft.getInstance().levelExtractor.allChanged();
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
                options().general.infiniPearlMode
        );
    }

    @Deprecated
    public static OptionInstance<Boolean> leaderboardsMode() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.leaderboards_mode",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.leaderboards_mode.tooltip")),
                options().general.leaderboardsMode
        );
    }

    public static OptionInstance<Boolean> killGhastOnFireball() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kill_ghast_on_fireball",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.kill_ghast_on_fireball.tooltip")),
                options().general.killGhastOnFireball
        );
    }

    public static OptionInstance<Boolean> fasterSpawners() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_spawners",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_spawners.tooltip")),
                options().general.fasterSpawners
        );
    }

    public static OptionInstance<Boolean> fasterSmelting() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_smelting",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_smelting.tooltip")),
                options().general.fasterSmelting
        );
    }

    public static OptionInstance<Boolean> fasterBrewing() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.faster_brewing",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.faster_brewing.tooltip")),
                options().general.fasterBrewing
        );
    }

    public static OptionInstance<Boolean> betterBiomes() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_biomes",
                OptionInstance.cachedConstantTooltip(ofRestartable(Component.translatable("speedrunnermod.options.better_biomes.tooltip"))),
                options().worldGen.betterBiomes
        );
    }

    public static OptionInstance<Boolean> generateSpeedrunnersWasteland() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.generate_speedrunners_wasteland",
                OptionInstance.cachedConstantTooltip(ofRestartable(Component.translatable("speedrunnermod.options.generate_speedrunners_wasteland.tooltip"))),
                options().worldGen.generateSpeedrunnersWasteland
        );
    }

    public static OptionInstance<Boolean> generateSpeedrunnerWood() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.generate_speedrunner_wood",
                OptionInstance.cachedConstantTooltip(ofRestartable(Component.translatable("speedrunnermod.options.generate_speedrunner_wood.tooltip"))),
                options().worldGen.generateSpeedrunnerWood
        );
    }

    public static OptionInstance<Boolean> commonPlainTrees() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.common_plain_trees",
                OptionInstance.cachedConstantTooltip(ofWorldReload(Component.translatable("speedrunnermod.options.common_plain_trees.tooltip"))),
                options().worldGen.commonPlainTrees
        );
    }

    public static OptionInstance<Boolean> commonOres() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.common_ores",
                OptionInstance.cachedConstantTooltip(ofWorldReload(Component.translatable("speedrunnermod.options.common_ores.tooltip"))),
                options().worldGen.commonOres
        );
    }

    public static OptionInstance<Boolean> netherWater() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.nether_water",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.nether_water.tooltip")),
                options().worldGen.netherWater
        );
    }

    public static OptionInstance<Boolean> betterFoods() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_foods",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.better_foods.tooltip")),
                options().general.betterFoods
        );
    }

    public static OptionInstance<Boolean> fallDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fall_damage",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fall_damage.tooltip")),
                options().general.fallDamage
        );
    }

    public static OptionInstance<Boolean> arrowsDestroyBeds() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.arrows_destroy_beds",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.arrows_destroy_beds.tooltip")),
                options().worldGen.arrowsDestroyBeds
        );
    }

    public static OptionInstance<Boolean> globalNetherPortals() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.global_nether_portals",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.global_nether_portals.tooltip")),
                options().worldGen.globalNetherPortals
        );
    }

    public static OptionInstance<Boolean> betterAnvil() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.better_anvil",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.better_anvil.tooltip")),
                options().general.betterAnvil
        );
    }

    public static OptionInstance<Boolean> higherEnchantmentLevels() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.higher_enchantment_levels",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.higher_enchantment_levels.tooltip")),
                options().general.higherEnchantmentLevels
        );
    }

    public static OptionInstance<Boolean> rightClickToRemoveSilkTouch() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.right_click_to_remove_silk_touch",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.tooltip")),
                options().general.rightClickToRemoveSilkTouch
        );
    }

    public static OptionInstance<Boolean> showDeathCords() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.show_death_cords",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.show_death_cords.tooltip")),
                options().general.showDeathCords
        );
    }

    public static OptionInstance<Boolean> kineticDamage() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.kinetic_damage",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.kinetic_damage.tooltip")),
                options().general.kineticDamage
        );
    }

    public static OptionInstance<Boolean> throwableFireballs() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.throwable_fireballs",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.throwable_fireballs.tooltip")),
                options().general.throwableFireballs
        );
    }

    public static OptionInstance<Boolean> instantWorldCreation() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.fast_world_creation",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.fast_world_creation.tooltip")),
                clientOptions().client.instantWorldCreation
        );
    }

    public static OptionInstance<Boolean> allowCommands() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.allow_commands",
                OptionInstance.noTooltip(),
                clientOptions().client.allowCommands
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

    public static OptionInstance<Boolean> increasedOxygen() {
        return createSimpleBooleanOption(
                "speedrunnermod.options.increased_oxygen",
                OptionInstance.noTooltip(),
                options().advanced.increasedOxygen
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

    public static OptionInstance<Boolean> viewFeatures() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.view_features",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.view_features.tooltip")),
                clientOptions().storedValues.viewFeatures,
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

    public static OptionInstance<Boolean> itemStackMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.item_stack_mixin",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.item_stack_mixin.tooltip")),
                options().mixins.itemStackMixin,
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

    public static OptionInstance<Boolean> abstractClientPlayerMixin() {
        return createSimpleBooleanOptionWithCustomSwitch(
                "speedrunnermod.options.abstract_client_player_mixin",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.abstract_client_player_mixin.tooltip")),
                clientOptions().mixins.abstractClientPlayerMixin,
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
                options().general.blockBreakingMultiplier,
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
                options().general.dragonPerchTime,
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
                options().worldGen.strongholdDistance
        );
    }

    public static OptionInstance<Integer> strongholdSpread() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.stronghold_spread",
                OptionInstance.noTooltip(),
                options().worldGen.strongholdSpread
        );
    }

    public static OptionInstance<Integer> totalStrongholds() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.total_strongholds",
                OptionInstance.noTooltip(),
                options().worldGen.totalStrongholds
        );
    }

    public static OptionInstance<Integer> totalPortalRooms() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.total_portal_rooms",
                OptionInstance.noTooltip(),
                options().worldGen.totalPortamRooms
        );
    }

    public static OptionInstance<Integer> totalLibraries() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.total_libraries",
                OptionInstance.noTooltip(),
                options().worldGen.totalLibraries
        );
    }

    public static OptionInstance<Integer> netherPortalDelay() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.nether_portal_delay",
                OptionInstance.cachedConstantTooltip(Component.translatable("speedrunnermod.options.nether_portal_delay.tooltip")),
                options().worldGen.netherPortalDelay,
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
                options().general.anvilCostLimit,
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
                clientOptions().client.fullBrightAmount
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
                options().general.fireballExplosionPower
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
                OptionInstance.cachedConstantTooltip(ListOptions.listIntegerTooltip(Component.translatable("speedrunnermod.options.dragon_mass_kill_radius.tooltip"))),
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

    public static OptionInstance<Integer> goliathAndZombieEntityDetectionRadius(boolean x, boolean y, boolean z) {
        return ofIntegerList(
                "speedrunnermod.options.goliath_and_zombie_entity_detection_radius",
                OptionInstance.cachedConstantTooltip(listIntegerTooltip(Component.translatable("speedrunnermod.options.goliath_and_zombie_entity_detection_radius.tooltip"))),
                options().advanced.goliathAndZombieEntityDetectionRadius,
                50,
                400,
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
                ListOptions::getGenericValueText,
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
     * Creates a new {@code mineshaft option}.
     */
    public static OptionInstance<Integer> createMineshaftFrequencyOption() {
        return createSimpleIntegerOption(
                "speedrunnermod.options.structure_spawn_rates.mineshafts",
                OptionInstance.cachedConstantTooltip(ofWorldReload(Component.translatable("speedrunnermod.options.structure_spawn_rates.mineshafts.tooltip"))),
                options().customStructureSpawnRates.mineshafts,
                (optionText, value) -> {
                    return Options.genericValueLabel(optionText, Component.literal(value + "%"));
                });
    }

    /**
     * Creates a new {@code Structure Spawn Rate option.}
     */
    public static OptionInstance<Integer> createStructureSpawnRateOption(String structure) {
        return new OptionInstance<>("speedrunnermod.options.structure_spawn_rates." + structure,
                OptionInstance.cachedConstantTooltip(ofWorldReload(Component.translatable("speedrunnermod.options.structure_spawn_rates_description.tooltip"))),
                (optionText, value) -> ListOptions.listIntegerText(optionText, structure),
                new OptionInstance.IntRange(3, 24), defaultStructureValue(structure), value -> determineValue(structure, value));
    }

    /**
     * @return an option that requires a restart to take effect.
     */
    public static Component ofRestartable(Component component) {
        return component
                .copy().append("\n\n")
                .copy().append(Component.translatable("speedrunnermod.option.requires_restart"));
    }

    /**
     * @return an option that requires a world reload to take effect.
     */
    public static Component ofWorldReload(Component component) {
        return component
                .copy().append("\n\n")
                .copy().append(Component.translatable("speedrunnermod.option.requires_world_reload"));
    }

    /**
     * Creates an {@code integer list option.}
     */
    private static OptionInstance<Integer> ofIntegerList(String key, OptionInstance.TooltipSupplier<Integer> tooltip, OptionValue<List<Integer>> option, int min, int max, boolean x, boolean y, boolean z) {
        return new OptionInstance<>(key, tooltip,
                (optionText, value) -> ListOptions.listIntegerText(optionText,
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
            case TranslationStringKeys.IGLOO -> setValue(options().customStructureSpawnRates.igloos.getCurrentValue(), value);
            case TranslationStringKeys.OCEAN_RUIN -> setValue(options().customStructureSpawnRates.oceanRuins.getCurrentValue(), value);
            case TranslationStringKeys.SWAMP_HUT -> setValue(options().customStructureSpawnRates.swampHuts.getCurrentValue(), value);
            case TranslationStringKeys.END_CITY -> setValue(options().customStructureSpawnRates.endCities.getCurrentValue(), value);
            case TranslationStringKeys.WOODLAND_MANSION -> setValue(options().customStructureSpawnRates.woodlandMansions.getCurrentValue(), value);
            case TranslationStringKeys.RUINED_PORTAL -> setValue(options().customStructureSpawnRates.ruinedPortals.getCurrentValue(), value);
            case TranslationStringKeys.SHIPWRECK -> setValue(options().customStructureSpawnRates.shipwrecks.getCurrentValue(), value);
            case TranslationStringKeys.TRIAL_CHAMBER -> setValue(options().customStructureSpawnRates.trialChambers.getCurrentValue(), value);
            case TranslationStringKeys.TRAIL_RUIN -> setValue(options().customStructureSpawnRates.trailRuins.getCurrentValue(), value);
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
            case TranslationStringKeys.IGLOO -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.igloos.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.igloos.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.OCEAN_RUIN -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.oceanRuins.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.oceanRuins.getCurrentValue().get(1)));
            }
            case TranslationStringKeys.SWAMP_HUT -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.swampHuts.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.swampHuts.getCurrentValue().get(1)));
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
            case TranslationStringKeys.TRAIL_RUIN -> {
                return Options.genericValueLabel(prefix, Component.literal(options().customStructureSpawnRates.trailRuins.getCurrentValue().getFirst() + ", " + options().customStructureSpawnRates.trailRuins.getCurrentValue().get(1)));
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
            case TranslationStringKeys.IGLOO -> {
                return options().customStructureSpawnRates.igloos.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.OCEAN_RUIN -> {
                return options().customStructureSpawnRates.oceanRuins.getCurrentValue().getFirst();
            }
            case TranslationStringKeys.SWAMP_HUT -> {
                return options().customStructureSpawnRates.swampHuts.getCurrentValue().getFirst();
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
            case TranslationStringKeys.TRAIL_RUIN -> {
                return options().customStructureSpawnRates.trailRuins.getCurrentValue().getFirst();
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
        switch (options().worldGen.structureSpawnRates.getCurrentValue()) {
            case EVERYWHERE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.everywhere.tooltip");
            case VERY_COMMON -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.very_common.tooltip");
            case COMMON -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.common.tooltip");
            case NORMAL -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.normal.tooltip");
            case RARE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.rare.tooltip");
            case VERY_RARE -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.very_rare.tooltip");
            case CUSTOM -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.custom.tooltip");
            default -> structureSpawnRate = Component.translatable("speedrunnermod.options.structure_spawn_rates.default.tooltip");
        }
        return ofWorldReload(Component.translatable("speedrunnermod.options.structure_spawn_rates.tooltip")
                .copy()
                .append("\n\n")
                .append(structureSpawnRate));
    }

    /**
     * @return the generic value text prefix, with aqua formatting.
     */
    private static Component getGenericValueText(Component prefix, int value) {
        return Options.genericValueLabel(prefix, Component.literal(Integer.toString(value)).withStyle(ChatFormatting.AQUA));
    }
}