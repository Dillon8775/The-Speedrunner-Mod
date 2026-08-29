package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.mixin.accessor.NumberProvidersInvoker.invokeCooking;
import static net.minecraft.world.level.storage.loot.providers.number.NumberProviders.COOKING_FAST_BURN_TIME_MULTIPLIER;
import static net.minecraft.world.level.storage.loot.providers.number.NumberProviders.COOKING_NORMAL_BURN_TIME_MULTIPLIER;

/**
 * All speedrunner mod number providers.
 */
public class ModNumberProviders {
    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_LOG = createNumberProvider("speedrunner_log");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_LOG = createNumberProvider("dead_speedrunner_log");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_WOOD = createNumberProvider("speedrunner_wood");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_WOOD = createNumberProvider("dead_speedrunner_wood");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_SAPLING = createNumberProvider("speedrunner_sapling");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_SAPLING = createNumberProvider("dead_speedrunner_sapling");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_BUSH = createNumberProvider("dead_speedrunner_bush");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_STICK = createNumberProvider("speedrunner_stick");
    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_PLANKS = createNumberProvider("speedrunner_planks");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_PLANKS = createNumberProvider("dead_speedrunner_planks");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_BOAT = createNumberProvider("speedrunner_boat");
    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_CHEST_BOAT = createNumberProvider("speedrunner_chest_boat");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_BOAT = createNumberProvider("dead_speedrunner_boat");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_CHEST_BOAT = createNumberProvider("dead_speedrunner_chest_boat");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_SLAB = createNumberProvider("speedrunner_slab");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_SLAB = createNumberProvider("dead_speedrunner_slab");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_STAIRS = createNumberProvider("speedrunner_stairs");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_STAIRS = createNumberProvider("dead_speedrunner_stairs");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_TRAPDOOR = createNumberProvider("speedrunner_trapdoor");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_TRAPDOOR = createNumberProvider("dead_speedrunner_trapdoor");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_DOOR = createNumberProvider("speedrunner_door");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_DOOR = createNumberProvider("dead_speedrunner_door");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_PRESSURE_PLATE = createNumberProvider("speedrunner_pressure_plate");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_PRESSURE_PLATE = createNumberProvider("dead_speedrunner_pressure_plate");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_FENCE = createNumberProvider("speedrunner_fence");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_FENCE = createNumberProvider("dead_speedrunner_fence");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_FENCE_GATE = createNumberProvider("speedrunner_fence_gate");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_FENCE_GATE = createNumberProvider("dead_speedrunner_fence_gate");

    public static final ResourceKey<NumberProvider> COOKING_TIME_SPEEDRUNNER_BUTTON = createNumberProvider("speedrunner_button");
    public static final ResourceKey<NumberProvider> COOKING_TIME_DEAD_SPEEDRUNNER_BUTTON = createNumberProvider("dead_speedrunner_button");

    /**
     * Bootstraps mod number providers.
     */
    public static void bootstrap(final BootstrapContext<NumberProvider> context) {
        HolderGetter<LootItemCondition> predicates = context.lookup(Registries.PREDICATE);
        Holder.Reference<NumberProvider> normalBurnTime = context.lookup(Registries.NUMBER_PROVIDER).getOrThrow(COOKING_NORMAL_BURN_TIME_MULTIPLIER);
        Holder.Reference<NumberProvider> fastBurnTime = context.lookup(Registries.NUMBER_PROVIDER).getOrThrow(COOKING_FAST_BURN_TIME_MULTIPLIER);

        context.register(COOKING_TIME_SPEEDRUNNER_LOG, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_LOG, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));
        context.register(COOKING_TIME_SPEEDRUNNER_WOOD, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_WOOD, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));

        context.register(COOKING_TIME_SPEEDRUNNER_SAPLING, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_SAPLING, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_BUSH, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_STICK, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_SPEEDRUNNER_PLANKS, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_PLANKS, invokeCooking(predicates, normalBurnTime, fastBurnTime, 400));

        context.register(COOKING_TIME_SPEEDRUNNER_BOAT, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_SPEEDRUNNER_CHEST_BOAT, invokeCooking(predicates, normalBurnTime, fastBurnTime, 300));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_BOAT, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_CHEST_BOAT, invokeCooking(predicates, normalBurnTime, fastBurnTime, 300));

        context.register(COOKING_TIME_SPEEDRUNNER_SLAB, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_SLAB, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_STAIRS, invokeCooking(predicates, normalBurnTime, fastBurnTime, 300));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_STAIRS, invokeCooking(predicates, normalBurnTime, fastBurnTime, 300));

        context.register(COOKING_TIME_SPEEDRUNNER_TRAPDOOR, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_TRAPDOOR, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_PRESSURE_PLATE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_PRESSURE_PLATE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_DOOR, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_DOOR, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_FENCE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_FENCE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_FENCE_GATE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_FENCE_GATE, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));

        context.register(COOKING_TIME_SPEEDRUNNER_BUTTON, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_BUTTON, invokeCooking(predicates, normalBurnTime, fastBurnTime, 200));
    }

    /**
     * Creates a new speedrunner mod number provider.
     */
    private static ResourceKey<NumberProvider> createNumberProvider(final String location) {
        return ResourceKey.create(Registries.NUMBER_PROVIDER, ofSpeedrunnerMod(location));
    }

    /**
     * Initializes all speedrunner mod number providers.
     */
    public static void initializeNumberProviders() {
        SpeedrunnerMod.debug("Initialized number providers.");
    }
}