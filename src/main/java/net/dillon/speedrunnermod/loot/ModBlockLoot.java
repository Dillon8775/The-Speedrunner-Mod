package net.dillon.speedrunnermod.loot;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModPotions;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.loot.data.GeneratableLootTable;
import net.dillon.speedrunnermod.loot.data.LootTableData;
import net.dillon.speedrunnermod.loot.providers.ModBlockLootTables;
import net.dillon.speedrunnermod.tag.ModPotionTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SetRandomPotionFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

/**
 * Generates normal block loot tables.
 */
public class ModBlockLoot extends FabricBlockLootSubProvider {
    private static final float[] NEW_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] NEW_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F, 0.06555558F, 0.70F, 0.075F, 0.1F};
    private static final float[] NEW_LEAVES_APPLE_DROP_CHANCE = new float[]{0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F};

    public ModBlockLoot(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(
                ModBlocks.DEAD_SPEEDRUNNER_BUSH,
                block -> this.createShearsDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                block, LootItem.lootTableItem(ModItems.SPEEDRUNNER_STICK)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                        )
                )
        );
        add(
                ModBlocks.SPEEDRUNNER_LEAVES,
                block -> newLeavesDrops(
                        block,
                        ModItems.SPEEDRUNNER_STICK,
                        ModItems.SPEEDRUNNER_SAPLING
                )
        );
        add(
                ModBlocks.DEAD_SPEEDRUNNER_LEAVES,
                block -> newLeavesDrops(
                        block,
                        ModItems.SPEEDRUNNER_STICK,
                        ModItems.DEAD_SPEEDRUNNER_SAPLING
                )
        );

        dropPottedContents(ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH);
        dropPottedContents(ModBlocks.POTTED_SPEEDRUNNER_SAPLING);

        dropSelf(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        dropSelf(ModBlocks.SPEEDRUNNER_BLOCK);
        dropSelf(ModBlocks.SPEEDRUNNER_FENCE);
        dropSelf(ModBlocks.SPEEDRUNNER_PLANKS);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        dropSelf(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
        dropSelf(ModBlocks.SPEEDRUNNER_SAPLING);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
        add(ModBlocks.SPEEDRUNNER_SLAB, this::createSlabItemTable);
        add(ModBlocks.DEAD_SPEEDRUNNER_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SPEEDRUNNER_STAIRS);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
        dropSelf(ModBlocks.SPEEDRUNNER_BUTTON);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_BUTTON);

        dropSelf(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        add(ModBlocks.SPEEDRUNNER_DOOR, this::createDoorTable);
        add(ModBlocks.DEAD_SPEEDRUNNER_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.SPEEDRUNNER_PRESSURE_PLATE);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_PRESSURE_PLATE);
        dropSelf(ModBlocks.SPEEDRUNNER_TRAPDOOR);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_TRAPDOOR);

        dropSelf(ModBlocks.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE);
        dropSelf(ModBlocks.METAL_SPEEDRUNNER_TRAPDOOR);
        add(ModBlocks.METAL_SPEEDRUNNER_DOOR, this::createDoorTable);

        addOreDrops();
        addWoodDrops();
        addDoomDrops();

        addVanillaDrops();
    }

    /**
     * Generates all speedrunner ore drops.
     */
    private void addOreDrops() {
        add(
                ModBlocks.SPEEDRUNNER_ORE,
                block -> createOreDrop(
                        block,
                        ModItems.SPEEDRUNNER_INGOT
                )
        );
        add(
                ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE,
                block -> createOreDrop(block, ModItems.SPEEDRUNNER_INGOT));
        add(
                ModBlocks.NETHER_SPEEDRUNNER_ORE,
                block -> createSilkTouchDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                        block,
                                        LootItem.lootTableItem(ModItems.SPEEDRUNNER_NUGGET)
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 6))))
                                .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                ));

        add(
                ModBlocks.IGNEOUS_ORE,
                block -> igneousOreDrop(block, 2));
        add(
                ModBlocks.DEEPSLATE_IGNEOUS_ORE,
                block -> igneousOreDrop(block, 2));
        add(
                ModBlocks.NETHER_IGNEOUS_ORE,
                block -> igneousOreDrop(block, 4));

        dropWhenSilkTouch(ModBlocks.THRUSTED_BLOCK);
        dropWhenSilkTouch(ModBlocks.EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.NETHER_EXPERIENCE_ORE);
    }

    /**
     * Generates all speedrunner wood drops.
     */
    private void addWoodDrops() {
        dropSelf(ModBlocks.SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.SPEEDRUNNER_WOOD);

        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    }

    /**
     * Generates all speedrunner mod doom block drops.
     */
    private void addDoomDrops() {
        dropWhenSilkTouch(ModBlocks.DOOM_STONE);
        dropWhenSilkTouch(ModBlocks.DOOM_LOG);
    }

    /**
     * Generates an igneous ore saplingItem.
     */
    private LootTable.Builder igneousOreDrop(Block dropWithSilkTouch, int min) {
        return createSilkTouchDispatchTable(
                dropWithSilkTouch,
                applyExplosionDecay(
                        dropWithSilkTouch,
                        LootItem.lootTableItem(ModItems.IGNEOUS_ROCK)
                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(min, 6)))
                                .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    /**
     * Generates a leaves saplingItem.
     */
    private LootTable.Builder newLeavesDrops(Block leaves, Item stickItem, Item saplingItem) {
        return createSilkTouchOrShearsDispatchTable(
                leaves,
                applyExplosionCondition(
                        leaves,
                        LootItem.lootTableItem(saplingItem)
                )
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NEW_SAPLING_DROP_CHANCE)))
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ContextIntProviders.exactly(1))
                                .add(
                                        applyExplosionCondition(
                                                leaves, LootItem.lootTableItem(Items.APPLE)
                                        )
                                )
                                .add(
                                        applyExplosionDecay(
                                                leaves, LootItem.lootTableItem(stickItem)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                        )
                                )
                        .when(this.doesNotHaveShearsOrSilkTouch())
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_APPLE_DROP_CHANCE))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_STICK_DROP_CHANCE))
                );
    }

    /**
     * Generates all vanilla block loot tables to be buffed.
     */
    private void addVanillaDrops() {
        add(
                Blocks.ACACIA_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.ACACIA_SAPLING
                )
        );
        add(
                Blocks.AZALEA_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.AZALEA
                )
        );
        add(
                Blocks.BIRCH_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.BIRCH_SAPLING
                )
        );
        add(
                Blocks.CHERRY_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.CHERRY_SAPLING
                )
        );
        add(
                Blocks.DARK_OAK_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.DARK_OAK_LEAVES
                )
        );
        add(
                Blocks.FLOWERING_AZALEA_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.FLOWERING_AZALEA
                )
        );
        add(
                Blocks.JUNGLE_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.JUNGLE_SAPLING
                )
        );
        add(
                Blocks.MANGROVE_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.MANGROVE_ROOTS
                )
        );
        add(
                Blocks.OAK_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.OAK_SAPLING
                )
        );
        add(
                Blocks.PALE_OAK_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.PALE_OAK_SAPLING
                )
        );
        add(
                Blocks.SPRUCE_LEAVES,
                block -> newLeavesDrops(
                        block,
                        Items.STICK,
                        Items.SPRUCE_SAPLING
                )
        );

        add(
                Blocks.GOLD_ORE,
                block -> createOreDrop(
                        block,
                        Items.GOLD_INGOT
                )
        );
        add(
                Blocks.DEEPSLATE_GOLD_ORE,
                block -> createOreDrop(
                        block,
                        Items.GOLD_INGOT
                )
        );
        add(
                Blocks.IRON_ORE,
                block -> createOreDrop(
                        block,
                        Items.IRON_INGOT
                )
        );
        add(
                Blocks.DEEPSLATE_IRON_ORE,
                block -> createOreDrop(
                        block,
                        Items.IRON_INGOT
                )
        );

        add(
                Blocks.GILDED_BLACKSTONE,
                block -> this.createSilkTouchDispatchTable(
                        block,
                        applyExplosionCondition(
                                block,
                                LootItem.lootTableItem(Items.GOLD_NUGGET)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                        .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                        )
                )
        );

        add(
                Blocks.NETHER_GOLD_ORE,
                block -> this.createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(Items.GOLD_NUGGET)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(9, 18)))
                                        .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                        )
                )
        );

        add(
                Blocks.GRAVEL,
                block -> this.createSilkTouchDispatchTable(
                        block,
                        applyExplosionCondition(
                                block,
                                LootItem.lootTableItem(Items.FLINT)
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), 0.25F, 0.25F, 0.25F, 1.0F))
                                        .otherwise(LootItem.lootTableItem(block))
                        )
                )
        );

        add(
                Blocks.DEAD_BUSH,
                block -> this.createShearsDispatchTable(
                        block,
                        this.applyExplosionDecay(
                                block, LootItem.lootTableItem(Items.STICK)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                        )
                )
        );

        add(
                Blocks.SWEET_BERRY_BUSH,
                block -> this.applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(
                                        LootPool.lootPool()
                                                .add(LootItem.lootTableItem(Items.SWEET_BERRIES))
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                                                .when(MatchBlock.blockMatches(this.blocks, Blocks.SWEET_BERRY_BUSH, StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3)))
                                )
                                .withPool(
                                        LootPool.lootPool()
                                                .add(LootItem.lootTableItem(Items.SWEET_BERRIES))
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5)))
                                                .apply(ApplyBonusCount.addUniformBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                                                .when(MatchBlock.blockMatches(this.blocks, Blocks.SWEET_BERRY_BUSH, StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2)))
                                )
                )
        );
    }

    /**
     * Generates the loot for when mining a doom block.
     */
    public static class DoomBlockLoot extends GeneratableLootTable {

        public DoomBlockLoot(LootTableData data) {
            super(data);
        }

        @Override
        public void generateLoot() {
            data.output().accept(
                    ModBlockLootTables.DOOM_BLOCK_LOOT,
                    LootTable.lootTable()
                            .withPool(LootPool.lootPool()
                                    .setRolls(ContextIntProviders.exactly(1))
                                    .add(
                                            LootItem.lootTableItem(Items.DIAMOND_SWORD)
                                                    .apply(enchantBetweenLevels(20, 33))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.NETHERITE_CHESTPLATE)
                                                    .apply(enchantBetweenLevels(27, 33))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.BOW)
                                                    .apply(enchantBetweenLevels(30, 33))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.CROSSBOW)
                                                    .apply(enchantBetweenLevels(27, 33))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.IRON_CHESTPLATE)
                                                    .apply(enchantBetweenLevels(24, 28))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                                    .setWeight(4)
                                                    .apply(setCount(1, 3))
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItems.RAID_ERADICATOR)
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItems.SPEEDRUNNERS_TOTEM)
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.FIRE_CHARGE)
                                                    .setWeight(3)
                                                    .apply(setCount(2, 5))
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItems.DRAGON_FIREBALL)
                                                    .setWeight(2)
                                                    .apply(setCount(1, 3))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE)
                                                    .setWeight(3)
                                                    .apply(setCount(2, 11))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.ELYTRA)
                                                    .apply(setDamage(0.18F, 0.45F))
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItems.KNOCKBACK_STICK)
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.POTION)
                                                    .setWeight(2)
                                                    .apply(SetRandomPotionFunction.fromTagKey(
                                                            data.potions().getOrThrow(ModPotionTags.DOOM_BLOCK_POTIONS))
                                                    )
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.POTION)
                                                    .apply(SetPotionFunction.setPotion(ModPotions.DRAGONS_AURA))
                                    )
                                    .add(
                                            LootItem.lootTableItem(ModItems.DRAGONS_PEARL)
                                                    .setWeight(2)
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.WIND_CHARGE)
                                                    .setWeight(2)
                                                    .apply(setCount(4, 16))
                                    )
                                    .add(
                                            LootItem.lootTableItem(Items.MACE)
                                                    .apply(setDamage(0.12F, 0.23F))
                                    )
                            )
            );
        }
    }
}