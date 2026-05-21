package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.effect.ModMobEffects;
import net.dillon.speedrunnermod.loot.ModLootTables;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

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
        if (!player.getAbilities().instabuild && !player.getMainHandItem().is(ModItemTags.DOOM_STONE_SAFE_TOOLS) && !player.hasEffect(ModMobEffects.DRAGONS_AURA)) {
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
            if (world.getRandom().nextFloat() < ModUtil.randomFloatInclusive(0.22F, 0.32F) && world instanceof ServerLevel serverLevel) {
                LootParams.Builder lootParams = new LootParams.Builder(serverLevel)
                        .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                        .withParameter(LootContextParams.BLOCK_STATE, world.getBlockState(pos))
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                        .withOptionalParameter(LootContextParams.TOOL, player.getMainHandItem());

                for (ItemStack stack : serverLevel.getServer().reloadableRegistries().getLootTable(ModLootTables.DOOM_BLOCK_LOOT)
                        .getRandomItems(lootParams.create(LootContextParamSets.BLOCK))) {
                    ModUtil.spawnFloatingItemEntity(world, pos, stack, player, true);
                }
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