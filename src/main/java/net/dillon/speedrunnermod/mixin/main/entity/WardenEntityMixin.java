package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(WardenEntity.class)
public class WardenEntityMixin extends HostileEntity {
    @Unique
    private ServerBossBar bossBar;

    public WardenEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code warden} attributes.

     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends WardenEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 400.0D : 200.0D);
        ModUtil.modifyMovementSpeed(this, isDoomMode() ? 0.4D : 0.2D);
        ModUtil.modifyKnockbackResistance(this, isDoomMode() ? 1.0D : 0.65D);
        ModUtil.modifyAttackKnockback(this, isDoomMode() ? 2.0D : 1.0D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 30.0D : 15.0D);
    }

    /**
     * Gives the warden a bossbar.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void createBossBar(EntityType<? extends HostileEntity> entityType, World world, CallbackInfo ci) {
        this.bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);
    }

    /**
     * Ticks the warden's bossbar.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickBossBar(CallbackInfo ci) {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Sets the name of the warden's bossbar to {@code "Warden".}
     */
    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    /**
     * Detects when a player is {@code in range} of a warden, and then {@code displays} the bossbar on that players screen.
     */
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    /**
     * Detects when a player gets {@code out of range} of a warden, and then {@code removes} the bossbar from that players screen.
     */
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }
}