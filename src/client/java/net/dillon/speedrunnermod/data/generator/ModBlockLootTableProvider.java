package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

/**
 * Generates block loot tables.
 * <p>Vanilla loot tables are modified separately, this is only used to generate the {@code speedrunner mod block loot tables.}</p>
 */
public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    private static final float[] NEW_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] NEW_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F, 0.06555558F, 0.70F, 0.075F, 0.1F};

    protected ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(
                ModBlocks.DEAD_SPEEDRUNNER_BUSH,
                this::speedrunnerBushDrops
        );
        add(
                ModBlocks.SPEEDRUNNER_LEAVES,
                block -> speedrunnerLeavesDrops(
                        block,
                        ModItems.SPEEDRUNNER_STICK,
                        ModBlocks.SPEEDRUNNER_SAPLING,
                        false,
                        NEW_SAPLING_DROP_CHANCE
                )
        );
        add(
                ModBlocks.DEAD_SPEEDRUNNER_LEAVES,
                block -> speedrunnerLeavesDrops(
                        block,
                        ModItems.SPEEDRUNNER_STICK,
                        ModBlocks.DEAD_SPEEDRUNNER_SAPLING,
                        false,
                        NEW_SAPLING_DROP_CHANCE
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
    }

    private void addOreDrops() {
        add(ModBlocks.SPEEDRUNNER_ORE, block -> simpleItemDrop(block, ModItems.SPEEDRUNNER_INGOT));
        add(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, block -> simpleItemDrop(block, ModItems.SPEEDRUNNER_INGOT));
        add(ModBlocks.NETHER_SPEEDRUNNER_ORE, block -> countedItemDrop(block, ModItems.SPEEDRUNNER_NUGGET, 2, 6));

        add(ModBlocks.IGNEOUS_ORE, block -> countedItemDrop(block, ModItems.IGNEOUS_ROCK, 2, 6));
        add(ModBlocks.DEEPSLATE_IGNEOUS_ORE, block -> countedItemDrop(block, ModItems.IGNEOUS_ROCK, 2, 6));
        add(ModBlocks.NETHER_IGNEOUS_ORE, block -> countedItemDrop(block, ModItems.IGNEOUS_ROCK, 4, 6));

        dropSelf(ModBlocks.THRUSTED_BLOCK);
        add(ModBlocks.EXPERIENCE_ORE, block -> countedItemDrop(block, ModItems.EXPERIENCE_FRAGMENT, 1, 3));
        add(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, block -> countedItemDrop(block, ModItems.EXPERIENCE_FRAGMENT, 1, 3));
        add(ModBlocks.NETHER_EXPERIENCE_ORE, block -> countedItemDrop(block, ModItems.EXPERIENCE_FRAGMENT, 1, 3));
    }

    private void addWoodDrops() {
        dropSelf(ModBlocks.SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.SPEEDRUNNER_WOOD);

        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    }

    private void addDoomDrops() {
        dropSelf(ModBlocks.DOOM_STONE);
        dropSelf(ModBlocks.DOOM_LOG);
    }

    @Deprecated
    private LootTable.Builder speedrunnerBushDrops(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(ModItems.SPEEDRUNNER_STICK)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 9)))
                        )));
    }

    @Deprecated
    private LootTable.Builder simpleItemDrop(Block block, Item item) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionDecay(block, LootItem.lootTableItem(item))));
    }

    @Deprecated
    private LootTable.Builder countedItemDrop(Block block, Item item, int min, int max) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionDecay(
                                block,
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(min, max)))
                                        .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                        )));
    }

    @Deprecated
    private LootTable.Builder speedrunnerLeavesDrops(Block leaves, Item item, Block drop, boolean goldenApple, float... chance) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionDecay(
                                drop,
                                LootItem.lootTableItem(drop)
                                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), chance))
                        )))
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionCondition(
                                leaves,
                                LootItem.lootTableItem(goldenApple ? Items.GOLDEN_APPLE : Items.APPLE)
                        ))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), 0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F)))
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(applyExplosionDecay(
                                leaves,
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                        ))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_STICK_DROP_CHANCE)));
    }
}