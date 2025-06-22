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
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(ItemStackArgumentType.class)
public class ItemStackArgumentTypeMixin {

    /**
     * Fixes suggestions bug when typing in speedrunner mod items.
     */
    @ChatGPT(Credit.MOST_CREDIT)
    @Inject(method = "listSuggestions", at = @At("HEAD"), cancellable = true)
    private void modifyItemSuggestions(CommandContext<CommandSource> context, SuggestionsBuilder builder, CallbackInfoReturnable<CompletableFuture<Suggestions>> cir) {
        String input = builder.getRemaining(); // Get user-typed input

        for (Identifier id : Registries.ITEM.getIds()) {
            if (input.contains(":")) {
                // If a namespace is typed, suggest only full namespaced versions
                if (id.toString().contains(input)) {
                    builder.suggest(id.toString());
                }
            } else {
                // If no namespace is typed, suggest only item paths (shorthand)
                if (id.getPath().contains(input)) {
                    builder.suggest(id.getPath());
                }
            }
        }

        cir.setReturnValue(builder.buildFuture());
    }

    /**
     * Fixes bug where typing in a speedrunner mod item does without the {@code "speedrunnermod:"} namespace, doesn't work.
     */
    @ChatGPT(Credit.MOST_CREDIT)
    @Inject(method = "parse", at = @At("HEAD"), cancellable = true)
    private void modifyItemParsing(StringReader reader, CallbackInfoReturnable<ItemStackArgument> cir) {
        int cursor = reader.getCursor();
        try {
            Identifier id = Identifier.fromCommandInput(reader); // Read input

            RegistryEntry<Item> entry;

            if (Registries.ITEM.containsId(id)) {
                entry = Registries.ITEM.getEntry(id).orElseThrow();
                cir.setReturnValue(new ItemStackArgument(entry, ComponentChanges.EMPTY));
            }

            for (int i = 0; i < options().advanced.modIds.length; i++) {
                Identifier modId = Identifier.of(options().advanced.modIds[i], id.getPath());
                if (Registries.ITEM.containsId(modId)) {
                    entry = Registries.ITEM.getEntry(modId).orElseThrow();
                    cir.setReturnValue(new ItemStackArgument(entry, entry.value().getDefaultStack().getComponentChanges()));
                }
            }

//            Identifier vanillaId = Identifier.ofVanilla(id.getPath());
//            if (Registries.ITEM.containsId(vanillaId)) {
//                 entry = Registries.ITEM.getEntry(vanillaId).orElseThrow();
//                 cir.setReturnValue(new ItemStackArgument(entry, ComponentChanges.EMPTY));
//            }
//
//            Identifier speedrunnerModId = ofSpeedrunnerMod(id.getPath());
//            if (Registries.ITEM.containsId(speedrunnerModId)) {
//                entry = Registries.ITEM.getEntry(speedrunnerModId).orElseThrow();
//                cir.setReturnValue(new ItemStackArgument(entry, ComponentChanges.EMPTY));
//            }

//            // Check if the item exists
//            if (!Registries.ITEM.containsId(id)) {
//                Identifier moddedId = ofSpeedrunnerMod(id.getPath()); // Assume mod namespace
//                if (Registries.ITEM.containsId(moddedId)) {
//                    cir.setReturnValue(new ItemStackArgument(Registries.ITEM.get(moddedId).getRegistryEntry(), Registries.ITEM.get(moddedId).getDefaultStack().getComponentChanges()));
//                    return;
//                }
//            }

        } catch (CommandSyntaxException e) {
            reader.setCursor(cursor); // Reset cursor if failed
        }
    }
}