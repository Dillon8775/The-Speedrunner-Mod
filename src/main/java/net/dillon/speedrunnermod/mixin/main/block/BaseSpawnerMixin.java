package net.dillon.speedrunnermod.mixin.main.block;

import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Changes the maximum spawn delay for mobs to spawn from spawner blocks.
 */
@Mixin(value = BaseSpawner.class, priority = 999)
public class BaseSpawnerMixin {
    @Shadow
    private int maxSpawnDelay = options().main.fasterSpawners.getCurrentValue() ? 400 : 800;
}