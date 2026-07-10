package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
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
        if (ModComponentHelper.hasDragonsAura(stack)) {
            cir.setReturnValue(cir.getReturnValue().copy().withStyle(ChatFormatting.LIGHT_PURPLE));
        }
    }
}