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
public abstract class ServerWorldMixin {

    /**
     * For tutorial mode.
     */
    @Inject(method = "onDimensionChanged", at = @At("TAIL"))
    private void tutorialModeDimensionChange(Entity entity, CallbackInfo ci) {
        if (options().main.tutorialMode && entity instanceof ServerPlayerEntity player && player.getWorld().getRegistryKey() == World.END) {
            options().tutorialMode.completeStep(TutorialStep.ENTERED_END, player, "speedrunnermod.tutorial_mode.enter_end.easy");
        }
    }
}