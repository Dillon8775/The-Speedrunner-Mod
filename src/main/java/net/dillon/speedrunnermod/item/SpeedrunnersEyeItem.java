package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * An {@code eye of ender} item that locates {@code most overworld structures.}
 */
public class SpeedrunnersEyeItem extends Item implements EyeItem {

    public SpeedrunnersEyeItem(Settings settings) {
        super(settings.component(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE).rarity(Rarity.RARE));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (world.getRegistryKey() != World.OVERWORLD) {
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.speedrunners_eye.wrong_dimension"), Formatting.AQUA, Formatting.WHITE);
        } else {
            if (player.isSneaking()) {
                if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.VILLAGE)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.RUINED_PORTAL);
                    this.playWorldSound(SoundEvents.BLOCK_PORTAL_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.RUINED_PORTAL)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.SHIPWRECK);
                    this.playWorldSound(SoundEvents.ENTITY_BOAT_PADDLE_WATER, 5.0F, 1.0F, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.SHIPWRECK)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_OCEAN_EXPLORER_MAPS);
                    this.playWorldSound(SoundEvents.ENTITY_ELDER_GUARDIAN_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_OCEAN_EXPLORER_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_WOODLAND_EXPLORER_MAPS);
                    this.playWorldSound(SoundEvents.ENTITY_VINDICATOR_AMBIENT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_WOODLAND_EXPLORER_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.DESERT_PYRAMIDS);
                    this.playWorldSound(SoundEvents.BLOCK_SAND_PLACE, 3.0F, 1.0F, world, player);
                    ModUtil.completeStepS2C(TutorialStep.CHANGE_SPEEDRUNNERS_EYE_LOCATOR, player, "speedrunnermod.tutorial_mode.use_speedrunners_eye");
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.DESERT_PYRAMIDS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, ModStructureTags.ANCIENT_CITIES);
                    this.playWorldSound(SoundEvents.ENTITY_WARDEN_HEARTBEAT, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(ModStructureTags.ANCIENT_CITIES)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.ON_TRIAL_CHAMBERS_MAPS);
                    this.playWorldSound(SoundEvents.ENTITY_BREEZE_CHARGE, world, player);
                } else if (itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE).equals(StructureTags.ON_TRIAL_CHAMBERS_MAPS)) {
                    itemStack.set(ModDataComponentTypes.LOCATING_STRUCTURE, StructureTags.VILLAGE);
                    this.playWorldSound(SoundEvents.ENTITY_VILLAGER_AMBIENT, world, player);
                }

                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.eye.looking_for", this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
            } else {
                player.sendMessage(ModTexts.CALCULATING, false);
                ServerWorld serverWorld = (ServerWorld)world;
                BlockPos playerpos = player.getBlockPos();
                ModUtil.findStructureAndShoot(world, player, itemStack, itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE));
                BlockPos blockPos = serverWorld.locateStructure(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE), playerpos, 100, false);
                int structureDistance = MathHelper.floor(ModUtil.getDistance(playerpos.getX(), playerpos.getZ(), blockPos.getX(), blockPos.getZ()));
                this.playPitchedLaunchSound(0.4F, world, player);
                ModUtil.sendMessageWithActionbarPref(player, this.locationText(structureDistance, this.structureTexts(itemStack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));

                String[] messages = !isBalancedMode() ?
                        new String[]{"speedrunnermod.tutorial_mode.craft_dragons_pearl",
                                "speedrunnermod.tutorial_mode.dragons_pearl_recipe"} :
                        new String[]{"speedrunnermod.tutorial_mode.craft_ender_eye"};
                ModUtil.completeStepS2C(TutorialStep.USE_SPEEDRUNNERS_EYE, player, messages);

                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            player.swingHand(hand, true);
            return ActionResult.SUCCESS;
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunners_eye.tooltip").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.eye.looking_for.tooltip", this.structureTexts(stack.get(ModDataComponentTypes.LOCATING_STRUCTURE))));
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{};
    }
}