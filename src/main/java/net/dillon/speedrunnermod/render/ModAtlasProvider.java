package net.dillon.speedrunnermod.render;

import net.minecraft.client.data.AtlasProvider;
import net.minecraft.data.PackOutput;

/**
 * Stores all atlas providers for the speedrunner mod.
 */
public class ModAtlasProvider extends AtlasProvider {

    public ModAtlasProvider(PackOutput output) {
        super(output);
    }

//    @Override
//    public CompletableFuture<?> run(CachedOutput cache) {
//        return CompletableFuture.allOf(
//                this.storeAtlas(
//                        cache,
//                        AtlasIds.SHIELD_PATTERNS,
//                        List.of(
//                                forMapper(Sheets.SHIELD_MAPPER)
//                        )
//        );
//    }
//
//    @Override
//    public String getName() {
//        return "Speedrunner Mod Atlas Provider";
//    }
}