package net.dillon.speedrunnermod.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade tags.
 */
public class ModTradeTags {
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_1 = create("retired_speedrunner/level_1");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_2 = create("retired_speedrunner/level_2");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_3 = create("retired_speedrunner/level_3");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_4 = create("retired_speedrunner/level_4");
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_5 = create("retired_speedrunner/level_5");

    /**
     * Creates a villager trade tag.
     */
    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, ofSpeedrunnerMod(name));
    }
}