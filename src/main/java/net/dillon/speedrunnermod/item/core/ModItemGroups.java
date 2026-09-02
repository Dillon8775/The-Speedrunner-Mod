package net.dillon.speedrunnermod.item.core;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.component.ModPotions;
import net.dillon.speedrunnermod.helper.ModHelper;
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
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

import java.util.List;
import java.util.stream.IntStream;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

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
                        entries.accept(Items.FIRE_CHARGE);
                        entries.accept(ModItems.INFINI_PEARL);
                        entries.accept(ModItems.ENDER_MATTER);
                        entries.accept(ModItems.INVENTORY_PRESERVER);
                        entries.accept(ModItems.DRAGON_FIREBALL);
                        entries.accept(ModItems.DRAGONS_PEARL);
                        entries.accept(ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE);
                        entries.accept(ModItems.DRAGONS_SWORD);
                        entries.accept(ModHelper.ofUnbreakable(Items.ELYTRA));
                        entries.accept(ModItems.KNOCKBACK_STICK);
                        displayContext.holders().lookup(Registries.POTION).ifPresent(registryWrapper -> {
                            addAllPotions(entries, registryWrapper, ModPotions.DRAGONS_AURA);
                            addAllPotions(entries, registryWrapper, ModPotions.LONG_DRAGONS_AURA);
                            addAllArrows(entries, registryWrapper, ModPotions.DRAGONS_AURA);
                            addAllArrows(entries, registryWrapper, ModPotions.LONG_DRAGONS_AURA);

                            addAllPotions(entries, registryWrapper, ModPotions.WITHERED);
                            addAllPotions(entries, registryWrapper, ModPotions.LONG_WITHERED);
                            addAllPotions(entries, registryWrapper, ModPotions.STRONG_WITHERED);
                            addAllArrows(entries, registryWrapper, ModPotions.WITHERED);
                            addAllArrows(entries, registryWrapper, ModPotions.LONG_WITHERED);
                            addAllArrows(entries, registryWrapper, ModPotions.STRONG_WITHERED);

                            addAllPotions(entries, registryWrapper, Potions.LUCK);
                            addAllPotions(entries, registryWrapper, ModPotions.STRONG_LUCK);
                            addAllArrows(entries, registryWrapper, Potions.LUCK);
                            addAllArrows(entries, registryWrapper, ModPotions.STRONG_LUCK);
                        });
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
     * Adds all arrows of potion type.
     */
    private static void addAllArrows(CreativeModeTab.Output entries, HolderLookup<Potion> registryWrapper, Holder<Potion> potion) {
        addPotions(entries, registryWrapper, Items.TIPPED_ARROW, potion);
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
     * Registers all item group events.
     */
    public static void initializeItemGroups() {
        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.INGREDIENTS, List.of(
                ModItems.SPEEDRUNNER_INGOT,
                ModItems.SPEEDRUNNER_NUGGET,
                ModItems.SPEEDRUNNER_PADDLE,
                ModItems.RAW_SPEEDRUNNER,
                ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE,
                ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE,
                ModItems.IGNEOUS_ROCK,
                ModItems.SPEEDRUNNER_STICK,
                ModItems.ENDER_MATTER,
                ModItems.EXPERIENCE_FRAGMENT
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.FOOD_AND_DRINKS, List.of(
                ModItems.SPEEDRUNNER_BULK,
                ModItems.ROTTEN_SPEEDRUNNER_BULK,
                ModItems.COOKED_FLESH,
                ModItems.PIGLIN_PORK,
                ModItems.COOKED_PIGLIN_PORK
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.SPAWN_EGGS, List.of(
                ModItems.GOLIATH_SPAWN_EGG
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.FUNCTIONAL_BLOCKS, List.of(
                ModItems.SPEEDRUNNERS_WORKBENCH,
                ModItems.DOOM_STONE,
                ModItems.DOOM_LOG,
                ModItems.DOOM_LEAVES
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.COMBAT, List.of(
                ModItems.SPEEDRUNNER_SWORD,
                ModItems.SPEEDRUNNER_SPEAR,
                ModItems.SPEEDRUNNER_HELMET,
                ModItems.SPEEDRUNNER_CHESTPLATE,
                ModItems.SPEEDRUNNER_LEGGINGS,
                ModItems.SPEEDRUNNER_BOOTS,
                ModItems.SPEEDRUNNER_BOW,
                ModItems.SPEEDRUNNER_CROSSBOW,
                ModItems.SPEEDRUNNER_SHIELD,
                ModItems.GOLDEN_SHIELD,
                ModItems.GOLDEN_SPEEDRUNNER_SWORD,
                ModItems.GOLDEN_SPEEDRUNNER_SPEAR,
                ModItems.GOLDEN_SPEEDRUNNER_HELMET,
                ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE,
                ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS,
                ModItems.GOLDEN_SPEEDRUNNER_BOOTS,
                ModItems.DRAGONS_SWORD,
                ModItems.DRAGON_FIREBALL,
                ModItems.RAID_ERADICATOR,
                ModItems.SPEEDRUNNERS_TOTEM,
                ModItems.KNOCKBACK_STICK,
                ModItems.SPEEDRUNNER_HARNESS,
                ModItems.GOLDEN_SPEEDRUNNER_HARNESS,
                ModItems.SPEEDRUNNER_NAUTILUS_ARMOR,
                ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES, List.of(
                ModItems.SPEEDRUNNER_SHOVEL,
                ModItems.SPEEDRUNNER_PICKAXE,
                ModItems.SPEEDRUNNER_AXE,
                ModItems.SPEEDRUNNER_HOE,
                ModItems.SPEEDRUNNER_SHEARS,
                ModItems.SPEEDRUNNER_FLINT_AND_STEEL,
                ModItems.GOLDEN_SPEEDRUNNER_SHOVEL,
                ModItems.GOLDEN_SPEEDRUNNER_PICKAXE,
                ModItems.GOLDEN_SPEEDRUNNER_AXE,
                ModItems.GOLDEN_SPEEDRUNNER_HOE,
                ModItems.ANNUL_EYE,
                ModItems.SPEEDRUNNERS_EYE,
                ModItems.INFERNO_EYE,
                ModItems.PIGLIN_AWAKENER,
                ModItems.BLAZE_SPOTTER,
                ModItems.ENDER_THRUSTER,
                ModItems.DRAGONS_PEARL,
                ModItems.INFINI_PEARL,
                ModItems.INVENTORY_PRESERVER,
                ModItems.SPEEDRUNNER_BOAT,
                ModItems.SPEEDRUNNER_CHEST_BOAT,
                ModItems.FIREPROOF_SPEEDRUNNER_BOAT,
                ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT,
                ModItems.DEAD_SPEEDRUNNER_BOAT,
                ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT,
                ModItems.CRIMSON_BOAT,
                ModItems.CRIMSON_CHEST_BOAT,
                ModItems.FIREPROOF_CRIMSON_BOAT,
                ModItems.FIREPROOF_CRIMSON_CHEST_BOAT,
                ModItems.WARPED_BOAT,
                ModItems.WARPED_CHEST_BOAT,
                ModItems.FIREPROOF_WARPED_BOAT,
                ModItems.FIREPROOF_WARPED_CHEST_BOAT
        ));

        Factories.factorItemLikesIntoCreativeTab(CreativeModeTabs.BUILDING_BLOCKS, List.of(
                ModItems.SPEEDRUNNER_BLOCK,
                ModItems.RAW_SPEEDRUNNER_BLOCK,
                ModItems.SPEEDRUNNER_ORE,
                ModItems.DEEPSLATE_SPEEDRUNNER_ORE,
                ModItems.NETHER_SPEEDRUNNER_ORE,
                ModItems.IGNEOUS_ORE,
                ModItems.DEEPSLATE_IGNEOUS_ORE,
                ModItems.NETHER_IGNEOUS_ORE,
                ModItems.EXPERIENCE_ORE,
                ModItems.DEEPSLATE_EXPERIENCE_ORE,
                ModItems.NETHER_EXPERIENCE_ORE,
                ModItems.FLESH_BLOCK,
                ModItems.THRUSTED_BLOCK,
                ModItems.SPEEDRUNNER_LOG,
                ModItems.SPEEDRUNNER_WOOD,
                ModItems.DEAD_SPEEDRUNNER_LOG,
                ModItems.DEAD_SPEEDRUNNER_WOOD,
                ModItems.SPEEDRUNNER_LEAVES,
                ModItems.DEAD_SPEEDRUNNER_LEAVES,
                ModItems.SPEEDRUNNER_SAPLING,
                ModItems.DEAD_SPEEDRUNNER_SAPLING,
                ModItems.DEAD_SPEEDRUNNER_BUSH,
                ModItems.SPEEDRUNNER_PLANKS,
                ModItems.DEAD_SPEEDRUNNER_PLANKS,
                ModItems.SPEEDRUNNER_SLAB,
                ModItems.DEAD_SPEEDRUNNER_SLAB,
                ModItems.SPEEDRUNNER_STAIRS,
                ModItems.DEAD_SPEEDRUNNER_STAIRS,
                ModItems.SPEEDRUNNER_FENCE,
                ModItems.DEAD_SPEEDRUNNER_FENCE,
                ModItems.SPEEDRUNNER_FENCE_GATE,
                ModItems.DEAD_SPEEDRUNNER_FENCE_GATE,
                ModItems.SPEEDRUNNER_TRAPDOOR,
                ModItems.DEAD_SPEEDRUNNER_TRAPDOOR,
                ModItems.METAL_SPEEDRUNNER_TRAPDOOR,
                ModItems.SPEEDRUNNER_BUTTON,
                ModItems.DEAD_SPEEDRUNNER_BUTTON,
                ModItems.SPEEDRUNNER_PRESSURE_PLATE,
                ModItems.DEAD_SPEEDRUNNER_PRESSURE_PLATE,
                ModItems.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE,
                ModItems.SPEEDRUNNER_DOOR,
                ModItems.METAL_SPEEDRUNNER_DOOR,
                ModItems.DEAD_SPEEDRUNNER_DOOR
        ));
    }
}