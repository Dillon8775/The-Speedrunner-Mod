package net.dillon.speedrunnermod.mixin.main.item;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.TutorialMode;
import net.dillon.speedrunnermod.util.TutorialStep;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Item.class)
public class ItemMixin {

    /**
     * Main tutorial mode stuff.
     */
    @Unique
    private void tutorialMode(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (options().main.tutorialMode) {
            if (options().main.playingMode.easy()) {
                if (entity instanceof ServerPlayerEntity player) {

                    if (stack.isOf(ModItems.SPEEDRUNNER_PICKAXE)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_SPEEDRUNNER_PICKAXE, player,
                                "speedrunnermod.tutorial_mode.obtained_speedrunner_pickaxe",
                                "speedrunnermod.tutorial_mode.obtain_speedrunner_boat");
                    }

                    if (stack.isOf(ModItems.SPEEDRUNNER_BOAT)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_SPEEDRUNNER_BOAT, player,
                                "speedrunnermod.tutorial_mode.obtained_speedrunner_boat",
                                "speedrunnermod.tutorial_mode.obtain_inferno_eye.easy");
                    }

                    if (stack.isOf(ModItems.INFERNO_EYE)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_INFERNO_EYE, player, "speedrunnermod.tutorial_mode.obtained_inferno_eye.easy");
                    }

                    if (stack.isOf(ModItems.PIGLIN_AWAKENER)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_PIGLIN_AWAKENER, player, "speedrunnermod.tutorial_mode.obtained_piglin_awakener.easy");
                    }

                    if (stack.isOf(ModItems.BLAZE_SPOTTER)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_BLAZE_SPOTTER, player, "speedrunnermod.tutorial_mode.obtained_blaze_spotter.easy");
                    }

                    if (stack.isOf(ModItems.SPEEDRUNNERS_EYE)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_SPEEDRUNNERS_EYE, player, "speedrunnermod.tutorial_mode.obtained_speedrunners_eye.easy");
                    }

                    if (stack.isOf(ModItems.DRAGONS_PEARL)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_DRAGONS_PEARL, player,
                                "speedrunnermod.tutorial_mode.obtained_dragons_pearl.easy",
                                "speedrunnermod.tutorial_mode.obtain_annul_eye.easy");
                    }

                    if (stack.isOf(ModItems.ANNUL_EYE)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_ANNUL_EYE, player, "speedrunnermod.tutorial_mode.obtained_annul_eye.easy");
                    }

                    if (stack.isOf(ModItems.SPEEDRUNNERS_WORKBENCH)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_SPEEDRUNNERS_WORKBENCH, player,
                                "speedrunnermod.tutorial_mode.obtained_speedrunners_workbench",
                                "speedrunnermod.tutorial_mode.transfer_enchantments");
                    }

                    if (stack.isOf(ModItems.ENDER_THRUSTER)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_ENTER_THRUSTER, player, "speedrunnermod.tutorial_mode.obtained_ender_thruster.easy");
                    }

                    if (stack.isOf(ModItems.DRAGONS_SWORD)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_DRAGONS_SWORD, player,
                                "speedrunnermod.tutorial_mode.obtained_dragons_sword.easy",
                                "speedrunnermod.tutorial_mode.obtain_wither_bone");
                    }

                    if (stack.isOf(ModItems.WITHER_BONE)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_WITHER_BONE, player, "speedrunnermod.tutorial_mode.obtained_wither_bone");
                    }

                    if (stack.isOf(ModItems.WITHER_SWORD)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_WITHER_SWORD, player,
                                "speedrunnermod.tutorial_mode.obtained_wither_sword",
                                "speedrunnermod.tutorial_mode.almost_done.easy",
                                "speedrunnermod.tutorial_mode.obtain_ender_matter");
                    }

                    if (stack.isOf(ModItems.ENDER_MATTER)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_ENDER_MATTER, player, "speedrunnermod.tutorial_mode.obtained_ender_matter");
                    }

                    if (stack.isOf(ModItems.INFINI_PEARL)) {
                        options().tutorialMode.completeStep(TutorialStep.OBTAINED_INFINI_PEARL, player, "speedrunnermod.tutorial_mode.completed.easy");
                    }
                }
            }
        }
    }

    /**
     * For tutorial mode and {@code Expert Shepherd} advancement.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void modifiedInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (entity instanceof ServerPlayerEntity player) {
            int j = 0;
            for (int i = 0; i < player.getInventory().size(); i++) {
                if (player.getInventory().getStack(i).isOf(Items.LIME_WOOL)) {
                    j += player.getInventory().getStack(i).getCount();
                }
                if (j >= 64) {
                    ModCriterions.TRIGGERED_BY_ITEM.trigger(player, Items.LIME_WOOL.getDefaultStack());
                    break;
                }
            }
        }
        this.tutorialMode(stack, world, entity, slot, selected, ci);
    }

    /**
     * For tutorial mode.
     */
    @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
    private void modifiedOnCraftByPlayer(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
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
            if (stack.isOf(ModItems.SPEEDRUNNERS_WORKBENCH)) {
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