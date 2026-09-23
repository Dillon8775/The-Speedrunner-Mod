package net.dillon.speedrunnermod.world.feature;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * Material rules for the speedrunner mod.
 */
public class ModMaterialRules {
    public static final ResourceKey<MaterialRule> END_DOOM = createMaterialRule("end_doom");

    /**
     * Bootstraps all material rules.
     */
    public static void bootstrap(final BootstrapContext<MaterialRule> context) {
        context.register(END_DOOM, MaterialRules.state(ModBlocks.DOOM_STONE.defaultBlockState()));
    }

    /**
     * Creates a new material rule key.
     */
    private static ResourceKey<MaterialRule> createMaterialRule(final String name) {
        return ResourceKey.create(Registries.MATERIAL_RULE, ofSpeedrunnerMod(name));
    }
}