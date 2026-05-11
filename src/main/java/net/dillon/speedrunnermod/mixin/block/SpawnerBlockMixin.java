package net.dillon.speedrunnermod.mixin.block;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnerBlock.class)
public abstract class SpawnerBlockMixin extends BaseEntityBlock {

    public SpawnerBlockMixin(Properties settings) {
        super(settings);
    }

    /**
     * Makes spawner blocks drop more experience when mined.
     */
    @Inject(method = "spawnAfterBreak", at = @At("TAIL"))
    private void increaseExperienceDroppedFromSpawnerBlockWithOrWithoutFortune(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
        int f = player != null ? EnchantmentHelper.getItemEnchantmentLevel(ModUtil.enchantment(player, Enchantments.FORTUNE), stack) * 172 : 1;
        int i = 512 + world.getRandom().nextInt(524) + world.getRandom().nextInt(128) + f;
        this.popExperience(world, pos, i);
    }
}