package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.packet.client.UpdateLastCompletedTutorialStepTranslationsS2CPacket;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.util.ModUtil.sendWithPrefix;

@Mixin(value = EnderDragonEntity.class, priority = 999)
public abstract class EnderDragonEntityMixin extends MobEntity {
    @Shadow
    private float damageDuringSitting;
    @Shadow
    protected abstract void parentDamage(ServerWorld world, DamageSource source, float amount);
    @Shadow @Final
    public EnderDragonPart head;
    @Shadow @Final
    private PhaseManager phaseManager;

    public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the ender dragon's maximum health.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeEnderDragonMaxHealth(CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, ModUtil.getEnderDragonMaxHealth());
    }

    /**
     * Makes the ender dragon heal slower from end crystals.
     */
    @ModifyArg(method = "tickWithEndCrystals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;setHealth(F)V"))
    private float changeTickCrystalHealAmount(float value) {
        return this.getHealth() + ModUtil.getEnderDragonEndCrystalHealingValue();
    }

    /**
     * Makes the ender dragon do less damage.
     */
    @ModifyArg(method = "damageLivingEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 2)
    private float changeEnderDragonDamageValue(float amount) {
        return ModUtil.getEnderDragonDamageValue();
    }

    /**
     * Makes end crystals do more damage to the ender dragon.
     */
    @ModifyArg(method = "crystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;damagePart(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/boss/dragon/EnderDragonPart;Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 3)
    private float changeEnderDragonCrystalDestroyedDamage(float amount) {
        return ModUtil.getEnderDragonDestroyedEndCrystalDamageValue();
    }

    /**
     * Cancels out {@code ender dragon damage} when on doom mode and nearby entities are alive.
     */
    @Inject(method = "damagePart", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/dragon/phase/Phase;modifyDamageTaken(Lnet/minecraft/entity/damage/DamageSource;F)F"), cancellable = true)
    private void cancelOutDamage(ServerWorld world, EnderDragonPart part, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && this.getHealth() <= 1.0F && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && this.isGiantOrWitherAlive()) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Increases the ender dragon stay time when it is perched.
     */
    @ModifyConstant(method = "damagePart", constant = @Constant(floatValue = 0.25F))
    private float increaseDragonStayTime(float constant) {
        return ModUtil.getEnderDragonSittingTime();
    }

    /**
     * Makes beds immune on doom mode.
     */
    @Redirect(method = "destroyBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z", ordinal = 1))
    private boolean makeBedsImmuneOnDoomMode(BlockState blockState, TagKey<?> tagKey) {
        return isDoomMode() ? blockState.isIn(BlockTags.DRAGON_IMMUNE) || blockState.isIn(BlockTags.BEDS) : blockState.isIn(BlockTags.DRAGON_IMMUNE);
    }

    /**
     * Makes all nearby hostile entities die upon the dragon's death, excluding {@code Enderman.}
     */
    @Inject(method = "updatePostDeath", at = @At("TAIL"))
    public void killNearbyHostiles(CallbackInfo ci) {
        if (options().advanced.dragonKillsNearbyHostileEntities.getCurrentValue() && this.getWorld() instanceof ServerWorld serverWorld) {
            EnderDragonEntity dragon = (EnderDragonEntity) (Object) this;
            World world = this.getWorld();

            List<HostileEntity> hostiles = world.getEntitiesByClass(HostileEntity.class,
                    dragon.getBoundingBox().expand(options().advanced.dragonMassKillRadius.getCurrentValue().getFirst(), options().advanced.dragonMassKillRadius.getCurrentValue().get(1), options().advanced.dragonMassKillRadius.getCurrentValue().get(2)), entity -> true);

            for (HostileEntity hostile : hostiles) {
                if (!(hostile instanceof EndermanEntity) && !hostile.hasCustomName()) {
                    hostile.kill(serverWorld);
                }
            }
        }
    }

    /**
     * Checks if a wither and/or giant are alive every tick, for tutorial mode on doom mode.
     */
    @Override
    public void tick() {
        super.tick();
        EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;
        PlayerEntity player = dragon.getWorld().getClosestPlayer(dragon, 300.0D);
        if (!this.isGiantOrWitherAlive()) {
            ModUtil.completeStepS2C(TutorialStep.KILL_WITHER, player,
                    "speedrunnermod.tutorial_mode.wither_died",
                    "speedrunnermod.tutorial_mode.kill_dragon");
        }
    }

    /**
     * Stops the dragon from dying if there is a nearby wither and/or giant, only on doom mode.
     */
    @Override
    public void onDeath(DamageSource source) {
        EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;
        LivingEntity livingEntity = dragon.getAttacker();
        if (livingEntity instanceof ServerPlayerEntity serverPlayer) {
            boolean bl = ServerStorage.isTutorialModeEnabledForPlayer(serverPlayer.getUuid()) && !ServerStorage.hasCompletedStep(serverPlayer, TutorialStep.USE_DRAGONS_PEARL) && !isBalancedMode();
            if ((isDoomMode() && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && this.isGiantOrWitherAlive()) || bl) {
                this.setHealth(1.0F);
                if (bl && !this.isGiantOrWitherAlive()) {
                    List<String> translations = new ArrayList<>();
                    String s = "speedrunnermod.tutorial_mode.use_dragons_pearl";
                    translations.add(s);
                    sendWithPrefix(s, serverPlayer);
                    ServerPlayNetworking.send(serverPlayer, new UpdateLastCompletedTutorialStepTranslationsS2CPacket(translations));
                }
            } else {
                PlayerEntity player = dragon.getWorld().getClosestPlayer((EnderDragonEntity)(Object)this, 300.0D);
                ModUtil.completeStepS2C(TutorialStep.KILL_DRAGON, player,
                        isDoomMode() ? "speedrunnermod.tutorial_mode.killed_dragon.doom" :
                                "speedrunnermod.tutorial_mode.killed_dragon");
                super.onDeath(source);
            }
        }
    }

    /**
     * Checks if a giant and/or a wither are alive.
     * <p>If either are present, the ender dragon {@code cannot die.}</p>
     */
    @Unique
    private boolean isGiantOrWitherAlive() {
        EnderDragonEntity dragon = (EnderDragonEntity) (Object) this;
        List<GiantEntity> giants = this.getWorld().getEntitiesByClass(GiantEntity.class,
                dragon.getBoundingBox().expand(options().advanced.dragonImmunityDetectionRadiusForGoliath.getCurrentValue().getFirst(), options().advanced.dragonImmunityDetectionRadiusForGoliath.getCurrentValue().get(1), options().advanced.dragonImmunityDetectionRadiusForGoliath.getCurrentValue().get(2)), entity -> true);
        List<WitherEntity> withers = this.getWorld().getEntitiesByClass(WitherEntity.class,
                dragon.getBoundingBox().expand(options().advanced.dragonImmunityDetectionRadiusForWither.getCurrentValue().getFirst(), options().advanced.dragonImmunityDetectionRadiusForWither.getCurrentValue().get(1), options().advanced.dragonImmunityDetectionRadiusForWither.getCurrentValue().get(2)), entity -> true);

        for (GiantEntity giant : giants) {
            if (giant.isAlive()) {
                return true;
            }
        }

        for (WitherEntity wither : withers) {
            if (wither.isAlive()) {
                return true;
            }
        }

        return false;
    }
}