package net.dillon.speedrunnermod.mixin.client.fix;

import net.dillon.speedrunnermod.item.SpeedrunnerCrossbowItem;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes some incorrect rendering with speedrunner bows and crossbows.
 */
@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Redirect(method = "evaluateWhichHandsToRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectHandRenderType(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.BOW_TOOLS) || stack.is(ConventionalItemTags.CROSSBOW_TOOLS);
    }

    @Redirect(method = "selectionUsingItemWhileHoldingBowLike", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectUsingItemHandRenderType(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.BOW_TOOLS) || stack.is(ConventionalItemTags.CROSSBOW_TOOLS);
    }

    @Redirect(method = "isChargedCrossbow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean redirectIsChargedCrossbow(ItemStack stack, Object o) {
        if (stack.is(ConventionalItemTags.CROSSBOW_TOOLS)) {
            return CrossbowItem.isCharged(stack) || SpeedrunnerCrossbowItem.isCharged(stack);
        }
        return false;
    }

    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean redirectRenderFirstPersonItem(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.CROSSBOW_TOOLS);
    }
}