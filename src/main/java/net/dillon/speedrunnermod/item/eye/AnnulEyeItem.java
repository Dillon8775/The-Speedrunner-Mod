package net.dillon.speedrunnermod.item.eye;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * <p>An {@code eye of ender} item that locates the {@code exact distance} of the {@code nearest stronghold} (in meters/blocks) and tells it to the player.</p>
 * <p>Additionally, this item allows the player to {@code teleport directly} to the nearest stronghold's {@code nearest portal room.}</p>
 */
public class AnnulEyeItem extends Item implements SpeedrunnerItem {

    public AnnulEyeItem(Properties settings) {
        super(settings
                .component(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.END_CITIES)
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (world.dimension() == Level.END) {
            ModHelper.sendCalculatingMessage(player);

            BlockPos playerpos = player.blockPosition();
            ServerLevel serverWorld = (ServerLevel)world;
            ModHelper.findStructureAndShoot(world, player, stack, stack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
            BlockPos blockPos = serverWorld.findNearestMapStructure(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE), playerpos, 100, false);
            int structureDistance = Mth.floor(ModHelper.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));

            ModHelper.sendMessageWithActionbarPref(player, this.locationText(structureDistance, this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
            this.playPitchedLaunchSound(0.4F, world, player);

            player.awardStat(Stats.ITEM_USED.get(this));
            player.swing(hand, true);

            this.decrementIfPossible(player, stack);

            return InteractionResult.SUCCESS;
        } else if (isBalancedMode()) {
            this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, world, player);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled_twomode").withStyle(ChatFormatting.LIGHT_PURPLE));
            player.swing(hand, true);
            stack.shrink(1);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_EYE);
            player.spawnAtLocation((ServerLevel)world, Items.BLAZE_POWDER);
        } else if (world.dimension() != Level.OVERWORLD && world.dimension() != Level.END) {
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye_of_annul.wrong_dimension"), ChatFormatting.GREEN, ChatFormatting.WHITE);
        } else {
            ModHelper.sendCalculatingMessage(player);
            BlockPos centerBlock = this.findPortalRoom(world, player.blockPosition());

            if (centerBlock == null) {
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye_of_annul.couldnt_find_portal_room").withStyle(ChatFormatting.RED));
            } else {
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye_of_annul.teleporting").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.BOLD));
                player.getCooldowns().addCooldown(this.getDefaultInstance(), TickCalculator.minutes(1));
                this.playThrowSound(world, player);

                this.correctlyTeleport(world, centerBlock, player, 0.0F);
                world.broadcastEntityEvent(player, ModStatuses.ADD_BLUE_PORTAL_PARTICLES);
                this.playWorldSound(SoundEvents.END_PORTAL_SPAWN, world, player);
                this.playTeleportSound(world, player);
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)player, stack);

                player.awardStat(Stats.ITEM_USED.get(this));
                player.swing(hand, true);
                this.playThrowSound(world, player);

                this.decrementIfPossible(player, stack);

                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    /**
     * Finds the nearest stronghold, to then find the closest end portal frame block inside of it.
     */
    private BlockPos findPortalRoom(Level world, BlockPos startPos) {
        BlockPos strongholdPos = ((ServerLevel)world).findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, startPos, 100, false);

        if (strongholdPos != null) {
            BlockPos portalRoomPos = this.findEndPortalFrame(world, strongholdPos);

            if (portalRoomPos != null) {
                return new BlockPos(portalRoomPos.getX(), portalRoomPos.getY(), portalRoomPos.getZ());
            }
        }

        return null;
    }

    /**
     * Finds the nearest end portal frame block inside the stronghold.
     */
    private BlockPos findEndPortalFrame(Level world, BlockPos strongholdPos) {
        for (BlockPos pos : BlockPos.withinManhattan(strongholdPos,
                options().advanced.annulEyeSearchRadius.getCurrentValue().getFirst(),
                options().advanced.annulEyeSearchRadius.getCurrentValue().get(1),
                options().advanced.annulEyeSearchRadius.getCurrentValue().get(2))) {
            if (this.isEndPortalFrame(world, pos)) {
                return this.getCenterPos(world, pos);
            }
        }

        return null;
    }

    /**
     * @return the center pos from the found portal block.
     */
    private BlockPos getCenterPos(Level world, BlockPos portalPos) {
        Direction facing = world.getBlockState(portalPos).getValue(HorizontalDirectionalBlock.FACING);
        BlockPos left;
        BlockPos right;
        BlockPos centerPos = portalPos;
        if (facing.equals(Direction.NORTH)) {
            left = portalPos.offset(-1, 0, 0);
            right = portalPos.offset(1, 0, 0);
            // if portalPos is center north block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.offset(0, 0, -2);
            }
            // if portalPos is left north block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.offset(1, 0, 0))) {
                centerPos = portalPos.offset(1, 0, -2);
            }
            // if portalPos is right north block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.offset(-1, 0, 0))) {
                centerPos = portalPos.offset(-1, 0, -2);
            }
        } else if (facing.equals(Direction.EAST)) {
            left = portalPos.offset(0, 0, -1);
            right = portalPos.offset(0, 0, 1);
            // if portalPos is center east block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.offset(2, 0, 0);
            }
            // if portalPos is left east block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.offset(0, 0, 1))) {
                centerPos = portalPos.offset(2, 0, 1);
            }
            // if portalPos is right east block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.offset(0, 0, -1))) {
                centerPos = portalPos.offset(2, 0, -1);
            }
        } else if (facing.equals(Direction.WEST)) {
            left = portalPos.offset(0, 0, 1);
            right = portalPos.offset(0, 0, -1);
            // if portalPos is center west block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.offset(-2, 0, 0);
            }
            // if portalPos is left west block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.offset(0, 0, -1))) {
                centerPos = portalPos.offset(-2, 0, -1);
            }
            // if portalPos is right west block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.offset(1, 0, 0))) {
                centerPos = portalPos.offset(-2, 0, 1);
            }
        } else {
            left = portalPos.offset(1, 0, 0);
            right = portalPos.offset(-1, 0, 0);
            // if portalPos is center south block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.offset(0, 0, 2);
            }
            // if portalPos is left south block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.offset(-1, 0, 0))) {
                centerPos = portalPos.offset(-1, 0, 2);
            }
            // if portalPos is right south block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.offset(1, 0, 0))) {
                centerPos = portalPos.offset(1, 0, 2);
            }
        }

        if (centerPos != portalPos) {
            BlockPos standingPos = world.getBlockState(centerPos).is(Blocks.END_PORTAL)
                    ? centerPos.above()
                    : centerPos;
            world.setBlockAndUpdate(standingPos, ModBlocks.THRUSTED_BLOCK.defaultBlockState());
            this.removeObstructions(world, standingPos);
            return standingPos.above();
        }
        world.setBlockAndUpdate(centerPos, ModBlocks.THRUSTED_BLOCK.defaultBlockState());
        this.removeObstructions(world, centerPos);
        return centerPos.above();
    }

    /**
     * @return {@code true} if the {@code searchingPos} is an end portal frame.
     */
    private boolean isEndPortalFrame(Level world, BlockPos searchingPos) {
        return world.getBlockState(searchingPos).getBlock().equals(Blocks.END_PORTAL_FRAME);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.eye_of_annul.tooltip.line1")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.eye_of_annul.tooltip.line2")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{
                Mode.BALANCED
        };
    }
}