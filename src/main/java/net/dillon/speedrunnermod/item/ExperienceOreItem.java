package net.dillon.speedrunnermod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

/**
 * Experience ore block items.
 */
public class ExperienceOreItem extends BlockItem {

    public ExperienceOreItem(Block block, Properties settings) {
        super(block, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("block.speedrunnermod.experience_ore.tooltip.line1").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("block.speedrunnermod.experience_ore.tooltip.line2").withStyle(ChatFormatting.GRAY));
    }
}