package net.dillon.speedrunnermod.item.eye;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Consumer;

/**
 * An item that can be used to {@code teleport} to the {@code surface.}
 */
public class EnderThrusterItem extends Item implements SpeedrunnerItem {

    public EnderThrusterItem(Properties settings) {
        super(settings.rarity(Rarity.RARE).stacksTo(1));
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENDERMAN_AMBIENT, world, player);
            stack.shrink(1);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled_twomode").withStyle(ChatFormatting.BLUE));
            player.swing(hand, SwingAnimation.DEFAULT, true);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, ModItems.SPEEDRUNNERS_EYE);
        } else if (world.dimension() == Level.NETHER) {
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.ender_thruster.wrong_dimension"), ChatFormatting.AQUA, ChatFormatting.WHITE);
        } else {
            int topY = world.getHeight(Heightmap.Types.MOTION_BLOCKING, player.getBlockX(), player.getBlockZ());
            BlockPos topPos = new BlockPos(player.getBlockX(), topY - 1, player.getBlockZ());
            double playerY = player.getY();

            boolean canTeleport = topY != playerY && !(playerY > topY);

            if (!canTeleport) {
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"));
            } else {
                player.getCooldowns().addCooldown(this.getDefaultInstance(), TickCalculator.seconds(10));

                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)player, stack);

                this.decrementIfPossible(player, stack);

                if (world.getBlockState(topPos).getBlock() == Blocks.WATER) {
                    world.setBlockAndUpdate(topPos, Blocks.FROSTED_ICE.defaultBlockState());
                } else if (world.getBlockState(topPos).getBlock() == Blocks.LAVA) {
                    world.setBlockAndUpdate(topPos, Blocks.BASALT.defaultBlockState());
                } else {
                    world.setBlockAndUpdate(topPos, ModBlocks.THRUSTED_BLOCK.defaultBlockState());
                }

                this.removeObstructions(world, topPos);

                player.randomTeleport(topPos.getX() + 0.5F, topY, topPos.getZ() + 0.5F, false);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.swing(hand, SwingAnimation.DEFAULT, true);
                world.broadcastEntityEvent(player, ModStatuses.ADD_BLUE_PORTAL_PARTICLES);
                this.playThrowSound(world, player);
                this.playTeleportSound(world, player);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.ender_thruster.tooltip")
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