package net.dillon.speedrunnermod.loot;

import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.advancements.predicates.entity.CubeMobPredicate;
import net.minecraft.advancements.predicates.entity.EntityFlagsPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.predicates.entity.EntityTypePredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.animal.frog.FrogVariants;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition.killedByPlayer;

/**
 * All custom speedrunner mod entity loot tables.
 */
public class ModEntityLoot extends FabricEntityLootSubProvider {

    public ModEntityLoot(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {
        addVanillaDrops();
    }

    /**
     * Generates all vanilla loot tables to be buffed.
     */
    private void addVanillaDrops() {
        add(
                EntityTypes.BLAZE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BLAZE_ROD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_BLOCK)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.FIRE_CHARGE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.BOGGED,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.TIPPED_ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SetPotionFunction.setPotion(Potions.POISON))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.CAVE_SPIDER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.STRING)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.SPIDER_EYE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.CHICKEN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.FEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.CHICKEN)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.COD,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.COD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE_MEAL)
                                        )
                                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
                        )
        );

        add(
                EntityTypes.COW,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.CREEPER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GUNPOWDER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .add(
                                                TagEntry.expandTag(this.items.getOrThrow(ItemTags.CREEPER_DROP_MUSIC_DISCS))
                                        )
                                        .when(LootItemEntityPropertyCondition.hasProperties(
                                                LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity().of(this.entityTypes, EntityTypeTags.SKELETONS)
                                        ))
                        )
        );

        add(
                EntityTypes.DOLPHIN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.COD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
        );

        add(
                EntityTypes.DONKEY,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.DROWNED,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.COPPER_INGOT)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(killedByPlayer())
                                        )
                        )
        );

        add(
                EntityTypes.ENDERMAN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ENDER_PEARL)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ENDER_MATTER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.33F, 0.03F))
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.EVOKER,
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().setRolls(ContextIntProviders.exactly(1)).add(LootItem.lootTableItem(Items.TOTEM_OF_UNDYING)))
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.EMERALD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.BREEZE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BREEZE_ROD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.GHAST,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GHAST_TEAR)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GUNPOWDER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_TEARS))
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                        .when(DamageSourceCondition.hasDamageSource(this.projectileDamage().direct(EntityPredicate.Builder.entity().of(this.entityTypes, EntityTypes.FIREBALL))))
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.GLOW_SQUID,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GLOW_INK_SAC)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.HORSE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.HUSK,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.RABBIT_FOOT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(
                                                                LootItemEntityPropertyCondition.hasProperties(
                                                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(this.entityTypes, EntityTypes.CAMEL_HUSK))).build()
                                                                )
                                                        )
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.IRON_INGOT))
                                        .add(LootItem.lootTableItem(Items.CARROT))
                                        .add(LootItem.lootTableItem(Items.POTATO).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                                        .when(killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.045F, 0.04F))
                        )
        );

        add(
                EntityTypes.IRON_GOLEM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Blocks.POPPY)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.IRON_INGOT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(5, 7)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.LLAMA,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.MAGMA_CUBE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.MAGMA_CREAM)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(this.killedByFrog().invert())
                                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().cubeMob(CubeMobPredicate.sized(MinMaxBounds.Ints.atLeast(2)))))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.PEARLESCENT_FROGLIGHT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(this.killedByFrogVariant(FrogVariants.WARM))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.VERDANT_FROGLIGHT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(this.killedByFrogVariant(FrogVariants.COLD))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.OCHRE_FROGLIGHT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(this.killedByFrogVariant(FrogVariants.TEMPERATE))
                                        )
                        )
        );

        add(
                EntityTypes.MULE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.MOOSHROOM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BEEF)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.NAUTILUS,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.NAUTILUS_SHELL))
                                        .when(killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.25F, 0.03F))
                        )
        );

        add(
                EntityTypes.PHANTOM,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.PHANTOM_MEMBRANE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.PIG,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.PORKCHOP)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.POLAR_BEAR,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.COD)
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .setWeight(3)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.SALMON)
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.RABBIT,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.RABBIT_HIDE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.RABBIT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.RABBIT_FOOT))
                                        .when(killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.2F, 0.05F))
                        )
        );

        add(
                EntityTypes.SALMON,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(2, 4))
                                        .add(
                                                LootItem.lootTableItem(Items.SALMON)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.BONE_MEAL))
                                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
                        )
        );

        add(
                EntityTypes.SHEEP,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.MUTTON)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(createSheepDispatchPool(BuiltInLootTables.SHEEP.map(this.lootTables::getOrThrow)))
        );

        add(
                EntityTypes.SHULKER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.SHULKER_SHELL)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                        )
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.75F, 0.07F))
                        )
        );

        add(
                EntityTypes.SKELETON,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.SLIME,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.SLIME_BALL)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(this.killedByFrog().invert())
                                        )
                                        .add(LootItem.lootTableItem(Items.SLIME_BALL).apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))).when(this.killedByFrog()))
                                        .when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().cubeMob(CubeMobPredicate.sized(MinMaxBounds.Ints.exactly(1)))))
                        )
        );

        add(
                EntityTypes.SPIDER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.STRING)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.SPIDER_EYE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.SQUID,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.INK_SAC)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.STRAY,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.TIPPED_ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SetPotionFunction.setPotion(Potions.SLOWNESS))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.PARCHED,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.TIPPED_ARROW)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SetPotionFunction.setPotion(Potions.WEAKNESS))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.STRIDER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.STRING)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.TRADER_LLAMA,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.TROPICAL_FISH,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.TROPICAL_FISH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.BONE_MEAL))
                                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
                        )
        );

        add(
                EntityTypes.VINDICATOR,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.EMERALD)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.WITCH,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(1, 3))
                                        .add(
                                                LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.SUGAR)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.SPIDER_EYE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GLASS_BOTTLE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GUNPOWDER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.STICK)
                                                        .setWeight(2)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 7)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.REDSTONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 12)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.ZOGLIN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 14)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
        );

        add(
                EntityTypes.HOGLIN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.PORKCHOP)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 14)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.LEATHER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.ZOMBIE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.RED_MUSHROOM)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.between(0.0F, 1.0F)))
                                                        .when(
                                                                LootItemEntityPropertyCondition.hasProperties(
                                                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().vehicle(EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(this.entityTypes, EntityTypes.ZOMBIE_HORSE))).build()
                                                                )
                                                        )
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.IRON_INGOT))
                                        .add(LootItem.lootTableItem(Items.CARROT))
                                        .add(LootItem.lootTableItem(Items.POTATO).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                                        .when(killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.045F, 0.05F))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .add(LootItem.lootTableItem(Items.MUSIC_DISC_LAVA_CHICKEN))
                                        .when(killedByPlayer())
                                        .when(
                                                LootItemEntityPropertyCondition.hasProperties(
                                                        LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .flags(EntityFlagsPredicate.Builder.flags().setIsBaby(true))
                                                                .vehicle(EntityPredicate.Builder.entity().of(this.entityTypes, EntityTypes.CHICKEN))
                                                )
                                        )
                        )
        );

        add(
                EntityTypes.ZOMBIE_VILLAGER,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(LootItem.lootTableItem(Items.IRON_INGOT))
                                        .add(LootItem.lootTableItem(Items.CARROT))
                                        .add(LootItem.lootTableItem(Items.POTATO).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                                        .when(killedByPlayer())
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.045F, 0.05F))
                        )
        );

        add(
                EntityTypes.ZOMBIE_HORSE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0f)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
        );

        add(
                EntityTypes.ZOMBIE_NAUTILUS,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.ZOMBIFIED_PIGLIN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_NUGGET)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 24)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_INGOT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(-1, 3)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(killedByPlayer())
                        )
        );

        add(
                EntityTypes.PIGLIN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.PIGLIN_PORK)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
        );

        add(
                EntityTypes.PIGLIN_BRUTE,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.PIGLIN_PORK)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
                                        )
                        )
        );

        add(
                EntityTypes.WARDEN,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(1, 3))
                                        .add(
                                                LootItem.lootTableItem(Items.ENDER_PEARL)
                                                        .setWeight(7)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 7)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                                        .setWeight(9)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.DIAMOND)
                                                        .setWeight(11)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 11)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE)
                                                        .setWeight(3)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNER_CHESTPLATE)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(35))
                                                                .withOptions(
                                                                        this.enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.050F, 0.10F))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(0, 1))
                                        .add(
                                                LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.DIAMOND_SWORD)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(this.enchantments, ContextIntProviders.exactly(35))
                                                                .withOptions(
                                                                        this.enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.045F, 0.09F))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.NETHERITE_INGOT)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNERS_EYE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.INVENTORY_PRESERVER)
                                                        .setWeight(6)
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNERS_TOTEM)
                                                        .setWeight(5)
                                        )
                                        .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.enchantments, 0.033F, 0.06F))
                                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(0, 1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ENDER_MATTER)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 12)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
        );

        add(
                EntityTypes.WITHER_SKELETON,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.NETHERITE_SCRAP)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.COAL)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.BONE)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 4)))
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.enchantments, ContextFloatProviders.exactly(1.0F)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL)
                                                        .when(() -> new LootItemRandomChanceWithEnchantedBonusCondition(
                                                                ModEnchantments.WITHERED_DROP_CHANCE,
                                                                new LevelBasedValue.Linear(
                                                                        ModEnchantments.WITHERED_PER_LEVEL_ABOVE_FIRST,
                                                                        ModEnchantments.WITHERED_PER_LEVEL_ABOVE_FIRST
                                                                ),
                                                                this.enchantments.getOrThrow(ModEnchantments.WITHERED)
                                                        ))
                                        )
                        )
        );
    }
}