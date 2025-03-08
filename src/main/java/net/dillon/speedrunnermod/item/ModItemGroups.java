package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.*;
import net.minecraft.text.Text;

import java.util.stream.IntStream;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The Speedrunner Mod {@code item group.}
 */
public class ModItemGroups {

    /**
     * This field is never used, but the {@code init} method inside this class takes care of that, and initializes this item group.
     */
    public static ItemGroup SPEEDRUNNER_MOD = Registry.register(Registries.ITEM_GROUP, ofSpeedrunnerMod("speedrunner_mod_item_group"),
            FabricItemGroup.builder()
                    .displayName(Text.literal("Speedrunner Mod"))
                    .icon(() -> new ItemStack(ModBlockItems.SPEEDRUNNERS_WORKBENCH)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SPEEDRUNNER_INGOT);
                        entries.add(ModItems.SPEEDRUNNER_NUGGET);
                        entries.add(ModBlockItems.SPEEDRUNNER_BLOCK);
                        entries.add(ModItems.RAW_SPEEDRUNNER);
                        entries.add(ModBlockItems.RAW_SPEEDRUNNER_BLOCK);
                        entries.add(ModBlockItems.SPEEDRUNNER_ORE);
                        entries.add(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE);
                        entries.add(ModBlockItems.NETHER_SPEEDRUNNER_ORE);
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
                        entries.add(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE);
                        entries.add(ModItems.SPEEDRUNNER_BOW);
                        entries.add(ModItems.SPEEDRUNNER_CROSSBOW);
                        entries.add(ModItems.SPEEDRUNNER_SHEARS);
                        entries.add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
                        entries.add(ModItems.SPEEDRUNNER_SHIELD);
                        entries.add(ModItems.SPEEDRUNNERS_TOTEM);
                        entries.add(ModBlockItems.SPEEDRUNNERS_WORKBENCH);
                        entries.add(ModItems.SPEEDRUNNERS_EYE);
                        if (options().main.playingMode.easy()) {
                            entries.add(ModItems.ENDER_THRUSTER);
                            entries.add(ModBlockItems.THRUSTED_BLOCK);
                        }
                        entries.add(ModItems.INFERNO_EYE);
                        if (options().main.playingMode.easy()) {
                            entries.add(ModItems.PIGLIN_AWAKENER);
                            entries.add(ModItems.BLAZE_SPOTTER);
                            entries.add(ModItems.RAID_ERADICATOR);
                            entries.add(ModItems.ANNUL_EYE);
                            entries.add(ModItems.DRAGONS_PEARL);
                        }
                        entries.add(ItemUtil.unbreakableComponentItem());
                        entries.add(ItemUtil.flightDurationComponentItem(1));
                        entries.add(ModItems.CRAFTABLE_INFINI_PEARL);
                        entries.add(ModItems.ENDER_MATTER);
                        entries.add(ModItems.KNOCKBACK_STICK);
                        entries.add(ModItems.DRAGONS_SWORD);
                        entries.add(ModItems.WITHER_SWORD);
                        entries.add(ModItems.WITHER_BONE);
                        entries.add(ModItems.SPEEDRUNNER_BOAT);
                        entries.add(ModItems.SPEEDRUNNER_CHEST_BOAT);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_BOAT);
                        entries.add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
                        entries.add(ModItems.CRIMSON_BOAT);
                        entries.add(ModItems.CRIMSON_CHEST_BOAT);
                        entries.add(ModItems.WARPED_BOAT);
                        entries.add(ModItems.WARPED_CHEST_BOAT);
                        displayContext.lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent(registryWrapper -> {
                            addAllLevelEnchantedBook(entries, registryWrapper, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS, ModEnchantments.DASH);
                            addAllLevelEnchantedBook(entries, registryWrapper, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS, ModEnchantments.COOLDOWN);
                        });
                        entries.add(ModItems.IGNEOUS_ROCK);
                        entries.add(ModBlockItems.IGNEOUS_ORE);
                        entries.add(ModBlockItems.DEEPSLATE_IGNEOUS_ORE);
                        entries.add(ModBlockItems.NETHER_IGNEOUS_ORE);
                        entries.add(ModBlockItems.EXPERIENCE_ORE);
                        entries.add(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE);
                        entries.add(ModBlockItems.NETHER_EXPERIENCE_ORE);
                        entries.add(ModBlockItems.SPEEDRUNNER_LOG);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_LOG);
                        entries.add(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG);
                        entries.add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                        entries.add(ModBlockItems.SPEEDRUNNER_WOOD);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_WOOD);
                        entries.add(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD);
                        entries.add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
                        entries.add(ModBlockItems.SPEEDRUNNER_LEAVES);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_LEAVES);
                        entries.add(ModBlockItems.SPEEDRUNNER_SAPLING);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_SAPLING);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_BUSH);
                        entries.add(ModBlockItems.SPEEDRUNNER_PLANKS);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_PLANKS);
                        entries.add(ModItems.SPEEDRUNNER_STICK);
                        entries.add(ModBlockItems.SPEEDRUNNER_SLAB);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_SLAB);
                        entries.add(ModBlockItems.SPEEDRUNNER_STAIRS);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_STAIRS);
                        entries.add(ModBlockItems.SPEEDRUNNER_FENCE);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_FENCE);
                        entries.add(ModBlockItems.SPEEDRUNNER_FENCE_GATE);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_FENCE_GATE);
                        entries.add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModBlockItems.SPEEDRUNNER_TRAPDOOR);
                        entries.add(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON);
                        entries.add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
                        entries.add(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
                        entries.add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
                        entries.add(ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
                        entries.add(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR);
                        entries.add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);
                        entries.add(ModBlockItems.SPEEDRUNNER_DOOR);
                        entries.add(ModBlockItems.SPEEDRUNNER_SIGN);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_SIGN);
                        entries.add(ModBlockItems.SPEEDRUNNER_HANGING_SIGN);
                        entries.add(ModBlockItems.DEAD_SPEEDRUNNER_HANGING_SIGN);
                        entries.add(ModBlockItems.DOOM_STONE);
                        entries.add(ModBlockItems.DOOM_LOG);
                        entries.add(ModBlockItems.STRIPPED_DOOM_LOG);
                        entries.add(ModBlockItems.DOOM_LEAVES);
                        entries.add(ModItems.PIGLIN_PORK);
                        entries.add(ModItems.COOKED_PIGLIN_PORK);
                        entries.add(ModItems.GOLDEN_PIGLIN_PORK);
                        entries.add(ModItems.GOLDEN_BEEF);
                        entries.add(ModItems.GOLDEN_PORKCHOP);
                        entries.add(ModItems.GOLDEN_MUTTON);
                        entries.add(ModItems.GOLDEN_CHICKEN);
                        entries.add(ModItems.GOLDEN_RABBIT);
                        entries.add(ModItems.GOLDEN_COD);
                        entries.add(ModItems.GOLDEN_SALMON);
                        entries.add(ModItems.GOLDEN_BREAD);
                        entries.add(ModItems.GOLDEN_POTATO);
                        entries.add(ModItems.GOLDEN_BEETROOT);
                        entries.add(ModItems.SPEEDRUNNER_BULK);
                        entries.add(ModItems.ROTTEN_SPEEDRUNNER_BULK);
                        entries.add(ModItems.COOKED_FLESH);
                    }).build());

    /**
     * Adds all the levels of the inputted enchanted book to the item group.
     */
    private static void addAllLevelEnchantedBook(ItemGroup.Entries entries, RegistryWrapper<Enchantment> registryWrapper, ItemGroup.StackVisibility stackVisibility, RegistryKey<Enchantment> enchantment) {
        registryWrapper.streamEntries().filter(enchantmentEntry -> enchantmentEntry.matchesKey(enchantment)).flatMap(enchantmentEntry -> IntStream.rangeClosed(enchantmentEntry.value().getMinLevel(), enchantmentEntry.value().getMaxLevel()).mapToObj(level -> EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(enchantmentEntry, level)))).forEach(stack -> entries.add(stack, stackVisibility));
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
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_BOW);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_SHEARS);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        addToItemGroup(ItemGroups.COMBAT, ModItems.SPEEDRUNNER_SHIELD);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_AXE);
        addToItemGroup(ItemGroups.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        addToItemGroup(ItemGroups.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.ROTTEN_SPEEDRUNNER_BULK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_FLESH);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.COOKED_PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_PIGLIN_PORK);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BEEF);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_PORKCHOP);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_MUTTON);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_CHICKEN);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_RABBIT);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_COD);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_SALMON);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BREAD);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_POTATO);
        addToItemGroup(ItemGroups.FOOD_AND_DRINK, ModItems.GOLDEN_BEETROOT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.IGNEOUS_ROCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.SPEEDRUNNER_STICK);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.DEAD_SPEEDRUNNER_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.CRIMSON_CHEST_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_BOAT);
        addToItemGroup(ItemGroups.TOOLS, ModItems.WARPED_CHEST_BOAT);
        addToItemGroup(ItemGroups.INGREDIENTS, ModItems.WITHER_BONE);
        addToItemGroup(ItemGroups.COMBAT, ModItems.WITHER_SWORD);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.ANNUL_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.SPEEDRUNNERS_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.INFERNO_EYE);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.PIGLIN_AWAKENER);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.BLAZE_SPOTTER);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.RAID_ERADICATOR);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.ENDER_THRUSTER);
        addToItemGroup(ItemGroups.COMBAT, ModItems.DRAGONS_SWORD);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModItems.DRAGONS_PEARL);

        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_LEAVES);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_LEAVES);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_SAPLING);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_SAPLING);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_PLANKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_PLANKS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_SLAB);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_FENCE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_FENCE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_DOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEAD_SPEEDRUNNER_BUSH);
        addToItemGroup(ItemGroups.FUNCTIONAL, ModBlockItems.SPEEDRUNNERS_WORKBENCH);
        addToItemGroup(ItemGroups.INGREDIENTS, ModBlockItems.SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.INGREDIENTS, ModBlockItems.RAW_SPEEDRUNNER_BLOCK);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.THRUSTED_BLOCK);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_SPEEDRUNNER_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_IGNEOUS_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DEEPSLATE_EXPERIENCE_ORE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.NETHER_EXPERIENCE_ORE);

        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_STONE);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.STRIPPED_DOOM_LOG);
        addToItemGroup(ItemGroups.BUILDING_BLOCKS, ModBlockItems.DOOM_LEAVES);
    }
}