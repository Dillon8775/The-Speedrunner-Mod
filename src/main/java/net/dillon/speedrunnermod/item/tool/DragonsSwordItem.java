package net.dillon.speedrunnermod.item.tool;

import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.item.material.ModToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

/**
 * A weapon that {@code one-shots} the {@code ender dragon.}
 */
public class DragonsSwordItem extends SpeedrunnerToolItem.Sword {

    public DragonsSwordItem(Item.Properties settings) {
        super(ModToolMaterials.DRAGONS_SWORD, 6, -3.0F, true, settings
                .rarity(Rarity.EPIC)
        );
    }

    /**
     * The Dragon's sword always has an enchantment glint.
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.dragons_sword.tooltip").withStyle(ChatFormatting.DARK_PURPLE));
    }
}