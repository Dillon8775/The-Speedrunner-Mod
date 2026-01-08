package net.dillon.speedrunnermod.mixin.main.item;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    /**
     * Allows water to be placed in the nether if the {@code allow water in nether} option is on.
     */
    @Redirect(method = "placeFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/Fluid;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private boolean allowWaterInNether(Fluid instance, TagKey<Fluid> tag) {
        return !options().main.netherWater.getCurrentValue();
    }
}