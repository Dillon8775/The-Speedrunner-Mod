package net.dillon.speedrunnermod.mixin.entity.goliath;

import net.dillon.speedrunnermod.entity.goliath.GoliathAttackGoal;
import net.dillon.speedrunnermod.entity.goliath.Minion;
import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.dillon.speedrunnermod.util.RandomChance;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
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
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

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
public class Goliath extends Monster implements net.dillon.speedrunnermod.entity.goliath.Goliath {
    @Unique
    protected WaterBoundPathNavigation waterNavigation;
    @Unique
    protected GroundPathNavigation landNavigation;
    @Unique
    boolean targetingUnderwater;
    @Unique
    private ServerBossEvent bossBar;
    @Unique
    private static final EntityDataAccessor<Boolean> SPAWNED_ZOMBIES = SynchedEntityData.defineId(Giant.class, EntityDataSerializers.BOOLEAN);

    public Goliath(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void setSpawnedZombies(boolean value) {
        this.entityData.set(SPAWNED_ZOMBIES, value);
    }

    @Override
    public boolean hasSpawnedZombies() {
        return this.entityData.get(SPAWNED_ZOMBIES);
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(SPAWNED_ZOMBIES, false);
    }

    @Override
    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("SpawnedZombies", this.hasSpawnedZombies());
    }

    @Override
    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setSpawnedZombies(input.getBooleanOr("SpawnedZombies", false));
    }

    /**
     * Drops more experience upon death when using looting.
     */
    @Override
    public int getBaseExperienceReward(ServerLevel world) {
        int looting = this.getLastHurtByMob() != null ? EnchantmentHelper.getEnchantmentLevel(ModHelper.enchantment((Giant)(Object)this, Enchantments.LOOTING), this.getLastHurtByMob()) * 150 : 0;
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
        ModAttributeHelper.modifyFollowRange(this, 35.0D);
        ModAttributeHelper.modifyMaxHealth(this, 400.0D);
        ModAttributeHelper.modifyMovementSpeed(this, 0.35D);
        ModAttributeHelper.modifyAttackDamage(this, 10.0D);
        ModAttributeHelper.modifyAttackKnockback(this, 1.5D);
        ModAttributeHelper.modifyKnockbackResistance(this, 0.7F);
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
            net.dillon.speedrunnermod.entity.goliath.Goliath.addAngryParticles(this);
        }

        this.bossBar.setProgress(this.getHealth() / this.getMaxHealth());

        if (this.getTarget() instanceof Minion minion && minion.isGoliathMinion()) {
            this.setTarget(null);
        }
    }

    /**
     * Teleports Goliath to the middle of the end island if it falls into the void.
     */
    @Override
    public void checkBelowWorld() {
        net.dillon.speedrunnermod.entity.goliath.Goliath.safeFromVoid(this);
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
    public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
        Entity entity = source.getDirectEntity();

        if (entity != null && entity.is(ModEntityTypeTags.GOLIATH_IMMUNE_MOBS)) {
            return false;
        }

        if (entity instanceof Player player && this.isHolding(heldItem -> heldItem.is(ItemTags.SPEARS))) {
            this.playSound(SoundEvents.SHIELD_BLOCK.value(), 5.0F, 1.0F);
            player.hurtServer(serverLevel, player.damageSources().mobAttack(this), player.getHealth() / RandomChance.floatInclusive(1.25F, 1.95F));
        }

        float maxHealth = this.getMaxHealth();
        boolean half = this.getHealth() <= maxHealth / 2;
        boolean low = this.getHealth() <= this.getMaxHealth() / 3;
        if (half) {
            if (entity instanceof Projectile projectile && projectile.getOwner() != null) {
                if (projectile.getOwner() != null) {
                    if (projectile.getOwner() instanceof Player) {
                        this.absorbDamage(maxHealth);
                    }

                    projectile.getOwner().hurtServer(serverLevel, projectile.getOwner().damageSources().generic(), RandomChance.floatInclusive(1.0F, 3.0F));
                }
                return false;
            }
        }

        if (low && entity instanceof Player) {
            this.heal(RandomChance.floatInclusive(1.35F, 3.45F));
        }

        if ((this.random.nextFloat() < 0.15F || low) && !source.is(DamageTypeTags.IS_FIRE)) {
            this.onGoliathDamage(low);
        }

        if (this.random.nextFloat() < 0.05F && this.getHealth() <= 250) {
            this.onGoliathDamageDropFood(serverLevel);
        }

        return super.hurtServer(serverLevel, source, amount);
    }

    /**
     * Handles {@code attacking} for Goliath.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        this.level().broadcastEntityEvent(this, (byte)4);
        return net.dillon.speedrunnermod.entity.goliath.Goliath.tryAttack(world, this, (LivingEntity)target);
    }

    /**
     * Handles {@code knockback} for Goliath.
     */
    @Override
    protected void blockedByItem(final LivingEntity defender, final DamageSource source, final float damage) {
        net.dillon.speedrunnermod.entity.goliath.Goliath.knockback(this, defender);
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
     * Detects when the player gets {@code out of range} of Goliath, and then {@code removes} the bossbar from that players screen.
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
     * Absorbs damage from a specific source.
     */
    @Unique
    private void absorbDamage(float maxHealth) {
        this.playSound(SoundEvents.SHIELD_BLOCK.value(), 5.0F, 1.0F);
        this.playSound(SoundEvents.GENERIC_EAT.value(), 5.0F, 1.0F);

        float missingHealth = maxHealth - this.getHealth();
        this.heal((float)Math.sqrt(missingHealth));
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
     * Spawns four TNT entities around Goliath, randomly, when damaged, and summons zombies as protection.
     */
    @Unique
    private void onGoliathDamage(boolean spawnZombies) {
        Level level = this.level();

        if (spawnZombies && !this.hasSpawnedZombies()) {
            Vec3 forward = this.getLookAngle().normalize();
            Vec3 right = forward.cross(new Vec3(0, 1, 0)).normalize();

            double forwardDistance = 8.0;
            double sideDistance = 4.0;

            Vec3 frontPos = this.position().add(forward.scale(forwardDistance));
            Vec3 rightPos = this.position().add(right.scale(sideDistance));
            Vec3 leftPos = this.position().subtract(right.scale(sideDistance));

            Vec3[] positions = {
                    frontPos,
                    rightPos,
                    leftPos
            };

            for (Vec3 pos : positions) {
                Zombie zombie = EntityTypes.ZOMBIE.create(level, EntitySpawnReason.TRIGGERED);

                ModAttributeHelper.modifyMaxHealth(zombie, 25.0F);
                zombie.setHealth(25.0F);
                ItemStack stack = Minion.zombiesFireball(level.getRandom().nextFloat() < 0.15F ? ModItems.DRAGON_FIREBALL : Items.FIRE_CHARGE);
                zombie.setItemSlot(EquipmentSlot.MAINHAND, stack);
                ((Minion)zombie).setGoliathMinion(true);
                ((Minion)zombie).setFireballChargeTime(ModConstants.DEFAULT_MINION_FIREBALL_CHARGE_SPEED);

                zombie.setGlowingTag(true);
                zombie.snapTo(pos.x, this.getY(), pos.z, this.getYRot(), 0.0F);

                level.addFreshEntity(zombie);
                level.playSound(null, zombie.getOnPos(), SoundEvents.ZOMBIE_AMBIENT, SoundSource.HOSTILE, 5.0F, 1.0F);
                level.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.AMBIENT, 5.0F, 1.0F);
            }

            List<ServerPlayer> players = ModHelper.getEntitiesWithinRange(this.level(), ServerPlayer.class, this, options().advanced.goliathAndZombieEntityDetectionRadius.getCurrentValue());
            for (ServerPlayer player : players) {
                player.sendSystemMessage(Component.translatable("speedrunnermod.doom_mode.minions.warning")
                        .withStyle(ChatFormatting.YELLOW));
            }
            this.setSpawnedZombies(true);
        }

        for (int i = 0; i < 4; i++) {
            PrimedTnt tnt = EntityTypes.TNT.create(level, EntitySpawnReason.TRIGGERED);
            tnt.setFuse(100);
            int x = i == 0 || i == 2 ? 5 : -5;
            int z = i == 0 || i == 1 ? 5 : -5;
            tnt.snapTo(this.getX() + x, this.getY() + 25, this.getZ() + z, 0.0F, 0.0F);
            level.playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.TNT_PRIMED, SoundSource.AMBIENT, 5.0F, 1.0F);
            level.addFreshEntity(tnt);
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
            PrimedTnt tnt = EntityTypes.TNT.create(this.level(), EntitySpawnReason.TRIGGERED);
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