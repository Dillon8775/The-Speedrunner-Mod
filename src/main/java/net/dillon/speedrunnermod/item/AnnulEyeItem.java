package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * <p>An {@code eye of ender} item that locates the {@code exact distance} of the {@code nearest stronghold} (in meters/blocks) and tells it to the player.</p>
 * <p>Additionally, this item allows the player to {@code teleport directly} to the nearest stronghold's {@code nearest portal room.}</p>
 */
public class AnnulEyeItem extends Item implements EyeItem {

    public AnnulEyeItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (isBalancedMode()) {
            this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, world, player);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.LIGHT_PURPLE), false);
            player.swingHand(hand, true);
            stack.decrement(1);
            player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            player.dropItem((ServerWorld)world, Items.ENDER_EYE);
            player.dropItem((ServerWorld)world, Items.BLAZE_POWDER);
        } else if (world.getRegistryKey() != World.OVERWORLD) {
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.wrong_dimension"), Formatting.GREEN, Formatting.WHITE);
        } else {
            boolean hasEnderEye = player.getInventory().contains(new ItemStack(Items.ENDER_EYE));
            boolean hasEnderPearl = player.getInventory().contains(new ItemStack(Items.ENDER_PEARL));
            boolean hasRequiredItems = (hasEnderEye && hasEnderPearl) || player.getAbilities().creativeMode;

            if (!hasRequiredItems) {
                if (!hasEnderEye && !hasEnderPearl) {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.has_none").formatted(Formatting.DARK_GREEN));
                } else if (!hasEnderEye) {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_eye").formatted(Formatting.GREEN));
                } else {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_pearl").formatted(Formatting.BLUE));
                }
                player.swingHand(hand, true);
                this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 5.0F, world, player);
            } else {
                player.sendMessage(ModTexts.CALCULATING, false);
                BlockPos centerBlock = this.findPortalRoom(world, player.getBlockPos());

                if (centerBlock == null) {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.couldnt_find_portal_room").formatted(Formatting.RED));
                } else {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_annul.teleporting").formatted(Formatting.LIGHT_PURPLE).formatted(Formatting.BOLD));
                    player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesAsTicks(1));
                    this.playThrowSound(world, player);

                    TaskScheduler.schedule(ModUtil.secondsAsTicks(3), () -> {
                        this.correctlyTeleport(world, centerBlock, player, 0.0F);
                        world.sendEntityStatus(player, ModStatuses.ADD_BLUE_PORTAL_PARTICLES);
                        this.playWorldSound(SoundEvents.BLOCK_END_PORTAL_SPAWN, world, player);
                        this.playTeleportSound(world, player);
                        ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);
                    });

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    player.swingHand(hand, true);
                    this.playThrowSound(world, player);

                    this.decrementIfPossible(player, stack);
                    this.decrementIfPossible(player, Items.ENDER_EYE);
                    this.decrementIfPossible(player, Items.ENDER_PEARL);

                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.CONSUME;
    }

    /**
     * Finds the nearest stronghold, to then find the closest end portal frame block inside of it.
     */
    private BlockPos findPortalRoom(World world, BlockPos startPos) {
        BlockPos strongholdPos = ((ServerWorld)world).locateStructure(StructureTags.EYE_OF_ENDER_LOCATED, startPos, 100, false);

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
    private BlockPos findEndPortalFrame(World world, BlockPos strongholdPos) {
        for (BlockPos pos : BlockPos.iterateOutwards(strongholdPos,
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
    private BlockPos getCenterPos(World world, BlockPos portalPos) {
        Direction facing = world.getBlockState(portalPos).get(HorizontalFacingBlock.FACING);
        BlockPos left;
        BlockPos right;
        BlockPos centerPos = portalPos;
        if (facing.equals(Direction.NORTH)) {
            left = portalPos.add(-1, 0, 0);
            right = portalPos.add(1, 0, 0);
            // if portalPos is center north block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.add(0, 0, -2);
            }
            // if portalPos is left north block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.add(1, 0, 0))) {
                centerPos = portalPos.add(1, 0, -2);
            }
            // if portalPos is right north block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.add(-1, 0, 0))) {
                centerPos = portalPos.add(-1, 0, -2);
            }
        } else if (facing.equals(Direction.EAST)) {
            left = portalPos.add(0, 0, -1);
            right = portalPos.add(0, 0, 1);
            // if portalPos is center east block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.add(2, 0, 0);
            }
            // if portalPos is left east block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.add(0, 0, 1))) {
                centerPos = portalPos.add(2, 0, 1);
            }
            // if portalPos is right east block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.add(0, 0, -1))) {
                centerPos = portalPos.add(2, 0, -1);
            }
        } else if (facing.equals(Direction.WEST)) {
            left = portalPos.add(0, 0, 1);
            right = portalPos.add(0, 0, -1);
            // if portalPos is center west block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.add(-2, 0, 0);
            }
            // if portalPos is left west block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.add(0, 0, -1))) {
                centerPos = portalPos.add(-2, 0, -1);
            }
            // if portalPos is right west block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.add(1, 0, 0))) {
                centerPos = portalPos.add(-2, 0, 1);
            }
        } else {
            left = portalPos.add(1, 0, 0);
            right = portalPos.add(-1, 0, 0);
            // if portalPos is center south block
            if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, right)) {
                centerPos = portalPos.add(0, 0, 2);
            }
            // if portalPos is left south block
            else if (this.isEndPortalFrame(world, right) && this.isEndPortalFrame(world, right.add(-1, 0, 0))) {
                centerPos = portalPos.add(-1, 0, 2);
            }
            // if portalPos is right south block
            else if (this.isEndPortalFrame(world, left) && this.isEndPortalFrame(world, left.add(1, 0, 0))) {
                centerPos = portalPos.add(1, 0, 2);
            }
        }

        if (centerPos != portalPos) {
            BlockPos standingPos = world.getBlockState(centerPos).isOf(Blocks.END_PORTAL)
                    ? centerPos.up()
                    : centerPos;
            world.setBlockState(standingPos, ModBlocks.THRUSTED_BLOCK.getDefaultState());
            this.removeObstructions(world, standingPos);
            return standingPos.up();
        }
        world.setBlockState(centerPos, ModBlocks.THRUSTED_BLOCK.getDefaultState());
        this.removeObstructions(world, centerPos);
        return centerPos.up();
    }

    /**
     * @return {@code true} if the {@code searchingPos} is an end portal frame.
     */
    private boolean isEndPortalFrame(World world, BlockPos searchingPos) {
        return world.getBlockState(searchingPos).getBlock().equals(Blocks.END_PORTAL_FRAME);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line1")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line2")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                ModOptions.Mode.BALANCED
        };
    }
}