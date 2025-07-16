package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PiglinBruteEntity.class)
public class PiglinBruteEntityMixin {

    /**
     * Modifies the {@code maximum health} for the piglin brute.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePiglinBruteAttributes(EntityType<? extends PiglinBruteEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 25.0D : 50.0D);
    }
}