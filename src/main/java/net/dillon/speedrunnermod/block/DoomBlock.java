package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Be careful what you wish for...
 */
public class DoomBlock {

    /**
     * Does... stuff.
     */
    private static void whenBroken(World world, BlockPos pos, PlayerEntity player) {
        boolean generatedItem = false;
        if (!player.getMainHandStack().isIn(ModItemTags.DOOM_STONE_SAFE_TOOLS)) {
            if (world.random.nextFloat() < 0.50F) {
                world.setBlockState(pos, Blocks.LAVA.getDefaultState());
                generatedItem = true;
            }

            if (world.random.nextFloat() < 0.40F) {
                for (int i = 0; i < world.random.nextInt(3) + 1; i++) {
                    ZombieEntity zombie = EntityType.ZOMBIE.create(world, SpawnReason.MOB_SUMMONED);
                    zombie.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsInTicks(30), 0, false, true, false));
                    zombie.refreshPositionAndAngles(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                    world.spawnEntity(zombie);
                }
                generatedItem = true;
            } else if (world.random.nextFloat() < 0.25F) {
                VindicatorEntity vindicator = EntityType.VINDICATOR.create(world, SpawnReason.MOB_SUMMONED);
                ItemStack axe = new ItemStack(Items.IRON_AXE);
                axe.setDamage(world.random.nextInt(100));
                vindicator.equipStack(EquipmentSlot.MAINHAND, axe);
                vindicator.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsInTicks(30), 0, false, true, false));
                vindicator.refreshPositionAndAngles(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                world.spawnEntity(vindicator);
                generatedItem = true;
            } else if (world.random.nextFloat() < 0.10F) {
                RavagerEntity ravager = EntityType.RAVAGER.create(world, SpawnReason.MOB_SUMMONED);
                ravager.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsInTicks(30), 0, false, true, false));
                ravager.refreshPositionAndAngles(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                world.spawnEntity(ravager);
                generatedItem = true;
            } else if (world.random.nextFloat() < 0.10F) {
                PiglinBruteEntity brute = EntityType.PIGLIN_BRUTE.create(world, SpawnReason.MOB_SUMMONED);
                ItemStack axe = new ItemStack(Items.GOLDEN_AXE);
                axe.setDamage(world.random.nextInt(24));
                brute.equipStack(EquipmentSlot.MAINHAND, axe);
                brute.setImmuneToZombification(true);
                brute.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsInTicks(30), 0, false, true, false));
                brute.refreshPositionAndAngles(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                world.spawnEntity(brute);
                generatedItem = true;
            } else if (world.random.nextFloat() < 0.05F) {
                GhastEntity ghast = EntityType.GHAST.create(world, SpawnReason.MOB_SUMMONED);
                ghast.setHealth(ghast.getMaxHealth() + 90.0F);
                ghast.refreshPositionAndAngles(pos.getX() + 1.0F, pos.getY() + 1.5F, pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                world.spawnEntity(ghast);
                generatedItem = true;
            }
        }

        if (world.random.nextFloat() < 0.10F) {
            ItemStack stack;
            if (world.random.nextFloat() < 0.10F) {
                stack = new ItemStack(Items.DIAMOND_SWORD);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.SHARPNESS), world.random.nextInt(3) + 3);
                if (world.random.nextFloat() < 0.40F) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.KNOCKBACK), world.random.nextInt(2) + 1);
                }
            } else if (world.random.nextFloat() < 0.10F) {
                stack = new ItemStack(Items.NETHERITE_CHESTPLATE);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.PROTECTION), world.random.nextInt(2) + 3);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.THORNS), world.random.nextInt(3) + 1);
            } else if (world.random.nextFloat() < 0.10F) {
                stack = new ItemStack(ModItems.SPEEDRUNNER_BOW);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.POWER), world.random.nextInt(3) + 4);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.FLAME), 1);
            } else if (world.random.nextInt() < 0.10F) {
                stack = new ItemStack(ModItems.SPEEDRUNNER_CROSSBOW);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.QUICK_CHARGE), 3);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.MULTISHOT), 1);
                stack.addEnchantment(ModUtil.enchantment(player, Enchantments.UNBREAKING), world.random.nextInt(2) + 2);
            } else if (world.random.nextFloat() < 0.10F) {
               stack = new ItemStack(Items.IRON_CHESTPLATE);
               stack.addEnchantment(ModUtil.enchantment(player, Enchantments.PROTECTION), world.random.nextInt(2) + 3);
               stack.addEnchantment(ModUtil.enchantment(player, Enchantments.UNBREAKING), 3);
               stack.setDamage(world.random.nextInt(50));
            } else if (world.random.nextFloat() < 0.10F) {
               stack = new ItemStack(Items.DIAMOND_SWORD);
               stack.addEnchantment(ModUtil.enchantment(player, Enchantments.SHARPNESS), world.random.nextInt(2) + 4);
               stack.addEnchantment(ModUtil.enchantment(player, Enchantments.UNBREAKING), 3);
               stack.addEnchantment(ModUtil.enchantment(player, Enchantments.FIRE_ASPECT), world.random.nextInt(2) + 1);
               if (world.random.nextFloat() < 0.40F) {
                   stack.addEnchantment(ModUtil.enchantment(player, Enchantments.KNOCKBACK), world.random.nextInt(4) + 2);
               }
            } else if (world.random.nextFloat() < 0.10F) {
                stack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
            } else if (world.random.nextFloat() < 0.10F) {
                stack = new ItemStack(Items.GOLDEN_APPLE, world.random.nextInt(3) + 1);
            } else if (world.random.nextInt() < 0.03F) {
                stack = new ItemStack(ModItems.KNOCKBACK_STICK);
            } else {
                stack = new ItemStack(ModItems.DRAGONS_PEARL);
            }

            ModUtil.spawnFloatingItemEntity(world, pos, stack, player, true);
            generatedItem = true;
        }

        if (generatedItem) {
            ModUtil.completeStepS2C(TutorialStep.BREAK_DOOM_BLOCK, player,
                    "speedrunnermod.tutorial_mode.kill_goliath",
                    "speedrunnermod.tutorial_mode.goliath_description");
        }
    }

    /**
     * Handles the fall damage when landing on a doom mode block.
     */
    private static void fallDamage(Entity entity, double fallDistance) {
        float fallDamage;
        if (!options().main.fallDamage.getCurrentValue()) {
            fallDamage = 0.0F;
        } else {
            fallDamage = isDoomMode() ? 1.15F : 1.0F;
            if (entity.isSneaking()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.handleFallDamage(fallDistance, fallDamage, entity.getDamageSources().fall());
    }

    /**
     * See {@link ModBlocks} for more.
     */
    protected static class Default extends Block {

        protected Default(Settings settings) {
            super(settings);
        }

        @Override
        public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.onBreak(world, pos, state, player);
        }
    }

    /**
     * See {@link ModBlocks} for more.
     */
    protected static class Pillar extends PillarBlock {

        protected Pillar(Settings settings) {
            super(settings);
        }

        @Override
        public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.onBreak(world, pos, state, player);
        }
    }

    /**
     * See {@link ModBlocks} for more.
     */
    protected static class Leaves extends TintedParticleLeavesBlock {

        protected Leaves(Settings settings) {
            super(0.01F, settings);
        }

        @Override
        public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.onBreak(world, pos, state, player);
        }
    }
}