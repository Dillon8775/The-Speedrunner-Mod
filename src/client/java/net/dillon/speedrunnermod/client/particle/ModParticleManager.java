package net.dillon.speedrunnermod.client.particle;

import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

/**
 * Manages all speedrunner mod particle types and registers them client-side.
 */
public class ModParticleManager {

    /**
     * Registers all speedrunner mod particle types on the client-side.
     */
    public static void registerDefaults() {
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SPEEDRUNNERS_TOTEM, SpeedrunnersTotemParticle.Factory::new);
    }
}