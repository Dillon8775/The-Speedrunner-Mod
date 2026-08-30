package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.VillagerTrade;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade entries.
 */
public class ModTrades {
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_EMERALD = createTrade("retired_speedrunner/1/emerald");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK = createTrade("retired_speedrunner/1/enchanted_book");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_GOLDEN_APPLE = createTrade("retired_speedrunner/2/golden_apple");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE = createTrade("retired_speedrunner/2/golden_upgrade_smithing_template");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_HARNESS = createTrade("retired_speedrunner/3/speedrunner_harness");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_NAUTILUS_ARMOR = createTrade("retired_speedrunner/3/speedrunner_nautilus_armor");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_ENCHANTED_GOLDEN_APPLE = createTrade("retired_speedrunner/3/enchanted_golden_apple");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_DRAGONS_FIREBALL = createTrade("retired_speedrunner/4/dragons_fireball");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK = createTrade("retired_speedrunner/4/enchanted_book");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_INFINI_PEARL = createTrade("retired_speedrunner/4/infini_pearl");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_SPECIAL_ARROW = createTrade("retired_speedrunner/5/special_arrow");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_SPECIAL_POTION = createTrade("retired_speedrunner/5/special_potion");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE = createTrade("retired_speedrunner/5/netherite_chestplate");

    /**
     * Creates a resource key for a villager trade.
     */
    @Author(Authors.SAMEDDIFFERENT)
    private static ResourceKey<VillagerTrade> createTrade(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all speedrunner mod trades.
     */
    public static void initializeTrades() {
        SpeedrunnerMod.LOGGER.debug("Initialized trades.");
    }
}