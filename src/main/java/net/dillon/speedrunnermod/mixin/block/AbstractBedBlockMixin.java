package net.dillon.speedrunnermod.mixin.block;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractBedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;
import static net.minecraft.world.level.block.BedBlock.OCCUPIED;

@Mixin(AbstractBedBlock.class)
public class AbstractBedBlockMixin {

    /**
     * Summons evoker fangs when using a bed on doom mode.
     */
    @Inject(method = "useWithoutItem", at = @At(value = "HEAD"), cancellable = true)
    private void summonEvokerFangs(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        BedRule bedRule = level.environmentAttributes().getValue(EnvironmentAttributes.BED_RULE, pos);
        if (!isDoomMode() || player.getAbilities().instabuild || bedRule.destroyOnUse() || state.getValue(OCCUPIED) || level.isClientSide() || (!(player instanceof ServerPlayer serverPlayer)) || (!(level instanceof ServerLevel serverLevel))) {
            return;
        }

        ServerAdvancementManager advancementManager = serverPlayer.level().getServer().getAdvancements();
        AdvancementHolder freeTheEnd = advancementManager.get(
                Identifier.fromNamespaceAndPath("minecraft", "end/kill_dragon")
        );
        if (freeTheEnd == null) {
            return;
        }

        if (!serverPlayer.getAdvancements().getOrStartProgress(freeTheEnd).isDone()) {
            Vec3 look = player.getLookAngle().normalize();

            double px = player.getX();
            double py = player.getY();
            double pz = player.getZ();

            double maxY = py + 1.0;
            float baseAngle = (float) Math.atan2(look.z, look.x);

            if (player.distanceToSqr(px + look.x * 3, py, pz + look.z * 3) < 9.0) {
                for (int i = 0; i < 5; i++) {
                    float angle = baseAngle + i * 0.4F * (float) Math.PI;

                    this.spawnFang(serverLevel,
                            px + Math.cos(angle) * 1.5,
                            pz + Math.sin(angle) * 1.5,
                            py, maxY, angle, 0
                    );
                }

                for (int i = 0; i < 8; i++) {
                    float angle = baseAngle
                            + i * ((float) Math.PI * 2F / 8F)
                            + (float) (Math.PI * 2.0 / 5.0);

                    this.spawnFang(serverLevel,
                            px + Math.cos(angle) * 2.5,
                            pz + Math.sin(angle) * 2.5,
                            py, maxY, angle, 3
                    );
                }
            } else {
                for (int i = 0; i < 16; i++) {
                    double reach = 1.25 * (i + 1);
                    this.spawnFang(serverLevel,
                            px + look.x * reach,
                            pz + look.z * reach,
                            py, maxY,
                            baseAngle,
                            i
                    );
                }
            }

            this.spawnFang(serverLevel,
                    px,
                    pz,
                    py,
                    maxY,
                    baseAngle,
                    0
            );

            serverLevel.playSound(null, player.getOnPos(), SoundEvents.EVOKER_AMBIENT, SoundSource.HOSTILE);
            serverPlayer.setRespawnPosition(new ServerPlayer.RespawnConfig(LevelData.RespawnData.of(level.dimension(), pos, serverPlayer.getYRot(), serverPlayer.getXRot()), false), true);
            player.sendOverlayMessage(Component.translatable("block.minecraft.bed.doom_mode")
                    .withStyle(ChatFormatting.LIGHT_PURPLE)
            );
            cir.setReturnValue(InteractionResult.SUCCESS_SERVER);
        }
    }

    /**
     * Spawns an evoker fang.
     */
    @Unique
    private void spawnFang(ServerLevel level, double x, double z, double minY, double maxY, float angle, int delayTicks) {
        delayTicks = Mth.clamp(delayTicks, 0, 16);

        BlockPos pos = BlockPos.containing(x, maxY, z);
        boolean found = false;
        double topOffset = 0.0;

        while (pos.getY() >= Mth.floor(minY) - 1) {
            BlockPos below = pos.below();
            BlockState state = level.getBlockState(below);
            if (state.isFaceSturdy(level, below, Direction.UP)) {
                if (!level.isEmptyBlock(pos)) {
                    VoxelShape shape = level.getBlockState(pos)
                            .getCollisionShape(level, pos);
                    if (!shape.isEmpty()) {
                        topOffset = shape.max(Direction.Axis.Y);
                    }
                }

                found = true;
                break;
            }

            pos = pos.below();
        }

        if (!found) {
            return;
        }

        EvokerFangs fang = new EvokerFangs(
                level,
                x,
                pos.getY() + topOffset,
                z,
                angle,
                delayTicks,
                null
        );

        level.addFreshEntity(fang);
    }
}