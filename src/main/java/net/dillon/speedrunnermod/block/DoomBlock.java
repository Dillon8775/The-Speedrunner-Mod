package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

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
    private static void whenBroken(Level world, BlockPos pos, Player player) {
        if (!player.getAbilities().instabuild && !player.getMainHandItem().is(ModItemTags.DOOM_STONE_SAFE_TOOLS) && !player.hasEffect(ModStatusEffects.DRAGONS_AURA)) {
            if (world.getRandom().nextFloat() < 0.50F) {
                world.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
            }

            List<EntityType<?>> possibleEntities = List.of(
                    EntityType.ZOMBIE,
                    EntityType.VINDICATOR,
                    EntityType.RAVAGER,
                    EntityType.PIGLIN_BRUTE,
                    EntityType.GHAST
            );
            if (world.getRandom().nextFloat() < 0.35F) {
                Mob entity = EntityType.ZOMBIE.create(world, EntitySpawnReason.MOB_SUMMONED);
                EntityType<?> entityType = possibleEntities.get(ModUtil.randomIntInclusive(0, possibleEntities.size() - 1));
                if (entityType == EntityType.VINDICATOR) {
                    entity = EntityType.VINDICATOR.create(world, EntitySpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.IRON_AXE);
                    axe.setDamageValue(world.getRandom().nextInt(100));
                    entity.setItemSlot(EquipmentSlot.MAINHAND, axe);
                } else if (entityType == EntityType.RAVAGER) {
                    entity = EntityType.RAVAGER.create(world, EntitySpawnReason.MOB_SUMMONED);
                } else if (entityType == EntityType.PIGLIN_BRUTE) {
                    entity = EntityType.PIGLIN_BRUTE.create(world, EntitySpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.GOLDEN_AXE);
                    axe.setDamageValue(world.getRandom().nextInt(24));
                    entity.setItemSlot(EquipmentSlot.MAINHAND, axe);
                    if (entity instanceof PiglinBrute brute) {
                        brute.setImmuneToZombification(true);
                    }
                } else if (entityType == EntityType.GHAST) {
                    entity = EntityType.GHAST.create(world, EntitySpawnReason.MOB_SUMMONED);
                }
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ModUtil.secondsAsTicks(30), 0, false, true, false));
                entity.snapTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, world.getRandom().nextFloat() * 360.0F, 0.0F);
                for (int i = 0; i < (entityType == EntityType.ZOMBIE ? 3 : 1); i++) {
                    world.addFreshEntity(entity);
                }
            }
        } else if (!player.getAbilities().instabuild) {
            if (world.getRandom().nextFloat() < 0.10F) {
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
                        ModItems.DRAGONS_PEARL,
                        Items.MACE
                );

                Item item = possibleItems.get(ModUtil.randomIntInclusive(0, possibleItems.size() - 1));
                ItemStack stack = new ItemStack(item);
                if (item == Items.DIAMOND_SWORD) {
                    stack.enchant(ModUtil.enchantment(player, Enchantments.SHARPNESS), ModUtil.randomIntInclusive(3, 5));
                    if (world.getRandom().nextFloat() < 0.40F) {
                        stack.enchant(ModUtil.enchantment(player, Enchantments.KNOCKBACK), ModUtil.randomIntInclusive(1, 2));
                    }
                } else if (item == Items.NETHERITE_CHESTPLATE) {
                    stack.enchant(ModUtil.enchantment(player, Enchantments.PROTECTION), ModUtil.randomIntInclusive(3, 4));
                    stack.enchant(ModUtil.enchantment(player, Enchantments.THORNS), ModUtil.randomIntInclusive(2, 3));
                } else if (item == Items.BOW || item == ModItems.SPEEDRUNNER_BOW) {
                    stack.enchant(ModUtil.enchantment(player, Enchantments.POWER), ModUtil.randomIntInclusive(3, 5));
                    stack.enchant(ModUtil.enchantment(player, Enchantments.FLAME), 1);
                } else if (item == ModItems.SPEEDRUNNER_CROSSBOW) {
                    stack.enchant(ModUtil.enchantment(player, Enchantments.QUICK_CHARGE), ModUtil.randomIntInclusive(2, 3));
                    stack.enchant(ModUtil.enchantment(player, Enchantments.UNBREAKING), ModUtil.randomIntInclusive(1, 3));
                    if (world.getRandom().nextFloat() < 0.65F) {
                        stack.enchant(ModUtil.enchantment(player, Enchantments.MULTISHOT), 1);
                    }
                } else if (item == Items.IRON_CHESTPLATE) {
                    stack.enchant(ModUtil.enchantment(player, Enchantments.PROTECTION), ModUtil.randomIntInclusive(3, 4));
                    stack.enchant(ModUtil.enchantment(player, Enchantments.UNBREAKING), ModUtil.randomIntInclusive(1, 3));
                    stack.setDamageValue(world.getRandom().nextInt(50));
                } else if (item == Items.GOLDEN_APPLE) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(1, 3));
                } else if (item == Items.FIRE_CHARGE) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(2, 5));
                } else if (item == ModItems.DRAGONS_FIREBALL) {
                    stack = new ItemStack(item, ModUtil.randomIntInclusive(1, 3));
                } else if (item == Items.ELYTRA) {
                    stack = ModUtil.ofUnbreakable(item);
                } else if (item == Items.POTION) {
                    stack.set(DataComponents.POTION_CONTENTS, new PotionContents(ModPotions.DRAGONS_AURA));
                } else if (item == Items.MACE) {
                    if (world.getRandom().nextFloat() < 0.35F) {
                        stack.enchant(ModUtil.enchantment(player, Enchantments.WIND_BURST), ModUtil.randomIntInclusive(1, 3));
                    }
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
            if (entity.isShiftKeyDown()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.causeFallDamage(fallDistance, fallDamage, entity.damageSources().fall());
    }

    protected static class Default extends Block {

        protected Default(Properties settings) {
            super(settings);
        }

        @Override
        public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.playerWillDestroy(world, pos, state, player);
        }
    }

    protected static class Pillar extends RotatedPillarBlock {

        protected Pillar(Properties settings) {
            super(settings);
        }

        @Override
        public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.playerWillDestroy(world, pos, state, player);
        }
    }

    protected static class Leaves extends TintedParticleLeavesBlock {

        protected Leaves(Properties settings) {
            super(0.01F, settings);
        }

        @Override
        public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
            fallDamage(entity, fallDistance);
        }

        @Override
        public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
            if (isDoomMode()) {
                whenBroken(world, pos, player);
            }
            return super.playerWillDestroy(world, pos, state, player);
        }
    }
}