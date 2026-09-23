package net.dillon.speedrunnermod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

/**
 * A portal-type particle for speedrunner items.
 */
public class BluePortalParticle extends PortalParticle {

    protected BluePortalParticle(ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, TextureAtlasSprite sprite) {
        super(clientWorld, d, e, f, g, h, i, sprite);
        float j = this.random.nextFloat() * 0.2F;
        this.rCol = j + 0.0F;
        this.gCol = j + 0.8F;
        this.bCol = j + 0.8F;
    }

    
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random) {
            return new BluePortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.get(random));
        }
    }
}