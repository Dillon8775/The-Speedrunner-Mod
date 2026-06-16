package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.equipment.ModArmorMaterials;
import net.dillon.speedrunnermod.item.equipment.ModEquipmentAssetKeys;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.mixin.accessor.ItemsInvoker.registerModBlock;
import static net.dillon.speedrunnermod.mixin.accessor.ItemsInvoker.registerModItem;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {
    public static final Identifier SPEEDRUNNER_ARMOR_PATH = Identifier.parse("speedrunnermod:speedrunner");
    public static final Identifier GOLDEN_SPEEDRUNNER_ARMOR_PATH = Identifier.parse("speedrunnermod:golden_speedrunner");

    public static final Item SPEEDRUNNER_INGOT = registerModItem(ModItemIds.SPEEDRUNNER_INGOT, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = registerModItem(ModItemIds.SPEEDRUNNER_NUGGET, Item::new);
    public static final Item RAW_SPEEDRUNNER = registerModItem(ModItemIds.RAW_SPEEDRUNNER, Item::new);

    public static final Item SPEEDRUNNER_SPEAR = registerModItem(ModItemIds.SPEEDRUNNER_SPEAR, settings -> new SpeedrunnerSpearItem(
            settings,
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

    public static final Item GOLDEN_SPEEDRUNNER_SPEAR = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SPEAR, settings -> new SpeedrunnerSpearItem(
            settings,
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

    public static final Item SPEEDRUNNER_SWORD = registerModItem(ModItemIds.SPEEDRUNNER_SWORD, Item::new,
            new Item.Properties().sword(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F));

    public static final Item SPEEDRUNNER_SHOVEL = registerModItem(ModItemIds.SPEEDRUNNER_SHOVEL, settings -> new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, settings));

    public static final Item SPEEDRUNNER_PICKAXE = registerModItem(ModItemIds.SPEEDRUNNER_PICKAXE, Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F));

    public static final Item SPEEDRUNNER_AXE = registerModItem(ModItemIds.SPEEDRUNNER_AXE, settings -> new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, settings));

    public static final Item SPEEDRUNNER_HOE = registerModItem(ModItemIds.SPEEDRUNNER_HOE, settings -> new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, settings));

    public static final Item SPEEDRUNNER_HELMET = registerModItem(ModItemIds.SPEEDRUNNER_HELMET, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_CHESTPLATE = registerModItem(ModItemIds.SPEEDRUNNER_CHESTPLATE, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_LEGGINGS = registerModItem(ModItemIds.SPEEDRUNNER_LEGGINGS, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOOTS = registerModItem(ModItemIds.SPEEDRUNNER_BOOTS, settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.SPEEDRUNNER, 30, settings));

    public static final Item SPEEDRUNNER_NAUTILUS_ARMOR = registerModItem(ModItemIds.SPEEDRUNNER_NAUTILUS_ARMOR, settings ->
            new SpeedrunnerNautilusArmorItem(settings, ModArmorMaterials.SPEEDRUNNER, 0.06F, 1.5F));

    public static final Item SPEEDRUNNER_HARNESS = registerModItem(ModItemIds.SPEEDRUNNER_HARNESS, settings ->
            new SpeedrunnerHarnessItem(settings, ModEquipmentAssetKeys.SPEEDRUNNER_HARDNESS, 0.7F));

    public static final Item SPEEDRUNNER_BOW = registerModItem(ModItemIds.SPEEDRUNNER_BOW, SpeedrunnerBowItem::new);
    public static final Item SPEEDRUNNER_CROSSBOW = registerModItem(ModItemIds.SPEEDRUNNER_CROSSBOW, SpeedrunnerCrossbowItem::new);
    public static final Item SPEEDRUNNER_SHEARS = registerModItem(ModItemIds.SPEEDRUNNER_SHEARS, SpeedrunnerShearsItem::new);
    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = registerModItem(ModItemIds.SPEEDRUNNER_FLINT_AND_STEEL, FlintAndSteelItem::new,
            new Item.Properties().stacksTo(1).durability(128));

    public static final Item SPEEDRUNNER_SHIELD = registerModItem(ModItemIds.SPEEDRUNNER_SHIELD, SpeedrunnerShieldItem::new);
    public static final Item GOLDEN_SHIELD = registerModItem(ModItemIds.GOLDEN_SHIELD, GoldenShieldItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SWORD, Item::new,
            new Item.Properties().sword(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_SHOVEL, settings -> new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_PICKAXE, Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_AXE, settings -> new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HOE, settings -> new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS, Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS, settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, 11, settings));

    public static final Item GOLDEN_UPGRADE_SMITHING_TEMPLATE = registerModItem(ModItemIds.GOLDEN_UPGRADE_SMITHING_TEMPLATE, GoldenUpgradeSmithingTemplateItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR, settings ->
            new GoldenSpeedrunnerNautilusArmorItem(settings, ModArmorMaterials.GOLDEN_SPEEDRUNNER, 0.07F, 1.7F));

    public static final Item GOLDEN_SPEEDRUNNER_HARNESS = registerModItem(ModItemIds.GOLDEN_SPEEDRUNNER_HARNESS, settings ->
            new GoldenSpeedrunnerHarnessItem(settings, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER_HARDNESS, 0.5F));

    public static final Item SPEEDRUNNER_BULK = registerModItem(ModItemIds.SPEEDRUNNER_BULK, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_bulk.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK, ModDataComponentTypes.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = registerModItem(ModItemIds.ROTTEN_SPEEDRUNNER_BULK, Item::new,
            new Item.Properties().food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK, ModDataComponentTypes.ROTTEN_SPEEDRUNNER_BULK));
    public static final Item COOKED_FLESH = registerModItem(ModItemIds.COOKED_FLESH, Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_FLESH));
    public static final Item PIGLIN_PORK = registerModItem(ModItemIds.PIGLIN_PORK, Item::new,
            new Item.Properties().food(ModFoodComponents.PIGLIN_PORK));
    public static final Item COOKED_PIGLIN_PORK = registerModItem(ModItemIds.COOKED_PIGLIN_PORK, Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_PIGLIN_PORK));
    public static final Item GOLIATH_SPAWN_EGG = registerModItem(ModItemIds.GOLIATH_SPAWN_EGG, SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityTypes.GIANT)
    );

    public static final Item IGNEOUS_ROCK = registerModItem(ModItemIds.IGNEOUS_ROCK, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.igneous_rock.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_STICK = registerModItem(ModItemIds.SPEEDRUNNER_STICK, Item::new);

    public static final Item SPEEDRUNNER_BOAT = registerModItem(ModItemIds.SPEEDRUNNER_BOAT, settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_BOAT = registerModItem(ModItemIds.FIREPROOF_SPEEDRUNNER_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.SPEEDRUNNER_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_BOAT = registerModItem(ModItemIds.DEAD_SPEEDRUNNER_BOAT, settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = registerModItem(ModItemIds.DEAD_SPEEDRUNNER_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_BOAT = registerModItem(ModItemIds.CRIMSON_BOAT, settings -> new BoatItem(
            ModEntityTypes.CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_BOAT = registerModItem(ModItemIds.FIREPROOF_CRIMSON_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_CHEST_BOAT = registerModItem(ModItemIds.CRIMSON_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_CRIMSON_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_BOAT = registerModItem(ModItemIds.WARPED_BOAT, settings -> new BoatItem(
            ModEntityTypes.WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_BOAT = registerModItem(ModItemIds.FIREPROOF_WARPED_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_CHEST_BOAT = registerModItem(ModItemIds.WARPED_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_CHEST_BOAT = registerModItem(ModItemIds.FIREPROOF_WARPED_CHEST_BOAT, settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item ENDER_MATTER = registerModItem(ModItemIds.ENDER_MATTER, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.ender_matter.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).stacksTo(16));

    public static final Item SPEEDRUNNER_PADDLE = registerModItem(ModItemIds.SPEEDRUNNER_PADDLE, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_paddle.tooltip"));
        }
    }, new Item.Properties().stacksTo(16));

    public static final Item EXPERIENCE_FRAGMENT = registerModItem(ModItemIds.EXPERIENCE_FRAGMENT, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.experience_fragment.tooltip"));
        }
    });

    public static final Item INVENTORY_PRESERVER = registerModItem(ModItemIds.INVENTORY_PRESERVER, settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.inventory_preserver.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).durability(1));

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
    public static final Item DRAGONS_FIREBALL = registerModItem(ModItemIds.DRAGONS_FIREBALL, DragonFireballItem::new);

    public static final Item SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_LOG);
    public static final Item STRIPPED_SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.STRIPPED_SPEEDRUNNER_LOG, ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
    public static final Item SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_WOOD);
    public static final Item STRIPPED_SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item SPEEDRUNNER_LEAVES = registerModBlock(ModBlockItemIds.SPEEDRUNNER_LEAVES, ModBlocks.SPEEDRUNNER_LEAVES);
    public static final Item SPEEDRUNNER_SAPLING = registerModBlock(ModBlockItemIds.SPEEDRUNNER_SAPLING, ModBlocks.SPEEDRUNNER_SAPLING);
    public static final Item SPEEDRUNNER_PLANKS = registerModBlock(ModBlockItemIds.SPEEDRUNNER_PLANKS, ModBlocks.SPEEDRUNNER_PLANKS);
    public static final Item SPEEDRUNNER_SLAB = registerModBlock(ModBlockItemIds.SPEEDRUNNER_SLAB, ModBlocks.SPEEDRUNNER_SLAB);
    public static final Item SPEEDRUNNER_STAIRS = registerModBlock(ModBlockItemIds.SPEEDRUNNER_STAIRS, ModBlocks.SPEEDRUNNER_STAIRS);
    public static final Item SPEEDRUNNER_FENCE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_FENCE);
    public static final Item SPEEDRUNNER_FENCE_GATE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_FENCE_GATE);
    public static final Item SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR, ModBlocks.SPEEDRUNNER_TRAPDOOR);
    public static final Item SPEEDRUNNER_BUTTON = registerModBlock(ModBlockItemIds.SPEEDRUNNER_BUTTON, ModBlocks.SPEEDRUNNER_BUTTON);
    public static final Item SPEEDRUNNER_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.SPEEDRUNNER_DOOR, ModBlocks.SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item DEAD_SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_LOG);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_LOG = registerModBlock(ModBlockItemIds.DEAD_STRIPPED_SPEEDRUNNER_LOG, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
    public static final Item DEAD_SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_WOOD = registerModBlock(ModBlockItemIds.DEAD_STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item DEAD_SPEEDRUNNER_LEAVES = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES, ModBlocks.DEAD_SPEEDRUNNER_LEAVES);
    public static final Item DEAD_SPEEDRUNNER_SAPLING = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_SAPLING, ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
    public static final Item DEAD_SPEEDRUNNER_PLANKS = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
    public static final Item DEAD_SPEEDRUNNER_SLAB = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB, ModBlocks.DEAD_SPEEDRUNNER_SLAB);
    public static final Item DEAD_SPEEDRUNNER_STAIRS = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS, ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
    public static final Item DEAD_SPEEDRUNNER_FENCE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_FENCE);
    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
    public static final Item DEAD_SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR, ModBlocks.DEAD_SPEEDRUNNER_TRAPDOOR);
    public static final Item DEAD_SPEEDRUNNER_BUTTON = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON, ModBlocks.DEAD_SPEEDRUNNER_BUTTON);
    public static final Item DEAD_SPEEDRUNNER_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.DEAD_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item DEAD_SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR, ModBlocks.DEAD_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item METAL_SPEEDRUNNER_TRAPDOOR = registerModBlock(ModBlockItemIds.METAL_SPEEDRUNNER_TRAPDOOR, ModBlocks.METAL_SPEEDRUNNER_TRAPDOOR);
    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = registerModBlock(ModBlockItemIds.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item METAL_SPEEDRUNNER_DOOR = registerModBlock(ModBlockItemIds.METAL_SPEEDRUNNER_DOOR, ModBlocks.METAL_SPEEDRUNNER_DOOR);
    public static final Item DEAD_SPEEDRUNNER_BUSH = registerModBlock(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH, ModBlocks.DEAD_SPEEDRUNNER_BUSH);
    public static final Item SPEEDRUNNERS_WORKBENCH = registerModBlock(ModBlockItemIds.SPEEDRUNNERS_WORKBENCH, ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, settings) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            settings) {

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
    public static final Item EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.EXPERIENCE_ORE, ModBlocks.EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE, ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item NETHER_EXPERIENCE_ORE = registerModBlock(ModBlockItemIds.NETHER_EXPERIENCE_ORE, ModBlocks.NETHER_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item FLESH_BLOCK = registerModBlock(ModBlockItemIds.FLESH_BLOCK, ModBlocks.FLESH_BLOCK);
    public static final Item DOOM_STONE = registerModBlock(ModBlockItemIds.DOOM_STONE, ModBlocks.DOOM_STONE);
    public static final Item DOOM_LOG = registerModBlock(ModBlockItemIds.DOOM_LOG, ModBlocks.DOOM_LOG);
    public static final Item DOOM_LEAVES = registerModBlock(ModBlockItemIds.DOOM_LEAVES, ModBlocks.DOOM_LEAVES);

    /**
     * Initializes all Speedrunner Mod {@code items.}
     */
    public static void initializeItems() {
        SpeedrunnerMod.debug("Initialized items.");
    }
}