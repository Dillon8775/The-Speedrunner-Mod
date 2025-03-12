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
public class SpeedrunnersTotemScreen extends AbstractFeatureScreen {

    public SpeedrunnersTotemScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.speedrunners_totem"), true, true);
    }

    @Override
    public @NotNull String linesKey() {
        return "speedrunners_totem";
    }

    @Override
    public int getPageNumber() {
        return 13;
    }

    @Override
    protected Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/items/speedrunners_totem.png");
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
        return ofSpeedrunnerMod("textures/gui/features/recipes/speedrunners_totem_crafting_recipe.png");
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.BLOCKS_AND_ITEMS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.NORMAL;
    }
}