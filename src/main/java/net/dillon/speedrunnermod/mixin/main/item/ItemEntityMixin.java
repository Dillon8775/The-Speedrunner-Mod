package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    /**
     * Makes all items in the {@code "Fireproof Items"} tag, fireproof.
     */
    @Author(Authors.UNKNOWN)
    @Inject(method = "isFireImmune", at = @At("RETURN"), cancellable = true)
    public void implementFireImmuneItemsFunctionality(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity item = (ItemEntity)(Object)this;
        ItemStack stack = item.getStack();

        if (options().main.fireproofItems.getCurrentValue()) {
            if (stack.isIn(ModItemTags.FIREPROOF_ITEMS)) {
                cir.setReturnValue(true);
            }
        }

        cir.getReturnValueZ();
    }
}