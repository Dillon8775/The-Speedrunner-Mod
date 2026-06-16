package net.dillon.speedrunnermod.mixin.registry;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Decoder;
import net.dillon.speedrunnermod.data.loader.*;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.resources.RegistryLoadTask;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isSsrDefault;

@Mixin(RegistryLoadTask.PendingRegistration.class)
public class RegistryLoadTaskPendingRegistrationMixin {

    /**
     * Directly modifies {@code ".json" files} to change and modify {@code world generation features,} including structures, mob spawns, and more.
     * <p>See package {@link net.dillon.speedrunnermod.data.loader} fore more on this.</p>
     */
    @Author(Authors.MAXENCEDC)
    @Inject(method = "loadFromResource", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static <T> void customDataGeneration(Decoder<T> elementDecoder, RegistryOps<JsonElement> ops, ResourceKey<T> elementKey, Resource thunk, CallbackInfoReturnable<Either<T, Exception>> cir, @Local JsonElement jsonElement) {
        if (!options().worldGen.customDataGeneration.getCurrentValue()) {
            return;
        }

        String registryPath = elementKey.registry().getPath();
        String elementPath = elementKey.identifier().getPath();
        String fileName = registryPath + "/" + elementPath + ".json";

        for (int i = 0; i < EntitySpawnsLoader.biomesWithDefaultMonsters().size(); i++) {
            if (fileName.equals(EntitySpawnsLoader.biomesWithDefaultMonsters().get(i))) {
                EntitySpawnsLoader.modifyBiomesWithDefaultMonsters(jsonElement);
            }
        }

        for (int i = 0; i < EntitySpawnsLoader.biomesWithFarmAnimals().size(); i++) {
            if (fileName.equals(EntitySpawnsLoader.biomesWithFarmAnimals().get(i))) {
                EntitySpawnsLoader.modifyBiomesWithFarmAnimals(jsonElement);
            }
        }

        if (fileName.equals(JsonIdentifiers.WARM_OCEAN)) {
            EntitySpawnsLoader.modifyWaterCreatureSpawns(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.BASALT_DELTAS)) {
            NetherBiomesLoader.modifyBasaltDeltas(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.CRIMSON_FOREST)) {
            NetherBiomesLoader.modifyCrimsonForest(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.NETHER_WASTES)) {
            NetherBiomesLoader.modifyNetherWastes(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.SOUL_SAND_VALLEY)) {
            NetherBiomesLoader.modifySoulSandValley(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.THE_END)) {
            TheEndBiomesLoader.modifyTheEnd(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.END)) {
            TheEndBiomesLoader.modifyEnd(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.WARPED_FOREST)) {
            NetherBiomesLoader.modifyWarpedForest(jsonElement);
        }

        if (fileName.equals(JsonIdentifiers.MONSTER_ROOM) || fileName.equals(JsonIdentifiers.MONSTER_ROOM_DEEP)) {
            PlacedFeaturesLoader.modifyMonsterRoom(jsonElement);
        }

        if (options().worldGen.commonOres.getCurrentValue()) {
            String oreDiamond = JsonIdentifiers.ORE_DIAMOND;
            if (fileName.equals(oreDiamond) || fileName.equals(JsonIdentifiers.ORE_DIAMOND_BURIED)) {
                PlacedFeaturesLoader.modifyOreDiamond(fileName, oreDiamond, jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.ORE_DIAMOND_LARGE)) {
                PlacedFeaturesLoader.modifyOreDiamondLarge(jsonElement);
            }

            String oreLapis = JsonIdentifiers.ORE_LAPIS;
            if (fileName.equals(oreLapis) || fileName.equals(JsonIdentifiers.ORE_LAPIS_BURIED)) {
                PlacedFeaturesLoader.modifyOreLapis(fileName, oreLapis, jsonElement);
            }
        }

        if (options().general.customBiomesAndCustomBiomeFeatures.getCurrentValue()) {
            if (fileName.equals(JsonIdentifiers.TREES_PLAINS)) {
                PlacedFeaturesLoader.modifyTreePlains(jsonElement);
            }
        }

        if (!isSsrDefault()) {
            if (fileName.equals(JsonIdentifiers.ANCIENT_CITIES)) {
                StructuresLoader.modifyAncientCities(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.DESERT_PYRAMIDS)) {
                StructuresLoader.modifyDesertPyramids(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.END_CITIES)) {
                StructuresLoader.modifyEndCities(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.JUNGLE_TEMPLES)) {
                StructuresLoader.modifyJungleTemples(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.NETHER_COMPLEXES)) {
                StructuresLoader.modifyNetherComplexes(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.PILLAGER_OUTPOSTS)) {
                StructuresLoader.modifyPillagerOutposts(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.RUINED_PORTALS)) {
                StructuresLoader.modifyRuinedPortals(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.SHIPWRECKS)) {
                StructuresLoader.modifyShipwrecks(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.STRONGHOLDS)) {
                StructuresLoader.modifyStrongholds(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.TRIAL_CHAMBERS)) {
                StructuresLoader.modifyTrialChambers(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.VILLAGES)) {
                StructuresLoader.modifyVillages(jsonElement);
            }

            if (fileName.equals(JsonIdentifiers.WOODLAND_MANSIONS)) {
                StructuresLoader.modifyWoodlandMansions(jsonElement);
            }
        }
    }
}
