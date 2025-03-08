package net.dillon.speedrunnermod.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * An infini pearl item, but just craftable. And this version of the pearl has durability.
 */
public class CraftableInfiniPearlItem extends InfiniPearlItem {

    public CraftableInfiniPearlItem(Settings settings) {
        super(settings.maxDamage(128));
    }

    /**
     * Decrement durability.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        stack.damage(1, player, EquipmentSlot.MAINHAND);
        return super.use(world, player, hand);
    }
}