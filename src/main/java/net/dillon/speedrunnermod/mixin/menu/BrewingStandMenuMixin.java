package net.dillon.speedrunnermod.mixin.menu;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingStandMenu.PotionSlot.class)
public class BrewingStandMenuMixin {

    /**
     * Triggers the {@code Dragon's Aura} advancement.
     */
    @Inject(method = "onTake", at = @At("TAIL"))
    private void triggerDragonsAuraAdvancement(Player player, ItemStack stack, CallbackInfo ci) {
        if (player instanceof ServerPlayer serverPlayerEntity) {
            if (ModUtil.hasDragonsAura(stack)) {
                ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayerEntity, new ItemStack(Items.POTION));
            }
        }
    }
}