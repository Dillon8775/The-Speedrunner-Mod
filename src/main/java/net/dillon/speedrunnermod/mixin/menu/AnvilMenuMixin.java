package net.dillon.speedrunnermod.mixin.menu;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
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
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
    @Shadow @Final
    private DataSlot cost;

    public AnvilMenuMixin(@Nullable MenuType<?> type, int syncId, Inventory playerInventory, ContainerLevelAccess context, ItemCombinerMenuSlotDefinition forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    /**
     * Sets the maximum cost for an anvil.
     */
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
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
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 39))
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
    @Inject(method = "createResult", at = @At("TAIL"))
    private void setLevelCostIfTooHigh(CallbackInfo ci) {
        if (options().main.anvilCostLimit.getCurrentValue() != 50 && this.cost.get() > options().main.anvilCostLimit.getCurrentValue()) {
            this.cost.set(options().main.anvilCostLimit.getCurrentValue());
        }
    }

    /**
     * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
     */
    @Redirect(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
        if (!options().main.higherEnchantmentLevels.getCurrentValue()) {
            return enchantment.getMaxLevel();
        }

        if (enchantment.getMaxLevel() == 1) {
            return enchantment.getMaxLevel();
        }

        ItemStack firstSlot = this.inputSlots.getItem(0);
        ItemStack secondSlot = this.inputSlots.getItem(1);

        ItemEnchantments firstEnchantments = EnchantmentHelper.getEnchantmentsForCrafting(firstSlot);
        ItemEnchantments secondEnchantments = EnchantmentHelper.getEnchantmentsForCrafting(secondSlot);

        Holder<Enchantment> entry =
                this.player.level().registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .wrapAsHolder(enchantment);

        int firstLevel = firstEnchantments.getLevel(entry);
        int secondLevel = secondEnchantments.getLevel(entry);

        int newLevel;
        if (firstLevel == secondLevel) {
            newLevel = firstLevel + 1;
        } else {
            newLevel = Math.max(firstLevel, secondLevel);
        }

        return Math.min(newLevel, 100);
    }
}