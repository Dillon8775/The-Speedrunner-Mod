package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.world.biome.ModBiomes;
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

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {
    @Shadow @Final @Mutable
    private ResourceKey<Biome>[][] MIDDLE_BIOMES;

    private static final ResourceKey<Biome>[][] VANILLA_MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA},
            {Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {Biomes.SAVANNA, Biomes.SAVANNA, Biomes.FOREST, Biomes.JUNGLE, Biomes.JUNGLE},
            {Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT}
    };

    private static final ResourceKey<Biome>[][] CUSTOM_MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA},
            {Biomes.PLAINS, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {Biomes.SAVANNA, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.FOREST, Biomes.JUNGLE, Biomes.JUNGLE},
            {Biomes.DESERT, Biomes.DESERT, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.DESERT, Biomes.DESERT}
    };

    private static final ResourceKey<Biome>[][] BETTER_MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA},
            {Biomes.PLAINS, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.PLAINS, Biomes.FOREST, Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.FOREST, Biomes.PLAINS, Biomes.DARK_FOREST},
            {Biomes.SAVANNA, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.FOREST, Biomes.JUNGLE, Biomes.PLAINS},
            {Biomes.DESERT, Biomes.DESERT, ModBiomes.SPEEDRUNNERS_WASTELAND, Biomes.DESERT, Biomes.DESERT}
    };

    /**
     * Replaces the vanilla middle-biome table only when the custom biome set is active.
     * Better biomes is a denser variant that keeps rare biomes rare.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (!common().worldGen.generateSpeedrunnersWasteland.getCurrentValue()) {
            this.MIDDLE_BIOMES = VANILLA_MIDDLE_BIOMES;
            return;
        }

        this.MIDDLE_BIOMES = common().worldGen.betterBiomes.getCurrentValue() ? BETTER_MIDDLE_BIOMES : CUSTOM_MIDDLE_BIOMES;
    }
}