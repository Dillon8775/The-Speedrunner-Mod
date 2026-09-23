package net.dillon.speedrunnermod.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.floats.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProvider;

import java.util.Optional;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * All speedrunner mod float providers.
 */
public class ModFloatProviders {
    public static final ResourceKey<ContextFloatProvider> CHANCE_THAT_A_STORM_SPAWNS_ON_DOOM_MODE = createFloatProvider("chance_that_a_storm_spawns_on_doom_mode");
    public static final ResourceKey<ContextFloatProvider> ZOMBIE_SPAWN_WITH_FIREBALL_CHANCE_DEFAULT = createFloatProvider("zombie_spawn_with_fireball_chance_default");
    public static final ResourceKey<ContextFloatProvider> ZOMBIE_SPAWN_WITH_FIREBALL_CHANCE_ON_DOOM_MODE = createFloatProvider("zombie_spawn_with_fireball_chance_on_doom_mode");

    /**
     * Bootstraps mod float providers.
     */
    public static void bootstrap(final BootstrapContext<ContextFloatProvider> context) {
        context.register(CHANCE_THAT_A_STORM_SPAWNS_ON_DOOM_MODE, new ConstantValue(0.25F));
        context.register(ZOMBIE_SPAWN_WITH_FIREBALL_CHANCE_DEFAULT, new ConstantValue(0.03F));
        context.register(ZOMBIE_SPAWN_WITH_FIREBALL_CHANCE_ON_DOOM_MODE, new ConstantValue(0.15F));
    }

    /**
     * Gets a float from a specified {@link ContextFloatProvider}.
     */
    public static float getFloatUnsafe(final ResourceKey<ContextFloatProvider> key, final ServerLevel serverLevel) {
        LootParams lootParams = new LootParams.Builder(serverLevel).create(LootContextParamSets.EMPTY);
        LootContext context = new LootContext.Builder(lootParams)
                .create(Optional.empty());

        ContextFloatProvider provider = context
                .getResolver()
                .lookupOrThrow(Registries.CONTEXT_FLOAT_PROVIDER)
                .getOrThrow(key)
                .value();

        return provider.getFloatUnsafe(context);
    }

    /**
     * Creates a new speedrunner mod number provider.
     */
    private static ResourceKey<ContextFloatProvider> createFloatProvider(final String path) {
        return ResourceKey.create(Registries.CONTEXT_FLOAT_PROVIDER, ofSpeedrunnerMod(path));
    }
}