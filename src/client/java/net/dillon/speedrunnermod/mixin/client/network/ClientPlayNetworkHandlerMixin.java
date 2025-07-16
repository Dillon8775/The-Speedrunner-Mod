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
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.sound.SoundEvents;
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