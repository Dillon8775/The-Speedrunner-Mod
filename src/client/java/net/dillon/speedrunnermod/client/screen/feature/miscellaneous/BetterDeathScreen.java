package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class BetterDeathScreen extends AbstractFeatureScreen {

    public BetterDeathScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.miscellaneous.better_death_screen"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(options().main.showDeathCords ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().main.showDeathCords = !options().main.showDeathCords;
            this.refreshNonRestartableFeature();
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "better_death_screen";
    }

    @Override
    public int getPageNumber() {
        return 26;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}