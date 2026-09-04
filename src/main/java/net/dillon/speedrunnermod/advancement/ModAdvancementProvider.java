package net.dillon.speedrunnermod.advancement;

import net.dillon.speedrunnermod.component.ModPotions;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.predicates.*;
import net.minecraft.advancements.predicates.entity.EntityEquipmentPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.ChangeDimensionTrigger;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.KilledTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod advancements.
 */
public class ModAdvancementProvider extends FabricAdvancementProvider {

    public ModAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider wrapperLookup, Consumer<AdvancementHolder> exporter) {
        HolderGetter<EntityType<?>> entityLookup = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderGetter<Item> itemLookup = wrapperLookup.lookupOrThrow(Registries.ITEM);
        HolderGetter<DamageType> damageType = wrapperLookup.lookupOrThrow(Registries.DAMAGE_TYPE);
        AdvancementHolder root = Advancement.Builder.advancement()
                .rootDisplay(
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
                .save(exporter, ofSpeedrunnerMod("root"));

        AdvancementHolder speedrunningTime = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.SPEEDRUNNER_INGOT,
                        Component.translatable("advancements.speedrunnermod.speedrunning_time.title"),
                        Component.translatable("advancements.speedrunnermod.speedrunning_time.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_INGOT))
                .save(exporter, ofItemAdvancement("speedrunning_time"));

        Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE,
                        Component.translatable("advancements.speedrunnermod.speedrunning_pro.title"),
                        Component.translatable("advancements.speedrunnermod.speedrunning_pro.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE))
                .save(exporter, ofItemAdvancement("speedrunning_pro"));

        requireSpeedrunnersWasteland(Advancement.Builder.advancement(), wrapperLookup)
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_SAPLING,
                        Component.translatable("advancements.speedrunnermod.what_a_wasteland.title"),
                        Component.translatable("advancements.speedrunnermod.what_a_wasteland.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .save(exporter, ofItemAdvancement("what_a_wasteland"));

        AdvancementHolder hardestRock = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.IGNEOUS_ROCK,
                        Component.translatable("advancements.speedrunnermod.hardest_rock.title"),
                        Component.translatable("advancements.speedrunnermod.hardest_rock.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IGNEOUS_ROCK))
                .save(exporter, ofItemAdvancement("hardest_rock"));

        AdvancementHolder eyeOfTheStructures = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNERS_EYE,
                        Component.translatable("advancements.speedrunnermod.eye_of_the_structures.title"),
                        Component.translatable("advancements.speedrunnermod.eye_of_the_structures.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNERS_EYE))
                .save(exporter, ofItemAdvancement("eye_of_the_structures"));

        Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INVENTORY_PRESERVER,
                        Component.translatable("advancements.speedrunnermod.i_lost_my_stuff.title"),
                        Component.translatable("advancements.speedrunnermod.i_lost_my_stuff.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("has_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.INVENTORY_PRESERVER))
                .save(exporter, ofItemAdvancement("i_lost_my_stuff"));

        Advancement.Builder.advancement()
                .parent(hardestRock)
                .display(
                        ModItems.SPEEDRUNNER_BULK,
                        Component.translatable("advancements.speedrunnermod.bulky.title"),
                        Component.translatable("advancements.speedrunnermod.bulky.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_BULK))
                .save(exporter, ofItemAdvancement("bulked"));

        AdvancementHolder quickerPick = Advancement.Builder.advancement()
                .parent(speedrunningTime)
                .display(
                        ModItems.SPEEDRUNNER_PICKAXE,
                        Component.translatable("advancements.speedrunnermod.quicker_pick.title"),
                        Component.translatable("advancements.speedrunnermod.quicker_pick.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_PICKAXE))
                .save(exporter, ofItemAdvancement("quicker_pick"));

        AdvancementHolder suitedForSpeedrunning = Advancement.Builder.advancement()
                .parent(quickerPick)
                .display(
                        ModItems.SPEEDRUNNER_CHESTPLATE,
                        Component.translatable("advancements.speedrunnermod.suited_for_speedrunning.title"),
                        Component.translatable("advancements.speedrunnermod.suited_for_speedrunning.description"),
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
                .save(exporter, ofItemAdvancement("suited_for_speedrunning"));

        AdvancementHolder oneStepAhead = Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNERS_WORKBENCH,
                        Component.translatable("advancements.speedrunnermod.one_step_ahead.title"),
                        Component.translatable("advancements.speedrunnermod.one_step_ahead.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("used_workbench", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_WORKBENCH))
                .save(exporter, ofBlockAdvancement("one_step_ahead"));

        Advancement.Builder.advancement()
                .parent(oneStepAhead)
                .display(
                        Items.ENCHANTED_BOOK,
                        Component.translatable("advancements.speedrunnermod.speedy.title"),
                        Component.translatable("advancements.speedrunnermod.speedy.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtained_dash_enchantment", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.SPEEDRUNNER_LOG))
                .save(exporter, ofEnchantmentAdvancement("speedy"));

        Advancement.Builder.advancement()
                .parent(oneStepAhead)
                .display(
                        Items.ENCHANTED_BOOK,
                        Component.translatable("advancements.speedrunnermod.that_was_fast.title"),
                        Component.translatable("advancements.speedrunnermod.that_was_fast.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtained_cooldown_enchantment", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.SPEEDRUNNER_WOOD))
                .save(exporter, ofEnchantmentAdvancement("that_was_fast"));

        Advancement.Builder.advancement()
                .parent(oneStepAhead)
                .display(
                        Items.ENCHANTED_BOOK,
                        Component.translatable("advancements.speedrunnermod.withers_secret.title"),
                        Component.translatable("advancements.speedrunnermod.withers_secret.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("obtained_withered_enchantment", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.DEAD_SPEEDRUNNER_LOG))
                .save(exporter, ofEnchantmentAdvancement("wither_glore"));

        AdvancementHolder betterSafeThanSorry = Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.GOLDEN_SPEEDRUNNER_BOOTS,
                        Component.translatable("advancements.speedrunnermod.better_safe_than_sorry.title"),
                        Component.translatable("advancements.speedrunnermod.better_safe_than_sorry.description"),
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
                .save(exporter, ofItemAdvancement("better_safe_than_sorry"));

        AdvancementHolder speedyGhast = Advancement.Builder.advancement()
                .parent(betterSafeThanSorry)
                .display(
                        ModItems.SPEEDRUNNER_HARNESS,
                        Component.translatable("advancements.speedrunnermod.speedy_ghast.title"),
                        Component.translatable("advancements.speedrunnermod.speedy_ghast.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("has_speedrunner_harness", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_HARNESS))
                .addCriterion("has_golden_speedrunner_harness", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_HARNESS))
                .save(exporter, ofItemAdvancement("speedy_harness"));

        Advancement.Builder.advancement()
                .parent(speedyGhast)
                .display(
                        ModItems.SPEEDRUNNER_NAUTILUS_ARMOR,
                        Component.translatable("advancements.speedrunnermod.speedy_slosh.title"),
                        Component.translatable("advancements.speedrunnermod.speedy_slosh.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("has_speedrunner_nautilus", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_NAUTILUS_ARMOR))
                .addCriterion("has_golden_speedrunner_nautilus", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR))
                .save(exporter, ofItemAdvancement("speedy_slosh"));

        AdvancementHolder rangedSpeedrunning = Advancement.Builder.advancement()
                .parent(suitedForSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_BOW,
                        Component.translatable("advancements.speedrunnermod.ranged_speedrunning.title"),
                        Component.translatable("advancements.speedrunnermod.ranged_speedrunning.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPEEDRUNNER_BOW))
                .save(exporter, ofItemAdvancement("ranged_speedrunning"));

        Advancement.Builder.advancement()
                .parent(rangedSpeedrunning)
                .display(
                        ModItems.SPEEDRUNNER_CROSSBOW,
                        Component.translatable("advancements.speedrunnermod.speedy_betsy.title"),
                        Component.translatable("advancements.speedrunnermod.speedy_betsy.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.SPEEDRUNNER_CROSSBOW))
                .save(exporter, ofItemAdvancement("speedy_betsy"));

        AdvancementHolder theEndOfTheMatter = Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_MATTER,
                        Component.translatable("advancements.speedrunnermod.the_end_of_the_matter.title"),
                        Component.translatable("advancements.speedrunnermod.the_end_of_the_matter.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENDER_MATTER))
                .save(exporter, ofItemAdvancement("the_end_of_the_matter"));

        AdvancementHolder theEndIsNear = Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.ANNUL_EYE,
                        Component.translatable("advancements.speedrunnermod.the_end_is_near.title"),
                        Component.translatable("advancements.speedrunnermod.the_end_is_near.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.ANNUL_EYE))
                .save(exporter, ofItemAdvancement("the_end_is_near"));

        Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.ENDER_THRUSTER,
                        Component.translatable("advancements.speedrunnermod.back_to_the_surface.title"),
                        Component.translatable("advancements.speedrunnermod.back_to_the_surface.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.ENDER_THRUSTER))
                .save(exporter, ofItemAdvancement("back_to_the_surface"));

        AdvancementHolder infernalGaze = Advancement.Builder.advancement()
                .parent(eyeOfTheStructures)
                .display(
                        ModItems.INFERNO_EYE,
                        Component.translatable("advancements.speedrunnermod.infernal_gaze.title"),
                        Component.translatable("advancements.speedrunnermod.infernal_gaze.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INFERNO_EYE))
                .save(exporter, ofItemAdvancement("infernal_gaze"));

        AdvancementHolder youShouldAddAFeature = Advancement.Builder.advancement()
                .parent(infernalGaze)
                .display(
                        Items.FIRE_CHARGE,
                        Component.translatable("advancements.speedrunnermod.you_should_add_a_feature.title"),
                        Component.translatable("advancements.speedrunnermod.you_should_add_a_feature.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", ItemLikeTrigger.Conditions.item(itemLookup, Items.FIRE_CHARGE))
                .save(exporter, ofItemAdvancement("you_should_add_a_feature"));

        Advancement.Builder.advancement()
                .parent(youShouldAddAFeature)
                .display(
                        Items.ZOMBIE_SPAWN_EGG,
                        Component.translatable("advancements.speedrunnermod.spare_me.title"),
                        Component.translatable("advancements.speedrunnermod.spare_me.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "killed_zombielike_holding_fireball",
                        KilledTrigger.TriggerInstance.playerKilledEntity(
                                EntityPredicate.Builder.entity()
                                        .of(entityLookup, ModEntityTypeTags.SPARE_ME_ADVANCEMENT_MOBS)
                                        .equipment(
                                                EntityEquipmentPredicate.Builder.equipment()
                                                        .mainhand(
                                                                ItemPredicate.Builder.item()
                                                                        .of(itemLookup, Items.FIRE_CHARGE)
                                                        )
                                        ),
                                DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(damageType.getOrThrow(DamageTypeTags.IS_PROJECTILE)))
                                        .direct(
                                                EntityPredicate.Builder.entity()
                                                        .of(entityLookup, EntityTypes.FIREBALL)
                                        )
                        )
                )
                .rewards(AdvancementRewards.Builder.experience(300))
                .save(exporter, ofAdventureAdvancement("spare_me"));

        AdvancementHolder perchAlready = Advancement.Builder.advancement()
                .parent(theEndIsNear)
                .display(
                        ModItems.DRAGONS_PEARL,
                        Component.translatable("advancements.speedrunnermod.perch_already.title"),
                        Component.translatable("advancements.speedrunnermod.perch_already.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.DRAGONS_PEARL))
                .save(exporter, ofItemAdvancement("perch_already"));

       AdvancementHolder theFinalTreasure = Advancement.Builder.advancement()
                .parent(perchAlready)
                .display(
                        ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE,
                        Component.translatable("advancements.speedrunnermod.the_final_treasure.title"),
                        Component.translatable("advancements.speedrunnermod.the_final_treasure.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        true
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE))
                .save(exporter, ofItemAdvancement("the_final_treasure"));

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
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_item", ItemLikeTrigger.Conditions.item(itemLookup, Items.POTION))
                .save(exporter, ofPotionAdvancement("dragons_aura"));

        PotionContents luckEffect = new PotionContents(Potions.LUCK);
        ItemStackTemplate luckPotion = new ItemStackTemplate(
                Items.POTION.builtInRegistryHolder(),
                1,
                DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, luckEffect).build()
        );
        AdvancementHolder luckyYou = Advancement.Builder.advancement()
                .parent(dragonsAura)
                .display(
                        luckPotion,
                        Component.translatable("advancements.speedrunnermod.lucky_you.title"),
                        Component.translatable("advancements.speedrunnermod.lucky_you.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_item", ItemLikeTrigger.Conditions.item(itemLookup, Items.LINGERING_POTION))
                .save(exporter, ofPotionAdvancement("lucky_you"));

        AdvancementHolder doomedToLuck = Advancement.Builder.advancement()
                .parent(luckyYou)
                .display(
                        ModItems.DOOM_STONE,
                        Component.translatable("advancements.speedrunnermod.doom_luck.title"),
                        Component.translatable("advancements.speedrunnermod.doom_luck.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("obtain_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.DOOM_STONE))
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(exporter, ofAdventureAdvancement("doomed_to_luck"));

        PotionContents witheredEffect = new PotionContents(ModPotions.WITHERED);
        ItemStackTemplate witheredPotion = new ItemStackTemplate(
                Items.POTION.builtInRegistryHolder(),
                1,
                DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, witheredEffect).build()
        );
        Advancement.Builder.advancement()
                .parent(dragonsAura)
                .display(
                        witheredPotion,
                        Component.translatable("advancements.speedrunnermod.the_black_plaque.title"),
                        Component.translatable("advancements.speedrunnermod.the_black_plaque.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("obtain_item", ItemLikeTrigger.Conditions.item(itemLookup, Items.SPLASH_POTION))
                .save(exporter, ofPotionAdvancement("the_black_plaque"));

        Advancement.Builder.advancement()
                .parent(dragonsAura)
                .display(
                        ModItems.DRAGON_FIREBALL,
                        Component.translatable("advancements.speedrunnermod.dragons_breath.title"),
                        Component.translatable("advancements.speedrunnermod.dragons_breath.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGON_FIREBALL))
                .save(exporter, ofItemAdvancement("dragons_breath"));

        AdvancementHolder piglinRally = Advancement.Builder.advancement()
                .parent(infernalGaze)
                .display(
                        ModItems.PIGLIN_AWAKENER,
                        Component.translatable("advancements.speedrunnermod.piglin_rally.title"),
                        Component.translatable("advancements.speedrunnermod.piglin_rally.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.PIGLIN_AWAKENER))
                .save(exporter, ofItemAdvancement("piglin_rally"));

        Advancement.Builder.advancement()
                .parent(piglinRally)
                .display(
                        ModItems.BLAZE_SPOTTER,
                        Component.translatable("advancements.speedrunnermod.the_blazez_awaitz.title"),
                        Component.translatable("advancements.speedrunnermod.the_blazez_awaitz.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.BLAZE_SPOTTER))
                .save(exporter, ofItemAdvancement("the_blazez_awaitz"));

        Advancement.Builder.advancement()
                .parent(doomedToLuck)
                .display(
                        Items.ZOMBIE_HEAD,
                        Component.translatable("advancements.speedrunnermod.david_and_goliath.title"),
                        Component.translatable("advancements.speedrunnermod.david_and_goliath.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("killed_goliath", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.GIANT)))
                .rewards(AdvancementRewards.Builder.experience(250))
                .save(exporter,ofAdventureAdvancement("david_and_goliath"));

        Advancement.Builder.advancement()
                .parent(doomedToLuck)
                .display(
                        ModItems.RAID_ERADICATOR,
                        Component.translatable("advancements.speedrunnermod.the_purge.title"),
                        Component.translatable("advancements.speedrunnermod.the_purge.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.RAID_ERADICATOR))
                .save(exporter, ofItemAdvancement("the_purge"));

        AdvancementHolder oneHitOneKill = Advancement.Builder.advancement()
                .parent(theFinalTreasure)
                .display(
                        ModItems.DRAGONS_SWORD,
                        Component.translatable("advancements.speedrunnermod.one_hit_one_kill.title"),
                        Component.translatable("advancements.speedrunnermod.one_hit_one_kill.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("obtained_dragons_sword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DRAGONS_SWORD))
                .rewards(AdvancementRewards.Builder.experience(500))
                .save(exporter, ofItemAdvancement("one_hit_one_kill"));

        Advancement.Builder.advancement()
                .parent(oneHitOneKill)
                .display(
                        Items.DRAGON_HEAD,
                        Component.translatable("advancements.speedrunnermod.your_majesty.title"),
                        Component.translatable("advancements.speedrunnermod.your_majesty.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        true
                )
                .addCriterion("used_dragons_sword", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.DRAGONS_SWORD))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(exporter, ofAdventureAdvancement("your_majesty"));

        AdvancementHolder yesTheEnd = Advancement.Builder.advancement()
                .parent(perchAlready)
                .display(
                        Items.DRAGON_HEAD,
                        Component.translatable("advancements.speedrunnermod.yes_the_end.title"),
                        Component.translatable("advancements.speedrunnermod.yes_the_end.description"),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )
                .addCriterion("yes_the_end", ChangeDimensionTrigger.TriggerInstance.changedDimension(Level.END, Level.OVERWORLD))
                .save(exporter, ofAdventureAdvancement("yes_the_end"));

        AdvancementHolder toInfiniAndBeyond = Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.INFINI_PEARL,
                        Component.translatable("advancements.speedrunnermod.to_infini_and_beyond.title"),
                        Component.translatable("advancements.speedrunnermod.to_infini_and_beyond.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.INFINI_PEARL))
                .rewards(AdvancementRewards.Builder.experience(300))
                .save(exporter, ofItemAdvancement("to_infini_and_beyond"));

        Advancement.Builder.advancement()
                .parent(toInfiniAndBeyond)
                .display(
                        ModItems.KNOCKBACK_STICK,
                        Component.translatable("advancements.speedrunnermod.boing.title"),
                        Component.translatable("advancements.speedrunnermod.boing.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("used_knockback_stick", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.KNOCKBACK_STICK))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(exporter, ofItemAdvancement("boing"));

        Advancement.Builder.advancement()
                .parent(toInfiniAndBeyond)
                .display(
                        Items.ELYTRA,
                        Component.translatable("advancements.speedrunnermod.forever_flight.title"),
                        Component.translatable("advancements.speedrunnermod.forever_flight.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                        .of(itemLookup, Items.ELYTRA)
                        .withComponents(DataComponentMatchers.Builder.components()
                                .exact(DataComponentExactPredicate.expect(DataComponents.ITEM_NAME, Component.translatable("item.speedrunnermod.icarus_wings")))
                                .any(DataComponents.UNBREAKABLE)
                                .build()))
                )
                .rewards(AdvancementRewards.Builder.experience(300))
                .save(exporter, ofItemAdvancement("forever_flying"));

        Advancement.Builder.advancement()
                .parent(theEndOfTheMatter)
                .display(
                        ModItems.SPEEDRUNNERS_TOTEM,
                        Component.translatable("advancements.speedrunnermod.immortal.title"),
                        Component.translatable("advancements.speedrunnermod.immortal.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("used_item", ItemLikeTrigger.Conditions.item(itemLookup, ModItems.SPEEDRUNNERS_TOTEM))
                .rewards(AdvancementRewards.Builder.experience(500))
                .save(exporter, ofAdventureAdvancement("immortal"));

        AdvancementHolder killWarden = Advancement.Builder.advancement()
                .parent(yesTheEnd)
                .display(
                        Items.SCULK_SHRIEKER,
                        Component.translatable("advancements.speedrunnermod.deep_dark.title"),
                        Component.translatable("advancements.speedrunnermod.deep_dark.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("killed_warden", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.WARDEN)))
                .rewards(AdvancementRewards.Builder.experience(250))
                .save(exporter, ofAdventureAdvancement("deep_dark"));

        Advancement.Builder.advancement()
                .parent(killWarden)
                .display(
                        Items.PLAYER_HEAD,
                        Component.translatable("advancements.speedrunnermod.dominion.title"),
                        Component.translatable("advancements.speedrunnermod.dominion.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("killed_dragon", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.ENDER_DRAGON)))
                .addCriterion("killed_elder_guardian", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.GIANT)))
                .addCriterion("killed_wither", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.WITHER)))
                .addCriterion("killed_warden", KilledTrigger.TriggerInstance.playerKilledEntity(
                        EntityPredicate.Builder.entity()
                                .of(entityLookup, EntityTypes.WARDEN)))
                .rewards(AdvancementRewards.Builder.experience(1000))
                .save(exporter, ofAdventureAdvancement("dominion"));

        AdvancementHolder lumberjack = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        Items.OAK_LOG,
                        Component.translatable("advancements.speedrunnermod.lumberjack.title"),
                        Component.translatable("advancements.speedrunnermod.lumberjack.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
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
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(exporter, ofChallengeAdvancement("lumberjack"));

        AdvancementHolder mineralSprings = Advancement.Builder.advancement()
                .parent(lumberjack)
                .display(
                        Items.QUARTZ,
                        Component.translatable("advancements.speedrunnermod.mineral_springs.title"),
                        Component.translatable("advancements.speedrunnermod.mineral_springs.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_coal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .addCriterion("has_iron_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .addCriterion("has_copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .addCriterion("has_gold_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .addCriterion("has_redstone_dust", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .addCriterion("has_lapis_lazuli", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LAPIS_LAZULI))
                .addCriterion("has_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .addCriterion("has_emerald", InventoryChangeTrigger.TriggerInstance.hasItems(Items.EMERALD))
                .addCriterion("has_quartz", InventoryChangeTrigger.TriggerInstance.hasItems(Items.QUARTZ))
                .addCriterion("has_amethest_shard", InventoryChangeTrigger.TriggerInstance.hasItems(Items.AMETHYST_SHARD))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(exporter, ofChallengeAdvancement("mineral_springs"));

        AdvancementHolder swordCollector = Advancement.Builder.advancement()
                .parent(mineralSprings)
                .display(
                        Items.DIAMOND_SWORD,
                        Component.translatable("advancements.speedrunnermod.sword_collector.title"),
                        Component.translatable("advancements.speedrunnermod.sword_collector.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_wood_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOODEN_SWORD))
                .addCriterion("has_stone_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_SWORD))
                .addCriterion("has_copper_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_SWORD))
                .addCriterion("has_iron_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_SWORD))
                .addCriterion("has_golden_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_SWORD))
                .addCriterion("has_diamond_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_SWORD))
                .addCriterion("has_netherite_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SWORD))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(exporter, ofChallengeAdvancement("sword_collector"));

        AdvancementHolder shepherd = Advancement.Builder.advancement()
                .parent(swordCollector)
                .display(
                        Items.WOOL.white(),
                        Component.translatable("advancements.speedrunnermod.shepherd.title"),
                        Component.translatable("advancements.speedrunnermod.shepherd.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_white_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.white()))
                .addCriterion("has_orange_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.orange()))
                .addCriterion("has_blue_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.blue()))
                .addCriterion("has_yellow_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.yellow()))
                .addCriterion("has_cyan_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.cyan()))
                .addCriterion("has_green_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.green()))
                .addCriterion("has_lime_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.lime()))
                .addCriterion("has_light_blue_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.lightBlue()))
                .addCriterion("has_gray_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.gray()))
                .addCriterion("has_light_gray_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.lightGray()))
                .addCriterion("has_black_Wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.black()))
                .addCriterion("has_red_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.red()))
                .addCriterion("has_brown_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.brown()))
                .addCriterion("has_magenta_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.magenta()))
                .addCriterion("has_purple_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.purple()))
                .addCriterion("has_pink_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WOOL.pink()))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(exporter, ofChallengeAdvancement("shepherd"));

        Advancement.Builder.advancement()
                .parent(shepherd)
                .display(
                        Items.WOOL.lime(),
                        Component.translatable("advancements.speedrunnermod.expert_shepherd.title"),
                        Component.translatable("advancements.speedrunnermod.expert_shepherd.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_stack_of_lime_wool", ItemLikeTrigger.Conditions.item(itemLookup, Items.WOOL.lime()))
                .rewards(AdvancementRewards.Builder.experience(50))
                .save(exporter, ofChallengeAdvancement("expert_shepherd"));

        AdvancementHolder artisan = Advancement.Builder.advancement()
                .parent(shepherd)
                .display(
                        Items.FLETCHING_TABLE,
                        Component.translatable("advancements.speedrunnermod.artisan.title"),
                        Component.translatable("advancements.speedrunnermod.artisan.description"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_blast_furnace", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAST_FURNACE))
                .addCriterion("has_smoker", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SMOKER))
                .addCriterion("has_cartography_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CARTOGRAPHY_TABLE))
                .addCriterion("has_brewing_stand", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BREWING_STAND))
                .addCriterion("has_composter", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COMPOSTER))
                .addCriterion("has_barrel", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BARREL))
                .addCriterion("has_fletching_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FLETCHING_TABLE))
                .addCriterion("has_cauldron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CAULDRON))
                .addCriterion("has_lectern", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LECTERN))
                .addCriterion("has_stonecutter", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONECUTTER))
                .addCriterion("has_loom", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LOOM))
                .addCriterion("has_smithing_table", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SMITHING_TABLE))
                .addCriterion("has_grindstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRINDSTONE))
                .rewards(AdvancementRewards.Builder.experience(300))
                .save(exporter, ofChallengeAdvancement("artisan"));

        Advancement.Builder.advancement()
                .parent(artisan)
                .display(
                        Items.MUSIC_DISC_CREATOR,
                        Component.translatable("advancements.speedrunnermod.music_enthusiast.title"),
                        Component.translatable("advancements.speedrunnermod.music_enthusiast.description"),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("has_disc_13", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_13))
                .addCriterion("has_disc_cat", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_CAT))
                .addCriterion("has_disc_blocks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_BLOCKS))
                .addCriterion("has_disc_bounce", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_BOUNCE))
                .addCriterion("has_disc_chirp", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_CHIRP))
                .addCriterion("has_disc_creator", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_CREATOR))
                .addCriterion("has_disc_creator_music_box", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_CREATOR_MUSIC_BOX))
                .addCriterion("has_disc_far", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_FAR))
                .addCriterion("has_disc_lava_chicken", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_LAVA_CHICKEN))
                .addCriterion("has_disc_mall", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_MALL))
                .addCriterion("has_disc_mellohi", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_MELLOHI))
                .addCriterion("has_disc_stal", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_STAL))
                .addCriterion("has_disc_strad", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_STRAD))
                .addCriterion("has_disc_ward", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_WARD))
                .addCriterion("has_disc_11", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_11))
                .addCriterion("has_disc_wait", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_WAIT))
                .addCriterion("has_disc_otherside", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_OTHERSIDE))
                .addCriterion("has_disc_relic", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_RELIC))
                .addCriterion("has_disc_5", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_5))
                .addCriterion("has_disc_pigstep", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_PIGSTEP))
                .addCriterion("has_disc_precipice", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_PRECIPICE))
                .addCriterion("has_disc_tears", InventoryChangeTrigger.TriggerInstance.hasItems(Items.MUSIC_DISC_TEARS))
                .rewards(AdvancementRewards.Builder.experience(2000))
                .save(exporter, ofChallengeAdvancement("music_enthusiast"));
    }

    /**
     * @return a {@code speedrunnermod/item} advancement.
     */
    private static Identifier ofItemAdvancement(String id) {
        return ofSpeedrunnerMod("items/"+id);
    }

    /**
     * @return a {@code speedrunnermod/adventure} advancement.
     */
    private static Identifier ofAdventureAdvancement(String id) {
        return ofSpeedrunnerMod("adventure/"+id);
    }

    /**
     * @return a {@code speedrunnermod/blocks} advancement.
     */
    private static Identifier ofBlockAdvancement(String id) {
        return ofSpeedrunnerMod("blocks/"+id);
    }

    /**
     * @return a {@code speedrunnermod/enchantments} advancement.
     */
    private static Identifier ofEnchantmentAdvancement(String id) {
        return ofSpeedrunnerMod("enchantments/"+id);
    }

    /**
     * @return a {@code speedrunnermod/potions} advancement.
     */
    private static Identifier ofPotionAdvancement(String id) {
        return ofSpeedrunnerMod("potions/"+id);
    }

    /**
     * @return a {@code speedrunnermod/challenges} advancement.
     */
    private static Identifier ofChallengeAdvancement(String id) {
        return ofSpeedrunnerMod("challenges/"+id);
    }

    /**
     * @return the advancement criterion for the {@code What A Wasteland} advancement.
     */
    private static Advancement.Builder requireSpeedrunnersWasteland(Advancement.Builder builder, HolderLookup.Provider registries) {
        HolderGetter<Biome> registryEntryLookup = registries.lookupOrThrow(Registries.BIOME);

        builder.addCriterion(
                ModBiomes.SPEEDRUNNERS_WASTELAND.identifier().toString(),
                PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(registryEntryLookup.getOrThrow(ModBiomes.SPEEDRUNNERS_WASTELAND)))
        );

        return builder;
    }
}
