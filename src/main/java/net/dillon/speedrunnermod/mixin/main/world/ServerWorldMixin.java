package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    /**
     * For tutorial mode.
     */
    @Inject(method = "onDimensionChanged", at = @At("TAIL"))
    private void tutorialModeDimensionChange(Entity entity, CallbackInfo ci) {
        if (options().main.tutorialMode && entity instanceof ServerPlayerEntity player && player.getWorld().getRegistryKey() == World.END) {
            if (options().main.playingMode.doom()) {
                options().tutorialMode.completeStep(TutorialStep.ENTER_END, player,
                        "speedrunnermod.tutorial_mode.entered_end.doom",
                        "speedrunnermod.tutorial_mode.obtain_totem");
            } else {
                options().tutorialMode.completeStep(TutorialStep.ENTER_END, player,
                        options().main.playingMode.easy() ? "speedrunnermod.tutorial_mode.entered_end.easy" :
                        "speedrunnermod.tutorial_mode.entered_end.normal");
            }
        }
    }
}