package net.dillon.speedrunnermod.loot;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;

import java.util.Optional;

import static net.dillon.dillonlib.util.Arithmetics.S_asTick;
import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.mixin.accessor.ContextIntProvidersInvoker.invokeCooking;
import static net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders.COOKING_FAST_BURN_TIME_REDUCTION_FACTOR;
import static net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders.COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR;

/**
 * All speedrunner mod int providers.
 */
public class ModIntProviders {
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_SPEEDRUNNER_ITEMS = createIntProvider("speedrunner_items");
    public static final ResourceKey<ContextIntProvider> COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS = createIntProvider("dead_speedrunner_items");
    public static final ResourceKey<ContextIntProvider> ZOMBIE_FIREBALL_CHARGE_SPEED_ON_DOOM_MODE = createIntProvider("zombie_fireball_charge_speed_on_doom_mode");
    public static final ResourceKey<ContextIntProvider> ZOMBIE_FIREBALL_CHARGE_SPEED_DEFAULT = createIntProvider("zombie_fireball_charge_speed_default");

    /**
     * Bootstraps mod int providers.
     */
    public static void bootstrap(final BootstrapContext<ContextIntProvider> context) {
        HolderGetter<LootItemCondition> predicates = context.lookup(Registries.PREDICATE);
        Holder.Reference<ContextIntProvider> normalBurnTime = context.lookup(Registries.CONTEXT_INT_PROVIDER).getOrThrow(COOKING_NORMAL_BURN_TIME_REDUCTION_FACTOR);
        Holder.Reference<ContextIntProvider> fastBurnTime = context.lookup(Registries.CONTEXT_INT_PROVIDER).getOrThrow(COOKING_FAST_BURN_TIME_REDUCTION_FACTOR);

        context.register(COOKING_TIME_SPEEDRUNNER_ITEMS, invokeCooking(predicates, normalBurnTime, fastBurnTime, S_asTick(10)));
        context.register(COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS, invokeCooking(predicates, normalBurnTime, fastBurnTime, S_asTick(5)));
        context.register(ZOMBIE_FIREBALL_CHARGE_SPEED_ON_DOOM_MODE, new ConstantValue(40));
        context.register(ZOMBIE_FIREBALL_CHARGE_SPEED_DEFAULT, new ConstantValue(100));
    }

    /**
     * Gets an int from a specified {@link ContextIntProvider}.
     */
    public static int getIntUnsafe(final ResourceKey<ContextIntProvider> key, final ServerLevel serverLevel) {
        LootParams lootParams = new LootParams.Builder(serverLevel).create(LootContextParamSets.EMPTY);
        LootContext context = new LootContext.Builder(lootParams)
                .create(Optional.empty());

        ContextIntProvider provider = context
                .getResolver()
                .lookupOrThrow(Registries.CONTEXT_INT_PROVIDER)
                .getOrThrow(key)
                .value();

        return provider.getIntUnsafe(context);
    }

    /**
     * Creates a new speedrunner mod number provider.
     */
    private static ResourceKey<ContextIntProvider> createIntProvider(final String path) {
        return ResourceKey.create(Registries.CONTEXT_INT_PROVIDER, ofSpeedrunnerMod(path));
    }
}