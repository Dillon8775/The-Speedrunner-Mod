package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PathAwareEntity.class)
public class PathAwareEntityMixin extends MobEntity {

    public PathAwareEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Allows all doom mode mobs to spawn in the end.
     */
    @Inject(method = "canSpawn", at = @At("HEAD"), cancellable = true)
    private void allowSpawningInEnd(WorldAccess world, SpawnReason spawnReason, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && world.getDimension().getSkybox()) {
            cir.setReturnValue(true);
        }
    }
}