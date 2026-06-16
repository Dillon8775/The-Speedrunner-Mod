package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeFeatures.class)
public interface TreeFeaturesInvoker {
    @Invoker("createStraightBlobTree")
    static TreeConfiguration.TreeConfigurationBuilder invokeCreateStraightBlobTree(final Block oakLog, final Block oakLeaves, final int baseHeight, final int heightRandA, final int heightRandB, final int blobRadius, final BlockStateProvider belowTrunkProvider) {
        throw new AssertionError();
    }
}