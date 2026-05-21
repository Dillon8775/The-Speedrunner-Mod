package net.dillon.speedrunnermod.mixin.client.render;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.mixin.entity.EyeOfEnderMixin;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelEventHandler.class)
public class LevelEventHandlerMixin {
    @Shadow @Final
    private ClientLevel level;

    /**
     * Allows for the correct particle breaking animation for different ender eyes.
     * <p>See {@link EyeOfEnderMixin} for more on this.</p>
     */
    @Inject(method = "levelEvent", at = @At("TAIL"))
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
    private void eyeOfEnderBreakEvent(Item item, ParticleOptions particleType, BlockPos pos) {
        RandomSource modRandom = this.level.getRandom();
        double r = (double)pos.getX() + 0.5;
        double s = pos.getY();
        double d = (double)pos.getZ() + 0.5;
        for (int t = 0; t < 8; ++t) {
            this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(item).getItem()), r, s, d, modRandom.nextGaussian() * 0.15, modRandom.nextDouble() * 0.2, modRandom.nextGaussian() * 0.15);
        }
        for (double e = 0.0; e < Math.PI * 2; e += 0.15707963267948966) {
            this.level.addParticle(particleType, r + Math.cos(e) * 5.0, s - 0.4, d + Math.sin(e) * 5.0, Math.cos(e) * -5.0, 0.0, Math.sin(e) * -5.0);
            this.level.addParticle(particleType, r + Math.cos(e) * 5.0, s - 0.4, d + Math.sin(e) * 5.0, Math.cos(e) * -7.0, 0.0, Math.sin(e) * -7.0);
        }
    }
}