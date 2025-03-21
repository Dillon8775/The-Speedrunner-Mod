package net.dillon.speedrunnermod.client.screen.feature.more;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
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
public class ReverseCraftingScreen extends AbstractFeatureScreen {

    public ReverseCraftingScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.more.reverse_crafting"), false, false, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "reverse_crafting";
    }

    @Override
    public int getPageNumber() {
        return 23;
    }

    @Override
    protected void renderCustomImage(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/features/recipes/wool_to_string.png"), this.width / 2 + 35, 155, 0, 0, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/features/recipes/sticks_to_planks.png"), this.width / 2 + 35, 235, 0, 0, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
    }

    @Override
    protected int getImageWidth() {
        return 118;
    }

    @Override
    protected int getImageHeight() {
        return 71;
    }

    @Override
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MORE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}