package net.dillon.speedrunnermod.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

/**
 * The particle for the {@code speedrunners totem.}
 * <p>Copied over from {@link TotemParticle}.</p>
 */
public class SpeedrunnersTotemParticle extends AnimatedParticle {

    SpeedrunnersTotemParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 1.25F);
        this.velocityMultiplier = 0.6F;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.scale *= 0.75F;
        this.maxAge = 60 + this.random.nextInt(12);
        this.updateSprite(spriteProvider);
        if (this.random.nextInt(4) == 0) {
            this.setColor(0.0F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.2F);
        } else {
            this.setColor(0.0F + this.random.nextFloat() * 0.1F, 0.3F + this.random.nextFloat() * 0.2F, 0.3F + this.random.nextFloat() * 0.2F);
        }
    }

    
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
            return new SpeedrunnersTotemParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}