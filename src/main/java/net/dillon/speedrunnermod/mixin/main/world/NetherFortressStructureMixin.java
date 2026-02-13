package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.structure.NetherFortressStructure;
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
    private static Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS;

    static {
        if (options().main.customDataGeneration.getCurrentValue() && options().advanced.modifiedStrongholdGeneration.getCurrentValue() && !isBalancedMode()) {
            if (isDoomMode()) {
                MONSTER_SPAWNS = Pool.<SpawnSettings.SpawnEntry>builder()
                        .add(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 1, 4), 50)
                        .add(new SpawnSettings.SpawnEntry(EntityType.PIGLIN_BRUTE, 2, 4), 25)
                        .add(new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 1), 25)
                        .add(new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 4, 12), 75)
                        .add(new SpawnSettings.SpawnEntry(EntityType.SKELETON, 5, 5), 50)
                        .add(new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 4), 20)
                        .build();
            } else {
                Pool.<SpawnSettings.SpawnEntry>builder()
                        .add(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 1, 4), 15)
                        .add(new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 2, 4), 15)
                        .add(new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2), 3)
                        .add(new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 1, 2), 8)
                        .add(new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 2), 1)
                        .add(new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1), 1)
                        .build();
            }
        }
    }
}