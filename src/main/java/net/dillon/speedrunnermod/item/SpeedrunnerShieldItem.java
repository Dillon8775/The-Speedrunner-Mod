package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * <p>A shield which has a faster cooldown, and more durability.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link net.dillon.speedrunnermod.mixin.main.entity.player.PlayerEntityMixin} for more.</p>
 */
public class SpeedrunnerShieldItem extends ShieldItem implements TooltipAppender {

    public SpeedrunnerShieldItem(Settings settings) {
        super(settings
                .maxCount(1)
                .maxDamage(672)
                .repairable(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT)
                .component(
                        DataComponentTypes.BLOCKS_ATTACKS,
                        new BlocksAttacksComponent(
                                0.25F,
                                1.0F,
                                List.of(new BlocksAttacksComponent.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacksComponent.ItemDamage(3.0F, 1.0F, 1.0F),
                                Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                Optional.of(SoundEvents.ITEM_SHIELD_BLOCK),
                                Optional.of(SoundEvents.ITEM_SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND));
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        if (options().client.itemTooltips) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_shield.tooltip").formatted(Formatting.GRAY));
        }
    }
}