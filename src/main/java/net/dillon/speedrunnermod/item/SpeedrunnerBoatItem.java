package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A boat, which is faster than normal boats, or just has different attributes.
 */
public class SpeedrunnerBoatItem extends BoatItem {

    public SpeedrunnerBoatItem(EntityType<? extends AbstractBoat> entityType, boolean fast, boolean fireproof, Properties properties) {
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();

        if (fast) {
            attributes.add(
                    ModAttributes.BONUS_BOAT_MOVEMENT_SPEED,
                    new AttributeModifier(ofSpeedrunnerMod("movement_speed_speedrunner_boat"), 0.35F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.ANY
            );
        }
        if (fireproof) {
            attributes.add(
                    ModAttributes.LAVA_INVULNERABILITY,
                    new AttributeModifier(ofSpeedrunnerMod("lava_invulnerability_speedrunner_boat"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.ANY
            );
        }

        super(entityType, properties
                .stacksTo(1)
                .attributes(
                        attributes.build()
                )
        );
    }
}