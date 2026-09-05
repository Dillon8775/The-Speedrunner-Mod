package net.dillon.speedrunnermod.mixin.structure;

import net.minecraft.world.level.levelgen.structure.structures.StrongholdStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

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
        if (common().accessibility().modifiedStrongholdYGeneration) {
            args.set(1, doomOrDefault(-48, 27)); // Min Y
            args.set(0, doomOrDefault(0, 63)); // Max Y
        }
    }
}