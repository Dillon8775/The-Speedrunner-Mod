package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

/**
 * An {@code eye of ender} item that locates nearby {@code nether fortresses} and {@code bastions.}
 */
public class InfernoEyeItem extends Item implements SpeedrunnerItem {

    public InfernoEyeItem(Properties settings) {
        super(settings.component(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES).fireResistant());
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (world.isClientSide()) {
            return InteractionResult.CONSUME;
        } else if (world.dimension() != Level.NETHER) {
            ModUtil.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension"), ChatFormatting.RED, ChatFormatting.WHITE);
        } else {
            if (player.isShiftKeyDown()) {
                if (stack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.FORTRESSES)) {
                    stack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.BASTIONS);
                    this.playWorldSound(SoundEvents.PIGLIN_AMBIENT, 2.0F, 1.0F, world, player);
                } else if (stack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.BASTIONS)) {
                    stack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES);
                    this.playWorldSound(SoundEvents.BLAZE_AMBIENT, 2.0F, 1.0F, world, player);
                }

                ModUtil.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
            } else {
                player.sendSystemMessage(ModTexts.CALCULATING);
                ModUtil.findStructureAndShoot(world, player, stack, stack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                ModUtil.sendMessageWithActionbarPref(player, Component.translatable("item.speedrunnermod.eye_of_inferno.located", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
                this.playWorldSound(SoundEvents.FIRECHARGE_USE, 0.5F, 1.0F, world, player);
                this.decrementIfPossible(player, stack);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.swing(hand, true);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        textConsumer.accept(Component.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))).withStyle(ChatFormatting.BOLD));
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{};
    }
}