package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * An item that can be used to {@code teleport} to the {@code surface.}
 */
public class EnderThrusterItem extends Item implements EyeItem {

    public EnderThrusterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient()) {
            if (!isBalancedMode()) {
                if (!(world.getRegistryKey() == World.NETHER)) {
                    int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, player.getBlockX(), player.getBlockZ());
                    BlockPos topPos = new BlockPos(player.getBlockX(), topY - 1, player.getBlockZ());
                    double playerY = player.getY();

                    boolean validTeleport = topY != playerY && !(playerY > topY);
                    boolean bl = isDoomMode() ?
                            validTeleport && (playerY < 0 ||
                                    player.getEntityWorld().getBiome(topPos).isIn(BiomeTags.IS_MOUNTAIN) ||
                                    player.getEntityWorld().getBiome(topPos).isIn(BiomeTags.IS_HILL)) :
                            validTeleport;

                    if (bl) {
                        player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsAsTicks(10));

                        ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);

                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }

                        if (world.getBlockState(topPos).getBlock() == Blocks.WATER) {
                            world.setBlockState(topPos, Blocks.FROSTED_ICE.getDefaultState());
                        } else if (world.getBlockState(topPos).getBlock() == Blocks.LAVA) {
                            world.setBlockState(topPos, Blocks.BASALT.getDefaultState());
                        } else {
                            world.setBlockState(topPos, ModBlocks.THRUSTED_BLOCK.getDefaultState());
                        }

                        boolean isAir = world.getBlockState(topPos.up()).isAir() && world.getBlockState(topPos.up(1)).isAir();
                        if (!isAir) {
                            for (int i = 1; i < 3; i++) {
                                world.setBlockState(topPos.up(i), Blocks.AIR.getDefaultState(), 3);
                            }
                        }

                        player.teleport(player.getX(), topY, player.getZ(), false);
                        world.sendEntityStatus(player, ModStatuses.ADD_BLUE_PORTAL_PARTICLES);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                        ModUtil.completeStepS2C(TutorialStep.USE_ENTER_THRUSTER, player,
                                "speedrunnermod.tutorial_mode.ender_thruster_description",
                                "speedrunnermod.tutorial_mode.craft_wither_bone");
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        if (!validTeleport) {
                            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"));
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.cannot_teleport_doom_mode"), false);
                        }
                    }
                } else {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.ender_thruster.wrong_dimension"), Formatting.AQUA, Formatting.WHITE);
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.BLUE), false);
                player.swingHand(hand, true);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                stack.decrement(1);
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.tooltip"));
        if (isBalancedMode()) {
            textConsumer.accept(ModTexts.STATE_OF_THE_ART_ITEM_DISABLED);
        } else if (isDoomMode()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.doom_mode.tooltip.line1").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.doom_mode.tooltip.line2").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.doom_mode.tooltip.line3").formatted(Formatting.GRAY));
        }
    }
}