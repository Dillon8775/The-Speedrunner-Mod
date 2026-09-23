package net.dillon.speedrunnermod.particle;

import net.dillon.speedrunnermod.entity.ModParticleTypes;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

/**
 * Manages all speedrunner mod particle types and registers them client-side.
 */
public class ModParticleManager {

    /**
     * Registers all speedrunner mod particle types on the client-side.
     */
    public static void registerParticleTypes() {
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.BLUE_PORTAL, BluePortalParticle.Factory::new);
        ParticleProviderRegistry.getInstance().register(ModParticleTypes.SPEEDRUNNERS_TOTEM, SpeedrunnersTotemParticle.Factory::new);
    }
}