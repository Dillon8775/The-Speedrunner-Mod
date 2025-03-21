package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.more.ResetKeyScreen;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@Environment(EnvType.CLIENT)
public class WitherSwordScreen extends AbstractFeatureScreen {

    public WitherSwordScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor.wither_sword"), true, true, new SpeedrunnersWastelandBiomeScreen(parent, options), Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), new ResetKeyScreen(parent, options), Text.translatable("speedrunnermod.menu.features.more"), new SpeedrunnerIngotsScreen(parent, options), Text.translatable("speedrunnermod.menu.features.blocks_and_items"), false, null, null);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "wither_sword";
    }

    @Override
    public int getPageNumber() {
        return this.getMaxPages();
    }

    @Override
    protected @NotNull Identifier getImage() {
        return ofSpeedrunnerMod("textures/gui/features/items/wither_sword.png");
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
    protected @NotNull Identifier getCraftingRecipeImage() {
        return ofSpeedrunnerMod("textures/gui/features/recipes/wither_sword_crafting_recipe.png");
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FINAL;
    }
}