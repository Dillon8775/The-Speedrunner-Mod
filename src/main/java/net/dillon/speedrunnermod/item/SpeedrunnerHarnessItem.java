package net.dillon.speedrunnermod.item;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

import java.util.function.Consumer;

/**
 * A harness item that makes happy ghasts fly faster.
 */
public class SpeedrunnerHarnessItem extends Item {
    private final float flyingSpeedDivider;

    public SpeedrunnerHarnessItem(Properties properties, ResourceKey<EquipmentAsset> equipmentAsset, float flyingSpeedDivider) {
        super(properties.stacksTo(1).component(DataComponents.EQUIPPABLE, speedrunnerHarness(equipmentAsset)));
        this.flyingSpeedDivider = flyingSpeedDivider;
    }

    /**
     * @return the flying divider for a happy ghast.
     */
    public float getFlyingSpeedDivider() {
        return this.flyingSpeedDivider;
    }

    /**
     * @return a speedrunner hardness data component.
     */
    private static Equippable speedrunnerHarness(ResourceKey<EquipmentAsset> equipmentAsset) {
        HolderGetter<EntityType<?>> entityGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);
        return Equippable.builder(EquipmentSlot.BODY)
                .setEquipSound(SoundEvents.HARNESS_EQUIP)
                .setAsset(equipmentAsset)
                .setAllowedEntities(entityGetter.getOrThrow(EntityTypeTags.CAN_EQUIP_HARNESS))
                .setEquipOnInteract(true)
                .setCanBeSheared(true)
                .setShearingSound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.HARNESS_UNEQUIP))
                .build();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_harness.tooltip"));
    }
}