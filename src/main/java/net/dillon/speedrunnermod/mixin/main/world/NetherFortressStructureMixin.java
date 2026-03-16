package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(NetherFortressStructure.class)
public class NetherFortressStructureMixin {
    /**
     * Changes monster spawning in nether fortresses, see {@link ModWorldGen} for details.
     */
    @Shadow @Final @Mutable
    private static WeightedList<MobSpawnSettings.SpawnerData> FORTRESS_ENEMIES;

    static {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdGeneration.getCurrentValue() && !isBalancedMode()) {
            if (isDoomMode()) {
                FORTRESS_ENEMIES = WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 1, 4), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.PIGLIN_BRUTE, 2, 4), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 1, 1), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 4, 12), 75)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 5, 5), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 1, 4), 20)
                        .build();
            } else {
                WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 1, 4), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 2, 4), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 1, 2), 3)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 1, 2), 8)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 1, 2), 1)
                        .add(new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 1, 1), 1)
                        .build();
            }
        }
    }
}