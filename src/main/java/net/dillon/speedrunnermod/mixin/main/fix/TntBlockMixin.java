package net.dillon.speedrunnermod.mixin.main.fix;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.TntBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntBlock.class)
public class TntBlockMixin {

    /**
     * Fixes {@code ignitables} not working on TNT blocks.
     */
    @Redirect(method = "onUseWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectTntOnUseWithItem(ItemStack stack, Item item) {
        return stack.isIn(ModItemTags.IGNITABLES);
    }
}