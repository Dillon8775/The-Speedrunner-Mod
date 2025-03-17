package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.TutorialMode;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
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
        if (options().main.tutorialMode && options().main.playingMode.easy() && !options().tutorialMode.interactedWithRetiredSpeedrunner && this.getVillagerData().getProfession().equals(ModVillagers.RETIRED_SPEEDRUNNER)) {
            this.send("speedrunnermod.tutorial_mode.interacted_with_retired_speedrunner", customer);
            this.send("speedrunnermod.tutorial_mode.obtain_ender_thruster.easy", customer);
            this.playDing(customer);
            options().tutorialMode.interactedWithRetiredSpeedrunner = true;
            ModOptions.saveConfig();
        }
    }
}