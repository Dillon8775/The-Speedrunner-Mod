package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ElderGuardianEntity.class)
public class ElderGuardianEntityMixin extends GuardianEntity {

    public ElderGuardianEntityMixin(EntityType<? extends GuardianEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code elder guardian} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends ElderGuardianEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMovementSpeed(this, 0.30000001192092896D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 8.0D : 4.0D);
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 50.0D : 25.0D);
    }

    /**
     * @author Dillon8775
     * @reason Decreases the range that an elder guardian can detect a player, and also decreases the mining fatigue duration.
     */
    @Overwrite
    public void mobTick(ServerWorld world) {
        super.mobTick(world);
        final int i = isDoomMode() ? 600 : 6000;
        if ((this.age + this.getId()) % i == 0) {
            final int duration = isDoomMode() ? ModUtil.minutesInTicks(5) : ModUtil.secondsInTicks(30);
            final double d = isDoomMode() ? 55.0D : 25.0D;
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, duration, 2);
            List<ServerPlayerEntity> list = StatusEffectUtil.addEffectToPlayersWithinDistance((ServerWorld)this.getWorld(), this, this.getPos(), d, statusEffectInstance, 1200);
            list.forEach(serverPlayerEntity -> serverPlayerEntity.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.ELDER_GUARDIAN_EFFECT, this.isSilent() ? GameStateChangeS2CPacket.DEMO_OPEN_SCREEN : (int)1.0f)));
        }
        if (!this.hasPositionTarget()) {
            this.setPositionTarget(this.getBlockPos(), 16);
        }
    }
}