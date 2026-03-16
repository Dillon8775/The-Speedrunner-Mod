package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.packet.client.OpenFeaturesScreenS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The {@code golden speedrunner upgrade smithing template item.}
 */
public class GoldenUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final Component GOLDEN_UPGRADE_APPLIES_TO_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.applies_to"))).withStyle(ChatFormatting.GOLD);
    private static final Component GOLDEN_INGREDIENTS_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.ingredients"))).withStyle(ChatFormatting.AQUA);
    private static final Component GOLDEN_BASE_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.base_slot_description")));
    private static final Component GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT = Component.translatable(Util.makeDescriptionId("item", ofSpeedrunnerMod("smithing_template.golden_upgrade.additions_slot_description")));

    public GoldenUpgradeSmithingTemplateItem(Properties settings) {
        super(GOLDEN_UPGRADE_APPLIES_TO_TEXT,
                GOLDEN_INGREDIENTS_TEXT,
                GOLDEN_BASE_SLOT_DESCRIPTION_TEXT,
                GOLDEN_ADDITIONS_SLOT_DESCRIPTION_TEXT,
                SmithingTemplateItem.createNetheriteUpgradeIconList(),
                SmithingTemplateItem.createNetheriteUpgradeMaterialList(), settings);
    }

    /**
     * Sends the packet to open the feature screens.
     */
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new OpenFeaturesScreenS2CPacket());
            ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayer, this.getDefaultInstance());
        }
        return super.use(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.golden_upgrade_smithing_template.tooltip.line1"));
        textConsumer.accept(Component.translatable("item.speedrunnermod.golden_upgrade_smithing_template.tooltip.line2").withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }
}