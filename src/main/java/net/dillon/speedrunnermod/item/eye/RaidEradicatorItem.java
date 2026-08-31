package net.dillon.speedrunnermod.item.eye;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.helper.VillagerGlowCountdown;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isBalancedMode;

/**
 * An item that kills all nearby {@link Raider}s.
 */
public class RaidEradicatorItem extends Item implements SpeedrunnerItem {

    public RaidEradicatorItem(Properties settings) {
        super(settings.rarity(Rarity.EPIC).stacksTo(16));
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (isBalancedMode()) {
            this.playWorldSound(SoundEvents.VINDICATOR_DEATH, 1.0F, world, player);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled").withStyle(ChatFormatting.GRAY));
            stack.shrink(1);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, Items.FIRE_CHARGE);
            player.spawnAtLocation((ServerLevel)world, Items.ENCHANTED_GOLDEN_APPLE);
            player.spawnAtLocation((ServerLevel)world, ModItems.SPEEDRUNNERS_EYE);
        } else {
            final int r = 300;
            List<Integer> radius = List.of(r, r, r);
            List<Raider> raiders = ModHelper.getEntitiesWithinRange(world, Raider.class, player, radius);

            if (raiders.isEmpty()) {
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.raid_eradicator.couldnt_find_raiders"));
            } else {
                this.playWorldSound(SoundEvents.RAVAGER_ROAR, 3.0F, 1.0F, world, player);
                player.getCooldowns().addCooldown(this.getDefaultInstance(), Arithmetics.mas(3));
                this.decrementIfPossible(player, stack);
                ServerPlayer serverPlayer = (ServerPlayer)player;

                List<Villager> villagers = ModHelper.getEntitiesWithinRange(world, Villager.class, player, radius);

                TaskScheduler.schedule(Arithmetics.sas(3), () -> {
                    for (Raider raider : raiders) {
                        if (!(raider instanceof Evoker) && !raider.hasCustomName() && !raider.isBaby()) {
                            raider.kill((ServerLevel)world);
                        } else {
                            Random random = new Random();
                            raider.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, Arithmetics.sas(30), 2, false, true, false));
                            raider.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, Arithmetics.sas(30), 1, false, true, false));
                            raider.addEffect(new MobEffectInstance(MobEffects.GLOWING, Arithmetics.mas(2), 0, false, true, false));
                            raider.randomTeleport(player.getX() + random.nextInt(7) - 3, player.getY() + random.nextDouble() * (2.0 - 0.5) + 0.5, player.getZ() + random.nextInt(7) - 3, false, state -> true);
                        }
                    }
                    if (!villagers.isEmpty()) {
                        for (Villager villager : villagers) {
                            villager.addEffect(new MobEffectInstance(MobEffects.REGENERATION, Arithmetics.sas(30), 1));
                            villager.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, Arithmetics.sas(30)));
                            villager.setGlowingTag(true);
                            ((VillagerGlowCountdown)villager).setGlowingFor(Arithmetics.mas(3));
                        }
                    }

                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(serverPlayer, ModItems.RAID_ERADICATOR.getDefaultInstance());

                    Component purgedText = Component.translatable("item.speedrunnermod.raid_eradicator.purged").withStyle(ChatFormatting.RED);
                    player.sendSystemMessage(purgedText);
                    serverPlayer.connection.send(new ClientboundSetTitleTextPacket(Component.translatable("item.speedrunnermod.raid_eradicator.success", serverPlayer.getName()).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD)));
                    serverPlayer.connection.send(new ClientboundSetSubtitleTextPacket(purgedText));
                    this.playWorldSound(SoundEvents.RAVAGER_DEATH, 3.0F, 1.0F, world, player);
                });
                player.awardStat(Stats.ITEM_USED.get(this));
                player.swing(hand, SwingAnimation.DEFAULT, true);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.raid_eradicator.tooltip")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{
                Mode.BALANCED
        };
    }
}