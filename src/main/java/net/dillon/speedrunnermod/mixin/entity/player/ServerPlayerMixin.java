package net.dillon.speedrunnermod.mixin.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.event.SpeedrunnersTotemEvent;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}, and sending the players death coordinates.
 */
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    @Shadow
    public abstract void sendSystemMessage(Component message);
    @Shadow
    public abstract ServerLevel level();
    @Unique
    private int effectsTimer = Arithmetics.sas(10);
    @Unique
    private boolean effectsAdded = false;
    @Unique
    private boolean canAddEffects = true;

    public ServerPlayerMixin(Level world, GameProfile profile) {
        super(world, profile);
    }

    /**
     * Allows the use of totems in the void, and levitation with {@code Dragon's Aura} effect.
     */
    @Override
    public void checkBelowWorld() {
        if (this.getY() < (double)(this.level().getMinY() - 64)) {
            ItemStack speedrunnersTotem = SpeedrunnersTotemEvent.getSpeedrunnersTotem((ServerPlayer) (Object)this);
            if (!speedrunnersTotem.isEmpty()) {
                int y = this.level().getHeight(Heightmap.Types.MOTION_BLOCKING, 0, 0);
                BlockPos pos = new BlockPos(0, y - 1, 0);
                if (this.level().getBlockState(pos).is(Blocks.WATER)) {
                    this.level().setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                } else if (this.level().getBlockState(pos).is(Blocks.LAVA)) {
                    this.level().setBlockAndUpdate(pos, Blocks.BASALT.defaultBlockState());
                }
                boolean isAir = this.level().getBlockState(pos.above()).isAir() && this.level().getBlockState(pos.above(1)).isAir();
                if (!isAir) {
                    for (int i = 1; i < 3; i++) {
                        this.level().setBlock(pos.above(i), Blocks.AIR.defaultBlockState(), 3);
                    }
                }

                this.randomTeleport(0.5, y, 0.5, true, state -> true);
                this.level().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 10.0F, 1.0F);
                SpeedrunnersTotemEvent.EVENT.invoker().invoke(this, speedrunnersTotem, this.damageSources().generic());
                speedrunnersTotem.shrink(1);
                ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultInstance());
            } else if (this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
                if (!this.effectsAdded && this.canAddEffects) {
                    this.addEffect(new MobEffectInstance(MobEffects.GLOWING, Arithmetics.sas(10)));
                    this.addEffect(new MobEffectInstance(MobEffects.LEVITATION, Arithmetics.sas(10), 19));
                    this.level().playSound(null, this.blockPosition(), SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, SoundSource.PLAYERS, 5.0F, 1.0F);
                    this.level().playSound(null, this.blockPosition(), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.PLAYERS, 5.0F, 1.0F);
                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger((ServerPlayer)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultInstance());
                    this.sendSystemMessage(Component.translatable("effect.speedrunnermod.dragons_aura.used_void"));
                    TaskScheduler.schedule(Arithmetics.sas(10), () -> {
                        this.removeEffect(ModMobEffects.DRAGONS_AURA);
                        this.sendSystemMessage(Component.translatable("effect.speedrunnermod.dragons_aura.expires"));
                        this.addEffect(new MobEffectInstance(ModMobEffects.DRAGONS_AURA, Arithmetics.sas(20)));
                    });
                    this.effectsAdded = true;
                } else if (this.effectsTimer <= 0) {
                    super.checkBelowWorld();
                }
            } else {
                super.checkBelowWorld();
            }
        }

        if (this.effectsAdded && this.effectsTimer > 0) {
            this.effectsTimer--;
        }
    }

    /**
     * Writes the {@code buff timer} and {@code effects added} expression to the player's data.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeDragonsAuraData(ValueOutput view, CallbackInfo ci) {
        view.putInt("DragonsAuraBuffTimer", this.effectsTimer);
        view.putBoolean("DragonsAuraBuffEffects", this.effectsAdded);
        view.putBoolean("DragonsAuraBuffCanAddEffects", this.canAddEffects);
    }

    /**
     * Reads the {@code buff timer} and {@code effects added} expression from the player's data.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readDragonsAuraData(ValueInput view, CallbackInfo ci) {
        this.effectsTimer = view.getIntOr("DragonsAuraBuffTimer", -1);
        this.effectsAdded = view.getBooleanOr("DragonsAuraBuffEffects", false);
        this.canAddEffects = view.getBooleanOr("DragonsAuraBuffCanAddEffects", false);
    }

    /**
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "die", at = @At("TAIL"))
    private void sendDeathCords(DamageSource source, CallbackInfo ci) {
        if (common().general().showDeathCords && this.level().getGameRules().get(GameRules.SHOW_DEATH_MESSAGES)) {
            ModHelper.latestDeathCords = new double[]{this.getX(), this.getY(), this.getZ()};
            this.sendSystemMessage(ModHelper.deathCords(ModHelper.latestDeathCords[0], ModHelper.latestDeathCords[1], ModHelper.latestDeathCords[2]));
        }
        this.canAddEffects = false;
        this.effectsAdded = false;
        this.effectsTimer = Arithmetics.sas(10);
    }
}