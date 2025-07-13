package net.dillon.speedrunnermod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod particle types.
 */
public class ModParticleTypes {
    public static final SimpleParticleType SPEEDRUNNERS_TOTEM = FabricParticleTypes.simple(false);
    public static final SimpleParticleType BLUE_PORTAL = FabricParticleTypes.simple(false);

    /**
     * Registers all speedrunner mod particle types.
     */
    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, ofSpeedrunnerMod("speedrunners_totem"), SPEEDRUNNERS_TOTEM);
        Registry.register(Registries.PARTICLE_TYPE, ofSpeedrunnerMod("blue_portal"), BLUE_PORTAL);
    }
}