package net.dillon.speedrunnermod.mixin.main.entity.player;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
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
    @Shadow @Final
    private ServerStatHandler statHandler;
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);
    @Shadow
    public abstract ServerWorld getServerWorld();
    @Shadow
    public abstract void sendMessageToClient(Text message, boolean overlay);

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
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {
            options().tutorialMode.completeStep(TutorialStep.ENTER_WORLD, this,
                    "speedrunnermod.tutorial_mode.greeting",
                    "speedrunnermod.tutorial_mode.craft_speedrunner_pickaxe");
        }
    }

    /**
     * Gives the player the {@code unbreakable elytra,} one stack of {@code flight duration 3 firework rockets}, and an {@code InfiniPearl} if enabled.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_TIME)) == 0) {

            ItemStack item;
            if (options().main.iCarusMode) {
                item = ModUtil.unbreakableItem(Items.ELYTRA);
                ItemStack fireworks = ModUtil.flightDurationComponentItem(64);

                this.equipment.put(EquipmentSlot.CHEST, item);
                this.getInventory().getMainStacks().set(options().advanced.iCarusFireworksInventorySlot - 1, fireworks);
            }

            if (options().main.infiniPearlMode) {
                ItemStack infiniPearl = ModUtil.unbreakableItem(ModItems.INFINI_PEARL);
                int slot = options().advanced.infiniPearlInventorySlot - 1;

                if (options().main.iCarusMode && options().advanced.iCarusFireworksInventorySlot == options().advanced.infiniPearlInventorySlot) {
                    slot += 1;
                }

                if (options().main.iCarusMode && options().advanced.iCarusFireworksInventorySlot == options().advanced.infiniPearlInventorySlot && options().advanced.infiniPearlInventorySlot >= 36) {
                    slot -= 2;
                }

                this.getInventory().getMainStacks().set(slot, infiniPearl);
            }
        }
    }
}