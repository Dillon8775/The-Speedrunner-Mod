package net.dillon.speedrunnermod.mixin.main.world;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code doom mode} is enabled.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow @Final @Mutable
    private static StrongholdGenerator.PieceData[] ALL_PIECES;

    static {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdGeneration.getCurrentValue() && !isBalancedMode()) {
            if (isDoomMode()) {
                ALL_PIECES = new StrongholdGenerator.PieceData[]{
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, options().main.strongholdLibraryCount.getCurrentValue() * 2) {

                            @Override
                            public boolean canGenerate(int chainLength) {
                                return super.canGenerate(chainLength) && chainLength > 3;
                            }
                        }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 1) {

                    @Override
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength) && chainLength > 5;
                    }
                }};
            } else {
                ALL_PIECES = new StrongholdGenerator.PieceData[]{new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2),
                        new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, options().main.strongholdPortalRoomCount.getCurrentValue()) {

                            @Override
                            public boolean canGenerate(int chainLength) {
                                return super.canGenerate(chainLength);
                            }
                        }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, options().main.strongholdLibraryCount.getCurrentValue()) {

                    @Override
                    public boolean canGenerate(int chainLength) {
                        return super.canGenerate(chainLength);
                    }
                }};
            }
        }
    }
}