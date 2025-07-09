package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * An item that {@code teleports} the player to the {@code nearest blaze spawner.}
 */
public class BlazeSpotterItem extends Item implements StateOfTheArtItem {

    public BlazeSpotterItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (isEasyMode()) {
                if (world.getRegistryKey() == World.NETHER) {
                    player.sendMessage(ModTexts.CALCULATING, false);
                    BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerWorld)world, player.getBlockPos());
                    if (blazeSpawnerPos != null) {
                        player.teleport(blazeSpawnerPos.getX() + 0.5F, blazeSpawnerPos.getY() + 1.0F, blazeSpawnerPos.getZ() + 0.5F, true);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0F, 1.0F);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 3.0F, 0.6F);
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsAsTicks(world.random.nextInt(4) + 7), 0, false, true, true));
                        player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsAsTicks(30));

                        ModUtil.completeStepS2C(TutorialStep.USE_BLAZE_SPOTTER, player,
                                "speedrunnermod.tutorial_mode.used_blaze_spotter",
                                "speedrunnermod.tutorial_mode.craft_speedrunners_eye");

                        ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, itemStack);

                        if (!player.getAbilities().creativeMode) {
                            itemStack.decrement(1);
                        }
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner"), Formatting.GOLD, Formatting.WHITE);
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension"), Formatting.GOLD, Formatting.WHITE);
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GOLD), false);
                player.swingHand(hand, true);
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
    @AI
    private BlockPos findNearestBlazeSpawner(ServerWorld world, BlockPos fortressPos) {
        for (BlockPos pos : BlockPos.iterateOutwards(fortressPos,
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().getFirst(),
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().get(1),
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().get(2))) {
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.blaze_spotter.tooltip"));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }
}