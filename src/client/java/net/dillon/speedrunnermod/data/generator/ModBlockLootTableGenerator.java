package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Generates block loot tables.
 * <p>Vanilla loot tables are modified separately, this is only used to generate the {@code speedrunner mod block loot tables.}</p>
 */
public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    private static final float[] NEW_SAPLING_DROP_CHANCE = new float[]{0.075F, 0.0800F, 0.093333336F, 0.15F};
    private static final float[] NEW_LEAVES_STICK_DROP_CHANCE = new float[]{0.65F, 0.06555558F, 0.70F, 0.075F, 0.1F};
    private final RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);

    protected ModBlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_BUSH, (Block block) -> dropsWithShears(block, applyExplosionDecay(block, ItemEntry.builder(ModItems.DEAD_SPEEDRUNNER_STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 9))))));
        addDrop(ModBlocks.SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModItems.SPEEDRUNNER_STICK, ModBlocks.SPEEDRUNNER_SAPLING, false, NEW_SAPLING_DROP_CHANCE));
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, (Block block) -> speedrunnerLeavesDrops(block, ModItems.DEAD_SPEEDRUNNER_STICK, ModBlocks.DEAD_SPEEDRUNNER_SAPLING, false, NEW_SAPLING_DROP_CHANCE));

        addPottedPlantDrops(ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH);
        addPottedPlantDrops(ModBlocks.POTTED_SPEEDRUNNER_SAPLING);

        addDrop(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        addDrop(ModBlocks.SPEEDRUNNER_BLOCK);
        addDrop(ModBlocks.SPEEDRUNNER_FENCE);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        addDrop(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
        addDrop(ModBlocks.SPEEDRUNNER_SAPLING);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
        addDrop(ModBlocks.SPEEDRUNNER_SLAB, this::slabDrops);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_SLAB, this::slabDrops);
        addDrop(ModBlocks.SPEEDRUNNER_STAIRS);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
        addDrop(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

        addDrop(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, this::doorDrops);
        addDrop(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, this::doorDrops);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addDrop(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addDrop(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
        addDrop(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        addDrop(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addDrop(ModBlocks.SPEEDRUNNER_TRAPDOOR);
        addDrop(ModBlocks.SPEEDRUNNER_DOOR, this::doorDrops);

        addOreDrops();
        addWoodDrops();
        addSignDrops();
        addDoomDrops();
    }

    private void addOreDrops() {
        addDrop(ModBlocks.SPEEDRUNNER_ORE, (Block block) -> oreDrops(block, ModItems.SPEEDRUNNER_INGOT));
        addDrop(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, (Block block) -> oreDrops(block, ModItems.SPEEDRUNNER_INGOT));
        addDrop(ModBlocks.NETHER_SPEEDRUNNER_ORE, (Block block) -> dropsWithSilkTouch(block, this.applyExplosionDecay(block, ItemEntry.builder(ModItems.SPEEDRUNNER_NUGGET).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 6)))).apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));

        addDrop(ModBlocks.IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        addDrop(ModBlocks.DEEPSLATE_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 2));
        addDrop(ModBlocks.NETHER_IGNEOUS_ORE, (Block block) -> igneousOreDrops(block, 4));

        addDropWithSilkTouch(ModBlocks.EXPERIENCE_ORE);
        addDropWithSilkTouch(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        addDropWithSilkTouch(ModBlocks.NETHER_EXPERIENCE_ORE);
    }

    private void addWoodDrops() {
        addDrop(ModBlocks.SPEEDRUNNER_LOG);
        addDrop(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);

        addDrop(ModBlocks.DEAD_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
        addDrop(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    }

    private void addSignDrops() {
        addDrop(ModBlocks.SPEEDRUNNER_SIGN);
        addDrop(ModBlocks.SPEEDRUNNER_WALL_SIGN);
        addDrop(ModBlocks.SPEEDRUNNER_HANGING_SIGN);
        addDrop(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN);

        addDrop(ModBlocks.DEAD_SPEEDRUNNER_SIGN);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN);
        addDrop(ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);
    }

    private void addDoomDrops() {
        addDropWithSilkTouch(ModBlocks.DOOM_STONE);
        addDropWithSilkTouch(ModBlocks.DOOM_LOG);
        addDropWithSilkTouch(ModBlocks.STRIPPED_DOOM_LOG);
    }

    private LootTable.Builder igneousOreDrops(Block dropWithSilkTouch, int min) {
        return dropsWithSilkTouch(dropWithSilkTouch, applyExplosionDecay(dropWithSilkTouch, ItemEntry.builder(ModItems.IGNEOUS_ROCK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, 6))).apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

    private LootTable.Builder speedrunnerLeavesDrops(Block leaves, Item item, Block drop, boolean goldenApple, float ... chance) {
        return dropsWithSilkTouchOrShears(leaves, addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chance))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(createWithoutShearsOrSilkTouchCondition()).with(addSurvivesExplosionCondition(leaves, ItemEntry.builder(goldenApple ? Items.GOLDEN_APPLE : Items.APPLE))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.50F, 0.05555558F, 0.35F, 0.07F, 0.1F)).with(applyExplosionDecay(leaves, ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), NEW_LEAVES_STICK_DROP_CHANCE)));
    }
}