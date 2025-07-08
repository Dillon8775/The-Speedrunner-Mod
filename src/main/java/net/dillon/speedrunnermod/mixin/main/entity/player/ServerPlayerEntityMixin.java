package net.dillon.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
    public abstract ServerWorld getServerWorld();

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    /**
     * Allows the use of totems in the void.
     */
    @Override
    public void attemptTickInVoid() {
        ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);
        ItemStack speedrunnersTotem = new ItemStack(ModItems.SPEEDRUNNERS_TOTEM);
        ItemStack enderMatter = new ItemStack(ModItems.ENDER_MATTER);
        if (this.getY() < (double)(this.getWorld().getBottomY() - 64) &&
                (this.getInventory().contains(speedrunnersTotem) ||
                        this.getMainHandStack().isOf(totem.getItem()) ||
                        this.getOffHandStack().isOf(totem.getItem())))
        {
            int y = this.getWorld().getTopY(Heightmap.Type.MOTION_BLOCKING, 0, 0);
            BlockPos pos = new BlockPos(0, y - 1, 0);
            if (this.getWorld().getBlockState(pos).isOf(Blocks.WATER)) {
                this.getWorld().setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
            } else if (this.getWorld().getBlockState(pos).isOf(Blocks.LAVA)) {
                this.getWorld().setBlockState(pos, Blocks.BASALT.getDefaultState());
            }
            boolean isAir = this.getWorld().getBlockState(pos.up()).isAir() && this.getWorld().getBlockState(pos.up(1)).isAir();
            if (!isAir) {
                for (int i = 1; i < 3; i++) {
                    this.getWorld().setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                }
            }

            this.damage((ServerWorld)this.getWorld(), this.getDamageSources().generic(), Integer.MAX_VALUE);
            this.teleport(0.5, y, 0.5, true);
            this.getWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 10.0F, 1.0F);
            ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());

            if (!ServerSyncedClientOptions.hasCompletedStep((ServerPlayerEntity)(Object)this, TutorialStep.FREE_FALL_INTO_VOID)) {
                if (!this.getInventory().contains(totem)) {
                    ModUtil.spawnFloatingItemEntity(this.getWorld(), totem, this);
                }
                if (!this.getInventory().contains(enderMatter)) {
                    ModUtil.spawnFloatingItemEntity(this.getWorld(), enderMatter, this);
                }
                ModUtil.completeStepS2C(TutorialStep.FREE_FALL_INTO_VOID, (ServerPlayerEntity)(Object)this,
                        "speedrunnermod.tutorial_mode.craft_speedrunners_totem");
            }
        } else {
            super.attemptTickInVoid();
        }
    }

    /**
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sendCords(DamageSource source, CallbackInfo ci) {
        if (options().main.showDeathCords.getCurrentValue() && this.getServerWorld().getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES)) {
            this.sendMessage(ModUtil.deathCords(this.getX(), this.getY(), this.getZ()), false);
        }
    }

    /**
     * Sends the tutorial mode chat message.
     */
    @Inject(method = "onSpawn", at = @At("TAIL"))
    private void sendTutorialMessage(CallbackInfo ci) {
        ModUtil.completeStepS2C(TutorialStep.ENTER_WORLD, this,
                "speedrunnermod.tutorial_mode.greeting",
                "speedrunnermod.tutorial_mode.craft_speedrunner_pickaxe");
    }
}