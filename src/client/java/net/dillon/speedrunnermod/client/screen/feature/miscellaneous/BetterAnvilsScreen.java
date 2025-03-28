package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class BetterAnvilsScreen extends AbstractFeatureScreen {

    public BetterAnvilsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.miscellaneous.better_anvils"));
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(ModTexts.CONFIGURE_OPTION, button -> {
            this.client.setScreen(new MainOptionsScreen(this, this.options));
        }).build());
        this.addButtonObject(ButtonWidget.builder(options().main.higherEnchantmentLevels ? ModTexts.DISABLE_HIGHER_ENCHANT_LEVELS : ModTexts.ENABLE_HIGHER_ENCHANT_LEVELS, button -> {
            options().main.higherEnchantmentLevels = !options().main.higherEnchantmentLevels;
            ModOptions.saveConfig();
            this.refreshFeatureScreen(this.getPageNumber(), this.getScreenCategory());
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "better_anvils";
    }

    @Override
    public int getPageNumber() {
        return 27;
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