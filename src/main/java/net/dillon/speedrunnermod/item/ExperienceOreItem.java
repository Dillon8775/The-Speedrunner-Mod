package net.dillon.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

/**
 * Experience ore block items.
 */
public class ExperienceOreItem extends BlockItem  {

    public ExperienceOreItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line1").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line2").formatted(Formatting.GRAY));
    }
}