package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that {@code teleports} the player to the {@code nearest blaze spawner.}
 */
public class BlazeSpotterItem extends Item implements TutorialMode {
    private boolean confirm = !options().client.confirmMessages;

    public BlazeSpotterItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (options().main.playingMode.easy()) {
                if (world.getRegistryKey() == World.NETHER) {
                    BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerWorld)world, player.getBlockPos());
                    if (blazeSpawnerPos != null) {
                        if (confirm) {
                            player.teleport(blazeSpawnerPos.getX() + 0.5F, blazeSpawnerPos.getY() + 1.0F, blazeSpawnerPos.getZ() + 0.5F, true);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 3.0F, 0.6F);
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.seconds(world.random.nextInt(4) + 7), 0, false, true, true));
                            player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(30));
                            if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && !options().tutorialMode.usedBlazeSpotter) {
                                this.send("speedrunnermod.tutorial_mode.used_blaze_spotter.easy", player);
                                this.send("speedrunnermod.tutorial_mode.obtain_speedrunners_eye.easy", player);
                                options().tutorialMode.usedBlazeSpotter = true;
                                ModOptions.saveConfig();
                            }
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }
                        } else {
                            player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.found_blaze_spawner").formatted(ModUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                            player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.confirm"), false);
                        }
                        if (options().client.confirmMessages) {
                            confirm = !confirm;
                        }
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner").formatted(ModUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    player.sendMessage(Text.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GOLD), false);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                itemStack.decrement(1);
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
                player.dropItem((ServerWorld)world, Items.LAVA_BUCKET);
            }
        }

        return ActionResult.CONSUME;
    }

    /**
     * Finds the nearest blaze spawner.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private BlockPos findNearestBlazeSpawner(ServerWorld world, BlockPos fortressPos) {
        for (BlockPos pos : BlockPos.iterate(fortressPos.add(options().advanced.blazeSpotterDistanceXYZ[0], options().advanced.blazeSpotterDistanceXYZ[1], options().advanced.blazeSpotterDistanceXYZ[2]), fortressPos.add(options().advanced.blazeSpotterDistanceXYZ[3], options().advanced.blazeSpotterDistanceXYZ[4], options().advanced.blazeSpotterDistanceXYZ[5]))) {
            if (world.getBlockState(pos).getBlock() == Blocks.SPAWNER) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof MobSpawnerBlockEntity) {
                    MobSpawnerBlockEntity spawnerBlockEntity = (MobSpawnerBlockEntity) blockEntity;
                    if (spawnerBlockEntity.getLogic().getRenderedEntity(world, pos).getType() == EntityType.BLAZE) {
                        if (!world.getBlockState(pos.up()).isAir() || !world.getBlockState(pos.up(1)).isAir()) {
                            for (int i = 1; i < 3; i++) {
                                world.setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                            }
                        }
                        return pos.toImmutable();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.blaze_spotter.tooltip"));
            ModUtil.stateOfTheArtItem(tooltip);
        }
    }
}