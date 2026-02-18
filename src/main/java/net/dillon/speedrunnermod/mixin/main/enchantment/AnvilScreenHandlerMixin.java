package net.dillon.speedrunnermod.mixin.main.enchantment;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
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
        if (options().main.anvilCostLimit.getCurrentValue() != 50 && this.levelCost.get() > options().main.anvilCostLimit.getCurrentValue()) {
            this.levelCost.set(options().main.anvilCostLimit.getCurrentValue());
        }
    }

    /**
     * Allows the combination of two maximum level enchanted items to go above the enchantment level cap.
     */
    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int countOverMaxLevel(Enchantment enchantment) {
        if (!options().main.higherEnchantmentLevels.getCurrentValue()) {
            return enchantment.getMaxLevel();
        }

        if (enchantment.getMaxLevel() == 1) {
            return enchantment.getMaxLevel();
        }

        ItemStack firstSlot = this.input.getStack(0);
        ItemStack secondSlot = this.input.getStack(1);

        ItemEnchantmentsComponent firstEnchantments = EnchantmentHelper.getEnchantments(firstSlot);
        ItemEnchantmentsComponent secondEnchantments = EnchantmentHelper.getEnchantments(secondSlot);

        RegistryEntry<Enchantment> entry =
                this.player.getEntityWorld().getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getEntry(enchantment);

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