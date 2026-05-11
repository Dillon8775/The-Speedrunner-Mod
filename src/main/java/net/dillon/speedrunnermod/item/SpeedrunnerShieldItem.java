package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.mixin.entity.player.PlayerMixin;
import net.dillon.speedrunnermod.mixin.item.component.BlocksAttacksMixin;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <p>A shield which has a faster cooldown, and more durability.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link PlayerMixin} for more.
 * <p>Shield cooldown function located in {@link BlocksAttacksMixin}</p></p>
 */
public class SpeedrunnerShieldItem extends ShieldItem {
    public static final float COOLDOWN_DIVIDER = 1.6F;

    public SpeedrunnerShieldItem(Properties settings) {
        super(settings
                .stacksTo(1)
                .durability(672)
                .repairable(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                .delayedComponent(
                        DataComponents.BLOCKS_ATTACKS,
                        context -> new BlocksAttacks(
                                0.25F,
                                0.6F,
                                List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                Optional.of(SoundEvents.SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_shield.tooltip").withStyle(ChatFormatting.GRAY));
    }
}