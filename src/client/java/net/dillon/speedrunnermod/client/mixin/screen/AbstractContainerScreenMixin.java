package net.dillon.speedrunnermod.client.mixin.screen;

import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> extends Screen {
    @Shadow
    protected abstract @Nullable Slot getHoveredSlot(double mouseX, double mouseY);

    public AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    /**
     * Adds the {@code Dragon's Aura} tooltip over armor items.
     */
    @Inject(method = "extractTooltip", at = @At("HEAD"), cancellable = true)
    private void addDragonsAuraTooltip(GuiGraphicsExtractor context, int x, int y, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(ModStatusEffects.DRAGONS_AURA)) {
            Slot hoveredSlot = this.getHoveredSlot(x, y);
            if (hoveredSlot == null || !hoveredSlot.hasItem()) {
                return;
            }

            ItemStack stack = hoveredSlot.getItem();
            if (!stack.is(ItemTags.ARMOR_ENCHANTABLE)) {
                return;
            }

            List<Component> originalTooltip = stack.getTooltipLines(Item.TooltipContext.EMPTY, player, Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.ADVANCED : TooltipFlag.NORMAL);
            originalTooltip.add(1, Component.translatable("item.minecraft.armor.dragons_aura.tooltip").withStyle(ChatFormatting.DARK_PURPLE));
            originalTooltip.add(2, Component.translatable("item.minecraft.armor.dragons_aura.description").withStyle(ChatFormatting.GRAY));
            context.setTooltipForNextFrame(this.font, originalTooltip, Optional.empty(), x, y);
            ci.cancel();
        }
    }
}