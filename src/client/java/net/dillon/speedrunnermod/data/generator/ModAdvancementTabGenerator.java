package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.advancement.TriggeredByItemCriterion;
import net.dillon.speedrunnermod.entity.ModPotions;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.ChangedDimensionCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.OnKilledCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod advancements.
 */
public class ModAdvancementTabGenerator extends FabricAdvancementProvider {

    protected ModAdvancementTabGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> exporter) {
        RegistryEntryLookup<EntityType<?>> entityLookup = wrapperLookup.getOrThrow(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup<Item> itemLookup = wrapperLookup.getOrThrow(RegistryKeys.ITEM);
        AdvancementEntry root = Advancement.Builder.create()
                .display(
                        ModItems.SPEEDRUNNERS_WORKBENCH,
                        Text.translatable("advancements.speedrunnermod.title"),
                        Text.translatable("advancements.speedrunnermod.description"),
                        ofSpeedrunnerMod("gui/advancements/backgrounds/speedrunner"),
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

        Advancement.Builder.create()
                .parent(speedrunningTime)
                .display(
                        ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE,
                        Text.translatable("advancements.speedrunnermod.speedrunning_pro.title"),
                        Text.translatable("advancements.speedrunnermod.speedrunning_pro.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE))
                .build(exporter, "speedrunnermod:items/speedrunning_pro");

        AdvancementEntry whatAWasteland = requireSpeedrunnersWasteland(Advancement.Builder.create(), wrapperLookup)
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_SAPLING,
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
                        ModItems.INVENTORY_PRESERVER,
                        Text.translatable("advancements.speedrunnermod.i_lost_my_stuff.title"),
                        Text.translatable("advancements.speedrunnermod.i_lost_my_stuff.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .criterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.INVENTORY_PRESERVER))
                .build(exporter, "speedrunnermod:items/i_lost_my_stuff");

        Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.SPEEDRUNNER_BULK,
                        Text.translatable("advancements.speedrunnermod.bulky.title"),
                        Text.translatable("advancements.speedrunnermod.bulky.description"),
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
                        ModItems.SPEEDRUNNERS_WORKBENCH,
                        Text.translatable("advancements.speedrunnermod.one_step_ahead.title"),
                        Text.translatable("advancements.speedrunnermod.one_step_ahead.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.OR)
                .criterion("used_workbench", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_WORKBENCH))
                .build(exporter, "speedrunnermod:blocks/one_step_ahead");

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
                .parent(rangedSpeedrunning)
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
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.ANNUL_EYE))
                .build(exporter, "speedrunnermod:items/the_end_is_near");

        AdvancementEntry theEndOfTheMatter = Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_MATTER,
                        Text.translatable("advancements.speedrunnermod.the_end_of_the_matter.title"),
                        Text.translatable("advancements.speedrunnermod.the_end_of_the_matter.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.ENDER_MATTER))
                .build(exporter, "speedrunnermod:items/the_end_of_the_matter");

        Advancement.Builder.create()
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
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.ENDER_THRUSTER))
                .build(exporter, "speedrunnermod:items/back_to_the_surface");

        AdvancementEntry infernalGaze = Advancement.Builder.create()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INFERNO_EYE,
                        Text.translatable("advancements.speedrunnermod.infernal_gaze.title"),
                        Text.translatable("advancements.speedrunnermod.infernal_gaze.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.INFERNO_EYE))
                .build(exporter, "speedrunnermod:items/infernal_gaze");

        Advancement.Builder.create()
                .parent(infernalGaze)
                .display(
                        Items.FIRE_CHARGE,
                        Text.translatable("advancements.speedrunnermod.you_should_add_a_feature.title"),
                        Text.translatable("advancements.speedrunnermod.you_should_add_a_feature.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.FIRE_CHARGE))
                .build(exporter, "speedrunnermod:items/you_should_add_a_feature");

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
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.DRAGONS_PEARL))
                .build(exporter, "speedrunnermod:items/perch_already");

        PotionContentsComponent dragonsAuraEffect = new PotionContentsComponent(ModPotions.DRAGONS_AURA);
        ItemStack stack =  new ItemStack(Items.POTION);
        stack.set(DataComponentTypes.POTION_CONTENTS, dragonsAuraEffect);
        AdvancementEntry dragonsAura = Advancement.Builder.create()
                .parent(theEndOfTheMatter)
                .display(
                        stack,
                        Text.translatable("advancements.speedrunnermod.dragons_aura.title"),
                        Text.translatable("advancements.speedrunnermod.dragons_aura.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("obtain_item", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.POTION))
                .build(exporter, "speedrunnermod:items/dragons_aura");

        Advancement.Builder.create()
                .parent(dragonsAura)
                .display(
                        ModItems.DRAGONS_FIREBALL,
                        Text.translatable("advancements.speedrunnermod.dragons_breath.title"),
                        Text.translatable("advancements.speedrunnermod.dragons_breath.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.DRAGONS_FIREBALL))
                .build(exporter, "speedrunnermod:items/dragons_breath");

        AdvancementEntry piglinRally = Advancement.Builder.create()
                .parent(infernalGaze)
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
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.PIGLIN_AWAKENER))
                .build(exporter, "speedrunnermod:items/piglin_rally");

        Advancement.Builder.create()
                .parent(piglinRally)
                .display(
                        ModItems.BLAZE_SPOTTER,
                        Text.translatable("advancements.speedrunnermod.the_blazez_awaitz.title"),
                        Text.translatable("advancements.speedrunnermod.the_blazez_awaitz.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.BLAZE_SPOTTER))
                .build(exporter, "speedrunnermod:items/the_blazez_awaitz");

        AdvancementEntry goliath = Advancement.Builder.create()
                .parent(perchAlready)
                .display(
                        Items.ZOMBIE_HEAD,
                        Text.translatable("advancements.speedrunnermod.goliath.title"),
                        Text.translatable("advancements.speedrunnermod.goliath.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .criterion("killed_goliath", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.GIANT)))
                .rewards(AdvancementRewards.Builder.experience(200))
                .build(exporter,"speedrunnermod:adventure/goliath");

        Advancement.Builder.create()
                .parent(goliath)
                .display(
                        ModItems.RAID_ERADICATOR,
                        Text.translatable("advancements.speedrunnermod.the_purge.title"),
                        Text.translatable("advancements.speedrunnermod.the_purge.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.RAID_ERADICATOR))
                .build(exporter, "speedrunnermod:items/the_purge");

        AdvancementEntry oneHitOneKill = Advancement.Builder.create()
                .parent(perchAlready)
                .display(
                        ModItems.DRAGONS_SWORD,
                        Text.translatable("advancements.speedrunnermod.one_hit_one_kill.title"),
                        Text.translatable("advancements.speedrunnermod.one_hit_one_kill.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("used_dragons_sword", InventoryChangedCriterion.Conditions.items(ModItems.DRAGONS_SWORD))
                .rewards(AdvancementRewards.Builder.experience(100))
                .build(exporter, "speedrunnermod:items/one_hit_one_kill");

        AdvancementEntry yesTheEnd = Advancement.Builder.create()
                .parent(oneHitOneKill)
                .display(
                        Items.DRAGON_HEAD,
                        Text.translatable("advancements.speedrunnermod.yes_the_end.title"),
                        Text.translatable("advancements.speedrunnermod.yes_the_end.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                )
                .criterion("exited_end", ChangedDimensionCriterion.Conditions.create(World.END, World.OVERWORLD))
                .build(exporter, "speedrunnermod:adventure/exited_end");

        Advancement.Builder.create()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.INFINI_PEARL,
                        Text.translatable("advancements.speedrunnermod.to_infini_and_beyond.title"),
                        Text.translatable("advancements.speedrunnermod.to_infini_and_beyond.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.INFINI_PEARL))
                .rewards(AdvancementRewards.Builder.experience(300))
                .build(exporter, "speedrunnermod:items/to_infini_and_beyond");

        Advancement.Builder.create()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.SPEEDRUNNERS_TOTEM,
                        Text.translatable("advancements.speedrunnermod.immortal.title"),
                        Text.translatable("advancements.speedrunnermod.immortal.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_TOTEM))
                .rewards(AdvancementRewards.Builder.experience(500))
                .build(exporter, "speedrunnermod:adventure/immortal");

        AdvancementEntry killWarden = Advancement.Builder.create()
                .parent(yesTheEnd)
                .display(
                        Items.SCULK_SHRIEKER,
                        Text.translatable("advancements.speedrunnermod.deep_dark.title"),
                        Text.translatable("advancements.speedrunnermod.deep_dark.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("killed_warden", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.WARDEN)))
                .rewards(AdvancementRewards.Builder.experience(250))
                .build(exporter, "speedrunnermod:adventure/deep_dark");

        Advancement.Builder.create()
                .parent(killWarden)
                .display(
                        Items.PLAYER_HEAD,
                        Text.translatable("advancements.speedrunnermod.dominion.title"),
                        Text.translatable("advancements.speedrunnermod.dominion.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("killed_dragon", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.ENDER_DRAGON)))
                .criterion("killed_elder_guardian", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.GIANT)))
                .criterion("killed_wither", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.WITHER)))
                .criterion("killed_warden", OnKilledCriterion.Conditions.createPlayerKilledEntity(
                        EntityPredicate.Builder.create()
                                .type(entityLookup, EntityType.WARDEN)))
                .rewards(AdvancementRewards.Builder.experience(1000))
                .build(exporter, "speedrunnermod:adventure/dominion");

        Advancement.Builder.create()
                .parent(quickerPick)
                .display(
                        Items.DIAMOND_SWORD,
                        Text.translatable("advancements.speedrunnermod.sword_collector.title"),
                        Text.translatable("advancements.speedrunnermod.sword_collector.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("has_wood_sword", InventoryChangedCriterion.Conditions.items(Items.WOODEN_SWORD))
                .criterion("has_stone_sword", InventoryChangedCriterion.Conditions.items(Items.STONE_SWORD))
                .criterion("has_golden_sword", InventoryChangedCriterion.Conditions.items(Items.GOLDEN_SWORD))
                .criterion("has_diamond_sword", InventoryChangedCriterion.Conditions.items(Items.DIAMOND_SWORD))
                .criterion("has_netherite_sword", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_SWORD))
                .criterion("has_speedrunner_sword", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_SWORD))
                .criterion("has_golden_speedrunner_sword", InventoryChangedCriterion.Conditions.items(ModItems.GOLDEN_SPEEDRUNNER_SWORD))
                .criterion("has_dragons_sword", InventoryChangedCriterion.Conditions.items(ModItems.DRAGONS_SWORD))
                .rewards(AdvancementRewards.Builder.experience(50))
                .build(exporter, "speedrunnermod:items/sword_collector");

        Advancement.Builder.create()
                .parent(whatAWasteland)
                .display(
                        Items.OAK_LOG,
                        Text.translatable("advancements.speedrunnermod.lumberjack.title"),
                        Text.translatable("advancements.speedrunnermod.lumberjack.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        true
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("has_oak_log", InventoryChangedCriterion.Conditions.items(Items.OAK_LOG))
                .criterion("has_birch_log", InventoryChangedCriterion.Conditions.items(Items.BIRCH_LOG))
                .criterion("has_acacia_log", InventoryChangedCriterion.Conditions.items(Items.ACACIA_LOG))
                .criterion("has_cherry_log", InventoryChangedCriterion.Conditions.items(Items.CHERRY_LOG))
                .criterion("has_jungle_log", InventoryChangedCriterion.Conditions.items(Items.JUNGLE_LOG))
                .criterion("has_dark_oak_log", InventoryChangedCriterion.Conditions.items(Items.DARK_OAK_LOG))
                .criterion("has_mangrove_log", InventoryChangedCriterion.Conditions.items(Items.MANGROVE_LOG))
                .criterion("has_pale_oak_log", InventoryChangedCriterion.Conditions.items(Items.PALE_OAK_LOG))
                .criterion("has_spruce_log", InventoryChangedCriterion.Conditions.items(Items.SPRUCE_LOG))
                .criterion("has_speedrunner_log", InventoryChangedCriterion.Conditions.items(ModItems.SPEEDRUNNER_LOG))
                .criterion("has_dead_speedrunner_log", InventoryChangedCriterion.Conditions.items(ModItems.DEAD_SPEEDRUNNER_LOG))
                .rewards(AdvancementRewards.Builder.experience(25))
                .build(exporter, "speedrunnermod:items/lumberjack");

        AdvancementEntry shepherd = Advancement.Builder.create()
                .parent(speedrunningTime)
                .display(
                        Items.WHITE_WOOL,
                        Text.translatable("advancements.speedrunnermod.shepherd.title"),
                        Text.translatable("advancements.speedrunnermod.shepherd.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        true
                )
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("has_white_wool", InventoryChangedCriterion.Conditions.items(Items.WHITE_WOOL))
                .criterion("has_orange_wool", InventoryChangedCriterion.Conditions.items(Items.ORANGE_WOOL))
                .criterion("has_blue_wool", InventoryChangedCriterion.Conditions.items(Items.BLUE_WOOL))
                .criterion("has_yellow_wool", InventoryChangedCriterion.Conditions.items(Items.YELLOW_WOOL))
                .criterion("has_cyan_wool", InventoryChangedCriterion.Conditions.items(Items.CYAN_WOOL))
                .criterion("has_green_wool", InventoryChangedCriterion.Conditions.items(Items.GREEN_WOOL))
                .criterion("has_lime_wool", InventoryChangedCriterion.Conditions.items(Items.LIME_WOOL))
                .criterion("has_light_blue_wool", InventoryChangedCriterion.Conditions.items(Items.LIGHT_BLUE_WOOL))
                .criterion("has_gray_wool", InventoryChangedCriterion.Conditions.items(Items.GRAY_WOOL))
                .criterion("has_light_gray_wool", InventoryChangedCriterion.Conditions.items(Items.LIGHT_GRAY_WOOL))
                .criterion("has_black_Wool", InventoryChangedCriterion.Conditions.items(Items.BLACK_WOOL))
                .criterion("has_red_wool", InventoryChangedCriterion.Conditions.items(Items.RED_WOOL))
                .criterion("has_brown_wool", InventoryChangedCriterion.Conditions.items(Items.BROWN_WOOL))
                .criterion("has_magenta_wool", InventoryChangedCriterion.Conditions.items(Items.MAGENTA_WOOL))
                .criterion("has_purple_wool", InventoryChangedCriterion.Conditions.items(Items.PURPLE_WOOL))
                .criterion("has_pink_wool", InventoryChangedCriterion.Conditions.items(Items.PINK_WOOL))
                .rewards(AdvancementRewards.Builder.experience(50))
                .build(exporter, "speedrunnermod:items/shepherd");

        Advancement.Builder.create()
                .parent(shepherd)
                .display(
                        Items.LIME_WOOL,
                        Text.translatable("advancements.speedrunnermod.expert_shepherd.title"),
                        Text.translatable("advancements.speedrunnermod.expert_shepherd.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        true
                )
                .criterion("has_stack_of_lime_wool", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.LIME_WOOL))
                .rewards(AdvancementRewards.Builder.experience(25))
                .build(exporter, "speedrunnermod:items/expert_shepherd");
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