package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.world.ModWorldGeneration;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * Makes nether fortress smaller and easier to navigate, see {@link ModWorldGeneration} for more.
 */
@Mixin(NetherFortressPieces.class)
public class NetherFortressPiecesMixin {
    @Shadow @Final @Mutable
    private static NetherFortressPieces.PieceWeight[] BRIDGE_PIECE_WEIGHTS;
    @Shadow @Final @Mutable
    private static  NetherFortressPieces.PieceWeight[] CASTLE_PIECE_WEIGHTS;

    static {
        if (options().worldGen.customDataGeneration.getCurrentValue() && options().advanced.modifiedNetherFortressGeneration.getCurrentValue() && !isBalancedMode()) {
            BRIDGE_PIECE_WEIGHTS = new NetherFortressPieces.PieceWeight[]{
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.BridgeStraight.class, 10, 1),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.BridgeCrossing.class, 10, 2),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.RoomCrossing.class, 10, 2),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.StairsRoom.class, 10, 1),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.MonsterThrone.class, 50, 3),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleEntrance.class, 10, 1)};
            CASTLE_PIECE_WEIGHTS = new NetherFortressPieces.PieceWeight[]{
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleSmallCorridorPiece.class, 10, 2),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleSmallCorridorCrossingPiece.class, 10, 2),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleSmallCorridorRightTurnPiece.class, 25, 3),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleSmallCorridorLeftTurnPiece.class, 25, 3),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleCorridorStairsPiece.class, 10, 2, true),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleCorridorTBalconyPiece.class, 7, 2),
                    new NetherFortressPieces.PieceWeight(NetherFortressPieces.CastleStalkRoom.class, 20, 2)};
        }
    }
}