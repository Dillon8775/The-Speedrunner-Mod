package net.dillon.speedrunnermod.client.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

/**
 * Generates block loot tables.
 * <p>Vanilla loot tables are modified separately, this is only used to generate the {@code speedrunner mod block loot tables.}</p>
 */
public class ModBlockLootTableGenerator extends FabricBlockLootSubProvider {
    private static final float[] NEW_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] NEW_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F, 0.06555558F, 0.70F, 0.075F, 0.1F};
    private final HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    protected ModBlockLootTableGenerator(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(ModBlocks.DEAD_SPEEDRUNNER_BUSH, (Block block) -> createShearsDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(ModItems.SPEEDRUNNER_STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 9))))));
        add(ModBlocks.SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModItems.SPEEDRUNNER_STICK, ModBlocks.SPEEDRUNNER_SAPLING, false, NEW_SAPLING_DROP_CHANCE));
        add(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModItems.SPEEDRUNNER_STICK, ModBlocks.DEAD_SPEEDRUNNER_SAPLING, false, NEW_SAPLING_DROP_CHANCE));

        dropPottedContents(ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH);
        dropPottedContents(ModBlocks.POTTED_SPEEDRUNNER_SAPLING);

        dropSelf(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        dropSelf(ModBlocks.SPEEDRUNNER_BLOCK);
        dropSelf(ModBlocks.SPEEDRUNNER_FENCE);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        dropSelf(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
        dropSelf(ModBlocks.SPEEDRUNNER_SAPLING);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
        add(ModBlocks.SPEEDRUNNER_SLAB, this::createSlabItemTable);
        add(ModBlocks.DEAD_SPEEDRUNNER_SLAB, this::createSlabItemTable);
        dropSelf(ModBlocks.SPEEDRUNNER_STAIRS);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
        dropSelf(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
        dropSelf(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

        dropSelf(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        add(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, this::createDoorTable);
        add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, this::createDoorTable);
        dropSelf(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        dropSelf(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        dropSelf(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
        dropSelf(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        dropSelf(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        dropSelf(ModBlocks.SPEEDRUNNER_TRAPDOOR);
        add(ModBlocks.SPEEDRUNNER_DOOR, this::createDoorTable);

        addOreDrops();
        addWoodDrops();
        addSignDrops();
        addDoomDrops();
    }

    private void addOreDrops() {
        add(ModBlocks.SPEEDRUNNER_ORE, (Block block) -> createOreDrop(block, ModItems.SPEEDRUNNER_INGOT));
        add(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, (Block block) -> createOreDrop(block, ModItems.SPEEDRUNNER_INGOT));
        add(ModBlocks.NETHER_SPEEDRUNNER_ORE, (Block block) -> createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(ModItems.SPEEDRUNNER_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6)))).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));

        add(ModBlocks.IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        add(ModBlocks.DEEPSLATE_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        add(ModBlocks.NETHER_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 4));

        dropWhenSilkTouch(ModBlocks.THRUSTED_BLOCK);
        dropWhenSilkTouch(ModBlocks.EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        dropWhenSilkTouch(ModBlocks.NETHER_EXPERIENCE_ORE);
    }

    private void addWoodDrops() {
        dropSelf(ModBlocks.SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.SPEEDRUNNER_WOOD);
        dropSelf(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);

        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
        dropSelf(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    }

    private void addSignDrops() {
        dropSelf(ModBlocks.SPEEDRUNNER_SIGN);
        dropSelf(ModBlocks.SPEEDRUNNER_WALL_SIGN);
        dropSelf(ModBlocks.SPEEDRUNNER_HANGING_SIGN);
        dropSelf(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN);

        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_SIGN);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN);
        dropSelf(ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);
    }

    private void addDoomDrops() {
        dropWhenSilkTouch(ModBlocks.DOOM_STONE);
        dropWhenSilkTouch(ModBlocks.DOOM_LOG);
        dropWhenSilkTouch(ModBlocks.STRIPPED_DOOM_LOG);
    }

    private LootTable.Builder igneousOreDrops(Block dropWithSilkTouch, int min) {
        return createSilkTouchDispatchTable(dropWithSilkTouch, applyExplosionDecay(dropWithSilkTouch, LootItem.lootTableItem(ModItems.IGNEOUS_ROCK).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, 6))).apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    private LootTable.Builder speedrunnerLeavesDrops(Block leaves, Item item, Block drop, boolean goldenApple, float ... chance) {
        return createSilkTouchOrShearsDispatchTable(leaves, applyExplosionCondition(leaves, LootItem.lootTableItem(drop)).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), chance))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f)).when(doesNotHaveShearsOrSilkTouch()).add(applyExplosionCondition(leaves, LootItem.lootTableItem(goldenApple ? Items.GOLDEN_APPLE : Items.APPLE))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), 0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F)).add(applyExplosionDecay(leaves, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))).when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_STICK_DROP_CHANCE)));
    }
}