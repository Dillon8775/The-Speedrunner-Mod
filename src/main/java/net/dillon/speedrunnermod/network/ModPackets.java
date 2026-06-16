package net.dillon.speedrunnermod.network;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.accessor.LivingEntityAccessor;
import net.dillon.speedrunnermod.network.client.CheckModeS2CPacket;
import net.dillon.speedrunnermod.network.client.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.network.client.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.network.client.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.network.server.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.network.server.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.network.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.server.DedicatedServerStorage;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

public class ModPackets {

    /**
     * Registers the {@link ClientPreferencesC2SPacket} packet.
     */
    private static void registerC2SClientPreferences() {
        PayloadTypeRegistry.serverboundPlay().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ClientPreferencesC2SPacket.PACKET, (packet, context) -> {
            UUID playerUuid = context.player().getUUID();
            DedicatedServerStorage.setActionbarPref(playerUuid, packet.actionbar());
            DedicatedServerStorage.setIcarusFireworkSlot(playerUuid, packet.iCarusFireworksInventorySlot());
            DedicatedServerStorage.setInfiniPearlSlot(playerUuid, packet.infiniPearlInventorySlot());
        });
    }

    /**
     * Registers the receiver for syncing client-side options with server-side.
     */
    private static void registerC2SRequestServerSideOptions() {
        PayloadTypeRegistry.serverboundPlay().register(RequestServerSideOptionsC2SPacket.PACKET, RequestServerSideOptionsC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(RequestServerSideOptionsC2SPacket.PACKET, (packet, context) -> {
            ModOptions serverOptions = options();
            ServerPlayNetworking.send(context.player(), MatchClientOptionsWithServerS2CPacket.from(serverOptions));
            info(context.player().getDisplayName().getString() + " requested this server's speedrunner mod settings.");
        });
    }

    /**
     * Registers the packet for syncing server-side options with client-side.
     */
    private static void registerC2SMatchServerOptionsWithClient() {
        PayloadTypeRegistry.serverboundPlay().register(MatchServerOptionsWithClientC2SPacket.PACKET, MatchServerOptionsWithClientC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(MatchServerOptionsWithClientC2SPacket.PACKET, (packet, context) -> {
            ModOptions clientOptions = packet.toOptions();
            String player = packet.playerName();
            DedicatedServerStorage.storePendingSyncRequest(player, clientOptions);
            context.server().sendSystemMessage(Component.translatable("speedrunnermod.client_options_request_received", player, player));
        });
    }

    /**
     * Registers {@code server-to-client} packets on server.
     */
    private static void registerS2COnServer() {
        // Only register on server
        if (isEnvironmentTypeServer()) {
            PayloadTypeRegistry.clientboundPlay().register(CheckModeS2CPacket.PACKET, CheckModeS2CPacket.CODEC);
            PayloadTypeRegistry.clientboundPlay().register(MatchClientOptionsWithServerS2CPacket.PACKET, MatchClientOptionsWithServerS2CPacket.CODEC);
            PayloadTypeRegistry.clientboundPlay().register(OpenFeaturesScreenS2CPacket.PACKET, OpenFeaturesScreenS2CPacket.CODEC);
            PayloadTypeRegistry.clientboundPlay().register(RequestClientSideOptionsS2CPacket.PACKET, RequestClientSideOptionsS2CPacket.CODEC);
        }
    }

    /**
     * Registers {@code join and disconnect} events on the server-side.
     */
    private static void registerDedicatedServerJoinAndDisconnectEvents() {
        // When a player joins the server, send the CheckPlayingModeS2CPacket over to client, to check if client-side playing mode matches server-side mode
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer player = handler.getPlayer();
            if (player != null) {
                // Handle icarus and infini pearl mode
                if (handler.getPlayer().getStats().getValue(Stats.CUSTOM.get(Stats.PLAY_TIME)) == 0) {
                    // Create a timer to give the server time to receive the slots from client
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            UUID playerUuid = handler.getPlayer().getUUID();
                            int iCarusFireworksInventorySlot = DedicatedServerStorage.getIcarusFireworkSlot(playerUuid);
                            int infiniPearlInventorySlot = DedicatedServerStorage.getInfiniPearlSlot(playerUuid);

                            ItemStack item;
                            if (options().general.iCarusMode.getCurrentValue()) {
                                item = ModUtil.ofUnbreakable(Items.ELYTRA);
                                ItemStack fireworks = ModUtil.fireworkWithFlightDuration(64);

                                ((LivingEntityAccessor)player).getEquipment().set(EquipmentSlot.CHEST, item);
                                player.getInventory().getNonEquipmentItems().set(iCarusFireworksInventorySlot - 1, fireworks);
                            }

                            if (options().general.infiniPearlMode.getCurrentValue()) {
                                ItemStack infiniPearl = ModUtil.ofUnbreakable(ModItems.INFINI_PEARL);
                                int slot = infiniPearlInventorySlot - 1;

                                if (options().general.iCarusMode.getCurrentValue() && iCarusFireworksInventorySlot == infiniPearlInventorySlot) {
                                    slot += 1;
                                }

                                if (options().general.iCarusMode.getCurrentValue() && iCarusFireworksInventorySlot == infiniPearlInventorySlot && infiniPearlInventorySlot >= 36) {
                                    slot -= 2;
                                }

                                player.getInventory().getNonEquipmentItems().set(slot, infiniPearl);
                            }
                        }
                    }, 150); // this is the delay that works to ensure that the server has time to receive the slots
                }
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            DedicatedServerStorage.clearPrefs(handler.getPlayer().getUUID());
        });

        if (isEnvironmentTypeServer()) {
            // Make sure each player's playing mode always matches each tick
            ServerTickEvents.END_SERVER_TICK.register(server -> {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    ServerPlayNetworking.send(player, new CheckModeS2CPacket(options().general.mode.getCurrentValue()));
                }
            });
        }
    }

    /**
     * Registers all speedrunner mod payloads/packets.
     */
    public static void registerPackets() {
        registerC2SClientPreferences();
        registerC2SMatchServerOptionsWithClient();
        registerC2SRequestServerSideOptions();

        registerS2COnServer(); // register server-to-client ONLY on EnvType.SERVER

        registerDedicatedServerJoinAndDisconnectEvents();

        SpeedrunnerMod.debug("Registered client-to-server packets.");
    }
}