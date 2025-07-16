package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PillagerEntity.class)
public class PillagerEntityMixin {

    /**
     * Modifies {@code pillager} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePillagerAttributes(EntityType<? extends PillagerEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 32.0D : 12.0D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 8.0D : 4.0D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 32.0D : 16.0D);
    }
}