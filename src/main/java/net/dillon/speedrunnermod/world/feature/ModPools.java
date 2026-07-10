package net.dillon.speedrunnermod.world.feature;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Contains all speedrunner mod pool entries.
 */
@Deprecated
@SuppressWarnings("unused")
public class ModPools {

    /**
     * Bootstraps all structure template pools.
     */
    public static void bootstrap(final BootstrapContext<StructureTemplatePool> context) {
    }

    /**
     * Creates a {@code pool key.}
     */
    private static ResourceKey<StructureTemplatePool> createTemplatePoolKey(final String name) {
        return Pools.createKey(ofSpeedrunnerMod(name));
    }
}