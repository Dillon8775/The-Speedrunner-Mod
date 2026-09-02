package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * The accessibility options category for the {@link ConfigurationScreen}.
 */
public class AccessibilityCategory {

    protected static ConfigCategory create() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("speedrunnermod.options.title.accessibility"))
                .tooltip(Component.translatable("speedrunnermod.options.accessibility.tooltip"))
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.items"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.items.description")))
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.piglin_awakener_piglin_count"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.piglin_awakener_piglin_count.description")))
                                                .binding(10, () -> common().accessibility().piglinAwakenerPiglinCount, v -> common().accessibility().piglinAwakenerPiglinCount = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(3, 25)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.ender_eye_breaking_cooldown"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.ender_eye_breaking_cooldown.description")))
                                                .binding(4, () -> common().accessibility().enderEyeBreakingCooldown, v -> common().accessibility().enderEyeBreakingCooldown = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 10)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(v + "s"))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.shift_to_throw_fireball"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.shift_to_throw_fireball.description")))
                                                .binding(false, () -> common().accessibility().shiftToThrowFireball, v -> common().accessibility().shiftToThrowFireball = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.mobs"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.mobs.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.longer_dragon_perch_stay_time"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.longer_dragon_perch_stay_time.description")))
                                                .binding(true, () -> common().accessibility().longerDragonPerchStayTime, v -> common().accessibility().longerDragonPerchStayTime = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.dragon_kills_nearby_hostile_entities.description")))
                                                .binding(true, () -> common().accessibility().dragonKillsNearbyHostileEntities, v -> common().accessibility().dragonKillsNearbyHostileEntities = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.dragon_immunity_from_goliath_and_wither"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.dragon_immunity_from_goliath_and_wither.description")))
                                                .binding(true, () -> common().accessibility().dragonImmunityFromGoliathAndWither, v -> common().accessibility().dragonImmunityFromGoliathAndWither = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.decreased_zombified_piglin_scare_distance.description")))
                                                .binding(true, () -> common().accessibility().decreasedZombifiedPiglinScareDistance, v -> common().accessibility().decreasedZombifiedPiglinScareDistance = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.advanced_worldgen"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.advanced_worldgen.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.modified_stronghold_generation"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.modified_stronghold_generation.description")))
                                                .binding(true, () -> common().accessibility().modifiedStrongholdGeneration, v -> common().accessibility().modifiedStrongholdGeneration = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.modified_stronghold_y_generation"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.modified_stronghold_y_generation.description")))
                                                .binding(true, () -> common().accessibility().modifiedStrongholdYGeneration, v -> common().accessibility().modifiedStrongholdYGeneration = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.modified_nether_fortress_generation"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.modified_nether_fortress_generation.description")))
                                                .binding(true, () -> common().accessibility().modifiedNetherFortressGeneration, v -> common().accessibility().modifiedNetherFortressGeneration = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}