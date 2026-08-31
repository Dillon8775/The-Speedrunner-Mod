package net.dillon.speedrunnermod.mixin.entity.dragon;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.material.ModToolMaterials;
import net.dillon.speedrunnermod.item.tool.DragonsSwordItem;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.EnderDragonPart;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

import static net.dillon.speedrunnermod.helper.ModHelper.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(value = EnderDragon.class, priority = 999)
public abstract class EnderDragonMixin extends Mob {
    @Shadow @Final
    public EnderDragonPart head;
    @Shadow
    public abstract void kill(ServerLevel level);
    @Shadow
    protected abstract void handleKillingBlow();
    @Shadow
    protected abstract void reallyHurt(ServerLevel level, DamageSource source, float damage);

    public EnderDragonMixin(EntityType<? extends EnderDragon> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Makes beds immune on doom mode.
     */
    @Redirect(method = "checkWalls", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 1))
    private boolean makeBedsImmuneOnDoomMode(BlockState blockState, TagKey<?> tagKey) {
        return isDoomMode() ? blockState.is(BlockTags.DRAGON_IMMUNE) || blockState.is(BlockTags.BEDS) : blockState.is(BlockTags.DRAGON_IMMUNE);
    }

    /**
     * Modifies the ender dragon's maximum health.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeEnderDragonMaxHealth(CallbackInfo ci) {
        ModAttributeHelper.modifyMaxHealth(this, ModConstants.getEnderDragonMaxHealth());
        ModAttributeHelper.modifyFollowRange(this, ModConstants.getEnderDragonFollowRange());
    }

    /**
     * Makes the ender dragon heal slower from end crystals.
     */
    @ModifyArg(method = "checkCrystals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;setHealth(F)V"))
    private float changeTickCrystalHealAmount(float value) {
        return this.getHealth() + ModConstants.getEnderDragonEndCrystalHealingValue();
    }

    /**
     * Makes the ender dragon do less damage.
     */
    @Inject(method = "hurt(Lnet/minecraft/server/level/ServerLevel;Ljava/util/List;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;mobAttack(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/damagesource/DamageSource;"), cancellable = true)
    private void changeEnderDragonDamageValue(ServerLevel world, List<Entity> entities, CallbackInfo ci, @Local Entity entity) {
        boolean bl = entity instanceof Player playerEntity && playerEntity.hasEffect(ModMobEffects.DRAGONS_AURA);
        DamageSource damageSource = this.damageSources().mobAttack(this);
        entity.hurtServer(world, damageSource, ModConstants.getEnderDragonDamageValue() / (bl ? 2 : 1));
        EnchantmentHelper.doPostAttackEffects(world, entity, damageSource);
        ci.cancel();
    }

    /**
     * Makes end crystals do more damage to the ender dragon.
     */
    @ModifyArg(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 3)
    private float changeEnderDragonCrystalDestroyedDamage(float amount) {
        return ModConstants.getEnderDragonDestroyedEndCrystalDamageValue();
    }

    /**
     * Handles the damage from the {@link DragonsSwordItem}.
     */
    @Inject(method = "hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/phases/DragonPhaseInstance;onHurt(Lnet/minecraft/world/damagesource/DamageSource;F)F"), cancellable = true)
    private void handleDragonsSwordDamage(ServerLevel serverLevel, EnderDragonPart part, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        EnderDragon dragon = (EnderDragon)(Object)this;

        Entity entity = source.getDirectEntity();
        if (entity instanceof LivingEntity living) {
            float enderDragonDamage = (float)living.getAttributeValue(ModAttributes.DRAGONBANE);
            if (!(enderDragonDamage > 1.0F)) {
                return;
            }

            if (!isDoomMode() || !isGiantOrWitherAlive(dragon)) {
                this.reallyHurt(serverLevel, source, 1000.0F);
                living.addEffect(new MobEffectInstance(ModMobEffects.DRAGONS_AURA, Arithmetics.mas(90)));
                if (living instanceof ServerPlayer player) {
                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(player, new ItemStack(ModItems.DRAGONS_SWORD));
                }
                for (int i = 0; i < 100; i++) {
                    living.getWeaponItem().hurtAndBreak(ModToolMaterials.DRAGONS_SWORD.durability(), living, EquipmentSlot.MAINHAND);
                }
                cir.setReturnValue(true);
            } else {
                this.protectDragon(dragon, false, true);
            }
        }
    }

    /**
     * Cancels out {@code ender dragon damage} when on doom mode and nearby entities are alive.
     */
    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void cancelOutDamage(ServerLevel serverLevel, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!isDoomMode()) {
            return;
        }

        EnderDragon dragon = (EnderDragon)(Object)this;
        if (this.getHealth() <= 1.0F && common().accessibility().dragonImmunityFromGoliathAndWither && isGiantOrWitherAlive(dragon)) {
            cir.setReturnValue(false);
        } else if (isZombieMinionAlive(dragon)) {
            this.protectDragon(dragon, false, false);
            cir.setReturnValue(false);
        }
    }

    /**
     * Stops the dragon from dying if there is a nearby wither and/or giant, only on doom mode.
     */
    @Override
    public void die(DamageSource source) {
        EnderDragon dragon = (EnderDragon)(Object)this;

        if (this.isDragonInvincible(dragon)) {
            this.protectDragon(dragon, true, true);
        }
    }

    /**
     * Increases the ender dragon stay time when it is perched.
     */
    @ModifyConstant(method = "hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/enderdragon/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z", constant = @Constant(floatValue = 0.25F))
    private float increaseDragonStayTime(float constant) {
        return ModConstants.getEnderDragonSittingTime();
    }

    /**
     * Makes all nearby hostile entities die upon the dragon's death, excluding {@code Enderman.}
     */
    @Inject(method = "tickDeath", at = @At("TAIL"))
    public void killNearbyHostiles(CallbackInfo ci) {
        if (common().accessibility().dragonKillsNearbyHostileEntities && this.level() instanceof ServerLevel serverWorld) {
            EnderDragon dragon = (EnderDragon) (Object) this;
            Level world = this.level();

            final int r = 300;
            List<Integer> radius = List.of(r, r, r);
            List<Monster> hostiles = getEntitiesWithinRange(world, Monster.class, dragon, radius);

            for (Monster hostile : hostiles) {
                if (!hostile.is(ModEntityTypeTags.BLACKLISTED_ENDER_DRAGON_KILL_MOBS)) {
                    hostile.kill(serverWorld);
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

        final int r = 300;
        List<Integer> radius = List.of(r, r, r);
        List<Player> players = getEntitiesWithinRange(dragon.level(), Player.class, dragon, radius);
        for (Player player : players) {
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(serverPlayer, dragon, dragon.damageSources().playerAttack(serverPlayer));
            }
        }
    }

    /**
     * Protects the ender dragon from damage.
     */
    @Unique
    private void protectDragon(EnderDragon dragon, boolean setHealth, boolean messages) {
        if (setHealth) {
            this.setHealth(5.0F);
        }
        this.playSound(SoundEvents.SHIELD_BLOCK.value(), 5.0F, 0.65F);
        this.playSound(SoundEvents.ENDER_DRAGON_GROWL, 5.0F, 0.65F);

        if (messages) {
            for (Player player : dragon.level().players()) {
                if (player instanceof ServerPlayer serverPlayer) {
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
     * @return true if the dragon can be {@code invincible.}
     */
    @Unique
    private boolean isDragonInvincible(EnderDragon dragon) {
        return isDoomMode() && common().accessibility().dragonImmunityFromGoliathAndWither && isGiantOrWitherAlive(dragon);
    }
}