package net.dillon.speedrunnermod.mixin.main.command;

import net.minecraft.command.CommandSource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Consumer;
import java.util.function.Function;

import static net.minecraft.command.CommandSource.shouldSuggest;

@Mixin(CommandSource.class)
public interface CommandSourceMixin {

    @Overwrite
    static <T> void forEachMatching(Iterable<T> candidates, String remaining, Function<T, Identifier> identifier, Consumer<T> action) {
        boolean bl = remaining.indexOf(58) > -1;

        for (T object : candidates) {
            Identifier identifier2 = (Identifier)identifier.apply(object);
            if (bl) {
                String string = identifier2.toString();
                if (shouldSuggest(remaining, string)) {
                    action.accept(object);
                }
            } else if (shouldSuggest(remaining, identifier2.getNamespace())
                    || identifier2.getNamespace().equals("minecraft") || identifier2.getNamespace().equals("speedrunnermod") && shouldSuggest(remaining, identifier2.getPath())) {
                action.accept(object);
            }
        }
    }

//    /**
//     * Fixes "bug" where modded items (or Speedrunner Mod items, commands, enchantments, etc.) do not display as a recommended entry when typing in the chat.
//     */
//    @ModifyArg(method = "forEachMatching(Ljava/lang/Iterable;Ljava/lang/String;Ljava/util/function/Function;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;getNamespace()Ljava/lang/String;", ordinal = 1))
//    private static String suggestModdedIdentifiers(Identifier id) {
//        return o.equals("minecraft") || o.equals("speedrunnermod");
//    }
}