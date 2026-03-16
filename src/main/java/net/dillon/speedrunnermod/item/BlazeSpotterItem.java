package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.Mode;

/**
 * An item that {@code teleports} the player to the {@code nearest blaze spawner.}
 */
public class BlazeSpotterItem extends Item implements EyeItem {

    public BlazeSpotterItem(Properties settings) {
        super(settings.stacksTo(16));
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.BLAZE_SHOOT, world, player);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled").withStyle(ChatFormatting.GOLD));
            player.swing(hand, true);
            stack.shrink(1);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, Items.FIRE_CHARGE);
        } else if (world.dimension() != Level.NETHER) {
            this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, 5.0F, world, player);
            ModUtil.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension"), ChatFormatting.GOLD, ChatFormatting.WHITE);
        } else {
            player.sendSystemMessage(ModTexts.CALCULATING);
            BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerLevel)world, player.blockPosition());
            if (blazeSpawnerPos == null) {
                this.playPitchedLaunchSound(3.0F, world, player);
                ModUtil.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner"), ChatFormatting.GOLD, ChatFormatting.WHITE);
            } else {
                this.correctlyTeleport(world, blazeSpawnerPos, player, 1.0F);
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ModUtil.secondsAsTicks(world.getRandom().nextInt(4) + 7), 0, false, true, true));
                player.getCooldowns().addCooldown(this.getDefaultInstance(), ModUtil.secondsAsTicks(30));
                world.broadcastEntityEvent(player, ModStatuses.ADD_BLAZE_SMOKE_PARTICLES);
                this.playTeleportSound(world, player);
                this.playWorldSound(SoundEvents.BLAZE_AMBIENT, 3.0F, 0.6F, world, player);
                this.playThrowSound(world, player);

                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayer)player, stack);

                player.awardStat(Stats.ITEM_USED.get(this));
                player.swing(hand, true);
                this.decrementIfPossible(player, stack);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }

    /**
     * Finds the nearest blaze spawner.
     */
    private BlockPos findNearestBlazeSpawner(ServerLevel world, BlockPos fortressPos) {
        for (BlockPos pos : BlockPos.withinManhattan(fortressPos,
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().getFirst(),
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().get(1),
                options().advanced.blazeSpotterSearchRadius.getCurrentValue().get(2))) {
            if (world.getBlockState(pos).getBlock() == Blocks.SPAWNER) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof SpawnerBlockEntity) {
                    SpawnerBlockEntity spawnerBlockEntity = (SpawnerBlockEntity) blockEntity;
                    if (spawnerBlockEntity.getSpawner().getOrCreateDisplayEntity(world, pos).getType() == EntityType.BLAZE) {
                        this.removeObstructions(world, pos);
                        return pos.immutable();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.blaze_spotter.tooltip")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                Mode.BALANCED
        };
    }
}