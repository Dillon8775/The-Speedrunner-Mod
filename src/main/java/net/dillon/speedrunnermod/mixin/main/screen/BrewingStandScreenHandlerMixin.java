package net.dillon.speedrunnermod.mixin.main.screen;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandScreenHandler.PotionSlot.class)
public class BrewingStandScreenHandlerMixin {

    /**
     * Triggers the {@code Dragon's Aura} advancement.
     */
    @Inject(method = "onTakeItem", at = @At("TAIL"))
    private void triggerDragonsAuraAdvancement(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (player instanceof ServerPlayerEntity serverPlayerEntity) {
            if (ModUtil.hasDragonsAura(stack)) {
                ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayerEntity, new ItemStack(Items.POTION));
            }
        }
    }
}