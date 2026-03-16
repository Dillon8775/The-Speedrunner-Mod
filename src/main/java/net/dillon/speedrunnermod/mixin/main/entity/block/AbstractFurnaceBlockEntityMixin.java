package net.dillon.speedrunnermod.mixin.main.entity.block;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceBlockEntityMixin {

    /**
     * Makes everything smelt faster.
     */
    @Inject(method = "getTotalCookTime", at = @At("HEAD"), cancellable = true)
    private static void increaseCookingTime(ServerLevel world, AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Integer> cir) {
        if (!options().main.fasterSmelting.getCurrentValue()) {
            return;
        }

        boolean bl = furnace instanceof BlastFurnaceBlockEntity || furnace instanceof SmokerBlockEntity;
        cir.setReturnValue(ModUtil.secondsAsTicks(bl ? 1 : 2));
    }
}