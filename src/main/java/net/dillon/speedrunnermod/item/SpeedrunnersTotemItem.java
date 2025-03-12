package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModComponents;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A totem that works anywhere in the players' inventory, stacks to 16, and has better effects upon use.
 */
@Author(Authors.YELEEFFF)
public class SpeedrunnersTotemItem extends Item {
    private static final byte BYTE_ID = 77;

    public SpeedrunnersTotemItem(Settings settings) {
        super(settings.maxCount(3).rarity(Rarity.RARE).component(DataComponentTypes.DEATH_PROTECTION, ModComponents.TOTEM_SPEEDRUNNERS)
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, ofSpeedrunnerMod("speedrunners_totem"))));
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
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line1").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line3").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line4"));
        }
    }
}