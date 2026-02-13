package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * An {@code eye of ender} item that locates nearby {@code nether fortresses} and {@code bastions.}
 */
public class InfernoEyeItem extends Item implements EyeItem {

    public InfernoEyeItem(Settings settings) {
        super(settings.component(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES).fireproof());
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (world.getRegistryKey() != World.NETHER) {
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension"), Formatting.RED, Formatting.WHITE);
        } else {
            if (player.isSneaking()) {
                if (stack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.FORTRESSES)) {
                    stack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.BASTIONS);
                    this.playWorldSound(SoundEvents.ENTITY_PIGLIN_AMBIENT, 2.0F, 1.0F, world, player);
                } else if (stack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.BASTIONS)) {
                    stack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES);
                    this.playWorldSound(SoundEvents.ENTITY_BLAZE_AMBIENT, 2.0F, 1.0F, world, player);
                }

                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
            } else {
                player.sendMessage(ModTexts.CALCULATING, false);
                ModUtil.findStructureAndShoot(world, player, stack, stack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye_of_inferno.located", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
                this.playWorldSound(SoundEvents.ITEM_FIRECHARGE_USE, 0.5F, 1.0F, world, player);
                this.decrementIfPossible(player, stack);
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))).formatted(Formatting.BOLD));
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{};
    }
}