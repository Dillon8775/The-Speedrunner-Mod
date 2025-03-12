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
public class EnderThrusterScreen extends AbstractFeatureScreen {

    public EnderThrusterScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.blocks_and_items.ender_thruster"), true, true);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "ender_thruster";
    }

    @Override
    public int getPageNumber() {
        return 17;
    }

    @Override
    protected Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/items/ender_thruster.png");
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
        return ofSpeedrunnerMod("textures/gui/features/recipes/ender_thruster_crafting_recipe.png");
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