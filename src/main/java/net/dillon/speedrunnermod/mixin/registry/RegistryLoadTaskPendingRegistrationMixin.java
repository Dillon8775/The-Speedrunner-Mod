package net.dillon.speedrunnermod.mixin.registry;

import com.google.gson.JsonElement;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Decoder;
import net.dillon.dillonlib.mixinplugin.Predicated;
import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.data.*;
import net.minecraft.resources.RegistryLoadTask;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.main.CommonMain.common;

@Predicated
@Mixin(RegistryLoadTask.PendingRegistration.class)
public class RegistryLoadTaskPendingRegistrationMixin {

    /**
     * Directly modifies {@code json files} to change and modify {@code world generation features,} including structures, mob spawns, and more.
     * <p>See package {@link net.dillon.speedrunnermod.data} fore more on this.</p>
     */
    @Author(Authors.MAXENCEDC)
    @Inject(method = "loadFromResource", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Decoder;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static <T> void customDataGeneration(Decoder<T> elementDecoder, RegistryOps<JsonElement> ops, ResourceKey<T> elementKey, Resource thunk, CallbackInfoReturnable<Either<T, Exception>> cir, @Local JsonElement jsonElement) {
        String registryPath = elementKey.registry().getPath();
        String elementPath = elementKey.identifier().getPath();
        String fileName = registryPath + "/" + elementPath + ".json";

        LoaderMain loaderMain = new LoaderMain(jsonElement);
        EntitySpawnsLoader entitySpawnsLoader = new EntitySpawnsLoader(loaderMain);
        NetherBiomesLoader netherBiomesLoader = new NetherBiomesLoader(entitySpawnsLoader);
        EndBiomesLoader endBiomesLoader = new EndBiomesLoader(entitySpawnsLoader);
        PlacedFeaturesLoader placedFeaturesLoader = new PlacedFeaturesLoader(loaderMain);
        StructuresLoader structuresLoader = new StructuresLoader(loaderMain);

        for (int i = 0; i < EntitySpawnsLoader.biomesWithDefaultMonsters().size(); i++) {
            if (fileName.equals(EntitySpawnsLoader.biomesWithDefaultMonsters().get(i))) {
                entitySpawnsLoader.putDefaultMonsters();
            }
        }

        for (int i = 0; i < EntitySpawnsLoader.biomesWithFarmAnimals().size(); i++) {
            if (fileName.equals(EntitySpawnsLoader.biomesWithFarmAnimals().get(i))) {
                entitySpawnsLoader.putDefaultCreatures();
            }
        }

        for (int i = 0; i < EntitySpawnsLoader.biomesWithWaterAnimals().size(); i++) {
            if (fileName.equals(EntitySpawnsLoader.biomesWithWaterAnimals().get(i))) {
                entitySpawnsLoader.putDefaultWaterCreatures();
            }
        }

        if (fileName.equals(JsonIdentifiers.BASALT_DELTAS)) {
            netherBiomesLoader.modifyBasaltDeltas();
        }

        if (fileName.equals(JsonIdentifiers.CRIMSON_FOREST)) {
            netherBiomesLoader.modifyCrimsonForest();
        }

        if (fileName.equals(JsonIdentifiers.NETHER_WASTES)) {
            netherBiomesLoader.modifyNetherWastes();
        }

        if (fileName.equals(JsonIdentifiers.SOUL_SAND_VALLEY)) {
            netherBiomesLoader.modifySoulSandValley();
        }

        if (fileName.equals(JsonIdentifiers.WARPED_FOREST)) {
            netherBiomesLoader.modifyWarpedForest();
        }


        if (fileName.equals(JsonIdentifiers.THE_END)) {
            endBiomesLoader.modifyTheEnd();
        }

        if (fileName.equals(JsonIdentifiers.END)) {
            endBiomesLoader.modifyEnd();
        }

        if (fileName.equals(JsonIdentifiers.MONSTER_ROOM) || fileName.equals(JsonIdentifiers.MONSTER_ROOM_DEEP)) {
            placedFeaturesLoader.modifyMonsterRoom();
        }

        if (common().worldgen().commonOres) {
            String oreDiamond = JsonIdentifiers.ORE_DIAMOND;
            if (fileName.equals(oreDiamond) || fileName.equals(JsonIdentifiers.ORE_DIAMOND_BURIED)) {
                placedFeaturesLoader.modifyOreDiamond(fileName, oreDiamond);
            }

            if (fileName.equals(JsonIdentifiers.ORE_DIAMOND_LARGE)) {
                placedFeaturesLoader.modifyOreDiamondLarge();
            }

            String oreLapis = JsonIdentifiers.ORE_LAPIS;
            if (fileName.equals(oreLapis) || fileName.equals(JsonIdentifiers.ORE_LAPIS_BURIED)) {
                placedFeaturesLoader.modifyOreLapis(fileName, oreLapis);
            }
        }

        if (common().worldgen().commonPlainTrees) {
            if (fileName.equals(JsonIdentifiers.TREES_PLAINS)) {
                placedFeaturesLoader.modifyTreePlains();
            }
        }

        if (common().worldgen().makeStructuresMoreCommon) {
            if (fileName.equals(JsonIdentifiers.ABANDONED_CAMP)) {
                structuresLoader.modifyAbandonedCamp();
            }

            if (fileName.equals(JsonIdentifiers.ANCIENT_CITIES)) {
                structuresLoader.modifyAncientCities();
            }

            if (fileName.equals(JsonIdentifiers.DESERT_PYRAMIDS)) {
                structuresLoader.modifyDesertPyramids();
            }

            if (fileName.equals(JsonIdentifiers.END_CITIES)) {
                structuresLoader.modifyEndCities();
            }

            if (fileName.equals(JsonIdentifiers.JUNGLE_TEMPLES)) {
                structuresLoader.modifyJunglePyramids();
            }

            if (fileName.equals(JsonIdentifiers.MINESHAFTS)) {
                structuresLoader.modifyMineshafts();
            }

            if (fileName.equals(JsonIdentifiers.IGLOOS)) {
                structuresLoader.modifyIgloos();
            }

            if (fileName.equals(JsonIdentifiers.NETHER_COMPLEXES)) {
                structuresLoader.modifyNetherComplexes();
            }

            if (fileName.equals(JsonIdentifiers.PILLAGER_OUTPOSTS)) {
                structuresLoader.modifyPillagerOutposts();
            }

            if (fileName.equals(JsonIdentifiers.RUINED_PORTALS)) {
                structuresLoader.modifyRuinedPortals();
            }

            if (fileName.equals(JsonIdentifiers.SHIPWRECKS)) {
                structuresLoader.modifyShipwrecks();
            }

            if (fileName.equals(JsonIdentifiers.OCEAN_RUINS)) {
                structuresLoader.modifyOceanRuins();
            }

            if (fileName.equals(JsonIdentifiers.SWAMP_HUTS)) {
                structuresLoader.modifySwampHuts();
            }

            if (fileName.equals(JsonIdentifiers.STRONGHOLDS)) {
                structuresLoader.modifyStrongholds();
            }

            if (fileName.equals(JsonIdentifiers.TRIAL_CHAMBERS)) {
                structuresLoader.modifyTrialChambers();
            }

            if (fileName.equals(JsonIdentifiers.TRAIL_RUINS)) {
                structuresLoader.modifyTrailRuins();
            }

            if (fileName.equals(JsonIdentifiers.VILLAGES)) {
                structuresLoader.modifyVillages();
            }

            if (fileName.equals(JsonIdentifiers.WOODLAND_MANSIONS)) {
                structuresLoader.modifyWoodlandMansions();
            }
        }
    }
}
