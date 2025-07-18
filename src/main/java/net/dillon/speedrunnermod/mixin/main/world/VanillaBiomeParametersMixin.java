package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {
    @Shadow @Final @Mutable
    private RegistryKey<Biome>[][] commonBiomes;

    /**
     * Changes biome generation, according to {@code Better Biomes} and {@code Custom Biomes}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (options().main.betterBiomes.getCurrentValue() && options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue()) {
            this.commonBiomes = new RegistryKey[][]{
                    {ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.PLAINS,
                            BiomeKeys.DESERT,
                            BiomeKeys.SAVANNA},
                    {BiomeKeys.PLAINS,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.DESERT,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.PLAINS},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        } else if (options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue()) {
            this.commonBiomes = new RegistryKey[][]{
                    {ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_TAIGA,
                            BiomeKeys.TAIGA},
                    {BiomeKeys.PLAINS,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.BIRCH_FOREST,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.JUNGLE},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        } else {
            this.commonBiomes = new RegistryKey[][]{
                    {BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_PLAINS,
                            BiomeKeys.SNOWY_TAIGA,
                            BiomeKeys.TAIGA},
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.BIRCH_FOREST,
                            BiomeKeys.DARK_FOREST},
                    {BiomeKeys.SAVANNA,
                            BiomeKeys.SAVANNA,
                            BiomeKeys.FOREST,
                            BiomeKeys.JUNGLE,
                            BiomeKeys.JUNGLE},
                    {BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        }
    }
}