package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

/**
 * The dragon's fireball, and you can literally throw them.
 */
public class DragonFireballItem extends Item {

    public DragonFireballItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC));
    }

    /**
     * Creates the fireball entity and shoots it!
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (ModUtil.createFireball(this, world, player, hand, true)) {
            return ActionResult.SUCCESS_SERVER;
        }
        return super.use(world, player, hand);
    }
}