package net.dillon.speedrunnermod.mixin.block;

import net.minecraft.world.level.BaseSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * Changes the maximum spawn delay for mobs to spawn from spawner blocks.
 */
@Mixin(value = BaseSpawner.class, priority = 999)
public class BaseSpawnerMixin {
    @Shadow
    private int maxSpawnDelay = common().general.fasterSpawners.getCurrentValue() ? 400 : 800;
}