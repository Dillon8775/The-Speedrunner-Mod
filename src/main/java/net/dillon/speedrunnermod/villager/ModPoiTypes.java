package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.accessor.PoiTypesInvoker;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * {@code Point of Interest Types} for the speedrunner mod.
 */
public class ModPoiTypes {
    public static final ResourceKey<PoiType> RETIRED_SPEEDRUNNER = registerPoiType("retired_speedrunner", PoiTypesInvoker.invokeGetBlockStates(ModBlocks.SPEEDRUNNERS_WORKBENCH));

    /**
     * Registers a {@link PoiType} for a villager profession.
     */
    @Author(Authors.SAMEDDIFFERENT)
    private static ResourceKey<PoiType> registerPoiType(String name, Set<BlockState> matchingStates) {
        ResourceKey<PoiType> key = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ofSpeedrunnerMod(name));
        Registry.register(
                BuiltInRegistries.POINT_OF_INTEREST_TYPE,
                key,
                new PoiType(
                        matchingStates,
                        1,
                        1
                )
        );
        PoiTypesInvoker.invokeRegisterBlockStates(BuiltInRegistries.POINT_OF_INTEREST_TYPE.getOrThrow(key), matchingStates);
        return key;
    }

    /**
     * Initializes all speedrunner mod poi types.
     */
    public static void initializeModPois() {
        SpeedrunnerMod.debug("Initialized POIs.");
    }
}