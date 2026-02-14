package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.packet.client.OpenFeaturesScreenS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The {@code golden speedrunner upgrade smithing template item.}
 */
public class GoldenUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Text GOLDEN_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.applies_to"))).formatted(Formatting.GOLD);
    private static final Text GOLDEN_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.ingredients"))).formatted(Formatting.AQUA);
    private static final Text GOLDEN_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.base_slot_description")));
    private static final Text GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.additions_slot_description")));

    public GoldenUpgradeSmithingTemplateItem(Settings settings) {
        super(GOLDEN_UPGRADE_APPLIES_TO_TEXT,
                GOLDEN_INGREDIENTS_TEXT,
                GOLDEN_BASE_SLOT_DESCRIPTION_TEXT,
                GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
                SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(), settings);
    }

    /**
     * Sends the packet to open the feature screens.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new OpenFeaturesScreenS2CPacket());
            ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayer, this.getDefaultStack());
        }
        return super.use(world, player, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.golden_upgrade_smithing_template.tooltip.line1"));
        textConsumer.accept(Text.translatable("item.speedrunnermod.golden_upgrade_smithing_template.tooltip.line2").formatted(Formatting.AQUA));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}