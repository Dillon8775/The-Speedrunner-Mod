package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.biome.ModBiomeKeys;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {
    @Shadow @Final @Mutable
    private ResourceKey<Biome>[][] MIDDLE_BIOMES;

    /**
     * Changes biome generation, according to {@code Better Biomes} and {@code Custom Biomes}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (options().main.betterBiomes.getCurrentValue() && options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue()) {
            this.MIDDLE_BIOMES = new ResourceKey[][]{
                    {ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.PLAINS,
                            Biomes.DESERT,
                            Biomes.SAVANNA},
                    {Biomes.PLAINS,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.FOREST,
                            Biomes.TAIGA,
                            Biomes.OLD_GROWTH_SPRUCE_TAIGA},
                    {Biomes.FLOWER_FOREST,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.FOREST,
                            Biomes.DESERT,
                            Biomes.DARK_FOREST},
                    {Biomes.SAVANNA,
                            Biomes.SAVANNA,
                            Biomes.FOREST,
                            Biomes.JUNGLE,
                            Biomes.PLAINS},
                    {Biomes.DESERT,
                            Biomes.DESERT,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.DESERT,
                            Biomes.DESERT}};
        } else if (options().main.customDataGeneration.getCurrentValue() && options().main.customBiomesAndCustomBiomeFeatures.getCurrentValue()) {
            this.MIDDLE_BIOMES = new ResourceKey[][]{
                    {ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.SNOWY_PLAINS,
                            Biomes.SNOWY_TAIGA,
                            Biomes.TAIGA},
                    {Biomes.PLAINS,
                            ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY,
                            Biomes.FOREST,
                            Biomes.TAIGA,
                            Biomes.OLD_GROWTH_SPRUCE_TAIGA},
                    {Biomes.FLOWER_FOREST,
                            Biomes.PLAINS,
                            Biomes.FOREST,
                            Biomes.BIRCH_FOREST,
                            Biomes.DARK_FOREST},
                    {Biomes.SAVANNA,
                            Biomes.SAVANNA,
                            Biomes.FOREST,
                            Biomes.JUNGLE,
                            Biomes.JUNGLE},
                    {Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT}};
        } else {
            this.MIDDLE_BIOMES = new ResourceKey[][]{
                    {Biomes.SNOWY_PLAINS,
                            Biomes.SNOWY_PLAINS,
                            Biomes.SNOWY_PLAINS,
                            Biomes.SNOWY_TAIGA,
                            Biomes.TAIGA},
                    {Biomes.PLAINS,
                            Biomes.PLAINS,
                            Biomes.FOREST,
                            Biomes.TAIGA,
                            Biomes.OLD_GROWTH_SPRUCE_TAIGA},
                    {Biomes.FLOWER_FOREST,
                            Biomes.PLAINS,
                            Biomes.FOREST,
                            Biomes.BIRCH_FOREST,
                            Biomes.DARK_FOREST},
                    {Biomes.SAVANNA,
                            Biomes.SAVANNA,
                            Biomes.FOREST,
                            Biomes.JUNGLE,
                            Biomes.JUNGLE},
                    {Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT,
                            Biomes.DESERT}};
        }
    }
}