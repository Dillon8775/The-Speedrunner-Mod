package net.dillon.speedrunnermod.mixin.main.entity.giant;

import net.dillon.speedrunnermod.entity.Giant;
import net.dillon.speedrunnermod.entity.GiantAttackGoal;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
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
@Mixin(GiantEntity.class)
public class GiantEntityMixin extends HostileEntity implements Giant {
    @Unique
    protected SwimNavigation waterNavigation;
    @Unique
    protected MobNavigation landNavigation;
    @Unique
    boolean targetingUnderwater;
    @Unique
    private ServerBossBar bossBar;

    public GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Drops more experience upon death when using looting.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        int looting = this.getAttacker() != null ? EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment((GiantEntity)(Object)this, Enchantments.LOOTING), this.getAttacker()) * 150 : 0;
        this.experiencePoints = 50 + looting;
        return super.getExperienceToDrop(world);
    }

    /**
     * Gives Goliath a bossbar and other navigation functions.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.bossBar = new ServerBossBar(Text.translatable("entity.minecraft.giant.speedrunner_mod"), BossBar.Color.GREEN, BossBar.Style.PROGRESS);
        this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
        this.waterNavigation = new SwimNavigation(this, this.getEntityWorld());
        this.landNavigation = new MobNavigation(this, this.getEntityWorld());
        ModUtil.modifyMaxHealth(this, 400.0D);
        ModUtil.modifyMovementSpeed(this, 0.35D);
        ModUtil.modifyAttackDamage(this, 10.0D);
        ModUtil.modifyAttackKnockback(this, 1.5D);
    }

    /**
     * Gives Goliath different goals, to be able to swim, look around, attack other entities, etc.
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new GiantAttackGoal((GiantEntity) (Object) this, 1.0D, false));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MobEntity.class, true));
    }

    /**
     * Ticks the bossbar and heals Goliath progressively.
     */
    @Override
    public void tick() {
        super.tick();
        if (this.age % 10 == 0) {
            this.heal(0.8F);
        }

        if (this.getHealth() <= this.getMaxHealth() / 3) {
            for (int i = 0; i < 5; i++) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.getEntityWorld().addParticleClient(ParticleTypes.ANGRY_VILLAGER, this.getParticleX(1.0), this.getRandomBodyY() + 1.0, this.getParticleZ(1.0), d, e, f);
            }
        }

        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    /**
     * Teleports Goliath to the middle of the end island if it falls into the void.
     */
    @Override
    public void attemptTickInVoid() {
        if (this.getEntityWorld() instanceof ServerWorld && this.getEntityWorld().getRegistryKey() == World.END) {
            if (this.getY() < (double)(this.getEntityWorld().getBottomY() - 64)) {
                this.teleport(0, 96, 0, true);
                if (!this.isSilent()) {
                    this.getEntityWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                    this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                }
            }
        }
    }

    /**
     * Summons TNT upon death and plays a fitting sound effect.
     */
    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        this.onGiantDeath();
        if (this.getAttacker() instanceof PlayerEntity player) {
            if (!this.isSilent() && player instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.BLOCKS, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), 1.0F, 1.0F, this.getEntityWorld().getRandom().nextLong()));
            }
        }
    }

    /**
     * Handles {@code damaging} for Goliath.
     */
    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        Entity entity = source.getSource();

        if (entity instanceof WitherSkullEntity ||
                entity instanceof WitherEntity ||
                entity instanceof IronGolemEntity ||
                entity instanceof RavagerEntity ||
                entity instanceof VindicatorEntity ||
                entity instanceof ZombieEntity ||
                entity instanceof EnderDragonEntity ||
                entity instanceof EndermanEntity ||
                entity instanceof VexEntity ||
                entity instanceof EvokerEntity ||
                entity instanceof EvokerFangsEntity ||
                entity instanceof AreaEffectCloudEntity) {
            return false;
        }

        if (this.getHealth() <= this.getMaxHealth() / 2 && entity instanceof ProjectileEntity projectile) {
            if (projectile.getOwner() != null) {
                this.playSound(SoundEvents.ITEM_SHIELD_BLOCK.value(), 5.0F, 1.0F);
                projectile.getOwner().damage(world, projectile.getOwner().getDamageSources().generic(), ModUtil.randomFloatInclusive(1.0F, 3.0F));
            }
            return false;
        }

        if (this.getHealth() <= this.getMaxHealth() / 3 && entity instanceof PlayerEntity) {
            this.heal(ModUtil.randomFloatInclusive(1.35F, 3.45F));
        }

        if ((this.random.nextFloat() < 0.15F || this.getHealth() <= this.getMaxHealth() / 3) && !source.isIn(DamageTypeTags.IS_FIRE)) {
            this.onGiantDamage();
        }

        if (this.random.nextFloat() < 0.05F && this.getHealth() <= 250) {
            this.onGiantDamageDropFood(world);
        }

        return super.damage(world, source, amount);
    }

    /**
     * Handles {@code attacking} for Goliath.
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        this.getEntityWorld().sendEntityStatus(this, (byte)4);
        return Giant.tryAttack(world, this, (LivingEntity)target);
    }

    /**
     * Handles {@code knockback} for Goliath.
     */
    @Override
    protected void knockback(LivingEntity target) {
        Giant.knockback(this, target);
    }

    /**
     * Handles {@code movements} for Goliath.
     */
    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
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
        if (!this.getEntityWorld().isClient()) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
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
        if (this.getEntityWorld().getDifficulty() == Difficulty.PEACEFUL && !this.getType().isAllowedInPeaceful()) {
            this.discard();
        } else {
            this.despawnCounter = 0;
        }
    }

    /**
     * Makes Goliath {@code immune to fall damage.}
     */
    @Override
    public boolean handleFallDamage(double fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    /**
     * Prevents Goliath from getting status effects.
     */
    @Override
    public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
        return false;
    }

    /**
     * Makes Goliath immune to fire and lava damage.
     */
    @Override
    public boolean isFireImmune() {
        return true;
    }

    /**
     * Makes Goliath immune to explosion damage.
     */
    @Override
    public boolean isImmuneToExplosion(Explosion explosion) {
        return true;
    }

    /**
     * Prevents Goliath from being able to be ridden.
     */
    @Override
    public boolean canStartRiding(Entity entity) {
        return false;
    }

    /**
     * Prevents Goliath from being able to use portals.
     */
    @Override
    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    /**
     * Sets the bossbars name to {@code "Goliath".}
     */
    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    /**
     * Detects when a player is {@code in range} of Goliath, and then {@code displays} the bossbar on that players screen.
     */
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    /**
     * Detects when the player gets {@code out of range} of the Goliath, and then {@code removes} the bossbar from that players screen.
     */
    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    /**
     * Puts Goliath's sound under the {@code "hostile"} category (neutral if doom mode is disabled).
     */
    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    /**
     * @return the {@code ambient sound} for Goliath.
     */
    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    /**
     * @return the {@code hurt sound} for Goliath.
     */
    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    /**
     * @return the {@code death sound} for Goliath.
     */
    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    /**
     * Applies the {@code stepping sound} for Goliath.
     */
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.50F, this.getSoundPitch());
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
    public float getSoundPitch() {
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
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    /**
     * Drops rotten flesh randomly when Goliath is damaged.
     */
    @Unique
    private void onGiantDamageDropFood(ServerWorld serverWorld) {
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
    private void dropFood(ServerWorld serverWorld, int v) {
        for (int i = 0; i < v; i++) {
            this.dropItem(serverWorld, ModItems.COOKED_FLESH);
        }
    }

    /**
     * Spawns four TNT entities around Goliath, randomly, when damaged.
     */
    @Unique
    private void onGiantDamage() {
        for (int i = 0; i < 4; i++) {
            TntEntity tnt = EntityType.TNT.create(this.getEntityWorld(), SpawnReason.TRIGGERED);
            tnt.setFuse(100);
            int x = i == 0 || i == 2 ? 5 : -5;
            int z = i == 0 || i == 1 ? 5 : -5;
            tnt.refreshPositionAndAngles(this.getX() + x, this.getY() + 25, this.getZ() + z, 0.0F, 0.0F);
            this.getEntityWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
            this.getEntityWorld().spawnEntity(tnt);
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
            TntEntity tnt = EntityType.TNT.create(this.getEntityWorld(), SpawnReason.TRIGGERED);
            if (tnt != null) {
                tnt.setFuse(data[3]);
                if (data[3] == 100) {
                    tnt.setInvulnerable(true);
                }
                tnt.refreshPositionAndAngles(
                        this.getX() + data[0],
                        this.getY() + data[1],
                        this.getZ() + data[2],
                        0.0F, 0.0F
                );
                this.getEntityWorld().spawnEntity(tnt);
            }
        }

        this.getEntityWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
    }
}