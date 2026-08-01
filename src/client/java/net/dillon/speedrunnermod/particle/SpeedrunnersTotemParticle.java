package net.dillon.speedrunnermod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

/**
 * The particle for the {@code speedrunners totem.}
 * <p>Copied over from {@link TotemParticle}.</p>
 */
public class SpeedrunnersTotemParticle extends SimpleAnimatedParticle {

    SpeedrunnersTotemParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet spriteProvider) {
        super(world, x, y, z, spriteProvider, 1.25F);
        this.friction = 0.6F;
        this.xd = velocityX;
        this.yd = velocityY;
        this.zd = velocityZ;
        this.quadSize *= 0.75F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(spriteProvider);
        if (this.random.nextInt(4) == 0) {
            this.setColor(0.0F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.2F, 0.8F + this.random.nextFloat() * 0.2F);
        } else {
            this.setColor(0.0F + this.random.nextFloat() * 0.1F, 0.3F + this.random.nextFloat() * 0.2F, 0.3F + this.random.nextFloat() * 0.2F);
        }
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random) {
            return new SpeedrunnersTotemParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}