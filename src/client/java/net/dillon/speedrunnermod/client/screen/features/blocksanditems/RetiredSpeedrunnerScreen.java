package net.dillon.speedrunnermod.client.screen.features.blocksanditems;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class RetiredSpeedrunnerScreen extends AbstractFeatureScreen {

    public RetiredSpeedrunnerScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.retired_speedrunner"), false, false, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "retired_speedrunner";
    }

    @Override
    public int getPageNumber() {
        return 21;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/features/other/retired_speedrunner.png"), this.width / 2 + 60, 180, 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected int getImageWidth() {
        return 60;
    }

    @Override
    protected int getImageHeight() {
        return 112;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}