package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.SpeedrunnerMod;
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

@Deprecated(forRemoval = true)
@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {
    @Shadow @Final @Mutable
    private RegistryKey<Biome>[][] commonBiomes;

    /**
     * Changes biome generation, according to the {@code better biomes} and {@code custom biomes} options.
     * <p>The {@code Speedrunner's Wasteland} biome is now handled in {@link net.dillon.speedrunnermod.world.api}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (SpeedrunnerMod.options().main.betterBiomes) {
            this.commonBiomes = new RegistryKey[][]{
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.DESERT,
                            BiomeKeys.SAVANNA},
                    {BiomeKeys.PLAINS,
                            BiomeKeys.PLAINS,
                            BiomeKeys.FOREST,
                            BiomeKeys.TAIGA,
                            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                    {BiomeKeys.FLOWER_FOREST,
                            BiomeKeys.PLAINS,
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
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT,
                            BiomeKeys.DESERT}};
        }
    }
}