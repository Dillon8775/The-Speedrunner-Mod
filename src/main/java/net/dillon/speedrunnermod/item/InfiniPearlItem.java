package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.helper.InfiniPearl;
import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * An {@code ender pearl} like item that does not get consumed nor do damage upon use.
 */
public class InfiniPearlItem extends EnderpearlItem implements SpeedrunnerItem {

    public InfiniPearlItem(Properties settings) {
        super(settings
                .rarity(Rarity.RARE)
                .stacksTo(1)
                .durability(571)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_RANGE,
                                        new AttributeModifier(ofSpeedrunnerMod("additional_range_infini_pearl"), 0.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_TARGET_DAMAGE,
                                        new AttributeModifier(ofSpeedrunnerMod("additional_target_damage_infini_pearl"), 3.0F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_INERTIA,
                                        new AttributeModifier(ofSpeedrunnerMod("submerged_water_range_infini_pearl"), 0.13F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_COOLDOWN,
                                        new AttributeModifier(ofSpeedrunnerMod("additional_cooldown_infini_pearl"), 1.0F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .build()
                )
        );
    }

    /**
     * Acts pretty much exactly like an {@code ender pearl,} just removing the item decrement and entity damage.
     */
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        this.playWorldSound(SoundEvents.ENDER_PEARL_THROW, 0.5F, 0.4F, world, player);

        ModComponentHelper.applyNewItemCooldown(player, this.getDefaultInstance());

        if (world instanceof ServerLevel serverLevel) {
            float additionalInertia = (float)player.getAttributeValue(ModAttributes.BONUS_INERTIA);
            float additionalTargetDamage = (float)player.getAttributeValue(ModAttributes.BONUS_TARGET_DAMAGE);
            float additionalRange = (float)player.getAttributeValue(ModAttributes.BONUS_RANGE);
            ThrowableProjectile throwableProjectile = Projectile.spawnProjectileFromRotation(ThrownEnderpearl::new, serverLevel, itemStack, player, 0.0F, 1.5F + additionalRange, 1.2F);
            ((InfiniPearl)throwableProjectile).setInertia(additionalInertia);
            ((InfiniPearl)throwableProjectile).setDamage(additionalTargetDamage);
        }

        // Safer boolean check
        if (!itemStack.getComponents().has(DataComponents.UNBREAKABLE)) {
            itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResult.SUCCESS;
    }

    /**
     * The InfiniPearl always has an enchantment glint.
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.infini_pearl.tooltip"));
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }

    @Override
    public Mode[] disabledModes() {
        return new Mode[]{};
    }
}