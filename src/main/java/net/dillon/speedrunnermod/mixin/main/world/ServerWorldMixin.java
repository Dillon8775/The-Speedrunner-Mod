package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.item.TutorialMode;
import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements TutorialMode {

    /**
     * For tutorial mode.
     */
    @Inject(method = "onDimensionChanged", at = @At("TAIL"))
    private void tutorialModeDimensionChange(Entity entity, CallbackInfo ci) {
        boolean tutorialModeCriteria = options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && options().tutorialMode.usedPiglinAwakener && options().tutorialMode.obtainedBlazeSpotter && options().tutorialMode.usedBlazeSpotter && options().tutorialMode.obtainedSpeedrunnersEye && options().tutorialMode.changedSpeedrunnersEyeLocator && options().tutorialMode.usedSpeedrunnersEye && options().tutorialMode.obtainedDragonsPearl && options().tutorialMode.obtainedAnnulEye && options().tutorialMode.usedAnnulEyeTeleporter && !options().tutorialMode.enteredEnd;
        if (options().main.tutorialMode && entity instanceof PlayerEntity player) {
            if (tutorialModeCriteria && player.getWorld().getRegistryKey() == World.END) {
                this.send("speedrunnermod.tutorial_mode.enter_end", player);
                options().tutorialMode.enteredEnd = true;
                ModOptions.saveConfig();
            }

            if (options().tutorialMode.killedDragon && player.getWorld().getRegistryKey() == World.OVERWORLD) {
                this.send("speedrunnermod.tutorial_mode.exit_end", player);
            }
        }
    }
}