package net.dillon.speedrunnermod.mixin.fix;

import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

@Mixin(PathfinderMob.class)
public class PathfinderMobFix extends Mob {

    public PathfinderMobFix(EntityType<? extends Mob> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Allows all doom mode mobs to spawn in the end.
     */
    @Inject(method = "checkSpawnRules", at = @At("HEAD"), cancellable = true)
    private void allowSpawningInEnd(LevelAccessor world, EntitySpawnReason spawnReason, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && world.dimensionType().hasEndFlashes()) {
            cir.setReturnValue(true);
        }
    }
}