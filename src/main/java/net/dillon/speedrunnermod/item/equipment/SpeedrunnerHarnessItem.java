package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.Equippable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A harness item that makes happy ghasts fly faster.
 */
public class SpeedrunnerHarnessItem extends Item {

    public SpeedrunnerHarnessItem(Properties properties, ResourceKey<EquipmentAsset> equipmentAsset, float flyingSpeed) {
        super(properties
                .stacksTo(1)
                .component(DataComponents.EQUIPPABLE, speedrunnerHarness(equipmentAsset))
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.BONUS_HAPPY_GHAST_FLYING_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("happy_ghast_flying_speed_speedrunner_harness"), flyingSpeed, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.BODY
                                )
                                .build()
                )
        );
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
}