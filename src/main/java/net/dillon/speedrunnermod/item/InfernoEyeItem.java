package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * An {@code eye of ender} item that locates nearby {@code nether fortresses} and {@code bastions.}
 */
public class InfernoEyeItem extends Item implements StateOfTheArtItem {

    public InfernoEyeItem(Settings settings) {
        super(settings.component(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES).fireproof());
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.NETHER) {
                if (player.isSneaking()) {
                    if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.FORTRESSES)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.BASTIONS);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.BASTIONS)) {
                        itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.FORTRESSES);
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                } else {
                    player.sendMessage(this.calculatingText(), false);
                    ModUtil.findStructureAndShoot(world, player, itemStack, itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.located", this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

                    options().tutorialMode.completeStep(TutorialStep.USE_INFERNO_EYE, player,
                            "speedrunnermod.tutorial_mode.used_inferno_eye",
                            options().main.playingMode.easy() ? "speedrunnermod.tutorial_mode.craft_piglin_awakener" :
                                    "speedrunnermod.tutorial_mode.craft_speedrunners_eye");

                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.RED, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))).formatted(Formatting.BOLD));
    }
}