package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An {@code eye of ender} item that locates nearby {@code nether fortresses} and {@code bastions.}
 */
public class InfernoEyeItem extends Item implements StateOfTheArtItem, TooltipAppender {
    private TagKey<Structure> structureType = ModStructureTags.FORTRESSES;

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
                    if (structureType.equals(ModStructureTags.FORTRESSES)) {
                        structureType = ModStructureTags.BASTIONS;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    } else if (structureType.equals(ModStructureTags.BASTIONS)) {
                        structureType = ModStructureTags.FORTRESSES;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(structureType)), options().client.itemMessages.isActionbar());
                } else {
                    player.sendMessage(this.calculatingText(), false);
                    ModUtil.findStructureAndShoot(world, player, itemStack, structureType);
                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.located", this.structureTexts(structureType)), options().client.itemMessages.isActionbar());
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
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        if (options().client.itemTooltips) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        }
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(structureType)).formatted(Formatting.BOLD));
    }
}