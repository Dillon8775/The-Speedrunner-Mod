package net.dillon.speedrunnermod.mixin.client.network;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.client.network.ClientConnectionState;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.GuardianAttackSoundInstance;
import net.minecraft.client.sound.SnifferDigSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.item.Items;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Author(Authors.YELEEFFF)
@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin extends ClientCommonNetworkHandler implements ClientPlayPacketListener, TickablePacketListener {
    @Shadow
    private ClientWorld world;

    public ClientPlayNetworkHandlerMixin(MinecraftClient client, ClientConnection connection, ClientConnectionState connectionState) {
        super(client, connection, connectionState);
    }

    /**
     * @author Dillon8775
     * @reason Injects {@code speedrunner totem item use rendering.}
     */
    @Overwrite
    public void onEntityStatus(EntityStatusS2CPacket packet) {
        NetworkThreadUtils.forceMainThread(packet, this, this.client);
        Entity entity = packet.getEntity(this.world);

        if (entity != null) {
            switch (packet.getStatus()) {
                case 63 -> this.client.getSoundManager().play(new SnifferDigSoundInstance((SnifferEntity) entity));
                case 21 -> this.client.getSoundManager().play(new GuardianAttackSoundInstance((GuardianEntity) entity));
                case 35, 77 -> {
                    this.client.particleManager.addEmitter(entity, packet.getStatus() == 35 ? ParticleTypes.TOTEM_OF_UNDYING : ModParticleTypes.SPEEDRUNNERS_TOTEM, 30);
                    this.world.playSoundClient(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
                    if (entity != this.client.player) break;

                    switch (packet.getStatus()) {
                        case 35 -> this.client.gameRenderer.showFloatingItem(Items.TOTEM_OF_UNDYING.getDefaultStack());
                        case 77 -> this.client.gameRenderer.showFloatingItem(ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());
                    }
                }
                default -> entity.handleStatus(packet.getStatus());
            }
        }
    }
}