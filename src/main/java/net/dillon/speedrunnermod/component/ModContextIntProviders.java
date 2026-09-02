package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;

import static net.dillon.dillonlib.util.Arithmetics.S_asTick;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.mixin.accessor.ContextIntProvidersInvoker.invokeCooking;
import static net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders.COOKING_FAST_BURN_TIME_REDUCTION_FACTOR;
import static net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders.COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR;

/**
 * All speedrunner mod int providers.
 */
public class ModContextIntProviders {
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_SPEEDRUNNER_ITEMS = createIntProvider("speedrunner_items");
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS = createIntProvider("dead_speedrunner_items");

    /**
     * Bootstraps mod int providers.
     */
    public static void bootstrap(final BootstrapContext<ContextIntProvider> context) {
        HolderGetter<LootItemCondition> predicates = context.lookup(Registries.PREDICATE);
        Holder.Reference<ContextIntProvider> normalBurnTime = context.register(COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(1));
        Holder.Reference<ContextIntProvider> fastBurnTime = context.register(COOKING_FAST_BURN_TIME_REDUCTION_FACTOR, new ConstantValue(2));

        context.register(COOKING_TIME_SPEEDRUNNER_ITEMS, invokeCooking(predicates, normalBurnTime, fastBurnTime, S_asTick(20)));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS, invokeCooking(predicates, normalBurnTime, fastBurnTime, S_asTick(10)));
    }

    /**
     * Creates a new speedrunner mod number provider.
     */
    private static ResourceKey<ContextIntProvider> createIntProvider(final String location) {
        return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, ofSpeedrunnerMod(location));
    }

    /**
     * Initializes all speedrunner mod int providers.
     */
    public static void initializeIntProviders() {
        SpeedrunnerMod.LOGGER.debug("Initialized int providers.");
    }
}