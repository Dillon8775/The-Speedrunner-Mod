package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.util.ModConstants;
import net.minecraft.world.gen.structure.StrongholdStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Changes how stronghold's initially generate in a world.
 */
@Mixin(StrongholdStructure.class)
public abstract class StrongholdFeatureMixin {

    /**
     * Changes the {@code minimum} and {@code maximum Y-value} a stronghold can generate at.
     */
    @ModifyArgs(method = "addPieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StructurePiecesCollector;shiftInto(IILnet/minecraft/util/math/random/Random;I)I"))
    private static void changeMinAndMaxYValue(Args args) {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdYGeneration.getCurrentValue()) {
            args.set(1, ModConstants.STRONGHOLD_MIN_Y);
            args.set(0, ModConstants.STRONGHOLD_MAX_Y);
        }
    }
}