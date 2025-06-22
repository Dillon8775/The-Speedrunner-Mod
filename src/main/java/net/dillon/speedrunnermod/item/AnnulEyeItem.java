package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.network.ServerPlayerEntity;
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

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * <p>An {@code eye of ender} item that locates the {@code exact distance} of the {@code nearest stronghold} (in meters/blocks) and tells it to the player.</p>
 * <p>Additionally, this item allows the player to {@code teleport directly} to the nearest stronghold's {@code nearest portal room.}</p>
 */
public class AnnulEyeItem extends Item implements StateOfTheArtItem {

    public AnnulEyeItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.OVERWORLD) {
                if (!options().main.playingMode.balanced()) {
                    boolean hasEnderEye = player.getInventory().contains(new ItemStack(Items.ENDER_EYE));
                    boolean hasEnderPearl = player.getInventory().contains(new ItemStack(Items.ENDER_PEARL));
                    boolean hasRequiredItems = hasEnderEye && hasEnderPearl;

                    if (player.getAbilities().creativeMode) {
                        hasRequiredItems = true;
                    }

                    if (hasRequiredItems) {
                        player.sendMessage(this.calculatingText(), false);
                        BlockPos endPortalFrameBlock = findPortalRoom(world, player.getBlockPos());

                        if (endPortalFrameBlock != null) {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.teleporting").formatted(Formatting.LIGHT_PURPLE).formatted(Formatting.BOLD), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                            player.teleport(endPortalFrameBlock.getX() + 0.5F, endPortalFrameBlock.getY() + 1.0F, endPortalFrameBlock.getZ() + 0.5F, true);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                            player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesInTicks(1));

                            ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, itemStack);

                            options().tutorialMode.completeStep(TutorialStep.USE_ANNUL_EYE, player, "speedrunnermod.tutorial_mode.enter_end");

                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                                this.decrementItem(player, Items.ENDER_EYE);
                                this.decrementItem(player, Items.ENDER_PEARL);
                            }

                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                            player.incrementStat(Stats.USED.getOrCreateStat(this));
                            player.swingHand(hand, true);
                            return ActionResult.SUCCESS;
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.couldnt_find_portal_room").formatted(Formatting.RED), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                        }
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                        player.swingHand(hand, true);
                        if (!hasEnderEye && !hasEnderPearl) {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.has_none").formatted(Formatting.DARK_GREEN), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                        } else if (!hasEnderEye) {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_eye").formatted(Formatting.GREEN), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.no_ender_pearl").formatted(Formatting.BLUE), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                        }
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.LIGHT_PURPLE), false);
                    player.swingHand(hand, true);
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 0.5F);
                    itemStack.decrement(1);
                    player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                    player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
                    player.dropItem((ServerWorld)world, Items.BLAZE_POWDER);
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_annul.wrong_dimension").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.GREEN, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
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

    /**
     * Decrements an item from the player's inventory.
     */
    private void decrementItem(PlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                stack.decrement(1);
                break;
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line1")
                .formatted(options().main.playingMode.balanced() ? Formatting.STRIKETHROUGH : Formatting.RESET));
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_annul.tooltip.line2")
                .formatted(options().main.playingMode.balanced() ? Formatting.STRIKETHROUGH : Formatting.RESET));
        if (options().main.playingMode.balanced()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.state_of_the_art_item.disabled")
                    .formatted(Formatting.RED).formatted(Formatting.BOLD).formatted(Formatting.ITALIC));
        }
    }
}