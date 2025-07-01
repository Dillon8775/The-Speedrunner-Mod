package net.dillon.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
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
     * Sends the players coordinates to chat upon death.
     */
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void sendCords(DamageSource source, CallbackInfo ci) {
        if (options().main.showDeathCords && this.getServerWorld().getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES)) {
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