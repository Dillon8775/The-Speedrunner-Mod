package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.advancement.UsedItemCriterion;
import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod advancements.
 */
public class ModAdvancementTabGenerator extends FabricAdvancementProvider {

    protected ModAdvancementTabGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> exporter) {
        RegistryEntryLookup<Item> itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        ModBlockItems.SPEEDRUNNERS_WORKBENCH,
                        Text.translatable("advancements.speedrunnermod.title"),
                        Text.translatable("advancements.speedrunnermod.description"),
                        ofSpeedrunnerMod("minecraft:textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("has_crafting_table", InventoryChangedCriterion.Conditions.items(Items.CRAFTING_TABLE))
                .build(exporter, "speedrunnermod:root");

        AdvancementEntry speedrunningTime = Advancement.Builder.create()
                .parent(root)
                .display(
                        ModItems.SPEEDRUNNER_INGOT,
                        Text.translatable("advancements.speedrunnermod.speedrunning_time.title"),
                        Text.translatable("advancements.speedrunnermod.speedrunning_time.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_INGOT))
                .build(exporter, "speedrunnermod:items/speedrunning_time");
        requireSpeedrunnersWasteland(Advancement.Builder.create(), wrapperLookup)
                .parent(speedrunningTime)
                .display(
                        ModBlockItems.SPEEDRUNNER_SAPLING,
                        Text.translatable("advancements.speedrunnermod.what_a_wasteland.title"),
                        Text.translatable("advancements.speedrunnermod.what_a_wasteland.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .build(exporter, "speedrunnermod:adventure/what_a_wasteland");
        Advancement.Builder.create()
                .parent(speedrunningTime)
                .display(
                        ModItems.IGNEOUS_ROCK,
                        Text.translatable("advancements.speedrunnermod.hardest_rock.title"),
                        Text.translatable("advancements.speedrunnermod.hardest_rock.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.IGNEOUS_ROCK))
                .build(exporter, "speedrunnermod:items/hardest_rock");

        AdvancementEntry eyeOfTheStructures = Advancement.Builder.create()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNERS_EYE,
                        Text.translatable("advancements.speedrunnermod.eye_of_the_structures.title"),
                        Text.translatable("advancements.speedrunnermod.eye_of_the_structures.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNERS_EYE))
                .build(exporter, "speedrunnermod:items/eye_of_the_structures");

        Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.SPEEDRUNNER_BULK,
                        Text.translatable("advancements.speedrunnermod.bulked.title"),
                        Text.translatable("advancements.speedrunnermod.bulked.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_BULK))
                .build(exporter, "speedrunnermod:items/bulked");

        AdvancementEntry quickerPick = Advancement.Builder.create()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_PICKAXE,
                        Text.translatable("advancements.speedrunnermod.quicker_pick.title"),
                        Text.translatable("advancements.speedrunnermod.quicker_pick.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_PICKAXE))
                .build(exporter, "speedrunnermod:items/quicker_pick");

        AdvancementEntry suitedForSpeedrunning = Advancement.Builder.create()
                .parent(quickerPick)
                .display(
                        ModItems.SPEEDRUNNER_CHESTPLATE,
                        Text.translatable("advancements.speedrunnermod.suited_for_speedrunning.title"),
                        Text.translatable("advancements.speedrunnermod.suited_for_speedrunning.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
                .criterion("has_speedrunner_helmet", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_HELMET))
                .criterion("has_speedrunner_chestplate", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_CHESTPLATE))
                .criterion("has_speedrunner_leggings", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_LEGGINGS))
                .criterion("has_speedrunner_boots", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_BOOTS))
                .build(exporter, "speedrunnermod:items/suited_for_speedrunning");

        Advancement.Builder.create()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.GOLDEN_SPEEDRUNNER_HELMET,
                        Text.translatable("advancements.speedrunnermod.better_safe_than_sorry.title"),
                        Text.translatable("advancements.speedrunnermod.better_safe_than_sorry.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
                .criterion("has_golden_speedrunner_helmet", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_SPEEDRUNNER_HELMET))
                .criterion("has_golden_speedrunner_chestplate", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE))
                .criterion("has_golden_speedrunner_leggings", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS))
                .criterion("has_golden_speedrunner_boots", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_SPEEDRUNNER_BOOTS))
                .build(exporter, "speedrunnermod:items/better_safe_than_sorry");

        AdvancementEntry rangedSpeedrunning = Advancement.Builder.create()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_BOW,
                        Text.translatable("advancements.speedrunnermod.ranged_speedrunning.title"),
                        Text.translatable("advancements.speedrunnermod.ranged_speedrunning.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_BOW))
                .build(exporter, "speedrunnermod:items/ranged_speedrunning");

        Advancement.Builder.create()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_CROSSBOW,
                        Text.translatable("advancements.speedrunnermod.speedy_betsy.title"),
                        Text.translatable("advancements.speedrunnermod.speedy_betsy.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_CROSSBOW))
                .build(exporter, "speedrunnermod:items/speedy_betsy");

        AdvancementEntry theEndIsNear = Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ANNUL_EYE,
                        Text.translatable("advancements.speedrunnermod.the_end_is_near.title"),
                        Text.translatable("advancements.speedrunnermod.the_end_is_near.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("used_item", UsedItemCriterion.Conditions.item(itemLookup, ModItems.ANNUL_EYE))
                .build(exporter, "speedrunnermod:items/the_end_is_near");

        AdvancementEntry backToTheSurface = Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_THRUSTER,
                        Text.translatable("advancements.speedrunnermod.back_to_the_surface.title"),
                        Text.translatable("advancements.speedrunnermod.back_to_the_surface.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("used_item", UsedItemCriterion.Conditions.item(itemLookup, ModItems.ENDER_THRUSTER))
                .build(exporter, "speedrunnermod:items/back_to_the_surface");

        AdvancementEntry devilsEye = Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INFERNO_EYE,
                        Text.translatable("advancements.speedrunnermod.devils_eye.title"),
                        Text.translatable("advancements.speedrunnermod.devils_eye.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.INFERNO_EYE))
                .build(exporter, "speedrunnermod:items/devils_eye");

        AdvancementEntry perchAlready = Advancement.Builder.create()
                .parent(theEndIsNear)
                .display(
                        ModItems.DRAGONS_PEARL,
                        Text.translatable("advancements.speedrunnermod.perch_already.title"),
                        Text.translatable("advancements.speedrunnermod.perch_already.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.DRAGONS_PEARL))
                .build(exporter, "speedrunnermod:items/perch_already");

        AdvancementEntry piglinRally = Advancement.Builder.create()
                .parent(devilsEye)
                .display(
                        ModItems.PIGLIN_AWAKENER,
                        Text.translatable("advancements.speedrunnermod.piglin_rally.title"),
                        Text.translatable("advancements.speedrunnermod.piglin_rally.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("used_item", UsedItemCriterion.Conditions.item(itemLookup, ModItems.PIGLIN_AWAKENER))
                .build(exporter, "speedrunnermod:items/piglin_rally");
    }

    /**
     * For the "What A Wasteland!" advancement.
     */
    private static Advancement.Builder requireSpeedrunnersWasteland(Advancement.Builder builder, RegistryWrapper.WrapperLookup registries) {
        RegistryEntryLookup<Biome> registryEntryLookup = registries.getOrThrow(RegistryKeys.BIOME);

        builder.criterion(
                ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY.getValue().toString(),
                TickCriterion.Conditions.createLocation(LocationPredicate.Builder.createBiome(registryEntryLookup.getOrThrow(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY)))
        );

        return builder;
    }
}