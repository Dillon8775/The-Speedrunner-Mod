package net.dillon.speedrunnermod.mixin.block;

import net.dillon.speedrunnermod.tag.ModBlockHardnessTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {
    @Shadow @Final
    private float destroySpeed;

    /**
     * All modified {@code block hardness values} for the speedrunner mod.
     */
    @Inject(method = "getDestroySpeed", at = @At("HEAD"), cancellable = true)
    private void applyFasterBlockBreaking(BlockGetter world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (common().general().fasterBlockBreaking) {
            int multiplier = common().general().blockBreakingMultiplier;
            cir.setReturnValue(this.destroySpeed / multiplier);

            // Speedrunner mod blocks do not get modified block hardness values. They are instead hard-coded.
            if (BuiltInRegistries.BLOCK.getKey(world.getBlockState(pos).getBlock()).getNamespace().equals("speedrunnermod")) {
                return;
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.INSTABREAK)) {
                cir.setReturnValue(0.0F);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_1)) {
                cir.setReturnValue(0.1F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_2)) {
                cir.setReturnValue(0.2F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_3)) {
                cir.setReturnValue(0.3F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_35)) {
                cir.setReturnValue(0.35F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_37)) {
                cir.setReturnValue(0.37F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_4)) {
                cir.setReturnValue(0.4F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_5)) {
                cir.setReturnValue(0.5F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_6)) {
                cir.setReturnValue(0.6F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_65)) {
                cir.setReturnValue(0.65F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_7)) {
                cir.setReturnValue(0.7F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_0_8)) {
                cir.setReturnValue(0.8F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_1_0)) {
                cir.setReturnValue(1.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_1_3)) {
                cir.setReturnValue(1.3F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_1_4)) {
                cir.setReturnValue(1.4F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_1_5)) {
                cir.setReturnValue(1.5F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_1_6)) {
                cir.setReturnValue(1.6F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_2_0)) {
                cir.setReturnValue(2.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_2_5)) {
                cir.setReturnValue(2.5F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_3_0)) {
                cir.setReturnValue(3.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_4_0)) {
                cir.setReturnValue(4.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_4_5)) {
                cir.setReturnValue(4.5F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_5_0)) {
                cir.setReturnValue(5.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_6_0)) {
                cir.setReturnValue(6.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_7_0)) {
                cir.setReturnValue(7.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_8_0)) {
                cir.setReturnValue(8.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_9_0)) {
                cir.setReturnValue(9.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_10)) {
                cir.setReturnValue(10.0F / multiplier);
            }

            if (world.getBlockState(pos).is(ModBlockHardnessTags.HARDNESS_25)) {
                cir.setReturnValue(25.0F / multiplier);
            }
        }
    }
}