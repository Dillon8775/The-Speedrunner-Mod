package net.dillon.speedrunnermod.mixin.world;

import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isEasyMode;

@Mixin(StrongholdPieces.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    /**
     * Increases the chances of there being an eye prefilled in an end portal block.
     */
    @ModifyConstant(method = "postProcess", constant = @Constant(floatValue = 0.9F))
    private float changePrefilledEyeChance(float constant) {
        return doomOrDefault(0.99F, isEasyMode() ? 0.6F : 0.9F);
    }
}