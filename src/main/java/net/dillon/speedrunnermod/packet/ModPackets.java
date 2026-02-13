package net.dillon.speedrunnermod.packet;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.packet.client.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.client.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.client.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.client.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.packet.server.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.server.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.packet.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

public class ModPackets {

    /**
     * Registers the {@link ClientPreferencesC2SPacket} packet.
     */
    private static void registerC2SClientPreferences() {
        PayloadTypeRegistry.playC2S().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ClientPreferencesC2SPacket.PACKET, (packet, context) -> {
            UUID playerUuid = context.player().getUuid();
            ServerStorage.setActionbarPref(playerUuid, packet.actionbar());
            ServerStorage.setIcarusFireworkSlot(playerUuid, packet.iCarusFireworksInventorySlot());
            ServerStorage.setInfiniPearlSlot(playerUuid, packet.infiniPearlInventorySlot());
        });
    }

    /**
     * Registers the receiver for syncing client-side options with server-side.
     */
    private static void registerC2SRequestServerSideOptions() {
        PayloadTypeRegistry.playC2S().register(RequestServerSideOptionsC2SPacket.PACKET, RequestServerSideOptionsC2SPacket.CODEC);

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
        PayloadTypeRegistry.playC2S().register(MatchServerOptionsWithClientC2SPacket.PACKET, MatchServerOptionsWithClientC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(MatchServerOptionsWithClientC2SPacket.PACKET, (packet, context) -> {
            ModOptions clientOptions = packet.toOptions();
            String player = packet.playerName();
            ServerStorage.storePendingSyncRequest(player, clientOptions);
            context.server().sendMessage(Text.translatable("speedrunnermod.client_options_request_received", player, player));
        });
    }

    /**
     * Registers {@code server-to-client} packets on server.
     */
    private static void registerS2COnServer() {
        // Only register on server
        if (isEnvironmentTypeServer()) {
            PayloadTypeRegistry.playS2C().register(CheckModeS2CPacket.PACKET, CheckModeS2CPacket.CODEC);
            PayloadTypeRegistry.playS2C().register(MatchClientOptionsWithServerS2CPacket.PACKET, MatchClientOptionsWithServerS2CPacket.CODEC);
            PayloadTypeRegistry.playS2C().register(OpenFeaturesScreenS2CPacket.PACKET, OpenFeaturesScreenS2CPacket.CODEC);
            PayloadTypeRegistry.playS2C().register(RequestClientSideOptionsS2CPacket.PACKET, RequestClientSideOptionsS2CPacket.CODEC);
        }
    }

    /**
     * Registers {@code join and disconnect} events on the server-side.
     */
    private static void registerDedicatedServerJoinAndDisconnectEvents() {
        // When a player joins the server, send the CheckPlayingModeS2CPacket over to client, to check if client-side playing mode matches server-side mode
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (player != null) {
                // Handle icarus and infini pearl mode
                if (handler.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
                    // Create a timer to give the server time to receive the slots from client
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            UUID playerUuid = handler.getPlayer().getUuid();
                            int iCarusFireworksInventorySlot = ServerStorage.getIcarusFireworkSlot(playerUuid);
                            int infiniPearlInventorySlot = ServerStorage.getInfiniPearlSlot(playerUuid);

                            ItemStack item;
                            if (options().main.iCarusMode.getCurrentValue()) {
                                item = ModUtil.ofUnbreakable(Items.ELYTRA);
                                ItemStack fireworks = ModUtil.fireworkWithFlightDuration(64);

                                player.equipment.put(EquipmentSlot.CHEST, item);
                                player.getInventory().getMainStacks().set(iCarusFireworksInventorySlot - 1, fireworks);
                            }

                            if (options().main.infiniPearlMode.getCurrentValue()) {
                                ItemStack infiniPearl = ModUtil.ofUnbreakable(ModItems.INFINI_PEARL);
                                int slot = infiniPearlInventorySlot - 1;

                                if (options().main.iCarusMode.getCurrentValue() && iCarusFireworksInventorySlot == infiniPearlInventorySlot) {
                                    slot += 1;
                                }

                                if (options().main.iCarusMode.getCurrentValue() && iCarusFireworksInventorySlot == infiniPearlInventorySlot && infiniPearlInventorySlot >= 36) {
                                    slot -= 2;
                                }

                                player.getInventory().getMainStacks().set(slot, infiniPearl);
                            }
                        }
                    }, 150); // this is the delay that works to ensure that the server has time to receive the slots
                }
            }
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ServerStorage.clearPrefs(handler.getPlayer().getUuid());
        });

        if (isEnvironmentTypeServer()) {
            // Make sure each player's playing mode always matches each tick
            ServerTickEvents.END_SERVER_TICK.register(server -> {
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    ServerPlayNetworking.send(player, new CheckModeS2CPacket(options().main.mode.getCurrentValue()));
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