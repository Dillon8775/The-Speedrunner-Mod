package net.dillon.speedrunnermod.item.eye;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.Mode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * An item that forces the {@code ender dragon} to {@code perch.}
 */
public class DragonsPearlItem extends Item implements SpeedrunnerItem {

    public DragonsPearlItem(Properties settings) {
        super(settings.stacksTo(16).rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENDER_EYE_DEATH, world, player);
            stack.shrink(1);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled_twomode").withStyle(ChatFormatting.LIGHT_PURPLE));
            player.swing(hand, SwingAnimation.DEFAULT, true);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, Items.BLAZE_POWDER);
            player.spawnAtLocation((ServerLevel)world, Items.FIRE_CHARGE);
            player.spawnAtLocation((ServerLevel)world, ModItems.SPEEDRUNNERS_EYE);
        } else if (world.dimension() != Level.END) {
            this.playPitchedLaunchSound(5.0F, world, player);
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.dragons_pearl.wrong_dimension").withStyle(ChatFormatting.LIGHT_PURPLE));
        } else {
            List<EnderDragon> dragons = ModHelper.getEntitiesWithinRange(world, EnderDragon.class, player, common().advanced.dragonsPearlSearchRadius.getCurrentValue());

            if (dragons.isEmpty()) {
                this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, 3.0F, world, player);
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.dragons_pearl.cannot_find_dragon").withStyle(ChatFormatting.LIGHT_PURPLE));
            } else {
                EnderDragon enderDragon = dragons.get(0);
                if (!isDragonAlreadyPerchingOrPerched(enderDragon) && !isDragonDead(enderDragon)) {
                    this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, 2.0F, 0.3F, world, player);
                    player.getCooldowns().addCooldown(this.getDefaultInstance(), Arithmetics.sas(30));

                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)player, stack);

                    this.decrementIfPossible(player, stack);

                    enderDragon.getPhaseManager().setPhase(EnderDragonPhase.LANDING);
                    this.playWorldSound(SoundEvents.ENDER_DRAGON_GROWL, 3.0F, 0.65F, world, player);

                    player.awardStat(Stats.ITEM_USED.get(this));
                    player.swing(hand, SwingAnimation.DEFAULT, true);
                    return InteractionResult.SUCCESS;
                } else {
                    if (!isDragonDead(enderDragon)) {
                        if (isDragonSitting(enderDragon)) {
                            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.dragons_pearl.already_perched").withStyle(ChatFormatting.LIGHT_PURPLE));
                        } else {
                            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.dragons_pearl.already_perching").withStyle(ChatFormatting.LIGHT_PURPLE));
                        }
                    } else {
                        ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.dragons_pearl.dragon_dead").withStyle(ChatFormatting.LIGHT_PURPLE));
                    }
                    this.playPitchedLaunchSound(5.0F, world, player);
                    player.swing(hand, SwingAnimation.DEFAULT, true);
                }
            }
        }

        return InteractionResult.CONSUME;
    }

    /**
     * Determines if the ender dragon is sitting or not.
     */
    private static boolean isDragonSitting(EnderDragon enderDragon) {
        return enderDragon.getPhaseManager().getCurrentPhase().getPhase() == EnderDragonPhase.SITTING_SCANNING ||
                enderDragon.getPhaseManager().getCurrentPhase().getPhase() == EnderDragonPhase.SITTING_FLAMING ||
                enderDragon.getPhaseManager().getCurrentPhase().getPhase() == EnderDragonPhase.SITTING_ATTACKING;
    }

    /**
     * Determines if the ender dragon is perching or already perched.
     */
    private static boolean isDragonAlreadyPerchingOrPerched(EnderDragon enderDragon) {
        return enderDragon.getPhaseManager().getCurrentPhase().getPhase() == EnderDragonPhase.LANDING || isDragonSitting(enderDragon);
    }

    /**
     * Checks to see if the ender dragon is dead.
     */
    private static boolean isDragonDead(EnderDragon enderDragon) {
        return enderDragon.getHealth() < 1.0F;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.dragons_pearl.tooltip")
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