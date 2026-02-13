package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A totem that works anywhere in the players' inventory, stacks to 16, and has better effects upon use.
 */
@Author(Authors.YELEEFFF)
public class SpeedrunnersTotemItem extends Item  {

    public SpeedrunnersTotemItem(Settings settings) {
        super(settings.maxCount(3).rarity(Rarity.RARE).component(DataComponentTypes.DEATH_PROTECTION, ModDataComponentTypes.TOTEM_SPEEDRUNNERS)
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, ofSpeedrunnerMod("speedrunners_totem"))));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line1").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line2").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line3").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line4"));
    }
}