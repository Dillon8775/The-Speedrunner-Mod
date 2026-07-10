package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.mixin.accessor.SmithingTemplateItemInvoker;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The {@code dragon upgrade smithing template item.}
 */
public class DragonUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component GOLDEN_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.dragon_upgrade.applies_to")));
    private static final Component GOLDEN_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.dragon_upgrade.ingredients")));
    private static final Component GOLDEN_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.dragon_upgrade.base_slot_description")));
    private static final Component GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.dragon_upgrade.additions_slot_description")));

    public DragonUpgradeSmithingTemplateItem(Properties settings) {
        super(GOLDEN_UPGRADE_APPLIES_TO_TEXT,
                GOLDEN_INGREDIENTS_TEXT,
                GOLDEN_BASE_SLOT_DESCRIPTION_TEXT,
                GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                SmithingTemplateItemInvoker.invokeNetheriteUpgradeIconList(),
                SmithingTemplateItemInvoker.invokeNetheriteUpgradeMaterialList(),
                settings
                        .rarity(Rarity.EPIC)
        );
    }
}