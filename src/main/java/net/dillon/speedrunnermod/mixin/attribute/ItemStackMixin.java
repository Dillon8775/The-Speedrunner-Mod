package net.dillon.speedrunnermod.mixin.attribute;

import net.dillon.speedrunnermod.component.ModAttributeKeys;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerBowItem;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerCrossbowItem;
import net.dillon.speedrunnermod.tag.ModAttributeTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import org.apache.commons.lang3.function.TriConsumer;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract void forEachModifier(EquipmentSlotGroup slot, TriConsumer<Holder<Attribute>, AttributeModifier, ItemAttributeModifiers.Display> consumer);

    /**
     * Modifies attribute tooltips to show modded attribute groups.
     */
    @Inject(method = "addAttributeTooltips", at = @At(value = "HEAD"), cancellable = true)
    private void modifyAttributeGroups(Consumer<Component> consumer, TooltipDisplay display, @Nullable Player player, CallbackInfo ci) {
        if (display.shows(DataComponents.ATTRIBUTE_MODIFIERS)) {
            Map<String, List<ModifierEntry>> grouped = new LinkedHashMap<>();
            for (EquipmentSlotGroup slot : EquipmentSlotGroup.values()) {

                this.forEachModifier(slot, (attribute, modifier, tooltip) -> {
                    if (tooltip == ItemAttributeModifiers.Display.hidden()) {
                        return;
                    }

                    String group;

                    if (attribute.is(ModAttributeTags.WHEN_SHOT)) {
                        group = "shot";
                    } else if (attribute.is(ModAttributeTags.WHEN_THROWN)) {
                        group = "thrown";
                    } else if (attribute.is(ModAttributeTags.WHEN_MINING)) {
                        group = "mining";
                    } else if (attribute.is(ModAttributeTags.WHEN_RIDDEN)) {
                        group = "ridden";
                    } else if (attribute.is(ModAttributeTags.UPON_DEATH)) {
                        group = "upon_death";
                    } else {
                        group = slot.getSerializedName();
                    }

                    ItemStack self = (ItemStack)(Object)this;
                    Item item = self.getItem();
                    if (attribute.is(ModAttributeKeys.ADDITIONAL_RANGE) && (item instanceof SpeedrunnerBowItem || item instanceof SpeedrunnerCrossbowItem)) {
                        group = "shot";
                    } else if (attribute.is(ModAttributeKeys.ADDITIONAL_COOLDOWN)) {
                        group = item instanceof ShieldItem ? "offhand" : "mainhand";
                    }

                    grouped.computeIfAbsent(group, g -> new ArrayList<>())
                            .add(new ModifierEntry(attribute, modifier, tooltip));
                });

            }
            for (Map.Entry<String, List<ModifierEntry>> entry : grouped.entrySet()) {
                consumer.accept(CommonComponents.EMPTY);
                consumer.accept(Component.translatable("item.modifiers." + entry.getKey()).withStyle(ChatFormatting.GRAY));
                for (ModifierEntry e : entry.getValue()) {
                    e.tooltip.apply(consumer, player, e.attribute, e.modifier);
                }
            }
        }

        ci.cancel();
    }

    private record ModifierEntry(
            Holder<Attribute> attribute,
            AttributeModifier modifier,
            ItemAttributeModifiers.Display tooltip
    ) {}
}