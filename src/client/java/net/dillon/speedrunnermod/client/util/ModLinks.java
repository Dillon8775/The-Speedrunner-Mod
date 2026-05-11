package net.dillon.speedrunnermod.client.util;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;

/**
 * All Speedrunner Mod {@code external links.}
 */
public class ModLinks {
    public static final String CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod";
    public static final String MODRINTH = "https://modrinth.com/mod/speedrunner-mod";
    public static final String GITHUB = "https://github.com/Dillon8775/Speedrunner-Mod";
    public static final String SHOWCASE_VIDEO = "https://youtu.be/08z9VvnFacw";
    public static final String RELEASE_TRAILER = "https://youtu.be/u37ujBiCMCw";
    public static final String LEADERBOARDS = "https://sites.google.com/view/dillon8775/leaderboards";
    public static final String LEADERBOARDS_SUBMISSION = "https://docs.google.com/forms/u/0/d/e/1FAIpQLScnw-e4gkYyHGEVztWY-DLmm64jIbnqLw_LCIBkF2uQFfqVTg/viewform";
    public static final String LEADERBOARDS_SPREADSHEET = "https://docs.google.com/spreadsheets/d/1JHiHHzRrcQnEeLCqxcjx9znPBJbCVDkhZ6ump9VtKHY";

    public static final String SODIUM = link("https://modrinth.com/mod/sodium");
    public static final String LITHIUM = link("https://modrinth.com/mod/lithium");
    public static final String SPEEDRUNIGT = link("https://modrinth.com/mod/speedrunigt");
    public static final String KRYPTON = link("https://modrinth.com/mod/krypton");
    public static final String SIMPLE_KEYBINDS = link("https://modrinth.com/mod/simple-keybinds");
    public static final String QUALITY_OF_QUESO = link("https://modrinth.com/mod/quality-of-queso");

    /**
     * Returns a {@code link} with correct version filtering.
     */
    private static String link(String url) {
        return url + "/versions?g=" + SpeedrunnerMod.MC_VERSION + "&l=fabric";
    }

    public static final String QUESTIONS_AND_ISSUES = "https://discord.gg/vfqEAn4YFy";
}