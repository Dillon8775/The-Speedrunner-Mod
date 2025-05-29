package net.dillon.speedrunnermod.item;

import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

/**
 * Constructs a new item with a tooltip.
 */
public class ItemWithTooltip extends Item implements TooltipAppender {

    public ItemWithTooltip(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
    }
}