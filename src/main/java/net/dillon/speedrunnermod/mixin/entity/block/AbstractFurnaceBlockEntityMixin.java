package net.dillon.speedrunnermod.mixin.entity.block;

import net.dillon.dillonlib.util.Arithmetics;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {
    @Shadow
    private int cookingTotalTime;

    /**
     * Vanilla resets cookingTotalTime from recipe.cookingTime() after each finished smelt.
     * Re-apply our custom value each tick while an input item exists.
     */
    @Inject(method = "serverTick", at = @At("TAIL"))
    private static void keepFasterCookingTime(ServerLevel world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity furnace, CallbackInfo ci) {
        if (!common().general().fasterSmelting) {
            return;
        }

        ItemStack input = furnace.getItem(0);
        if (input.isEmpty()) {
            return;
        }

        boolean fastSmeltingBlock = furnace instanceof BlastFurnaceBlockEntity || furnace instanceof SmokerBlockEntity;
        ((AbstractFurnaceBlockEntityMixin)(Object)furnace).cookingTotalTime = Arithmetics.sas(fastSmeltingBlock ? 1 : 2);
    }
}