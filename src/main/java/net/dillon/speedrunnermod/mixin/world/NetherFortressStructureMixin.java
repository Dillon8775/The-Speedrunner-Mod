package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.world.ModWorldGeneration;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityTypes;
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
     * Changes monster spawning in nether fortresses, see {@link ModWorldGeneration} for details.
     */
    @Shadow @Final @Mutable
    private static WeightedList<MobSpawnSettings.SpawnerData> FORTRESS_ENEMIES;

    static {
        if (options().worldGen.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdGeneration.getCurrentValue() && !isBalancedMode()) {
            if (isDoomMode()) {
                FORTRESS_ENEMIES = WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, 1, 4), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.PIGLIN_BRUTE, 2, 4), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIFIED_PIGLIN, 1, 1), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.WITHER_SKELETON, 4, 12), 75)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, 5, 5), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, 1, 4), 20)
                        .build();
            } else {
                WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, 1, 4), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.PIGLIN, 2, 4), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIFIED_PIGLIN, 1, 2), 3)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.WITHER_SKELETON, 1, 2), 8)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, 1, 2), 1)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, 1, 1), 1)
                        .build();
            }
        }
    }
}