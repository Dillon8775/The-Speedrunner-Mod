package net.dillon.speedrunnermod.mixin.main.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.ForgingSlotsManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Improves the anvils functionality by adding and changing several different features.
 */
@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {
    @Shadow @Final
    private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, ForgingSlotsManager forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    /**
     * Sets the maximum cost for an anvil.
     */
    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (options().main.betterAnvil.getCurrentValue()) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }

    /**
     * Sets the maximum cost for an anvil, - 1.
     */
    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 39))
    private int mixinMaxInt(int i) {
        if (options().main.betterAnvil.getCurrentValue()) {
            return Integer.MAX_VALUE - 1;
        } else {
            return 39;
        }
    }

    /**
     * Sets the anvil cost to the {@code anvil cost limit} option if the cost exceeds the limit value (unless the anvil cost limit is 50, meaning that there is no limit).
     */
    @Inject(method = "updateResult", at = @At("TAIL"))
    private void setLevelCostIfTooHigh(CallbackInfo ci) {
        if (options().main.anvilCostLimit.getCurrentValue() != 50) {
            this.levelCost.set(options().main.anvilCostLimit.getCurrentValue());
        }
    }

    /**
     * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
     */
    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
        ItemStack itemStack = this.input.getStack(0); // Gets the input of the first slot in the anvil
        ItemStack itemStack2 = itemStack.copy(); // Copies the first input in the anvil, used for the result
        ItemStack itemStack3 = this.input.getStack(1); // Gets the input of the second slot in the anvil
        ItemEnchantmentsComponent itemStack2Component = EnchantmentHelper.getEnchantments(itemStack2); // Gets the enchantments on the first slot in anvil
        ItemEnchantmentsComponent itemStack3Component = EnchantmentHelper.getEnchantments(itemStack3); // Gets the enchantments on the second slot in anvil
        int newEnchantmentLevel = 0; // Initialize new enchantment level
        int secondSlotLevel; // Initialize second slot integer

        // As long as the first slot has enchantments, run for each enchantment on the first slot and increment accordingly
        if (!itemStack2Component.getEnchantmentEntries().isEmpty()) {
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> firstSlotEnchantmentEntry : itemStack2Component.getEnchantmentEntries()) { // Goes through all first slot enchantment entries
                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> secondSlotEnchantmentEntry : itemStack3Component.getEnchantmentEntries()) { // Which then goes through all second slot enchantment entries
                    int firstSlotLevel = itemStack2Component.getLevel(firstSlotEnchantmentEntry.getKey()); // Gets the level of the enchantment entry in the first anvil slot
                    secondSlotLevel = itemStack3Component.getLevel(secondSlotEnchantmentEntry.getKey()); // Gets the level of the enchantment entry in the second anvil slot

                    // If enchantment levels match, get the current value of the enchantment level, and increment by one
                    // Otherwise, return the maximum value between the two enchantment level
                    newEnchantmentLevel = firstSlotLevel == secondSlotLevel ? firstSlotLevel + 1 : Math.max(firstSlotLevel, secondSlotLevel);
                }
            }
        } else { // Otherwise, run through each enchantment on the second slot
            for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemStack3Component.getEnchantmentEntries()) {
                secondSlotLevel = itemStack3Component.getLevel(entry.getKey()); // Gets the level of the enchantment in the second anvil slot
                newEnchantmentLevel = secondSlotLevel; // Set new enchantment level to the level of the enchantment in the second anvil slot
            }
        }

        // Cap the new enchantment level at 100
        if (newEnchantmentLevel > 100) {
            newEnchantmentLevel = 100;
        }

        // Return the incremented integer value, unless the default maximum level is 1, then there is no point to increment
        boolean isntOne = enchantment.getMaxLevel() != 1;
        return options().main.higherEnchantmentLevels.getCurrentValue() && isntOne ? newEnchantmentLevel : enchantment.getMaxLevel();
    }
}