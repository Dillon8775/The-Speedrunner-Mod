package net.dillon.speedrunnermod.mixin.fix;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SulfurCube.class)
public class SulfurCubeFix {

    /**
     * Allows speedrunner flint and steels to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean allowIgitables(ItemStack heldItem, Object o) {
        return heldItem.is(ModItemTags.IGNITABLES);
    }

    /**
     * Allows speedrunner flint and steels to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 1))
    private boolean allowIgnitables(ItemStack heldItem, Object o) {
        return heldItem.is(ModItemTags.IGNITABLES);
    }

    /**
     * Allows speedrunner flint and steels to work on sulfur cubes when checking.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 2))
    private boolean allowSpeedrunnerFlintAndSteelToWork(ItemStack heldItem, Object o) {
        return heldItem.is(ModItemTags.IGNITABLES);
    }

    /**
     * Allows speedrunner shears to work on sulfur cubes.
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 3))
    private boolean allowSpeedrunnerShearsToWork(ItemStack heldItem, Object o) {
        return heldItem.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}