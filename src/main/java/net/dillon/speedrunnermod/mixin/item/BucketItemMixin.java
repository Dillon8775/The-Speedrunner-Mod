package net.dillon.speedrunnermod.mixin.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    /**
     * Allows water to be placed in the nether if the {@code allow water in nether} option is on.
     */
    @Redirect(method = "emptyContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/Fluid;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean allowWaterInNether(Fluid instance, TagKey<Fluid> tag) {
        return !options().main.netherWater.getCurrentValue();
    }
}