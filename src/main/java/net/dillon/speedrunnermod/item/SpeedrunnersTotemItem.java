package net.dillon.speedrunnermod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A totem that works anywhere in the players' inventory, stacks to 16, and has better effects upon use.
 */
public class SpeedrunnersTotemItem extends Item {
    private static final byte BYTE_ID = 77;

    public SpeedrunnersTotemItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.RARE));
    }

    /**
     * Returns the byte identifier for the Speedrunners totem.
     */
    public static byte use() {
        return BYTE_ID;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line1"));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line2"));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line3"));
        }
    }
}