package net.dillon.speedrunnermod.mixin.client.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fixes some incorrect rendering with speedrunner crossbows.
 */
@Mixin(AvatarRenderer.class)
public class AvatarRendererFix {

    @Redirect(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private static boolean renderCrossbowsCorrectly(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.CROSSBOW_TOOLS);
    }
}