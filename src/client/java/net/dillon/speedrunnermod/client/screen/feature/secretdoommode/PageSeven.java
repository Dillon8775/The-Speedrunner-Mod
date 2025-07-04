package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PageSeven extends PageSix {

    public PageSeven(Screen parent) {
        super(parent);
    }

    @Override
    public int getPageNumber() {
        return 7;
    }

    @Override
    public @NotNull String linesKey() {
        return "page_seven";
    }
}