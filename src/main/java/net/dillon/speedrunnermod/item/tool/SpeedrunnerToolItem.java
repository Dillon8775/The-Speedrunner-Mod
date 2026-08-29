package net.dillon.speedrunnermod.item.tool;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.Block;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A speedrunner tool or weapon that deals additional damage to speedrunner mobs.
 */
public class SpeedrunnerToolItem {

    /**
     * Creates a speedrunner sword.
     */
    public static class Sword extends Item {

        public Sword(ToolMaterial toolMaterial, float attackDamageBaseline, float attackSpeedBaseline, boolean dragon, Item.Properties properties) {
            super(properties
                    .sword(toolMaterial, attackDamageBaseline, attackSpeedBaseline)
                    .attributes(
                            createDefaultAttributes(toolMaterial, attackDamageBaseline, attackSpeedBaseline, dragon)
                    )
            );
        }
    }

    /**
     * Creates a speedrunner tool.
     */
    public static class Tool extends Item {

        public Tool(ToolMaterial toolMaterial, TagKey<Block> minesEfficiently, float attackDamageBaseline, float attackSpeedBaseline, float disableBlockingSeconds, Item.Properties properties) {
            super(properties
                    .tool(toolMaterial, minesEfficiently, attackDamageBaseline, attackSpeedBaseline, disableBlockingSeconds)
                    .attributes(
                            createDefaultAttributes(toolMaterial, attackDamageBaseline, attackSpeedBaseline, false)
                    )
            );
        }
    }

    /**
     * Creates a speedrunner axe.
     */
    public static class Axe extends Item {

        public Axe(
                ToolMaterial toolMaterial,
                float attackDamageBaseline,
                float attackSpeedBaseline,
                Item.Properties properties
        ) {
            super(properties
                    .axe(toolMaterial, attackDamageBaseline, attackSpeedBaseline)
                    .attributes(
                            createDefaultAttributes(
                                    toolMaterial,
                                    attackDamageBaseline,
                                    attackSpeedBaseline,
                                    false
                            )
                    )
            );
        }
    }

    /**
     * Creates a speedrunner shovel.
     */
    public static class Shovel extends Item {

        public Shovel(
                ToolMaterial toolMaterial,
                float attackDamageBaseline,
                float attackSpeedBaseline,
                Item.Properties properties
        ) {
            super(properties
                    .shovel(toolMaterial, attackDamageBaseline, attackSpeedBaseline)
                    .attributes(
                            createDefaultAttributes(
                                    toolMaterial,
                                    attackDamageBaseline,
                                    attackSpeedBaseline,
                                    false
                            )
                    )
            );
        }
    }

    /**
     * Creates a speedrunner hoe.
     */
    public static class Hoe extends Item {

        public Hoe(
                ToolMaterial toolMaterial,
                float attackDamageBaseline,
                float attackSpeedBaseline,
                Item.Properties properties
        ) {
            super(properties
                    .hoe(toolMaterial, attackDamageBaseline, attackSpeedBaseline)
                    .attributes(
                            createDefaultAttributes(
                                    toolMaterial,
                                    attackDamageBaseline,
                                    attackSpeedBaseline,
                                    false
                            )
                    )
            );
        }
    }

    /**
     * @return the default attribute instance for a speedrunner tool.
     */
    public static ItemAttributeModifiers createDefaultAttributes(ToolMaterial toolMaterial, float attackDamageBaseline, float attackSpeedBaseline, boolean dragon) {
        ItemAttributeModifiers.Builder attributes = ItemAttributeModifiers.builder();

        attributes.add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamageBaseline + toolMaterial.attackDamageBonus(),
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND
        );
        attributes.add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeedBaseline, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );

        if (dragon) {
            attributes.add(
                    Attributes.ARMOR,
                    new AttributeModifier(ofSpeedrunnerMod("armor_dragons_sword"), 9.0F, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.ANY
            );
            attributes.add(
                    Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(ofSpeedrunnerMod("armor_toughness_dragons_sword"), 3.0F, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.ANY
            );
        }

        attributes.add(
                ModAttributes.IMPERATIVE_DAMAGE,
                new AttributeModifier(ofSpeedrunnerMod("imperative_damage_speedrunner_tool"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                EquipmentSlotGroup.MAINHAND
        );

        if (dragon) {
            attributes.add(
                    ModAttributes.DRAGONBANE,
                    new AttributeModifier(ofSpeedrunnerMod("ender_dragon_damage_dragons_sword"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.MAINHAND
            );
        } else {
            attributes.add(
                    ModAttributes.DOOM_BLOCK_IMMUNITY,
                    new AttributeModifier(ofSpeedrunnerMod("doom_block_protection_speedrunner_tool"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                    EquipmentSlotGroup.MAINHAND
            );
        }

        return attributes.build();
    }
}