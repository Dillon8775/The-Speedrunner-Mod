package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class SafeBootScreen extends AbstractModScreen {

    public SafeBootScreen(Screen parent) {
        super(parent, ModTexts.TITLE_SAFE_BOOT);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.FIX_AND_RESTART, (buttonWidget) -> {
            SpeedrunnerModClient.fixOptions();
            info("Fixing options! Re-launch to apply changes.");
            this.client.scheduleStop();
        }).dimensions(this.width / 2 - 50 - 105, height, 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.CLOSE_GAME, (buttonWidget) -> {
            info("Closing game! No changes were made.");
            this.client.scheduleStop();
        }).dimensions(this.width / 2 - 50, height, 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.PROCEED_ANYWAY, (buttonWidget) -> {
            info("Proceeding. Due to corrupt options, you may experience issues. Re-launch the game to fix options.");
            this.client.setScreen(new TitleScreen(false));
        }).dimensions(this.width / 2 - 50 + 105, height, 100, 20).build());
    }

    @Override
    public void close() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.options.error.line1"), this.width / 2, 100, 16777215);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.options.error.line2"), this.width / 2, 120, 16777215);
    }

    @Override
    protected String pageId() {
        return "bfpogr09wi0w";
    }

    @Override
    protected int columns() {
        return 3;
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