package net.dillon.speedrunnermod.item.eye;

import net.dillon.dillonlib.task.CommonTasks;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.Awakened;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

import static net.dillon.dillonlib.util.Arithmetics.M_asTick;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * An item that teleports {@code nearby piglin} to the player.
 */
public class PiglinAwakenerItem extends Item implements SpeedrunnerItem {

    public PiglinAwakenerItem(Properties settings) {
        super(settings
                .component(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY)
                .stacksTo(16));
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.PIGLIN_AMBIENT, 1.0F, world, player);
            player.sendSystemMessage(Component.translatable("item.speedrunnermod.item_disabled_twomode").withStyle(ChatFormatting.GOLD));
            player.swing(hand, SwingAnimation.DEFAULT, true);
            for (int i = 0; i < 8; i++) {
                player.spawnAtLocation((ServerLevel)world, Items.GOLD_INGOT);
            }
            player.spawnAtLocation((ServerLevel)world, stack.getOrDefault(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY).getItem());
            stack.shrink(1);
        } else if (world.dimension() != Level.NETHER) {
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.piglin_awakener.wrong_dimension"), ChatFormatting.RED, ChatFormatting.WHITE);
        } else {
            final int r = 100;
            List<Integer> radius = List.of(r, r, r);
            List<Piglin> piglins = CommonTasks.getEntitiesWithinRange(world, Piglin.class, player, radius,
                    e ->
                            !e.hasCustomName()
                                    && !e.isBaby()
                                    && e instanceof Piglin piglin
                                    && !((Awakened)piglin).isAwakened());

            if (piglins.isEmpty()) {
                ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.piglin_awakener.couldnt_find_piglins"), ChatFormatting.RED, ChatFormatting.WHITE);
            } else {
                boolean isSafe = player.getAbilities().instabuild;
                boolean hasGold = player.getInventory().contains(new ItemStack(Items.GOLD_INGOT)) || player.getAbilities().instabuild;
                for (EquipmentSlot armorItem : EquipmentSlot.VALUES) {
                    ItemStack itemStack = player.getItemBySlot(armorItem);
                    if (itemStack.is(ItemTags.PIGLIN_SAFE_ARMOR)) {
                        isSafe = true;
                    }
                }

                if (!isSafe) {
                    this.playWorldSound(SoundEvents.PIGLIN_AMBIENT, 3.0F, 1.0F, world, player);
                    this.playWorldSound(SoundEvents.PIGLIN_AMBIENT, world, player);
                    ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.piglin_awakener.unsafe"), ChatFormatting.RED, ChatFormatting.WHITE);
                } else if (!hasGold) {
                    this.playWorldSound(SoundEvents.PIGLIN_AMBIENT, 3.0F, 1.0F, world, player);
                    ModHelper.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.piglin_awakener.no_gold_ingot"), ChatFormatting.RED, ChatFormatting.WHITE);
                } else {
                    this.playThrowSound(world, player);
                    this.playWorldSound(SoundEvents.PIGLIN_ANGRY, 3.0F, 1.0F, world, player);
                    boolean sneakingWhenClicked = player.isShiftKeyDown();

                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)player, stack);

                    int piglinTeleported = 0;
                    for (Piglin piglin : piglins) {
                        if (world.getRandom().nextFloat() < 0.50F) {
                            piglin.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, M_asTick(1), 0, false, true, false));
                        }
                        double x = !sneakingWhenClicked ? player.getX() + world.getRandom().nextInt(7) - 3 : player.getX();
                        double y = !sneakingWhenClicked ? player.getY() + world.getRandom().nextDouble() * (2.0 - 0.5) + 0.5 : player.getY();
                        double z = !sneakingWhenClicked ? player.getZ() + world.getRandom().nextInt(7) - 3 : player.getZ();
                        piglin.randomTeleport(x, y, z, false, state -> true);
                        piglinTeleported++;
                        ((Awakened)piglin).setAwakened(true);
                        if (piglinTeleported >= common().accessibility().piglinAwakenerPiglinCount || (isDoomMode() && piglinTeleported >= 3)) {
                            break;
                        }
                    }

                    int teleportedPerTick = piglinTeleported * 200;
                    player.getCooldowns().addCooldown(this.getDefaultInstance(), ModHelper.atMost(teleportedPerTick, 1200));
                    this.decrementIfPossible(player, stack);

                    player.awardStat(Stats.ITEM_USED.get(this));
                    player.swing(hand, SwingAnimation.DEFAULT, true);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.piglin_awakener.tooltip")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.piglin_awakener.tooltip.shift")
                .withStyle(this.isDisabled() ? ChatFormatting.STRIKETHROUGH : ChatFormatting.RESET).withStyle(ChatFormatting.GRAY));
        if (isDoomMode()) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.piglin_awakener.doom_mode").withStyle(ChatFormatting.RED));
        }
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{
                Mode.BALANCED
        };
    }
}