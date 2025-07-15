package net.dillon.speedrunnermod.mixin.main.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowGolemEntity.class)
public class SnowGolemEntityMixin {

    /**
     * Fixes {@code speedrunner shears} not working on snow golems.
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectSnowGolemEntityUseWithShears(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}