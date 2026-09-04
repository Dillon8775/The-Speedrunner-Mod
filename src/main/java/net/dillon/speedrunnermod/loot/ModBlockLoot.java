package net.dillon.speedrunnermod.loot;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Generates normal block loot tables.
 */
public class ModBlockLoot extends FabricBlockLootSubProvider {
    private static final float[] NEW_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] NEW_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F, 0.06555558F, 0.70F, 0.075F, 0.1F};

    public ModBlockLoot(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    /**
     * Creates a new speedrunner mod loot table.
     */
    protected static ResourceKey<LootTable> createLootTable(final String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ofSpeedrunnerMod(path));
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
                                                .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 6))))
                                .apply(ApplyBonusCount.addOreBonusCount(this.enchantments.getOrThrow(Enchantments.FORTUNE)))
                ));

        add(
                ModBlocks.IGNEOUS_ORE,
                block -> igneousOreDrops(block, 2));
        add(
                ModBlocks.DEEPSLATE_IGNEOUS_ORE,
                block -> igneousOreDrops(block, 2));
        add(
                ModBlocks.NETHER_IGNEOUS_ORE,
                block -> igneousOreDrops(block, 4));

        dropWhenSilkTouch(ModBlocks.THRUSTED_BLOCK);
        dropWhenSilkTouch(ModBlocks.EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.NETHER_EXPERIENCE_ORE);
    }

    private void addWoodDrops() {
        dropSelf(ModBlocks.SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.SPEEDRUNNER_WOOD);

        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    }

    private void addDoomDrops() {
        dropWhenSilkTouch(ModBlocks.DOOM_STONE);
        dropWhenSilkTouch(ModBlocks.DOOM_LOG);
    }

    private LootTable.Builder igneousOreDrops(Block dropWithSilkTouch, int min) {
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

    private LootTable.Builder speedrunnerLeavesDrops(Block leaves, Item item, Block drop, boolean goldenApple, float... chance) {
        return createSilkTouchOrShearsDispatchTable(
                leaves,
                applyExplosionCondition(
                        leaves,
                        LootItem.lootTableItem(drop)
                )
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), chance)))
                .withPool(LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .when(doesNotHaveShearsOrSilkTouch())
                        .add(applyExplosionCondition(leaves, LootItem.lootTableItem(goldenApple ? Items.GOLDEN_APPLE : Items.APPLE)))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), 0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F))
                        .add(applyExplosionDecay(leaves, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(this.enchantments.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_STICK_DROP_CHANCE))
                );
    }
}