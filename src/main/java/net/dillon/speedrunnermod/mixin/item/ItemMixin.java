package net.dillon.speedrunnermod.mixin.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerItem;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract ItemStack getDefaultInstance();

    /**
     * For the {@code Expert Shepherd} advancement.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void modifiedInventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
        if (entity instanceof ServerPlayer player) {
            int j = 0;
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                if (player.getInventory().getItem(i).is(Items.LIME_WOOL)) {
                    j += player.getInventory().getItem(i).getCount();
                }
                if (j >= 64) {
                    ModCriterions.TRIGGERED_BY_ITEM.trigger(player, Items.LIME_WOOL.getDefaultInstance());
                    break;
                }
            }
        }
    }

    /**
     * Adds tooltips to certain items, for item descriptions, craftables, and enchanted books.
     */
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    private void appendTooltipsToOtherItems(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type, CallbackInfo ci) {
        if ((isEasyMode() || isDoomMode()) && stack.is(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.piglin_awakener_craftable.tooltip"));
        }
        if (isDoomMode() && stack.is(ModItemTags.DOOM_STONE_SAFE_TOOLS)) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.doom_mode_safe_tools.tooltip").withStyle(ChatFormatting.RED));
        }
        if (stack.is(ModItemTags.GOLDEN_SPEEDRUNNER_ARMOR)) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.golden_speedrunner_armor.tooltip").withStyle(ChatFormatting.YELLOW), 35);
        }
        if (stack.is(Items.ENCHANTED_BOOK)) {
            ItemEnchantments itemEnchantmentsComponent = EnchantmentHelper.getEnchantmentsForCrafting(stack);
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemEnchantmentsComponent.entrySet()) {
                if (entry.getKey().is(ModEnchantments.DASH)) {
                    SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.dash.tooltip").withStyle(ChatFormatting.GRAY));
                }
                if (entry.getKey().is(ModEnchantments.COOLDOWN)) {
                    SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.cooldown.tooltip").withStyle(ChatFormatting.GRAY));
                }
                if (entry.getKey().is(ModEnchantments.WITHERED)) {
                    SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("enchantment.speedrunnermod.withered.tooltip"), 40);
                }
            }
        }
        if (stack.is(Items.FIRE_CHARGE) || stack.is(ModItems.DRAGONS_FIREBALL)) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.minecraft.fire_charge.throw").withStyle(ChatFormatting.GRAY));
        }
        if (options().main.lavaBoats.getCurrentValue() && (stack.is(ModItemTags.FIREPROOF_BOATS) || stack.is(ModItemTags.FIREPROOF_CHEST_BOATS))) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.boat.tooltip").withStyle(ChatFormatting.GOLD));
        }
        if (stack.is(ModItemTags.FASTER_BOATS) || stack.is(ModItemTags.FASTER_CHEST_BOATS)) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.boat.tooltip.fast").withStyle(ChatFormatting.GRAY));
        }
    }
}