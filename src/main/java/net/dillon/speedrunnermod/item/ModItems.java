package net.dillon.speedrunnermod.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.item.equipment.ModArmorMaterials;
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

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * All Speedrunner Mod {@code items.}
 */
public class ModItems {
    private static final Text GOLDEN_SPEEDRUNNER_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.applies_to"))).formatted(Formatting.GOLD);
    private static final Text GOLDEN_SPEEDRUNNER_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.ingredients"))).formatted(Formatting.AQUA);
    private static final Text GOLDEN_SPEEDRUNNER_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.base_slot_description")));
    private static final Text GOLDEN_SPEEDRUNNER_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_speedrunner_upgrade.additions_slot_description")));
    public static final Identifier SPEEDRUNNER_ARMOR_PATH = Identifier.of(MOD_ID, "speedrunner");
    public static final Identifier GOLDEN_SPEEDRUNNER_ARMOR_PATH = Identifier.of(MOD_ID, "golden_speedrunner");

    public static final Item SPEEDRUNNER_INGOT = Items.register(of("speedrunner_ingot"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_ingot.tooltip.line3").formatted(Formatting.GRAY));
            }
        }
    });

    public static final Item SPEEDRUNNER_NUGGET = Items.register(of("speedrunner_nugget"), Item::new);

    public static final Item RAW_SPEEDRUNNER = Items.register(of("raw_speedrunner"), Item::new);

    public static final Item SPEEDRUNNER_SWORD = Items.register(of("speedrunner_sword"), settings -> new SpeedrunnerSwordItem(
            5, false, settings));

    public static final Item SPEEDRUNNER_SHOVEL = Items.register(of("speedrunner_shovel"), settings -> new ShovelItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 4, -3.0F, settings));

    public static final Item SPEEDRUNNER_PICKAXE = Items.register(of("speedrunner_pickaxe"), settings -> new PickaxeItem(
            ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F, settings));

    public static final Item SPEEDRUNNER_AXE = Items.register(of("speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 8, -3.05F, settings));

    public static final Item SPEEDRUNNER_HOE = Items.register(of("speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.SPEEDRUNNER_SHOVEL_AXE_HOE, 0, -0.5F, settings));

    public static final Item SPEEDRUNNER_HELMET = Items.register(of("speedrunner_helmet"), settings -> new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.HELMET, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.HELMET.getMaxDamage(30))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_CHESTPLATE = Items.register(of("speedrunner_chestplate"), settings -> new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.CHESTPLATE, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(30))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_LEGGINGS = Items.register(of("speedrunner_leggings"), settings -> new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.LEGGINGS, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(30))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, SPEEDRUNNER_ARMOR_PATH)));

    public static final Item SPEEDRUNNER_BOOTS = Items.register(of("speedrunner_boots"), settings -> new ArmorItem(
            ModArmorMaterials.SPEEDRUNNER, EquipmentType.BOOTS, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.BOOTS.getMaxDamage(30))
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

    public static final Item GOLDEN_SPEEDRUNNER_PICKAXE = Items.register(of("golden_speedrunner_pickaxe"), settings -> new PickaxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 2, -2.8F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_AXE = Items.register(of("golden_speedrunner_axe"), settings -> new AxeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 7, -3.0F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HOE = Items.register(of("golden_speedrunner_hoe"), settings -> new HoeItem(
            ModToolMaterials.GOLDEN_SPEEDRUNNER, 0, -0.5F, settings));

    public static final Item GOLDEN_SPEEDRUNNER_HELMET = Items.register(of("golden_speedrunner_helmet"), settings -> new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.HELMET, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.HELMET.getMaxDamage(11))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_CHESTPLATE = Items.register(of("golden_speedrunner_chestplate"), settings -> new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.CHESTPLATE, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(11))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_LEGGINGS = Items.register(of("golden_speedrunner_leggings"), settings -> new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.LEGGINGS, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(11))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, GOLDEN_SPEEDRUNNER_ARMOR_PATH)));

    public static final Item GOLDEN_SPEEDRUNNER_BOOTS = Items.register(of("golden_speedrunner_boots"), settings -> new ArmorItem(
            ModArmorMaterials.GOLDEN_SPEEDRUNNER, EquipmentType.BOOTS, settings), new Item.Settings().maxCount(1).maxDamage(EquipmentType.BOOTS.getMaxDamage(11))
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
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line1"));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line2"));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunner_bulk.tooltip.line3"));
            }
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

    public static final Item GOLDEN_PIGLIN_PORK = Items.register(of("golden_piglin_pork"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_PIGLIN_PORK));

    public static final Item GOLDEN_BEEF = Items.register(of("golden_beef"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_BEEF));

    public static final Item GOLDEN_PORKCHOP = Items.register(of("golden_porkchop"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_PORKCHOP));

    public static final Item GOLDEN_MUTTON = Items.register(of("golden_mutton"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_MUTTON));

    public static final Item GOLDEN_CHICKEN = Items.register(of("golden_chicken"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_CHICKEN));

    public static final Item GOLDEN_RABBIT = Items.register(of("golden_rabbit"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_RABBIT));

    public static final Item GOLDEN_COD = Items.register(of("golden_cod"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_COD));

    public static final Item GOLDEN_SALMON = Items.register(of("golden_salmon"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_SALMON));

    public static final Item GOLDEN_BREAD = Items.register(of("golden_bread"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_BREAD));

    public static final Item GOLDEN_POTATO = Items.register(of("golden_potato"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_POTATO));

    public static final Item GOLDEN_BEETROOT = Items.register(of("golden_beetroot"), Item::new,
            new Item.Settings().food(ModFoodComponents.GOLDEN_BEETROOT));

    public static final Item IGNEOUS_ROCK = Items.register(of("igneous_rock"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.igneous_rock.tooltip").formatted(Formatting.GRAY));
            }
        }
    });

    public static final Item SPEEDRUNNER_STICK = Items.register(of("speedrunner_stick"), Item::new);

    public static final Item SPEEDRUNNER_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPEEDRUNNER, new Item.Settings().maxCount(1).fireproof(), false, false);
    public static final Item SPEEDRUNNER_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.SPEEDRUNNER, new Item.Settings().maxCount(1).fireproof(), true, false);
    public static final Item DEAD_SPEEDRUNNER_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.DEAD_SPEEDRUNNER, new Item.Settings().maxCount(1).fireproof(), false, false);
    public static final Item DEAD_SPEEDRUNNER_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.DEAD_SPEEDRUNNER, new Item.Settings().maxCount(1).fireproof(), true, false);
    public static final Item CRIMSON_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRIMSON, new Item.Settings().maxCount(1).fireproof(), false, false);
    public static final Item CRIMSON_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.CRIMSON, new Item.Settings().maxCount(1).fireproof(), true, false);
    public static final Item WARPED_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.WARPED, new Item.Settings().maxCount(1).fireproof(), false, false);
    public static final Item WARPED_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.WARPED, new Item.Settings().maxCount(1).fireproof(), true, false);

    public static final Item WITHER_BONE = Items.register(of("wither_bone"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.wither_bone.tooltip").formatted(Formatting.GRAY));
            }
        }
    });

    public static final Item ENDER_MATTER = Items.register(of("ender_matter"), settings -> new Item(
            settings) {

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            if (options().client.itemTooltips) {
                tooltip.add(Text.translatable("item.speedrunnermod.ender_matter.tooltip").formatted(Formatting.GRAY));
            }
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