package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
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

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that can be used to {@code teleport} to the {@code surface.}
 */
public class EnderThrusterItem extends Item implements StateOfTheArtItem {
    private boolean confirm = !options().client.confirmMessages;

    public EnderThrusterItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (options().main.playingMode.easy()) {
                if (!(world.getRegistryKey() == World.NETHER)) {
                    int y = world.getTopY(Heightmap.Type.MOTION_BLOCKING, player.getBlockX(), player.getBlockZ());
                    BlockPos pos = new BlockPos(player.getBlockX(), y - 1, player.getBlockZ());
                    double playerY = ModUtil.roundToNearestTenthsPlace(player.getY());

                    if (y != playerY && !(playerY > y)) {
                        if (confirm) {
                            player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(10));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }

                            if (world.getBlockState(pos).getBlock() == Blocks.WATER) {
                                world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                            } else if (world.getBlockState(pos).getBlock() == Blocks.LAVA) {
                                world.setBlockState(pos, Blocks.BASALT.getDefaultState());
                            } else {
                                world.setBlockState(pos, ModBlocks.THRUSTED_BLOCK.getDefaultState());
                            }

                            boolean isAir = world.getBlockState(pos.up()).isAir() && world.getBlockState(pos.up(1)).isAir();
                            if (!isAir) {
                                for (int i = 1; i < 3; i++) {
                                    world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                                }
                            }

                            player.teleport(player.getX(), y, player.getZ(), true);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                            ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, itemStack);

                            options().tutorialMode.completeStep(TutorialStep.USE_ENTER_THRUSTER, player,
                                    "speedrunnermod.tutorial_mode.ender_thruster_description",
                                    options().main.playingMode.doom() ? "speedrunnermod.tutorial_mode.craft_wither_bone" :
                                            "speedrunnermod.tutorial_mode.craft_dragons_sword");
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.confirm").formatted(Formatting.WHITE), false);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        }
                        if (options().client.confirmMessages) {
                            confirm = !confirm;
                        }
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"), options().client.itemMessages.isActionbar());
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.AQUA, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.BLUE), false);
                player.swingHand(hand, true);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                itemStack.decrement(1);
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.ender_thruster.tooltip"));
            this.addStateOfTheArtItemTooltip(tooltip);
        }
    }
}