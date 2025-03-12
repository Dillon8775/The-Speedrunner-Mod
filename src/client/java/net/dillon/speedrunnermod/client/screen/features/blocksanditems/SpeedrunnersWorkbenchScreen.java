package net.dillon.speedrunnermod.client.screen.features.blocksanditems;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class SpeedrunnersWorkbenchScreen extends AbstractFeatureScreen {

    public SpeedrunnersWorkbenchScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_workbench"), true, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunners_workbench";
    }

    @Override
    public int getPageNumber() {
        return 20;
    }

    @Override
    protected Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/blocks/speedrunners_workbench.png");
    }

    @Override
    protected int getImageWidth() {
        return 32;
    }

    @Override
    protected int getImageHeight() {
        return 32;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return ofSpeedrunnerMod("textures/gui/features/recipes/speedrunners_workbench_crafting_recipe.png");
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