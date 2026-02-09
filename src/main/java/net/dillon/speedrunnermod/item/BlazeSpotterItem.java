package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.*;

/**
 * An item that {@code teleports} the player to the {@code nearest blaze spawner.}
 */
public class BlazeSpotterItem extends Item implements EyeItem {

    public BlazeSpotterItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENTITY_BLAZE_SHOOT, world, player);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GOLD), false);
            player.swingHand(hand, true);
            stack.decrement(1);
            player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
        } else if (world.getRegistryKey() != World.NETHER) {
            this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 5.0F, world, player);
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension"), Formatting.GOLD, Formatting.WHITE);
        } else {
            player.sendMessage(ModTexts.CALCULATING, false);
            BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerWorld)world, player.getBlockPos());
            if (blazeSpawnerPos == null) {
                this.playPitchedLaunchSound(3.0F, world, player);
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner"), Formatting.GOLD, Formatting.WHITE);
            } else {
                player.teleport(blazeSpawnerPos.getX() + 0.5F, blazeSpawnerPos.getY() + 1.0F, blazeSpawnerPos.getZ() + 0.5F, false);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsAsTicks(world.random.nextInt(4) + 7), 0, false, true, true));
                player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsAsTicks(30));
                world.sendEntityStatus(player, ModStatuses.ADD_BLAZE_SMOKE_PARTICLES);
                this.playTeleportSound(world, player);
                this.playWorldSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 3.0F, 0.6F, world, player);
                this.playThrowSound(world, player);

                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);
                ModUtil.completeStepS2C(TutorialStep.USE_BLAZE_SPOTTER, player,
                        "speedrunnermod.tutorial_mode.used_blaze_spotter",
                        "speedrunnermod.tutorial_mode.craft_speedrunners_eye");

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                this.decrementIfPossible(player, stack);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    /**
     * Finds the nearest blaze spawner.
     */
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
        textConsumer.accept(Text.translatable("item.speedrunnermod.blaze_spotter.tooltip")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                Mode.BALANCED
        };
    }
}