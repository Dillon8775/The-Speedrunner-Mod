package net.dillon.speedrunnermod.mixin.block;

import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedStoneOreBlock.class)
public class RedStoneOreBlockMixin extends Block {

    public RedStoneOreBlockMixin(Properties settings) {
        super(settings);
    }

    /**
     * Makes redstone ores drop more experience when mined, triple that amount if in the {@code Speedrunner's Wasteland} biome.
     * <p>Done separately because the {@link RedStoneOreBlock} is an entirely separate class from {@link net.minecraft.world.level.block.DropExperienceBlock}</p>
     */
    @Inject(method = "spawnAfterBreak", at = @At("TAIL"))
    private void increasedExperienceDroppedFromRedstoneOreWithFortune(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
        if (player != null && EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.SILK_TOUCH), stack) == 0) {
            int f;
            int i;
            if (world.getBiome(pos).is(ModBiomes.SPEEDRUNNERS_WASTELAND)) {
                if (state.is(Blocks.REDSTONE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 52;
                    i = 4 + world.getRandom().nextInt(11) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 4 + world.getRandom().nextInt(11) + f;
                    this.popExperience(world, pos, i);
                }
            } else {
                if (state.is(Blocks.REDSTONE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 48;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 72;
                    this.popExperience(world, pos, f);
                }
            }
        }
    }
}