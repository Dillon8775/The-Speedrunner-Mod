package net.dillon.speedrunnermod.mixin.main.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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

@Mixin(value = EnderDragon.class, priority = 999)
public class EnderDragonMixin extends Mob {
    @Shadow @Final
    public EnderDragonPart head;

    public EnderDragonMixin(EntityType<? extends EnderDragon> entityType, Level world) {
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
    @ModifyArg(method = "checkCrystals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;setHealth(F)V"))
    private float changeTickCrystalHealAmount(float value) {
        return this.getHealth() + ModUtil.getEnderDragonEndCrystalHealingValue();
    }

    /**
     * Makes the ender dragon do less damage.
     */
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;mobAttack(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;"), cancellable = true)
    private void changeEnderDragonDamageValue(ServerLevel world, List<Entity> entities, CallbackInfo ci, @Local Entity entity) {
        boolean bl = entity instanceof Player playerEntity && playerEntity.hasEffect(ModStatusEffects.DRAGONS_AURA);
        DamageSource damageSource = this.damageSources().mobAttack(this);
        entity.hurtServer(world, damageSource, ModUtil.getEnderDragonDamageValue() / (bl ? 2 : 1));
        EnchantmentHelper.doPostAttackEffects(world, entity, damageSource);
        ci.cancel();
    }

    /**
     * Makes end crystals do more damage to the ender dragon.
     */
    @ModifyArg(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 3)
    private float changeEnderDragonCrystalDestroyedDamage(float amount) {
        return ModUtil.getEnderDragonDestroyedEndCrystalDamageValue();
    }

    /**
     * Cancels out {@code ender dragon damage} when on doom mode and nearby entities are alive.
     */
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/phases/DragonPhaseInstance;onHurt(Lnet/minecraft/world/damagesource/DamageSource;F)F"), cancellable = true)
    private void cancelOutDamage(ServerLevel world, EnderDragonPart part, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        EnderDragon dragon = (EnderDragon)(Object)this;
        if (isDoomMode() && this.getHealth() <= 1.0F && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && isGiantOrWitherAlive(dragon)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Increases the ender dragon stay time when it is perched.
     */
    @ModifyConstant(method = "hurt", constant = @Constant(floatValue = 0.25F))
    private float increaseDragonStayTime(float constant) {
        return ModUtil.getEnderDragonSittingTime();
    }

    /**
     * Makes beds immune on doom mode.
     */
    @Redirect(method = "checkWalls", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isIn(Lnet/minecraft/tags/TagKey;)Z", ordinal = 1))
    private boolean makeBedsImmuneOnDoomMode(BlockState blockState, TagKey<?> tagKey) {
        return isDoomMode() ? blockState.is(BlockTags.DRAGON_IMMUNE) || blockState.is(BlockTags.BEDS) : blockState.is(BlockTags.DRAGON_IMMUNE);
    }

    /**
     * Makes all nearby hostile entities die upon the dragon's death, excluding {@code Enderman.}
     */
    @Inject(method = "tickDeath", at = @At("TAIL"))
    public void killNearbyHostiles(CallbackInfo ci) {
        if (options().advanced.dragonKillsNearbyHostileEntities.getCurrentValue() && this.level() instanceof ServerLevel serverWorld) {
            EnderDragon dragon = (EnderDragon) (Object) this;
            Level world = this.level();

            List<Monster> hostiles = getEntitiesWithinRange(world, Monster.class, dragon, options().advanced.dragonMassKillRadius.getCurrentValue());

            for (Monster hostile : hostiles) {
                if (!(hostile instanceof EnderMan)) {
                    hostile.kill(serverWorld);
                }
            }
        }
    }

    /**
     * Stops the dragon from dying if there is a nearby wither and/or giant, only on doom mode.
     */
    @Override
    public void die(DamageSource source) {
        EnderDragon dragon = (EnderDragon)(Object)this;

        if (this.isDragonInvincible(dragon)) {
            this.setHealth(5.0F);
            this.playSound(SoundEvents.SHIELD_BLOCK.value(), 5.0F, 0.65F);
            this.playSound(SoundEvents.ENDER_DRAGON_GROWL, 5.0F, 0.65F);
        }

        for (Player player : dragon.level().players()) {
            if (player instanceof ServerPlayer serverPlayer) {
                if (this.isDragonInvincible(dragon)) {
                    if (isGiantAlive(dragon) && isWitherAlive(dragon)) {
                        serverPlayer.sendSystemMessage(Component.translatable("speedrunnermod.doom_mode.giant_and_wither_still_alive"), false);
                    } else if (isGiantAlive(dragon)) {
                        serverPlayer.sendSystemMessage(Component.translatable("speedrunnermod.doom_mode.giant_still_alive"), false);
                    } else if (isWitherAlive(dragon)) {
                        serverPlayer.sendSystemMessage(Component.translatable("speedrunnermod.doom_mode.wither_still_alive"), false);
                    }
                }
            }
        }
    }

    /**
     * Grants the "Free the End" advancement to all nearby players.
     */
    @Inject(method = "tickDeath", at = @At("TAIL"))
    private void grantAdvancementToAll(CallbackInfo ci) {
        EnderDragon dragon = (EnderDragon)(Object)this;

        List<Player> players = getEntitiesWithinRange(dragon.level(), Player.class, dragon, List.of(300, 300, 300));
        for (Player player : players) {
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(serverPlayer, dragon, dragon.damageSources().playerAttack(serverPlayer));
            }
        }
    }

    /**
     * @return true if the dragon can be {@code invincible.}
     */
    @Unique
    private boolean isDragonInvincible(EnderDragon dragon) {
        return isDoomMode() && options().advanced.dragonImmunityFromGoliathAndWither.getCurrentValue() && isGiantOrWitherAlive(dragon);
    }
}