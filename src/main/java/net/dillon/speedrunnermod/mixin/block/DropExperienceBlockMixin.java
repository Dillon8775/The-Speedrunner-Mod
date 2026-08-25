package net.dillon.speedrunnermod.mixin.block;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(DropExperienceBlock.class)
public class DropExperienceBlockMixin extends Block {

    public DropExperienceBlockMixin(Properties settings) {
        super(settings);
    }

    /**
     * Removes the silk touch enchantment when right-clicking on an ore block.
     */
    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!world.isClientSide() && itemStack.isCorrectToolForDrops(state) && EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.SILK_TOUCH), itemStack) > 0 && options().general.rightClickToRemoveSilkTouch.getCurrentValue()) {
            ItemEnchantments itemEnchantmentsComponent = EnchantmentHelper.updateEnchantments(itemStack, builder -> builder.removeIf(enchantmentRegistryEntry -> enchantmentRegistryEntry.is(Enchantments.SILK_TOUCH)));
            EnchantmentHelper.setEnchantments(itemStack, itemEnchantmentsComponent);
            world.playSound(null, pos, SoundEvents.WARDEN_HEARTBEAT, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayer) {
                ((ServerPlayer)player).connection.send(new ClientboundSoundPacket(SoundEvents.RESPAWN_ANCHOR_DEPLETE, SoundSource.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F, world.getRandom().nextLong()));
            }
            ModHelper.sendMessageWithActionbarPref(player, Component.translatable("speedrunnermod.removed_silk_touch"), ChatFormatting.RED, ChatFormatting.WHITE);
            player.setItemInHand(hand, itemStack);
            player.swing(hand, SwingAnimation.DEFAULT, true);
            return InteractionResult.SUCCESS;
        } else {
            return super.useItemOn(stack, state, world, pos, player, hand, hit);
        }
    }

    /**
     * Makes ores drop more experience when mined, triple that amount if in the {@code Speedrunner's Wasteland} biome.
     */
    @Inject(method = "spawnAfterBreak", at = @At("TAIL"))
    public void increaseExperienceDroppedFromOresWithFortune(BlockState state, ServerLevel world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        Player player = world.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
        if (player != null && EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.SILK_TOUCH), stack) == 0) {
            int f;
            int i;
            if (world.getBiome(pos).is(ModBiomes.SPEEDRUNNERS_WASTELAND)) {
                if (state.is(Blocks.GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 10 + world.getRandom().nextInt(20) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 86;
                    i = 10 + world.getRandom().nextInt(20) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.IRON_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 52;
                    i = 5 + world.getRandom().nextInt(10) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_IRON_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 5 + world.getRandom().nextInt(10) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.COAL_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 20;
                    i = 4 + world.getRandom().nextInt(8) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_COAL_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 30;
                    i = 4 + world.getRandom().nextInt(8) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.NETHER_GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 38;
                    i = 5 + world.getRandom().nextInt(8) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.LAPIS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 8 + world.getRandom().nextInt(17) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_LAPIS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 8 + world.getRandom().nextInt(17) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DIAMOND_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 70;
                    i = 17 + world.getRandom().nextInt(28) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 101;
                    i = 17 + world.getRandom().nextInt(28) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.EMERALD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 13 + world.getRandom().nextInt(23) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_EMERALD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 13 + world.getRandom().nextInt(23) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.NETHER_QUARTZ_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 42;
                    i = 11 + world.getRandom().nextInt(17) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 70;
                    i = 20 + world.getRandom().nextInt(25) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 100;
                    i = 20 + world.getRandom().nextInt(25) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 15 + world.getRandom().nextInt(20) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 64;
                    i = 17 + world.getRandom().nextInt(25) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 94;
                    i = 17 + world.getRandom().nextInt(25) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 15 + world.getRandom().nextInt(20) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 1516;
                    i = 1516 + world.getRandom().nextInt(1024) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 1864;
                    i = 2048 + world.getRandom().nextInt(1512) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 32;
                    i = 256 + world.getRandom().nextInt(256) + f;
                    this.popExperience(world, pos, i);
                }
            } else {
                if (state.is(Blocks.GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 2 + world.getRandom().nextInt(5) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 81;
                    i = 2 + world.getRandom().nextInt(5) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.IRON_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 48;
                    i = 1 + world.getRandom().nextInt(2) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.DEEPSLATE_IRON_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 72;
                    i = 1 + world.getRandom().nextInt(2) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(Blocks.COAL_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 18;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DEEPSLATE_COAL_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 27;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.NETHER_GOLD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 36;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.LAPIS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 54;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DEEPSLATE_LAPIS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 72;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DIAMOND_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 66;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 99;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.EMERALD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 54;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.DEEPSLATE_EMERALD_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 72;
                    this.popExperience(world, pos, f);
                } else if (state.is(Blocks.NETHER_QUARTZ_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 36;
                    this.popExperience(world, pos, f);
                } else if (state.is(ModBlocks.SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 63;
                    i = 2 + world.getRandom().nextInt(6) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 95;
                    i = 2 + world.getRandom().nextInt(6) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 1 + world.getRandom().nextInt(3) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 60;
                    i = 2 + world.getRandom().nextInt(6) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 90;
                    i = 2 + world.getRandom().nextInt(6) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 2 + world.getRandom().nextInt(6) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 1024;
                    i = 1024 + world.getRandom().nextInt(824) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.DEEPSLATE_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 1512;
                    i = 1512 + world.getRandom().nextInt(1024) + f;
                    this.popExperience(world, pos, i);
                } else if (state.is(ModBlocks.NETHER_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, Enchantments.FORTUNE), stack) * 512;
                    i = 1024 + world.getRandom().nextInt(512) + f;
                    this.popExperience(world, pos, i);
                }
            }
        }
    }
}