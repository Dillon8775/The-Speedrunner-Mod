package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.VillagerTrade;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade entries.
 */
public class ModTrades {
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_BOOK = resourceKey("retired_speedrunner/1/book");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK = resourceKey("retired_speedrunner/1/enchanted_book");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_EMERALD = resourceKey("retired_speedrunner/2/emerald");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE = resourceKey("retired_speedrunner/2/golden_upgrade_smithing_template");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_POTION = resourceKey("retired_speedrunner/3/potion");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_OMINOUS_BOTTLE = resourceKey("retired_speedrunner/3/ominous_bottle");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_GOLDEN_APPLE = resourceKey("retired_speedrunner/4/golden_apple");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK = resourceKey("retired_speedrunner/4/enchanted_book");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_INFINI_PEARL = resourceKey("retired_speedrunner/4/infini_pearl");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_NETHERITE_UPGRADE_SMITHING_TEMPLATE = resourceKey("retired_speedrunner/5/netherite_upgrade_smithing_template");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_DRAGONS_AURA = resourceKey("retired_speedrunner/5/dragons_aura");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE = resourceKey("retired_speedrunner/5/netherite_chestplate");

    /**
     * Creates a resource key for a villager trade.
     */
    @Author(Authors.SAMEDDIFFERENT)
    private static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, ofSpeedrunnerMod(path));
    }
}