package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
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

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * An item that can be used to {@code teleport} to the {@code surface.}
 */
public class EnderThrusterItem extends Item implements StateOfTheArtItem {

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
                    double playerY = player.getY();

                    if (y != playerY && !(playerY > y)) {
                        player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsInTicks(10));
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

                        ModUtil.completeStepS2C(TutorialStep.USE_ENTER_THRUSTER, player,
                                "speedrunnermod.tutorial_mode.ender_thruster_description",
                                "speedrunnermod.tutorial_mode.craft_wither_bone");
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.couldnt_teleport"), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.ender_thruster.wrong_dimension").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.AQUA, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.ender_thruster.tooltip"));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }
}