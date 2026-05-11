package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A totem that works anywhere in the players' inventory, stacks to 16, and has better effects upon use.
 */
@Author(Authors.YELEEFFF)
public class SpeedrunnersTotemItem extends Item {

    public SpeedrunnersTotemItem(Properties settings) {
        super(settings.stacksTo(3).rarity(Rarity.RARE).component(DataComponents.DEATH_PROTECTION, ModDataComponentTypes.SPEEDRUNNERS_TOTEM_EFFECTS)
                .setId(ResourceKey.create(Registries.ITEM, ofSpeedrunnerMod("speedrunners_totem"))));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line1").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line2").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line3").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_totem.tooltip.line4"));
    }
}