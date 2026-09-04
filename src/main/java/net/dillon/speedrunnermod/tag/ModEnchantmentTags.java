package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.component.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createEnchantmentTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

public class ModEnchantmentTags extends FabricTagsProvider<Enchantment> {
    public static final TagKey<Enchantment> SPEEDRUNNER_ENCHANTMENTS = createEnchantmentTag(ofSpeedrunnerMod("speedrunner_enchantments"));
    public static final TagKey<Enchantment> ON_RANDOM_SPEEDRUNNER_LOOT = createEnchantmentTag(ofSpeedrunnerMod("on_random_speedrunner_loot"));
    public static final TagKey<Enchantment> RETIRED_SPEEDRUNNER_TRADES = createEnchantmentTag(ofSpeedrunnerMod("retired_speedrunner_trades"));
    public static final TagKey<Enchantment> WITHERED_ENCHANTMENTS = createEnchantmentTag(ofSpeedrunnerMod("withered_enchantments"));
    public static final TagKey<Enchantment> ON_GOLDEN_SWORD_IN_RUINED_PORTAL = createEnchantmentTag(ofSpeedrunnerMod("on_golden_sword_in_ruined_portal"));

    public ModEnchantmentTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ENCHANTMENT, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(ModEnchantmentTags.SPEEDRUNNER_ENCHANTMENTS)
                .add(ModEnchantments.DASH)
                .add(ModEnchantments.COOLDOWN)
                .add(ModEnchantments.WITHERED);

        tag(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                .add(Enchantments.PROTECTION)
                .add(Enchantments.FIRE_PROTECTION)
                .add(Enchantments.BLAST_PROTECTION)
                .add(Enchantments.PROJECTILE_PROTECTION)
                .add(Enchantments.RESPIRATION)
                .add(Enchantments.AQUA_AFFINITY)
                .add(Enchantments.THORNS)
                .add(Enchantments.DEPTH_STRIDER)
                .add(Enchantments.SHARPNESS)
                .add(Enchantments.LOOTING)
                .add(Enchantments.SWEEPING_EDGE)
                .add(Enchantments.EFFICIENCY)
                .add(Enchantments.FORTUNE)
                .add(Enchantments.UNBREAKING)
                .add(Enchantments.POWER)
                .add(Enchantments.PUNCH)
                .add(Enchantments.FLAME)
                .add(Enchantments.INFINITY)
                .add(Enchantments.MENDING)
                .add(Enchantments.FROST_WALKER)
                .addTag(ModEnchantmentTags.SPEEDRUNNER_ENCHANTMENTS);

        tag(ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES)
                .add(Enchantments.PROTECTION)
                .add(Enchantments.FEATHER_FALLING)
                .add(Enchantments.THORNS)
                .add(Enchantments.SHARPNESS)
                .add(Enchantments.FIRE_ASPECT)
                .add(Enchantments.LOOTING)
                .add(Enchantments.FORTUNE)
                .add(Enchantments.POWER)
                .add(Enchantments.INFINITY)
                .add(Enchantments.MENDING)
                .add(ModEnchantments.COOLDOWN);

        tag(ModEnchantmentTags.ON_GOLDEN_SWORD_IN_RUINED_PORTAL)
                .add(Enchantments.LOOTING);

        tag(ModEnchantmentTags.WITHERED_ENCHANTMENTS)
                .add(ModEnchantments.WITHERED);

        tag(EnchantmentTags.NON_TREASURE)
                .addTag(ModEnchantmentTags.SPEEDRUNNER_ENCHANTMENTS);
    }
}