package net.dillon.speedrunnermod.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A shield with an extremely fast cooldown, but very low durability.
 */
public class GoldenShieldItem extends ShieldItem {
    public static final float COOLDOWN_DIVIDER = 1.9F;

    public GoldenShieldItem(Settings settings) {
        super(settings
                .maxCount(1)
                .maxDamage(76)
                .repairable(Items.GOLD_INGOT)
                .component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                .component(
                        DataComponentTypes.BLOCKS_ATTACKS,
                        new BlocksAttacksComponent(
                                0.25F,
                                0.2F,
                                List.of(new BlocksAttacksComponent.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F),
                                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                                Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND)
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.golden_shield.tooltip").formatted(Formatting.GRAY));
    }
}