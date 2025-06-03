package net.dillon.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.BlockItem;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

/**
 * Constructs a new block item with a tooltip.
 */
public class BlockItemWithTooltip extends BlockItem implements TooltipAppender {

    public BlockItemWithTooltip(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
    }
}