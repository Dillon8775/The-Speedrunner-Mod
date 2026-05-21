package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.equipment.ModArmorMaterials;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.accessor.ItemsInvoker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {
    public static final Identifier SPEEDRUNNER_ARMOR_PATH = Identifier.parse("speedrunnermod:speedrunner");
    public static final Identifier GOLDEN_SPEEDRUNNER_ARMOR_PATH = Identifier.parse("speedrunnermod:golden_speedrunner");

    public static final Item SPEEDRUNNER_INGOT = ItemsInvoker.invokeRegisterItem(of("speedrunner_ingot"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = ItemsInvoker.invokeRegisterItem(of("speedrunner_nugget"), Item::new);

    public static final Item RAW_SPEEDRUNNER = ItemsInvoker.invokeRegisterItem(of("raw_speedrunner"), Item::new);

    public static final Item SPEEDRUNNER_SPEAR = ItemsInvoker.invokeRegisterItem(of("speedrunner_spear"), settings -> new SpeedrunnerSpearItem(
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

    public static final Item GOLDEN_SPEEDRUNNER_SPEAR = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_spear"), settings -> new SpeedrunnerSpearItem(
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

    public static final Item SPEEDRUNNER_SWORD = ItemsInvoker.invokeRegisterItem(of("speedrunner_sword"), Item::new,
            new Item.Properties().sword(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F));

    public static final Item SPEEDRUNNER_SHOVEL = ItemsInvoker.invokeRegisterItem(of("speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, settings));

    public static final Item SPEEDRUNNER_PICKAXE = ItemsInvoker.invokeRegisterItem(of("speedrunner_pickaxe"), Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F));

    public static final Item SPEEDRUNNER_AXE = ItemsInvoker.invokeRegisterItem(of("speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, settings));

    public static final Item SPEEDRUNNER_HOE = ItemsInvoker.invokeRegisterItem(of("speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, settings));

    public static final Item SPEEDRUNNER_HELMET = ItemsInvoker.invokeRegisterItem(of("speedrunner_helmet"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_CHESTPLATE = ItemsInvoker.invokeRegisterItem(of("speedrunner_chestplate"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_LEGGINGS = ItemsInvoker.invokeRegisterItem(of("speedrunner_leggings"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOOTS = ItemsInvoker.invokeRegisterItem(of("speedrunner_boots"), settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.SPEEDRUNNER, 30, settings));

    public static final Item SPEEDRUNNER_BOW = ItemsInvoker.invokeRegisterItem(of("speedrunner_bow"), SpeedrunnerBowItem::new);

    public static final Item SPEEDRUNNER_CROSSBOW = ItemsInvoker.invokeRegisterItem(of("speedrunner_crossbow"), SpeedrunnerCrossbowItem::new);

    public static final Item SPEEDRUNNER_SHEARS = ItemsInvoker.invokeRegisterItem(of("speedrunner_shears"), SpeedrunnerShearsItem::new);

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = ItemsInvoker.invokeRegisterItem(of("speedrunner_flint_and_steel"), FlintAndSteelItem::new, new Item.Properties().stacksTo(1).durability(128));

    public static final Item SPEEDRUNNER_SHIELD = ItemsInvoker.invokeRegisterItem(of("speedrunner_shield"), SpeedrunnerShieldItem::new);

    public static final Item GOLDEN_SHIELD = ItemsInvoker.invokeRegisterItem(of("golden_shield"), GoldenShieldItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_sword"), Item::new,
            new Item.Properties().sword(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_pickaxe"), Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_helmet"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_chestplate"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_leggings"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = ItemsInvoker.invokeRegisterItem(of("golden_speedrunner_boots"), settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, 11, settings));

    public static final Item GOLDEN_UPGRADE_SMITHING_TEMPLATE = ItemsInvoker.invokeRegisterItem(of("golden_upgrade_smithing_template"), GoldenUpgradeSmithingTemplateItem::new);

    public static final Item SPEEDRUNNER_BULK = ItemsInvoker.invokeRegisterItem(of("speedrunner_bulk"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_bulk.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK, ModDataComponentTypes.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = ItemsInvoker.invokeRegisterItem(of("rotten_speedrunner_bulk"), Item::new,
            new Item.Properties().food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK, ModDataComponentTypes.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item COOKED_FLESH = ItemsInvoker.invokeRegisterItem(of("cooked_flesh"), Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_FLESH));

    public static final Item PIGLIN_PORK = ItemsInvoker.invokeRegisterItem(of("piglin_pork"), Item::new,
            new Item.Properties().food(ModFoodComponents.PIGLIN_PORK));

    public static final Item COOKED_PIGLIN_PORK = ItemsInvoker.invokeRegisterItem(of("cooked_piglin_pork"), Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_PIGLIN_PORK));

    public static final Item GOLIATH_SPAWN_EGG = ItemsInvoker.invokeRegisterItem(of("goliath_spawn_egg"), SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityType.GIANT)
    );

    public static final Item IGNEOUS_ROCK = ItemsInvoker.invokeRegisterItem(of("igneous_rock"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.igneous_rock.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_STICK = ItemsInvoker.invokeRegisterItem(of("speedrunner_stick"), Item::new);

    public static final Item SPEEDRUNNER_BOAT = ItemsInvoker.invokeRegisterItem(of("speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item SPEEDRUNNER_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_BOAT = ItemsInvoker.invokeRegisterItem(of("dead_speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("dead_speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_BOAT = ItemsInvoker.invokeRegisterItem(of("crimson_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_crimson_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("crimson_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_crimson_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_BOAT = ItemsInvoker.invokeRegisterItem(of("warped_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_warped_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("warped_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_CHEST_BOAT = ItemsInvoker.invokeRegisterItem(of("fireproof_warped_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item ENDER_MATTER = ItemsInvoker.invokeRegisterItem(of("ender_matter"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.ender_matter.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).stacksTo(16));

    public static final Item SPEEDRUNNER_PADDLE = ItemsInvoker.invokeRegisterItem(of("speedrunner_paddle"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_paddle.tooltip"));
        }
    }, new Item.Properties().stacksTo(16));

    public static final Item EXPERIENCE_FRAGMENT = ItemsInvoker.invokeRegisterItem(of("experience_fragment"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.experience_fragment.tooltip"));
        }
    });

    public static final Item INVENTORY_PRESERVER = ItemsInvoker.invokeRegisterItem(of("inventory_preserver"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.inventory_preserver.tooltip"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).durability(1));

    public static final Item ANNUL_EYE = ItemsInvoker.invokeRegisterItem(of("annul_eye"), AnnulEyeItem::new);
    public static final Item SPEEDRUNNERS_EYE = ItemsInvoker.invokeRegisterItem(of("speedrunners_eye"), SpeedrunnersEyeItem::new);
    public static final Item INFERNO_EYE = ItemsInvoker.invokeRegisterItem(of("inferno_eye"), InfernoEyeItem::new);
    public static final Item PIGLIN_AWAKENER = ItemsInvoker.invokeRegisterItem(of("piglin_awakener"), PiglinAwakenerItem::new);
    public static final Item BLAZE_SPOTTER = ItemsInvoker.invokeRegisterItem(of("blaze_spotter"), BlazeSpotterItem::new);
    public static final Item RAID_ERADICATOR = ItemsInvoker.invokeRegisterItem(of("raid_eradicator"), RaidEradicatorItem::new);
    public static final Item ENDER_THRUSTER = ItemsInvoker.invokeRegisterItem(of("ender_thruster"), EnderThrusterItem::new);
    public static final Item DRAGONS_SWORD = ItemsInvoker.invokeRegisterItem(of("dragons_sword"), DragonsSwordItem::new);
    public static final Item DRAGONS_PEARL = ItemsInvoker.invokeRegisterItem(of("dragons_pearl"), DragonsPearlItem::new);
    public static final Item INFINI_PEARL = ItemsInvoker.invokeRegisterItem(of("infini_pearl"), InfiniPearlItem::new);
    public static final Item KNOCKBACK_STICK = ItemsInvoker.invokeRegisterItem(of("knockback_stick"), KnockbackStickItem::new);
    public static final Item SPEEDRUNNERS_TOTEM = ItemsInvoker.invokeRegisterItem(of("speedrunners_totem"), SpeedrunnersTotemItem::new);
    public static final Item DRAGONS_FIREBALL = ItemsInvoker.invokeRegisterItem(of("dragon_fireball"), DragonFireballItem::new);

    public static final Item SPEEDRUNNER_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_LOG);
    public static final Item STRIPPED_SPEEDRUNNER_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
    public static final Item SPEEDRUNNER_WOOD = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_WOOD);
    public static final Item STRIPPED_SPEEDRUNNER_WOOD = ItemsInvoker.invokeRegisterBlock(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item SPEEDRUNNER_LEAVES = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_LEAVES);
    public static final Item SPEEDRUNNER_SAPLING = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_SAPLING);
    public static final Item SPEEDRUNNER_PLANKS = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_PLANKS);
    public static final Item SPEEDRUNNER_SLAB = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_SLAB);
    public static final Item SPEEDRUNNER_STAIRS = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_STAIRS);
    public static final Item SPEEDRUNNER_FENCE = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_FENCE);
    public static final Item SPEEDRUNNER_FENCE_GATE = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_FENCE_GATE);
    public static final Item WOODEN_SPEEDRUNNER_TRAPDOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item WOODEN_SPEEDRUNNER_BUTTON = ItemsInvoker.invokeRegisterBlock(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item WOODEN_SPEEDRUNNER_PRESSURE_PLATE = ItemsInvoker.invokeRegisterBlock(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item WOODEN_SPEEDRUNNER_DOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item SPEEDRUNNER_SIGN = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item SPEEDRUNNER_HANGING_SIGN = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item DEAD_SPEEDRUNNER_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_LOG);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
    public static final Item DEAD_SPEEDRUNNER_WOOD = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_WOOD = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item DEAD_SPEEDRUNNER_LEAVES = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES);
    public static final Item DEAD_SPEEDRUNNER_SAPLING = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
    public static final Item DEAD_SPEEDRUNNER_PLANKS = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
    public static final Item DEAD_SPEEDRUNNER_SLAB = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_SLAB);
    public static final Item DEAD_SPEEDRUNNER_STAIRS = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
    public static final Item DEAD_SPEEDRUNNER_FENCE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_BUTTON = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_DOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item DEAD_SPEEDRUNNER_SIGN = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN, settings), new Item.Properties().requiredFeatures().stacksTo(16));

    public static final Item DEAD_SPEEDRUNNER_HANGING_SIGN = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item SPEEDRUNNER_TRAPDOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_TRAPDOOR);
    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
    public static final Item SPEEDRUNNER_DOOR = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_DOOR);
    public static final Item DEAD_SPEEDRUNNER_BUSH = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH);
    public static final Item SPEEDRUNNERS_WORKBENCH = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, settings) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunners_workbench.tooltip"));
        }
    });

    public static final Item SPEEDRUNNER_BLOCK = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_BLOCK);
    public static final Item RAW_SPEEDRUNNER_BLOCK = ItemsInvoker.invokeRegisterBlock(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
    public static final Item THRUSTED_BLOCK = ItemsInvoker.invokeRegisterBlock(ModBlocks.THRUSTED_BLOCK);
    public static final Item SPEEDRUNNER_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.SPEEDRUNNER_ORE);
    public static final Item DEEPSLATE_SPEEDRUNNER_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
    public static final Item NETHER_SPEEDRUNNER_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.NETHER_SPEEDRUNNER_ORE);
    public static final Item IGNEOUS_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.IGNEOUS_ORE);
    public static final Item DEEPSLATE_IGNEOUS_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEEPSLATE_IGNEOUS_ORE);
    public static final Item NETHER_IGNEOUS_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.NETHER_IGNEOUS_ORE);
    public static final Item EXPERIENCE_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item NETHER_EXPERIENCE_ORE = ItemsInvoker.invokeRegisterBlock(ModBlocks.NETHER_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item FLESH_BLOCK = ItemsInvoker.invokeRegisterBlock(ModBlocks.FLESH_BLOCK);
    public static final Item DOOM_STONE = ItemsInvoker.invokeRegisterBlock(ModBlocks.DOOM_STONE);
    public static final Item DOOM_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.DOOM_LOG);
    public static final Item STRIPPED_DOOM_LOG = ItemsInvoker.invokeRegisterBlock(ModBlocks.STRIPPED_DOOM_LOG);
    public static final Item DOOM_LEAVES = ItemsInvoker.invokeRegisterBlock(ModBlocks.DOOM_LEAVES);

    /**
     * Creates and registers an {@code item.}
     */
    private static ResourceKey<Item> of(String id) {
        return ResourceKey.create(Registries.ITEM, ofSpeedrunnerMod(id));
    }

    /**
     * Initializes all Speedrunner Mod {@code items.}
     */
    public static void initializeItems() {
        SpeedrunnerMod.debug("Initialized items.");
    }
}