package net.dillon.speedrunnermod.mixin.fix;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Sheep.class)
public class SheepFix {

    /**
     * Allows sheep to be sheared with {@code speedrunner shears.}
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean interactMob(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}