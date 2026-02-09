package net.dillon.speedrunnermod.mixin.client.network;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Author(Authors.YELEEFFF)
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler {
    @Shadow
    private ClientWorld world;

    public ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
        super(client, connection, connectionState);
    }

    /**
     * Implements correct use and rendering of the {@code speedrunners totem.}
     */
    @Inject(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/EntityStatusS2CPacket;getStatus()B"))
    private void implementSpeedrunnersTotemStatus(EntityStatusS2CPacket packet, CallbackInfo ci, @Local Entity entity) {
        if (entity instanceof EnderPearlEntity pearl) {
            if (packet.getStatus() == ModStatuses.ADD_TRAIL_BLUE_PORTAL_PARTICLES) {
                Vec3d vec3d = pearl.getVelocity();
                Vec3d vec3d2 = pearl.getEntityPos();
                for (int i = 0; i < 32; ++i) {
                    this.client.particleManager.addParticle(ModParticleTypes.BLUE_PORTAL, vec3d2.x - vec3d.x * 0.25, vec3d2.y - vec3d.y * 0.25, vec3d2.z - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
                }
            }
        }

        if (entity instanceof PlayerEntity player) {
            boolean infiniPearlLanding = packet.getStatus() == ModStatuses.ADD_INFINI_PEARL_LANDING_PARTICLES;
            boolean defaultPearlLanding = packet.getStatus() == ModStatuses.ADD_PEARL_LANDING_PARTICLES;
            boolean pearlLanding = infiniPearlLanding || defaultPearlLanding;
            if (pearlLanding) {
                for (int i = 0 ; i < 32; i++) {
                    this.client.particleManager.addParticle(
                            infiniPearlLanding ? ModParticleTypes.BLUE_PORTAL : ParticleTypes.PORTAL,
                            player.getParticleX(0.5),
                            player.getRandomBodyY() - 0.25,
                            player.getParticleZ(0.5),
                            (player.getRandom().nextDouble() - 0.5) * 2.0,
                            -player.getRandom().nextDouble(),
                            (player.getRandom().nextDouble() - 0.5) * 2.0
                    );
                }
            }
            if (packet.getStatus() == ModStatuses.ADD_BLUE_PORTAL_PARTICLES) {
                for (int j = 0; j < 128; j++) {
                    double d = j / 127.0;
                    float f = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float g = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float h = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    double e = MathHelper.lerp(d, player.lastX, player.getX()) + (player.getRandom().nextDouble() - 0.5) * player.getWidth() * 2.0;
                    double k = MathHelper.lerp(d, player.lastY, player.getY()) + player.getRandom().nextDouble() * player.getHeight();
                    double l = MathHelper.lerp(d, player.lastZ, player.getZ()) + (player.getRandom().nextDouble() - 0.5) * player.getWidth() * 2.0;
                    this.client.particleManager.addParticle(ModParticleTypes.BLUE_PORTAL, e, k, l, f, g, h);
                }
            }
            if (packet.getStatus() == ModStatuses.ADD_BLAZE_SMOKE_PARTICLES) {
                for (int i = 0; i < 32; i++) {
                    this.client.particleManager.addParticle(ParticleTypes.LARGE_SMOKE, player.getParticleX(0.5), player.getRandomBodyY(), player.getParticleZ(0.5), 0.0, 0.0, 0.0);
                }
            }
        }

        if (packet.getStatus() == ModStatuses.ADD_SPEEDRUNNER_TOTEM_PARTICLES) {
            this.client.particleManager.addEmitter(entity, ModParticleTypes.SPEEDRUNNERS_TOTEM, 30);
            this.world.playSoundClient(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
            if (entity != this.client.player) {
                return;
            }
            this.client.gameRenderer.showFloatingItem(ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());
        }
    }
}