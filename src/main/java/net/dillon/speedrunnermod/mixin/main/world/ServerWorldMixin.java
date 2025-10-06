package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.server.ServerStorage;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    /**
     * For tutorial mode.
     */
    @Inject(method = "onDimensionChanged", at = @At("TAIL"))
    private void tutorialModeDimensionChange(Entity entity, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player && ServerStorage.isTutorialModeEnabledForPlayer(player) && player.getEntityWorld().getRegistryKey() == World.END) {
            if (isDoomMode()) {
                ModUtil.completeStepS2C(TutorialStep.ENTER_END, player,
                        "speedrunnermod.tutorial_mode.entered_end.doom",
                        "speedrunnermod.tutorial_mode.obtain_totem");
            } else {
                ModUtil.completeStepS2C(TutorialStep.ENTER_END, player,
                        isEasyMode() ? "speedrunnermod.tutorial_mode.entered_end.easy" :
                        "speedrunnermod.tutorial_mode.entered_end.normal");
            }
        }
    }
}