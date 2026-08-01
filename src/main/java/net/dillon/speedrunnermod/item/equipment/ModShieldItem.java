package net.dillon.speedrunnermod.item.equipment;

import net.dillon.dillonlib.factory.item.ShieldFactory;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.mixin.entity.player.PlayerMixin;
import net.dillon.speedrunnermod.mixin.item.component.BlocksAttacksMixin;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * <p>A modded shield.</p>
 * <p>See {@link net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe}, SpeedrunnerShieldModelRenderer and {@link PlayerMixin} for more.
 * <p>Shield cooldown function located in {@link BlocksAttacksMixin}</p>.</p>
 */
public class ModShieldItem extends ShieldFactory {
    private final float cooldownDivider;
    private final float cooldownSeconds;

    public ModShieldItem(float cooldownDivider, float cooldownSeconds, float disableCooldownScale, int durability, TagKey<Item> repairable, Properties properties) {
        super(properties
                .durability(durability)
                .repairable(repairable)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_COOLDOWN,
                                        new AttributeModifier(ofSpeedrunnerMod("additional_cooldown_modded_shield"), cooldownSeconds, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.BODY
                                )
                                .build()
                ),
                disableCooldownScale
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