package net.dillon.speedrunnermod.mixin.client.render;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.WorldEventHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(WorldEventHandler.class)
public abstract class CustomWorldEvents {
    @Shadow @Final
    private World world;

    /**
     * Allows for the correct particle breaking animation for different ender eyes.
     * <p>See {@link net.dillon.speedrunnermod.mixin.main.entity.EyeOfEnderEntityMixin} for more on this.</p>
     */
    @Inject(method = "processWorldEvent", at = @At("TAIL"))
    private void implementSpeedrunnerModWorldEvents(int eventId, BlockPos pos, int data, CallbackInfo ci) {
        if (eventId == ModStatuses.ADD_SMOKE_PARTICLES) {
            this.eyeOfEnderBreakEvent(ModItems.INFERNO_EYE, ParticleTypes.SMOKE, pos);
        } else if (eventId == ModStatuses.ADD_BLUE_PORTAL_PARTICLES_FOR_SPEEDRUNNERS_EYE) {
            this.eyeOfEnderBreakEvent(ModItems.SPEEDRUNNERS_EYE, ModParticleTypes.BLUE_PORTAL, pos);
        }
    }

    /**
     * The event method for rendering the particles when an eye of ender breaks.
     */
    @Unique
    private void eyeOfEnderBreakEvent(Item item, ParticleEffect particleType, BlockPos pos) {
        Random modRandom = this.world.random;
        double r = (double)pos.getX() + 0.5;
        double s = pos.getY();
        double d = (double)pos.getZ() + 0.5;
        for (int t = 0; t < 8; ++t) {
            this.world.addParticleClient(new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(item)), r, s, d, modRandom.nextGaussian() * 0.15, modRandom.nextDouble() * 0.2, modRandom.nextGaussian() * 0.15);
        }
        for (double e = 0.0; e < Math.PI * 2; e += 0.15707963267948966) {
            this.world.addParticleClient(particleType, r + Math.cos(e) * 5.0, s - 0.4, d + Math.sin(e) * 5.0, Math.cos(e) * -5.0, 0.0, Math.sin(e) * -5.0);
            this.world.addParticleClient(particleType, r + Math.cos(e) * 5.0, s - 0.4, d + Math.sin(e) * 5.0, Math.cos(e) * -7.0, 0.0, Math.sin(e) * -7.0);
        }
    }
}