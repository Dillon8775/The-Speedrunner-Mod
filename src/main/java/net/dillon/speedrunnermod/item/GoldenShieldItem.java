package net.dillon.speedrunnermod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A shield with an extremely fast cooldown, but very low durability.
 */
public class GoldenShieldItem extends ShieldItem {
    public static final float COOLDOWN_DIVIDER = 1.9F;

    public GoldenShieldItem(Properties settings) {
        super(settings
                .stacksTo(1)
                .durability(76)
                .repairable(Items.GOLD_INGOT)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                .delayedComponent(
                        DataComponents.BLOCKS_ATTACKS,
                        context -> new BlocksAttacks(
                                0.25F,
                                0.2F,
                                List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                Optional.of(SoundEvents.SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.golden_shield.tooltip").withStyle(ChatFormatting.GRAY));
    }
}