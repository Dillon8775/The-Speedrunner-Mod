package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.screen.WorkbenchScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The base screen for the {@code Speedrunner's Workbench.}
 */
@Environment(EnvType.CLIENT)
public class WorkbenchScreen extends ForgingScreen<WorkbenchScreenHandler> {
    private static final Identifier ERROR_TEXTURE = Identifier.ofVanilla("container/anvil/error");
    private static final Identifier TEXTURE = ofSpeedrunnerMod("textures/gui/container/workbench.png");
    private final PlayerEntity player;

    public WorkbenchScreen(WorkbenchScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, TEXTURE);
        this.titleX = 40;
        this.titleY = 20;
        this.player = inventory.player;
    }

    /**
     * Renders tooltips in {@code Speedrunner's Workbench's slots} to help the player.
     */
    private void renderSlotTooltip(DrawContext context, int mouseX, int mouseY) {
        Optional<Text> optional = Optional.empty();

        if (this.focusedSlot != null) {
            ItemStack focusedSlotStack = this.focusedSlot.getStack();
            if (focusedSlotStack.isEmpty()) {
                switch (this.focusedSlot.id) {
                    case 0 -> optional = Optional.of(Text.translatable("block.speedrunnermod.speedrunners_workbench.enchanted_tool").formatted(Formatting.BLUE));
                    case 1 -> optional = Optional.of(Text.translatable("block.speedrunnermod.speedrunners_workbench.unenchanted_tool").formatted(Formatting.LIGHT_PURPLE));
                    case 2 -> optional = Optional.of(Text.translatable("block.speedrunnermod.speedrunners_workbench.output").formatted(Formatting.AQUA));
                }
            }
        }

        optional.ifPresent(text -> context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 115), mouseX, mouseY));
    }

    /**
     * Call {@link WorkbenchScreen#renderSlotTooltip(DrawContext, int, int)}.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderSlotTooltip(context, mouseX, mouseY);
    }

    /**
     * Close the handled screen when pressing escape on keyboard.
     */
    @Override
    public boolean keyPressed(KeyInput input) {
        if (input.key() == GLFW.GLFW_KEY_ESCAPE) {
            this.client.player.closeHandledScreen();
        }

        return super.keyPressed(input);
    }

    /**
     * Copied over from {@link AnvilScreenHandler}.
     * <p>Draws the foreground.</p>
     */
    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
        int i = this.handler.getLevelCost();
        if (i > 0) {
            int j = -8323296; // green
            Text text;
            if (!this.handler.getSlot(2).hasStack()) {
                text = null;
            } else {
                text = Text.translatable("block.speedrunnermod.speedrunners_workbench.cost", i);
                if (!this.handler.getSlot(2).canTakeItems(this.player)) {
                    j = -40864; // red
                }
            }

            if (text != null) {
                int k = this.backgroundWidth - 8 - this.textRenderer.getWidth(text) - 2;
                context.fill(k - 2, 67, this.backgroundWidth - 8, 79, 1325400064);
                context.drawTextWithShadow(this.textRenderer, text, k, 69, j);
            }
        }
    }

    /**
     * Draws the red arrow texture when something doesn't go righ.t
     */
    @Override
    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if ((this.handler.getSlot(0).hasStack() || this.handler.getSlot(1).hasStack()) && !this.handler.getSlot(this.handler.getResultSlotIndex()).hasStack()) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, ERROR_TEXTURE, x + 99, y + 45, 28, 21);
        }
    }

    @Override
    public void resize(int width, int height) {
        this.init(width, height);
    }
}