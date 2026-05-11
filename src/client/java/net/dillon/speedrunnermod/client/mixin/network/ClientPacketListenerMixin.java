package net.dillon.speedrunnermod.client.mixin.network;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Author(Authors.YELEEFFF)
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin extends ClientCommonPacketListenerImpl {
    @Shadow
    private ClientLevel level;

    public ClientPacketListenerMixin(Minecraft client, Connection connection, CommonListenerCookie connectionState) {
        super(client, connection, connectionState);
    }

    /**
     * Implements correct use and rendering of the {@code speedrunners totem.}
     */
    @Inject(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundEntityEventPacket;getEventId()B"))
    private void implementSpeedrunnersTotemStatus(ClientboundEntityEventPacket packet, CallbackInfo ci, @Local Entity entity) {
        if (entity instanceof ThrownEnderpearl pearl) {
            if (packet.getEventId() == ModStatuses.ADD_TRAIL_BLUE_PORTAL_PARTICLES) {
                Vec3 vec3d = pearl.getDeltaMovement();
                Vec3 vec3d2 = pearl.position();
                for (int i = 0; i < 32; ++i) {
                    this.minecraft.particleEngine.createParticle(ModParticleTypes.BLUE_PORTAL, vec3d2.x - vec3d.x * 0.25, vec3d2.y - vec3d.y * 0.25, vec3d2.z - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
                }
            }
        }

        if (entity instanceof Player player) {
            boolean infiniPearlLanding = packet.getEventId() == ModStatuses.ADD_INFINI_PEARL_LANDING_PARTICLES;
            boolean defaultPearlLanding = packet.getEventId() == ModStatuses.ADD_PEARL_LANDING_PARTICLES;
            boolean pearlLanding = infiniPearlLanding || defaultPearlLanding;
            if (pearlLanding) {
                for (int i = 0 ; i < 32; i++) {
                    this.minecraft.particleEngine.createParticle(
                            infiniPearlLanding ? ModParticleTypes.BLUE_PORTAL : ParticleTypes.PORTAL,
                            player.getRandomX(0.5),
                            player.getRandomY() - 0.25,
                            player.getRandomZ(0.5),
                            (player.getRandom().nextDouble() - 0.5) * 2.0,
                            -player.getRandom().nextDouble(),
                            (player.getRandom().nextDouble() - 0.5) * 2.0
                    );
                }
            }
            if (packet.getEventId() == ModStatuses.ADD_BLUE_PORTAL_PARTICLES) {
                for (int j = 0; j < 128; j++) {
                    double d = j / 127.0;
                    float f = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float g = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    float h = (player.getRandom().nextFloat() - 0.5F) * 0.2F;
                    double e = Mth.lerp(d, player.xo, player.getX()) + (player.getRandom().nextDouble() - 0.5) * player.getBbWidth() * 2.0;
                    double k = Mth.lerp(d, player.yo, player.getY()) + player.getRandom().nextDouble() * player.getBbHeight();
                    double l = Mth.lerp(d, player.zo, player.getZ()) + (player.getRandom().nextDouble() - 0.5) * player.getBbWidth() * 2.0;
                    this.minecraft.particleEngine.createParticle(ModParticleTypes.BLUE_PORTAL, e, k, l, f, g, h);
                }
            }
            if (packet.getEventId() == ModStatuses.ADD_BLAZE_SMOKE_PARTICLES) {
                for (int i = 0; i < 32; i++) {
                    this.minecraft.particleEngine.createParticle(ParticleTypes.LARGE_SMOKE, player.getRandomX(0.5), player.getRandomY(), player.getRandomZ(0.5), 0.0, 0.0, 0.0);
                }
            }
        }

        if (packet.getEventId() == ModStatuses.ADD_SPEEDRUNNER_TOTEM_PARTICLES) {
            this.minecraft.particleEngine.createTrackingEmitter(entity, ModParticleTypes.SPEEDRUNNERS_TOTEM, 30);
            this.level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, entity.getSoundSource(), 1.0F, 1.0F, false);
            if (entity != this.minecraft.player) {
                return;
            }
            this.minecraft.gameRenderer.displayItemActivation(ModItems.SPEEDRUNNERS_TOTEM.getDefaultInstance());
        }
    }
}