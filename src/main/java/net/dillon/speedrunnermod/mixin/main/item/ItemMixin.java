package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
    public abstract ItemStack getDefaultStack();

    /**
     * For the {@code Expert Shepherd} advancement.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void modifiedInventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player) {
            int j = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).isOf(Items.LIME_WOOL)) {
                    j += player.getInventory().getStack(i).getCount();
                }
                if (j >= 64) {
                    ModCriterions.TRIGGERED_BY_ITEM.trigger(player, Items.LIME_WOOL.getDefaultStack());
                    break;
                }
            }
        }
    }

    /**
     * Adds tooltips to certain items, for item descriptions, craftables, and enchanted books.
     */
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    private void appendTooltipsToOtherItems(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type, CallbackInfo ci) {
        if ((isEasyMode() || isDoomMode()) && stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener_craftable.line1").formatted(Formatting.GOLD));
            textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener_craftable.line2").formatted(Formatting.GOLD));
        }
        if (isDoomMode() && stack.isIn(ModItemTags.DOOM_STONE_SAFE_TOOLS)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.doom_mode_safe_tools.tooltip").formatted(Formatting.RED));
        }
        if (stack.isIn(ModItemTags.GOLDEN_SPEEDRUNNER_ARMOR)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.golden_speedrunner_armor.tooltip").formatted(Formatting.YELLOW));
        }
        if (stack.isOf(Items.ENCHANTED_BOOK)) {
            ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                if (entry.getKey().matchesKey(ModEnchantments.DASH)) {
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
                }
                if (entry.getKey().matchesKey(ModEnchantments.COOLDOWN)) {
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
                }
                if (entry.getKey().matchesKey(ModEnchantments.WITHERED)) {
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.withered.tooltip.line1").formatted(Formatting.GRAY));
                    textConsumer.accept(Text.translatable("enchantment.speedrunnermod.withered.tooltip.line2").formatted(Formatting.GRAY));
                }
            }
        }
        if (stack.isOf(Items.FIRE_CHARGE) || stack.isOf(ModItems.DRAGONS_FIREBALL)) {
            textConsumer.accept(Text.translatable("item.minecraft.fire_charge.throw").formatted(Formatting.GRAY));
        }
        if (options().main.lavaBoats.getCurrentValue() && stack.getOrDefault(ModDataComponentTypes.BOOLEAN, false) && (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS))) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GOLD));
        }
        if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
        }
    }
}