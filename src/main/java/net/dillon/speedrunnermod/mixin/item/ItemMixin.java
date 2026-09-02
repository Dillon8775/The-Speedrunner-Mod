package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.helper.ModComponentHelper.hasEnchantment;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract ItemStack getDefaultInstance();

    /**
     * Ticks advancement criterions.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void modifiedInventoryTick(ItemStack stack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
        if (entity instanceof ServerPlayer player) {
            int j = 0;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                if (player.getInventory().getItem(i).is(Items.WOOL.lime())) {
                    j += player.getInventory().getItem(i).getCount();
                }
                if (j >= 64) {
                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, Items.WOOL.lime().getDefaultInstance());
                    break;
                }
            }

            if (ModComponentHelper.hasDragonsAura(stack)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(Items.POTION));
            }

            if (ModComponentHelper.hasWithered(stack)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(Items.SPLASH_POTION));
            }

            if (ModComponentHelper.hasLuck(stack)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(Items.LINGERING_POTION));
            }

            if (hasEnchantment(stack, ModEnchantments.DASH)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(ModItems.SPEEDRUNNER_LOG)); // Placeholder item for triggered instance
            }

            if (hasEnchantment(stack, ModEnchantments.WITHERED)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(ModItems.DEAD_SPEEDRUNNER_LOG)); // Placeholder item for triggered instance
            }

            if (hasEnchantment(stack, ModEnchantments.COOLDOWN)) {
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(ModItems.SPEEDRUNNER_WOOD)); // Placeholder item for triggered instance
            }
        }
    }

    /**
     * Adds tooltips to certain items, for item descriptions, craftables, and enchanted books.
     */
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendTooltipsToOtherItems(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type, CallbackInfo ci) {
        if (stack.is(Items.ENCHANTED_BOOK)) {
            if (hasEnchantment(stack, ModEnchantments.DASH)) {
                SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.dash.tooltip").withStyle(ChatFormatting.AQUA));
            }
            if (hasEnchantment(stack, ModEnchantments.COOLDOWN)) {
                SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.cooldown.tooltip").withStyle(ChatFormatting.AQUA));
            }
            if (hasEnchantment(stack, ModEnchantments.WITHERED)) {
                SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.withered.tooltip").withStyle(ChatFormatting.LIGHT_PURPLE), 40);
            }
        }
    }
}