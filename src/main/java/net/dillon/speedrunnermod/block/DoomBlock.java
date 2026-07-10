package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.loot.ModLootTables;
import net.dillon.speedrunnermod.util.RandomChance;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

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
    private static void whenBroken(Level level, BlockPos pos, Player player) {
        float doomBlockProtection = (float)player.getAttributeValue(ModAttributes.DOOM_BLOCK_IMMUNITY);
        boolean hasProtection = doomBlockProtection > 1.0F;
        if (!hasProtection && EnchantmentHelper.getEnchantmentLevel(ModHelper.enchantment(player, Enchantments.SILK_TOUCH), player) > 0) {
            return;
        }

        if (!player.getAbilities().instabuild && !hasProtection && !player.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            if (level.getRandom().nextFloat() < 0.50F) {
                level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
            }

            List<EntityType<?>> possibleEntities = List.of(
                    EntityTypes.ZOMBIE,
                    EntityTypes.VINDICATOR,
                    EntityTypes.RAVAGER,
                    EntityTypes.PIGLIN_BRUTE,
                    EntityTypes.GHAST
            );
            if (level.getRandom().nextFloat() < 0.35F) {
                Mob entity = EntityTypes.ZOMBIE.create(level, EntitySpawnReason.MOB_SUMMONED);
                EntityType<?> entityType = possibleEntities.get(RandomChance.intInclusive(0, possibleEntities.size() - 1));
                if (entityType == EntityTypes.VINDICATOR) {
                    entity = EntityTypes.VINDICATOR.create(level, EntitySpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.IRON_AXE);
                    axe.setDamageValue(level.getRandom().nextInt(100));
                    entity.setItemSlot(EquipmentSlot.MAINHAND, axe);
                } else if (entityType == EntityTypes.RAVAGER) {
                    entity = EntityTypes.RAVAGER.create(level, EntitySpawnReason.MOB_SUMMONED);
                } else if (entityType == EntityTypes.PIGLIN_BRUTE) {
                    entity = EntityTypes.PIGLIN_BRUTE.create(level, EntitySpawnReason.MOB_SUMMONED);
                    ItemStack axe = new ItemStack(Items.GOLDEN_AXE);
                    axe.setDamageValue(level.getRandom().nextInt(24));
                    entity.setItemSlot(EquipmentSlot.MAINHAND, axe);
                    if (entity instanceof PiglinBrute brute) {
                        brute.setImmuneToZombification(true);
                    }
                } else if (entityType == EntityTypes.GHAST) {
                    entity = EntityTypes.GHAST.create(level, EntitySpawnReason.MOB_SUMMONED);
                }
                entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, TickCalculator.seconds(30), 0, false, true, false));
                entity.snapTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, level.getRandom().nextFloat() * 360.0F, 0.0F);
                for (int i = 0; i < (entityType == EntityTypes.ZOMBIE ? 3 : 1); i++) {
                    level.addFreshEntity(entity);
                }
            }
        } else if (!player.getAbilities().instabuild) {
            if (level.getRandom().nextFloat() < calculateChance(player) && level instanceof ServerLevel serverLevel) {
                LootParams.Builder lootParams = new LootParams.Builder(serverLevel)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.BLOCK_STATE, level.getBlockState(pos))
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                        .withOptionalParameter(LootContextParams.TOOL, player.getMainHandItem());

                for (ItemStack stack : serverLevel.getServer().reloadableRegistries().getLootTable(ModLootTables.DOOM_BLOCK_LOOT)
                        .getRandomItems(lootParams.create(LootContextParamSets.BLOCK))) {
                    spawnFloatingItemEntity(level, pos, stack, player, true);
                }
                if (player instanceof ServerPlayer serverPlayer && serverPlayer.hasEffect(MobEffects.LUCK)) {
                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(serverPlayer, new ItemStack(ModItems.DOOM_STONE));
                }
            }
        }
    }

    /**
     * @return the chance to spawn loot from a doom block.
     */
    private static float calculateChance(Player player) {
        float playerLuck = (float)player.getAttributeValue(Attributes.LUCK);
        float clampedLuck = Math.clamp(playerLuck, 0.0F, playerLuck);
        float additionalLuck = clampedLuck >= 1.0F ? 0.5F + clampedLuck : 1.0F;
        float minChance = 0.12F * additionalLuck;
        float maxChance = 0.16F * additionalLuck;
        return RandomChance.floatInclusive(minChance, maxChance);
    }

    /**
     * Handles the fall damage when landing on a doom mode block.
     */
    private static void fallDamage(Entity entity, double fallDistance) {
        float fallDamage;
        if (!options().general.fallDamage.getCurrentValue()) {
            fallDamage = 0.0F;
        } else {
            fallDamage = isDoomMode() ? 1.15F : 1.0F;
            if (entity.isShiftKeyDown()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.causeFallDamage(fallDistance, fallDamage, entity.damageSources().fall());
    }

    /**
     * Spawns a {@code floating stack entity} from the {@link BlockPos}'s position.
     */
    private static void spawnFloatingItemEntity(Level world, BlockPos pos, ItemStack stack, Player player, boolean playSound) {
        ItemEntity item = new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 3.0F, pos.getZ() + 0.5F, stack);
        item.setInvulnerable(true);
        item.setGlowingTag(true);
        item.setNoGravity(true);
        item.setUnlimitedLifetime();

        Vec3 itemPos = item.position();
        Vec3 playerPos = player.position();
        Vec3 motion = playerPos.subtract(itemPos).normalize().scale(0.1D);
        item.setDeltaMovement(motion.x, motion.y, motion.z);

        if (playSound) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 3.0F, 1.0F);
        }
        world.addFreshEntity(item);
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
            super(0.00F, settings);
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