package net.dillon.speedrunnermod.mixin.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowGolem.class)
public class SnowGolemFix {

    /**
     * Fixes {@code speedrunner shears} not working on snow golems.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectSnowGolemEntityUseWithShears(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}