package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    /**
     * Increases the chances of there being an eye prefilled in an end portal block.
     */
    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return ModUtil.getPrefilledEnderEyeChance();
    }
}