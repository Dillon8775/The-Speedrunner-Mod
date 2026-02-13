package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.entity.ModPotions;
import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

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
        if (!player.getAbilities().creativeMode && !player.getMainHandStack().isIn(ModItemTags.DOOM_STONE_SAFE_TOOLS) && !player.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
            if (world.random.nextFloat() < 0.50F) {
                world.setBlockState(pos, Blocks.LAVA.getDefaultState());
            }

            List<EntityType<?>> possibleEntities = List.of(
                    EntityType.ZOMBIE,
                    EntityType.VINDICATOR,
                    EntityType.RAVAGER,
                    EntityType.PIGLIN_BRUTE,
                    EntityType.GHAST
            );
            if (world.random.nextFloat() < 0.35F) {
                MobEntity entity = EntityType.ZOMBIE.create(world, SpawnReason.MOB_SUMMONED);
                EntityType<?> entityType = possibleEntities.get(ModUtil.randomIntInclusive(0, possibleEntities.size() - 1));
                if (entityType == EntityType.VINDICATOR) {
                    entity = EntityType.VINDICATOR.create(world, SpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.IRON_AXE);
                    axe.setDamage(world.random.nextInt(100));
                    entity.equipStack(EquipmentSlot.MAINHAND, axe);
                } else if (entityType == EntityType.RAVAGER) {
                    entity = EntityType.RAVAGER.create(world, SpawnReason.MOB_SUMMONED);
                } else if (entityType == EntityType.PIGLIN_BRUTE) {
                    entity = EntityType.PIGLIN_BRUTE.create(world, SpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.GOLDEN_AXE);
                    axe.setDamage(world.random.nextInt(24));
                    entity.equipStack(EquipmentSlot.MAINHAND, axe);
                    if (entity instanceof PiglinBruteEntity brute) {
                        brute.setImmuneToZombification(true);
                    }
                } else if (entityType == EntityType.GHAST) {
                    entity = EntityType.GHAST.create(world, SpawnReason.MOB_SUMMONED);
                }
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.secondsAsTicks(30), 0, false, true, false));
                entity.refreshPositionAndAngles(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.random.nextFloat() * 360.0F, 0.0F);
                for (int i = 0; i < (entityType == EntityType.ZOMBIE ? 3 : 1); i++) {
                    world.spawnEntity(entity);
                }
            }
        } else if (!player.getAbilities().creativeMode) {
            if (world.random.nextFloat() < 0.10F) {
                List<Item> possibleItems = List.of(
                        Items.DIAMOND_SWORD,
                        Items.NETHERITE_CHESTPLATE,
                        Items.BOW,
                        ModItems.SPEEDRUNNER_BOW,
                        ModItems.SPEEDRUNNER_CROSSBOW,
                        Items.IRON_CHESTPLATE,
                        Items.ENCHANTED_GOLDEN_APPLE,
                        Items.GOLDEN_APPLE,
                        ModItems.RAID_ERADICATOR,
                        ModItems.SPEEDRUNNERS_TOTEM,
                        Items.FIRE_CHARGE,
                        ModItems.DRAGONS_FIREBALL,
                        Items.ELYTRA,
                        ModItems.KNOCKBACK_STICK,
                        Items.POTION,
                        ModItems.DRAGONS_PEARL
                );

                Item item = possibleItems.get(ModUtil.randomIntInclusive(0, possibleItems.size() - 1));
                ItemStack stack = new ItemStack(item);
                if (item == Items.DIAMOND_SWORD) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.SHARPNESS), ModUtil.randomIntInclusive(3, 5));
                    if (world.random.nextFloat() < 0.40F) {
                        stack.addEnchantment(ModUtil.enchantment(player, Enchantments.KNOCKBACK), ModUtil.randomIntInclusive(1, 2));
                    }
                } else if (item == Items.NETHERITE_CHESTPLATE) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.PROTECTION), ModUtil.randomIntInclusive(3, 4));
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.THORNS), ModUtil.randomIntInclusive(2, 3));
                } else if (item == Items.BOW || item == ModItems.SPEEDRUNNER_BOW) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.POWER), ModUtil.randomIntInclusive(3, 5));
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.FLAME), 1);
                } else if (item == ModItems.SPEEDRUNNER_CROSSBOW) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.QUICK_CHARGE), ModUtil.randomIntInclusive(2, 3));
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.UNBREAKING), ModUtil.randomIntInclusive(1, 3));
                    if (world.getRandom().nextFloat() < 0.65F) {
                        stack.addEnchantment(ModUtil.enchantment(player, Enchantments.MULTISHOT), 1);
                    }
                } else if (item == Items.IRON_CHESTPLATE) {
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.PROTECTION), ModUtil.randomIntInclusive(3, 4));
                    stack.addEnchantment(ModUtil.enchantment(player, Enchantments.UNBREAKING), ModUtil.randomIntInclusive(1, 3));
                    stack.setDamage(world.random.nextInt(50));
                } else if (item == Items.GOLDEN_APPLE) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(1, 3));
                } else if (item == Items.FIRE_CHARGE) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(2, 5));
                } else if (item == ModItems.DRAGONS_FIREBALL) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(1, 3));
                } else if (item == Items.ELYTRA) {
                    stack = ModUtil.ofUnbreakable(item);
                } else if (item == Items.POTION) {
                    stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(ModPotions.DRAGONS_AURA));
                }

                ModUtil.spawnFloatingItemEntity(world, pos, stack, player, true);
            }
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