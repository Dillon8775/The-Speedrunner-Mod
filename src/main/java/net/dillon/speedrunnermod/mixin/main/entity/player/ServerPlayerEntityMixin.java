package net.dillon.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.rule.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}, and sending the players death coordinates.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);
    @Shadow
    public abstract ServerWorld getEntityWorld();
    @Unique
    private int effectsTimer = ModUtil.secondsAsTicks(10);
    @Unique
    private boolean effectsAdded = false;
    @Unique
    private boolean canAddEffects = true;

    public ServerPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    /**
     * Allows the use of totems in the void, and levitation with {@code Dragon's Aura} effect.
     */
    @Override
    public void attemptTickInVoid() {
        ItemStack speedrunnersTotem = new ItemStack(ModItems.SPEEDRUNNERS_TOTEM);
        if (this.getY() < (double)(this.getEntityWorld().getBottomY() - 64)) {
            if (this.getInventory().contains(speedrunnersTotem)) {
                int y = this.getEntityWorld().getTopY(Heightmap.Type.MOTION_BLOCKING, 0, 0);
                BlockPos pos = new BlockPos(0, y - 1, 0);
                if (this.getEntityWorld().getBlockState(pos).isOf(Blocks.WATER)) {
                    this.getEntityWorld().setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                } else if (this.getEntityWorld().getBlockState(pos).isOf(Blocks.LAVA)) {
                    this.getEntityWorld().setBlockState(pos, Blocks.BASALT.getDefaultState());
                }
                boolean isAir = this.getEntityWorld().getBlockState(pos.up()).isAir() && this.getEntityWorld().getBlockState(pos.up(1)).isAir();
                if (!isAir) {
                    for (int i = 1; i < 3; i++) {
                        this.getEntityWorld().setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                    }
                }

                this.teleport(0.5, y, 0.5, true);
                this.getEntityWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 10.0F, 1.0F);
                this.damage(this.getEntityWorld(), this.getDamageSources().generic(), Integer.MAX_VALUE);
                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());
            } else if (this.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
                if (!this.effectsAdded && this.canAddEffects) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, ModUtil.secondsAsTicks(10)));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, ModUtil.secondsAsTicks(10), 19));
                    this.getEntityWorld().playSound(null, this.getBlockPos(), SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.PLAYERS, 5.0F, 1.0F);
                    this.getEntityWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.PLAYERS, 5.0F, 1.0F);
                    ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());
                    this.effectsAdded = true;
                } else if (this.effectsTimer <= 0) {
                    super.attemptTickInVoid();
                }
            } else {
                super.attemptTickInVoid();
            }
        }

        if (this.effectsAdded && this.effectsTimer > 0) {
            this.effectsTimer--;
        }
    }

    /**
     * Writes the {@code buff timer} and {@code effects added} expression to the player's data.
     */
    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeDragonsAuraData(WriteView view, CallbackInfo ci) {
        view.putInt("DragonsAuraBuffTimer", this.effectsTimer);
        view.putBoolean("DragonsAuraBuffEffects", this.effectsAdded);
        view.putBoolean("DragonsAuraBuffCanAddEffects", this.canAddEffects);
    }

    /**
     * Reads the {@code buff timer} and {@code effects added} expression from the player's data.
     */
    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readDragonsAuraData(ReadView view, CallbackInfo ci) {
        this.effectsTimer = view.getInt("DragonsAuraBuffTimer", -1);
        this.effectsAdded = view.getBoolean("DragonsAuraBuffEffects", false);
        this.canAddEffects = view.getBoolean("DragonsAuraBuffCanAddEffects", false);
    }

    /**
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sendDeathCords(DamageSource source, CallbackInfo ci) {
        if (options().main.showDeathCords.getCurrentValue() && this.getEntityWorld().getGameRules().getValue(GameRules.SHOW_DEATH_MESSAGES)) {
            ModUtil.latestDeathCords = new double[]{this.getX(), this.getY(), this.getZ()};
            this.sendMessage(ModUtil.deathCords(ModUtil.latestDeathCords[0], ModUtil.latestDeathCords[1], ModUtil.latestDeathCords[2]), false);
        }
        this.canAddEffects = false;
        this.effectsAdded = false;
        this.effectsTimer = ModUtil.secondsAsTicks(10);
    }
}