package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
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
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * An item that can be used to {@code teleport} to the {@code surface.}
 */
public class EnderThrusterItem extends Item implements EyeItem {

    public EnderThrusterItem(Settings settings) {
        super(settings.rarity(Rarity.RARE).maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENTITY_ENDERMAN_AMBIENT, world, player);
            stack.decrement(1);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.BLUE), false);
            player.swingHand(hand, true);
            player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
        } else if (world.getRegistryKey() == World.NETHER) {
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.ender_thruster.wrong_dimension"), Formatting.AQUA, Formatting.WHITE);
        } else {
            int topY = world.getTopY(Heightmap.Type.MOTION_BLOCKING, player.getBlockX(), player.getBlockZ());
            BlockPos topPos = new BlockPos(player.getBlockX(), topY - 1, player.getBlockZ());
            double playerY = player.getY();

            boolean canTeleport = topY != playerY && !(playerY > topY);

            if (!canTeleport) {
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"));
            } else {
                player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsAsTicks(10));

                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);

                this.decrementIfPossible(player, stack);

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

                ModUtil.completeStepS2C(TutorialStep.USE_ENTER_THRUSTER, player,
                        "speedrunnermod.tutorial_mode.ender_thruster_description",
                        "speedrunnermod.tutorial_mode.obtain_ender_matter");

                player.teleport(topPos.getX() + 0.5F, topY, topPos.getZ() + 0.5F, false);
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                world.sendEntityStatus(player, ModStatuses.ADD_BLUE_PORTAL_PARTICLES);
                this.playThrowSound(world, player);
                this.playTeleportSound(world, player);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.tooltip")
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