package net.dillon.speedrunnermod.mixin.main.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.util.ModUtil.*;

@Mixin(value = EnderDragonEntity.class, priority = 999)
public class EnderDragonEntityMixin extends MobEntity {
    @Shadow @Final
    public EnderDragonPart head;

    public EnderDragonEntityMixin(EntityType<? extends EnderDragonEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the ender dragon's maximum health.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeEnderDragonMaxHealth(CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, ModUtil.getEnderDragonMaxHealth());
        ModUtil.modifyFollowRange(this, ModUtil.getEnderDragonFollowRange());
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
    @Inject(method = "damageLivingEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSources;mobAttack(Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/entity/damage/DamageSource;"), cancellable = true)
    private void changeEnderDragonDamageValue(ServerWorld world, List<Entity> entities, CallbackInfo ci, @Local Entity entity) {
        boolean bl = entity instanceof PlayerEntity playerEntity && playerEntity.hasStatusEffect(ModStatusEffects.DRAGONS_AURA);
        DamageSource damageSource = this.getDamageSources().mobAttack(this);
        entity.damage(world, damageSource, ModUtil.getEnderDragonDamageValue() / (bl ? 2 : 1));
        EnchantmentHelper.onTargetDamaged(world, entity, damageSource);
        ci.cancel();
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
        EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;
        if (isDoomMode() && this.getHealth() <= 1.0F && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && isGiantOrWitherAlive(dragon)) {
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
        if (options().advanced.dragonKillsNearbyHostileEntities.getCurrentValue() && this.getEntityWorld() instanceof ServerWorld serverWorld) {
            EnderDragonEntity dragon = (EnderDragonEntity) (Object) this;
            World world = this.getEntityWorld();

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
     * Stops the dragon from dying if there is a nearby wither and/or giant, only on doom mode.
     */
    @Override
    public void onDeath(DamageSource source) {
        EnderDragonEntity dragon = (EnderDragonEntity)(Object)this;

        if (this.isDragonInvincible(dragon)) {
            this.setHealth(1.0F);
            this.playSound(SoundEvents.ITEM_SHIELD_BLOCK.value(), 5.0F, 0.65F);
            this.playSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 5.0F, 0.65F);
        }

        for (PlayerEntity player : dragon.getEntityWorld().getPlayers()) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                if (this.isDragonInvincible(dragon)) {
                    if (isGiantAlive(dragon) && isWitherAlive(dragon)) {
                        serverPlayer.sendMessageToClient(Text.translatable("speedrunnermod.doom_mode.giant_and_wither_still_alive"), false);
                    } else if (isGiantAlive(dragon)) {
                        serverPlayer.sendMessageToClient(Text.translatable("speedrunnermod.doom_mode.giant_still_alive"), false);
                    } else if (isWitherAlive(dragon)) {
                        serverPlayer.sendMessageToClient(Text.translatable("speedrunnermod.doom_mode.wither_still_alive"), false);
                    } else {
                        Criteria.PLAYER_KILLED_ENTITY.trigger(serverPlayer, dragon, dragon.getDamageSources().playerAttack(serverPlayer));
                    }
                }
            }
        }
    }

    /**
     * @return true if the dragon can be {@code invincible.}
     */
    @Unique
    private boolean isDragonInvincible(EnderDragonEntity dragon) {
        return isDoomMode() && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && isGiantOrWitherAlive(dragon);
    }
}