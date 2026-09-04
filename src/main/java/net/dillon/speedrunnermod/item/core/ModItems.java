package net.dillon.speedrunnermod.item.core;

import net.dillon.dillonlib.factory.item.IgnitableFactory;
import net.dillon.dillonlib.mixin.accessor.ItemsInvoker;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.loot.ModContextInts;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.*;
import net.dillon.speedrunnermod.item.equipment.*;
import net.dillon.speedrunnermod.item.eye.*;
import net.dillon.speedrunnermod.item.material.ModArmorMaterials;
import net.dillon.speedrunnermod.item.material.ModEquipmentAssetKeys;
import net.dillon.speedrunnermod.item.material.ModToolMaterials;
import net.dillon.speedrunnermod.item.tool.*;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static net.dillon.dillonlib.mixin.accessor.ItemsInvoker.registerModBlock;
import static net.dillon.dillonlib.mixin.accessor.ItemsInvoker.registerModItem;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {

    public static final Item SPEEDRUNNER_INGOT = registerModItem(ModItemIds.SPEEDRUNNER_INGOT, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = registerModItem(ModItemIds.SPEEDRUNNER_NUGGET, Item::new);
    public static final Item RAW_SPEEDRUNNER = registerModItem(ModItemIds.RAW_SPEEDRUNNER, Item::new);

    public static final Item SPEEDRUNNER_SPEAR = registerModItem(ModItemIds.SPEEDRUNNER_SPEAR, properties -> new SpeedrunnerSpearItem(
            properties,
            ModToolMaterials.SPEEDRUNNER_SPEAR,
            5.5F,
            7.0F,
            0.225F,
            1.0F,
            1.0F,
            0.55F,
            2.75F,
            10.5F,
            6.65F,
            10.50F
    ));

    public static final Item GOLDEN_SPEEDRUNNER_SPEAR = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SPEAR, properties -> new SpeedrunnerSpearItem(
            properties,
            ModToolMaterials.GOLDEN_SPEEDRUNNER_SPEAR,
            6.0F,
            7.0F,
            0.335F,
            0.98F,
            0.75F,
            0.65F,
            3.25F,
            12.0F,
            8.0F,
            13.0F
    ));

    public static final Item SPEEDRUNNER_SWORD = registerModItem(ModItemIds.SPEEDRUNNER_SWORD, properties ->
            new SpeedrunnerToolItem.Sword(
                    ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F, false, properties));

    public static final Item SPEEDRUNNER_SHOVEL = registerModItem(ModItemIds.SPEEDRUNNER_SHOVEL, properties ->
            new SpeedrunnerToolItem.Shovel(
                    ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, properties));

    public static final Item SPEEDRUNNER_PICKAXE = registerModItem(ModItemIds.SPEEDRUNNER_PICKAXE, properties ->
            new SpeedrunnerToolItem.Tool(
                    ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, BlockTags.MINEABLE_WITH_PICKAXE, 3, -2.8F, 0.0F, properties));

    public static final Item SPEEDRUNNER_AXE = registerModItem(ModItemIds.SPEEDRUNNER_AXE, properties ->
            new SpeedrunnerToolItem.Axe(
                    ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, properties));

    public static final Item SPEEDRUNNER_HOE = registerModItem(ModItemIds.SPEEDRUNNER_HOE, properties ->
            new SpeedrunnerToolItem.Hoe(
                    ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, properties));

    public static final Item SPEEDRUNNER_HELMET = registerModItem(ModItemIds.SPEEDRUNNER_HELMET, properties ->
            new SpeedrunnerHelmetItem(
                    ModArmorMaterials.SPEEDRUNNER, properties, false));

    public static final Item SPEEDRUNNER_CHESTPLATE = registerModItem(ModItemIds.SPEEDRUNNER_CHESTPLATE, properties ->
            new SpeedrunnerChestplateItem(ModArmorMaterials.SPEEDRUNNER, properties, false));

    public static final Item SPEEDRUNNER_LEGGINGS = registerModItem(ModItemIds.SPEEDRUNNER_LEGGINGS, properties ->
            new SpeedrunnerLeggingsItem(
                    ModArmorMaterials.SPEEDRUNNER, properties, false));

    public static final Item SPEEDRUNNER_BOOTS = registerModItem(ModItemIds.SPEEDRUNNER_BOOTS, properties ->
            new SpeedrunnerBootsItem(
                    ModArmorMaterials.SPEEDRUNNER, properties, false));

    public static final Item SPEEDRUNNER_NAUTILUS_ARMOR = registerModItem(ModItemIds.SPEEDRUNNER_NAUTILUS_ARMOR, properties ->
            new SpeedrunnerNautilusArmorItem(
                    properties, ModArmorMaterials.SPEEDRUNNER, 0.06F, 0.8F));

    public static final Item SPEEDRUNNER_HARNESS = registerModItem(ModItemIds.SPEEDRUNNER_HARNESS, properties ->
            new SpeedrunnerHarnessItem(
                    properties, ModEquipmentAssetKeys.SPEEDRUNNER_HARDNESS, 0.5F));

    public static final Item SPEEDRUNNER_BOW = registerModItem(ModItemIds.SPEEDRUNNER_BOW, SpeedrunnerBowItem::new);
    public static final Item SPEEDRUNNER_CROSSBOW = registerModItem(ModItemIds.SPEEDRUNNER_CROSSBOW, SpeedrunnerCrossbowItem::new);
    public static final Item SPEEDRUNNER_SHEARS = registerModItem(ModItemIds.SPEEDRUNNER_SHEARS, SpeedrunnerShearsItem::new);
    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = registerModItem(ModItemIds.SPEEDRUNNER_FLINT_AND_STEEL, IgnitableFactory.FlintAndSteel::new,
            new Item.Properties().stacksTo(1).durability(128));

    public static final Item SPEEDRUNNER_SHIELD = registerModItem(ModItemIds.SPEEDRUNNER_SHIELD, properties ->
            new ModShieldItem(1.6F, -2.0F, 0.6F, 672, ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE, properties));
    public static final Item GOLDEN_SHIELD = registerModItem(ModItemIds.GOLDEN_SHIELD, properties ->
            new ModShieldItem(1.9F, -3.0F, 0.2F, 76, ModItemTags.GOLDEN_SHIELD_REPAIRABLE, properties));

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SWORD, properties ->
            new SpeedrunnerToolItem.Sword(
                    ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F, false, properties));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SHOVEL, properties ->
            new SpeedrunnerToolItem.Shovel(
                    ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F, properties));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_PICKAXE, properties ->
            new SpeedrunnerToolItem.Tool(
                    ModToolMaterials.GOLDEN_SPEEDRUNNER, BlockTags.MINEABLE_WITH_PICKAXE, 2, -2.8F, 0.0F, properties));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_AXE, properties ->
            new SpeedrunnerToolItem.Axe(
                    ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, properties));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HOE, properties ->
            new SpeedrunnerToolItem.Hoe(
                    ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, properties));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET, properties ->
            new SpeedrunnerHelmetItem(
                    ModArmorMaterials.GOLDEN_SPEEDRUNNER, properties, true));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE, properties ->
            new SpeedrunnerChestplateItem(ModArmorMaterials.GOLDEN_SPEEDRUNNER, properties, true));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS, properties ->
            new SpeedrunnerLeggingsItem(
                    ModArmorMaterials.GOLDEN_SPEEDRUNNER, properties, true));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS, properties ->
            new SpeedrunnerBootsItem(
                    ModArmorMaterials.GOLDEN_SPEEDRUNNER, properties, true));

    public static final Item GOLDEN_UPGRADE_SMITHING_TEMPLATE = ItemsInvoker.registerModItem(ModItemIds.GOLDEN_UPGRADE_SMITHING_TEMPLATE, GoldenUpgradeSmithingTemplateItem::new);
    public static final Item DRAGON_UPGRADE_SMITHING_TEMPLATE = ItemsInvoker.registerModItem(ModItemIds.DRAGON_UPGRADE_SMITHING_TEMPLATE, DragonUpgradeSmithingTemplateItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR, properties ->
            new SpeedrunnerNautilusArmorItem(
                    properties, ModArmorMaterials.GOLDEN_SPEEDRUNNER, 0.07F, 1.0F)
    );

    public static final Item GOLDEN_SPEEDRUNNER_HARNESS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HARNESS, properties ->
            new SpeedrunnerHarnessItem(
                    properties, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER_HARDNESS, 0.7F)
    );

    public static final Item SPEEDRUNNER_BULK = registerModItem(ModItemIds.SPEEDRUNNER_BULK, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_bulk.tooltip"));
        }
    }, new Item.Properties()
            .rarity(Rarity.RARE)
            .food(ModConsumables.SPEEDRUNNER_BULK, ModDataComponentTypes.SPEEDRUNNER_BULK)
    );

    public static final Item ROTTEN_SPEEDRUNNER_BULK = registerModItem(ModItemIds.ROTTEN_SPEEDRUNNER_BULK, Item::new,
            new Item.Properties()
                    .food(ModConsumables.ROTTEN_SPEEDRUNNER_BULK, ModDataComponentTypes.ROTTEN_SPEEDRUNNER_BULK)
    );

    public static final Item COOKED_FLESH = registerModItem(ModItemIds.COOKED_FLESH, Item::new,
            new Item.Properties()
                    .food(ModConsumables.COOKED_FLESH)
    );

    public static final Item PIGLIN_PORK = registerModItem(ModItemIds.PIGLIN_PORK, Item::new,
            new Item.Properties()
                    .food(ModConsumables.PIGLIN_PORK)
    );

    public static final Item COOKED_PIGLIN_PORK = registerModItem(ModItemIds.COOKED_PIGLIN_PORK, Item::new,
            new Item.Properties()
                    .food(ModConsumables.COOKED_PIGLIN_PORK)
    );

    public static final Item GOLIATH_SPAWN_EGG = registerModItem(ModItemIds.GOLIATH_SPAWN_EGG, SpawnEggItem::new,
            new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .spawnEgg(EntityTypes.GIANT)
    );

    public static final Item IGNEOUS_ROCK = registerModItem(ModItemIds.IGNEOUS_ROCK, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.igneous_rock.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_STICK = registerModItem(ModItemIds.SPEEDRUNNER_STICK, Item::new);

    public static final Item SPEEDRUNNER_BOAT = registerModItem(ModItemIds.SPEEDRUNNER_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.SPEEDRUNNER_BOAT, true, false, properties));

    public static final Item FIREPROOF_SPEEDRUNNER_BOAT = registerModItem(ModItemIds.FIREPROOF_SPEEDRUNNER_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, true, true, properties));

    public static final Item SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.SPEEDRUNNER_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, true, false, properties));

    public static final Item FIREPROOF_SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, true, true, properties));

    public static final Item DEAD_SPEEDRUNNER_BOAT = registerModItem(ModItemIds.DEAD_SPEEDRUNNER_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, true, false, properties));

    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.DEAD_SPEEDRUNNER_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, true, false, properties));

    public static final Item CRIMSON_BOAT = registerModItem(ModItemIds.CRIMSON_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.CRIMSON_BOAT, false, false, properties));

    public static final Item FIREPROOF_CRIMSON_BOAT = registerModItem(ModItemIds.FIREPROOF_CRIMSON_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_CRIMSON_BOAT, false, true, properties));

    public static final Item CRIMSON_CHEST_BOAT = registerModItem(ModItemIds.CRIMSON_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.CRIMSON_CHEST_BOAT, false, false, properties));

    public static final Item FIREPROOF_CRIMSON_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_CRIMSON_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, false, true, properties));

    public static final Item WARPED_BOAT = registerModItem(ModItemIds.WARPED_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.WARPED_BOAT, false, false, properties));

    public static final Item FIREPROOF_WARPED_BOAT = registerModItem(ModItemIds.FIREPROOF_WARPED_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_WARPED_BOAT, false, true, properties));

    public static final Item WARPED_CHEST_BOAT = registerModItem(ModItemIds.WARPED_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.WARPED_CHEST_BOAT, false, false, properties));

    public static final Item FIREPROOF_WARPED_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_WARPED_CHEST_BOAT, properties ->
            new SpeedrunnerBoatItem(
                    ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, false, true, properties));

    public static final Item ENDER_MATTER = registerModItem(ModItemIds.ENDER_MATTER, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.ender_matter.tooltip"));
        }
    }, new Item.Properties()
            .rarity(Rarity.RARE)
            .stacksTo(16)
    );

    public static final Item SPEEDRUNNER_PADDLE = registerModItem(ModItemIds.SPEEDRUNNER_PADDLE, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_paddle.tooltip"));
        }
    }, new Item.Properties()
            .stacksTo(16)
    );

    public static final Item EXPERIENCE_FRAGMENT = registerModItem(ModItemIds.EXPERIENCE_FRAGMENT, properties -> new Item(
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.experience_fragment.tooltip"));
        }
    }, new Item.Properties()
            .rarity(Rarity.UNCOMMON)
    );

    public static final Item INVENTORY_PRESERVER = registerModItem(ModItemIds.INVENTORY_PRESERVER, InventoryPreserverItem::new);

    public static final Item ANNUL_EYE = registerModItem(ModItemIds.ANNUL_EYE, AnnulEyeItem::new);
    public static final Item SPEEDRUNNERS_EYE = registerModItem(ModItemIds.SPEEDRUNNERS_EYE, SpeedrunnersEyeItem::new);
    public static final Item INFERNO_EYE = registerModItem(ModItemIds.INFERNO_EYE, InfernoEyeItem::new);
    public static final Item PIGLIN_AWAKENER = registerModItem(ModItemIds.PIGLIN_AWAKENER, PiglinAwakenerItem::new);
    public static final Item BLAZE_SPOTTER = registerModItem(ModItemIds.BLAZE_SPOTTER, BlazeSpotterItem::new);
    public static final Item RAID_ERADICATOR = registerModItem(ModItemIds.RAID_ERADICATOR, RaidEradicatorItem::new);
    public static final Item ENDER_THRUSTER = registerModItem(ModItemIds.ENDER_THRUSTER, EnderThrusterItem::new);
    public static final Item DRAGONS_SWORD = registerModItem(ModItemIds.DRAGONS_SWORD, DragonsSwordItem::new);
    public static final Item DRAGONS_PEARL = registerModItem(ModItemIds.DRAGONS_PEARL, DragonsPearlItem::new);
    public static final Item INFINI_PEARL = registerModItem(ModItemIds.INFINI_PEARL, InfiniPearlItem::new);
    public static final Item KNOCKBACK_STICK = registerModItem(ModItemIds.KNOCKBACK_STICK, KnockbackStickItem::new);
    public static final Item SPEEDRUNNERS_TOTEM = registerModItem(ModItemIds.SPEEDRUNNERS_TOTEM, SpeedrunnersTotemItem::new);
    public static final Item DRAGON_FIREBALL = registerModItem(ModItemIds.DRAGON_FIREBALL, DragonFireballItem::new);

    public static final Item SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LOG, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_WOOD, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_LEAVES = registerModBlock(ModBlockItemIds.SPEEDRUNNER_LEAVES, ModBlocks.SPEEDRUNNER_LEAVES);
    public static final Item SPEEDRUNNER_SAPLING = registerModBlock(ModBlockItemIds.SPEEDRUNNER_SAPLING, ModBlocks.SPEEDRUNNER_SAPLING, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_PLANKS = registerModBlock(ModBlockItemIds.SPEEDRUNNER_PLANKS, ModBlocks.SPEEDRUNNER_PLANKS, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_SLAB = registerModBlock(ModBlockItemIds.SPEEDRUNNER_SLAB, ModBlocks.SPEEDRUNNER_SLAB, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_STAIRS = registerModBlock(ModBlockItemIds.SPEEDRUNNER_STAIRS, ModBlocks.SPEEDRUNNER_STAIRS, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_FENCE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_FENCE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_FENCE_GATE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_FENCE_GATE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_BUTTON = registerModBlock(ModBlockItemIds.SPEEDRUNNER_BUTTON, ModBlocks.SPEEDRUNNER_BUTTON, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.SPEEDRUNNER_PRESSURE_PLATE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR, ModBlocks.SPEEDRUNNER_TRAPDOOR, p -> p.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS));
    public static final Item SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.SPEEDRUNNER_DOOR, ModBlocks.SPEEDRUNNER_DOOR, (block, properties) -> new DoubleHighBlockItem(block, properties.cookingFuel(ModContextInts.COOKING_TIME_SPEEDRUNNER_ITEMS)));

    public static final Item DEAD_SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LOG, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_WOOD, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_LEAVES = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES, ModBlocks.DEAD_SPEEDRUNNER_LEAVES);
    public static final Item DEAD_SPEEDRUNNER_SAPLING = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_SAPLING, ModBlocks.DEAD_SPEEDRUNNER_SAPLING, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_BUSH = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH, ModBlocks.DEAD_SPEEDRUNNER_BUSH);
    public static final Item DEAD_SPEEDRUNNER_PLANKS = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS, ModBlocks.DEAD_SPEEDRUNNER_PLANKS, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_SLAB = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB, ModBlocks.DEAD_SPEEDRUNNER_SLAB, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_STAIRS = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS, ModBlocks.DEAD_SPEEDRUNNER_STAIRS, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_FENCE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_FENCE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_BUTTON = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON, ModBlocks.DEAD_SPEEDRUNNER_BUTTON, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.DEAD_SPEEDRUNNER_PRESSURE_PLATE, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR, ModBlocks.DEAD_SPEEDRUNNER_TRAPDOOR, p -> p.cookingFuel(ModContextInts.COOKING_TIME_DEAD_SPEEDRUNNER_ITEMS));
    public static final Item DEAD_SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR, ModBlocks.DEAD_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    @Deprecated(forRemoval = true)
    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE);
    @Deprecated(forRemoval = true)
    public static final Item METAL_SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.METAL_SPEEDRUNNER_TRAPDOOR, ModBlocks.METAL_SPEEDRUNNER_TRAPDOOR);
    @Deprecated(forRemoval = true)
    public static final Item METAL_SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.METAL_SPEEDRUNNER_DOOR, ModBlocks.METAL_SPEEDRUNNER_DOOR);

    public static final Item SPEEDRUNNERS_WORKBENCH = registerModBlock(ModBlockItemIds.SPEEDRUNNERS_WORKBENCH, ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, properties) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            properties) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunners_workbench.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_BLOCK = registerModBlock(ModBlockItemIds.SPEEDRUNNER_BLOCK, ModBlocks.SPEEDRUNNER_BLOCK);
    public static final Item RAW_SPEEDRUNNER_BLOCK = registerModBlock(ModBlockItemIds.RAW_SPEEDRUNNER_BLOCK, ModBlocks.RAW_SPEEDRUNNER_BLOCK);
    public static final Item THRUSTED_BLOCK = registerModBlock(ModBlockItemIds.THRUSTED_BLOCK, ModBlocks.THRUSTED_BLOCK);
    public static final Item SPEEDRUNNER_ORE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_ORE, ModBlocks.SPEEDRUNNER_ORE);
    public static final Item DEEPSLATE_SPEEDRUNNER_ORE = registerModBlock(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
    public static final Item NETHER_SPEEDRUNNER_ORE = registerModBlock(ModBlockItemIds.NETHER_SPEEDRUNNER_ORE, ModBlocks.NETHER_SPEEDRUNNER_ORE);
    public static final Item IGNEOUS_ORE = registerModBlock(ModBlockItemIds.IGNEOUS_ORE, ModBlocks.IGNEOUS_ORE);
    public static final Item DEEPSLATE_IGNEOUS_ORE = registerModBlock(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE, ModBlocks.DEEPSLATE_IGNEOUS_ORE);
    public static final Item NETHER_IGNEOUS_ORE = registerModBlock(ModBlockItemIds.NETHER_IGNEOUS_ORE, ModBlocks.NETHER_IGNEOUS_ORE);
    public static final Item EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.EXPERIENCE_ORE, ModBlocks.EXPERIENCE_ORE);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE, ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
    public static final Item NETHER_EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.NETHER_EXPERIENCE_ORE, ModBlocks.NETHER_EXPERIENCE_ORE);
    public static final Item FLESH_BLOCK = registerModBlock(ModBlockItemIds.FLESH_BLOCK, ModBlocks.FLESH_BLOCK);
    public static final Item DOOM_STONE = registerModBlock(ModBlockItemIds.DOOM_STONE, ModBlocks.DOOM_STONE);
    public static final Item DOOM_LOG = registerModBlock(ModBlockItemIds.DOOM_LOG, ModBlocks.DOOM_LOG);
    public static final Item DOOM_LEAVES = registerModBlock(ModBlockItemIds.DOOM_LEAVES, ModBlocks.DOOM_LEAVES);

    /**
     * Initializes all Speedrunner Mod {@code items.}
     */
    public static void initializeItems() {
        SpeedrunnerMod.LOGGER.debug("Initialized items.");
    }
}