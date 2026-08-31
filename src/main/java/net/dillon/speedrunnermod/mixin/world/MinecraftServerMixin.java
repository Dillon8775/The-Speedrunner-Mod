package net.dillon.speedrunnermod.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.util.RandomChance;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow
    public abstract void setWeatherParameters(int clearTime, int rainTime, boolean raining, boolean thundering);

    /**
     * Sets the time to night upon world creation when doom mode is enabled.
     */
    @Author(Authors.ECLIPSEISOFFLINE)
    @Inject(method = "createLevels", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ServerScoreboard;load(Lnet/minecraft/world/scores/ScoreboardSaveData$Packed;)V"))
    private void createLevels(CallbackInfo ci, @Local(name = "levelData") ServerLevelData serverLevelData, @Local(name = "overworld") ServerLevel level) {
        if (!isDoomMode()) {
            return;
        }

        Optional<Holder<WorldClock>> clock = level.dimensionType().defaultClock();
        clock.ifPresent(worldClockHolder -> {
            if (!serverLevelData.isInitialized()) {
                level.clockManager().setTotalTicks(worldClockHolder, 13000L);
                if (level.getRandom().nextFloat() < RandomChance.floatInclusive(0.25F, 0.33F)) {
                    int ticks = Arithmetics.mas(10);
                    this.setWeatherParameters(0, ticks, true, true);
                    TaskScheduler.schedule(ticks, () -> this.setWeatherParameters(0, 0, false, false));
                }
            }
        });
    }
}