package net.dillon.speedrunnermod.mixin.entity.goliath;

import net.dillon.speedrunnermod.entity.goliath.Minion;
import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.tag.ModDamageTypeTags;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Zombie.class)
public class ZombieMixin extends Monster implements Minion {
    @Unique
    private static final EntityDataAccessor<Integer> FIREBALL_CHARGE_TIME = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.INT);
    @Unique
    private static final EntityDataAccessor<Boolean> GOLIATH_MINION = SynchedEntityData.defineId(Zombie.class, EntityDataSerializers.BOOLEAN);

    public ZombieMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void setFireballChargeTime(int time) {
        this.entityData.set(FIREBALL_CHARGE_TIME, time);
    }

    @Override
    public int getFireballChargeTime() {
        return this.entityData.get(FIREBALL_CHARGE_TIME);
    }

    @Override
    public void setGoliathMinion(boolean value) {
        this.entityData.set(GOLIATH_MINION, value);
    }

    @Override
    public boolean isGoliathMinion() {
        return this.entityData.get(GOLIATH_MINION);
    }

    /**
     * Creates the {@code fireball charge time.}
     */
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void writeFireproofTracker(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(FIREBALL_CHARGE_TIME, 0);
        builder.define(GOLIATH_MINION, false);
    }

    /**
     * Writes the {@code fireball charge time} to NBT.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeFireproofToNbt(ValueOutput view, CallbackInfo ci) {
        view.putInt("FireballChargeTime", this.getFireballChargeTime());
        view.putBoolean("GoliathMinion", this.isGoliathMinion());
    }

    /**
     * Reads the {@code fireball charge time} by NBT and writes it back.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readFireproofFromNbt(ValueInput view, CallbackInfo ci) {
        this.setFireballChargeTime(view.getIntOr("FireballChargeTime", 0));
        this.setGoliathMinion(view.getBooleanOr("GoliathMinion", false));
    }

    /**
     * Modifies {@code zombie} attributes.
     */
    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("TAIL"))
    private void changeZombieAttributes(EntityType<? extends Zombie> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyFollowRange(this, isDoomMode() ? 50.0D : 25.0D);
        ModAttributeHelper.modifyMovementSpeed(this, isDoomMode() ? 0.33D : 0.23D);
        ModAttributeHelper.modifyAttackDamage(this, isDoomMode() ? 7.0D : 2.0D);
        ModAttributeHelper.modifyArmor(this, isDoomMode() ? 2.0D : 1.0D);
    }

    /**
     * Gives zombies fireballs on doom mode.
     */
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void giveZombiesFireballs(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (level.getRandom().nextFloat() < Minion.spawnWithFireballChance()) {
            this.setItemSlot(EquipmentSlot.MAINHAND, Minion.zombiesFireball(Items.FIRE_CHARGE));
            ModAttributeHelper.modifyMaxHealth(this, this.getAttributeValue(Attributes.MAX_HEALTH) / 2);
            ModAttributeHelper.modifyArmor(this, this.getAttributeValue(Attributes.ATTACK_DAMAGE) / 2);
        }
    }

    /**
     * Makes zombies always target players if they are a minion.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void setTargetPlayer(CallbackInfo ci) {
        if (!this.isGoliathMinion()) {
            return;
        }

        List<Player> players = this.findEntities(Player.class);
        List<Mob> surroundingMobs = this.findEntities(Mob.class);
        if (players.isEmpty()) {
            return;
        }
        Player targetPlayer = players.getFirst();
        for (Mob mob : surroundingMobs) {
            if (!mob.is(ModEntityTypeTags.BLACKLISTED_MINION_CALL_MOBS)) {
                mob.setTarget(targetPlayer);
            }
        }
        this.setTarget(targetPlayer);
        targetPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, TickCalculator.seconds(3)));

        net.dillon.speedrunnermod.entity.goliath.Goliath.addAngryParticles(this);
        net.dillon.speedrunnermod.entity.goliath.Goliath.safeFromVoid(this);
    }

    /**
     * Makes zombies immune to explosions and fire damage if they are a minion.
     */
    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    private void immuneToFireAndExplosions(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!this.isGoliathMinion()) {
            return;
        }

        if (source.is(DamageTypes.IN_WALL)) {
            List<Giant> giants = this.findEntities(Giant.class);
            Giant goliath = giants.getFirst();
            this.teleportTo(goliath.getX(), goliath.getY() + 25, goliath.getZ());
            cir.setReturnValue(false);
        }

        if (!source.is(ModDamageTypeTags.ALLOWED_ZOMBIE_MINION_DAMAGE_TYPES)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        if (!super.doHurtTarget(world, target)) {
            return false;
        } else {
            if (isDoomMode() && target instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TickCalculator.seconds(10), 0));
            }

            return true;
        }
    }

    /**
     * @return a list of entities.
     */
    @Unique
    private List findEntities(Class<? extends LivingEntity> entity) {
        return ModHelper.getEntitiesWithinRange(this.level(), entity, this, options().advanced.goliathAndZombieEntityDetectionRadius.getCurrentValue());
    }
}