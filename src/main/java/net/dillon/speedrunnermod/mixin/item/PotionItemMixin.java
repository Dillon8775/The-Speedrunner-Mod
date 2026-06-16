package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends Item {

    public PotionItemMixin(Properties settings) {
        super(settings);
    }

    /**
     * Makes the dragon's aura potion effect name color {@code purple.}
     */
    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void makePurpleDragonsAura(ItemStack stack, CallbackInfoReturnable<Component> cir) {
        if (ModUtil.hasDragonsAura(stack)) {
            cir.setReturnValue(cir.getReturnValue().copy().withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }

    /**
     * Triggers the {@code Dragon's Aura} advancement.
     */
    @Override
    public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof ServerPlayer player && ModUtil.hasDragonsAura(stack)) {
            ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(Items.POTION));
        }
    }
}