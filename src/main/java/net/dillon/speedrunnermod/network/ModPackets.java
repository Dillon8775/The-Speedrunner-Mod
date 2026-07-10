package net.dillon.speedrunnermod.network;

import net.dillon.speedrunnermod.helper.ModHelper;
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
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

public class ModPackets {

    /**
     * Registers the {@link ClientPreferencesC2SPacket} packet.
     */
    private static void registerC2SClientPreferences() {
        PayloadTypeRegistry.serverboundPlay().register(ClientPreferencesC2SPacket.PACKET, ClientPreferencesC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ClientPreferencesC2SPacket.PACKET, (packet, context) -> {
            UUID playerUuid = context.player().getUUID();
            DedicatedServerStorage.setActionbarPref(playerUuid, packet.actionbar());
            DedicatedServerStorage.setWarningMessages(playerUuid, packet.warningMessages());
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
                                item = ModHelper.ofUnbreakable(Items.ELYTRA);
                                ItemStack fireworks = ModHelper.fireworkWithFlightDuration(64);

                                ((LivingEntityAccessor)player).getEquipment().set(EquipmentSlot.CHEST, item);
                                player.getInventory().getNonEquipmentItems().set(iCarusFireworksInventorySlot - 1, fireworks);
                            }

                            if (options().general.infiniPearlMode.getCurrentValue()) {
                                ItemStack infiniPearl = ModHelper.ofUnbreakable(ModItems.INFINI_PEARL);
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

                    // Everything after this point is doom mode exclusive
                    if (!isDoomMode()) {
                        return;
                    }

                    // Create doom mode tasks
                    TaskScheduler.schedule(TickCalculator.seconds(1), () -> {
                        ServerLevel serverLevel = server.getLevel(ServerLevel.OVERWORLD);
                        player.connection.send(
                                new ClientboundSetTitleTextPacket(Component.translatable("speedrunnermod.doom_mode",
                                                player.getName())
                                        .withStyle(ChatFormatting.RED)
                                        .withStyle(ChatFormatting.BOLD)
                                )
                        );
                        player.connection.send(
                                new ClientboundSetSubtitleTextPacket(Component.translatable("speedrunnermod.doom_mode.desc")
                                        .withStyle(ChatFormatting.RED)
                                )
                        );
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, TickCalculator.minutes(2)));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, TickCalculator.minutes(2)));

                        serverLevel.playSound(null, player.getOnPos(), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.HOSTILE);

                        Vec3 look = player.getLookAngle();
                        double distance = 5.0;

                        double x = player.getX() + look.x * distance;
                        double z = player.getZ() + look.z * distance;
                        int y = serverLevel.getHeight(
                                Heightmap.Types.MOTION_BLOCKING,
                                (int) x,
                                (int) z
                        );
                        Vec3 spawnPos = new Vec3(x, y, z);

                        LightningBolt lightningBolt =
                                EntityTypes.LIGHTNING_BOLT.create(serverLevel, EntitySpawnReason.NATURAL);
                        lightningBolt.snapTo(spawnPos);
                        serverLevel.addFreshEntity(lightningBolt);
                    });
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