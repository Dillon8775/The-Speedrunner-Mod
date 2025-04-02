package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.TutorialMode;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.village.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin implements TutorialMode {
    @Shadow
    public abstract VillagerData getVillagerData();

    @Inject(method = "beginTradeWith", at = @At("TAIL"))
    private void speedrunnersWorkbenchBlock(PlayerEntity customer, CallbackInfo ci) {
        if (options().main.tutorialMode && this.getVillagerData().getProfession().equals(ModVillagers.RETIRED_SPEEDRUNNER)) {
            options().tutorialMode.completeStep(TutorialStep.INTERACTED_WITH_RETIRED_SPEEDRUNNER, customer,
                    "speedrunnermod.tutorial_mode.interacted_with_retired_speedrunner",
                    "speedrunnermod.tutorial_mode.obtain_ender_thruster.easy");
        }
    }
}