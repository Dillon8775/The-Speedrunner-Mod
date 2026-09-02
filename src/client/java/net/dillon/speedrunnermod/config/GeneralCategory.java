package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.isOnServer;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * The general options category for the {@link ConfigurationScreen}.
 */
public class GeneralCategory {

    protected static ConfigCategory create() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("speedrunnermod.options.title.general"))
                .tooltip(Component.translatable("speedrunnermod.options.general.tooltip"))
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.core"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.core.description")))
                                .option(
                                        Option.<Mode>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.mode"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.mode.description")))
                                                .binding(Mode.EASY, () -> common().general().mode, v -> common().general().mode = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(Mode.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .available(!isOnServer())
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.icarus_mode"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.icarus_mode.description")))
                                                .binding(false, () -> common().general().iCarusMode, v -> common().general().iCarusMode = v)
                                                .controller(BooleanControllerBuilder::create)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.infini_pearl_mode"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.infini_pearl_mode.description")))
                                                .binding(false, () -> common().general().infiniPearlMode, v -> common().general().infiniPearlMode = v)
                                                .controller(BooleanControllerBuilder::create)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.player_friendly"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.player_friendly.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.better_foods"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.better_foods.description")))
                                                .binding(true, () -> common().general().betterFoods, v -> common().general().betterFoods = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fall_damage"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fall_damage.description")))
                                                .binding(true, () -> common().general().fallDamage, v -> common().general().fallDamage = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.kinetic_damage"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.kinetic_damage.description")))
                                                .binding(true, () -> common().general().kineticDamage, v -> common().general().kineticDamage = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.increased_oxygen"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.increased_oxygen.description")))
                                                .binding(true, () -> common().general().increasedOxygen, v -> common().general().increasedOxygen = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.show_death_cords"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.show_death_cords.description")))
                                                .binding(true, () -> common().general().showDeathCords, v -> common().general().showDeathCords = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.right_click_to_remove_silk_touch"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.right_click_to_remove_silk_touch.description")))
                                                .binding(true, () -> common().general().rightClickToRemoveSilkTouch, v -> common().general().rightClickToRemoveSilkTouch = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.blocks"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.blocks.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.faster_block_breaking"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.faster_block_breaking.description")))
                                                .binding(true, () -> common().general().fasterBlockBreaking, v -> common().general().fasterBlockBreaking = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.block_breaking_multiplier"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.block_breaking_multiplier.description")))
                                                .binding(1, () -> common().general().blockBreakingMultiplier, v -> common().general().blockBreakingMultiplier = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 3)
                                                        .step(1)
                                                        .formatValue(v -> v == 1 ? Component.literal("§7Default") : Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.faster_spawners"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.faster_spawners.description")))
                                                .binding(true, () -> common().general().fasterSpawners, v -> common().general().fasterSpawners = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.faster_smelting"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.faster_smelting.description")))
                                                .binding(true, () -> common().general().fasterSmelting, v -> common().general().fasterSmelting = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.faster_brewing"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.faster_brewing.description")))
                                                .binding(true, () -> common().general().fasterBrewing, v -> common().general().fasterBrewing = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.anvil"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.anvil.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.better_anvil"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.better_anvil.description")))
                                                .binding(true, () -> common().general().betterAnvil, v -> common().general().betterAnvil = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.anvil_cost_limit"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.anvil_cost_limit.description")))
                                                .binding(10, () -> common().general().anvilCostLimit, v -> common().general().anvilCostLimit = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 50)
                                                        .step(1)
                                                        .formatValue(v -> v == 50 ? Component.literal("No Limit")
                                                                : v == 1 ? Component.literal(v + " level") :
                                                                Component.literal(v + " levels"))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.higher_enchantment_levels"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.higher_enchantment_levels.description")))
                                                .binding(true, () -> common().general().higherEnchantmentLevels, v -> common().general().higherEnchantmentLevels = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.other"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.other.description")))
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.dragon_perch_time"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.dragon_perch_time.description")))
                                                .binding(8, () -> common().general().dragonPerchTime, v -> common().general().dragonPerchTime = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(8, 90)
                                                        .step(1)
                                                        .formatValue(v -> v < 9 ? Component.literal("§7OFF") : Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.throwable_fireballs"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.throwable_fireballs.description")))
                                                .binding(true, () -> common().general().throwableFireballs, v -> common().general().throwableFireballs = v)
                                                .controller(BooleanControllerBuilder::create)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fireball_explosion_power"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fireball_explosion_power.description")))
                                                .binding(2, () -> common().general().fireballExplosionPower, v -> common().general().fireballExplosionPower = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 10)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.kill_ghast_on_fireball"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.kill_ghast_on_fireball.description")))
                                                .binding(false, () -> common().general().killGhastOnFireball, v -> common().general().killGhastOnFireball = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}