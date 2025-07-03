package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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

    /**
     * @author Dillon8775
     * @reason Modifies {@code warden} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder addAttributes() {
        double genericMaxHealth = isDoomMode() ? 400.0 : 200.0;
        double genericMovementSpeed = isDoomMode() ? 0.4 : 0.2;
        double genericKnockbackResistance = isDoomMode() ? 1.0 : 0.65;
        double genericAttackKnockback = isDoomMode() ? 2.0 : 1.0;
        double genericAttackDamage = isDoomMode() ? 30.0 : 15.0;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, genericKnockbackResistance)
                .add(EntityAttributes.ATTACK_KNOCKBACK, genericAttackKnockback)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}