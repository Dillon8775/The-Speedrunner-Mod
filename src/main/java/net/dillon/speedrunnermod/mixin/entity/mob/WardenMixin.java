package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Warden.class)
public class WardenMixin extends Monster {
    @Unique
    private ServerBossEvent bossBar;

    public WardenMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code warden} attributes.

     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeWardenAttributes(EntityType<? extends Warden> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMaxHealth(this, isDoomMode() ? 500.0D : 200.0D);
        ModAttributeHelper.modifyMovementSpeed(this, isDoomMode() ? 0.4D : 0.2D);
        ModAttributeHelper.modifyKnockbackResistance(this, isDoomMode() ? 1.0D : 0.65D);
        ModAttributeHelper.modifyAttackKnockback(this, isDoomMode() ? 2.0D : 1.0D);
        ModAttributeHelper.modifyAttackDamage(this, isDoomMode() ? 30.0D : 15.0D);
    }

    /**
     * Gives the warden a bossbar.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void createBossBar(EntityType<? extends Monster> entityType, Level world, CallbackInfo ci) {
        this.bossBar = new ServerBossEvent(this.getUUID(), this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
    }

    /**
     * Ticks the warden's bossbar.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void tickBossBar(CallbackInfo ci) {
        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Sets the name of the warden's bossbar to {@code "Warden".}
     */
    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    /**
     * Detects when a player is {@code in range} of a warden, and then {@code displays} the bossbar on that players screen.
     */
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    /**
     * Detects when a player gets {@code out of range} of a warden, and then {@code removes} the bossbar from that players screen.
     */
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }
}