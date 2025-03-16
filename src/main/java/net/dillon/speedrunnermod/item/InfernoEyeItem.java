package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An {@code eye of ender} item that locates nearby {@code nether fortresses} and {@code bastions.}
 */
public class InfernoEyeItem extends Item implements TutorialMode {
    private String structureType = "Fortress";
    private TagKey<Structure> type = ModStructureTags.FORTRESSES;

    public InfernoEyeItem(Settings settings) {
        super(settings.fireproof());
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (world.getRegistryKey() == World.NETHER) {
                if (player.isSneaking()) {
                    if (structureType.equals("Fortress")) {
                        structureType = "Bastion";
                        type = ModStructureTags.BASTIONS;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    } else if (structureType.equals("Bastion")) {
                        structureType = "Fortress";
                        type = ModStructureTags.FORTRESSES;
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.HOSTILE, 2.0F, 1.0F);
                    }

                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                } else {
                    ItemUtil.findStructureAndShoot(world, player, itemStack, type);
                    player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.located", structureType).formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
                    if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && !options().tutorialMode.usedInfernoEye) {
                        this.send("speedrunnermod.tutorial_mode.used_inferno_eye", player);
                        this.send("speedrunnermod.tutorial_mode.obtain_piglin_awakener", player);
                        options().tutorialMode.usedInfernoEye = true;
                        ModOptions.saveConfig();
                    }

                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }
                }

                player.incrementStat(Stats.USED.getOrCreateStat(this));
                player.swingHand(hand, true);
                return ActionResult.SUCCESS;
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.eye_of_inferno.wrong_dimension").formatted(ItemUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.tooltip"));
        }
        tooltip.add(Text.translatable("item.speedrunnermod.eye_of_inferno.looking_for.tooltip", structureType).formatted(Formatting.BOLD));
    }
}