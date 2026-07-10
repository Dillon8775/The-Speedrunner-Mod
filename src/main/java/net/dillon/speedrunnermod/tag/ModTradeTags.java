package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade tags.
 */
public class ModTradeTags {
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_1 = createTradeTag("retired_speedrunner/level_1");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_2 = createTradeTag("retired_speedrunner/level_2");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_3 = createTradeTag("retired_speedrunner/level_3");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_4 = createTradeTag("retired_speedrunner/level_4");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_5 = createTradeTag("retired_speedrunner/level_5");

    /**
     * Registers a {@code villager trade tag.}
     */
    private static TagKey<VillagerTrade> createTradeTag(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, ofSpeedrunnerMod(name));
    }

    /**
     * Initializes all Speedrunner Mod {@code trade tags.}
     */
    public static void initializeTradeTags() {
        SpeedrunnerMod.debug("Initialized trade tags.");
    }
}