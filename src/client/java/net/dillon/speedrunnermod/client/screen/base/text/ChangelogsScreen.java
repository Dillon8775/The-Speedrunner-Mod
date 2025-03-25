package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.SpeedrunnerModClient;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.dillon.speedrunnermod.client.screen.base.text.changelog.balancingupdate.v110;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Main menu for changelogs.
 */
@Environment(EnvType.CLIENT)
public class ChangelogsScreen extends AbstractModScreen {

    public ChangelogsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_CHANGELOGS);
    }

    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addSingleButton(ButtonWidget.builder(Text.literal("v1.10 Changelog").formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new v110(this, this.options));
        }).build());

        // Add all changelogs to screen
        List<AbstractChangelogScreen> sortedScreens = SpeedrunnerModClient.ALL_CHANGELOG_SCREENS.stream()
                .map(constructor -> constructor.apply(this, this.options))
                .sorted((a, b) -> {
                    List<Integer> versionA = parseVersion(a.getTitle().getString());
                    List<Integer> versionB = parseVersion(b.getTitle().getString());

                    int len = Math.max(versionA.size(), versionB.size());
                    for (int i = 0; i < len; i++) {
                        int numA = (i < versionA.size()) ? versionA.get(i) : 0;
                        int numB = (i < versionB.size()) ? versionB.get(i) : 0;
                        if (numA != numB) {
                            return Integer.compare(numB, numA); // descending
                        }
                    }
                    return 0;
                })
                .toList();

        // Skip first entry (to add latest changelog to top)
        for (int i = 1; i < sortedScreens.size(); i++) {
            AbstractScrollableScreen screen = sortedScreens.get(i);

            this.buttonList.addSingleButton(ButtonWidget.builder(screen.getTitle(), btn -> this.client.setScreen(screen)
            ).build());
        }

        super.init();
    }

    /**
     * Pareses version number.
     */
    private List<Integer> parseVersion(String title) {
        // Example input: "v1.9.8 Changelog"
        try {
            String versionPart = title.replaceAll("[^0-9.]", ""); // "1.9.8"
            String[] parts = versionPart.split("\\.");
            List<Integer> versionNumbers = new ArrayList<>();
            for (String part : parts) {
                versionNumbers.add(Integer.parseInt(part));
            }
            return versionNumbers;
        } catch (Exception e) {
            return List.of(0); // fallback if parsing fails
        }
    }

    @Override
    protected String pageId() {
        return "fddkpfsdipowa";
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}