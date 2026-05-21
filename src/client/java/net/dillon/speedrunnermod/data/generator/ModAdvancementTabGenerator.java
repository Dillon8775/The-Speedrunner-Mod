package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.advancement.TriggeredByItemCriterion;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod advancements.
 */
public class ModAdvancementTabGenerator extends FabricAdvancementProvider {

    protected ModAdvancementTabGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> exporter) {
        HolderGetter<EntityType<?>> entityLookup = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderGetter<Item> itemLookup = wrapperLookup.lookupOrThrow(Registries.ITEM);
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.SPEEDRUNNERS_WORKBENCH,
                        Component.translatable("advancements.speedrunnermod.title"),
                        Component.translatable("advancements.speedrunnermod.description"),
                        ofSpeedrunnerMod("gui/advancements/backgrounds/speedrunner"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("has_crafting_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .save(exporter, "speedrunnermod:root");

        AdvancementHolder speedrunningTime = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.SPEEDRUNNER_INGOT,
                        Component.translatable("advancements.speedrunnermod.speedrunning_time.title"),
                        Component.translatable("advancements.speedrunnermod.speedrunning_time.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_INGOT))
                .save(exporter, "speedrunnermod:items/speedrunning_time");

        Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE,
                        Component.translatable("advancements.speedrunnermod.speedrunning_pro.title"),
                        Component.translatable("advancements.speedrunnermod.speedrunning_pro.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE))
                .save(exporter, "speedrunnermod:items/speedrunning_pro");

        AdvancementHolder whatAWasteland = requireSpeedrunnersWasteland(Advancement.Builder.advancement(), wrapperLookup)
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_SAPLING,
                        Component.translatable("advancements.speedrunnermod.what_a_wasteland.title"),
                        Component.translatable("advancements.speedrunnermod.what_a_wasteland.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .save(exporter, "speedrunnermod:adventure/what_a_wasteland");

        Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.IGNEOUS_ROCK,
                        Component.translatable("advancements.speedrunnermod.hardest_rock.title"),
                        Component.translatable("advancements.speedrunnermod.hardest_rock.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IGNEOUS_ROCK))
                .save(exporter, "speedrunnermod:items/hardest_rock");

        AdvancementHolder eyeOfTheStructures = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNERS_EYE,
                        Component.translatable("advancements.speedrunnermod.eye_of_the_structures.title"),
                        Component.translatable("advancements.speedrunnermod.eye_of_the_structures.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNERS_EYE))
                .save(exporter, "speedrunnermod:items/eye_of_the_structures");

        Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INVENTORY_PRESERVER,
                        Component.translatable("advancements.speedrunnermod.i_lost_my_stuff.title"),
                        Component.translatable("advancements.speedrunnermod.i_lost_my_stuff.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.INVENTORY_PRESERVER))
                .save(exporter, "speedrunnermod:items/i_lost_my_stuff");

        Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.SPEEDRUNNER_BULK,
                        Component.translatable("advancements.speedrunnermod.bulky.title"),
                        Component.translatable("advancements.speedrunnermod.bulky.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_BULK))
                .save(exporter, "speedrunnermod:items/bulked");

        AdvancementHolder quickerPick = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_PICKAXE,
                        Component.translatable("advancements.speedrunnermod.quicker_pick.title"),
                        Component.translatable("advancements.speedrunnermod.quicker_pick.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_PICKAXE))
                .save(exporter, "speedrunnermod:items/quicker_pick");

        AdvancementHolder suitedForSpeedrunning = Advancement.Builder.advancement()
                .parent(quickerPick)
                .display(
                        ModItems.SPEEDRUNNER_CHESTPLATE,
                        Component.translatable("advancements.speedrunnermod.suited_for_speedrunning.title"),
                        Component.translatable("advancements.speedrunnermod.suited_for_speedrunning.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("has_speedrunner_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_HELMET))
                .addCriterion("has_speedrunner_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_CHESTPLATE))
                .addCriterion("has_speedrunner_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_LEGGINGS))
                .addCriterion("has_speedrunner_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_BOOTS))
                .save(exporter, "speedrunnermod:items/suited_for_speedrunning");

        Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNERS_WORKBENCH,
                        Component.translatable("advancements.speedrunnermod.one_step_ahead.title"),
                        Component.translatable("advancements.speedrunnermod.one_step_ahead.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("used_workbench", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_WORKBENCH))
                .save(exporter, "speedrunnermod:blocks/one_step_ahead");

        Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.GOLDEN_SPEEDRUNNER_HELMET,
                        Component.translatable("advancements.speedrunnermod.better_safe_than_sorry.title"),
                        Component.translatable("advancements.speedrunnermod.better_safe_than_sorry.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("has_golden_speedrunner_helmet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_HELMET))
                .addCriterion("has_golden_speedrunner_chestplate", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE))
                .addCriterion("has_golden_speedrunner_leggings", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS))
                .addCriterion("has_golden_speedrunner_boots", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_BOOTS))
                .save(exporter, "speedrunnermod:items/better_safe_than_sorry");

        AdvancementHolder rangedSpeedrunning = Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_BOW,
                        Component.translatable("advancements.speedrunnermod.ranged_speedrunning.title"),
                        Component.translatable("advancements.speedrunnermod.ranged_speedrunning.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_BOW))
                .save(exporter, "speedrunnermod:items/ranged_speedrunning");

        Advancement.Builder.advancement()
                .parent(rangedSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_CROSSBOW,
                        Component.translatable("advancements.speedrunnermod.speedy_betsy.title"),
                        Component.translatable("advancements.speedrunnermod.speedy_betsy.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_CROSSBOW))
                .save(exporter, "speedrunnermod:items/speedy_betsy");

        AdvancementHolder theEndIsNear = Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ANNUL_EYE,
                        Component.translatable("advancements.speedrunnermod.the_end_is_near.title"),
                        Component.translatable("advancements.speedrunnermod.the_end_is_near.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.ANNUL_EYE))
                .save(exporter, "speedrunnermod:items/the_end_is_near");

        AdvancementHolder theEndOfTheMatter = Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_MATTER,
                        Component.translatable("advancements.speedrunnermod.the_end_of_the_matter.title"),
                        Component.translatable("advancements.speedrunnermod.the_end_of_the_matter.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENDER_MATTER))
                .save(exporter, "speedrunnermod:items/the_end_of_the_matter");

        Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_THRUSTER,
                        Component.translatable("advancements.speedrunnermod.back_to_the_surface.title"),
                        Component.translatable("advancements.speedrunnermod.back_to_the_surface.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.ENDER_THRUSTER))
                .save(exporter, "speedrunnermod:items/back_to_the_surface");

        AdvancementHolder infernalGaze = Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INFERNO_EYE,
                        Component.translatable("advancements.speedrunnermod.infernal_gaze.title"),
                        Component.translatable("advancements.speedrunnermod.infernal_gaze.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INFERNO_EYE))
                .save(exporter, "speedrunnermod:items/infernal_gaze");

        Advancement.Builder.advancement()
                .parent(infernalGaze)
                .display(
                        Items.FIRE_CHARGE,
                        Component.translatable("advancements.speedrunnermod.you_should_add_a_feature.title"),
                        Component.translatable("advancements.speedrunnermod.you_should_add_a_feature.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.FIRE_CHARGE))
                .save(exporter, "speedrunnermod:items/you_should_add_a_feature");

        AdvancementHolder perchAlready = Advancement.Builder.advancement()
                .parent(theEndIsNear)
                .display(
                        ModItems.DRAGONS_PEARL,
                        Component.translatable("advancements.speedrunnermod.perch_already.title"),
                        Component.translatable("advancements.speedrunnermod.perch_already.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.DRAGONS_PEARL))
                .save(exporter, "speedrunnermod:items/perch_already");

        PotionContents dragonsAuraEffect = new PotionContents(ModPotions.DRAGONS_AURA);
        ItemStackTemplate dragonsAuraPotion = new ItemStackTemplate(
                Items.POTION.builtInRegistryHolder(),
                1,
                DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, dragonsAuraEffect).build()
        );
        AdvancementHolder dragonsAura = Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        dragonsAuraPotion,
                        Component.translatable("advancements.speedrunnermod.dragons_aura.title"),
                        Component.translatable("advancements.speedrunnermod.dragons_aura.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_item", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.POTION))
                .save(exporter, "speedrunnermod:items/dragons_aura");

        Advancement.Builder.advancement()
                .parent(dragonsAura)
                .display(
                        ModItems.DRAGONS_FIREBALL,
                        Component.translatable("advancements.speedrunnermod.dragons_breath.title"),
                        Component.translatable("advancements.speedrunnermod.dragons_breath.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGONS_FIREBALL))
                .save(exporter, "speedrunnermod:items/dragons_breath");

        AdvancementHolder piglinRally = Advancement.Builder.advancement()
                .parent(infernalGaze)
                .display(
                        ModItems.PIGLIN_AWAKENER,
                        Component.translatable("advancements.speedrunnermod.piglin_rally.title"),
                        Component.translatable("advancements.speedrunnermod.piglin_rally.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.PIGLIN_AWAKENER))
                .save(exporter, "speedrunnermod:items/piglin_rally");

        Advancement.Builder.advancement()
                .parent(piglinRally)
                .display(
                        ModItems.BLAZE_SPOTTER,
                        Component.translatable("advancements.speedrunnermod.the_blazez_awaitz.title"),
                        Component.translatable("advancements.speedrunnermod.the_blazez_awaitz.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.BLAZE_SPOTTER))
                .save(exporter, "speedrunnermod:items/the_blazez_awaitz");

        AdvancementHolder davidAndGoliath = Advancement.Builder.advancement()
                .parent(perchAlready)
                .display(
                        Items.ZOMBIE_HEAD,
                        Component.translatable("advancements.speedrunnermod.david_and_goliath.title"),
                        Component.translatable("advancements.speedrunnermod.david_and_goliath.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_goliath", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.GIANT)))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(200))
                .save(exporter,"speedrunnermod:adventure/david_and_goliath");

        Advancement.Builder.advancement()
                .parent(davidAndGoliath)
                .display(
                        ModItems.RAID_ERADICATOR,
                        Component.translatable("advancements.speedrunnermod.the_purge.title"),
                        Component.translatable("advancements.speedrunnermod.the_purge.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.RAID_ERADICATOR))
                .save(exporter, "speedrunnermod:items/the_purge");

        AdvancementHolder oneHitOneKill = Advancement.Builder.advancement()
                .parent(perchAlready)
                .display(
                        ModItems.DRAGONS_SWORD,
                        Component.translatable("advancements.speedrunnermod.one_hit_one_kill.title"),
                        Component.translatable("advancements.speedrunnermod.one_hit_one_kill.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("used_dragons_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGONS_SWORD))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(100))
                .save(exporter, "speedrunnermod:items/one_hit_one_kill");

        AdvancementHolder yesTheEnd = Advancement.Builder.advancement()
                .parent(oneHitOneKill)
                .display(
                        Items.DRAGON_HEAD,
                        Component.translatable("advancements.speedrunnermod.yes_the_end.title"),
                        Component.translatable("advancements.speedrunnermod.yes_the_end.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("exited_end", net.minecraft.advancements.criterion.ChangeDimensionTrigger.TriggerInstance.changedDimension(Level.END, Level.OVERWORLD))
                .save(exporter, "speedrunnermod:adventure/exited_end");

        Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.INFINI_PEARL,
                        Component.translatable("advancements.speedrunnermod.to_infini_and_beyond.title"),
                        Component.translatable("advancements.speedrunnermod.to_infini_and_beyond.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INFINI_PEARL))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(300))
                .save(exporter, "speedrunnermod:items/to_infini_and_beyond");

        Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.SPEEDRUNNERS_TOTEM,
                        Component.translatable("advancements.speedrunnermod.immortal.title"),
                        Component.translatable("advancements.speedrunnermod.immortal.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", TriggeredByItemCriterion.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_TOTEM))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(500))
                .save(exporter, "speedrunnermod:adventure/immortal");

        AdvancementHolder killWarden = Advancement.Builder.advancement()
                .parent(yesTheEnd)
                .display(
                        Items.SCULK_SHRIEKER,
                        Component.translatable("advancements.speedrunnermod.deep_dark.title"),
                        Component.translatable("advancements.speedrunnermod.deep_dark.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("killed_warden", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.WARDEN)))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(250))
                .save(exporter, "speedrunnermod:adventure/deep_dark");

        Advancement.Builder.advancement()
                .parent(killWarden)
                .display(
                        Items.PLAYER_HEAD,
                        Component.translatable("advancements.speedrunnermod.dominion.title"),
                        Component.translatable("advancements.speedrunnermod.dominion.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("killed_dragon", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.ENDER_DRAGON)))
                .addCriterion("killed_elder_guardian", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.GIANT)))
                .addCriterion("killed_wither", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.WITHER)))
                .addCriterion("killed_warden", net.minecraft.advancements.criterion.KilledTrigger.TriggerInstance.playerKilledEntity(
                        net.minecraft.advancements.criterion.EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityType.WARDEN)))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(1000))
                .save(exporter, "speedrunnermod:adventure/dominion");

        Advancement.Builder.advancement()
                .parent(quickerPick)
                .display(
                        Items.DIAMOND_SWORD,
                        Component.translatable("advancements.speedrunnermod.sword_collector.title"),
                        Component.translatable("advancements.speedrunnermod.sword_collector.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_wood_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOODEN_SWORD))
                .addCriterion("has_stone_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_SWORD))
                .addCriterion("has_golden_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_SWORD))
                .addCriterion("has_diamond_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_SWORD))
                .addCriterion("has_netherite_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SWORD))
                .addCriterion("has_speedrunner_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_SWORD))
                .addCriterion("has_golden_speedrunner_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_SWORD))
                .addCriterion("has_dragons_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGONS_SWORD))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(50))
                .save(exporter, "speedrunnermod:items/sword_collector");

        Advancement.Builder.advancement()
                .parent(whatAWasteland)
                .display(
                        Items.OAK_LOG,
                        Component.translatable("advancements.speedrunnermod.lumberjack.title"),
                        Component.translatable("advancements.speedrunnermod.lumberjack.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_oak_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_LOG))
                .addCriterion("has_birch_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BIRCH_LOG))
                .addCriterion("has_acacia_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ACACIA_LOG))
                .addCriterion("has_cherry_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CHERRY_LOG))
                .addCriterion("has_jungle_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUNGLE_LOG))
                .addCriterion("has_dark_oak_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DARK_OAK_LOG))
                .addCriterion("has_mangrove_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MANGROVE_LOG))
                .addCriterion("has_pale_oak_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PALE_OAK_LOG))
                .addCriterion("has_spruce_log", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SPRUCE_LOG))
                .addCriterion("has_speedrunner_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_LOG))
                .addCriterion("has_dead_speedrunner_log", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DEAD_SPEEDRUNNER_LOG))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(25))
                .save(exporter, "speedrunnermod:items/lumberjack");

        AdvancementHolder shepherd = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        Items.WHITE_WOOL,
                        Component.translatable("advancements.speedrunnermod.shepherd.title"),
                        Component.translatable("advancements.speedrunnermod.shepherd.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                .addCriterion("has_orange_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ORANGE_WOOL))
                .addCriterion("has_blue_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLUE_WOOL))
                .addCriterion("has_yellow_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.YELLOW_WOOL))
                .addCriterion("has_cyan_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CYAN_WOOL))
                .addCriterion("has_green_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GREEN_WOOL))
                .addCriterion("has_lime_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIME_WOOL))
                .addCriterion("has_light_blue_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIGHT_BLUE_WOOL))
                .addCriterion("has_gray_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAY_WOOL))
                .addCriterion("has_light_gray_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LIGHT_GRAY_WOOL))
                .addCriterion("has_black_Wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLACK_WOOL))
                .addCriterion("has_red_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_WOOL))
                .addCriterion("has_brown_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BROWN_WOOL))
                .addCriterion("has_magenta_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MAGENTA_WOOL))
                .addCriterion("has_purple_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PURPLE_WOOL))
                .addCriterion("has_pink_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.PINK_WOOL))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(50))
                .save(exporter, "speedrunnermod:items/shepherd");

        Advancement.Builder.advancement()
                .parent(shepherd)
                .display(
                        Items.LIME_WOOL,
                        Component.translatable("advancements.speedrunnermod.expert_shepherd.title"),
                        Component.translatable("advancements.speedrunnermod.expert_shepherd.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("has_stack_of_lime_wool", TriggeredByItemCriterion.Conditions.item(itemLookup, Items.LIME_WOOL))
                .rewards(net.minecraft.advancements.AdvancementRewards.Builder.experience(25))
                .save(exporter, "speedrunnermod:items/expert_shepherd");
    }

    /**
     * For the "What A Wasteland!" advancement.
     */
    private static Advancement.Builder requireSpeedrunnersWasteland(Advancement.Builder builder, HolderLookup.Provider registries) {
        HolderGetter<Biome> registryEntryLookup = registries.lookupOrThrow(Registries.BIOME);

        builder.addCriterion(
                ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY.identifier().toString(),
                net.minecraft.advancements.criterion.PlayerTrigger.TriggerInstance.located(net.minecraft.advancements.criterion.LocationPredicate.Builder.inBiome(registryEntryLookup.getOrThrow(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY)))
        );

        return builder;
    }
}
