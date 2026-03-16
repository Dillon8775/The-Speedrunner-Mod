package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdStructure;
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
    @ModifyArgs(method = "generatePieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder;moveBelowSeaLevel(IILnet/minecraft/util/RandomSource;I)I"))
    private static void changeStrongholdMinAndMaxY(Args args) {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdYGeneration.getCurrentValue()) {
            args.set(1, ModUtil.getStrongholdMinY());
            args.set(0, ModUtil.getStrongholdMaxY());
        }
    }
}