package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.base.text.changelog.balancingupdate.v110;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Main menu for changelogs.
 */
@Environment(EnvType.CLIENT)
public class ChangelogsScreen extends AbstractModScreen {

    public ChangelogsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_CHANGELOGS);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addSingleButton(ButtonWidget.builder(Text.literal("v1.10 Changelog").formatted(Formatting.GREEN), (button) -> {
            this.client.setScreen(new v110(parent, options));
        }).build());

        super.init();
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