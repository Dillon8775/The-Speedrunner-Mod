package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.equipment.ModArmorMaterials;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {
    private static final Text GOLDEN_SPEEDRUNNER_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.applies_to"))).formatted(Formatting.GOLD);
    private static final Text GOLDEN_SPEEDRUNNER_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.ingredients"))).formatted(Formatting.AQUA);
    private static final Text GOLDEN_SPEEDRUNNER_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.base_slot_description")));
    private static final Text GOLDEN_SPEEDRUNNER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.additions_slot_description")));
    public static final Identifier SPEEDRUNNER_ARMOR_PATH = Identifier.of("speedrunnermod:speedrunner");
    public static final Identifier GOLDEN_SPEEDRUNNER_ARMOR_PATH = Identifier.of("speedrunnermod:golden_speedrunner");

    public static final Item SPEEDRUNNER_INGOT = Items.register(of("speedrunner_ingot"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line1").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line2").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line3").formatted(Formatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = Items.register(of("speedrunner_nugget"), Item::new);

    public static final Item RAW_SPEEDRUNNER = Items.register(of("raw_speedrunner"), Item::new);

    public static final Item SPEEDRUNNER_SWORD = Items.register(of("speedrunner_sword"), settings -> new SpeedrunnerSwordItem(
            5, false, settings));

    public static final Item SPEEDRUNNER_SHOVEL = Items.register(of("speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, settings));

    public static final Item SPEEDRUNNER_PICKAXE = Items.register(of("speedrunner_pickaxe"), Item::new,
            new Item.Settings().pickaxe(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F));

    public static final Item SPEEDRUNNER_AXE = Items.register(of("speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, settings));

    public static final Item SPEEDRUNNER_HOE = Items.register(of("speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, settings));

    public static final Item SPEEDRUNNER_HELMET = Items.register(of("speedrunner_helmet"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.SPEEDRUNNER, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(30))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_CHESTPLATE = Items.register(of("speedrunner_chestplate"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.SPEEDRUNNER, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(30))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_LEGGINGS = Items.register(of("speedrunner_leggings"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.SPEEDRUNNER, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(30))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOOTS = Items.register(of("speedrunner_boots"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.SPEEDRUNNER, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(30))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOW = Items.register(of("speedrunner_bow"), SpeedrunnerBowItem::new);

    public static final Item SPEEDRUNNER_CROSSBOW = Items.register(of("speedrunner_crossbow"), SpeedrunnerCrossbowItem::new);

    public static final Item SPEEDRUNNER_SHEARS = Items.register(of("speedrunner_shears"), SpeedrunnerShearsItem::new);

    public static final Item SPEEDRUNNER_FLINT_AND_STEEL = Items.register(of("speedrunner_flint_and_steel"), FlintAndSteelItem::new, new Item.Settings().maxCount(1).maxDamage(128));

    public static final Item SPEEDRUNNER_SHIELD = Items.register(of("speedrunner_shield"), SpeedrunnerShieldItem::new);

    public static final Item GOLDEN_SPEEDRUNNER_SWORD = Items.register(of("golden_speedrunner_sword"), settings -> new SpeedrunnerSwordItem(
            4, true, settings));

    public static final Item GOLDEN_SPEEDRUNNER_SHOVEL = Items.register(of("golden_speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2.5F, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = Items.register(of("golden_speedrunner_pickaxe"), Item::new,
            new Item.Settings().pickaxe(ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = Items.register(of("golden_speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = Items.register(of("golden_speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = Items.register(of("golden_speedrunner_helmet"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.HELMET)
                    .maxDamage(EquipmentType.HELMET.getMaxDamage(11))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = Items.register(of("golden_speedrunner_chestplate"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.CHESTPLATE)
                    .maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(11))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = Items.register(of("golden_speedrunner_leggings"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.LEGGINGS)
                    .maxDamage(EquipmentType.LEGGINGS.getMaxDamage(11))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = Items.register(of("golden_speedrunner_boots"), Item::new,
            new Item.Settings()
                    .maxCount(1)
                    .armor(ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.BOOTS)
                    .maxDamage(EquipmentType.BOOTS.getMaxDamage(11))
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE = Items.register(of("golden_speedrunner_upgrade_smithing_template"), settings -> new SmithingTemplateItem(
            GOLDEN_SPEEDRUNNER_UPGRADE_APPLIES_TO_TEXT,
            GOLDEN_SPEEDRUNNER_INGREDIENTS_TEXT,
            GOLDEN_SPEEDRUNNER_BASE_SLOT_DESCRIPTION_TEXT,
            GOLDEN_SPEEDRUNNER_ADDITIONS_SLOT_DESCRIPTION_TEXT,
            SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
            settings));

    public static final Item SPEEDRUNNER_BULK = Items.register(of("speedrunner_bulk"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line1"));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line2"));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line3"));
        }
    }, new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.SPEEDRUNNER_BULK, ModDataComponentTypes.SPEEDRUNNER_BULK));

    public static final Item ROTTEN_SPEEDRUNNER_BULK = Items.register(of("rotten_speedrunner_bulk"), Item::new,
            new Item.Settings().food(ModFoodComponents.ROTTEN_SPEEDRUNNER_BULK, ModDataComponentTypes.ROTTEN_SPEEDRUNNER_BULK));

    public static final Item COOKED_FLESH = Items.register(of("cooked_flesh"), Item::new,
            new Item.Settings().food(ModFoodComponents.COOKED_FLESH));

    public static final Item PIGLIN_PORK = Items.register(of("piglin_pork"), Item::new,
            new Item.Settings().food(ModFoodComponents.PIGLIN_PORK));

    public static final Item COOKED_PIGLIN_PORK = Items.register(of("cooked_piglin_pork"), Item::new,
            new Item.Settings().food(ModFoodComponents.COOKED_PIGLIN_PORK));

    public static final Item IGNEOUS_ROCK = Items.register(of("igneous_rock"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.igneous_rock.tooltip").formatted(Formatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_STICK = Items.register(of("speedrunner_stick"), Item::new);

    public static final Item SPEEDRUNNER_BOAT = Items.register(of("speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item SPEEDRUNNER_CHEST_BOAT = Items.register(of("speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item DEAD_SPEEDRUNNER_BOAT = Items.register(of("dead_speedrunner_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, settings), new Item.Settings().maxCount(1));

    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = Items.register(of("dead_speedrunner_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, settings), new Item.Settings().maxCount(1));

    public static final Item CRIMSON_BOAT = Items.register(of("crimson_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item CRIMSON_CHEST_BOAT = Items.register(of("crimson_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.CRIMSON_CHEST_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item WARPED_BOAT = Items.register(of("warped_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item WARPED_CHEST_BOAT = Items.register(of("warped_chest_boat"), settings -> new BoatItem(
            ModEntityTypes.WARPED_CHEST_BOAT, settings), new Item.Settings().maxCount(1).fireproof());

    public static final Item WITHER_BONE = Items.register(of("wither_bone"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.wither_bone.tooltip").formatted(Formatting.GRAY));
        }
    });

    public static final Item ENDER_MATTER = Items.register(of("ender_matter"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.ender_matter.tooltip").formatted(Formatting.GRAY));
        }
    }, new Item.Settings().rarity(Rarity.RARE).maxCount(16));

    public static final Item WITHER_SWORD = Items.register(of("wither_sword"), WitherSwordItem::new);
    public static final Item ANNUL_EYE = Items.register(of("annul_eye"), AnnulEyeItem::new);
    public static final Item SPEEDRUNNERS_EYE = Items.register(of("speedrunners_eye"), SpeedrunnersEyeItem::new);
    public static final Item INFERNO_EYE = Items.register(of("inferno_eye"), InfernoEyeItem::new);
    public static final Item PIGLIN_AWAKENER = Items.register(of("piglin_awakener"), PiglinAwakenerItem::new);
    public static final Item BLAZE_SPOTTER = Items.register(of("blaze_spotter"), BlazeSpotterItem::new);
    public static final Item RAID_ERADICATOR = Items.register(of("raid_eradicator"), RaidEradicatorItem::new);
    public static final Item ENDER_THRUSTER = Items.register(of("ender_thruster"), EnderThrusterItem::new);
    public static final Item DRAGONS_SWORD = Items.register(of("dragons_sword"), DragonsSwordItem::new);
    public static final Item DRAGONS_PEARL = Items.register(of("dragons_pearl"), DragonsPearlItem::new);
    public static final Item INFINI_PEARL = Items.register(of("infini_pearl"), InfiniPearlItem::new);
    public static final Item KNOCKBACK_STICK = Items.register(of("knockback_stick"), KnockbackStickItem::new);
    public static final Item SPEEDRUNNERS_TOTEM = Items.register(of("speedrunners_totem"), SpeedrunnersTotemItem::new);

    public static final Item SPEEDRUNNER_LOG = Items.register(ModBlocks.SPEEDRUNNER_LOG);
    public static final Item STRIPPED_SPEEDRUNNER_LOG = Items.register(ModBlocks.STRIPPED_SPEEDRUNNER_LOG);
    public static final Item SPEEDRUNNER_WOOD = Items.register(ModBlocks.SPEEDRUNNER_WOOD);
    public static final Item STRIPPED_SPEEDRUNNER_WOOD = Items.register(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item SPEEDRUNNER_LEAVES = Items.register(ModBlocks.SPEEDRUNNER_LEAVES);
    public static final Item SPEEDRUNNER_SAPLING = Items.register(ModBlocks.SPEEDRUNNER_SAPLING);
    public static final Item SPEEDRUNNER_PLANKS = Items.register(ModBlocks.SPEEDRUNNER_PLANKS);
    public static final Item SPEEDRUNNER_SLAB = Items.register(ModBlocks.SPEEDRUNNER_SLAB);
    public static final Item SPEEDRUNNER_STAIRS = Items.register(ModBlocks.SPEEDRUNNER_STAIRS);
    public static final Item SPEEDRUNNER_FENCE = Items.register(ModBlocks.SPEEDRUNNER_FENCE);
    public static final Item SPEEDRUNNER_FENCE_GATE = Items.register(ModBlocks.SPEEDRUNNER_FENCE_GATE);
    public static final Item WOODEN_SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item WOODEN_SPEEDRUNNER_BUTTON = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item WOODEN_SPEEDRUNNER_DOOR = Items.register(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, TallBlockItem::new);

    public static final Item SPEEDRUNNER_SIGN = Items.register(ModBlocks.SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item SPEEDRUNNER_HANGING_SIGN = Items.register(ModBlocks.SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item DEAD_SPEEDRUNNER_LOG = Items.register(ModBlocks.DEAD_SPEEDRUNNER_LOG);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_LOG = Items.register(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
    public static final Item DEAD_SPEEDRUNNER_WOOD = Items.register(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
    public static final Item DEAD_STRIPPED_SPEEDRUNNER_WOOD = Items.register(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
    public static final Item DEAD_SPEEDRUNNER_LEAVES = Items.register(ModBlocks.DEAD_SPEEDRUNNER_LEAVES);
    public static final Item DEAD_SPEEDRUNNER_SAPLING = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);
    public static final Item DEAD_SPEEDRUNNER_PLANKS = Items.register(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
    public static final Item DEAD_SPEEDRUNNER_SLAB = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SLAB);
    public static final Item DEAD_SPEEDRUNNER_STAIRS = Items.register(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
    public static final Item DEAD_SPEEDRUNNER_FENCE = Items.register(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
    public static final Item DEAD_SPEEDRUNNER_FENCE_GATE = Items.register(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_BUTTON = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
    public static final Item DEAD_WOODEN_SPEEDRUNNER_DOOR = Items.register(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, TallBlockItem::new);

    public static final Item DEAD_SPEEDRUNNER_SIGN = Items.register(ModBlocks.DEAD_SPEEDRUNNER_SIGN, (block, settings) -> new SignItem(
            ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN, settings), new Item.Settings().requires().maxCount(16));

    public static final Item DEAD_SPEEDRUNNER_HANGING_SIGN = Items.register(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, (block, settings) -> new HangingSignItem(
            ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN, settings), new Item.Settings().maxCount(16));

    public static final Item SPEEDRUNNER_TRAPDOOR = Items.register(ModBlocks.SPEEDRUNNER_TRAPDOOR);
    public static final Item SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = Items.register(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE);
    public static final Item SPEEDRUNNER_DOOR = Items.register(ModBlocks.SPEEDRUNNER_DOOR);
    public static final Item DEAD_SPEEDRUNNER_BUSH = Items.register(ModBlocks.DEAD_SPEEDRUNNER_BUSH);
    public static final Item SPEEDRUNNERS_WORKBENCH = Items.register(ModBlocks.SPEEDRUNNERS_WORKBENCH, (block, settings) -> new BlockItem(ModBlocks.SPEEDRUNNERS_WORKBENCH,
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
        }
    });

    public static final Item SPEEDRUNNER_BLOCK = Items.register(ModBlocks.SPEEDRUNNER_BLOCK);
    public static final Item RAW_SPEEDRUNNER_BLOCK = Items.register(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
    public static final Item THRUSTED_BLOCK = Items.register(ModBlocks.THRUSTED_BLOCK);
    public static final Item SPEEDRUNNER_ORE = Items.register(ModBlocks.SPEEDRUNNER_ORE);
    public static final Item DEEPSLATE_SPEEDRUNNER_ORE = Items.register(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
    public static final Item NETHER_SPEEDRUNNER_ORE = Items.register(ModBlocks.NETHER_SPEEDRUNNER_ORE);
    public static final Item IGNEOUS_ORE = Items.register(ModBlocks.IGNEOUS_ORE);
    public static final Item DEEPSLATE_IGNEOUS_ORE = Items.register(ModBlocks.DEEPSLATE_IGNEOUS_ORE);
    public static final Item NETHER_IGNEOUS_ORE = Items.register(ModBlocks.NETHER_IGNEOUS_ORE);
    public static final Item EXPERIENCE_ORE = Items.register(ModBlocks.EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item DEEPSLATE_EXPERIENCE_ORE = Items.register(ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item NETHER_EXPERIENCE_ORE = Items.register(ModBlocks.NETHER_EXPERIENCE_ORE, ExperienceOreItem::new);
    public static final Item FLESH_BLOCK = Items.register(ModBlocks.FLESH_BLOCK);
    public static final Item DOOM_STONE = Items.register(ModBlocks.DOOM_STONE);
    public static final Item DOOM_LOG = Items.register(ModBlocks.DOOM_LOG);
    public static final Item STRIPPED_DOOM_LOG = Items.register(ModBlocks.STRIPPED_DOOM_LOG);
    public static final Item DOOM_LEAVES = Items.register(ModBlocks.DOOM_LEAVES);

    /**
     * Creates and registers an {@code item.}
     */
    private static RegistryKey<Item> of(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, ofSpeedrunnerMod(id));
    }

    /**
     * Initializes all Speedrunner Mod {@code items.}
     */
    public static void initializeItems() {
    }
}