package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

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
    public static CreativeModeTab SPEEDRUNNER_MOD = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ofSpeedrunnerMod("speedrunner_mod_item_group"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
                    .title(Component.translatable("speedrunnermod.item_group"))
                    .icon(() -> new ItemStack(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE)).displayItems((displayContext, entries) -> {
                        entries.accept(ModItems.SPEEDRUNNER_INGOT);
                        entries.accept(ModItems.SPEEDRUNNER_NUGGET);
                        entries.accept(ModItems.SPEEDRUNNER_BLOCK);
                        entries.accept(ModItems.RAW_SPEEDRUNNER);
                        entries.accept(ModItems.RAW_SPEEDRUNNER_BLOCK);
                        entries.accept(ModItems.SPEEDRUNNER_ORE);
                        entries.accept(ModItems.DEEPSLATE_SPEEDRUNNER_ORE);
                        entries.accept(ModItems.NETHER_SPEEDRUNNER_ORE);
                        entries.accept(ModItems.SPEEDRUNNER_SWORD);
                        entries.accept(ModItems.SPEEDRUNNER_SHOVEL);
                        entries.accept(ModItems.SPEEDRUNNER_PICKAXE);
                        entries.accept(ModItems.SPEEDRUNNER_AXE);
                        entries.accept(ModItems.SPEEDRUNNER_HOE);
                        entries.accept(ModItems.SPEEDRUNNER_HELMET);
                        entries.accept(ModItems.SPEEDRUNNER_CHESTPLATE);
                        entries.accept(ModItems.SPEEDRUNNER_LEGGINGS);
                        entries.accept(ModItems.SPEEDRUNNER_BOOTS);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_SWORD);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_AXE);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_HOE);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_HELMET);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
                        entries.accept(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
                        entries.accept(ModItems.SPEEDRUNNER_SPEAR);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_SPEAR);
                        entries.accept(ModItems.SPEEDRUNNER_BOW);
                        entries.accept(ModItems.SPEEDRUNNER_CROSSBOW);
                        entries.accept(ModItems.SPEEDRUNNER_HARNESS);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_HARNESS);
                        entries.accept(ModItems.SPEEDRUNNER_NAUTILUS_ARMOR);
                        entries.accept(ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR);
                        entries.accept(ModItems.SPEEDRUNNERS_WORKBENCH);
                        entries.accept(ModItems.SPEEDRUNNER_SHIELD);
                        entries.accept(ModItems.GOLDEN_SHIELD);
                        entries.accept(ModItems.SPEEDRUNNERS_TOTEM);
                        entries.accept(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
                        entries.accept(ModItems.SPEEDRUNNER_SHEARS);
                        entries.accept(ModItems.SPEEDRUNNERS_EYE);
                        entries.accept(ModItems.ENDER_THRUSTER);
                        entries.accept(ModItems.THRUSTED_BLOCK);
                        entries.accept(ModItems.INFERNO_EYE);
                        entries.accept(ModItems.PIGLIN_AWAKENER);
                        entries.accept(ModItems.BLAZE_SPOTTER);
                        entries.accept(ModItems.RAID_ERADICATOR);
                        entries.accept(ModItems.ANNUL_EYE);
                        entries.accept(Items.ENDER_EYE);
                        entries.accept(Items.ENDER_PEARL);
                        entries.accept(Items.GOLD_INGOT);
                        entries.accept(Items.BLAZE_POWDER);
                        entries.accept(ModItems.DRAGONS_PEARL);
                        entries.accept(ModItems.DRAGONS_FIREBALL);
                        entries.accept(Items.FIRE_CHARGE);
                        entries.accept(ModItems.INFINI_PEARL);
                        entries.accept(ModItems.ENDER_MATTER);
                        entries.accept(ModItems.INVENTORY_PRESERVER);
                        displayContext.holders().lookup(Registries.POTION).ifPresent(registryWrapper -> {
                            addAllPotions(entries, registryWrapper, ModPotions.DRAGONS_AURA);
                            addAllPotions(entries, registryWrapper, ModPotions.LONG_DRAGONS_AURA);
                        });
                        entries.accept(ModItems.DRAGONS_SWORD);
                        entries.accept(ModUtil.ofUnbreakable(Items.ELYTRA));
                        entries.accept(ModUtil.fireworkWithFlightDuration(1));
                        entries.accept(ModItems.KNOCKBACK_STICK);
                        displayContext.holders().lookup(Registries.ENCHANTMENT).ifPresent(registryWrapper -> {
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.DASH);
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.COOLDOWN);
                            addAllLevelEnchantedBook(entries, registryWrapper, ModEnchantments.WITHERED);
                        });
                        entries.accept(ModItems.PIGLIN_PORK);
                        entries.accept(ModItems.COOKED_PIGLIN_PORK);
                        entries.accept(ModItems.SPEEDRUNNER_BULK);
                        entries.accept(ModItems.ROTTEN_SPEEDRUNNER_BULK);
                        entries.accept(ModItems.COOKED_FLESH);
                        entries.accept(ModItems.FLESH_BLOCK);
                        entries.accept(Items.ZOMBIE_HEAD);
                        entries.accept(ModItems.GOLIATH_SPAWN_EGG);
                        entries.accept(ModItems.EXPERIENCE_FRAGMENT);
                        entries.accept(ModItems.EXPERIENCE_ORE);
                        entries.accept(ModItems.DEEPSLATE_EXPERIENCE_ORE);
                        entries.accept(ModItems.NETHER_EXPERIENCE_ORE);
                        entries.accept(ModItems.IGNEOUS_ROCK);
                        entries.accept(ModItems.IGNEOUS_ORE);
                        entries.accept(ModItems.DEEPSLATE_IGNEOUS_ORE);
                        entries.accept(ModItems.NETHER_IGNEOUS_ORE);
                        entries.accept(ModItems.SPEEDRUNNER_LOG);
                        entries.accept(ModItems.SPEEDRUNNER_WOOD);
                        entries.accept(ModItems.STRIPPED_SPEEDRUNNER_LOG);
                        entries.accept(ModItems.STRIPPED_SPEEDRUNNER_WOOD);
                        entries.accept(ModItems.SPEEDRUNNER_PLANKS);
                        entries.accept(ModItems.SPEEDRUNNER_STAIRS);
                        entries.accept(ModItems.SPEEDRUNNER_SLAB);
                        entries.accept(ModItems.SPEEDRUNNER_FENCE);
                        entries.accept(ModItems.SPEEDRUNNER_FENCE_GATE);
                        entries.accept(ModItems.SPEEDRUNNER_DOOR);
                        entries.accept(ModItems.SPEEDRUNNER_TRAPDOOR);
                        entries.accept(ModItems.SPEEDRUNNER_PRESSURE_PLATE);
                        entries.accept(ModItems.SPEEDRUNNER_BUTTON);
                        entries.accept(ModItems.SPEEDRUNNER_BOAT);
                        entries.accept(ModItems.FIREPROOF_SPEEDRUNNER_BOAT);
                        entries.accept(ModItems.SPEEDRUNNER_CHEST_BOAT);
                        entries.accept(ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT);
                        entries.accept(ModItems.SPEEDRUNNER_PADDLE);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_LOG);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_WOOD);
                        entries.accept(ModItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                        entries.accept(ModItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_PLANKS);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_STAIRS);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_SLAB);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_FENCE);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_FENCE_GATE);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_DOOR);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_TRAPDOOR);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_PRESSURE_PLATE);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_BUTTON);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_BOAT);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
                        entries.accept(ModItems.METAL_SPEEDRUNNER_DOOR);
                        entries.accept(ModItems.METAL_SPEEDRUNNER_TRAPDOOR);
                        entries.accept(ModItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
                        entries.accept(ModItems.SPEEDRUNNER_STICK);
                        entries.accept(ModItems.SPEEDRUNNER_SAPLING);
                        entries.accept(ModItems.SPEEDRUNNER_LEAVES);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_SAPLING);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_LEAVES);
                        entries.accept(ModItems.DEAD_SPEEDRUNNER_BUSH);
                        entries.accept(ModItems.CRIMSON_BOAT);
                        entries.accept(ModItems.FIREPROOF_CRIMSON_BOAT);
                        entries.accept(ModItems.CRIMSON_CHEST_BOAT);
                        entries.accept(ModItems.FIREPROOF_CRIMSON_CHEST_BOAT);
                        entries.accept(ModItems.WARPED_BOAT);
                        entries.accept(ModItems.FIREPROOF_WARPED_BOAT);
                        entries.accept(ModItems.WARPED_CHEST_BOAT);
                        entries.accept(ModItems.FIREPROOF_WARPED_CHEST_BOAT);
                        entries.accept(ModItems.DOOM_STONE);
                        entries.accept(ModItems.DOOM_LOG);
                        entries.accept(ModItems.DOOM_LEAVES);
                    }).build());

    /**
     * Adds all the levels of the inputted enchanted book to the item group.
     */
    private static void addAllLevelEnchantedBook(CreativeModeTab.Output entries, HolderLookup<Enchantment> registryWrapper, ResourceKey<Enchantment> enchantment) {
        registryWrapper.listElements()
                .filter(enchantmentEntry -> enchantmentEntry.is(enchantment))
                .flatMap(enchantmentEntry -> IntStream.rangeClosed(enchantmentEntry.value().getMinLevel(), enchantmentEntry.value().getMaxLevel())
                        .mapToObj(level -> EnchantmentHelper.createBook(new EnchantmentInstance(enchantmentEntry, level))))
                .forEach(stack -> entries.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }

    /**
     * Adds all potions of type.
     */
    private static void addAllPotions(CreativeModeTab.Output entries, HolderLookup<Potion> registryWrapper, Holder<Potion> potion) {
        addPotions(entries, registryWrapper, Items.POTION, potion);
        addPotions(entries, registryWrapper, Items.SPLASH_POTION, potion);
        addPotions(entries, registryWrapper, Items.LINGERING_POTION, potion);
    }

    /**
     * Adds all the potions of inputted potions to the item group.
     */
    private static void addPotions(CreativeModeTab.Output entries, HolderLookup<Potion> registryWrapper, Item item, Holder<Potion> potion) {
        registryWrapper.listElements()
                .filter(potionEntry -> potionEntry.is(potion.unwrapKey().get()))
                .map(entry -> PotionContents.createItemStack(item, entry))
                .forEach(stack -> entries.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
    }

    /**
     * Adds an item to an item group.
     */
    private static void addToItemGroup(ResourceKey<CreativeModeTab> group, Item item) {
        CreativeModeTabEvents.modifyOutputEvent(group).register(entries -> entries.accept(item));
    }

    /**
     * The method to register the modified and new item groups.
     */
    public static void registerModifiedItemGroups() {
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.SPEEDRUNNER_INGOT);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.SPEEDRUNNER_NUGGET);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.RAW_SPEEDRUNNER);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.SPEEDRUNNER_BLOCK);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.RAW_SPEEDRUNNER_BLOCK);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.IGNEOUS_ROCK);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.SPEEDRUNNER_STICK);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.ENDER_MATTER);
        addToItemGroup(CreativeModeTabs.INGREDIENTS, ModItems.EXPERIENCE_FRAGMENT);

        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_SWORD);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_SPEAR);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_HELMET);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_LEGGINGS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_BOOTS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_BOW);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_SHIELD);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SHIELD);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SPEAR);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.DRAGONS_SWORD);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.DRAGONS_FIREBALL);
        if (options().general.throwableFireballs.getCurrentValue()) {
            addToItemGroup(CreativeModeTabs.COMBAT, Items.FIRE_CHARGE);
        }
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.RAID_ERADICATOR);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNERS_TOTEM);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.KNOCKBACK_STICK);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_HARNESS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HARNESS);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.SPEEDRUNNER_NAUTILUS_ARMOR);
        addToItemGroup(CreativeModeTabs.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR);

        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_SHOVEL);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_PICKAXE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_AXE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_HOE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_SHEARS);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_FLINT_AND_STEEL);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.GOLDEN_SPEEDRUNNER_AXE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.GOLDEN_SPEEDRUNNER_HOE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.DEAD_SPEEDRUNNER_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.CRIMSON_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.CRIMSON_CHEST_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.WARPED_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.WARPED_CHEST_BOAT);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.ANNUL_EYE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.SPEEDRUNNERS_EYE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.INFERNO_EYE);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.PIGLIN_AWAKENER);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.BLAZE_SPOTTER);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.ENDER_THRUSTER);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.DRAGONS_PEARL);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.INFINI_PEARL);
        addToItemGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, ModItems.INVENTORY_PRESERVER);

        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ModItems.SPEEDRUNNER_BULK);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ModItems.ROTTEN_SPEEDRUNNER_BULK);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ModItems.COOKED_FLESH);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ModItems.PIGLIN_PORK);
        addToItemGroup(CreativeModeTabs.FOOD_AND_DRINKS, ModItems.COOKED_PIGLIN_PORK);

        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_LOG);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_WOOD);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_LOG);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_STRIPPED_SPEEDRUNNER_LOG);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_WOOD);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_LEAVES);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_LEAVES);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_SAPLING);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_SAPLING);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_PLANKS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_PLANKS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_SLAB);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_SLAB);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_FENCE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_FENCE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_FENCE_GATE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.METAL_SPEEDRUNNER_TRAPDOOR);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_BUTTON);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_BUTTON);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_PRESSURE_PLATE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.METAL_SPEEDRUNNER_DOOR);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_DOOR);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_STAIRS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_STAIRS);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEAD_SPEEDRUNNER_BUSH);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.THRUSTED_BLOCK);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.SPEEDRUNNER_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEEPSLATE_SPEEDRUNNER_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.NETHER_SPEEDRUNNER_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.IGNEOUS_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEEPSLATE_IGNEOUS_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.NETHER_IGNEOUS_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.EXPERIENCE_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.DEEPSLATE_EXPERIENCE_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.NETHER_EXPERIENCE_ORE);
        addToItemGroup(CreativeModeTabs.BUILDING_BLOCKS, ModItems.FLESH_BLOCK);

        addToItemGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, ModItems.SPEEDRUNNERS_WORKBENCH);
        addToItemGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, ModItems.DOOM_STONE);
        addToItemGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, ModItems.DOOM_LOG);
        addToItemGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, ModItems.DOOM_LEAVES);

        addToItemGroup(CreativeModeTabs.SPAWN_EGGS, ModItems.GOLIATH_SPAWN_EGG);

        SpeedrunnerMod.debug("Registered item groups.");
    }
}