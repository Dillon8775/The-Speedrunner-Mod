package net.dillon.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Experience ore block items.
 */
public class ExperienceOreItem extends BlockItem implements TooltipAppender {

    public ExperienceOreItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        if (options().client.itemTooltips) {
            textConsumer.accept(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line1").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("block.speedrunnermod.experience_ore.tooltip.line2").formatted(Formatting.GRAY));
        }
        if (options().client.textureTooltips) {
            textConsumer.accept(Text.translatable("speedrunnermod.texture_creator.krevikus"));
        }
    }
}