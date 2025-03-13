package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.option.ModOptions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The speedrunner pickaxe.
 */
public class SpeedrunnerPickaxeItem extends PickaxeItem implements TutorialItem {

    public SpeedrunnerPickaxeItem() {
        super(ModToolMaterials.SPEEDRUNNER_SWORD_PICKAXE, 3, -2.8F, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, ofSpeedrunnerMod("speedrunner_pickaxe"))));
    }

    /**
     * Sends the tutorial message upon first inventory tick.
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!options().tutorialMode.obtainedSpeedrunnerPickaxe && entity instanceof PlayerEntity player) {
            this.sendTutorialMessage(player);
        }
        options().tutorialMode.obtainedSpeedrunnerPickaxe = true;
        ModOptions.saveConfig();
    }

    /**
     * Sends the tutorial message to the player upon crafting.
     */
    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        if (!options().tutorialMode.obtainedSpeedrunnerPickaxe) {
            this.sendTutorialMessage(player);
        }
        options().tutorialMode.obtainedSpeedrunnerPickaxe = true;
        ModOptions.saveConfig();
    }

    @Override
    public void sendTutorialMessage(PlayerEntity player) {
        this.withPrefix("speedrunnermod.tutorial_mode.obtained_speedrunner_pickaxe", player);
    }
}