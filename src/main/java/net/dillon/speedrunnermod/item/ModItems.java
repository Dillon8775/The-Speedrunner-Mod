package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.equipment.ModArmorMaterials;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.ChatFormatting;
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

    public static final Item SPEEDRUNNER_INGOT = Items.registerItem(of("speedrunner_ingot"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line1").withStyle(ChatFormatting.GRAY));
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line2").withStyle(ChatFormatting.GRAY));
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line3").withStyle(ChatFormatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = Items.registerItem(of("speedrunner_nugget"), Item::new);

    public static final Item RAW_SPEEDRUNNER = Items.registerItem(of("raw_speedrunner"), Item::new);

    public static final Item SPEEDRUNNER_SWORD = Items.registerItem(of("speedrunner_sword"), Item::new,
            new Item.Properties().sword(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 5, -2.4F));

    public static final Item SPEEDRUNNER_SHOVEL = Items.registerItem(of("speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, settings));

    public static final Item SPEEDRUNNER_PICKAXE = Items.registerItem(of("speedrunner_pickaxe"), Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F));

    public static final Item SPEEDRUNNER_AXE = Items.registerItem(of("speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, settings));

    public static final Item SPEEDRUNNER_HOE = Items.registerItem(of("speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, settings));

    public static final Item SPEEDRUNNER_HELMET = Items.registerItem(of("speedrunner_helmet"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_CHESTPLATE = Items.registerItem(of("speedrunner_chestplate"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_LEGGINGS = Items.registerItem(of("speedrunner_leggings"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(30))
                    .setId(ResourceKey.create(Registries.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOOTS = Items.registerItem(of("speedrunner_boots"), settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.SPEEDRUNNER, 30, settings));

    public static final Item SPEEDRUNNER_BOW = Items.registerItem(of("speedrunner_bow"), SpeedrunnerBowItem::new);

    public static final Item SPEEDRUNNER_CROSSBOW = Items.registerItem(of("speedrunner_crossbow"), SpeedrunnerCrossbowItem::new);

    public static final Item SPEEDRUNNER_SHEARS = Items.registerItem(of("speedrunner_shears"), SpeedrunnerShearsItem::new);

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = Items.registerItem(of("speedrunner_flint_and_steel"), FlintAndSteelItem::new, new Item.Properties().stacksTo(1).durability(128));

    public static final Item SPEEDRUNNER_SHIELD = Items.registerItem(of("speedrunner_shield"), SpeedrunnerShieldItem::new);

    public static final Item GOLDEN_SHIELD = Items.registerItem(of("golden_shield"), GoldenShieldItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = Items.registerItem(of("golden_speedrunner_sword"), Item::new,
            new Item.Properties().sword(ModToolMaterials.GOLDEN_SPEEDRUNNER, 4, -2.4F));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = Items.registerItem(of("golden_speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = Items.registerItem(of("golden_speedrunner_pickaxe"), Item::new,
            new Item.Properties().pickaxe(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = Items.registerItem(of("golden_speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = Items.registerItem(of("golden_speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = Items.registerItem(of("golden_speedrunner_helmet"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = Items.registerItem(of("golden_speedrunner_chestplate"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = Items.registerItem(of("golden_speedrunner_leggings"), Item::new,
            new Item.Properties()
                    .stacksTo(1)
                    .humanoidArmor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(11))
                    .setId(ResourceKey.create(Registries.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = Items.registerItem(of("golden_speedrunner_boots"), settings -> new SpeedrunnerBootsItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, 11, settings));

    public static final Item GOLDEN_UPGRADE_SMITHING_TEMPLATE = Items.registerItem(of("golden_upgrade_smithing_template"), GoldenUpgradeSmithingTemplateItem::new);

    public static final Item SPEEDRUNNER_BULK = Items.registerItem(of("speedrunner_bulk"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line1"));
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line2"));
        }
    }, new Item.Properties().rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK, ModDataComponentTypes.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = Items.registerItem(of("rotten_speedrunner_bulk"), Item::new,
            new Item.Properties().food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK, ModDataComponentTypes.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item COOKED_FLESH = Items.registerItem(of("cooked_flesh"), Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_FLESH));

    public static final Item PIGLIN_PORK = Items.registerItem(of("piglin_pork"), Item::new,
            new Item.Properties().food(ModFoodComponents.PIGLIN_PORK));

    public static final Item COOKED_PIGLIN_PORK = Items.registerItem(of("cooked_piglin_pork"), Item::new,
            new Item.Properties().food(ModFoodComponents.COOKED_PIGLIN_PORK));

    public static final Item GOLIATH_SPAWN_EGG = Items.registerItem(of("goliath_spawn_egg"), SpawnEggItem::new,
            new Item.Properties().spawnEgg(EntityType.GIANT)
    );

    public static final Item IGNEOUS_ROCK = Items.registerItem(of("igneous_rock"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.igneous_rock.tooltip").withStyle(ChatFormatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_STICK = Items.registerItem(of("speedrunner_stick"), Item::new);

    public static final Item SPEEDRUNNER_BOAT = Items.registerItem(of("speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_BOAT = Items.registerItem(of("fireproof_speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item SPEEDRUNNER_CHEST_BOAT = Items.registerItem(of("speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_SPEEDRUNNER_CHEST_BOAT = Items.registerItem(of("fireproof_speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_BOAT = Items.registerItem(of("dead_speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = Items.registerItem(of("dead_speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_BOAT = Items.registerItem(of("crimson_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_BOAT = Items.registerItem(of("fireproof_crimson_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item CRIMSON_CHEST_BOAT = Items.registerItem(of("crimson_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_CRIMSON_CHEST_BOAT = Items.registerItem(of("fireproof_crimson_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_BOAT = Items.registerItem(of("warped_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_BOAT = Items.registerItem(of("fireproof_warped_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item WARPED_CHEST_BOAT = Items.registerItem(of("warped_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item FIREPROOF_WARPED_CHEST_BOAT = Items.registerItem(of("fireproof_warped_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, settings), new Item.Properties().stacksTo(1));

    public static final Item ENDER_MATTER = Items.registerItem(of("ender_matter"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.ender_matter.tooltip.line1").withStyle(ChatFormatting.DARK_PURPLE));
            textConsumer.accept(Component.translatable("item.speedrunnermod.ender_matter.tooltip.line2").withStyle(ChatFormatting.GRAY));
            textConsumer.accept(Component.translatable("item.speedrunnermod.ender_matter.tooltip.line3").withStyle(ChatFormatting.GRAY));
        }
    }, new Item.Properties().rarity(Rarity.RARE).stacksTo(16));

    public static final Item SPEEDRUNNER_PADDLE = Items.registerItem(of("speedrunner_paddle"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_paddle.tooltip.line1").withStyle(ChatFormatting.GRAY));
        }
    }, new Item.Properties().stacksTo(16));

    public static final Item EXPERIENCE_FRAGMENT = Items.registerItem(of("experience_fragment"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.experience_fragment.tooltip").withStyle(ChatFormatting.GRAY));
        }
    });

    public static final Item INVENTORY_PRESERVER = Items.registerItem(of("inventory_preserver"), settings -> new Item(
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.inventory_preserver.tooltip").withStyle(ChatFormatting.GRAY));
        }
    }, new Item.Properties().rarity(Rarity.RARE).durability(1));

    public static final Item ANNUL_EYE = Items.registerItem(of("annul_eye"), AnnulEyeItem::new);
    public static final Item SPEEDRUNNERS_EYE = Items.registerItem(of("speedrunners_eye"), SpeedrunnersEyeItem::new);
    public static final Item INFERNO_EYE = Items.registerItem(of("inferno_eye"), InfernoEyeItem::new);
    public static final Item PIGLIN_AWAKENER = Items.registerItem(of("piglin_awakener"), PiglinAwakenerItem::new);
    public static final Item BLAZE_SPOTTER = Items.registerItem(of("blaze_spotter"), BlazeSpotterItem::new);
    public static final Item RAID_ERADICATOR = Items.registerItem(of("raid_eradicator"), RaidEradicatorItem::new);
    public static final Item ENDER_THRUSTER = Items.registerItem(of("ender_thruster"), EnderThrusterItem::new);
    public static final Item DRAGONS_SWORD = Items.registerItem(of("dragons_sword"), DragonsSwordItem::new);
    public static final Item DRAGONS_PEARL = Items.registerItem(of("dragons_pearl"), DragonsPearlItem::new);
    public static final Item INFINI_PEARL = Items.registerItem(of("infini_pearl"), InfiniPearlItem::new);
    public static final Item KNOCKBACK_STICK = Items.registerItem(of("knockback_stick"), KnockbackStickItem::new);
    public static final Item SPEEDRUNNERS_TOTEM = Items.registerItem(of("speedrunners_totem"), SpeedrunnersTotemItem::new);
    public static final Item DRAGONS_FIREBALL = Items.registerItem(of("dragon_fireball"), DragonFireballItem::new);

    public static final Item SPEEDRUNNER_LOG = Items.registerBlock(ModBlocks.SPEEDRUNNER_LOG);
    public static final Item STRIPPED_SPEEDRUNNER_LOG = Items.registerBlock(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
    public static final Item SPEEDRUNNER_WOOD = Items.registerBlock(ModBlocks.SPEEDRUNNER_WOOD);
    public static final Item STRIPPED_SPEEDRUNNER_WOOD = Items.registerBlock(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item SPEEDRUNNER_LEAVES = Items.registerBlock(ModBlocks.SPEEDRUNNER_LEAVES);
    public static final Item SPEEDRUNNER_SAPLING = Items.registerBlock(ModBlocks.SPEEDRUNNER_SAPLING);
    public static final Item SPEEDRUNNER_PLANKS = Items.registerBlock(ModBlocks.SPEEDRUNNER_PLANKS);
    public static final Item SPEEDRUNNER_SLAB = Items.registerBlock(ModBlocks.SPEEDRUNNER_SLAB);
    public static final Item SPEEDRUNNER_STAIRS = Items.registerBlock(ModBlocks.SPEEDRUNNER_STAIRS);
    public static final Item SPEEDRUNNER_FENCE = Items.registerBlock(ModBlocks.SPEEDRUNNER_FENCE);
    public static final Item SPEEDRUNNER_FENCE_GATE = Items.registerBlock(ModBlocks.SPEEDRUNNER_FENCE_GATE);
    public static final Item WOODEN_SPEEDRUNNER_TRAPDOOR = Items.registerBlock(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item WOODEN_SPEEDRUNNER_BUTTON = Items.registerBlock(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.registerBlock(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item WOODEN_SPEEDRUNNER_DOOR = Items.registerBlock(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item SPEEDRUNNER_SIGN = Items.registerBlock(ModBlocks.SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item SPEEDRUNNER_HANGING_SIGN = Items.registerBlock(ModBlocks.SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item DEAD_SPEEDRUNNER_LOG = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_LOG);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_LOG = Items.registerBlock(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
    public static final Item DEAD_SPEEDRUNNER_WOOD = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_WOOD = Items.registerBlock(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item DEAD_SPEEDRUNNER_LEAVES = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES);
    public static final Item DEAD_SPEEDRUNNER_SAPLING = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
    public static final Item DEAD_SPEEDRUNNER_PLANKS = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
    public static final Item DEAD_SPEEDRUNNER_SLAB = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_SLAB);
    public static final Item DEAD_SPEEDRUNNER_STAIRS = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
    public static final Item DEAD_SPEEDRUNNER_FENCE = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = Items.registerBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_BUTTON = Items.registerBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.registerBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_DOOR = Items.registerBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, DoubleHighBlockItem::new);

    public static final Item DEAD_SPEEDRUNNER_SIGN = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN, settings), new Item.Properties().requiredFeatures().stacksTo(16));

    public static final Item DEAD_SPEEDRUNNER_HANGING_SIGN = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Properties().stacksTo(16));

    public static final Item SPEEDRUNNER_TRAPDOOR = Items.registerBlock(ModBlocks.SPEEDRUNNER_TRAPDOOR);
    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = Items.registerBlock(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
    public static final Item SPEEDRUNNER_DOOR = Items.registerBlock(ModBlocks.SPEEDRUNNER_DOOR);
    public static final Item DEAD_SPEEDRUNNER_BUSH = Items.registerBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH);
    public static final Item SPEEDRUNNERS_WORKBENCH = Items.registerBlock(ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, settings) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            settings) {

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").withStyle(ChatFormatting.GRAY));
            textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").withStyle(ChatFormatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_BLOCK = Items.registerBlock(ModBlocks.SPEEDRUNNER_BLOCK);
    public static final Item RAW_SPEEDRUNNER_BLOCK = Items.registerBlock(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
    public static final Item THRUSTED_BLOCK = Items.registerBlock(ModBlocks.THRUSTED_BLOCK);
    public static final Item SPEEDRUNNER_ORE = Items.registerBlock(ModBlocks.SPEEDRUNNER_ORE);
    public static final Item DEEPSLATE_SPEEDRUNNER_ORE = Items.registerBlock(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
    public static final Item NETHER_SPEEDRUNNER_ORE = Items.registerBlock(ModBlocks.NETHER_SPEEDRUNNER_ORE);
    public static final Item IGNEOUS_ORE = Items.registerBlock(ModBlocks.IGNEOUS_ORE);
    public static final Item DEEPSLATE_IGNEOUS_ORE = Items.registerBlock(ModBlocks.DEEPSLATE_IGNEOUS_ORE);
    public static final Item NETHER_IGNEOUS_ORE = Items.registerBlock(ModBlocks.NETHER_IGNEOUS_ORE);
    public static final Item EXPERIENCE_ORE = Items.registerBlock(ModBlocks.EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = Items.registerBlock(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item NETHER_EXPERIENCE_ORE = Items.registerBlock(ModBlocks.NETHER_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item FLESH_BLOCK = Items.registerBlock(ModBlocks.FLESH_BLOCK);
    public static final Item DOOM_STONE = Items.registerBlock(ModBlocks.DOOM_STONE);
    public static final Item DOOM_LOG = Items.registerBlock(ModBlocks.DOOM_LOG);
    public static final Item STRIPPED_DOOM_LOG = Items.registerBlock(ModBlocks.STRIPPED_DOOM_LOG);
    public static final Item DOOM_LEAVES = Items.registerBlock(ModBlocks.DOOM_LEAVES);

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