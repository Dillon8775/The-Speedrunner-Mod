package net.dillon.speedrunnermod.main;

import net.blay09.mods.balm.Balm;
import net.dillon.dillonlib.platform.Platforms;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.mixin.accessor.LivingEntityAccessor;
import net.dillon.speedrunnermod.packet.ClientPacketHandlers;
import net.dillon.speedrunnermod.packet.ServerPacketHandlers;
import net.dillon.speedrunnermod.packet.clientbound.CheckModeS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.MatchClientOptionsWithServerS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.OpenFeaturesScreenS2CPacket;
import net.dillon.speedrunnermod.packet.clientbound.RequestClientSideOptionsS2CPacket;
import net.dillon.speedrunnermod.packet.serverbound.ClientPreferencesC2SPacket;
import net.dillon.speedrunnermod.packet.serverbound.MatchServerOptionsWithClientC2SPacket;
import net.dillon.speedrunnermod.packet.serverbound.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.server.DedicatedServerStorage;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
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

import static net.dillon.dillonlib.util.Arithmetics.M_asTick;
import static net.dillon.dillonlib.util.Arithmetics.S_asTick;
import static net.dillon.speedrunnermod.main.CommonMain.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * Common-side events for The Speedrunner Mod.
 */
public class CommonEvents {

    /**
     * Registers {@code join and disconnect} events on the server-side.
     */
    public static void registerDedicatedServerJoinAndDisconnectEvents() {
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
                            if (common().general().iCarusMode) {
                                item = ModHelper.ofUnbreakable(Items.ELYTRA);
                                ItemStack fireworks = ModHelper.fireworkWithFlightDuration(64);

                                ((LivingEntityAccessor)player).getEquipment().set(EquipmentSlot.CHEST, item);
                                player.getInventory().getNonEquipmentItems().set(iCarusFireworksInventorySlot - 1, fireworks);
                            }

                            if (common().general().infiniPearlMode) {
                                ItemStack infiniPearl = ModHelper.ofUnbreakable(ModItems.INFINI_PEARL);
                                int slot = infiniPearlInventorySlot - 1;

                                if (common().general().iCarusMode && iCarusFireworksInventorySlot == infiniPearlInventorySlot) {
                                    slot += 1;
                                }

                                if (common().general().iCarusMode && iCarusFireworksInventorySlot == infiniPearlInventorySlot && infiniPearlInventorySlot >= 36) {
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
                    TaskScheduler.schedule(S_asTick(1), () -> {
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
                        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, M_asTick(2)));
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, M_asTick(2)));

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

        if (Platforms.getCommonPlatform().isEnvironmentServer()) {
            // Make sure each player's playing mode always matches, each tick
            ServerTickEvents.END_SERVER_TICK.register(server -> {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    Balm.networking().sendTo(player, new CheckModeS2CPacket(common().general().mode));
                }
            });
        }
    }

    /**
     * Registers all packets.
     */
    public static void registerPackets() {
        Balm.networking().registerServerboundPacket(
                ClientPreferencesC2SPacket.PACKET_TYPE,
                ClientPreferencesC2SPacket.class,
                ClientPreferencesC2SPacket.CODEC,
                ServerPacketHandlers::handleClientPreferences
        );

        Balm.networking().registerServerboundPacket(
                RequestServerSideOptionsC2SPacket.PACKET_TYPE,
                RequestServerSideOptionsC2SPacket.class,
                RequestServerSideOptionsC2SPacket.CODEC,
                ServerPacketHandlers::handleRequestServerSideOptions
        );

        Balm.networking().registerServerboundPacket(
                MatchServerOptionsWithClientC2SPacket.PACKET_TYPE,
                MatchServerOptionsWithClientC2SPacket.class,
                MatchServerOptionsWithClientC2SPacket.CODEC,
                ServerPacketHandlers::handleMatchServerOptionsWithUser
        );

        Balm.networking().registerClientboundPacket(
                CheckModeS2CPacket.PACKET_TYPE,
                CheckModeS2CPacket.class,
                CheckModeS2CPacket.CODEC,
                ClientPacketHandlers::handleCheckMode
        );

        Balm.networking().registerClientboundPacket(
                MatchClientOptionsWithServerS2CPacket.PACKET_TYPE,
                MatchClientOptionsWithServerS2CPacket.class,
                MatchClientOptionsWithServerS2CPacket.CODEC,
                ClientPacketHandlers::handleMatchUserOptionsWithServer
        );

        Balm.networking().registerClientboundPacket(
                OpenFeaturesScreenS2CPacket.PACKET_TYPE,
                OpenFeaturesScreenS2CPacket.class,
                OpenFeaturesScreenS2CPacket.CODEC,
                ClientPacketHandlers::handleOpenFeaturesScreen
        );

        Balm.networking().registerClientboundPacket(
                RequestClientSideOptionsS2CPacket.PACKET_TYPE,
                RequestClientSideOptionsS2CPacket.class,
                RequestClientSideOptionsS2CPacket.CODEC,
                ClientPacketHandlers::handleMatchClientOptionsWithServer
        );
    }
}