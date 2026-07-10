package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code enchantments.}
 */
public class ModEnchantments {
    // For boots, grants the player the speed effect, amplifier increased for each level
    public static final ResourceKey<Enchantment> DASH = create("dash");
    // For items that have a "cooldown" (shields, ender pearls, chorus fruit, etc.), this lowers the cooldown on those items
    public static final ResourceKey<Enchantment> COOLDOWN = create("cooldown");
    // Inflicts target with wither effect for 3-7 seconds (based on level), and gives increased chance of wither skeleton skulls
    public static final ResourceKey<Enchantment> WITHERED = create("withered");

    /**
     * Bootstraps all speedrunner mod enchantments.
     */
    public static void bootstrap(BootstrapContext<Enchantment> registerable) {
        HolderGetter<Item> itemLookup = registerable.lookup(Registries.ITEM);

        register(registerable, DASH, Enchantment.enchantment(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), // The items that this enchantment can be applied to
                                7, // Probability of appearing in the enchantment table
                                3, // Maximum enchantment level
                                Enchantment.dynamicCost(1, 6), // Cost per level (base)
                                Enchantment.dynamicCost(12, 8), // Cost per level (maximum)
                                7, // Anvil applying cost
                                EquipmentSlotGroup.FEET))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ofSpeedrunnerMod("dash_increased_speed"),
                                Attributes.MOVEMENT_SPEED,
                                LevelBasedValue.perLevel(0.1F),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ofSpeedrunnerMod("dash_increased_water_movement"),
                                Attributes.WATER_MOVEMENT_EFFICIENCY,
                                LevelBasedValue.perLevel(0.05F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ofSpeedrunnerMod("dash_increased_lava_movement"),
                                ModAttributes.LAVA_MOVEMENT_EFFICIENCY,
                                LevelBasedValue.perLevel(0.06F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
        );

        register(registerable, ModEnchantments.COOLDOWN, Enchantment.enchantment(
                                Enchantment.definition(
                                        itemLookup.getOrThrow(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS),
                                        8, // Probability of appearing in the enchantment table
                                        3, // Maximum enchantment level
                                        Enchantment.dynamicCost(1, 7), // Cost per level (base)
                                        Enchantment.dynamicCost(11, 7), // Cost per level (maximum)
                                        5, // Anvil applying cost
                                        EquipmentSlotGroup.MAINHAND)
                        )
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ofSpeedrunnerMod("additional_cooldown_cooldown_enchantment"),
                                        ModAttributes.BONUS_COOLDOWN,
                                        LevelBasedValue.perLevel(-0.5F),
                                        AttributeModifier.Operation.ADD_VALUE
                                )
                        )
        );

        register(registerable, ModEnchantments.WITHERED, Enchantment.enchantment(
                                Enchantment.definition(
                                        itemLookup.getOrThrow(ItemTags.SWORDS),
                                        4, // Probability of appearing in the enchantment table
                                        5, // Maximum enchantment level
                                        Enchantment.dynamicCost(1, 10), // Cost per level (base)
                                        Enchantment.dynamicCost(23, 4), // Cost per level (maximum)
                                        3, // Anvil applying cost
                                        EquipmentSlotGroup.MAINHAND)
                        )
                        .withEffect(
                                EnchantmentEffectComponents.ATTRIBUTES,
                                new EnchantmentAttributeEffect(
                                        ofSpeedrunnerMod("withered_effect_withered_enchantment"),
                                        ModAttributes.WITHERED_EFFECT,
                                        LevelBasedValue.perLevel(1.0F),
                                        AttributeModifier.Operation.ADD_VALUE
                                )
                        )
        );
    }

    /**
     * Registers an enchantment.
     */
    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.identifier()));
    }

    /**
     * Creates an enchantment resource key.
     */
    private static ResourceKey<Enchantment> create(String path) {
        return ResourceKey.create(Registries.ENCHANTMENT, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code enchantments.}
     */
    public static void initializeEnchantments() {
        SpeedrunnerMod.debug("Initialized enchantments.");
    }
}