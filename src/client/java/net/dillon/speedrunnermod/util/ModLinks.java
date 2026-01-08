package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * All Speedrunner Mod {@code external links.}
 */
@Environment(EnvType.CLIENT)
public class ModLinks {
    public static final String CURSEFORGE = "https://www.curseforge.com/minecraft/mc-mods/speedrunner-mod";
    public static final String MODRINTH = "https://modrinth.com/mod/speedrunner-mod";
    public static final String GITHUB = "https://github.com/Dillon8775/Speedrunner-Mod";
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

    public static final String QUESTIONS_AND_ISSUES = "https://docs.google.com/forms/d/e/1FAIpQLSfkTe-xvvYiqLAYmqdMqeE6IgEJkt6S4sfhVfi4D_eAJ2FAXQ/viewform";
    public static final String SUGGESTIONS_AND_FEEDBACK = "https://docs.google.com/forms/d/e/1FAIpQLSf1b3cmA95jpQgY3dd_e8X1oY9jdLlzL36LTyBsjl8Z2ekHQw/viewform";
}