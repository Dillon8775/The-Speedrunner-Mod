package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.TutorialItem;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin implements TutorialItem {

    /**
     * Main tutorial mode stuff.
     */
    @Unique
    private void tutorialMode(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (!options().main.playingMode.doom()) {
            if (entity instanceof PlayerEntity player) {

                if (stack.isOf(ModItems.SPEEDRUNNER_PICKAXE)) {
                    if (!options().tutorialMode.obtainedSpeedrunnerPickaxe) {
                        this.send("speedrunnermod.tutorial_mode.obtained_speedrunner_pickaxe", player);
                        this.send("speedrunnermod.tutorial_mode.obtain_speedrunner_boat", player);
                        options().tutorialMode.obtainedSpeedrunnerPickaxe = true;
                        ModOptions.saveConfig();
                    }
                }

                if (stack.isOf(ModItems.SPEEDRUNNER_BOAT)) {
                    if (options().tutorialMode.obtainedSpeedrunnerPickaxe && !options().tutorialMode.obtainedSpeedrunnerBoat) {
                        this.send("speedrunnermod.tutorial_mode.obtained_speedrunner_boat", player);
                        this.send("speedrunnermod.tutorial_mode.obtain_inferno_eye", player);
                        options().tutorialMode.obtainedSpeedrunnerBoat = true;
                        ModOptions.saveConfig();
                    }
                }

                if (stack.isOf(ModItems.INFERNO_EYE)) {
                    if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && !options().tutorialMode.obtainedInfernoEye) {
                        this.send("speedrunnermod.tutorial_mode.obtained_inferno_eye", player);
                        options().tutorialMode.obtainedInfernoEye = true;
                        ModOptions.saveConfig();
                    }
                }
            }
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void tutorialModeUse(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (!options().main.playingMode.doom()) {

            if (stack.isOf(ModItems.INFERNO_EYE)) {
                if (options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && !options().tutorialMode.usedInfernoEye) {
                    this.send("speedrunnermod.tutorial_mode.used_inferno_eye", player);
                    options().tutorialMode.usedInfernoEye = true;
                    ModOptions.saveConfig();
                }
            }
        }
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void inventoryTickTutorialMode(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        this.tutorialMode(stack, world, entity, slot, selected, ci);
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
    private void onCraftByPlayerTutorialMode(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        this.tutorialMode(stack, world, player, 0, false, ci);
    }

    /**
     * Adds tooltips to items that can be used to craft the {@code piglin awakener.}
     */
    @Inject(method = "appendTooltip", at = @At("TAIL"))
    private void appendTooltips(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (options().client.itemTooltips) {
            if (stack.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
                tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener_craftable").formatted(Formatting.GOLD));
            }
            if (stack.isOf(ModBlockItems.SPEEDRUNNERS_WORKBENCH)) {
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line1").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line3").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("item.speedrunnermod.speedrunners_workbench.tooltip.line4").formatted(Formatting.GRAY));
            }
            if (stack.isOf(Items.ENCHANTED_BOOK)) {
                ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(stack);
                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : itemEnchantmentsComponent.getEnchantmentEntries()) {
                    if (entry.getKey().matchesKey(ModEnchantments.DASH)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.dash.tooltip").formatted(Formatting.GRAY));
                    }
                    if (entry.getKey().matchesKey(ModEnchantments.COOLDOWN)) {
                        tooltip.add(Text.translatable("enchantment.speedrunnermod.cooldown.tooltip").formatted(Formatting.GRAY));
                    }
                }
            }
            if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
                tooltip.add(Text.translatable("item.totem_of_undying.tooltip"));
            }
            if (stack.isIn(ModItemTags.FIREPROOF_BOATS) || stack.isIn(ModItemTags.FIREPROOF_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip").formatted(Formatting.GRAY));
            }
            if (stack.isIn(ModItemTags.FASTER_BOATS) || stack.isIn(ModItemTags.FASTER_CHEST_BOATS)) {
                tooltip.add(Text.translatable("item.speedrunnermod.boat.tooltip.fast").formatted(Formatting.GRAY));
            }
        }
        if (options().client.textureTooltips) {
            if (stack.isIn(ModItemTags.Block.TEXTURE_CREATOR_MANNYQUESO)) {
                tooltip.add(Text.translatable("speedrunnermod.texture_creator.mannyqueso"));
            }
        }
    }
}