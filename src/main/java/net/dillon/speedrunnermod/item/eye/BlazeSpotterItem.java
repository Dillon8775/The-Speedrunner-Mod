package net.dillon.speedrunnermod.item.eye;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.eum.Mode;
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
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

import java.util.function.Consumer;

import static net.dillon.dillonlib.util.Arithmetics.S_asTick;

/**
 * An item that {@code teleports} the player to the {@code nearest blaze spawner.}
 */
public class BlazeSpotterItem extends Item implements SpeedrunnerItem {

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
            player.swing(hand, SwingAnimation.DEFAULT, true);
            stack.shrink(1);
            player.spawnAtLocation((ServerLevel)world, Items.ENDER_PEARL);
            player.spawnAtLocation((ServerLevel)world, Items.FIRE_CHARGE);
        } else if (world.dimension() != Level.NETHER) {
            this.playWorldSound(SoundEvents.ENDER_EYE_LAUNCH, 5.0F, world, player);
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.blaze_spotter.wrong_dimension"), ChatFormatting.GOLD, ChatFormatting.WHITE);
        } else {
            ModHelper.sendCalculatingMessage(player);
            BlockPos blazeSpawnerPos = this.findNearestBlazeSpawner((ServerLevel)world, player.blockPosition());
            if (blazeSpawnerPos == null) {
                this.playPitchedLaunchSound(3.0F, world, player);
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.blaze_spotter.couldnt_find_spawner"), ChatFormatting.GOLD, ChatFormatting.WHITE);
            } else {
                this.correctlyTeleport(world, blazeSpawnerPos, player, 1.0F);
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, S_asTick(world.getRandom().nextInt(4) + 7), 0, false, true, true));
                player.getCooldowns().addCooldown(this.getDefaultInstance(), S_asTick(30));
                world.broadcastEntityEvent(player, ModStatuses.ADD_BLAZE_SMOKE_PARTICLES);
                this.playTeleportSound(world, player);
                this.playWorldSound(SoundEvents.BLAZE_AMBIENT, 3.0F, 0.6F, world, player);
                this.playThrowSound(world, player);

                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)player, stack);

                player.awardStat(Stats.ITEM_USED.get(this));
                player.swing(hand, SwingAnimation.DEFAULT, true);
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
        final int xPlaneRadius = 156;
        final int yPlaneRadius = 72;
        for (BlockPos pos : BlockPos.withinClippedManhattan(fortressPos,
                xPlaneRadius,
                yPlaneRadius,
                xPlaneRadius)) {
            if (world.getBlockState(pos).getBlock() == Blocks.SPAWNER) {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (blockEntity instanceof SpawnerBlockEntity) {
                    SpawnerBlockEntity spawnerBlockEntity = (SpawnerBlockEntity) blockEntity;
                    if (spawnerBlockEntity.getSpawner().getOrCreateDisplayEntity(world, pos).getType() == EntityTypes.BLAZE) {
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
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.blaze_spotter.tooltip")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{
                Mode.BALANCED
        };
    }
}