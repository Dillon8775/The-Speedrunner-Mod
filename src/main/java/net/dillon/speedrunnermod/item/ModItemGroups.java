package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.entity.ModPotions;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.util.stream.IntStream;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * The Speedrunner Mod {@code item group.}
 */
public class ModItemGroups {

    /**
     * This field is never used, but the {@code init} method inside this class takes care of that, and initializes this item group.
     */
    public static ItemGroup SPEEDRUNNER_MOD = Registry.register(Registries.ITEM_GROUP, ofSpeedrunnerMod("speedrunner_mod_item_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("speedrunnermod.item_group"))
                    .icon(() -> new ItemStack(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SPEEDRUNNER_INGOT);
                        entries.add(ModItems.SPEEDRUNNER_NUGGET);
                        entries.add(ModItems.SPEEDRUNNER_BLOCK);
                        entries.add(ModItems.RAW_SPEEDRUNNER);
                        entries.add(ModItems.RAW_SPEEDRUNNER_BLOCK);
                        entries.add(ModItems.SPEEDRUNNER_ORE);
                        entries.add(ModItems.DEEPSLATE_SPEEDRUNNER_ORE);
                        entries.add(ModItems.NETHER_SPEEDRUNNER_ORE);
                        entries.add(ModItems.SPEEDRUNNER_SWORD);
                        entries.add(ModItems.SPEEDRUNNER_SHOVEL);
                        entries.add(ModItems.SPEEDRUNNER_PICKAXE);
                        entries.add(ModItems.SPEEDRUNNER_AXE);
                        entries.add(ModItems.SPEEDRUNNER_HOE);
                        entries.add(ModItems.SPEEDRUNNER_HELMET);
                        entries.add(ModItems.SPEEDRUNNER_CHESTPLATE);
                        entries.add(ModItems.SPEEDRUNNER_LEGGINGS);
                        entries.add(ModItems.SPEEDRUNNER_BOOTS);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_SWORD);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_AXE);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_HOE);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_HELMET);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
                        entries.add(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.SPEEDRUNNER_BOW);
                        entries.add(ModItems.SPEEDRUNNER_CROSSBOW);
                        entries.add(ModItems.SPEEDRUNNER_SHEARS);
                        entries.add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
                        entries.add(ModItems.SPEEDRUNNERS_TOTEM);
                        entries.add(ModItems.SPEEDRUNNERS_WORKBENCH);
                        entries.add(ModItems.SPEEDRUNNER_SHIELD);
                        entries.add(ModItems.GOLDEN_SHIELD);
                        entries.add(Items.GOLD_INGOT);
                        entries.add(ModItems.SPEEDRUNNERS_EYE);
                        entries.add(ModItems.ENDER_THRUSTER);
                        entries.add(ModItems.THRUSTED_BLOCK);
                        entries.add(ModItems.INFERNO_EYE);
                        entries.add(ModItems.PIGLIN_AWAKENER);
                        entries.add(ModItems.BLAZE_SPOTTER);
                        entries.add(ModItems.RAID_ERADICATOR);
                        entries.add(ModItems.ANNUL_EYE);
                        entries.add(Items.ENDER_PEARL);
                        entries.add(Items.ENDER_EYE);
                        entries.add(ModItems.DRAGONS_PEARL);
                        entries.add(ModItems.DRAGONS_FIREBALL);
                        entries.add(Items.FIRE_CHARGE);
                        entries.add(ModItems.INFINI_PEARL);
                        entries.add(ModItems.ENDER_MATTER);
                        entries.add(ModItems.INVENTORY_PRESERVER);
                        displayContext.lookup().getOptional(RegistryKeys.POTION).ifPresent(registryWrapper -> {
                            addAllPotions(entries, registryWrapper, ModPotions.DRAGONS_AURA);
                            addAllPotions(entries, registryWrapper, ModPotions.LONG_DRAGONS_AURA);
                        });
                        entries.add(ModUtil.ofUnbreakable(Items.ELYTRA));
                        entries.add(ModUtil.fireworkWithFlightDuration(1));
                        entries.add(ModItems.KNOCKBACK_STICK);
                        entries.add(ModItems.DRAGONS_SWORD);
                        entries.add(ModItems.SPEEDRUNNER_PADDLE);
                        entries.add(ModItems.SPEEDRUNNER_BOAT);
                        entries.add(ModItems.FIREPROOF_SPEEDRUNNER_BOAT);
                        entries.add(ModItems.SPEEDRUNNER_CHEST_BOAT);
                        entries.add(ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_BOAT);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
                        entries.add(ModItems.CRIMSON_BOAT);
                        entries.add(ModItems.FIREPROOF_CRIMSON_BOAT);
                        entries.add(ModItems.CRIMSON_CHEST_BOAT);
                        entries.add(ModItems.FIREPROOF_CRIMSON_CHEST_BOAT);
                        entries.add(ModItems.WARPED_BOAT);
                        entries.add(ModItems.FIREPROOF_WARPED_BOAT);
                        entries.add(ModItems.WARPED_CHEST_BOAT);
                        entries.add(ModItems.FIREPROOF_WARPED_CHEST_BOAT);
                        displayContext.lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent(registryWrapper -> {
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.DASH);
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.COOLDOWN);
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.WITHERED);
                        });
                        entries.add(ModItems.PIGLIN_PORK);
                        entries.add(ModItems.COOKED_PIGLIN_PORK);
                        entries.add(ModItems.SPEEDRUNNER_BULK);
                        entries.add(ModItems.ROTTEN_SPEEDRUNNER_BULK);
                        entries.add(ModItems.COOKED_FLESH);
                        entries.add(ModItems.FLESH_BLOCK);
                        entries.add(ModItems.EXPERIENCE_FRAGMENT);
                        entries.add(ModItems.EXPERIENCE_ORE);
                        entries.add(ModItems.DEEPSLATE_EXPERIENCE_ORE);
                        entries.add(ModItems.NETHER_EXPERIENCE_ORE);
                        entries.add(ModItems.IGNEOUS_ROCK);
                        entries.add(ModItems.IGNEOUS_ORE);
                        entries.add(ModItems.DEEPSLATE_IGNEOUS_ORE);
                        entries.add(ModItems.NETHER_IGNEOUS_ORE);
                        entries.add(ModItems.SPEEDRUNNER_LOG);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_LOG);
                        entries.add(ModItems.STRIPPED_SPEEDRUNNER_LOG);
                        entries.add(ModItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                        entries.add(ModItems.SPEEDRUNNER_WOOD);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_WOOD);
                        entries.add(ModItems.STRIPPED_SPEEDRUNNER_WOOD);
                        entries.add(ModItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
                        entries.add(ModItems.SPEEDRUNNER_LEAVES);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_LEAVES);
                        entries.add(ModItems.SPEEDRUNNER_SAPLING);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_SAPLING);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_BUSH);
                        entries.add(ModItems.SPEEDRUNNER_PLANKS);
                        entries.add(ModItems.SPEEDRUNNER_STICK);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_PLANKS);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_STICK);
                        entries.add(ModItems.SPEEDRUNNER_SLAB);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_SLAB);
                        entries.add(ModItems.SPEEDRUNNER_STAIRS);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_STAIRS);
                        entries.add(ModItems.SPEEDRUNNER_FENCE);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_FENCE);
                        entries.add(ModItems.SPEEDRUNNER_FENCE_GATE);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_FENCE_GATE);
                        entries.add(ModItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModItems.SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModItems.WOODEN_SPEEDRUNNER_BUTTON);
                        entries.add(ModItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
                        entries.add(ModItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
                        entries.add(ModItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
                        entries.add(ModItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
                        entries.add(ModItems.WOODEN_SPEEDRUNNER_DOOR);
                        entries.add(ModItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);
                        entries.add(ModItems.SPEEDRUNNER_DOOR);
                        entries.add(ModItems.SPEEDRUNNER_SIGN);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_SIGN);
                        entries.add(ModItems.SPEEDRUNNER_HANGING_SIGN);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_HANGING_SIGN);
                        entries.add(ModItems.DOOM_STONE);
                        entries.add(ModItems.DOOM_LOG);
                        entries.add(ModItems.STRIPPED_DOOM_LOG);
                        entries.add(ModItems.DOOM_LEAVES);
                    }).build());

    /**
     * Adds all the levels of the inputted enchanted book to the item group.
     */
    private static void addAllLevelEnchantedBook(ItemGroup.Entries entries, RegistryWrapper<Enchantment> registryWrapper, RegistryKey<Enchantment> enchantment) {
        registryWrapper.streamEntries()
                .filter(enchantmentEntry -> enchantmentEntry.matchesKey(enchantment))
                .flatMap(enchantmentEntry -> IntStream.rangeClosed(enchantmentEntry.value().getMinLevel(), enchantmentEntry.value().getMaxLevel())
                        .mapToObj(level -> EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(enchantmentEntry, level))))
                .forEach(stack -> entries.add(stack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
    }

    /**
     * Adds all potions of type.
     */
    private static void addAllPotions(ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, RegistryEntry<Potion> potion) {
        addPotions(entries, registryWrapper, Items.POTION, potion);
        addPotions(entries, registryWrapper, Items.SPLASH_POTION, potion);
        addPotions(entries, registryWrapper, Items.LINGERING_POTION, potion);
    }

    /**
     * Adds all the potions of inputted potions to the item group.
     */
    private static void addPotions(ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, RegistryEntry<Potion> potion) {
        registryWrapper.streamEntries()
                .filter(potionEntry -> potionEntry.matchesKey(potion.getKey().get()))
                .map(entry -> PotionContentsComponent.createStack(item, entry))
                .forEach(stack -> entries.add(stack, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS));
    }

    /**
     * Adds an item to an item group.
     */
    private static void addToItemGroup(RegistryKey<ItemGroup> group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    /**
     * The method to register the modified and new item groups.
     */
    public static void registerModifiedItemGroups() {
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_INGOT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_NUGGET);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.RAW_SPEEDRUNNER);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.RAW_SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.IGNEOUS_ROCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_STICK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.ENDER_MATTER);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.EXPERIENCE_FRAGMENT);

        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOW);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SHIELD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SHIELD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.DRAGONS_SWORD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.DRAGONS_FIREBALL);
        if (options().main.throwableFireballs.getCurrentValue()) {
            addToItemGroup(ItemGroups.COMBAT, Items.FIRE_CHARGE);
        }
        addToItemGroup(ItemGroups.COMBAT, ModItems.RAID_ERADICATOR);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNERS_TOTEM);
        addToItemGroup(ItemGroups.COMBAT, ModItems.KNOCKBACK_STICK);

        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHEARS);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.DEAD_SPEEDRUNNER_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.ANNUL_EYE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNERS_EYE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.INFERNO_EYE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.PIGLIN_AWAKENER);
        addToItemGroup(ItemGroups.TOOLS, ModItems.BLAZE_SPOTTER);
        addToItemGroup(ItemGroups.TOOLS, ModItems.ENDER_THRUSTER);
        addToItemGroup(ItemGroups.TOOLS, ModItems.DRAGONS_PEARL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.INFINI_PEARL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.INVENTORY_PRESERVER);

        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.ROTTEN_SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_FLESH);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_PIGLIN_PORK);

        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_LEAVES);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_LEAVES);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_SAPLING);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_SAPLING);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_PLANKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_PLANKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_FENCE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_FENCE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_DOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_BUSH);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.THRUSTED_BLOCK);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEEPSLATE_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.NETHER_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEEPSLATE_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.NETHER_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.DEEPSLATE_EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.NETHER_EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModItems.FLESH_BLOCK);

        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.SPEEDRUNNERS_WORKBENCH);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.DOOM_STONE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.DOOM_LOG);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.STRIPPED_DOOM_LOG);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.DOOM_LEAVES);

        SpeedrunnerMod.debug("Registered modified item groups.");
    }
}