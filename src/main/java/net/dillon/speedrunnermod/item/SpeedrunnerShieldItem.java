package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * <p>A shield which has a faster cooldown, and more durability.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link net.dillon.speedrunnermod.mixin.main.entity.player.PlayerEntityMixin} for more.</p>
 */
public class SpeedrunnerShieldItem extends ShieldItem {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(672).repairable(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE).equippableUnswappable(EquipmentSlot.OFFHAND));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_shield.tooltip").formatted(Formatting.GRAY));
        }
    }
}