package net.dillon.speedrunnermod.mixin.fix;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.TntBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntBlock.class)
public class TntBlockMixin {

    /**
     * Fixes {@code ignitables} not working on TNT blocks.
     */
    @Redirect(method = "useItemOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectTntOnUseWithItem(ItemStack stack, Object o) {
        return stack.is(ModItemTags.IGNITABLES);
    }
}