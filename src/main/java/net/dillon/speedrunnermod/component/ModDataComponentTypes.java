package net.dillon.speedrunnermod.component;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ClearAllEffectsConsumeEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.minecraft.component.type.ConsumableComponents.food;

/**
 * Different types of components for mod items.
 */
public class ModDataComponentTypes {
    @ChatGPT(Credit.FULL_CREDIT)
    public static final ComponentType<Boolean> BOOLEAN = Registry.register(
            Registries.DATA_COMPONENT_TYPE, ofSpeedrunnerMod("boolean"), ComponentType.<Boolean>builder().codec(Codec.BOOL).build());

    public static final ComponentType<TagKey<Structure>> LOCATING_STRUCTURE = Registry.register(
            Registries.DATA_COMPONENT_TYPE, ofSpeedrunnerMod("locating_structure"), ComponentType.<TagKey<Structure>>builder().codec(TagKey.codec(RegistryKeys.STRUCTURE)).build());

    public static final DeathProtectionComponent TOTEM_SPEEDRUNNERS = new DeathProtectionComponent(
            List.of(
                    new ClearAllEffectsConsumeEffect(),
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.REGENERATION, 1800, 1),
                                    new StatusEffectInstance(StatusEffects.ABSORPTION, ModUtil.minutesInTicks(1), 1),
                                    new StatusEffectInstance(StatusEffects.RESISTANCE, ModUtil.secondsInTicks(30)),
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.minutesInTicks(2)),
                                    new StatusEffectInstance(StatusEffects.STRENGTH, ModUtil.secondsInTicks(15)),
                                    new StatusEffectInstance(StatusEffects.SPEED, ModUtil.secondsInTicks(30)),
                                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, ModUtil.secondsInTicks(10))
                            )
                    )
            ));

    public static final ConsumableComponent SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            List.of(
                                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1200, 0),
                                    new StatusEffectInstance(StatusEffects.SPEED, 600, 0)
                            )
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 500, 1), 0.5F
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 200), 0.25F
                    )
            )
            .build();

    public static final ConsumableComponent ROTTEN_SPEEDRUNNER_BULK = food()
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HUNGER, 400, 0), 0.5F
                    )
            )
            .consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 0.1F
                    )
            )
            .build();

    /**
     * Initializes all speedrunner mod data components.
     */
    public static void init() {}
}