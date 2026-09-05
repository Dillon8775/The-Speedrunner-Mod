package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.world.ModWorldGeneration;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressStructure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(NetherFortressStructure.class)
public class NetherFortressStructureMixin {
    /**
     * Changes monster spawning in nether fortresses, see {@link ModWorldGeneration} for details.
     */
    @Shadow @Final @Mutable
    private static WeightedList<MobSpawnSettings.SpawnerData> FORTRESS_ENEMIES;

    static {
        if (common().accessibility().modifiedStrongholdGeneration && !isBalancedMode()) {
            if (isDoomMode()) {
                FORTRESS_ENEMIES = WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, new UniformInt(1, 4)), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.PIGLIN_BRUTE, new UniformInt(2, 4)), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIFIED_PIGLIN, new ConstantInt(1)), 25)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.WITHER_SKELETON, new UniformInt(4, 12)), 75)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, new ConstantInt(5)), 50)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, new UniformInt(1, 4)), 20)
                        .build();
            } else {
                WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, new UniformInt(1, 4)), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.PIGLIN, new UniformInt(2, 4)), 15)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIFIED_PIGLIN, new UniformInt(1, 2)), 3)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.WITHER_SKELETON, new UniformInt(1, 2)), 8)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, new UniformInt(1, 2)), 1)
                        .add(new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, new ConstantInt(1)), 1)
                        .build();
            }
        }
    }
}