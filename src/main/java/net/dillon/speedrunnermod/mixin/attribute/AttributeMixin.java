package net.dillon.speedrunnermod.mixin.attribute;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Attribute.class)
public abstract class AttributeMixin {
    @Shadow
    public abstract String getDescriptionId();

    /**
     * Changes the tooltip style for modded attributes.
     */
    @Inject(method = "getStyle", at = @At("HEAD"), cancellable = true)
    private void changeStyle(boolean valueIncrease, CallbackInfoReturnable<ChatFormatting> cir) {
        if (this.isInFormatter(ModAttributes.WHITE_FORMATTERS)) {
            cir.setReturnValue(ChatFormatting.WHITE);
        }
        if (this.isInFormatter(ModAttributes.YELLOW_FORMATTERS)) {
            cir.setReturnValue(ChatFormatting.YELLOW);
        }
        if (this.isInFormatter(ModAttributes.GOLD_FORMATTERS)) {
            cir.setReturnValue(ChatFormatting.GOLD);
        }
        if (this.isInFormatter(ModAttributes.RED_FORMATTERS)) {
            cir.setReturnValue(ChatFormatting.RED);
        }
        if (this.isInFormatter(ModAttributes.DARK_PURPLE_FORMATTERS)) {
            cir.setReturnValue(ChatFormatting.DARK_PURPLE);
        }
        if (this.isInFormatter(ModAttributes.REVERSE_FORMATTERS)) {
            cir.setReturnValue(valueIncrease ? ChatFormatting.RED : ChatFormatting.BLUE);
        }
    }

    /**
     * @return if an attribute's description is in a formatter list.
     */
    @Unique
    private boolean isInFormatter(List<String> formatters) {
        for (String s : formatters) {
            if (getDescriptionId().equals(s)) {
                return true;
            }
        }
        return false;
    }
}