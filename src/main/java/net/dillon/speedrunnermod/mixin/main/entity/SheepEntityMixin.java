package net.dillon.speedrunnermod.mixin.main.entity;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public class SheepEntityMixin {

    /**
     * Allows sheep to be sheared with {@code speedrunner shears.}
     */
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean interactMob(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.SHEAR_TOOLS);
    }
}