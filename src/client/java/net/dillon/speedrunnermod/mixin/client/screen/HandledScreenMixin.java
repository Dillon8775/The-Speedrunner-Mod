package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen {
    @Shadow
    protected abstract @Nullable Slot getSlotAt(double mouseX, double mouseY);

    public HandledScreenMixin(Text title) {
        super(title);
    }

    /**
     * Adds the {@code Dragon's Aura} tooltip over armor items.
     */
    @Inject(method = "drawMouseoverTooltip", at = @At("HEAD"), cancellable = true)
    private void addDragonsAuraTooltip(DrawContext context, int x, int y, CallbackInfo ci) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && player.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
            Slot hoveredSlot = this.getSlotAt(x, y);
            if (hoveredSlot == null || !hoveredSlot.hasStack()) {
                return;
            }

            ItemStack stack = hoveredSlot.getStack();
            if (!stack.isIn(ItemTags.ARMOR_ENCHANTABLE)) {
                return;
            }

            List<Text> originalTooltip = stack.getTooltip(Item.TooltipContext.DEFAULT, player, MinecraftClient.getInstance().options.advancedItemTooltips ? TooltipType.ADVANCED : TooltipType.BASIC);
            originalTooltip.add(1, Text.translatable("item.minecraft.armor.dragons_aura.tooltip").formatted(Formatting.DARK_PURPLE));
            originalTooltip.add(2, Text.translatable("item.minecraft.armor.dragons_aura.description").formatted(Formatting.GRAY));
            context.drawTooltip(this.textRenderer, originalTooltip, Optional.empty(), x, y);
            ci.cancel();
        }
    }
}