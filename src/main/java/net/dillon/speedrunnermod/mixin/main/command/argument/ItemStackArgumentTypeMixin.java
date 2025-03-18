package net.dillon.speedrunnermod.mixin.main.command.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

@ChatGPT(Credit.FULL_CREDIT)
@Mixin(ItemStackArgumentType.class)
public class ItemStackArgumentTypeMixin {

    @Inject(method = "listSuggestions", at = @At("HEAD"), cancellable = true)
    private void modifyItemSuggestions(CommandContext<CommandSource> context, SuggestionsBuilder builder, CallbackInfoReturnable<CompletableFuture<Suggestions>> cir) {
        String input = builder.getRemaining(); // Get user-typed input

        // Suggest only items that match what has been typed
        for (Identifier id : Registries.ITEM.getIds()) {
            if (id.getPath().startsWith(input)) { // Match user input dynamically
                builder.suggest(id.toString());
            }
        }

        cir.setReturnValue(builder.buildFuture());
    }

    /**
     * Fixes bug where typing in a speedrunner mod item does without the {@code "speedrunnermod:"} namespace, doesn't work.
     */
    @Inject(method = "parse", at = @At("HEAD"), cancellable = true)
    private void modifyItemParsing(StringReader reader, CallbackInfoReturnable<ItemStackArgument> cir) {
        int cursor = reader.getCursor();
        try {
            Identifier id = Identifier.fromCommandInput(reader); // Read input

            // Check if the item exists
            if (!Registries.ITEM.containsId(id)) {
                Identifier moddedId = ofSpeedrunnerMod(id.getPath()); // Assume mod namespace
                if (Registries.ITEM.containsId(moddedId)) {
                    cir.setReturnValue(new ItemStackArgument(Registries.ITEM.get(moddedId).getRegistryEntry(), Registries.ITEM.get(moddedId).getDefaultStack().getComponentChanges()));
                    return;
                }
            }

        } catch (CommandSyntaxException e) {
            reader.setCursor(cursor); // Reset cursor if failed
        }
    }
}