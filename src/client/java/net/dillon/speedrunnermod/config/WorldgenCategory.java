package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * The world gen options category for the {@link ConfigurationScreen}.
 */
public class WorldgenCategory {

    protected static ConfigCategory create() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("speedrunnermod.options.title.worldgen"))
                .tooltip(Component.translatable("speedrunnermod.options.worldgen.tooltip"))
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.mod_generation"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.mod_generation.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.make_structures_more_common"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.make_structures_more_common.description")))
                                                .binding(true, () -> common().worldgen().makeStructuresMoreCommon, v -> common().worldgen().makeStructuresMoreCommon = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.generate_speedrunners_wasteland"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.generate_speedrunners_wasteland.description")))
                                                .binding(true, () -> common().worldgen().generateSpeedrunnersWasteland, v -> common().worldgen().generateSpeedrunnersWasteland = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.generate_speedrunner_wood"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.generate_speedrunner_wood.description")))
                                                .binding(true, () -> common().worldgen().generateSpeedrunnerWood, v -> common().worldgen().generateSpeedrunnerWood = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.better_biomes"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.better_biomes.description")))
                                                .binding(true, () -> common().worldgen().betterBiomes, v -> common().worldgen().betterBiomes = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .flag(OptionFlag.GAME_RESTART)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.common_ores"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.common_ores.description")))
                                                .binding(true, () -> common().worldgen().commonOres, v -> common().worldgen().commonOres = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.common_plain_trees"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.common_plain_trees.description")))
                                                .binding(true, () -> common().worldgen().commonPlainTrees, v -> common().worldgen().commonPlainTrees = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
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
                                                .binding(true, () -> common().worldgen().netherWater, v -> common().worldgen().netherWater = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.global_nether_portals"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.global_nether_portals.description")))
                                                .binding(true, () -> common().worldgen().globalNetherPortals, v -> common().worldgen().globalNetherPortals = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.nether_portal_delay"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.nether_portal_delay.description")))
                                                .binding(2, () -> common().worldgen().netherPortalDelay, v -> common().worldgen().netherPortalDelay = v)
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
                                                .binding(true, () -> common().worldgen().arrowsDestroyBeds, v -> common().worldgen().arrowsDestroyBeds = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}