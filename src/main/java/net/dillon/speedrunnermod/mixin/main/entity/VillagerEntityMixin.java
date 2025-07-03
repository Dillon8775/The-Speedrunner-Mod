package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Shadow
    public abstract VillagerData getVillagerData();

    @Inject(method = "beginTradeWith", at = @At("TAIL"))
    private void speedrunnersWorkbenchBlock(PlayerEntity customer, CallbackInfo ci) {
        if (this.getVillagerData().profession().matchesKey(ModVillagers.RETIRED_SPEEDRUNNER_KEY)) {
            ModUtil.completeStepS2C(TutorialStep.INTERACT_WITH_RETIRED_SPEEDRUNNER, customer,
                    "speedrunnermod.tutorial_mode.retired_speedrunner_description",
                    isDoomMode() ? "speedrunnermod.tutorial_mode.use_annul_eye" :
                            isEasyMode() ? "speedrunnermod.tutorial_mode.craft_ender_thruster" :
                                    "speedrunnermod.tutorial_mode.craft_wither_bone");
        }
    }
}