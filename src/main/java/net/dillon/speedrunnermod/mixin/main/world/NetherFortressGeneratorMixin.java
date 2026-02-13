package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * Makes nether fortress smaller and easier to navigate, see {@link ModWorldGen} for more.
 */
@Mixin(NetherFortressGenerator.class)
public class NetherFortressGeneratorMixin {
    @Shadow @Final @Mutable
    private static NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES;
    @Shadow @Final @Mutable
    private static  NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES;

    static {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedNetherFortressGeneration.getCurrentValue() && !isBalancedMode()) {
            ALL_BRIDGE_PIECES = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};
            ALL_CORRIDOR_PIECES = new NetherFortressGenerator.PieceData[]{
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2),
                    new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
        }
    }
}