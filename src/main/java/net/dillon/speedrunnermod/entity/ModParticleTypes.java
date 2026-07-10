package net.dillon.speedrunnermod.entity;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

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
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ofSpeedrunnerMod("speedrunners_totem"), SPEEDRUNNERS_TOTEM);
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, ofSpeedrunnerMod("blue_portal"), BLUE_PORTAL);

        SpeedrunnerMod.debug("Registered speedrunner mod particle types.");
    }
}