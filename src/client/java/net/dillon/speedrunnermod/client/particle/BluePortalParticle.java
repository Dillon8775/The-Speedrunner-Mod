package net.dillon.speedrunnermod.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.random.Random;

/**
 * A portal-type particle for speedrunner items.
 */
public class BluePortalParticle extends PortalParticle {

    protected BluePortalParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Sprite sprite) {
        super(clientWorld, d, e, f, g, h, i, sprite);
        float j = this.random.nextFloat() * 0.2F;
        this.red = j + 0.0F;
        this.green = j + 0.8F;
        this.blue = j + 0.8F;
    }

    
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
            return new BluePortalParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider.getSprite(random));
        }
    }
}