package net.dillon.speedrunnermod.mixin.entity.goliath;

import net.dillon.speedrunnermod.entity.goliath.Goliath;
import net.dillon.speedrunnermod.entity.goliath.GoliathAttackGoal;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.illager.Evoker;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The {@code Goliath Boss (doom mode)} exclusive.
 * <p>- Constantly regenerates health</p>
 * <p>- Knocks back players and mobs when attacking</p>
 * <p>- Teleports to the middle of the end island if it falls into the void</p>
 * <p>- Random chance of spawning TNT as a defence mechanism</p>
 * <p>- Immune to explosions, fall damage, fire and lava damage, and cannot go through end portals or gateways</p>
 * <p>- Summons TNT upon death</p>
 * <p>- And more...</p>
 */
@Mixin(Giant.class)
public class GoliathEntity extends Monster implements Goliath {
    @Unique
    protected WaterBoundPathNavigation waterNavigation;
    @Unique
    protected GroundPathNavigation landNavigation;
    @Unique
    boolean targetingUnderwater;
    @Unique
    private ServerBossEvent bossBar;

    public GoliathEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Drops more experience upon death when using looting.
     */
    @Override
    public int getBaseExperienceReward(ServerLevel world) {
        int looting = this.getLastHurtByMob() != null ? EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment((Giant)(Object)this, Enchantments.LOOTING), this.getLastHurtByMob()) * 150 : 0;
        this.xpReward = 50 + looting;
        return super.getBaseExperienceReward(world);
    }

    /**
     * Gives Goliath a bossbar and other navigation functions.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.bossBar = new ServerBossEvent(this.getUUID(), Component.translatable("entity.minecraft.giant.speedrunner_mod"), BossEvent.BossBarColor.GREEN, BossEvent.BossBarOverlay.PROGRESS);
        this.setPathfindingMalus(PathType.LAVA, 8.0F);
        this.setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, 0.0F);
        this.setPathfindingMalus(PathType.FIRE, 0.0F);
        this.waterNavigation = new WaterBoundPathNavigation(this, this.level());
        this.landNavigation = new GroundPathNavigation(this, this.level());
        ModUtil.modifyFollowRange(this, 35.0D);
        ModUtil.modifyMaxHealth(this, 400.0D);
        ModUtil.modifyMovementSpeed(this, 0.35D);
        ModUtil.modifyAttackDamage(this, 10.0D);
        ModUtil.modifyAttackKnockback(this, 1.5D);
        ModUtil.modifyKnockbackResistance(this, 0.7F);
    }

    /**
     * Gives Goliath different goals, to be able to swim, look around, attack other entities, etc.
     */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new GoliathAttackGoal((Giant) (Object) this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 32.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, true));
    }

    /**
     * Ticks the bossbar and heals Goliath progressively.
     */
    @Override
    public void tick() {
        super.tick();
        if (this.tickCount % 10 == 0) {
            this.heal(0.8F);
        }

        if (this.getHealth() <= this.getMaxHealth() / 3) {
            for (int i = 0; i < 5; i++) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0), this.getRandomY() + 1.0, this.getRandomZ(1.0), d, e, f);
            }
        }

        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Teleports Goliath to the middle of the end island if it falls into the void.
     */
    @Override
    public void checkBelowWorld() {
        if (this.level() instanceof ServerLevel && this.level().dimension() == Level.END) {
            if (this.getY() < (double)(this.level().getMinY() - 64)) {
                this.randomTeleport(0, 96, 0, true);
                if (!this.isSilent()) {
                    this.level().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.HOSTILE, 10.0F, 1.0F);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 10.0F, 1.0F);
                }
            }
        }
    }

    /**
     * Summons TNT upon death and plays a fitting sound effect.
     */
    @Override
    public void die(DamageSource source) {
        super.die(source);
        this.onGiantDeath();
        if (this.getLastHurtByMob() instanceof Player player) {
            if (!this.isSilent() && player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(new ClientboundSoundPacket(SoundEvents.RESPAWN_ANCHOR_DEPLETE, SoundSource.BLOCKS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, this.level().getRandom().nextLong()));
            }
        }
    }

    /**
     * Handles {@code damaging} for Goliath.
     */
    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();

        if (entity instanceof WitherSkull ||
                entity instanceof WitherBoss ||
                entity instanceof IronGolem ||
                entity instanceof Ravager ||
                entity instanceof Vindicator ||
                entity instanceof Zombie ||
                entity instanceof EnderDragon ||
                entity instanceof EnderMan ||
                entity instanceof Vex ||
                entity instanceof Evoker ||
                entity instanceof EvokerFangs ||
                entity instanceof AreaEffectCloud) {
            return false;
        }

        if (this.getHealth() <= this.getMaxHealth() / 2 && entity instanceof Projectile projectile) {
            if (projectile.getOwner() != null) {
                this.playSound(SoundEvents.SHIELD_BLOCK.value(), 5.0F, 1.0F);
                projectile.getOwner().hurtServer(world, projectile.getOwner().damageSources().generic(), ModUtil.randomFloatInclusive(1.0F, 3.0F));
            }
            return false;
        }

        if (this.getHealth() <= this.getMaxHealth() / 3 && entity instanceof Player) {
            this.heal(ModUtil.randomFloatInclusive(1.35F, 3.45F));
        }

        if ((this.random.nextFloat() < 0.15F || this.getHealth() <= this.getMaxHealth() / 3) && !source.is(DamageTypeTags.IS_FIRE)) {
            this.onGoliathDamage();
        }

        if (this.random.nextFloat() < 0.05F && this.getHealth() <= 250) {
            this.onGoliathDamageDropFood(world);
        }

        return super.hurtServer(world, source, amount);
    }

    /**
     * Handles {@code attacking} for Goliath.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        this.level().broadcastEntityEvent(this, (byte)4);
        return Goliath.tryAttack(world, this, (LivingEntity)target);
    }

    /**
     * Handles {@code knockback} for Goliath.
     */
    @Override
    protected void blockedByItem(LivingEntity target) {
        Goliath.knockback(this, target);
    }

    /**
     * Handles {@code movements} for Goliath.
     */
    @Override
    public void travel(Vec3 movementInput) {
        if (this.canSimulateMovement() && this.isInWater() && this.isTargetingUnderwater()) {
            this.moveRelative(0.01F, movementInput);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(movementInput);
        }
    }

    /**
     * Handles {@code swimming} for Goliath.
     */
    @Override
    public void updateSwimming() {
        super.updateSwimming();
        if (!this.level().isClientSide()) {
            if (this.canSimulateMovement() && this.isInWater() && this.isTargetingUnderwater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }
    }

    /**
     * Checks if Goliath can despawn.
     */
    @Override
    public void checkDespawn() {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL && !this.getType().isAllowedInPeaceful()) {
            this.discard();
        } else {
            this.noActionTime = 0;
        }
    }

    /**
     * Makes Goliath {@code immune to fall damage.}
     */
    @Override
    public boolean causeFallDamage(double fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    /**
     * Prevents Goliath from getting status effects.
     */
    @Override
    public boolean addEffect(MobEffectInstance effect, @Nullable Entity source) {
        return false;
    }

    /**
     * Makes Goliath immune to fire and lava damage.
     */
    @Override
    public boolean fireImmune() {
        return true;
    }

    /**
     * Makes Goliath immune to explosion damage.
     */
    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
    }

    /**
     * Prevents Goliath from being able to be ridden.
     */
    @Override
    public boolean canRide(Entity entity) {
        return false;
    }

    /**
     * Prevents Goliath from being able to use portals.
     */
    @Override
    public boolean canUsePortal(boolean allowVehicles) {
        return false;
    }

    /**
     * Sets the bossbars name to {@code "Goliath".}
     */
    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    /**
     * Detects when a player is {@code in range} of Goliath, and then {@code displays} the bossbar on that players screen.
     */
    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossBar.addPlayer(player);
    }

    /**
     * Detects when the player gets {@code out of range} of the Goliath, and then {@code removes} the bossbar from that players screen.
     */
    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossBar.removePlayer(player);
    }

    /**
     * Puts Goliath's sound under the {@code "hostile"} category (neutral if doom mode is disabled).
     */
    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    /**
     * @return the {@code ambient sound} for Goliath.
     */
    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    /**
     * @return the {@code hurt sound} for Goliath.
     */
    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ZOMBIE_HURT;
    }

    /**
     * @return the {@code death sound} for Goliath.
     */
    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    /**
     * Applies the {@code stepping sound} for Goliath.
     */
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.50F, this.getVoicePitch());
    }

    /**
     * @return the {@code volume} for Goliath.
     */
    @Override
    public float getSoundVolume() {
        return 5.0F;
    }

    /**
     * @return the {@code pitch} for Goliath.
     */
    public float getVoicePitch() {
        return 0.7F;
    }

    /**
     * @return {@code true} if Goliath is attacking while underwater.
     */
    @Unique
    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isInWater();
        }
    }

    /**
     * Drops rotten flesh randomly when Goliath is damaged.
     */
    @Unique
    private void onGoliathDamageDropFood(ServerLevel serverWorld) {
        int v = 3;
        this.dropFood(serverWorld, v);
        v--;
        if (this.random.nextFloat() < 0.3F) {
            this.dropFood(serverWorld, v);
        }
        if (this.random.nextFloat() < 0.2F) {
            this.dropFood(serverWorld, v);
        }
    }

    /**
     * Drops flesh on the ground.
     */
    @Unique
    private void dropFood(ServerLevel serverWorld, int v) {
        for (int i = 0; i < v; i++) {
            this.spawnAtLocation(serverWorld, ModItems.COOKED_FLESH);
        }
    }

    /**
     * Spawns four TNT entities around Goliath, randomly, when damaged.
     */
    @Unique
    private void onGoliathDamage() {
        for (int i = 0; i < 4; i++) {
            PrimedTnt tnt = EntityType.TNT.create(this.level(), EntitySpawnReason.TRIGGERED);
            tnt.setFuse(100);
            int x = i == 0 || i == 2 ? 5 : -5;
            int z = i == 0 || i == 1 ? 5 : -5;
            tnt.snapTo(this.getX() + x, this.getY() + 25, this.getZ() + z, 0.0F, 0.0F);
            this.level().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.TNT_PRIMED, SoundSource.AMBIENT, 5.0F, 1.0F);
            this.level().addFreshEntity(tnt);
        }
    }

    /**
     * Spawns TNT (13 exactly) entities around Goliath upon dying.
     */
    @Unique
    private void onGiantDeath() {
        int[][] tntData = {
                { 5,  25,  5, 100},
                {-5,  25,  5, 100},
                { 5,  25, -5, 100},
                {-5,  25, -5, 100},
                { 5,  50,  5, 100},
                {-5,  50,  5, 100},
                { 5,  50, -5, 100},
                {-5,  50, -5, 100},
                { 5,  75,  5, 120},
                {-5,  75,  5, 120},
                { 5,  75, -5, 120},
                {-5,  75, -5, 120},
                {0, 100,  0, 140}
        };

        for (int[] data : tntData) {
            PrimedTnt tnt = EntityType.TNT.create(this.level(), EntitySpawnReason.TRIGGERED);
            if (tnt != null) {
                tnt.setFuse(data[3]);
                if (data[3] == 100) {
                    tnt.setInvulnerable(true);
                }
                tnt.snapTo(
                        this.getX() + data[0],
                        this.getY() + data[1],
                        this.getZ() + data[2],
                        0.0F, 0.0F
                );
                this.level().addFreshEntity(tnt);
            }
        }

        this.level().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.TNT_PRIMED, SoundSource.AMBIENT, 5.0F, 1.0F);
    }
}