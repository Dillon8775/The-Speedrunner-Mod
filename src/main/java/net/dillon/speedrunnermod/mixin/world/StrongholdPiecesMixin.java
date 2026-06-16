package net.dillon.speedrunnermod.mixin.world;

import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
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
@Mixin(StrongholdPieces.class)
public class StrongholdPiecesMixin {
    @Shadow @Final @Mutable
    private static StrongholdPieces.PieceWeight[] STRONGHOLD_PIECE_WEIGHTS;

    static {
        if (options().worldGen.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdGeneration.getCurrentValue() && !isBalancedMode()) {
            if (isDoomMode()) {
                STRONGHOLD_PIECE_WEIGHTS = new StrongholdPieces.PieceWeight[]{
                        new StrongholdPieces.PieceWeight(StrongholdPieces.Straight.class, 25, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.PrisonHall.class, 50, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.LeftTurn.class, 25, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.RightTurn.class, 25, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.RoomCrossing.class, 75, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.StraightStairsDown.class, 50, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.StairsDown.class, 50, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.FiveCrossing.class, 50, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.ChestCorridor.class, 25, 5),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.Library.class, 100, options().worldGen.strongholdLibraryCount.getCurrentValue() * 2) {

                            @Override
                            public boolean doPlace(int chainLength) {
                                return super.doPlace(chainLength) && chainLength > 3;
                            }
                        }, new StrongholdPieces.PieceWeight(StrongholdPieces.PortalRoom.class, 50, 1) {

                    @Override
                    public boolean doPlace(int chainLength) {
                        return super.doPlace(chainLength) && chainLength > 5;
                    }
                }};
            } else {
                STRONGHOLD_PIECE_WEIGHTS = new StrongholdPieces.PieceWeight[]{new StrongholdPieces.PieceWeight(StrongholdPieces.Straight.class, 20, 2),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.PrisonHall.class, 5, 1),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.LeftTurn.class, 10, 2),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.RightTurn.class, 10, 2),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.RoomCrossing.class, 20, 1),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.StraightStairsDown.class, 10, 1),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.StairsDown.class, 10, 1),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.FiveCrossing.class, 10, 2),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.ChestCorridor.class, 25, 2),
                        new StrongholdPieces.PieceWeight(StrongholdPieces.PortalRoom.class, 200, options().worldGen.strongholdPortalRoomCount.getCurrentValue()) {

                            @Override
                            public boolean doPlace(int chainLength) {
                                return super.doPlace(chainLength);
                            }
                        }, new StrongholdPieces.PieceWeight(StrongholdPieces.Library.class, 200, options().worldGen.strongholdLibraryCount.getCurrentValue()) {

                    @Override
                    public boolean doPlace(int chainLength) {
                        return super.doPlace(chainLength);
                    }
                }};
            }
        }
    }
}