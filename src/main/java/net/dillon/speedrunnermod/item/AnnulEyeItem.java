package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * <p>An {@code eye of ender} item that locates the {@code exact distance} of the {@code nearest stronghold} (in meters/blocks) and tells it to the player.</p>
 * <p>Additionally, this item allows the player to {@code teleport directly} to the nearest stronghold's {@code nearest portal room.}</p>
 */
public class AnnulEyeItem extends Item implements EyeItem, TutorialMode {
    private boolean confirm = !options().client.confirmMessages;

    public AnnulEyeItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.OVERWORLD) {
                if (options().main.playingMode.easy()) {
                    ItemStack enderEye = new ItemStack(Items.ENDER_EYE);
                    ItemStack enderPearl = new ItemStack(Items.ENDER_PEARL);
                    boolean hasEnderEye = player.getInventory().contains(enderEye);
                    boolean hasEnderPearl = player.getInventory().contains(enderPearl);
                    boolean hasRequiredItems = hasEnderEye && hasEnderPearl;

                    if (player.getAbilities().creativeMode) {
                        hasRequiredItems = true;
                    }

                    if (hasRequiredItems) {
                        player.sendMessage(this.calculatingText(), false);
                        BlockPos endPortalFrameBlock = findPortalRoom(world, player.getBlockPos());

                        if (endPortalFrameBlock != null) {
                            if (confirm) {
                                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.found_portal_room").formatted(Formatting.GREEN), false);
                                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.teleporting").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                                player.teleport(endPortalFrameBlock.getX() + 0.5F, endPortalFrameBlock.getY() + 1.0F, endPortalFrameBlock.getZ() + 0.5F, true);
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                                player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(60));

                                if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && options().tutorialMode.usedSpeedrunnersEye && options().tutorialMode.obtainedDragonsPearl && options().tutorialMode.obtainedAnnulEye && !options().tutorialMode.usedAnnulEyeTeleporter) {
                                    this.send("speedrunnermod.tutorial_mode.used_annul_eye_teleporter.easy", player);
                                    options().tutorialMode.usedAnnulEyeTeleporter = true;
                                    ModOptions.saveConfig();
                                }

                                if (!player.getAbilities().creativeMode) {
                                    itemStack.decrement(1);
                                    for (int i = 0; i < player.getInventory().size(); i++) {
                                        ItemStack stack = player.getInventory().getStack(i);
                                        if (stack.isOf(Items.ENDER_EYE)) {
                                            stack.decrement(1);
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < player.getInventory().size(); i++) {
                                        ItemStack stack = player.getInventory().getStack(i);
                                        if (stack.isOf(Items.ENDER_PEARL)) {
                                            stack.decrement(1);
                                            break;
                                        }
                                    }
                                }
                            } else {
                                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.found_portal_room").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.confirm"), false);
                            }

                            if (options().client.confirmMessages) {
                                confirm = !confirm;
                            }

                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            player.incrementStat(Stats.USED.getOrCreateStat(this));
                            player.swingHand(hand, true);
                            return ActionResult.SUCCESS;
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.couldnt_find_portal_room").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                        }
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                        player.swingHand(hand, true);
                        if (!hasEnderEye && !hasEnderPearl) {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.has_none").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                        } else if (!hasEnderEye) {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_eye").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_pearl").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                        }
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.LIGHT_PURPLE), false);
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.GREEN, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.CONSUME;
    }

    /**
     * Finds the nearest stronghold, to then find the closest end portal frame block inside of it.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private BlockPos findPortalRoom(World world, BlockPos startPos) {
        BlockPos strongholdPos = ((ServerWorld)world).locateStructure(StructureTags.EYE_OF_ENDER_LOCATED, startPos, 100, false);

        if (strongholdPos != null) {
            BlockPos portalRoomPos = findEndPortalFrame(world, strongholdPos);

            if (portalRoomPos != null) {
                return new BlockPos(portalRoomPos.getX(), portalRoomPos.getY(), portalRoomPos.getZ());
            }
        }

        return null;
    }

    /**
     * Finds the nearest end portal frame block inside the stronghold.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private BlockPos findEndPortalFrame(World world, BlockPos strongholdPos) {
        for (BlockPos pos : BlockPos.iterate(strongholdPos.add(options().advanced.annulEyePortalRoomDistanceXYZ[0], options().advanced.annulEyePortalRoomDistanceXYZ[1], options().advanced.annulEyePortalRoomDistanceXYZ[2]), strongholdPos.add(options().advanced.annulEyePortalRoomDistanceXYZ[3], options().advanced.annulEyePortalRoomDistanceXYZ[4], options().advanced.annulEyePortalRoomDistanceXYZ[5]))) {
            if (world.getBlockState(pos).getBlock().equals(Blocks.END_PORTAL_FRAME)) {
                return pos.toImmutable();
            }
        }

        return null;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            if (options().main.playingMode.easy()) {
                tooltip.add(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line2").formatted(Formatting.GRAY));
            }
        }
    }
}