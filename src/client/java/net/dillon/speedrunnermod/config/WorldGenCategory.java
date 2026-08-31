package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * The world gen options category for the {@link ConfigurationScreen}.
 */
public class WorldGenCategory {

    protected static ConfigCategory create() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("speedrunnermod.options.title.worldgen"))
                .tooltip(Component.translatable("speedrunnermod.options.worldGen().tooltip"))
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.worldgen"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.worldGen().description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.generate_speedrunners_wasteland"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.generate_speedrunners_wasteland.description")))
                                                .binding(true, () -> common().worldGen().generateSpeedrunnersWasteland, v -> common().worldGen().generateSpeedrunnersWasteland = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.generate_speedrunner_wood"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.generate_speedrunner_wood.description")))
                                                .binding(true, () -> common().worldGen().generateSpeedrunnerWood, v -> common().worldGen().generateSpeedrunnerWood = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.better_biomes"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.better_biomes.description")))
                                                .binding(true, () -> common().worldGen().betterBiomes, v -> common().worldGen().betterBiomes = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.common_ores"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.common_ores.description")))
                                                .binding(true, () -> common().worldGen().commonOres, v -> common().worldGen().commonOres = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.common_plain_trees"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.common_plain_trees.description")))
                                                .binding(true, () -> common().worldGen().commonPlainTrees, v -> common().worldGen().commonPlainTrees = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.strongholds"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.strongholds.description")))
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.total_strongholds"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.total_strongholds.description")))
                                                .binding(128, () -> common().structureConfigs().stronghold.totalStrongholdsPerWorld, v -> common().structureConfigs().stronghold.totalStrongholdsPerWorld = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(4, 156)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.stronghold_distance"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.stronghold_distance.description")))
                                                .binding(4, () -> common().structureConfigs().stronghold.distance, v -> common().structureConfigs().stronghold.distance = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(3, 64)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.stronghold_spread"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.stronghold_spread.description")))
                                                .binding(3, () -> common().structureConfigs().stronghold.spread, v -> common().structureConfigs().stronghold.spread = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(2, 32)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.total_portal_rooms"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.total_portal_rooms.description")))
                                                .binding(3, () -> common().structureConfigs().stronghold.totalPortalRoomsPerWorld, v -> common().structureConfigs().stronghold.totalPortalRoomsPerWorld = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 3)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.total_libraries"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.total_libraries.description")))
                                                .binding(2, () -> common().structureConfigs().stronghold.totalLibrariesPerWorld, v -> common().structureConfigs().stronghold.totalLibrariesPerWorld = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 8)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.dimension_specific"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.dimension_specific.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.nether_water"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.nether_water.description")))
                                                .binding(true, () -> common().worldGen().netherWater, v -> common().worldGen().netherWater = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.global_nether_portals"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.global_nether_portals.description")))
                                                .binding(true, () -> common().worldGen().globalNetherPortals, v -> common().worldGen().globalNetherPortals = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.nether_portal_delay"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.nether_portal_delay.description")))
                                                .binding(2, () -> common().worldGen().netherPortalDelay, v -> common().worldGen().netherPortalDelay = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(-1, 5)
                                                        .step(1)
                                                        .formatValue(v -> v == -1 ? Component.literal("Go by Gamerule").withStyle(ChatFormatting.GREEN) :
                                                                v == 0 ? Component.literal("None").withStyle(ChatFormatting.RED) :
                                                                        Component.literal(v + "s"))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.arrows_destroy_beds"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.arrows_destroy_beds.description")))
                                                .binding(true, () -> common().worldGen().arrowsDestroyBeds, v -> common().worldGen().arrowsDestroyBeds = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}