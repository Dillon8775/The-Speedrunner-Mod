package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(ExperienceDroppingBlock.class)
public class ExperienceDroppingBlockMixin extends Block {

    public ExperienceDroppingBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * Removes the silk touch enchantment when right-clicking on an ore block.
     */
    @Override
    public ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient && itemStack.isSuitableFor(state) && EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.SILK_TOUCH), itemStack) > 0 && options().main.rightClickToRemoveSilkTouch) {
            ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.apply(itemStack, builder -> builder.remove(enchantmentRegistryEntry -> enchantmentRegistryEntry.matchesKey(Enchantments.SILK_TOUCH)));
            EnchantmentHelper.set(itemStack, itemEnchantmentsComponent);
            world.playSound(null, pos, SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (player instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity)player).networkHandler.sendPacket(new PlaySoundS2CPacket(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.BLOCKS, pos.getX(), pos.getY(), pos.getZ(), 1.0F, 1.0F, world.getRandom().nextLong()));
            }
            player.sendMessage(Text.translatable("speedrunnermod.removed_silk_touch").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            player.setStackInHand(hand, itemStack);
            return ActionResult.SUCCESS;
        } else {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }
    }

    /**
     * Makes ores drop more experience when mined, triple that amount if in the {@code Speedrunner's Wasteland} biome.
     */
    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience, CallbackInfo ci) {
        PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
        if (player != null && EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.SILK_TOUCH), stack) == 0) {
            int f;
            int i;
            if (world.getBiome(pos) == ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY) {
                if (state.isOf(Blocks.GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 10 + world.random.nextInt(20) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 86;
                    i = 10 + world.random.nextInt(20) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 52;
                    i = 5 + world.random.nextInt(10) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 5 + world.random.nextInt(10) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 20;
                    i = 4 + world.random.nextInt(8) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 30;
                    i = 4 + world.random.nextInt(8) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 38;
                    i = 5 + world.random.nextInt(8) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 8 + world.random.nextInt(17) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 8 + world.random.nextInt(17) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 70;
                    i = 17 + world.random.nextInt(28) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 101;
                    i = 17 + world.random.nextInt(28) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 13 + world.random.nextInt(23) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 76;
                    i = 13 + world.random.nextInt(23) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 42;
                    i = 11 + world.random.nextInt(17) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 70;
                    i = 20 + world.random.nextInt(25) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 100;
                    i = 20 + world.random.nextInt(25) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 15 + world.random.nextInt(20) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 64;
                    i = 17 + world.random.nextInt(25) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 94;
                    i = 17 + world.random.nextInt(25) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 58;
                    i = 15 + world.random.nextInt(20) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 1516;
                    i = 1516 + world.random.nextInt(1024) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 1864;
                    i = 2048 + world.random.nextInt(1512) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 32;
                    i = 256 + world.random.nextInt(256) + f;
                    this.dropExperience(world, pos, i);
                }
            } else {
                if (state.isOf(Blocks.GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 2 + world.random.nextInt(5) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 81;
                    i = 2 + world.random.nextInt(5) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 48;
                    i = 1 + world.random.nextInt(2) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 72;
                    i = 1 + world.random.nextInt(2) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(Blocks.COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 18;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 27;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 36;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 54;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 72;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 66;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 99;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 54;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 72;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 36;
                    this.dropExperience(world, pos, f);
                } else if (state.isOf(ModBlocks.SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 63;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 95;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_SPEEDRUNNER_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 1 + world.random.nextInt(3) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 60;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 90;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_IGNEOUS_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 54;
                    i = 2 + world.random.nextInt(6) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 1024;
                    i = 1024 + world.random.nextInt(824) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.DEEPSLATE_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 1512;
                    i = 1512 + world.random.nextInt(1024) + f;
                    this.dropExperience(world, pos, i);
                } else if (state.isOf(ModBlocks.NETHER_EXPERIENCE_ORE)) {
                    f = EnchantmentHelper.getLevel(ItemUtil.entityEnchantment(player, Enchantments.FORTUNE), stack) * 512;
                    i = 1024 + world.random.nextInt(512) + f;
                    this.dropExperience(world, pos, i);
                }
            }
        }
    }
}