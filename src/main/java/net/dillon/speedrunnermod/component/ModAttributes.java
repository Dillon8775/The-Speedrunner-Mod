package net.dillon.speedrunnermod.component;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod {@code attributes.}
 */
public class ModAttributes {
    public static final ArrayList<String> WHITE_FORMATTERS = new ArrayList<>();
    public static final ArrayList<String> YELLOW_FORMATTERS = new ArrayList<>();
    public static final ArrayList<String> GOLD_FORMATTERS = new ArrayList<>();
    public static final ArrayList<String> RED_FORMATTERS = new ArrayList<>();
    public static final ArrayList<String> DARK_PURPLE_FORMATTERS = new ArrayList<>();
    public static final ArrayList<String> REVERSE_FORMATTERS = new ArrayList<>();

    public static final Holder<Attribute> BONUS_AIR_RECOVERY = register("bonus_air_recovery", 0.0, 0.0, 8.0, null);
    public static final Holder<Attribute> BONUS_BOAT_MOVEMENT_SPEED = register("bonus_boat_movement_speed", 1.0, 1.0, 100.0, null);
    public static final Holder<Attribute> BONUS_BOW_CHARGE_SPEED = register("bonus_bow_charge_speed", 0.0, 0.0, 32.0, null);
    public static final Holder<Attribute> BONUS_BOW_POWER = register("bonus_bow_power", 0.0, 0.0, 10.0, null);
    public static final Holder<Attribute> BONUS_RANGE = register("bonus_range", 0.0, 0.0, 16.0, null);
    public static final Holder<Attribute> BONUS_COOLDOWN = register("bonus_cooldown", 0.0, -3.0, 10.0, REVERSE_FORMATTERS);
    public static final Holder<Attribute> BONUS_HAPPY_GHAST_FLYING_SPEED = register("bonus_happy_ghast_flying_speed", 0.0, 0.0, 8.0, WHITE_FORMATTERS);
    public static final Holder<Attribute> BONUS_NAUTILUS_MOVEMENT_SPEED = register("bonus_nautilus_movement_speed", 0.0, 0.0, 8.0, null);
    public static final Holder<Attribute> BONUS_NAUTILUS_DASH_SPEED = register("bonus_nautilus_dash_speed", 0.0, 0.0, 4.0, null);
    @FakeAttribute(inconsistent = false)
    public static final Holder<Attribute> BONUS_SPEAR_REACH = register("bonus_spear_reach", 0.0, 0.0, 16.0, null);
    @FakeAttribute(inconsistent = false)
    public static final Holder<Attribute> BONUS_SPEAR_CREATIVE_REACH = register("bonus_spear_creative_reach", 0.0, 0.0, 28.0, null);
    public static final Holder<Attribute> BONUS_SPEAR_LUNGE_MOMENTUM = register("bonus_spear_lunge_momentum", 0.0, 0.0, 32.0, null);
    public static final Holder<Attribute> BONUS_SPEAR_LUNGE_EXHAUSTION = register("bonus_spear_lunge_exhaustion", 0.0, -10.0, 10.0, REVERSE_FORMATTERS);
    public static final Holder<Attribute> BONUS_TARGET_DAMAGE = register("bonus_target_damage", 0.0, 0.0, 32.0, null);
    public static final Holder<Attribute> BONUS_INERTIA = register("bonus_inertia", 0.0, 0.0, 16.0, null);
    public static final Holder<Attribute> IMPERATIVE_DAMAGE = register("imperative_damage", 1.0, 1.0, 2048.0, null);
    public static final Holder<Attribute> IMPERATIVE_PROTECTION = register("imperative_protection", 1.0, 1.0, 2048.0, null);
    public static final Holder<Attribute> SHRIEKER_STEALTH = register("shrieker_stealth", 1.0, 1.0, 100.0, null);
    public static final Holder<Attribute> PIGLIN_STEALTH = register("piglin_stealth", 1.0, 1.0, 100.0, YELLOW_FORMATTERS);
    public static final Holder<Attribute> DOOM_BLOCK_IMMUNITY = register("doom_block_immunity", 1.0, 1.0, 100.0, RED_FORMATTERS);
    public static final Holder<Attribute> DRAGONBANE = register("dragonbane", 1.0, 1.0, 100.0, DARK_PURPLE_FORMATTERS);
    @FakeAttribute(inconsistent = false)
    public static final Holder<Attribute> INVENTORY_PRESERVATION = register("inventory_preservation", 0.0, 0.0, 100.0, null);
    public static final Holder<Attribute> LAVA_MOVEMENT_EFFICIENCY = register("lava_movement_efficiency", 1.0, 1.0, 16.0, GOLD_FORMATTERS);
    public static final Holder<Attribute> LAVA_INVULNERABILITY = register("lava_invulnerability", 1.0, 1.0, 100.0, GOLD_FORMATTERS);
    public static final Holder<Attribute> UNDERWATER_VISION = register("underwater_vision", 1.0, 1.0, 128.0, null);
    public static final Holder<Attribute> WITHERED_EFFECT = register("withered_effect", 0.0, 0.0, 128.0, null);

    /**
     * Registers a speedrunner mod attribute.
     */
    private static Holder<Attribute> register(final String name, final double defaultValue, final double minValue, final double maxValue, @Nullable ArrayList<String> formatter) {
        String actualName = "attribute.name." + name;
        if (formatter != null) {
            formatter.add(actualName);
        }

        Attribute attribute = new RangedAttribute(actualName, defaultValue, minValue, maxValue)
                .setSyncable(true);
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ofSpeedrunnerMod(name), attribute);
    }

    /**
     * Initializes all Speedrunner Mod attributes.
     */
    public static void initializeAttributes() {
        SpeedrunnerMod.LOGGER.debug("Initialized attributes.");
    }
}