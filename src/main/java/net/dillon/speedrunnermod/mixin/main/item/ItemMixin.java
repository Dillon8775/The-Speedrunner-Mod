package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * Adds tooltips to items that can be used to craft the {@code piglin awakener.}
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltips(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (options().client.itemTooltips) {
            if (stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
                tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
            }
            if (stack.isOf(ModBlockItems.SPEEDRUNNERS_WORKBENCH)) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
            }
            if (stack.isOf(Items.ENCHANTED_BOOK)) {
                ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                    if (entry.getKey().matchesKey(ModEnchantments.DASH)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
                    }
                    if (entry.getKey().matchesKey(ModEnchantments.COOLDOWN)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
                    }
                }
            }
            if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                tooltip.add(Text.translatable("item.totem_of_undying.tooltip"));
            }
            if (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
            }
            if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
            }
        }
        if (options().client.textureTooltips) {
            if (stack.isIn(ModItemTags.Block.TEXTURE_CREATOR_MANNYQUESO)) {
                tooltip.add(Text.translatable("speedrunnermod.texture_creator.mannyqueso"));
            }
        }
    }
}