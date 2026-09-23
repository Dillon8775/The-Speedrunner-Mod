package net.dillon.speedrunnermod.screen.feature;

/**
 * Determines the category of a {@code feature screen}.
 */
public enum FeatureScreenCategory {
    FIRST_TIME_PLAYING("texts"),
    BLOCKS_AND_ITEMS("texts/features/blocksanditems"),
    TOOLS_AND_ARMOR("texts/features/toolsandarmor"),
    POTIONS_AND_ENCHANTMENTS("texts/features/potionsandenchantments"),
    ORES_AND_WORLDGEN("texts/features/oresandworldgen"),
    MISCELLANEOUS("texts/features/miscellaneous"),
    DOOM_MODE("texts/features/doommode"),
    SECRET_DOOM_MODE("texts/features/secretdoommode");

    private final String path;

    FeatureScreenCategory(final String path) {
        this.path = path;
    }

    /**
     * @return the path for a category.
     */
    public String getPath() {
        return this.path;
    }
}