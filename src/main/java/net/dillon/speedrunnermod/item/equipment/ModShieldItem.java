package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.mixin.entity.player.PlayerMixin;
import net.dillon.speedrunnermod.mixin.item.component.BlocksAttacksMixin;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * <p>A modded shield.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link PlayerMixin} for more.
 * <p>Shield cooldown function located in {@link BlocksAttacksMixin}</p>.</p>
 */
public class ModShieldItem extends ShieldItem {
    private final float cooldownDivider;
    private final float cooldownSeconds;

    public ModShieldItem(float cooldownDivider, float cooldownSeconds, float disableCooldownScale, int durability, TagKey<Item> repairable, Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(durability)
                .repairable(repairable)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                .delayedComponent(
                        DataComponents.BLOCKS_ATTACKS,
                        context -> new BlocksAttacks(
                                0.25F,
                                disableCooldownScale,
                                List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                Optional.of(SoundEvents.SHIELD_BLOCK),
                                Optional.of(SoundEvents.SHIELD_BREAK)
                        )
                )
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_COOLDOWN,
                                        new AttributeModifier(ofSpeedrunnerMod("additional_cooldown_modded_shield"), cooldownSeconds, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.BODY
                                )
                                .build()
                )
        );
        this.cooldownDivider = cooldownDivider;
        this.cooldownSeconds = cooldownSeconds;
    }

    /**
     * @return the correct cooldown divider to use for the shield.
     */
    public float getCooldownDivider() {
        return this.cooldownDivider;
    }

    /**
     * @return the correct cooldown seconds for the shield.
     */
    public float getCooldownSeconds() {
        return this.cooldownSeconds;
    }
}