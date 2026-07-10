package net.dillon.speedrunnermod.tag;

/**
 * Holder class for all speedrunner mod tags.
 */
public class ModTags {

    /**
     * Initializes all speedrunner mod tags.
     */
    public static void initializeAllTags() {
        ModAttributeTags.initializeAttributeTags();
        ModBiomeTags.initializeBiomeTags();
        ModBlockHardnessTags.initializeBlockHardnessTags();
        ModBlockTags.initializeBlockTags();
        ModDamageTypeTags.initializeDamageTypeTags();
        ModEnchantmentTags.initializeEnchantmentTags();
        ModEntityTypeTags.initializeEntityTypeTags();
        ModFluidTags.initializeFluidTags();
        ModItemTags.initializeItemTags();
        ModPotionsTags.initializePotionTags();
        ModStructureTags.initializeStructureTags();
        ModTradeTags.initializeTradeTags();
    }
}