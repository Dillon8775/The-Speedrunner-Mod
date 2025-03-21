package net.dillon.speedrunnermod.client.screen.base.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The Speedrunner Mod's {@code "fast world creation"} options, which set the settings of each new world created.
 */
@Environment(EnvType.CLIENT)
public class FastWorldCreationOptionsScreen extends AbstractModScreen {

    public FastWorldCreationOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    /**
     * All of the {@code fast world creation options.}
     */
    private SimpleOption<?>[] fwcOptions() {
        return new SimpleOption[]{
                ModListOptions.FAST_WORLD_CREATION,
                options().client.fastWorldCreation ? ModListOptions.GAMEMODE : ModListOptions.Inactiveable.IAO_GAMEMODE,
                options().client.fastWorldCreation ? ModListOptions.DIFFICULTY : ModListOptions.Inactiveable.IAO_DIFFICULTY,
                options().client.fastWorldCreation ? ModListOptions.ALLOW_CHEATS : ModListOptions.Inactiveable.IAO_ALLOW_CHEATS
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();

        this.optionList.addAll(fwcOptions());
        this.deactivateButton(0, ButtonSide.RIGHT, options().client.fastWorldCreation);
        this.deactivateButton(1, ButtonSide.LEFT, options().client.fastWorldCreation);
        this.deactivateButton(1, ButtonSide.RIGHT, options().client.fastWorldCreation);

        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected String pageId() {
        return "gfnipfioad";
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}