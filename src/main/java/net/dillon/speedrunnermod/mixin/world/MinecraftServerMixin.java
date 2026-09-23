package net.dillon.speedrunnermod.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.data.*;
import net.dillon.speedrunnermod.loot.ModFloatProviders;
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

import static net.dillon.dillonlib.util.Arithmetics.M_asTick;
import static net.dillon.speedrunnermod.main.CommonMain.LOGGER;
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
        int totalDefaultMonstersSpawns = EntitySpawnsLoader.biomesWithDefaultMonsters().size();
        int totalDefaultCreatureSpawns = EntitySpawnsLoader.biomesWithFarmAnimals().size();
        int totalDefaultWaterCreatureSpawns = EntitySpawnsLoader.biomesWithWaterAnimals().size();
        int totalNetherBiomes = 5;
        int totalEndBiomes = 2;
        int totalPlacedFeatures = 8;
        int totalStructures = 18;

        LOGGER.info("Modified {}/{} default monster spawns.", EntitySpawnsLoader.MONSTERS_MODIFIED, totalDefaultMonstersSpawns);
        LOGGER.info("Modified {}/{} default creature spawns.", EntitySpawnsLoader.CREATURES_MODIFIED, totalDefaultCreatureSpawns);
        LOGGER.info("Modified {}/{} default water creature spawns.", EntitySpawnsLoader.WATER_CREATURES_MODIFIED, totalDefaultWaterCreatureSpawns);
        LOGGER.info("Modified {}/{} nether biomes.", NetherBiomesLoader.MODIFIED_NETHER_BIOMES, totalNetherBiomes);
        LOGGER.info("Modified {}/{} end biomes.", EndBiomesLoader.MODIFIED_END_BIOMES, totalEndBiomes);
        LOGGER.info("Modified {}/{} placed features.", PlacedFeaturesLoader.MODIFIED_PLACEMENTS, totalPlacedFeatures);
        LOGGER.info("Modified {}/{} structures.", StructuresLoader.MODIFIED_STRUCTURES, totalStructures);

        if (EntitySpawnsLoader.MONSTERS_MODIFIED != totalDefaultMonstersSpawns) {
            LOGGER.error("Default monster spawns doesn't match!");
        }
        if (EntitySpawnsLoader.CREATURES_MODIFIED != totalDefaultCreatureSpawns) {
            LOGGER.error("Default creature spawns doesn't match!");
        }
        if (EntitySpawnsLoader.WATER_CREATURES_MODIFIED != totalDefaultWaterCreatureSpawns) {
            LOGGER.error("Default water creature spawns doesn't match!");
        }
        if (NetherBiomesLoader.MODIFIED_NETHER_BIOMES != totalNetherBiomes) {
            LOGGER.error("Nether biomes doesn't match!");
        }
        if (EndBiomesLoader.MODIFIED_END_BIOMES != totalEndBiomes) {
            LOGGER.error("End biomes doesn't match!");
        }
        if (PlacedFeaturesLoader.MODIFIED_PLACEMENTS != totalPlacedFeatures) {
            LOGGER.error("Placed features doesn't match!");
        }
        if (StructuresLoader.MODIFIED_STRUCTURES != totalStructures) {
            LOGGER.error("Structures doesn't match!");
        }

        EntitySpawnsLoader.MONSTERS_MODIFIED = 0;
        EntitySpawnsLoader.CREATURES_MODIFIED = 0;
        EntitySpawnsLoader.WATER_CREATURES_MODIFIED = 0;
        NetherBiomesLoader.MODIFIED_NETHER_BIOMES = 0;
        EndBiomesLoader.MODIFIED_END_BIOMES = 0;
        PlacedFeaturesLoader.MODIFIED_PLACEMENTS = 0;
        StructuresLoader.MODIFIED_STRUCTURES = 0;

        if (!isDoomMode()) {
            return;
        }

        Optional<Holder<WorldClock>> clock = level.dimensionType().defaultClock();
        clock.ifPresent(worldClockHolder -> {
            if (!serverLevelData.isInitialized()) {
                level.clockManager().setTotalTicks(worldClockHolder, 13000L);
                if (level.getRandom().nextFloat() < ModFloatProviders.getFloatUnsafe(ModFloatProviders.CHANCE_THAT_A_STORM_SPAWNS_ON_DOOM_MODE, level)) {
                    int ticks = M_asTick(10);
                    this.setWeatherParameters(0, ticks, true, true);
                    TaskScheduler.schedule(ticks, () -> this.setWeatherParameters(0, 0, false, false));
                }
            }
        });
    }
}